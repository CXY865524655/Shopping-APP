<template>
  <div class="assessHeader">
      <van-nav-bar 
            title="商品评价"
            right-text="发布"
            :fixed="true"
            :border="true"
            left-arrow
            style="height:3.2rem; line-height: 3.2rem;"
            @click-left="onClickLeft"
            @click-right="onClickRight"
        />
  </div>
</template>

<script>  
import { vuexData } from '@/assets/js/mixin.js'
export default {
  name: 'AssessHeader',
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
      onClickLeft() {
        this.$router.back()
      },

      // 发布
      onClickRight() {
        this.$emit('send')
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
    .assessHeader {
      background-color: green;
    }
</style>
