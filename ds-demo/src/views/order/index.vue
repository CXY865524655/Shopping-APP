<template>
    <div class="orderC">
        <div style="margin-bottom: 8vh;">
            <Header></Header>   
            <AddressChoose :selectedAddress='selectedAddress'></AddressChoose>
            <List :list='list'></List>
        </div>
        

        <div>
            <van-submit-bar
                :price='totalPrice'
                button-text='提交订单'
                @submit='onSubmit'
                :label='label'
            >
                <!-- <span slot='tip'>
                    你的收货地址不支持同城送, <span>修改地址</span>
                </span> -->
            </van-submit-bar> 
        </div>

        <van-action-sheet v-model="show" title="请输入支付密码">
            <div style="width: 100%; height: 300px;">
                <van-password-input
                    :value="value"
                    info="密码为 6 位数字"
                    :focused="showKeyboard"
                />

                <van-number-keyboard
                    :show="showKeyboard"
                    @input="onInput"
                    @delete="onDelete"
                />

            </div>
        </van-action-sheet>
    </div>
</template>

<script>
import AddressChoose from './components/AddressChoose'  
import Header from './components/Header'  
import List from './components/List' 
import { vuexData } from '@/assets/js/mixin.js'
import md5 from 'js-md5'
import Vue from 'vue'
export default {
  name: 'Order',
  mixins: [ vuexData ],
  data() {
    return {
        value: '',
        showKeyboard: true,
        show: false,
        list: [],
        totalNum: '',
        totalPrice: 0,
        label: '',
        selectedAddress: {}
    }
  },
  computed: {

  },
  components: {
    AddressChoose,
    Header,
    List
  },
  
  created(){

  },
  methods: {
    onSubmit() {
        this.show = true
    },

    createOrderId(){
        // 生成订单编号
                    let currDate = new Date()
                    let year = currDate.getFullYear()
                    let month = currDate.getMonth() + 1 < 10 ? "0" + (currDate.getMonth() + 1): currDate.getMonth() + 1
                    let day = currDate.getDate()<10 ? "0"+currDate.getDate() : currDate.getDate()
                    
                    //获取年月日
                    let date = year + month + day 
                    
                    //获取当时时间戳
                    let timestamp = Date.parse(currDate) 
                    
                    //生成订单
                    let orderId = date + timestamp 
                    return orderId
    },
    onInput(key) {
        this.value = (this.value + key).slice(0, 6);
        if(this.value.length === 6) {
            this.$api.user.checkPayPwd(this.USER_ID, md5(this.value))
            .then(res => {
                if(res.data.status === 'success') {
                    this.$toast('支付成功')
                    this.show = false
                    this.value = ''

                    let orderID = this.createOrderId()

                    this.list.forEach( item  => {
                        Vue.set(item, 'imageShow', '')
                        Vue.set(item, 'imgss', '')
                        Vue.set(item, 'orderID', orderID)
                        Vue.set(item, 'selectedAddress', this.selectedAddress)
                    })

                    this.pay_success({
                        userId: this.USER_ID,
                        list: JSON.stringify(this.list)
                    })
                    

                    this.$router.push({ name: 'Home' })
                }else if(res.data.status === 'fail') {
                    this.$toast(res.data.data.errMsg)
                    this.value = ''
                }
            })
            
        }
    },
    onDelete() {
        this.value = this.value.slice(0, this.value.length - 1);
    },

    init(){  
        this.list = JSON.parse(JSON.stringify(this.GET_ORDER_LIST))
        // 总价
        let totalPrice = 0.00
        let totalNum = 0            // 总数量
        this.list.forEach(item => {

            totalPrice += item.productModel.mallprice * item.num
            totalNum += item.num
        })
        this.totalPrice = totalPrice * 100
        this.totalNum = totalNum

        this.label = '共'+this.totalNum+'件， 合计：'
        if(this.$route.query && this.$route.query.selectedAddress) {
            this.selectedAddress = JSON.parse(this.$route.query.selectedAddress)
        }else if(this.GET_ADDRESS_LIST.length > 0) {
            this.selectedAddress = this.GET_ADDRESS_LIST[0]
        }
    }
  },
  mounted() {
      this.$nextTick(() => {
          this.init()
      })
  },
  activated(){
    
  },
  watch: {
      
  },
}
</script>

<style lang='less' scoped>
.orderC {
    position: fixed;
    left: 0;
    top: 0;
    right: 0;
    bottom: 0;
    box-sizing: border-box;  // 防止 父元素设置 padding 后 子元素溢出
    padding: 12px;
    background-color: #DCDCDC;   // #f5f5f5


    // 默认加了 position: fixed; 无法滑动 ，增加下面2行代码可以解决
    -webkit-overflow-scrolling: auto;
    overflow-y: scroll;

    
}

.content {
    padding: 16px 16px 160px;
}
// 隐藏 scroll 滚动条
    .orderC::-webkit-scrollbar {
        display: none  
    }
</style>
