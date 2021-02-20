<template>
  <div class="proAssess">
        <!-- <van-row style="margin-top: 2.0vw;">
            <van-col style="width: 100%; height: 6.0vh; display: flex;"><div style="margin:auto 0; margin-left: 2.5vw;">商品评价</div></van-col>
        </van-row>  -->
        <van-row >
            <van-col span="6" style="height: 6.0vh; display: flex;"><div style="margin:auto 0; margin-left: 2.5vw;">商品评价</div></van-col>
            <van-col span="18" style="height: 6.0vh; display: flex;">
                <van-rate v-model="value1" style="margin:auto 0;"/>
            </van-col>
        </van-row>
        <!-- <van-row >
            <van-col span="6" style="height: 6.0vh; display: flex;"><div style="margin:auto 0; margin-left: 2.5vw;">物流服务</div></van-col>
            <van-col span="18" style="height: 6.0vh; display: flex;">
                <van-rate v-model="value2" style="margin:auto 0;"/>
            </van-col>
        </van-row>
        <van-row >
            <van-col span="6" style="height: 6.0vh; display: flex;"><div style="margin:auto 0; margin-left: 2.5vw;">服务态度</div></van-col>
            <van-col span="18" style="height: 6.0vh; display: flex;">
                <van-rate v-model="value3" style="margin:auto 0;"/>
            </van-col>
        </van-row>     -->
        <div style="width: 100%; height: 100.0vh; background-color: white;"></div>
        
  </div>
</template>

<script>  
import { vuexData } from '@/assets/js/mixin.js'
export default {
  name: 'proAssess',
  mixins: [ vuexData ],
  props: {
      starNum: Number
  },
  watch: {
      starNum(newVal) {
          this.value1 = newVal
      },
      value1(newVal) {
          this.$emit('update:starNum', newVal)
      },
  },
  data() {
    return {
        value1: 0,
        // value2:2,
        // value3:3,
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
  
}
</script>

<style lang="less" scoped>
    .proAssess {
        width: 100%;
        border-bottom: 1px solid #A9A9A9;
        background-color: white;
        /deep/ .van-uploader__wrapper {
            width: 100vw;
        }

    }
</style>
