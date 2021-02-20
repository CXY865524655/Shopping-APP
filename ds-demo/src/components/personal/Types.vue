<template>
  <div id="orderType">
    <div v-if="detail.length>0" class='hasData' >
        <List :list='detail' :text='text'></List>
    </div>
    <div v-else
         class="noDataShow" >
         <div style="display: flex;">
           <svg-icon 
          slot="icon"
          iconClass="orderNull"
          style="width:30vw;height:30vw;margin:auto;"
      />
      </div>
      
      <span class="desc">暂时没有订单~</span>
    </div>
  </div>
</template>

<script type="text/javascript">
import List from './components/List'
import { vuexData } from '@/assets/js/mixin.js'

export default {
  mixins: [ vuexData ],

  props: {
    text: String,
    detail: Array
  },
  watch: {
    detail(newVal) {
      this.$emit('update:detail', newVal)
    }
  },
  mounted (){

  },
  data () {
    return {
        list: '',
    }
  },
  components: {
    List
  },
  methods: {
      async init(name) {
        await this.$api.user.initOrderDetail(this.USER_ID)
        .then(res => {
          let l = res.data.data
          switch(this.text) {
            case '全部':
              let bought = l.bought? JSON.parse(l.bought):[]
              
              if(bought&&bought.length>=1) {
                    bought.forEach((item,index)=>{
                        Vue.set(item, 'imageShow', '')
                        Vue.set(item, 'imgss', [])
                        let imgPath = item.image
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
                this.list = bought

              break;

            case '待付款':
            let unpaid = l.unpaid? JSON.parse(l.unpaid):[]

              if(unpaid&&unpaid.length>=1) {
                    unpaid.forEach((item,index)=>{
                        Vue.set(item, 'imageShow', '')
                        Vue.set(item, 'imgss', [])
                        let imgPath = item.image
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
                this.list = unpaid
              break;
            case '待收货':
            let unreceived = l.unreceived? JSON.parse(l.unreceived):[]

            if(unreceived&&unreceived.length>=1) {
                    unreceived.forEach((item,index)=>{
                        Vue.set(item, 'imageShow', '')
                        Vue.set(item, 'imgss', [])
                        let imgPath = item.image
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
                this.list = unreceived
              break;
            case '待评价':
              let unassess = l.unassess? JSON.parse(l.unassess):[]

              if(unassess && unassess.length>=1) {
                    unassess.forEach((item,index)=>{
                        Vue.set(item, 'imageShow', '')
                        Vue.set(item, 'imgss', [])
                        let imgPath = item.image
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
              this.list = unassess
              break;
          }
        })
    }
  },
  created() {

  }
}
</script>

<style lang="less" scoped>
#orderType {
  margin-top: 8.0vh;
  height: 100%;
  
  .hasData {
    width: 100%;
    height: 100%;
    flex-direction: column;
    justify-content: center;
    align-items: center;
  }
  .noDataShow {
    width: 100%;
    height: 450px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    img {
      width: 60%;
      height: 60%;
    }
    .desc {
      color: grey;
      font-size: 1.0rem;
      margin-top: 5px;
    }
  }
  .noData {
    margin-top: 3rem;
    width: 100%;
    height: 100%;
    text-align: center;
  }

  
}
</style>
