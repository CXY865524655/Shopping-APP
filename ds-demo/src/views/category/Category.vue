<template>
    <div id="category">
        <search-header></search-header>

        <!--头部-->
        <!-- <Header></Header> -->
        <!--内容-->
        <div class="listWrapper"
            v-if="true">
          <!--左边-->
          <div class="leftWrapper">
            <ul class="wrapper">
              <li class="categoryItem"
                  v-for="(category, index) in categoryList"
                  :key="index"
                  :class="{ selected: categoryIndex === index+1 }"
                  @click.stop="clickLeftCategory(index+1)"
                  ref="leftCategory">
                  
                <span class="textWrapper">{{category.categoryname}}</span>
              </li>
              
            </ul>
          </div>
          <!-- 子标签 -->
            <right-content 
              :isShowLoading.sync="isShowLoading" 
              :index="categoryIndex" 
              style="width:79%;" 
              :subCategoryByIndex="subCategoryByIndex" 
              :subProductList="subProductList"
              :rightNum.sync="rightNum"
              />
        </div>
        <!-- 加载动画 -->
        <!-- <Loading :show="isShowLoading" /> -->
        <!-- 点击左边按钮加载数据时候显示动画 -->
        <!-- <LoadingGif v-show="isShowLoadingGif" /> -->
        <!-- <van-loading type="spinner" v-show="aa" style="width: 100%;height: 100%;"/> -->
        <!-- <loading></loading> -->
        <DoubleBounce v-show="isShowLoading" style="width:100px;height:100px;left:0;right:0;bottom:0;top:0;position: absolute; margin: auto;"></DoubleBounce>

    </div>
</template>



<script>
import SearchHeader from './components/SearchHeader'
import RightContent from './components/RightContent'
// import RightSubCategory from './components/RightSubCategory.vue'
// import Loading from '@/components/common/Loading.vue'
import BScroll from 'better-scroll'
import { vuexData } from '@/assets/js/mixin.js'
import { DoubleBounce } from 'vue-loading-spinner'
import Vue from 'vue'

// import MenuList from '@/views/category/components/MenuList'
export default {
    name: 'Category',
    mixins: [vuexData],
    components: {
        SearchHeader,
        RightContent,
        // RightSubCategory,
        // Loading,
        DoubleBounce
    },
    data(){
      return {
        rightNum: 1,
        aa:true,
        isShowLoading: false,
        categoryIndex: 0,
        categoryList: [],        // 大标签列表
        subCategoryByIndex: [],  // 大标签对应的小标签列表
        subProductList: []       // 小标签对应的商品列表
      }
    },
    activated(){
      // this.$nextTick(()=>{
      //   this.initProductScroll()
      // })
    },
    methods: {
      async changeCategoryIndex(index) {
        this.categoryIndex = index
      },

      async initCategory() {
        if(this.$route.query && this.$route.query.xz) {
            this.isShowLoading = true
            let xz = JSON.parse(this.$route.query.xz)
            let categoryId = xz.categoryId
            let subCategoryId = xz.subCategoryId

            await this.$api.goods.initCategory()
              .then(res=> {
                      this.categoryList = res.data.data.categoryList
                      this.categoryIndex = categoryId
                      this.rightNum = subCategoryId
              })

              this.getSubProductList(categoryId,subCategoryId)
            // await this.$api.goods.getSubProductList(categoryId, subCategoryId)
            // .then(res => {
            //     this.$nextTick(() => {
            //         this.subProductList = res.data.data.subProductList
            //         this.initProductScroll();
            //         let categoryNow = {
            //             categoryId,
            //             subCategoryId
            //         }
            //         this.SET_CATEGORY_NOW({
            //             categoryNow
            //         })
            //     })
            // })
            
            this.isShowLoading = false
        }else {
            this.categoryIndex = this.GET_CATEGORY_NOW.categoryId?this.GET_CATEGORY_NOW.categoryId:1
            this.isShowLoading = true
            await this.$api.goods.initCategory()
              .then(res=> {
                  this.categoryList = res.data.data.categoryList
                  if(this.GET_CATEGORY_NOW) {
                      this.getSubProductList(this.GET_CATEGORY_NOW.categoryId,this.GET_CATEGORY_NOW.subCategoryId)
                      this.categoryIndex = this.GET_CATEGORY_NOW.categoryId
                      this.rightNum = this.GET_CATEGORY_NOW.subCategoryId
                  }else {
                    this.getSubProductList(1, 1)
                  }
              })
              this.isShowLoading = false
        }
      },
      async init() {
          this.$nextTick(()=>{
              this.SET_IS_CHOOSE_CATEGORY({
                choose: false
              })
              this.initCategory()
            })    
      },
      async clickLeftCategory(index) {
        this.SET_IS_CHOOSE_CATEGORY({
          choose: true
        })
        this.categoryIndex = index
        await this.getSubProductList(index, 1)
        this.productScroll.refresh()
        this.productScroll.scrollToElement(this.$refs.leftCategory[index], 300,100,-250)
      },

      async jiexiImgs(item){
          let imgPath = item.image
                  let i = imgPath.indexOf(';')
                  if(i >= 0) {
                      // 多个
                      let paths = imgPath.split(';')
                      paths.forEach(async (v,i) => {
                          await this.$api.goods.getGoodImg(v)
                          .then(res => {
                              return 'data:image/png;base64,' + btoa(
                                  new Uint8Array(res.data)
                                  .reduce((data, byte) => data + String.fromCharCode(byte), '')
                              )
                          })
                          .then(data => {
                            if(i===0) {
                              Vue.set(item, 'imageShow', data)
                            }
                              item.imgss.push(data)
                          })
                      })
                      

                  }else if(imgPath.length>=1){
                      // 一个
                      this.$api.goods.getGoodImg(imgPath)
                          .then(res => {
                              return 'data:image/png;base64,' + btoa(
                                  new Uint8Array(res.data)
                                  .reduce((data, byte) => data + String.fromCharCode(byte), '')
                              )
                          })
                          .then(data => {
                              Vue.set(item, 'imageShow', data)
                              item.imgss.push(data)
                          })
                  }
      },

      async jiexiDetail(item){
                        let detailss = item.details
                        let iD = detailss.indexOf(';')
                        if(iD >= 0) {
                            // 多个
                            let pathss = detailss.split(';')
                            pathss.forEach(async  v  => {
                                await this.$api.goods.getDetailImg(v)
                                .then(res => {
                                    return 'data:image/png;base64,' + btoa(
                                        new Uint8Array(res.data)
                                        .reduce((data, byte) => data + String.fromCharCode(byte), '')
                                    )
                                })
                                .then(data => {
                                    item.detailsShow.push(data)
                                })
                            })
                        }else if(detailss.length>=1){
                            // 一个
                            this.$api.goods.getDetailImg(detailss)
                                .then(res => {
                                    return 'data:image/png;base64,' + btoa(
                                        new Uint8Array(res.data)
                                        .reduce((data, byte) => data + String.fromCharCode(byte), '')
                                    )
                                })
                                .then(data => {
                                    item.detailsShow.push(data)
                                })
                        }
      },
      async getSubProductList(categoryId, subCategoryId) {
        this.SET_LOADING({
          loading: true
        })

        await this.$api.goods.getSubProductList(categoryId, subCategoryId)   
          .then(async res => {
              let pro = JSON.parse(JSON.stringify(res.data.data.subProductList))
              this.subCategoryByIndex = JSON.parse(JSON.stringify(res.data.data.subCategoryModelList))
              if(pro&&pro.length>=1) {
                    await pro.forEach(async item =>{
                        Vue.set(item, 'imageShow', '')
                        Vue.set(item, 'imgss', [])
                        Vue.set(item, 'detailsShow',[])
                        await this.jiexiImgs(item)
                        await this.jiexiDetail(item)
                    })
                    this.subProductList = pro
                }else {
                  this.subProductList = []
                }
              
              this.initProductScroll()

              this.SET_LOADING({
                loading: false
              })

              let categoryNow = {
                categoryId,
                subCategoryId,
              }
              this.SET_CATEGORY_NOW({
                categoryNow: categoryNow
              })

          })
      },
    initProductScroll() {
            if(!this.productScroll) {
                
                this.productScroll = new BScroll('.leftWrapper', {
                    probeType: 3,    // 除了实时派发scroll事件，在swipe的情况下仍然能实时派发scroll事件
                    click: true,     // 是否派发click事件，通常判断浏览器派发的click还是betterscroll派发的click，可以用_constructed，若是bs派发的则为true
                    scrollY: true,   // true 滚动方向为 Y 轴
                    tap: true,   // better-scroll 会阻止原生的 click 事件，我们可以设置 tap 为 true
                    mouseWheel: true, // 启动鼠标滚轴
                    bounce:true,    // 上拉下拉的回滚效果，默认为 true

                    // 上拉加载
                    pullUpLoad: {
                        threshold: -60,
                        stop: -20
                    },

                    // 下拉刷新
                    pullDownRefresh: {
                        threshold: 60,
                        stop: 20
                    }
                })
                

            this.productScroll.on('pullingDown', () => {
                
                // this.$toast('下拉加载中...')
                // 事情做完，需要调用此方法告诉 better-scroll 数据已加载，否则下拉事件只会执行一次
                    this.productScroll.finishPullDown()

            })

            this.productScroll.on('pullingUp', () => {
                
                
                this.isNotAdding = false
                this.isEnding = false
                this.isPullLoading = true
                    this.$nextTick(()=> {
                        setTimeout(() => {
                            this.categoryList = this.categoryList2
                            this.productScroll.refresh()
                            // this.$toast('加载成功')
                
                            // 事情做完，需要调用此方法告诉 better-scroll 数据已加载，否则上拉事件只会执行一次
                            this.isNotAdding = false
                            this.isEnding = true
                            this.isPullLoading = false
                            // console.log(this.$refs.lis[this.$refs.lis.length-1])
                            // 2.1 进入默认商品列表滚到顶部
                            // this.productScroll.refresh()
                            // this.productScroll.scrollToElement(this.$refs.lis[13], 10, 0, 0);
                            this.productScroll.finishPullUp()
                        }, 2000);
                    })
                })
            }else{
                this.productScroll.refresh();
            }
        },
    },
    mounted() {

      this.$nextTick(()=>{
         this.init()
      })
    },
    watch:{
      
    }
}
</script>

<style scoped>
#category {
  width: 100%;
  height: 100%;
  background-color: white;
  overflow: hidden;
}

.menu-list{
  width: 100%;
  height: 100%;

}


.listWrapper {
  display: flex;
  position: absolute;
  top: 2.75rem;
  bottom: 3rem;
  width: 100%;
  overflow: hidden;
}

.leftWrapper {
  background-color: #f4f4f4;
  width: 5.8125rem;
  flex: 0 0 5.8125rem;
  text-align: left;
}

.categoryItem {
  padding: 1.0rem 0;
  border-bottom: solid 0.01rem #e8e9e8;
  position: relative;
  /* height: 1.3rem; */
  

}

.categoryItem .textWrapper {
  line-height: 1.25rem;
  border-left: solid 0.1875rem transparent;
  padding: 0.3125rem 0.6875rem;
  font-size: 0.91rem;
  color: #666666;
}

.categoryItem.selected {
  background: #fff;
}

.categoryItem.selected .textWrapper {
  border-left-color: #3cb963;
  font-weight: bold;
  font-size: 1.0rem;
  color: #333333;
}

@media (min-width: 960px) {
  .wrapper {
    border-right: 0.01rem solid #e8e9e8;
  }

  .wrapper .categoryItem {
    background: #fff;
  }
}
</style>