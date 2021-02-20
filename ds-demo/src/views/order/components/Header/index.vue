<template>
  <div class="orderHeader">
      <van-nav-bar 
            left-arrow
            title="确认订单"
            :fixed="true"
            :border="true"
            style=""
            @click-left="clickLeft"
            class="nav"
        />
  </div>
</template>

<script>  
import { vuexData } from '@/assets/js/mixin.js'
export default {
  name: 'OrderHeader',
  mixins: [ vuexData ],
  data() {
    return {
           
    }
  },
  components: {
      
  },
  
  created(){
    

  },
  methods: {
      clickLeft() {
        this.$router.back(); 
      }
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
    .orderHeader {
        background-color: #C0C0C0;
        .nav {
            height:7.0vh;   
            line-height: 7.0vh;
        }

    }
</style>
