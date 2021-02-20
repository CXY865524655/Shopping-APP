<template>
  <div class="base_info">
      <van-cell-group>
        <van-cell class='product_name'>
            <div class="name">
                {{goodInfo.productModel? goodInfo.productModel.goodsname: goodInfo.goodsname}}
            </div>
            <div class="describe">
                {{goodInfo.productModel?goodInfo.productModel.describes:goodInfo.describes}}
            </div>
            <div class="price"><font class="a">￥</font>
            <font v-show="goodInfo.mallprice">
                {{
                goodInfo.mallprice?
                goodInfo.mallprice%1===0?
                goodInfo.mallprice+'.00':goodInfo.mallprice.toString().split('.')[1].length===1?
                goodInfo.mallprice+'0':goodInfo.mallprice:'0.00'
                
            }}
            </font>
            <font v-show="goodInfo.productModel">
                {{
                    goodInfo.productModel?
                    goodInfo.productModel.mallprice%1===0?
                    goodInfo.productModel.mallprice+'.00':goodInfo.productModel.mallprice.toString().split('.')[1].length===1?
                    goodInfo.productModel.mallprice+'0':goodInfo.productModel.mallprice:'0.00'
                }}
            </font>
            </div>
                        
            <div class="old_price">
                价格 
                <font class="text" v-show="goodInfo.price">
                    ￥{{goodInfo.price?goodInfo.price%1===0?goodInfo.price+'.00':goodInfo.price.toString().split('.')[1].length===1?goodInfo.price+'0':goodInfo.price:'0.00'}}
                </font>
                <font class="text" v-show="goodInfo.productModel">
                    ￥{{goodInfo.productModel?goodInfo.productModel.price%1===0?goodInfo.productModel.price+'.00':goodInfo.productModel.price.toString().split('.')[1].length===1?goodInfo.productModel.price+'0':goodInfo.productModel.price:'0.00'}}
                </font>

                <div style="float:right;margin-right: 0.6rem;">销量 ：{{goodInfo.productModel?goodInfo.productModel.sales:goodInfo.sales}}</div>
            </div>
        </van-cell>
        <!-- <van-cell class="goods-express">
            <van-col span="12" style="padding-left: 4px;">运费：0.00</van-col>
            <van-col span="12" style="text-align:right;padding-right: 15px;">剩余：0</van-col> -->
            <!-- <van-col span="6" class="like">
                {{true?'取消收藏':'收藏'}}：
                <van-icon
                    :name="!isCollectionFlag?'like':'like-o'"
                    
                    class="like-o"
                    
                />
            </van-col> -->
        <!-- </van-cell> -->
    </van-cell-group>
  </div>

</template>

<script>

export default {
  props:{
      goodInfo: Object
  },
  methods: {

  }
}   
</script>

<style lang="less" scoped>
.base_info {
    margin-bottom: 8vh;
    .product_name {
        .name {
            font-size: 18px;
            color: #2c3e50;
        }
        .price {
                line-height: 10vw;
                color: #FFAA00;
                font-size: 28px;
                font-weight: 600;
                .a {
                   font-size: 15px;
                   margin-right: 2px;
                }
        }

        .old_price {
            color: grey;
            font-size: 0.8rem;
            .text {
                text-decoration: line-through;

            }
        }

        .describe {
            margin-top: -7px;
            padding: 0.5rem 0;
            color: grey;
            font-size: 0.9rem;
        }
    }
}
    
</style>
