<template>
    <div class="home">
        <!-- <SHeader></SHeader> -->
        <Swipes :list='swipes'></Swipes>
        <GridItems></GridItems>
        <QiangGou :list='goodPrice' @finish='finish' style="margin-bottom: 15px;"></QiangGou>
        <!-- <FooterHeader></FooterHeader> -->
        <FooterList class="footer" :list='footer' ></FooterList>
    </div>
</template>

<script>
import Swipes from './components/Swipes'
import GridItems from './components/GridItems'
import QiangGou from './components/QiangGou'
import FooterList from './components/FooterList'
import FooterHeader from './components/FooterHeader'
// import SHeader from './components/SHeader'
import Vue from 'vue'
export default {
    data() {
        return {
            goodPrice: [],
            footer: [],
            swipes: []
        }
    },
    components: {
        Swipes,
        GridItems,
        QiangGou,
        FooterList,
        // SHeader
        FooterHeader
    },
    created() {

    },
    mounted() {
            this.init()
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
                            pathss.forEach(async v => {
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
        finish() {
            this.$api.goods.initHomeData()
            .then(res => {
                this.goodPrice = JSON.parse(res.data.data.homeData.goodprice)
                if(this.goodPrice&&this.goodPrice.length>=1) {
                        this.goodPrice.forEach(async item =>{
                        Vue.set(item, 'imageShow', '')
                        Vue.set(item, 'imgss', [])
                        Vue.set(item, 'detailsShow',[])
                        await this.jiexiImgs(item)
                        await this.jiexiDetail(item)
                    })

                }

                this.footer = JSON.parse(res.data.data.homeData.recommends)
                if(this.footer&&this.footer.length>=1) {
                        this.footer.forEach(async item =>{
                        Vue.set(item, 'imageShow', '')
                        Vue.set(item, 'imgss', [])
                        Vue.set(item, 'detailsShow',[])
                        await this.jiexiImgs(item)
                        await this.jiexiDetail(item)
                    })

                }

                                 
                this.swipes = JSON.parse(res.data.data.homeData.headerswipes)
                if(this.swipes&&this.swipes.length>=1) {
                        this.swipes.forEach(async item =>{
                        Vue.set(item, 'imageShow', '')
                        Vue.set(item, 'imgss', [])
                        Vue.set(item, 'detailsShow',[])
                        await this.jiexiImgs(item)
                        await this.jiexiDetail(item)
                    })

                }
            })
        },

        fo(some){
            some.forEach(async item=>{
                Vue.set(item, 'imageShow', '')
                Vue.set(item, 'imgss', [])
                Vue.set(item, 'detailsShow',[])
                await this.jiexiImgs(item)
                await this.jiexiDetail(item)
            })
        },
         init() {
            this.$api.goods.initHomeData()
            .then(async res => {
                let pri = JSON.parse(JSON.stringify(res.data.data.homeData.goodprice))
                if(pri&&pri.length>=1) {
                    await this.fo(pri)    
                    this.goodPrice = pri
                }


                this.footer = JSON.parse(JSON.stringify(res.data.data.homeData.recommends))
                if(this.footer&&this.footer.length>=1) {
                    this.footer.forEach(async item =>{
                        Vue.set(item, 'imageShow', '')
                        Vue.set(item, 'imgss', [])
                        Vue.set(item, 'detailsShow',[])
                        await this.jiexiImgs(item)
                        await this.jiexiDetail(item)
                    })
                }


                this.swipes = JSON.parse(JSON.stringify(res.data.data.homeData.headerswipes))
                if(this.swipes&&this.swipes.length>=1) {
                    this.swipes.forEach(async item => {
                        Vue.set(item, 'imageShow', '')
                        Vue.set(item, 'imgss', [])
                        Vue.set(item, 'detailsShow',[])
                        await this.jiexiImgs(item)
                        await this.jiexiDetail(item)
                    })
                }
            })
            
        },
        hehe(){
            this.$api.goods.initHomeData()
            
        }
    },
    watch: {
       
    }
}
</script>

<style lang="less" scoped>
.home::-webkit-scrollbar {
      display: none  
}
.home {
    position: fixed;
    // 默认加了 position: fixed; 无法滑动 ，增加下面2行代码可以解决
    -webkit-overflow-scrolling: auto;
    overflow-y: scroll;
    .footer{
        margin-bottom: 8.0vh;
    }
}

</style>