<template>
    <div>
        <van-dropdown-menu active-color="orange">
            <van-dropdown-item v-model="value1" :options="option1" />
        </van-dropdown-menu>
  </div>
</template>

<script type="text/javascript">

export default {
  props: {
    v1: Number,
  },
  watch:{
    v1(newVal) {
      this.value1 = newVal
    },

    value1(newVal) {
      this.$emit('update:v1',newVal)
      this.$emit('shuaiXuan',newVal)
    },
    
  },
  data () {
    return {
        value1: 0,
        option1: [
            { text: '价格升序', value: 0 },
            { text: '价格降序', value: 1 },
            { text: '销量排序', value: 2 },
            { text: '好评排序', value: 3 }
        ],
        
        value:''
    }
  },
  components: {

  },
  methods: {
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