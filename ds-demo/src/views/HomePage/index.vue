<template>
    <div class="home">
        <router-view></router-view>
        
        <div class="menu_floor">
            <van-tabbar v-model="active">
                <van-tabbar-item 
                    @click.stop="goToPage(val)"
                    v-for="(val, index) of tab"
                    :key="val.id" 
                    :icon="val.icon"
                    :class="{'van-tabbar-item--active': index==active}" 
                    :info="val.page==='Cart' ? totalNum > 0 ? totalNum : '' : ''"
                    >
                    {{val.title}}
                </van-tabbar-item>
            </van-tabbar>   
        </div>
    </div>
</template>

<script>
import { vuexData } from '@/assets/js/mixin.js'

export default {
    mixins: [ vuexData ],
    data() {
        return {
            info: 1,
            tab: [
                { id: 1, title: '商城', icon: 'wap-home', page: 'Home' },
                { id: 2, title: '分类', icon: 'wap-nav', page: 'Category' },
                { id: 3, title: '购物车', icon: 'shopping-cart', page: 'Cart' },
                { id: 4, title: '我的', icon: 'contact', page: 'Personal' },
            ]
        }
    },
    components: {

    },
    computed: {
        
        // 购物车 总商品 数目
        totalNum() {
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
        },
        active:{
            get () {
                return this.GET_ACTIVE
            },
            set (val) {
                this.SET_ACTIVE({
                    active: val
                })
            }
        },
    },
    created() {
        this.tabbarUpdate(this.$route.name)
    },
    methods: {
        goToPage(selected) {
            if(selected.page === 'Category'){
                this.SET_CATEGORY_NOW({
                    categoryNow: ''
                })
            }
            this.$router.push({ name: selected.page })
        },
        tabbarUpdate(item) {
            const tabbarType = {
                AA: 0,
                Category: 1,
                Cart: 2,
                Personal :3
            }
            this.active = tabbarType[item]
        }
    },
    watch: {
        $router: {
            handler(val) {
                this.tabbarUpdate(val.name)
            }
        }
    }
}
</script>

<style lang="less" scoped>
.home {
    position: fixed;
    height: 100%;
    width: 100%;
    .menu_floor {
        position: relative;
        // height: auto;
        left: 0px;
        right: 0px;
        bottom: 0px;
        z-index: 200;
        background: #fff;
    }
}

</style>