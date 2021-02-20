<template lang="html">
  <div class="resultPage">
        <van-nav-bar
            title="商品详情"
            :fixed="true"
            :border="false"
            style="height:8vh" 
        />
        <SHeader class="sheader" :value.sync="Svalue" @onSearch="onSearchA"></SHeader>
        <ShuaiXuan :v1.sync='value1' @shuaiXuan='shuaiXuan'></ShuaiXuan>
        <List :list.sync="list"></List>
        <Back @back='back' />
  </div>
</template>

<script>  
import SHeader from './components/SHeader'
import ShuaiXuan from './components/ShuaiXuan'
import List from './components/List'
import Back from '@/components/public/Back.vue'
import Vue from 'vue'
import { vuexData } from '@/assets/js/mixin.js'
export default {
  name: 'resultPage',
  mixins: [ vuexData ],
  data() {
    return {
            Svalue:'',
            list:[],
            value1:0,
        }
  },
  components: {
      SHeader,
      ShuaiXuan,
      List,
      Back
  },
  
  created(){
    
  },
  mounted() {
      this.$nextTick(()=>{
        this.init()
      })
  },
  methods:{
    onSearchA(val){
        this.onSearch(val,this.value1)
    },
    shuaiXuan(value1){
        this.onSearch(this.Svalue,value1)
    },
 
    async jiexiImgs(item){
                        let imgPath = item.image
                        let i = imgPath ? imgPath.indexOf(';') : -1
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
                            

                        }else if(imgPath && imgPath.length>=1){
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
                let detailss = item.details
                let iD = detailss?detailss.indexOf(';'):-1
                if(iD >= 0) {
                    // 多个
                    let pathss = detailss.split(';')
                    pathss.forEach(async (v) => {
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
                }else if(detailss&&detailss.length>=1){
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
    async onSearch(val,condition1){
        this.show = false
        await this.$api.goods.searchGoods(val,condition1)
          .then(async res =>{
              this.list = JSON.parse(JSON.stringify(res.data.data))
                if(this.list&&this.list.length >= 1) {
                    await this.list.forEach(async (item)=>{
                        Vue.set(item, 'imageShow', '')
                        Vue.set(item, 'imgss', [])
                        Vue.set(item, 'detailsShow',[])

                        await this.jiexiImgs(item)
                        await this.jiexiDetail(item)
                    })
                }
          })
            this.show = true
    },
    async init() {
        this.Svalue = this.$route.query.name
        this.onSearch(this.Svalue,0)
    },

      
  },
  activated(){
    
  },
  watch: {
      
  },
}
</script>

<style lang="less" scoped>
.resultPage::-webkit-scrollbar {
      display: none  
}
.resultPage {
    position: fixed;

    // 默认加了 position: fixed; 无法滑动 ，增加下面2行代码可以解决
    -webkit-overflow-scrolling: auto;
    overflow-y: scroll;

    
}

.sheader {
    margin-top: 8vh;
}


</style>
