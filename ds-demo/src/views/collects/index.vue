<template>
  <div id="collects">
        <Header></Header>
        <List :list="list" :v-show="show"></List>
        <DoubleBounce v-show="!show" style="width:100px;height:100px;left:0;right:0;bottom:0;top:0;position: absolute; margin: auto;"></DoubleBounce>
  </div>
</template>

<script>  
import  Vue from 'vue'
import List from './components/List'
import Header from './components/Header'
import { DoubleBounce } from 'vue-loading-spinner'
import { vuexData } from '@/assets/js/mixin.js'
export default {
  name: 'Collects',
  mixins: [ vuexData ],
  data() {
    return {
        list: [],
        show: ''
    }
  },
  components: {
    Header,
    List,
    DoubleBounce
  },
  
  created(){
    
  },
  methods: {
      async jiexiImgs(item){
          let imgPath = item.image
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
                        let detailss = item.details
                        let iD = detailss.indexOf(';')
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
      async init() {
          this.show = false
          await this.get_collect_list(
              {
                  collect: this.GET_COLLECT_LIST?this.GET_COLLECT_LIST.join(','):''
              })
            .then(res => {
                if(res.data.data) {
                    this.list = JSON.parse(JSON.stringify(res.data.data.collects))
                    if(this.list&&this.list.length>=1) {
                        this.list.forEach(async (item)=>{
                            Vue.set(item, 'imageShow', '')
                            Vue.set(item, 'imgss', [])
                            Vue.set(item, 'detailsShow',[])
                            await this.jiexiImgs(item)
                            await this.jiexiDetail(item)
                        })
                    }
                }
            })
            
            this.show = true
      },
      clickLeft() {
        this.$router.back(); 
      },
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

<style lang="less" scoped>
    #collects {

        position: fixed;
        left: 0;
        top: 0;
        right: 0;
        bottom: 0;
        background-color: #ffffff;
        z-index: 99999;
    }
    .router-slider-enter-active,
    .router-slider-leave-active {
        transition: all 0.3s;
    }
    .router-slider-enter,
    .router-slider-leave-active {
        transform: translate3d(2rem, 0, 0);
        opacity: 0;
    }
</style>
