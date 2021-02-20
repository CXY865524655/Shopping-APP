<template>
  <div class="saySometing">
      <van-field
        v-model="message"
        rows="2"
        autosize
        type="textarea"
        placeholder="宝贝满足你的期待了吗？ 说说你的使用心得，分享给想买的他们吧"
    />

        <div style="width: 100%; height: 5.0vh; background-color: white;"></div>

  </div>
</template>

<script>  
import { vuexData } from '@/assets/js/mixin.js'
export default {
  name: 'SaySometing',
  mixins: [ vuexData ],
  props: {
    assessMsg: String
  },
  data() {
    return {
       message: ''   
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
      assessMsg(newVal) {
        this.message = newVal
      },
      message(newVal) {
        this.$emit('update:assessMsg', newVal)
      },
  },
}
</script>

<style lang="less" scoped>
    .saySometing {
        width: 100%;
        height: 15.0vh;
        margin-top: 2.5vh;
    }
</style>
