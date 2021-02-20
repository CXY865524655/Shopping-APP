<template>
  <div id="collectHeader">
      <van-nav-bar 
            left-arrow
            title="我的收藏"
            :fixed="true"
            :border="true"
            style="height:3.2rem; line-height: 3.2rem;"
            
            @click-left="clickLeft"
        />
  </div>
</template>

<script>  
import { vuexData } from '@/assets/js/mixin.js'
export default {
  name: 'CollectHeader',
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
  #collectHeader {
    z-index: 100;
  }
</style>
