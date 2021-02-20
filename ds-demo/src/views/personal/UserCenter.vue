<template>
  <div id="userCenter">
    <van-nav-bar title="个人资料"
                 :fixed=true
                 :border=false
                 @click-left="onClickLeft"
                 left-arrow
                 style="height:2.5rem" />
    <div class="icon" style="height:4.5rem;width:100%;" >
      <span class="title">头像</span>
      <div
        @click="changeImg"
        style="position:absolute;right:15px;margin-top:1px;"
        >
        <van-image 
            round
            style="position:absolute;right:30px;top:-20px; height:3.5rem;width:3.5rem;"
            v-if="GET_USER_IMG"
            :src="GET_USER_IMG"
            alt=""/>

            <van-image 
            round
            style="position:absolute;right:30px;top:-20px; height:3.5rem;width:3.5rem;"
            v-else
            src="https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3200149520,426458188&fm=26&gp=0.jpg"       
            alt=""
        />
      <van-icon 
        color="#969799"
        name="arrow"
        
       />
      </div>
      
    </div>
    <van-cell-group>
      
      <van-cell title="昵称"
                is-link
                :value="USER_NAME"
                @click.stop="goToChangeNickName" />
      <van-cell title="性别" 
                is-link
                :value="USER_SEX"
                @click.stop="onChangeSex" />
      <van-cell title="生日"
                is-link
                :value="USER_BIRTHDAY"
                @click.stop="selectBrithday"
                 />
      <van-cell title="手机号"
                :value="USER_TELEPHONE" />
      <van-cell title="设置支付密码"
          is-link
          @click.stop="goToSetPay"
        />
        
    </van-cell-group>




    <van-button size=large
                style="margin-top:1rem"
                @click.stop="logOut">退出登陆</van-button>
    <!-- 修改昵称弹框 -->           
    <van-dialog
      v-model="showChangeName"
      title="请输入新的昵称"
      show-cancel-button
      :beforeClose="changeName"
    >
      <!-- <img src="https://img.yzcdn.cn/vant/apple-3.jpg"> -->
      <van-cell-group>
        <van-field v-model="value" placeholder="请输入用户名" input-align="center" />
      </van-cell-group>
    </van-dialog>  

<!-- 修改头像 -->           
    <van-dialog
      v-model="showChangeImg"
      title="头像"
      show-cancel-button
      :before-close="changeUserImg"
      :cancel="cancelBox"
    >
    <div style="margin-top:4.0vh;display:flex;" >
      <div style="margin:auto;">
        <!-- <van-image 
          style="height:80px;width:80px;float:left"
          :src="GET_USER_IMG"
        /> -->

        <van-image 
                        round
                        style="height:80px;width:80px;float:left"
                        v-if="GET_USER_IMG"
                        :src="GET_USER_IMG"
                        alt=""/>

                        <van-image 
                        round
                        style="height:80px;width:80px;float:left"
                        v-else
                        src="https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3200149520,426458188&fm=26&gp=0.jpg"       
                        alt=""/>


      <van-icon 
        color="#969799"
        name="arrow"
         style="margin-left:3.0vw;margin-right:3.0vw;"
       />
      <van-uploader
        v-model="fileList"
        multiple
        :before-read="beforeRead"
        :max-count="1"
        style=""
      >
      </van-uploader>
      </div>
        

    </div>
        
        
    </van-dialog> 

    <!-- 修改支付密码 -->           
    <van-dialog
      v-model="showSetPay"
      :title="setPayTitle"
      show-cancel-button
      :beforeClose="set_pay_pwd"
    >
      <!-- <img src="https://img.yzcdn.cn/vant/apple-3.jpg"> -->
      <van-cell-group>
        <van-field v-model='payPwd' placeholder="输入支付密码" input-align="center" />
      </van-cell-group>
    </van-dialog> 



    <!-- 时间选择器 -->
    <van-popup v-model="showDateTimePopView"
               round
               position="bottom">
      <van-datetime-picker v-model="currentDate"
                           type="date"
                           @confirm="confirm" 
                           @cancel="cancel"
                           :formatter="formatter"
                           :max-date="maxDate"
                           :min-date="minDate" />
    </van-popup>
    <!-- 性别选择器 -->
    <van-popup v-model="showChooseSex"
               position="bottom"
               :style="{ height: '25%' }">
      <van-radio-group v-model="user.sex">
        <van-cell-group style="margin-top:2rem; text-align: center;"
                        @click.stop="clickCell(radio)">
          <van-cell title="男"
                    clickable
                    @click="radio = '1'">
            <van-radio slot="right-icon"
                       name="1"
                       checked-color="#07c160" />
          </van-cell>
          <van-cell title="女"
                    clickable
                    @click="radio = '2'">
            <van-radio slot="right-icon"
                       name="2"
                       checked-color="#07c160" />
          </van-cell>
        </van-cell-group>
      </van-radio-group>
    </van-popup>
    <!--路由的出口-->
    <transition name="router-slider"
                mode="out-in">
      <router-view></router-view>
    </transition>
  </div>

</template>
<script type="text/javascript">
// import { mapState, mapMutations, mapGetters, mapActions } from 'vuex'
import { vuexData } from '@/assets/js/mixin.js'
import md5 from 'js-md5'

// // 引入时间格式化组件Moment
import Moment from 'moment'

export default {
  name: 'UserCenter',
  mixins: [vuexData],
  data () {
    return {
      isRemove:true,
      value: '',
      showChangeName: false,  // 是否显示修改昵称弹框
      showDateTimePopView: false,
      showChooseSex: false,
      currentDate: new Date('2020/01/22'),
      // 最小时间
      minDate: new Date('1949/01/01'),
      maxDate: new Date('2099/01/01'),
      showSetPay: false,
      setPayTitle: '',
      payPwd: '',
      showChangeImg:false,
      fileList:[],
      uploadForm: null,
      userImg:[]
    }
  },
  computed: {
    
    phoneNumber () {
      // 设置隐藏手机号中间四位
      var mobile = String(this.userInfo.phone)
      var reg = /^(\d{3})\d{4}(\d{4})$/
      return mobile.replace(reg, '$1****$2')
    }
  },
  components: {

  },
  methods: {

    cancelBox(){

    },  
    
    changeImg(){
      this.showChangeImg = true
    },
    // ...mapMutations(['USER_INFO_BRITHDAY', 'LOGIN_OUT', 'USER_INFO_SEX']),
    // ...mapActions(['log_out']),
    // 返回
    onClickLeft () {
      this.$router.back();
    },



    // 修改昵称
    goToChangeNickName () {
      this.showChangeName = true;
    },
    async goToSetPay(){
      await this.$api.user.hasPayPwd(this.USER_ID)
      .then(res => {
        if(res.data.status === 'success' ) {
          let hasPayPwd = res.data.data
          if(hasPayPwd) {
              this.setPayTitle = '请输入原来的支付密码'
          }else {
              this.setPayTitle = '请输入新的支付密码'
          }
            
        }
      })
      this.showSetPay = true;
    },

    // 修改昵称
   async changeName (action, done) {
      
      if(action=='cancel') {
        done()
        return 
      }

      if(action=='confirm') {
      
        
        await this.change_username({ 
                username: this.value,
                id: this.USER_ID
              })
              .then(() => {
                setTimeout(() => {
                
                  this.showChangeName = false;
                  this.$toast({
                    message: "用户信息修改成功！",
                    duration: 800
                  })
                  
                }, 300);
              })
              .catch(() => {
                this.value = this.user.username
              })
            done()
      }
      
    },

 // 修改昵称
   async set_pay_pwd (action, done) {
      
      if(action=='cancel') {
        done()
        return 
      }

      if(action=='confirm') {
        if(this.payPwd) {
          let len = this.payPwd.length
          if(len !== 6) {
              this.$toast('密码为6位数')
              this.payPwd = ''
              done(false)
              return
          }else {
            if(this.setPayTitle === '请输入原来的支付密码') {
                await this.$api.user.checkPayPwd(this.USER_ID, md5(this.payPwd))
                .then(res => {
                    if(res.data.status === 'success') {
                        this.setPayTitle = '请输入新的支付密码'

                    }else if(res.data.status === 'fail') {
                        this.$toast(res.data.data.errMsg)
                    }
                    this.payPwd = ''
                    done(false)
                    return
                })
            }else if(this.setPayTitle === '请输入新的支付密码'){
                await this.$api.user.setPayPwd(this.USER_ID, md5(this.payPwd))
                .then(() => {
                    this.showSetPay = false;
                    this.$toast({
                      message: "密码修改成功！",
                      duration: 800
                    })
                    this.payPwd = ''
                })
              done()
              return
            } 
          }
        }else {
          this.$toast('请输入密码')
          done(false)
          return
        }
      }
    },
    
    // 性别弹窗
    onChangeSex () {
      this.showChooseSex = true;
    },

    // 修改性别
    clickCell (radio) {
      let sex = radio;
      this.change_sex({ 
        id: this.USER_ID,
        sex
       })
       .then(() => {
          setTimeout(() => {
            this.showChooseSex = false;
            this.$toast({
              message: "用户信息修改成功！",
              duration: 800
            })
        }, 300);
       })
      
    },
    // 选择生日
    selectBrithday () {
      this.showDateTimePopView = true;
    },
    // 格式化DateTime pickView
    formatter (type, value) {
      if (type === 'year') {
        return `${value}年`;
      } else if (type === 'month') {
        return `${value}月`
      } else if (type === 'day') {
        return `${value}日`
      }
      return value;
    },
    // DateTime pcikView 确定
    confirm (value) {
      let birthday = Moment(value).format("YYYY-MM-DD");
      // this.brithdayText = brithday;
      
      this.change_birthday({ 
          id: this.USER_ID,
          birthday
        })
        .then(() => {
          
          this.showDateTimePopView = false;
          this.$toast({
            message: '个人资料修改成功',
            duration: 800
          })
       })
      
    },
    // DateTimt pickView 取消
    cancel () {
      this.showDateTimePopView = false;
    },
    // 退出登录
    logOut () {
      this.$dialog.alert({
          title: "确认要退出登录吗？",
          message: "确认要退出登录吗？",
          showCancelButton: true
        }).then(() => {
            this.log_out()
            
            this.$toast({
                message: '退出登录成功',
                duration: 800   
            })
            this.$router.back()
            // this.$toast("你点击了确定按钮")
        }).catch(() => {
            // this.$toast("你点击了取消按钮")
        })
    //   Dialog.confirm({
    //     message: this.$t('mine.loginInfo')
    //   }).then(() => {
    //     // on confirm
    //     this.LOGIN_OUT();
    //     Toast({
    //       message: this.$t('mine.infoSuccess'),
    //       duration: 800
    //     });
    //     this.$router.back();
    //   }).catch(() => {
    //     // on cancel
    //   });
    },
    init(){
      this.value = this.USER_NAME
      // let path = this.GET_USER_IMG_path
      // if(this.GET_USER_IMG) {
      //     let li=path.lastIndexOf('/')
      // }
    },

    beforeRead(file) {
      this.userImg.push(file)
      this.uploadForm = null
      this.uploadForm = new FormData()
      this.uploadForm.append('file', file)
      return true
    },

    async dodo(){
        let dataF = this.uploadForm
                                if(dataF.getAll("file").length>0){
                                    await this.$api.user.saveUserImg(dataF)
                                    .then(async ()=>{
                                        let str = ''

                                        if(dataF.getAll("file").length >= 1) {
                                            await dataF.getAll("file").forEach((item,index) => {
                                                if(index===0) {
                                                    str = str + item.name
                                                }else {
                                                    str = str + ';' + item.name
                                                }
                                            })
                                            await this.$api.user.updateUserImgPath(str, this.USER_ID)
                                            .then(async ()=>{
                                                await this.$api.user.getUserNameByID(this.USER_ID)
                                                .then(async res => {
                                                  let u = JSON.parse(JSON.stringify(res.data.data))
                                                  u.imageShow = ''
                                                  let imgP = u.image_url
                                                  await this.$api.user.getUserImg(imgP)
                                                  .then(res => {
                                                      return 'data:image/png;base64,' + btoa(
                                                          new Uint8Array(res.data)
                                                          .reduce((data, byte) => data + String.fromCharCode(byte), '')
                                                      )
                                                  })
                                                  .then(data => {
                                                      u.imageShow = data
                                                      
                                                      this.fileList = []
                                                      this.showChangeImg = false
                                                      this.SET_USER({
                                                        user: u
                                                      })
                                                      this.$router.push({ name: 'UserCenter'})

                                                  })


                                                    
                                                })


                                            }) 
                                        }
                                        
                                    })
                                    
                                }
    },

    async changeUserImg(action, done){
            if(action === 'confirm') {
                await this.$api.user.delUserImgPath(this.USER_ID)
                  .then(async () => {
                      await this.dodo()
                  })
                done() //关闭
            } else if(action === 'cancel') {
              done() //关闭
            }

                
            
  }
    
  },
  created() {

  },
  mounted(){
    this.$nextTick(() => {
      this.init()
    })
  }
}
</script>

<style lang="less" scoped>
#userCenter {
  //   /deep/ .van-nav-bar__left {
  //   left: 0;
  //   bottom: 15px;
  // } 
  
  position: fixed;
  left: 0;
  right: 0;
  top: 0;
  bottom: 0;
  background-color: #f5f5f5;
  z-index: 999;
  .icon {
    display: flex;
    height: 3rem;
    width: 100%;
    margin-top: 3rem;
    padding: 0 16px;
    background-color: #ffffff;
    align-items: center;
    .title {
      height: 3rem;
      line-height: 3rem;
      color: #323233;
      font-size: 14px;
    }
    img {
      position: absolute;
      right: 1.6rem;
      height: 2.5rem;
      width: 2.5rem;
    }
  }
  /*转场动画*/
  .router-slider-enter-active,
  .router-slider-leave-active {
    transition: all 0.3s;
  }

  .router-slider-enter,
  .router-slider-leave-active {
    transform: translate3d(2rem, 0, 0);
    opacity: 0;
  }
}
</style>
