<template>
    <div class="aa">
        <right-sub-category :isShowLoading.sync="isLoading" :rightContent="subCategoryList" :rightSubCNum.sync="rightSubCNum"/>
        <div class="wrap">
            <ul>
                <li ref="lis"
                    v-for="(category, index) in subProducts"
                    :key="index"
                    :class="{betweenProduct: index!==0}"
                    @click.stop="toDetail(category)"
                >
                    <van-card
                        :price="category.mallprice%1===0?category.mallprice+'.00':category.mallprice.toString().split('.')[1].length===1?category.mallprice+'.0':category.mallprice"
                        :origin-price="category.price%1===0?category.price+'.00':category.price.toString().split('.')[1].length===1?category.price+'.0':category.price"
                        :desc="category.describes"
                        :title="category.goodsname"
                    >
                        <div slot="thumb" >
                            <van-image
                                v-if="category.imageShow"
                                width="90"
                                height="90"
                                :src="category.imageShow"
                            />
                        </div>
                    </van-card>
                    
                </li>
                <li>
                    <div class="bottom-msg" v-show="showBottomText">
                        <span v-show="isEnding">- 到底了 -</span>
                        <span v-show="isNotAdding">- 加载更多 -</span>
                        <loading v-show="isPullLoading" :loadingWord="PullingUpWord" :text="''"></loading>
                    </div>
                </li> 
            </ul>
        </div>
    </div>
    
</template>

<script>
import BScroll from 'better-scroll'
import loading from '@/components/common/CategoryLoading.vue'
import RightSubCategory from './RightSubCategory.vue'
import {mapGetters, mapMutations} from 'vuex'
import Vue from 'vue'

export default {
    
    data() {
        return {
            isLoading: '',
            tabHeight: 126,
            showBottomText: false,
            categoryIndex: this.index,
            rightSubCNum: 1,    // 子菜单的选中num
            rightContent:[],
            subCategoryList: [],
            subProducts: [],
            
            pulldownTip: {
                text: '下拉刷新', // 松开立即刷新
                textup: '上拉加载更多', // 松开立即刷新
                rotate: '' // icon-rotate
            },
            isPullLoading: false,   // 加载中
            isNotAdding: true,         // 未加载
            isEnding: false,        // 到底了
            pullupMsg: '加载更多',
            PullingUpWord: '正在拼命加载中...'
        }
    },
    props:{
        index: Number,
        subCategoryByIndex: Array,
        subProductList: Array,
        isShowLoading: Boolean,
        rightNum: Number
    },
    methods: {
        ...mapMutations([
            'SET_CATEGORY_NOW'
        ]),

        init(){
            this.subCategoryList = this.subCategoryByIndex
            this.subProducts = this.subProductList
            this.categoryIndex = this.index
            if(this.subProducts.length * this.tabHeight >= 630) {
                this.showBottomText = true
            }else{
                this.showBottomText = false
            }
        },

        toDetail(item){
            
            this.$router.push({ 
                name: 'Product',
                query: {
                    goodInfo: JSON.stringify(item),
                    imgs: JSON.stringify(item.imgss),
                    detailsShow: JSON.stringify(item.detailsShow)
                }
            })

        },

        updateDom() {
            this.$forceUpdate()
        },

        initProductScroll() {
            if(!this.productScroll) {
                
                this.productScroll = new BScroll('.wrap', {
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
        async jiexiImgs(item){
          let imgPath = item.image
                        let i = imgPath.indexOf(';')
                        if(i >= 0) {
                            // 多个
                            let paths = imgPath.split(';')
                            let p = paths[0]
                            paths.forEach((item,index) => {
                                this.$api.goods.getGoodImg(p)
                                .then(res => {
                                    return 'data:image/png;base64,' + btoa(
                                        new Uint8Array(res.data)
                                        .reduce((data, byte) => data + String.fromCharCode(byte), '')
                                    )
                                })
                                .then(data => {
                                    if(index===0) {
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
            this.isLoading = true
            await this.$api.goods.getSubProductList(categoryId, subCategoryId)
            .then(res => {
                this.$nextTick(() => {
                    this.subProducts = JSON.parse(JSON.stringify(res.data.data.subProductList))
                    if(this.subProducts.length * this.tabHeight >= 630) {
                        this.showBottomText = true
                    }else{
                        this.showBottomText = false
                    }
                if(this.subProducts && this.subProducts.length >= 1) {
                    this.subProducts.forEach(async item  => {
                        Vue.set(item, 'imageShow', '')
                        Vue.set(item, 'imgss', [])
                        Vue.set(item, 'detailsShow',[])
                        await this.jiexiImgs(item)
                        await this.jiexiDetail(item)
                    })
                }

                    this.initProductScroll();
                    this.isLoading = false
                    let categoryNow = {
                        categoryId,
                        subCategoryId
                    }
                    this.SET_CATEGORY_NOW({
                        categoryNow
                    })
                })
            })
            
        }
    },
    mounted() {
        this.$nextTick(() => {
            this.init();
            this.initProductScroll();
        })
    },
    components: {
        loading,
        RightSubCategory
    },
    watch: {
        rightNum(newVal) {
            this.rightSubCNum = newVal
        },
        rightSubCNum(newVal){
            this.getSubProductList(this.categoryIndex, newVal)
        },
        isShowLoading(newVal) {
            this.isLoading = newVal
        },

        isLoading(newVal) {
            this.$emit("update:isShowLoading", newVal)
        },

        index(newVal) {
            this.categoryIndex = newVal
        },

        subCategoryByIndex(newVal) {
            this.subCategoryList = newVal
        },

        subProductList(newVal) {
            this.subProducts = newVal
            this.$nextTick(() => {
                this.init();    
                this.initProductScroll();
            })
        },

        

    },
    computed: {
        ...mapGetters(['GET_COLLECT_LIST'])
    }
}
</script>

<style lang="less" scoped>
    .aa {
        width: 100%;
        height: 100%;
        margin-bottom: 3.2rem;
        .betweenProduct{
            margin-top: 3px;
        }

        .wrap {
            width: 100%;
            height: 95%;
            margin-bottom: 3.2rem;
        }
    }
    
    .bottom-tip{
        width: 100%;
        height: 35px;
        line-height: 35px;
        text-align: center;
        color: #777;
        background: #f2f2f2;
        position: absolute;
        /* bottom: -35px; */
        left: 0;
    }

    .bottom-msg {
        text-align: center;
        z-index: 100;
        width: 100%;
        height: 20px;
        line-height: 20px;
        // margin-top: 5px;
        color:#959ba3 ;
        span {
            vertical-align: middle;
            margin-left: 15px;
            font-size: 17px;
        }
    }
</style>