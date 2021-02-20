<template>
  <div id="addressEditor">
    <Editor :editorType="editorType" :address="address"></Editor>
  </div>
</template>

<script type="text/javascript">

import Editor from './components/Editor.vue'
import Vue from 'vue'
export default {
  data () {
    return {
        editorType: '',
        address: {}
    }
  },
  components: {
    Editor
  },
  methods: {
    
  },
  watch: {
    
  },
  mounted() {
    this.address = JSON.parse(this.$route.params.address)
    this.editorType = this.$route.params.editorType
  }
}
</script>
<style lang="less" scoped>

#addressEditor {
  // 编辑 新增 页面 路由跳转动画必须加的css，否则没有跳转动画
  position: fixed;
  left: 0;
  top: 0;
  right: 0;
  bottom: 0;
  background-color: #f5f5f5;
  z-index: 99999;
}
</style>
