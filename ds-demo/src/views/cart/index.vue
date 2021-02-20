<template>
    <div class="cart">
        <Header @clickRemove='clickRemove'></Header>
        <!-- 购物车没有数据 -->
        <div class='emptyCart'
            v-show='cart_is_null&&!show'>
            <img src='@/assets/images/cart/cart-null.png'
                alt=''>
            <div class='title'>购物车竟然是空的</div>
            <div @click.stop='goToSee'
                class='goHome'>去看看
            </div>
        </div>
        <div class='emptyCart' v-show='show'></div>
        <!-- 有数据  -->
        <div class='emptyCart'
            v-show='!cart_is_null&&!show'>
            <List 
                :v-show='!cart_is_null'
                :checkall.sync='isCheckAll'
                :selecteds.sync='selectedItem'
                @initAllCount='initAllCount'
                @clickBlur='clickBlur'
                @clickPlus='clickPlus'
                @clickMinus='clickMinus'
                ></List>
        </div>
        <!-- <Content v-show='!cart_is_null' class='content'></Content> -->
        
        <van-submit-bar
                :price='total'
                button-text='提交订单'
                @submit='onSubmit'
            >
            <van-checkbox 
                v-model='isCheckAll'
                @click.stop='checkAll'
                >全选</van-checkbox>
            <!-- <span slot='tip'>
                你的收货地址不支持同城送, <span>修改地址</span>
            </span> -->
        </van-submit-bar>



        <DoubleBounce v-show='show' style='width:100px;height:100px;left:0;right:0;bottom:0;top:0;position: absolute; margin: auto;'></DoubleBounce>

    </div>
</template>

<script>
// import Content from './components/Content'
import List from './components/List'
import { DoubleBounce } from 'vue-loading-spinner'
import { vuexData } from '@/assets/js/mixin.js'
import Header from './components/Header'
import Vue from 'vue'
export default {
    name: 'Cart1',
    mixins: [ vuexData ],

    components:{
        // Content,
        List,
        DoubleBounce,
        Header,
    },

    data() {
        return {
            total: 0.00,
            show: false,
            isCheckAll: false,
            cart_is_null: true,
            selectedItem: [],
        }
    },

    computed: {
        
    },

    methods: {
        goToSee() {
            if(!this.USER_ID) {
                this.$router.push({ name: 'Login' })
                return 
            }
            this.$router.push({ name: 'Home' })
            return
        },
      async onSubmit() {
          if(this.selectedItem.length > 0) {
            let selecteds = JSON.parse(JSON.stringify(this.selectedItem))
            let cart = JSON.parse(JSON.stringify(this.GET_CART))
            cart = cart.filter( item  => {
                let a = selecteds.findIndex(i => i === item.goodsid)
                if(a >= 0){
                    return item
                }
            })
            this.SET_ORDER_LIST({
                order: cart
            })
            this.$router.push({
                name: 'Order'
            })
          } else {
              this.$toast('没有选中任何商品')
          }
      },

      // 商品 num 变化
      async clickBlur(item) {
          let num = parseInt(item.num)
          let id = item.goodsid
          let cart = {
            goodsId: id,
            num
          }
          this.set_cart_item({
              userId: this.USER_ID,
              cart
          })
        this.initAllCount()
      },

     // 商品 num + 1
     async clickPlus(item) {
        await this.$api.user.cart_num_up1(this.USER_ID, item.productModel.goodsid)

        // 重新计算 商品数量
        this.initAllCount()
      },

      // 商品 num - 1
    async clickMinus(item) {
        let id = item.productModel.goodsid
        let cart = {
              goodsId: id
        }
        await this.removeOne_from_cart({
              userid: this.USER_ID,
              cart
          })
        this.initAllCount()
      },

        clickRemove() {
            if(this.selectedItem.length > 0) {
                this.$dialog.alert({
                    title: '温馨提示',
                    message: '确认删除选中商品了吗',
                    showCancelButton: true
                })
                .then(() => {
                        let selecteds = this.selectedItem
                        
                        this.delete_cart_choose_item({
                            userId: this.USER_ID,
                            selecteds
                        })
                        .then(() => {
                            this.total = 0.00
                            this.isCheckAll = false
                            this.selectedItem = []
                            if(this.GET_CART.length <= 0) {
                                this.cart_is_null = true
                            }
                        })
                    })
                .catch(() => {

                })
            }else {
                this.$toast('没有选中任何商品')
            }
        },

        // 判断是否登录
        isLogin() {
            if(!localStorage.getItem('token') || localStorage.getItem('token').trim()===''){
                return false
            }
            return true
        },

        async init() {
            if(this.isLogin()){
                this.selectedItem = []
                this.show = true
                await this.get_cart_list({
                        userid: this.USER_ID
                    })
                    .then(res => {
                        let cartList = JSON.parse(JSON.stringify(res.data.data))
                        // 判断购物车为空
                        if(cartList.length === 0 ) {
                            this.cart_is_null = true
                        } else {
                            this.cart_is_null = false
                            let ls = cartList
                            if(ls&&ls.length>=1) {
                                this.compliePro(ls)
                                ls.forEach(item => {
                                    Vue.set(item, 'goodsid', item.productModel.goodsid)
                                })
                                this.SET_CART({
                                    cart: ls
                                })
                            }
                        }
                    })
                    this.show = false
            } else {
                this.cart_is_null = true
            }
        },

        async compliePro(ls){
            ls.forEach(async item =>{
                Vue.set(item, 'isChecked', false)
                Vue.set(item, 'imageShow', '')
                Vue.set(item, 'imgss', [])
                Vue.set(item, 'detailsShow',[])
                await this.jiexiImgs(item)
                await this.jiexiDetail(item)
            })
        },
        async jiexiImgs(item){
          let imgPath = item.productModel.image
                        let i = imgPath.indexOf(';')
                        if(i >= 0) {
                            // 多个
                            let paths = imgPath.split(';')
                            paths.forEach(async (v,i) => {
                                await this.$api.goods.getGoodImg(v)
                                .then(res => {
                                    return 'data:image/png;base64,' + btoa(
                                        new Uint8Array(res.data)
                                        .reduce((data, byte) => data + String.fromCharCode(byte), '')
                                    )
                                })
                                .then(data => {
                                  if(i===0) {
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
                        let detailss = item.productModel.details
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

        initAllCount() {
            var totalPrice = 0.00
            this.GET_CART.forEach(val => {
                if(val.isChecked) {
                    totalPrice += val.productModel.mallprice * val.num
                }
            })
            this.total = totalPrice * 100 // 精确到分所以需要 * 100
        },

        checkAll() {
            if(this.GET_CART && this.GET_CART.length >=1 ) {
                let items = JSON.parse(JSON.stringify(this.GET_CART))
                if(!this.isCheckAll) {
                    items.forEach(item => {
                        item.isChecked = false
                    })

                    this.SET_CART({
                        cart: items
                    })

                    this.selectedItem = []
                    // 计算总价格
                    this.total = 0.00
                }else {
                    items.forEach(item=>{
                        item.isChecked = true
                        if (this.selectedItem.indexOf(item.productModel.goodsid) < 0) {
                            this.selectedItem.push(item.productModel.goodsid)
                        }
                    })
                    this.SET_CART({
                        cart: items
                    })
                    this.initAllCount()
                }
            }
        }
    },
    mounted() {
      this.$nextTick(() => {
          this.init()
      })
  },
  watch: {
    
  }
}
</script>

<style lang="less" scoped>
.cart {
    position: fixed;
    left: 0;
    top: 0;
    right: 0;
    bottom: 0;
    background-color: #ffffff;
    z-index: 14;
    
    .emptyCart {
      height: 84.5%; 
      display: flex;
      flex-direction: column;
      justify-content: center;
      background-color: #f5f5f5;
      img {
        padding-top: 2rem;
        width: 30vw;
        height: 30vw;
        margin: 0 auto;
      }
      .title {
        margin-top: 2vh;
        margin-bottom: 2vh;
        font-size: 18px;
        text-align: center;
        padding: 0.5rem;
      }
      .goHome {
        margin-top: 2rem;
        color: white;
        background-color: #45c763;
        text-align: center;
        margin: 0 auto;
        padding: 0.5rem;
        width: 5.1rem;
        height: 1.1rem;
        line-height: 1.1rem;
        border-radius: 1.1rem;
      }
    }

    .van-submit-bar {
        position: relative;
        background-color: transparent;
        
    }
    .van-submit-bar__bar {
        // background-color: transparent;
        padding: 0px 10px 0px 15px;
        z-index: 14;
        background-color: white;
        margin-bottom: -10px;
    }
}

</style>
