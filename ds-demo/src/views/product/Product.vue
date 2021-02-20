<template lang="html">
    <div class="product">
        <van-nav-bar
            title="商品详情"
            :fixed="true"
            :border="false"
            style="height:3.2rem" 
        />
        <swipe :image="imghaha" style="margin-top:3.2rem"></swipe> 
        <baseinfo :goodInfo="goodInfo"></baseinfo>
        <Details :images.sync="detailsShow" :goodid.sync='goodInfo.goodsid'></Details>
        <footers 
            :star.sync="isStar"
            @cancleStar="cancleStar"
            @addStar="addStar"
            @addToCart="addToCart"
        >
            
        </footers>
        <!-- sku 代码 -->
        <!-- <Submit :isShow.sync="showBuy"></Submit> -->
        <Back @back='back' />
    </div>
</template>

<script>
import swipe from './components/Swipes.vue'
import footers from './components/Footer.vue'
// import Submit from './components/Details/Submit.vue'
import Details from './components/Details'
import baseinfo from './components/BaseInfo.vue'
import Back from '@/components/public/Back.vue'
import { vuexData } from '@/assets/js/mixin.js'
export default {
    name: 'Product',
    mixins: [ vuexData ],
    data(){
        return {
            showBuy: false,
            isStar: false,
            imghaha:[],
            detailsShow: []
        }
    },
    components: {
        swipe,
        footers,
        Details,
        baseinfo,
        Back,
        // Submit
    },
    mounted() {
        this.$nextTick(() => {
            this.init()
        })
    },

    methods: {
         init() {
            let goodsss = JSON.parse(this.$route.query.goodInfo)
            this.imghaha = JSON.parse(this.$route.query.imgs)
            this.detailsShow = JSON.parse(this.$route.query.detailsShow)
            let goodsid = goodsss.goodsid
            if(this.USER_ID) {
                 this.$api.user.checkStar(this.USER_ID, goodsid)         
                .then(res => {
                    this.isStar = res.data.data
                })
                .catch(() => {
                    this.isStar = false
                })
            }
        },

        // 加入购物车
        addToCart() {
                let cart = {
                    goodsId: parseInt(this.goodInfo.goodsid),
                    num: 1,
                    productModel: JSON.parse(this.$route.query.goodInfo)
                }
                this.add_to_cart({
                    userId: this.USER_ID,
                    cart
                })
                .then(() => {
                    this.$toast('加入购物车成功')
                })
        },
        cancleStar() {
                let index = this.GET_COLLECT_LIST.findIndex(item => item==this.goodInfo.goodsid)
                if(index >= 0) {
                    this.delete_collect({
                        userId: this.USER_ID,
                        collect: this.goodInfo.goodsid
                    })
                }
        },

        addStar() {
                let index = this.GET_COLLECT_LIST.findIndex(item => item==this.goodInfo.goodsid)
                if(index < 0) {
                    this.add_collect({
                        userId: this.USER_ID,
                        collect: this.goodInfo.goodsid
                    })
                }
        },
        // // 立即购买
        // onClickBuyNow() {
        //     this.$router.push({ name: ''})
        // },
    },
    watch:{
        
    },
    computed: {
        goodInfo(){
            if(this.$route.query && this.$route.query.goodInfo) {
                return JSON.parse(this.$route.query.goodInfo)
            }
            return ''
        },
        
    }
}
</script>

<style lang="less" scoped>

.product::-webkit-scrollbar {
      display: none  
}
.product {


    
    position: fixed;

    // 默认加了 position: fixed; 无法滑动 ，增加下面2行代码可以解决
    -webkit-overflow-scrolling: auto;
    overflow-y: scroll;


    .footers {
        bottom: 0;
    }
}
    
</style>