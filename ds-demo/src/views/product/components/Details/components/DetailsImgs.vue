<template>
  <section class="content">
    <div 
      v-show="imagesG.length <= 0"
      class="img-wrap" 
      v-for="(img,index) in imgs"
      :key="index"
      >
      <van-image 
        v-if="img"
        :src="img.img"
      />
    </div>

    <div
      v-show="imagesG.length>0"
      class="img-wrap" 
      v-for="(img,index) in imagesG"
      :key="index"
      >
      <van-image 
        v-if="img"
        :src="img"
      />
    </div>
  </section>

</template>

<script>
export default {
  props:{
    images: Array
  },
  data() {
      return {
        imagesG:[],
          imgs: [
              { img: 'http://dummyimage.com/600x600/5a9e6e/FFF.png' },
              { img: 'http://dummyimage.com/600x600/5a9e6e/FFF.png' },
              { img: 'http://dummyimage.com/600x600/5a9e6e/FFF.png' },
              { img: 'http://dummyimage.com/600x600/5a9e6e/FFF.png' },
              { img: 'http://dummyimage.com/600x600/5a9e6e/FFF.png' },
              { img: 'http://dummyimage.com/600x600/5a9e6e/FFF.png' },
              { img: 'http://dummyimage.com/600x600/5a9e6e/FFF.png' },
              { img: 'http://dummyimage.com/600x600/5a9e6e/FFF.png' },
              { img: 'http://dummyimage.com/600x600/5a9e6e/FFF.png' },
          ]
      }
  },
  watch:{
    images(newV) {
      this.imagesG = newV
    },
    imagesG(newV){
      this.$emit("update:images",newV)
    }
  },
  computed:{
    
  },
  mounted(){
  }
}
</script>

<style lang="less" scoped>
  .content {
    width: 100%;
    margin-top: 10px;
    border-top: 5px solid #F8FCFF;
    .img-wrap {

      img {
    height: 120vw;

        display: block;
        width: 100%;
        height: 100%;
      }
    }
  }
</style>
