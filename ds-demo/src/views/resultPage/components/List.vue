<template>
    <div id="list">
        <ul v-show="show">
            <li 
                v-for="(item, index) in goods"
                :key='index'
                @click.stop="toDetails(item)"
                >
                <van-card
                    :num='item.num'
                    :price="item.mallprice%1===0?item.mallprice+'.00':item.mallprice.toString().split('.')[1].length===1?item.mallprice+'0':item.mallprice"
                    :origin-price="item.price%1===0?item.price+'.00':item.price.toString().split('.')[1].length===1?item.price+'0':item.price"
                    :desc='item.describes'
                    :title='item.goodsname'
                    :class="{fcard: index === 0, lcard: index === list.length - 1, mcard: index !== 0  }"
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
        </ul>
        <DoubleBounce v-show="!show" style="width:100px;height:100px;left:0;right:0;bottom:0;top:0;position: absolute; margin: auto;"></DoubleBounce>
    </div>  
</template>

<script>  
import { vuexData } from '@/assets/js/mixin.js'
import { DoubleBounce } from 'vue-loading-spinner'
    
export default {
  name: 'List',
  mixins: [ vuexData ],
  props: {
      list: Array,
      text: String
  },
  
  data() {
    return {
        goods:[],
        show: true
    }
  },
  methods: {
      toDetails(item){
          
            this.$router.push({ 
                name: 'Product',
                query: {
                    goodInfo: JSON.stringify(item),
                    imgs: JSON.stringify(item.imgss),
                    detailsShow: JSON.stringify(item.detailsShow)
                }
            })

        },
      none() {

      },
        goToAssess(item) {
            this.$router.push({ 
                name: 'Assess',
                query: {
                    assess: JSON.stringify(item)
                }
            })

        }
  },
  components: {
    DoubleBounce
  },
  
  created(){
    
  },
  mounted() {

      this.$nextTick(()=>{
      })
  },
  computed: {
      
  },
  activated(){
    
  },
  watch: {
      list(newVal) {
          this.goods = newVal
      },

      goods(newVal) {
          this.$emit('list', newVal)
      }
  },
}
</script>

<style lang="less" scoped>

#list {
    border-radius: 1.0rem;
    background: white;
    margin-top: 3.0vh;
    box-sizing: border-box;  // 防止 父元素设置 padding 后 子元素溢出

    .fcard {
        border-radius: 1.0rem;
    }

    .lcard {
        border-bottom-right-radius: 1.0rem;
        border-bottom-left-radius: 1.0rem;
    }
    
    .mcard {
        margin-top: 1.8vh;
    }
    
}
</style>
