<template>
  <div class="good">
        <van-row style="height: 100%; display: flex;">
            <van-col span="6" style="height: 100%; display: flex;">
                
                <van-image
                    v-if="assess.imageShow"
                    style="
                      width: 85%;
                      height: 85%;
                      margin: auto;
                      margin-top: 1.5vh;
                      margin-left: 2.0vh;
                    "
                    :src="assess.imageShow"
                />
            </van-col>
            <van-col span="18" style="height: 100%; display: flex;">
                <div style="width: 85%; height: 75%; margin: auto; margin-top: 3.0vh;">{{assess.productModel.goodsname}}</div>
            </van-col>
        </van-row>
        <div style="width: 100%; height: 2.3vh; border-bottom: 1px solid #DCDCDC; background-color: white;"></div>
  </div>
</template>

<script>  
import { vuexData } from '@/assets/js/mixin.js'
export default {
  name: 'good',
  mixins: [ vuexData ],
  data() {
    return {
        assess: JSON.parse(this.$route.query.assess)
    }
  },
  components: {
      
  },
  
  created(){
    

  },
  methods: {
      

  },
  mounted() {
      
  },
  activated(){
    
  },
  watch: {
      
  },
}
</script>

<style lang="less" scoped>
    .good {
        width: 100%;
        height: 15.0vh;
        margin-top: 7.0vh;
        background-color: white;
        /deep/ .van-image__img {
            border-radius: 5px;
        }
    }
</style>
