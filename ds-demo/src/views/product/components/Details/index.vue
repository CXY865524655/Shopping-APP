<template>
  <div class="details">
    <van-tabs v-model="active" @click='onClickTabs'>
        <van-tab title="详情">
            <DetailsImgs :images.sync="images"></DetailsImgs>
        </van-tab>
        <van-tab title="评论">
          <div 
            style="height: 700px;"
            v-show='show'
          />
          <div 
            style="height: 700px;"
            v-show='assessIsNull&&!show'
          >
            还没有人来评论哦~~
          </div>
          <AssessList 
            v-show='!assessIsNull&&!show'
            v-for="(val, index) in userAssesses"
            :key="index"
            :assess="!assessIsNull? val.assess:''"
            :sendTime="!assessIsNull? val.sendTime:''"
            :image="!assessIsNull? val.imageShow:''"
            :starNum='!assessIsNull? val.starNum:0'
            :username="!assessIsNull? val.username:''"
            :class="{mBottom:userAssesses&&userAssesses.length>=1?index===userAssesses.length-1:false,mTop:index>=1}"
            >
              
            </AssessList>
          <DoubleBounce v-show="show" style="width:100px;height:100px;left:0;right:0;bottom:0;top:0;position: absolute; margin: auto;"></DoubleBounce>
        </van-tab>
    </van-tabs>
  </div>

</template>

<script>
import DetailsImgs from './components/DetailsImgs'
import AssessList from './components/AssessList'
import { vuexData } from '@/assets/js/mixin.js'
import { DoubleBounce } from 'vue-loading-spinner'
import Vue from 'vue'

export default {
  mixins: [ vuexData ],
  computed:{
    
  },
  data() {
    return {
      show: false,
      assessIsNull: true,
      active: 0,
      imagesG:[],
      te:'54676uyrtfds3ertykrtr4356756i8ujhfgdrft5u7iulkjhfgrfe4wertyuiupoiiuytreedfghjkhljkhjghgfdsartyuiujgfdsafgdhjkjhgrewt5y6uikjhg',
      imgs: [
        {imgPath: 'http://dummyimage.com/600x600/5a9e6e/FFF.png'},
        {imgPath: 'http://dummyimage.com/600x600/5a9e6e/FFF.png'},
        {imgPath: 'http://dummyimage.com/600x600/5a9e6e/FFF.png'},
        {imgPath: 'http://dummyimage.com/600x600/5a9e6e/FFF.png'},
        {imgPath: 'http://dummyimage.com/600x600/5a9e6e/FFF.png'},
        {imgPath: 'http://dummyimage.com/600x600/5a9e6e/FFF.png'},
      ],
      userAssesses: [
      ]
    }
  },
  props: {
    images: Array,
  },
  components: {
    DetailsImgs,
    AssessList,
    DoubleBounce
  },
  mounted(){
    
  },
  methods: {
    onClickTabs(name, title) {
      if(title === '评论') {
        this.show = true
         this.getGoodAssess({
          goodsid: JSON.parse(this.$route.query.goodInfo).goodsid
        })
        .then(res => {
          let getAssess = res.data.data
          if(getAssess && getAssess.length > 0) {
             let oo = res.data.data
              oo.forEach((item,index)=>{
                  let imgPath = item.image_url
                  
                  if(imgPath.length>=1 && imgPath.substring(0,4)!='http'){
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
                        })
                  }else{
                    Vue.set(item, 'imageShow', 'https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3200149520,426458188&fm=26&gp=0.jpg')
                  }
              })
              
            this.userAssesses = oo
            this.assessIsNull = false
          }else {
              this.userAssesses = []
              this.assessIsNull = true
          }
          this.show = false
        })
        

      }
    }
  },
  watch: {
    images(newV) {
      this.imagesG = newV
    },
    imagesG(newV) {
      this.$emit("update:images", newV)
    }
  }
}   
</script>

<style lang="less" scoped>
.mBottom {
  margin-bottom: 10vh;
}
.mTop {
  margin-top: 4vh;
}
.details {
    /deep/ .van-tabs__line {
    z-index:0;
  }
}

</style>
