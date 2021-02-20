<template>
  <section class="content">
    <div class="img-wrap">
      <div class="content1">
            <van-row style="height: 100%; ">
                <van-col style="height: 100%;  display: flex;" span="4">
                    <van-image
                        v-show="image"
                        :src="image"
                        style="width: 12vw; height:12vw; margin: 0 auto; margin-left: 4vw;"
                        round
                    />
                </van-col>
                <van-col span="20" style="height: 100%;  display: flex; margin-top: 0.8vh;" >
                    <div style="width: 100%; height: 5.0vh;  display: flex;">
                        <div style="width: 100%;">
                            <van-row style="height: 100%;width: 100%;  ">
                                <van-col style="height: 100%;  display: flex;" span="5">
                                    <div style="margin:auto 0; margin-left: 12px; font-weight: bold;font-size:16px;">{{username}}</div>
                                </van-col>
                                <van-col style="height: 100%;  display: flex;" span="19">
                                    <div style="display: flex;">
                                        <van-rate
                                        
                                            v-model="starNum"
                                            :count="starNum"
                                            :size="20"
                                            color="#ee0a24"
                                            icon="like"
                                            void-color="#eee"
                                            :touchable="false"
                                            readonly
                                            style="margin:auto 0;"
                                        />
                                    </div>
                                </van-col>
                            </van-row>


                            <van-row style="width: 100%;  ">
                                    <div style="
                                      margin:auto 0;
                                      margin-left: 4vw;
                                      font-size:13px;
                                      color: #808080;
                                    ">{{sendTime}}</div>
                            </van-row>

                                    
                        </div>
                    </div>
                </van-col>
            </van-row>
            <div style="width: 77%;margin-left: 19vw; margin-top: 3.2vh; word-wrap:break-word;font-size:15px;">
                {{assess}}
            </div>
      </div>
    </div>
  </section>

</template>

<script>
export default {
  props:{
    assess:String,
    sendTime:String,
    starNum: Number,
    image: String,
    username:String
  },
  watch: {
    
  },
  data() {
      return {
          value: 0,
          name: '陈祥煜',
      }
  },
  methods: {
      getTime() {
            let date = new Date()
            let dateStr = date.getFullYear() + '-' + date.getMonth() + '-' + date.getDate() + ' ' + date.getHours() +':' + date.getMinutes()+':' +date.getSeconds()
            return dateStr
      },
      getName(name) {
          let newN = name.trim()
          if(newN && newN.length > 0) {
                let firstName = newN.substr(0,1)
                let lastName = newN.substr(newN.length - 1,1)
                return firstName + '**' + lastName
          }
          return '';
      },

  },
  computed:{
    
  }
}
</script>

<style lang="less" scoped>

  .mLeft {
    margin-left: 1.0vw;
  }
  .mTop {
    margin-top: 0.3vh;
  }
  .content {
    width: 100%;
    margin-top: 10px;
    border-top: 5px solid #F8FCFF;
    
    .img-wrap {
    height:auto!important;
    
    
      .content1 {
          width: 100%;
          height: 100%;
      }
      img {
        display: block;
        width: 100%;
        height: 100%;
      }
    }
  }
</style>
