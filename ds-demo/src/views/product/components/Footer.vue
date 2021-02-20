<template>
    <div class="footers">
        <van-goods-action style="z-index: 999;">
            <van-goods-action-icon icon="cart-o" text="购物车" @click.stop='clickCart' :info="totalNum"/>
            <van-goods-action-icon icon="star" :text="starStatusText" :color="starStatusColor" @click.stop='clickStar' ref="star"/>
            <van-goods-action-button type="warning" @click.stop="addToCart" text="加入购物车" />
        </van-goods-action>
    </div>
</template>

<script>
import { vuexData } from '@/assets/js/mixin.js'

export default {
    mixins: [ vuexData ],
    name: 'Footer',
    props: {
        star: Boolean
    },


    data() {
        return {
            isStar: false
        }
    },
    created() {

    },
    computed:{
        starStatusText(){
            return this.isStar?'已收藏':'收藏'
        },
        starStatusColor(){
            return this.isStar?'#ff5000':'#808080'
        },
        totalNum: {
            get(){
                if(!localStorage.getItem('token') || localStorage.getItem('token').trim()===''){
                    return 0
                }
                let total = 0
                if(this.GET_CART && this.GET_CART.length > 0) {
                    this.GET_CART.forEach(item => {
                        total = total + item.num
                    })
                }
                return total
            }
        }
    },
    methods: {
        clickCart() {
            if(!this.isLogin()) {
                this.$router.push({ name: 'Login'})
            }else{
                this.$router.push({ name: 'Cart' })
            }
        },

        // 判断是否登录
        isLogin() {
            if(!localStorage.getItem('token') || localStorage.getItem('token').trim()===''){
                return false
            }
            return true
        },

        clickStar() {
            if(!this.isLogin()) {
                this.$router.push({ name: 'Login'})
            }else{
                if(this.$refs.star.color==='#ff5000') {
                    this.$refs.star.color='#808080'
                    this.$emit('cancleStar')
                    this.isStar = false
                    this.$refs.star.text='收藏'
                    this.$toast('取消收藏成功')
                }else {
                    this.$refs.star.color='#ff5000'
                    this.$emit('addStar')
                    this.isStar = true
                    this.$refs.star.text='已收藏'
                    this.$toast('收藏成功')
                }
            }
        },

        // 立即购买
        onClickBuyNow() {
            if(!this.isLogin()) {
                this.$router.push({ name: 'Login'})
            }else{
                this.$emit('onClickBuyNow')
            }
        },

        // 加入购物车
        addToCart() {
            if(!this.isLogin()) {
                this.$router.push({ name: 'Login'})
            }else{
                this.$emit('addToCart')
            }
        }
    },
    watch: {
        star(newVal) {
            this.isStar = newVal
        },
        isStar(newVal) {
            this.$emit('update:star', newVal)
        }
    }
}
</script>

<style  scoped>
.footer{
    position: fixed;
    bottom: 0;
}
</style>
