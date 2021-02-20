<template>
    <div>
        <van-search
          action-text
          style="height:7.5vh;"
          v-model="v"
          shape="round"
          background=""
          :placeholder="placeholderV"
          @search="onSearch"
          @cancel="onCancel"
      >
      </van-search>
  </div>
</template>

<script type="text/javascript">

export default {
  props:{
    value: String
  },
  watch:{
    value(newV) {
      this.v = newV
    },
    v(newV) {
      this.$emit('update:value',newV)
    },
  },
  data () {
    return {
        v: '',
        placeholderV: '小台农芒果'
    }
  },
  components: {

  },
  mounted(){
     this.$nextTick(()=>{
       this.init()
     }) 
  },
  methods: {
    init(){
    },
    onBack(){
      this.$router.push({ name: 'Category' })
    },
    onSearch(val){
      if(val) {
        this.v = val
        this.$emit('onSearch',val)
      }else {
        this.v = this.placeholderV
        this.$emit('onSearch',this.placeholderV)
      }
    },
    onCancel(){

    },
    // 到搜索界面
    goSearch () {
      this.$toast({
        message: '暂未实现哦~',
        duration: 800
      });
    }
  }
}
</script>

<style lang="less" scoped>
.searchWrapper {
  padding: 0.5rem 0.625rem;
  background: #fff;
  position: relative;
  top: 0;
  left: 0;
  right: 0;
  border-bottom: solid 1px #e6e6e6;
  z-index: 999;
}

.searchBox {
  display: block;
  padding: 0 0.875rem 0 1.75rem;
  border-radius: 1.75rem;
  height: 1.75rem;
  line-height: 1.75rem;
  font-size: 0.875rem;
  background: #f2f2f2;
}

.iconSearchWrapper {
  position: absolute;
  left: 1.1rem;
  top: 0.53rem;
  width: 1.25rem;
  height: 1.25rem;
}

.icon {
  fill: #999;
  width: 1rem;
  height: 1rem;
}

.iconSearch {
  width: 100%;
  height: 100%;
}

.searchPrompt {
  color: #b5b5b5;
}
</style>