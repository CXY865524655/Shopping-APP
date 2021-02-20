<template>
  <div id="addAddress">
    <Editor :editorType="editorType" :address="address"></Editor>
  </div>
</template>

<script type="text/javascript">

import Editor from './components/Editor.vue'
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
  created() {

  },
  mounted() {
    this.editorType = this.$route.params.editorType
  }
}
</script>
<style lang="less" scoped>

#addAddress {
  position: fixed;
  left: 0;
  top: 0;
  right: 0;
  bottom: 0;
  background-color: #f5f5f5;
  z-index: 99999;
}
</style>
