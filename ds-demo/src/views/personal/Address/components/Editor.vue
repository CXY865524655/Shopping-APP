
<template>
  <div id="editor">
    <van-nav-bar :title="text"  
                 left-arrow
                 :fixed=true
                 :border=true
                 @click-left="onClickLeft"></van-nav-bar>
    <!-- area.js文件在官方可以下载，放于assets/js/area.js  -->
    <van-address-edit :area-list="areaList"
                      show-postal
                      show-set-default
                      show-search-result
                      :show-delete="text==='编辑地址'"
                      :search-result="searchResult"
                      save-button-text="保存"
                      @save="onSave"
                      @delete="onDelete"
                      :address-info='addressInfo'
                      style="margin-top: 3.33rem"
                      :is-saving="saving"
                      :is-deleting="deleting"
                      />
  </div>
</template>

<script type="text/javascript">
// area.js文件在官方可以下载，放于assets/js/area.js 
import areaList from '@/config/area.js'
import { vuexData } from '@/assets/js/mixin.js'
import Vue from 'vue'
export default {
  props: {
    editorType: String,
    address: Object
  },  
  created(){
    
  },
  mixins: [vuexData],
  data () {
    return {
      saving: false,
      deleting: false,
      areaList: areaList,
      searchResult: [],
      addressInfo: {},
      text: ''    // 地址编辑类型  add or edit
    }
  },
  components: {

  },
  methods: {
    
    // 生成唯一ID (n: 生成多少位， parseInt(Math.random() * str.length) 相当于 [0,1) * 36 即 [0,36) 再取整 即 [0,35] 对应str的1-36个字 * n个)
    getAddressID(n) {
      var str = 'abcdefghijklmnopqrstuvwxyz0123456789'
      var result = ''
      for(var i=0; i < n; i++) {
        result += str[parseInt(Math.random() * str.length)]
      }
      return result
    },


    // 1.返回上级界面
    onClickLeft () {
      this.$router.go(-1);
    },
    // 2. 保存
    async onSave (content) {
      this.saving = true
      switch(this.text) {
        case '新增地址':
          content['id'] = this.getAddressID(36).toString()
          content['address'] = content.province + content.city + content.county + content.addressDetail
          await this.insertAddress({
            userId: this.USER_ID,
            content
          })
          this.saving = false
          // 返回
          this.$router.back()       
          break

        case '编辑地址':
          content['address'] = content.province + content.city + content.county + content.addressDetail
           // 保存地址
          await this.updateAddress({
            userid: this.USER_ID,
            content
          })
          // 返回
          this.$router.back()
          break 
      }
    },

    // 删除
    async onDelete(content) {
      let addressId = content['id']
      let userId = this.USER_ID
      // 删除操作
      await this.delectUserAddress({
        id: addressId,
        userid: userId
      })
      this.$router.back()
    },
    // 生成不重复的id
    addressID () {
      var lastUuid = 0;
      return (new Date()).getTime() * 1000 + (lastUuid++) % 1000;
    }
  },
  watch: {
    editorType(newVal) {
      this.text = newVal
    },
    address(newVal) {
      this.addressInfo = newVal
    }
  }
}
</script>
<style lang="less" scoped>

#editor {
  position: fixed;
  left: 0;
  top: 0;
  right: 0;
  bottom: 0;
  background-color: #f5f5f5;
  z-index: 99999;
  
}
</style>
