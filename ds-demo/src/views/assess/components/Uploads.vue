<template>
  <div class="uploads">
                
                <!-- <van-image
                    style="width: 85%; height: 85%; margin: auto;  margin-top: 1.5vh; margin-left: 2.0vh;"
                    src="https://img.yzcdn.cn/vant/cat.jpeg"
                /> -->
                <van-uploader 
                    style="margin: auto;  margin-top: 1.5vh; margin-left: 2.0vh; word-wrap:break-word;"
                    :after-read="afterRead" 
                    :before-read="beforeRead"
                    v-model="fileList"
                    multiple 
                    max-count="6"
                >
                    <div style="width:80px;height:80px;  text-align: center; border: 1px dashed #C0C0C0; ">
                        
                        <van-row style="height:60%; display: flex;">
                            
                                <svg-icon iconClass="camera"
                                    style="margin: auto;width: 7.0vw; height: 7.0vh;" 
                                />
                            
                        </van-row>

                        <van-row style="height:40%; display: flex;">
                            <div style="font-size: 13px; margin-bottom: 2.2vh; margin: auto; color:#A9A9A9;">添加图片</div>
                        </van-row>


                    </div>
                </van-uploader>
                <div style="width:100%;height:20px;"></div>
            <!-- <van-col span="6" style="height: 100%; display: flex;">
                <van-uploader 
                    style="width: 85%; height: 85%; margin: auto;  margin-top: 1.5vh; margin-left: 2.0vh;"
                    :after-read="afterRead" 
                    :before-read="beforeRead"
                >
                    <div style="width:80px;height:80px;  text-align: center; border: 1px dashed #C0C0C0;">
                    
                        <van-row style="height:60%; display: flex;">
                            <svg-icon iconClass="video"
                                    style="margin: auto; width: 7.0vw; height: 7.0vh;" 
                                />
                        </van-row>

                        <van-row style="height:40%; display: flex;">
                            <div style="font-size: 13px; margin-bottom: 2.2vh; margin: auto;color:#A9A9A9;">添加视频</div>
                        </van-row>
                    </div>
                </van-uploader>
            </van-col> -->
  </div>
</template>

<script>  
import { vuexData } from '@/assets/js/mixin.js'
export default {
  name: 'uploads',
  mixins: [ vuexData ],
  data() {
    return {
    fileList: [
        
      ] 
    }
  },
  components: {
      
  },
  
  created(){
    

  },
  methods: {
    
    beforeRead(file) {
        if (file.type !== 'image/png' && file.type !== 'image/jpg') {
            this.$toast('请上传 jpg 格式图片');
            return false;
        }
        return true;
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
    .uploads {
        width: 100%;
        background-color: white;
        /deep/ .van-uploader__wrapper {
            width: 100vw;
        }

    }
</style>
