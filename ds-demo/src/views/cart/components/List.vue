<template>
  <div id="list">
      <div class="wrap" ref="wrap">
        <ul class="items">
                <!--  betweenProduct: index !== 0 除了第一个和顶部无距离，每个商品之间留有一些空格 -->
                <li ref='lis'
                    v-for='(item, index) in GET_CART'
                    :key='index'
                    :class='{ betweenProduct: index !== 0 }'
                    class='item'
                >
                    <div class="select">
                        <van-checkbox
                            :value='index'
                            v-model='item.isChecked'
                            checked-color='#15C481'
                            class='box'
                            @click.stop='select(index, item)'
                        />
                    </div>
                    <div
                        class='item_card'
                    >
                            <van-card
                                :price="item.productModel.mallprice%1===0?item.productModel.mallprice+'.00':item.productModel.mallprice.toString().split('.')[1].length===1?item.productModel.mallprice+'0':item.productModel.mallprice"
                                :desc='item.productModel.describes'
                                :title='item.productModel.goodsname'
                                @click-thumb.stop='toDetail(item)'


                            >
                            <div slot="thumb" >
                                <van-image
                                    v-if="item.imageShow"
                                    width="90"
                                    height="90"
                                    :src="item.imageShow"
                                />
                            </div>
                            <div slot="footer">
                                <van-stepper 
                                    v-model='item.num'
                                    @blur='clickBlur(item)'
                                    @plus='clickPlus(item)'
                                    @minus='clickMinus(item)'
                                />
                            </div>
                            </van-card>
                    </div>
                    
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
// import { DoubleBounce } from 'vue-loading-spinner'
import { vuexData } from '@/assets/js/mixin.js'
import BScroll from 'better-scroll'

export default {
  name: 'List',
  mixins: [ vuexData ],
  props: {
      checkall: Boolean,
      selecteds: Array
  },
  watch: {
    
    checkall(newVal) {
        this.isCheckAll = newVal
    },
    selecteds(newVal) {
        this.selectedItem = newVal
    },
    selectedItem(newVal) {
        this.$emit('update:selecteds', newVal)
    }

  },
  data() {
    return {
        tabHeight: 126,
        showBottomText: '',
        isCheckAll: '',
        selectedItem: [],
        step: 1,
    }
  },
  components: {

  },
  methods: {

      // 商品 num 变化
      clickBlur(item) {
          this.$emit('clickBlur',item)
      },

      // 商品 num + 1
      clickPlus(item) {
          this.$emit('clickPlus',item)
      },
      // 商品 num - 1
      clickMinus(item) {
        this.$emit('clickMinus',item)
      },
      
      // 选中购物车商品时
      select(index, item) {
          
          if(this.selectedItem.indexOf(item.productModel.goodsid) >= 0 ) {
              let items = this.selectedItem.filter(i => i != item.productModel.goodsid)
              this.selectedItem = items
              item.isChecked = false
              this.$emit('initAllCount')
          } else {
              this.selectedItem.push(item.productModel.goodsid)
              item.isChecked = true
              this.$emit('initAllCount')
          }

          // 当全部商品被选中时勾选全选按钮，否则取消
          if(this.selectedItem.length < this.GET_CART.length) {
              this.$emit('update:checkall', false)
          } else {
              this.$emit('update:checkall', true)
          }
          this.$emit('initAllCount')
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
        initProductScroll() {
            // 改动后
            if(this.GET_CART) {
                if(this.GET_CART.length * this.tabHeight >= 630) {
                    this.showBottomText = true
                }else{
                    this.showBottomText = false
                }
            }else {
                this.showBottomText = false
            }

            // 改动前
            // if(this.GET_CART.length * this.tabHeight >= 630) {
            //     this.showBottomText = true
            // }else{
            //     this.showBottomText = false
            // }
            if(!this.productScroll) {
                this.productScroll = new BScroll('.wrap', {
                    probeType: 3,     // 除了实时派发scroll事件，在swipe的情况下仍然能实时派发scroll事件
                    click: true,      // 是否派发click事件，通常判断浏览器派发的click还是betterscroll派发的click，可以用_constructed，若是bs派发的则为true
                    scrollY: true,    // true 滚动方向为 Y 轴
                    tap: true,        // better-scroll 会阻止原生的 click 事件，我们可以设置 tap 为 true
                    mouseWheel: true, // 启动鼠标滚轴
                    bounce:true,      // 上拉下拉的回滚效果，默认为 true

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
  
  mounted() {
      this.$nextTick(() => {
          this.initProductScroll()
      })
  },
  activated(){
    
  },
  
}
</script>

<style lang="less" scoped>
    #list {
        margin-top: 3.3rem;
        margin-bottom: 5.8rem;
        position: fixed;

        // 设置下面四行 才能滑动（否则为自适应，才不会滑动哦~）
        left: 0;
        top: 0;
        right: 0;
        bottom: 0;

        display: flex;
        flex-direction: column;
        justify-content: center;
        background-color: #f5f5f5;
        // z-index: 99999999999;
        .betweenProduct{
            margin-top: 3px;
        }

        .wrap {
            width: 100%;
            height: 100%;
            .items{

                .item {
                    width: 100%;
                    display: flex;
                    .select {
                        display: flex;  
                        width: 10%;
                        .box {
                            margin:0 0 0 auto;  // 水平垂直居中  
                        }
                    }
                    .item_card {
                        width: 90%;
                    }
                }
            }
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
