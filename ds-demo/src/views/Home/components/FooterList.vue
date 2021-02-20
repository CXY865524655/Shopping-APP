<template>
    <div class="footerList" :style="{width: '100%', height: footerH}">
        <ul>
            <li 
                v-for="(i, index) in footer"
                :key='index'
                :class="{isFirst: index===0}"
                @click.stop='goToPro(i)'
            >
                <van-card
                    :price="i.mallprice % 1 === 0 ? i.mallprice + '.00' : i.mallprice.toString().split('.')[1].length===1 ? i.mallprice + '0' : i.mallprice"
                    :origin-price="i.mallprice==i.price ? null : i.price % 1 === 0 ? i.price + '.00' : i.price.toString().split('.')[1].length===1 ? i.price + '0' : i.price"
                    :desc="i.describes"
                    :title="i.goodsname"
                    >
                    <div slot="thumb" >
                        <van-image
                            v-if="i.imageShow"
                            width="90"
                            height="90"
                            :src="i.imageShow"
                        />
                    </div>
                    <div slot="footer" @click.stop='add(i)'>
                        <svg-icon iconClass="add"
                                    style="margin: auto;width: 7.5vw; height: 7.5vw; " 
                                />
                    </div>
                </van-card>
            </li>
        </ul>
    </div>
</template>

<script>

import { vuexData } from '@/assets/js/mixin.js'
import FooterHeader from './FooterHeader'
export default {
    mixins: [ vuexData ],

    props: {
        list: Array
    },
    computed:{
        footerH(){
            return this.footer.length * 132.14 + 'px'
        }
    },
    watch: {
       list(newVal) {
           this.footer = newVal
       }
    },
    data() {
        return {
            footer: [],
            
        }
    },
    components: {
        FooterHeader
    },
    created() {

    },
    methods: {
        // 判断是否登录
        isLogin() {
            if(!localStorage.getItem('token') || localStorage.getItem('token').trim()===''){
                return false
            }
            return true
        },
        add(item) {
            if(!this.isLogin()) {
                this.$router.push({ name: 'Login'})
            }else{
                let cart = {
                        goodsId: parseInt(item.goodsid),
                        num: 1,
                        productModel: JSON.parse(JSON.stringify(item))
                    }
                    this.add_to_cart({
                        userId: this.USER_ID,
                        cart
                    })
                    .then(() => {
                        this.$toast('加入购物车成功')
                    })
            }
        },
        goToPro(i) {
            this.$router.push({ name: 'Product', query:{
                goodInfo: JSON.stringify(i),
                imgs: JSON.stringify(i.imgss),
                detailsShow: JSON.stringify(i.detailsShow)
            }})
        }
    }
}
</script>

<style lang="less" scoped>

.footerList {
    width: 100%;
}

.isFirst{
    margin-top: 3vh;
}

</style>