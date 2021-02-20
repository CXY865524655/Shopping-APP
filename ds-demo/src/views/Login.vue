<template>
    <div class="login"> 
        <div class="login-warpper">
            <img src="@/assets/images/login/jian2.jpg" alt="" srcset="" class="login-img">
            <div class="form">
                <div class="form-cont">
                    <div style="width: 100%;">
                        <span style="float: right; font-size: 13px;" @click.stop="$router.push({ name: 'Register' })">注册</span>
                    </div>   
                    
                    <div v-show="isLoginByAccount">
                        <form ref="loginByAccount">
                            <div class="form-group">
                                <label for="username" class="sr-only">username</label>
                                <input type="text" v-model="login.account.username" class="form-control" maxlength="16" placeholder="请输入用户名" autocomplete="off">
                            </div>
                            <div class="form-group">
                                <label for="password" class="sr-only">password</label>
                                <input type="password" v-model="login.account.password" class="form-control" maxlength="16" placeholder="请输入密码" autocomplete="off">
                            </div>
                            <div  style="width:100%; height: 30px;margin-top: -15px;">
                                <span style="float: left; font-size: 13px ">
                                    
                                    <input type="checkbox" v-model="login.account.remember" id="remenberPass" style="vertical-align:middle;  "/>
                                    <label for="remenberPass" class="col-sm-2 control-label" style="vertical-align:middle;">记住密码</label>
                                </span>
                                <!-- <span style="float: right; font-size: 13px " @click.stop="showPopup">忘记密码？</span> -->
                            </div>
                        
                            <div class="form-group verify" :style="`display: ${login.account.showCode ? '' : 'none'};`">
                                
                                <input type="text" placeholder="请输入验证码" v-model="login.account.code"  class="form-control verify-input" style="width: 150px;" maxlength="4"  autocomplete="off">
                                <div class="col-sm-10" @click.stop="refreshCode">
                                    <s-identify :identifyCode="identifyCode"></s-identify>
                                </div>
                            </div>
                        </form>
                    </div>

                    <div v-show="isLoginByTel">
                        <form ref="loginByTel">
                            <div class="form-group">
                                <label for="telephone" class="sr-only">TELPHONE</label>
                                <input type="text" v-model="login.telephone.telephone" class="form-control" maxlength="16" placeholder="请输入手机号" autocomplete="off">
                            </div>
                            <div class="form-group verify">
                                <input
                                type="text"
                                placeholder="请输入验证码"
                                v-model="login.telephone.code"
                                class="form-control"
                                maxlength="4"
                                autocomplete="off"
                                style="width: 100%;"
                                />
                                
                                <!-- <van-button  class="col-sm-2 control-label" style="width: 120px; " type="info" @click.stop="sendCode" :disabled="sendCodeDisabled">{{ sendCodeMsg }}</van-button> -->
                                
                            </div>
                            <div class="form-group verify" style="float: right; margin-top: -73px;">
                                    <van-button  class="col-sm-2 control-label" style="width: 140px; height: 40px; border-radius: 30px;" type="info" @click.prevent="sendCode" :disabled="sendCodeDisabled"><div style="margin-top:-4%; margin-left:-2%;">{{ sendCodeMsg }}</div></van-button>
                            </div>
                        </form>
                    </div>
                    <div class="form-group form-group2">
                        <van-button :loading='loginLoding' style="margin-right:10px; width: 100%; border-radius: 20px;" type="primary" @click.stop="logining">登 录</van-button>
                    </div>
                    <!-- <div  style="width:100%; height: 30px;margin-top: -15px;">
                            <span style="float: right; font-size: 13px " @click.stop="showPopup">注册</span>
                            <span style="float: left; font-size: 13px " @click.stop="showPopup">忘记密码？</span>
                    </div> -->
                    <div style="width:100%; height: 30px; text-align: center;" @click.stop="showPopup"><span style="font-size: 13px; " >更多登录方式</span></div>
                    <van-popup
                        v-model="show"
                        round
                        close-icon="close"
                        position="bottom"
                        style=" height: 30%; border-radius: 10px 10px 0px 0px ;text-align: center; color: blue"
                        >
                            <div class="father" v-show="isLoginByAccount" @click.stop="changeToTel">
                                <div class="son" >
                                    短信验证码登录
                                </div>
                            </div>
                            <div class="father" v-show="isLoginByTel" @click.stop="changeToAccount">
                                <div class="son" >
                                    账号密码登录
                                </div>
                            </div>
                            <div style="width: 100%; height: 7%; background-color: #D3D3D3;"></div>
                            <div class="father" @click.stop="closePopup">
                                <div class="son quxiao" >
                                   取消 
                                </div>
                            </div>
                    </van-popup>
                </div>
            </div>
            <Back @back='back' />
        </div>
    </div>
</template>

<script>  
import { vuexData } from '@/assets/js/mixin.js'
import Vue from 'vue'
import Back from '@/components/public/Back.vue'
import SIdentify from '@/components/public/identify.vue'
import md5 from 'js-md5'
const Base64 = require('js-base64').Base64
export default {
  name: 'app',
  mixins: [ vuexData ],
  data() {
    return {
        sendCodeDisabled: false,
        sendCodeMsg: '获取验证码',
        sendCodeTime: 0,
        isLoginByAccount: true,
        isLoginByTel: false,
        show: false,
        username: '',
        password: '',
        loginLoding: false,
        verifyTxt: '',
        login:{
            account: {
                username: '',
                password: '',
                errNum: 0,
                showCode: false,
                code: '',
                remember: ''
            },
            telephone: {
                telephone: '',
                code: ''
            }
        },
        // 验证码
        identifyCodes: "1234567890",
        identifyCode: "",
    }
  },
  components: {
    Back,
    SIdentify
  },

  mounted(){
      // 验证码
      this.identifyCode = "";
      this.makeCode(this.identifyCodes, 4);
  },

  methods: {
      logOut() {
          this.LogOut()
          .then(() => {
              this.$router.push('/login')
          })
      },

      showPopup() {
          this.show = true
      },
      
      logining() {


          // 账户登录账户登录
          if(this.isLoginByAccount && !this.isLoginByTel) {

            // 判空
            if (!this.login.account.username || this.login.account.username == '') {
                this.$toast("请输入用户名")
                return 
            }

            if (!this.login.account.password || this.login.account.password == '') {
                this.$toast("请输入密码")
                return 
            }

            if (this.login.account.showCode && (!this.login.account.code || this.login.account.code == '')) {
                this.$toast("请输入验证码")
                return 
            }

            if (this.login.account.showCode && (this.login.account.code != this.identifyCode) ) {
                this.$toast("验证码错误")
                this.refreshCode()
                return 
            }
            // 启动loading
            this.loginLoding = true

            
            // 传参到后台验证
            let user = {}
            Vue.set(user,'username', this.login.account.username)
            Vue.set(user,'password', md5(this.login.account.password))
            this.Login(user)
            .then((res) => {
                // 密码加密 -- Base64 
                let passwordBase64 = Base64.encode(this.login.account.password)
                // 保存上一次登录的用户信息
                let lastLoginUser = {}
                Vue.set(lastLoginUser,'id', res.data.data.user_Info.id)
                Vue.set(lastLoginUser,'username', res.data.data.user_Info.username)
                Vue.set(lastLoginUser,'password', passwordBase64)
                Vue.set(lastLoginUser,'remember', this.login.account.remember)

                this.SET_LAST_LOGIN_USER({
                    lastLoginUser
                })
                localStorage.setItem('lastLoginUser',JSON.stringify(lastLoginUser))

                // j
                // this.$cookieStore.setCookie('lastLoginUser', JSON.stringify(lastLoginUser))   

                // 判断是否勾上了 ‘记住密码’
                if(this.login.account.remember) {
                    localStorage.setItem(res.data.data.user_Info.username, JSON.stringify(lastLoginUser))
                    // j
                    // this.$cookieStore.setCookie(res.data.data.user_Info.username, JSON.stringify(lastLoginUser))   
                }else {
                    localStorage.removeItem(this.login.account.username)
                    // j
                    // this.$cookieStore.removeCookie(this.login.account.username)
                }
                this.$toast(res.data.data.success_msg)
                this.$router.push({ name: 'Personal'})
            })
            .catch(() => {
                // 关闭loading
                this.loginLoding = false;
                
                this.login.account.showCode = localStorage.getItem("err" + this.login.account.username) >= 3 ? true : false
                // j
                // this.login.account.showCode = sessionStorage.getItem(this.login.account.username) >= 3 ? true : false

                
                if (this.login.account.showCode) {
                    this.refreshCode()
                }
                // j
                // this.showCode = sessionStorage.getItem(this.login.account.username)
                this.showCode = localStorage.getItem("err" + this.login.account.username)
            })
            return 
          }
          
          // 手机号登录
            if(!this.isLoginByAccount && this.isLoginByTel) {
                // 判空
                if (!this.login.telephone.telephone || this.login.telephone.telephone == '') {
                    this.$toast("请输入手机号")
                    return 
                }

                if (!this.login.telephone.code || this.login.telephone.code == '') {
                    this.$toast("请输入验证码")
                    return 
                }
                let users = {}
                Vue.set(users,'telephone',this.login.telephone.telephone)
                Vue.set(users,'code',this.login.telephone.code)
                Vue.set(users,'relCode',localStorage.getItem('loginCode'))
                
                
                this.Login(users)
                .then(() => {
                    this.$router.push({ name: 'Personal'})
                })
                return
            }
      },
    
      changeToAccount() {
          this.isLoginByTel = false
          this.isLoginByAccount = true
          this.show = false
          Object.assign(this.login, this.$options.data().login)  // 浅拷贝， 恢复data默认值的比较好的方法
      },
      changeToTel() {
          this.isLoginByTel = true
          this.isLoginByAccount = false
          this.show = false
          Object.assign(this.login, this.$options.data().login)
      },
      closePopup() {
          this.show = false
      },

      sendCodeH(msg){
          this.sendCodeTime = 60
                    this.sendCodeDisabled = true
                    this.sendCodeMsg = `${this.sendCodeTime} s后重新获取`
                    this.$toast('验证码: '+ msg)
                    localStorage.setItem('loginCode', msg)
                    // localStorage.setItem(`${this.login.telephone.telephone}`, res.data) // 注意写法
      },
      async sendCode() {
          
          if ( !this.login.telephone.telephone || this.login.telephone.telephone == '') {
                this.$toast("请输入手机号")
                return
            }

            if(this.login.telephone.telephone.length != 11 ||
                !/^[1][3,4,5,7,8][0-9]{9}$/.test(this.login.telephone.telephone)){
                this.$toast('请输入正确的手机号');
                return
            }

            

            await this.$api.user.loginSendCode(this.login.telephone.telephone)
            .then(async res => {
                if ( res.data.status == 'success' ) {
                    await this.$api.user.sendByMail("15602204126", res.data.data)
                    this.sendCodeH(res.data.data)
                }
                if( res.data.status == 'fail') {
                    this.$toast(res.data.data.errMsg)
                    return
                }

            })

            let timeInit = setInterval(() => {
                this.sendCodeTime--
                this.sendCodeMsg = `${this.sendCodeTime}s后重新获取`
                if(this.sendCodeTime <= 0) {
                    this.sendCodeMsg = '获取验证码'
                    this.sendCodeDisabled = false
                    window.clearInterval(timeInit)
                }
            }, 1000)


            
      },
        // 验证码
        randomNum(min, max) {
            return Math.floor(Math.random() * (max - min) + min);
        },
        refreshCode() {
            this.identifyCode = "";
            this.makeCode(this.identifyCodes, 4);
        },
        makeCode(o, l) {
            for (let i = 0; i < l; i++) {
                this.identifyCode += this.identifyCodes[
                this.randomNum(0, this.identifyCodes.length)
                ];
            }
        },

        updateLoginUserInfo(user) {

            // 改动
            if(user) {
                this.login.account.username = user.username
            
                if(user.remember) {
                    let password = Base64.decode(user.password)
                    this.login.account.password = password
                }
                this.login.account.remember = user.remember 
            }
        },
        


  },

  computed: {
      isCheckedAll() {
          return localStorage.getItem('isCheckedPwd');
      }
  },

  created(){
    //   j
    // var a = this.$cookieStore.getCookie('lastLoginUser') ? JSON.parse(this.$cookieStore.getCookie('lastLoginUser')) :''
    // // 改动
    // if(a) {
    //     let lastLoginUser = a
    //     this.updateLoginUserInfo(lastLoginUser)
    // }



    var a = localStorage.getItem('lastLoginUser') ? JSON.parse(localStorage.getItem('lastLoginUser')) :''
    // 改动
    if(a) {
        let lastLoginUser = a
        this.updateLoginUserInfo(lastLoginUser)
    }

  },
  activated(){
    
  },
  watch: {
      // 保证router进来后确定user是否错误次数过多
      'login.account.username' : {
          deep: true,
          handler(newVal) {
            //   j
            //   this.login.account.showCode = sessionStorage.getItem(newVal) >= 3 ? true : false
            //    if(this.$cookieStore.getCookie(newVal)) {
            //     let rememberUser = JSON.parse(this.$cookieStore.getCookie(newVal))
            //     this.updateLoginUserInfo(rememberUser)
            //   }else {
            //     this.login.account.remember = false
            //     this.login.account.password = ''
            //   }


              this.login.account.showCode = localStorage.getItem(newVal) >= 3 ? true : false
              
              if(localStorage.getItem(newVal)) {
                let rememberUser = JSON.parse(localStorage.getItem(newVal))
                this.updateLoginUserInfo(rememberUser)
              }else {
                this.login.account.remember = false
                this.login.account.password = ''
              }
          }
      }
  }
}
</script>

<style lang="less" scoped>
.father{
    width:100%;
    height:46%;
    position: relative;
    border-bottom: 1px solid #D3D3D3;
    .son {
        position: absolute;
        left: 50%;
        top: 50%;
        transform: translateX(-50%) translateY(-50%);
        font-size: 14px;
    }
        
    .quxiao {
        font-size: 15px;
        font-weight: bold;
        color: black;
    }
} 

.login-warpper{
    /deep/ .van-cell--center {
        padding-left: 0;
        border-bottom: 1px solid rgba(0,0,0,.1);
    }
} 
    
.login-warpper {
    /deep/.van-hairline--top-bottom:after{
        border: 0;
    }
} 
.login-warpper {
    /deep/.van-field__control::-webkit-input-placeholder{
        color: #D8D8D8;
        font-size: 15px;
    }  
} 
      
.login{
    height: 100%;
    width: 100%;
    background: #EFEFEF;
    overflow: hidden;
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    z-index: 100;
    .login-warpper{
        z-index: 10;
        .login-img{
            width: 100%;
            height: 100%;
            position: absolute;
        }
            
        .form{
            width: 90%;
            border-radius: 5px;
            margin: 0 auto;
            background: #fff;
            position: absolute;
            top: 17%;
            left: 50%;
            transform: translateX(-50%);
            padding: 7px;
            box-shadow: -2px 3px 23px 1px rgba(0, 0, 0, 0.1);
            .form-cont{
                padding: 8px;
                .login-title{
                    letter-spacing: 1px;
                    font-size: 20px;
                    margin: 0 0 30px 0;
                    color: #000000;
                }
                    
                .form-group{
                     margin-bottom: 30px;
                    .van-button{
                        height: 45px;
                        line-height: 45px;
                        font-size: 14px;
                        padding: 0 15px;
                        width: 100px;
                    }
                        
                    .sr-only{
                        position: absolute;
                        width: 1px;
                        height: 1px;
                        padding: 0;
                        margin: -1px;
                        overflow: hidden;
                        clip: rect(0,0,0,0);
                        border: 0;    
                        font-size: 14px;
                        font-weight: 300;
                        font-family: "Open Sans", Arial, sans-serif!important;
                    }
                        
                    

                    .form-control{
                        font-size: 16px;
                        font-weight: 300;
                        width: 100%;
                        display: block;
                        height: 50px;
                        padding-left: 0;
                        padding-right: 0;
                        border: none;
                        border-bottom: 1px solid rgba(0, 0, 0, 0.1);
                        -webkit-box-shadow: none;
                        -moz-box-shadow: none;
                        -o-box-shadow: none;
                        box-shadow: none;
                        -webkit-border-radius: 0px;
                        -moz-border-radius: 0px;
                        -ms-border-radius: 0px;
                        border-radius: 0px;
                        -moz-transition: all 0.3s ease;
                        -o-transition: all 0.3s ease;
                        -webkit-transition: all 0.3s ease;
                        -ms-transition: all 0.3s ease;
                        transition: all 0.3s ease;
                        font-family: "Open Sans", Arial, sans-serif!important;
                        color: #555;
                        .form-control:focus{
                            border-bottom: 1px solid rgba(0, 0, 0, 0.4) 
                        }
                    }
                        
                    input::-webkit-input-placeholder{
                        color: #D8D8D8;
                        font-size: 15px;
                    }
                        
                    .btn-primary{
                        height: 50px;
                        padding-right: 20px;
                        padding-left: 20px;
                        border: none;
                        background: #33cccc;
                        color: #ffffff;
                        box-shadow: -1px 5px 10px -1px rgba(51, 204, 204, 0.4);
                        display: inline-block;
                        padding: 6px 12px;
                        margin-bottom: 0;
                        font-size: 14px;
                        font-weight: 400;
                        line-height: 1.42857143;
                        text-align: center;
                        white-space: nowrap;
                        vertical-align: middle;
                        display: inline-block;
                        padding: 6px 14px;
                        margin-bottom: 0;
                        font-size: 14px;
                        font-weight: 400;
                        line-height: 1.42857143;
                        text-align: center;
                        white-space: nowrap;
                        vertical-align: middle;
                        -ms-touch-action: manipulation;
                        touch-action: manipulation;
                        cursor: pointer;
                        -webkit-user-select: none;
                        -moz-user-select: none;
                        -ms-user-select: none;
                        user-select: none;
                        background-image: none;
                        border: 1px solid transparent;
                        border-radius: 4px;
                        .btn-primary:active,.btn-primary:focus,.btn-primary:hover{
                            color: #ffffff;
                            background: #47d1d1 !important;
                            outline: none;
                            border-color: #122b40;
                            box-shadow: inset 0 1.5px 2.5px rgba(0,0,0,.125);
                        }
                    }
                        
                    .reg{
                        box-shadow: -1px 5px 10px -1px rgba(255, 0, 128, 0.4);   
                    }
                }
                       
                .verify{
                    display: flex;
                    align-items: center;
                    margin-top: 20px;
                    label{
                        flex: 0 0 20%;
                    }
                    .verify-input{
                        flex: 0 0 57%;
                        margin-right: 5%;
                        height: 40px;
                    }
                        
                    .col-sm-10{
                        flex: 1
                    }
                        
                }
                    
            }
                
        }
           
    }
   
}       
</style>

