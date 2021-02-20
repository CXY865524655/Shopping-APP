<template>
    <div class="assess">
        <Header @send='send'></Header>
        <Good></Good>
        <SaySometing :assessMsg.sync='assessMsg'></SaySometing>
        <!-- <Uploads></Uploads> -->
        <ProAssess style="margin-top:10px;" :starNum.sync='starNum'></ProAssess>

        <DoubleBounce v-show='show' style='width:100px;height:100px;left:0;right:0;bottom:0;top:0;position: absolute; margin: auto;'></DoubleBounce>

    </div>
</template>

<script>
import { DoubleBounce } from 'vue-loading-spinner'
import { vuexData } from '@/assets/js/mixin.js'
import Header from './components/Header'
import Good from './components/Good'
import SaySometing from './components/SaySometing'
// import Uploads from './components/Uploads'
import ProAssess from './components/ProAssess'
// import Vue from 'vue'

export default {
    name: 'Cart1',
    mixins: [ vuexData ],

    components:{
        DoubleBounce,
        Header,
        Good,
        SaySometing,
        // Uploads,
        ProAssess
    },

    data() {
        return {
            goodInfo: JSON.parse(this.$route.query.assess),
            assessMsg: '',
            show: false,
            starNum: 0
        }
    },

    computed: {
        
    },

    methods: {
        getTime() {
            let date = new Date()
            let dateStr = date.getFullYear() + '-' + date.getMonth() + '-' + date.getDate() + ' ' + date.getHours() +':' + date.getMinutes()+':' +date.getSeconds()
            
            return dateStr
        },
        send() {
            
            if(!this.assessMsg){
                this.$toast('请输入你的评论')
                return
            }

            if(this.starNum===0){
                this.$toast('请对商品进行评价')
                return
            }


            let assess = {
                userID: this.USER_ID,
                assess: this.assessMsg,
                starNum: this.starNum,
                sendTime: this.getTime()
            }

            assess = JSON.stringify(assess)
            this.sendAssess({
                goodsid: this.goodInfo.goodsid, 
                assess,
                num:this.starNum,
                buyNum: this.goodInfo.num
            })
            .then(async () => {
                await this.del_unassess({
                    userId: this.USER_ID,
                    unassess: this.$route.query.assess
                }).then(() => { 
                    this.$toast('评论成功~')
                    this.$router.push({ name: 'Home'})
                })
            })
        },
        
    },
    
    mounted() {
        this.init()
  },
  watch: {
    
  }
}
</script>

<style lang="less" scoped>
.assess {
    position: fixed;
    left: 0;
    top: 0;
    right: 0;
    bottom: 0;
    background-color:#DCDCDC;
    z-index: 14;
}

</style>