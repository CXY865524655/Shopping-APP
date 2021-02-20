<template>
  <div class="login">
    <div class="login-warpper">
      <img src="@/assets/images/login/jian2.jpg" alt srcset class="login-img" />
      <div class="form">
        <div class="form-cont">
            <div style="width:100%; height: 35px;"><p class="login-title">{{ registerTitle }}</p></div>
          
          <div class="form-group" v-show="registerMsg">
            <label for="username" class="sr-only">请输入用户名：</label>
            <input
              type="text"
              v-model="username"
              class="form-control"
              maxlength="16"
              placeholder="请输入用户名"
              autocomplete="off"
            />
          </div>
          <div class="form-group" v-show="registerTel">
            <label for="telephone" class="sr-only">请输入手机号码:</label>
            <input
              type="text"
              v-model="telephone"
              class="form-control"
              maxlength="16"
              placeholder="请输入手机号码"
              autocomplete="off"
            />
          </div>

          <div class="form-group verify" v-show="registerTel">
              <input
              type="text"
              placeholder="请输入验证码"
              v-model="code"
              class="form-control"
              maxlength="4"
              autocomplete="off"
              style="width: 100%;"
            />
            
          </div>
          <div class="form-group verify" style="float: right; margin-top: -80px;" v-show="registerTel">
              
            <van-button  class="col-sm-2 control-label" style="width: 130px; height: 40px; border-radius: 30px;" type="info" @click.stop="sendCode" :disabled="sendCodeDisabled">
                <div style="margin-top:-4%; margin-left:-2%;">{{ sendCodeTime &lt;= 0 ? '获取验证码' : `${sendCodeTime}s后重新获取`}}</div>
            </van-button>
            
          </div>
          
          <div class="form-group" v-show="registerMsg">
            <label for="password" class="sr-only">请输入密码：</label>
            <input
              type="password"
              v-model="password"
              class="form-control"
              maxlength="16"
              placeholder="请输入密码"
              autocomplete="off"
            />
          </div>
          <div class="form-group" v-show="registerMsg">
            <label for="password" class="sr-only">请再次输入密码：</label>
            <input
              type="password"
              v-model="password2"
              class="form-control"
              maxlength="16"
              placeholder="请再次输入密码"
              autocomplete="off"
            />
          </div>
          

          <div class="form-group form-group2">
            <van-button
              :loading="regLoding"
              type="danger"
              @click.stop="next"
              style="width: 100%; border-radius: 30px;"
            >{{ registerBtnTitle ? '注册' : '下一步' }}</van-button>
          </div>
        </div>
      </div>
      <Back @back="back" />
    </div>
  </div>
</template>

<script>
import { vuexData } from '@/assets/js/mixin.js'
import Back from '@/components/public/Back.vue'
import md5 from 'js-md5'
import Vue from 'vue'
export default {
  name: "Login",
  mixins: [vuexData],
  data() {
    return {
      registerBtnTitle: false,
      registerTitle: '手机注册',
      registerTel: true,
      registerMsg: false,
      sendCodeTime: 0,
      telephone: '',
      sendCodeDisabled: false,
      username: '',
      password: '',
      password2: '',
      tip: '',
      timer: 0,
      regLoding: false,
      loginLoding: false,
      code: "",
      sms: "",
      retry: "", // 60秒倒计时
      dataTimer: 10 // 60秒倒计时
    };
  },
  components: {
    Back
  },

  methods: {
    // 更换验证码
    async replaceVerify() {
      this.$refs.eleVerify["src"] = this.Api.getAverify();
    },
    registerCheck() {
      if (!this.username) {
        this.$toast("请输入用户名");
        return;
      }
      if (!this.password) {
        this.$toast("请输入密码");
        return;
      }
      if (!this.password2) {
        this.$toast("请再次输入密码");
        return;
      }

      if (!this.codeMsg) {
        this.$toast("请输入验证码");
        return;
      }
      if (this.password2 !== this.password) {
        this.$toast("两次密码不一致");
        return;
      }

      if (this.codeMsg.length < 4) {
        this.$toast("请输入正确的验证码");
        return;
      }
      // 注册
      this.register();
    },

    async register() {
      //  注册
      try {
        this.regLoding = true
        const { data } = await this.Api.register({
          username: this.username,
          password: this.password,
          telephone: this.telephone,
          codeMsg: this.codeMsg,
        })
        if (data.status == "success") {
          this.$toast(data.data.success_msg);
          this.setName(data.data.userInfo);
          // this.setToken(data.token)
          //  vuex中的数据发生改变时触发localStorage的存储操作
          
          localStorage.setItem('userName', JSON.stringify(data.data.userInfo))
          
          setTimeout(() => {
            this.$router.go(-1);
          }, 1500);
        } else if (data.status == "fail"){
          this.$toast(data.data.errMsg);
          setTimeout(() => {
            this.codeMsg = "";
            // this.replaceVerify();
          }, 600);
        }
        this.regLoding = false;
        
      } catch (error) {

        this.$toast("网络错误");
        this.regLoding = false;
        // this.replaceVerify();
      }
    },
    sendCodeH(msg){
        localStorage.setItem('registerCode', msg)
                      this.$toast(msg)

                      this.sendCodeTime = 60
                      this.sendCodeDisabled = true
                      this.sendCodeMsg = `${this.sendCodeTime}s后重新获取`

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
    // 发送验证码
    sendCode() {
          if(!this.telephone || this.telephone == '') {
              this.$toast('请输入手机号码')
              return
          }

          if(this.telephone.length != 11 || !/^[1][3,4,5,7,8][0-9]{9}$/.test(this.telephone)) { 
              this.$toast("请输入正确的手机号");
              return
          }
          this.$api.user.checkTel(this.telephone)
          .then(res => {
              if (res.data.status == 'success') {
                  this.$api.user.registerSendCode(this.telephone)
                  .then(async res => {
                      await this.$api.user.sendByMail("15602204126", res.data.data)
                      this.sendCodeH(res.data.data)
                  })

              }else if(res.data.status == 'fail') {
                this.$toast(res.data.data.errMsg)
              }
          })
      },
      async next() {
          if ( this.registerTel && !this.registerMsg ) {
                if(!this.telephone || this.telephone == '' ) {
                    this.$toast('请输入手机号码')
                    return
                }

                if (!this.code || this.code == '') {
                  this.$toast('请输入验证码')
                  return
                }
                this.regLoding = true
                await this.$api.user.checkRegisterCode(this.telephone, this.code, localStorage.getItem('registerCode'))
                .then(res => {
                    if (res.data.status == 'success') {
                        this.registerTel = false
                        this.registerMsg = true
                        this.registerBtnTitle = true
                        this.registerTitle = '完善信息'
                        this.regLoding  = false
                        localStorage.removeItem('registerCode')
                    }else if( res.data.status == 'fail') {
                        this.$toast(res.data.data.errMsg)
                        this.regLoding = false
                    }
                })
          }else if( !this.registerTel && this.registerMsg ) {
              
            if(!this.username || this.username == '') {
                this.$toast('请输入用户名')
                return
            }

            if(!this.password || this.password == '') {
                this.$toast('请输入密码')
                return
            }

            if(!this.password2 || this.password2 == '') {
                this.$toast('请再次输入密码')
                return
            }

            if (this.password2 != this.password) {
                this.$toast('两次密码不一致')
                return
            }
            this.regLoding = true
            var checkUserSuccess = false
            await  this.$api.user.checkUser(this.username)
            .then(res => {
                if(res.data.status ==  'success') {
                    checkUserSuccess = true
                }else if(res.data.status == 'fail') {
                    this.$toast(res.data.data.errMsg)
                    checkUserSuccess = false
                }
            })

              var registerSuccess = false
              if(checkUserSuccess) {
                  // 执行数据库插入user信息
                  await this.$api.user.insertUser(this.telephone, this.username, md5(this.password))
                  .then(res => {
                      if(res.data.status == 'success') {
                        registerSuccess = true
                          this.$toast("注册成功！")
                          this.regLoding = false
                          
                          let lastLoginUser = {}
                          Vue.set(lastLoginUser, 'username', this.username)
                          Vue.set(lastLoginUser, 'password', '')
                          Vue.set(lastLoginUser, 'remember', false)
                          this.SET_LAST_LOGIN_USER({
                            lastLoginUser
                          })
                      }else if(res.data.status == 'fail') {
                        registerSuccess = false
                        this.$toast(res.data.data.errMsg)
                      }
                  })
                  .catch((err) => {
                    registerSuccess = false
                    this.$toast(err.data.data.errMsg)
                  })
              }else{
                  this.regLoding = false
              }
              if(registerSuccess) {
                 this.$router.push({ name: 'Login'})
              }
          }
      }
  }
}
</script>

<style lang="less" scoped>
.login-warpper {
  /deep/.van-cell--center {
    padding-left: 0;
    border-bottom: 1px solid rgba(0, 0, 0, 0.1);
  }
} 

.login-warpper {
  /deep/.van-hairline--top-bottom:after {
      border: 0;
    }
} 

.login-warpper {
  /deep/.van-field__control::-webkit-input-placeholder {
        color: #D8D8D8;
        font-size: 15px;
      }
} 

.login {
  height: 100%;
  background: #EFEFEF;
  overflow: hidden;
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  z-index: 100;

  .login-warpper {
    z-index: 10;

    .login-img {
      width: 100%;
      height: 100%;
      position: absolute
    }

    .form {
      width: 90%;
      margin: 0 auto;
      background: #fff;
      position: absolute;
      top: 17%;
      left: 50%;
      transform: translateX(-50%);
      padding: 7px;
      box-shadow: -2px 3px 23px 1px rgba(0, 0, 0, 0.1);

      .form-cont {
        padding: 8px;

        .login-title {
          letter-spacing: 1px;
          font-size: 20px;
          margin: 0 0 30px 0;
          color: #000000;
        }

        .form-group {
          margin-bottom: 30px;

          .van-button {
            height: 45px;
            line-height: 45px;
            font-size: 14px;
            padding: 0 15px;
            width: 100px;
          }

          .sr-only {
            position: absolute;
            width: 1px;
            height: 1px;
            padding: 0;
            margin: -1px;
            overflow: hidden;
            clip: rect(0, 0, 0, 0);
            border: 0;
            font-size: 14px;
            font-weight: 300;
            font-family: 'Open Sans', Arial, sans-serif !important;
          }

          .form-control {
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
            font-family: 'Open Sans', Arial, sans-serif !important;
            color: #555;

            .form-control:focus {
              border-bottom: 1px solid rgba(0, 0, 0, 0.4);
            }
          }

          input::-webkit-input-placeholder {
            color: #D8D8D8;
            font-size: 15px;
          }

          .btn-primary {
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

            .btn-primary:active, .btn-primary:focus, .btn-primary:hover {
              color: #ffffff;
              background: #47d1d1 !important;
              outline: none;
              border-color: #122b40;
              box-shadow: inset 0 1.5px 2.5px rgba(0, 0, 0, 0.125);
            }
          }

          .reg {
            box-shadow: -1px 5px 10px -1px rgba(255, 0, 128, 0.4);
          }
        }

        .verify {
          display: flex;
          align-items: center;
          margin-top: 20px;

          label {
            flex: 0 0 20%;
          }

          .verify-input {
            flex: 0 0 45%;
            margin-right: 5%;
            height: 40px;
          }

          .col-sm-10 {
            flex: 1;
          }
        }
      }
    }
  }
}
</style>

