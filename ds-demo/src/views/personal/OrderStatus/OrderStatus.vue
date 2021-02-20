<template>
  <div id="myOrder">
    <van-nav-bar title="我的订单"
                 :border=false
                 :fixed="true"
                 @click-left="onClickLeft"
                 left-arrow
                 style="top:0;" />
    <van-tabs v-model="active"
              class="tt"
              animated
              swipeable
              sticky
              :border="false"
              :offset-top="38.39"
              ref="tabs"
              type="line"
              color="#28BE57"
              title-active-color="#28BE57"
              animated:yes
              @click="init"
              >
      <!-- 全部 -->
      <van-tab 
        v-for="(status,index) in orderStatusText"
        :key="index"
        v-show="!show"

      >
        <div slot="title">
          <span>{{status.title}}</span>
        </div>
        <Types :text='status.title' :detail.sync='status.detail'/>
        <!-- <div style="width: 100%; height: 100%; background-color: red;">123<br><br><br><br><br><br><br><br></div> -->
      </van-tab>
    </van-tabs>

    <DoubleBounce v-show='show' style='width:100px;height:100px;left:0;right:0;bottom:0;top:0;position: absolute; margin: auto;'></DoubleBounce>

  </div>
</template>
<script type="text/javascript">
import Types from '@/components/personal/Types.vue'
import { DoubleBounce } from 'vue-loading-spinner'
import Vue from 'vue'
import { vuexData } from '@/assets/js/mixin.js'

export default {

  mixins: [ vuexData ],
  data () {
    return {
      // 路由传递过来的数据 active
      active: this.$route.params.active,
      orderStatusText:'',
      show: false
    }
  },
  components: {
    Types,
    DoubleBounce

  },
  computed: {
    
  },
  methods: {
    onClickLeft () {
      this.$router.back();
    },

    async init() {  
      this.show = true
        await this.$api.user.initOrderDetail(this.USER_ID)
        .then(res => {
          let l = JSON.parse(JSON.stringify(res.data.data))
          let bought = l.bought? JSON.parse(l.bought):[]
          if(bought&&bought.length>=1) {
                    bought.forEach((item)=>{
                        Vue.set(item, 'imageShow', '')
                        Vue.set(item, 'imgss', [])
                        let imgPath = ''

                        if(item.image) {
                            imgPath = item.image
                        }else {
                            imgPath = item.productModel.image
                        }
                        let i = imgPath.indexOf(';')
                        if(i >= 0) {
                            // 多个
                            let paths = imgPath.split(';')
                            let p = paths[0]
                            this.$api.goods.getGoodImg(p)
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
                    })
                }
                

          let unpaid = l.unpaid?JSON.parse(l.unpaid):[]
          if(unpaid&&unpaid.length>=1) {
                    unpaid.forEach((item)=>{
                        Vue.set(item, 'imageShow', '')
                        Vue.set(item, 'imgss', [])
                        let imgPath = ''

                        if(item.image) {
                            imgPath = item.image
                        }else {
                            imgPath = item.productModel.image
                        }
                        let i = imgPath.indexOf(';')

                        if(i >= 0) {
                            // 多个
                            let paths = imgPath.split(';')
                            let p = paths[0]
                            this.$api.goods.getGoodImg(p)
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
                    })
                }

          let unreceived = l.unreceived?JSON.parse(l.unreceived):[]

          if(unreceived&&unreceived.length>=1) {
                    unreceived.forEach((item)=>{
                        Vue.set(item, 'imageShow', '')
                        Vue.set(item, 'imgss', [])
                        let imgPath = ''
                        if(item.image) {
                            imgPath = item.image
                        }else {
                            imgPath = item.productModel.image
                        }
                        let i = imgPath.indexOf(';')

                        if(i >= 0) {
                            // 多个
                            let paths = imgPath.split(';')
                            let p = paths[0]
                            this.$api.goods.getGoodImg(p)
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
                    })
                }
          let unassess = l.unassess?JSON.parse(l.unassess):[]

           if(unassess&&unassess.length>=1) {
                    unassess.forEach((item)=>{
                        Vue.set(item, 'imageShow', '')
                        Vue.set(item, 'imgss', [])
                        let imgPath = ''
                        if(item.image) {
                            imgPath = item.image
                        }else {
                            imgPath = item.productModel.image
                        }
                        let i = imgPath.indexOf(';')

                        if(i >= 0) {
                            // 多个
                            let paths = imgPath.split(';')
                            let p = paths[0]
                            this.$api.goods.getGoodImg(p)
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
                    })
                }
          let a = []
          a.push({title: '全部', detail:bought})
          a.push({title: '待付款', detail:unpaid})
          a.push({title: '待收货', detail:unreceived})
          a.push({title: '待评价', detail:unassess})
          this.orderStatusText = a
        })
        this.show = false
    }
    
  },
  mounted() {
    this.$nextTick(()=>{
      this.init()
    })
  }
}
</script>

<style lang="less" scoped>
.tt::-webkit-scrollbar {
      display: none  
}
#myOrder {

  .tt{
    position: fixed;
    left: 0;
    right: 0;
    top: 0;
    bottom: 0;
    // 默认加了 position: fixed; 无法滑动 ，增加下面2行代码可以解决
    -webkit-overflow-scrolling: auto;
    overflow-y: scroll;
  }
  position: fixed;
  left: 0;
  right: 0;
  top: 0;
  bottom: 0;
  z-index: 100;
  background-color: white;
  z-index: 99999999;
  
  .van-icon {
    color: #dedede;
  }

}
</style>
