<template>
  <div id="cartHeader">
      <van-nav-bar 
            title="购物车"
            :fixed="true"
            :border="true"
            style="height:3.2rem; line-height: 3.2rem;"
            right-text="删除"
            @click-right="clickRemove"
        />
  </div>
</template>

<script>  
import { vuexData } from '@/assets/js/mixin.js'
export default {
  name: 'CartHeader',
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
      clickRemove() {
        this.$emit('clickRemove')
      },
      
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

</style>
