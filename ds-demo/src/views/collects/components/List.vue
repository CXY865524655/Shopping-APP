<template>
  <div id="list" style="display: flex;">
      <div  v-show="collects.length <= 0" style="margin:30vh auto; display:flex;">
          <div class="collectNull">
              <svg-icon 
                      slot="icon"
                      iconClass="collectNull"
                      style="width:38vw;height:38vw;margin:-5vh auto; "
                  />
                 <div style="margin:6vh auto;text-align:center;font-size:17px;">还没有收藏商品哦~</div>
          </div>
              
         
      </div>
      <div class="wrap" ref="wrap" v-show="collects.length > 0">
        <ul>
                <!--  betweenProduct: index!==0 除了第一个和顶部无距离，每个商品之间留有一些空格 -->
                <li ref="lis"
                    v-for="(item, index) in collects"
                    :key="index"
                    :class="{betweenProduct: index!==0}"
                    @click.stop="toDetail(item)"
                >
                    <van-card
                        :price="item.mallprice%1===0?item.mallprice+'.00':item.mallprice.toString().split('.')[1].length===1?item.mallprice+'0':item.mallprice"
                        :origin-price="item.price%1===0?item.price+'.00':item.price.toString().split('.')[1].length===1?item.price+'0':item.price"
                        :desc="item.describes"
                        :title="item.goodsname"
                    >
                    <div slot="thumb" >
                            <van-image
                                v-if="item.imageShow"
                                width="90"
                                height="90"
                                :src="item.imageShow"
                            />
                        </div>

                    </van-card>
                </li>
                <!-- <li>
                    <div class="bottom-msg" v-show="showBottomText">
                        <span v-show="isEnding">- 到底了 -</span>
                        <span v-show="isNotAdding">- 加载更多 -</span>
                        <loading v-show="isPullLoading" :loadingWord="PullingUpWord" :text="''"></loading>
                    </div>
                </li> -->
            </ul>
        </div>
  </div>
</template>

<script>  
// import Back from '@/components/public/Back.vue'
// import {DoubleBounce} from 'vue-loading-spinner'
import { vuexData } from '@/assets/js/mixin.js'
import BScroll from 'better-scroll'

export default {
  name: 'List',
  mixins: [ vuexData ],
  props: {
      list: Array
  },
  data() {
    return {
        collects: [] ,
        tabHeight: 126,
        showBottomText: ''
    }
  },
  components: {

  },
  methods: {
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

        initProductScroll() {
            if(this.list.length * this.tabHeight >= 630) {
                this.showBottomText = true
            }else{
                this.showBottomText = false
            }


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
                
                this.$toast('下拉加载中...')
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
                            this.$toast('加载成功')
                
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
  created(){
   
  

  },
  mounted() {
      this.$nextTick(() => {
          this.initProductScroll()
      })
  },
  activated(){
    
  },
  watch: {
    list(newVal) {
        this.collects = newVal
    }  
  },
}
</script>

<style lang="less" scoped>
    #list {
        margin-top: 3.8rem;
        // margin-bottom: 3.8rem;
        position: fixed;

        // 设置下面四行 才能滑动（否则为自适应，不会滑动）
        left: 0;
        top: 0;
        right: 0;
        bottom: 0;

        background-color: #ffffff;
            
        .collectNull {
            width: 40vw;
            height: 60vw;
        }
        .betweenProduct{
            margin-top: 3px;
        }

        .wrap {
            width: 100%;
            height: 100%;
        }
        .newxin {
            width: 100%;
            height: 50px;
            text-align: center;
            line-height: 50px;
            background: red;
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
    }
</style>
