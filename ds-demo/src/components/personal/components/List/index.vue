<template>
    <div id="list">
        <ul>
            <li 
                v-for="(item, index) in list"
                :key='index'>
                <van-card
                    :num='item.num'
                    :price="(item.productModel.mallprice*item.num)%1===0?(item.productModel.mallprice*item.num)+'.00':(item.productModel.mallprice*item.num).toString().split('.')[1].length===1?item.productModel.mallprice*item.num+'0':item.productModel.mallprice*item.num"
                    :desc='item.productModel.describes'
                    :title='item.productModel.goodsname'
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
                    <div slot="price" style="height:40px;">
                        <div style="width:100%;height:50%; ">
                            <font style="font-size:15px;">合计: </font>
                            <font style="font-size:13px;">￥ {{(item.productModel.mallprice*item.num)%1===0?(item.productModel.mallprice*item.num)+'.00':(item.productModel.mallprice*item.num).toString().split('.')[1].length===1?item.productModel.mallprice*item.num+'0':item.productModel.mallprice*item.num}}</font>
                        </div>

                        <div style="width:100%;height:50%; ">
                            <font>单价: </font>
                            <font>￥ {{item.productModel.mallprice%1===0?item.productModel.mallprice+'.00':item.productModel.mallprice.toString().split('.')[1].length===1?item.productModel.mallprice+'0':item.productModel.mallprice}}</font>
                        
                        </div>
                    </div>
                    <div slot="footer" style="margin-top: 1.0vh;" v-show="text==='待评价'">
                        <van-button  
                            size="mini" 
                            color="orange" 
                            style="border-radius: 50px;width: 10.0vw;" 
                            plain
                            @click="text==='待评价' ? goToAssess(item) : none()"
                        >
                            评价
                        </van-button>
                    </div>
                </van-card>
            </li>
        </ul>
    </div>  
</template>

<script>  
import { vuexData } from '@/assets/js/mixin.js'
    
export default {
  name: 'List',
  mixins: [ vuexData ],
  props: {
      list: Array,
      text: String
  },
  
  data() {
    return {
    }
  },
  methods: {
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
    
  },
  
  created(){
    
  },
  mounted() {
  },
  computed: {
      
  },
  activated(){
    
  },
  watch: {
      list(newVal) {
          this.orderList = newVal
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
