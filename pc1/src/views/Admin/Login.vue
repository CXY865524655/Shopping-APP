<template>
    <div style="display:flex;" class="login">
    <img src="@/assets/images/home/loginB.jpg" alt="" srcset="" class="imgss"/>
        <el-form ref="form" :model="form" label-width="80px" style="margin:35vh auto;">
            <el-form-item label="用户名">
                <span style="width:50px;margin-right:0.5vw;">用户名</span><el-input v-model="form.name" style="width: 200px;"></el-input>
            </el-form-item>
            <el-form-item label="密码">
                <span style="width:50px;margin-right:1.0vw;margin-left:0.5vw;margin-right:0.6vw;">密码</span><el-input v-model="form.password" style="width: 200px;"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="login" style="width:100%;z-index:10;">登录</el-button>
            </el-form-item>
            <!-- <el-button type="primary" @click="login" style="width:100%;z-index:10;">登录</el-button> -->
        </el-form>
    </div>
</template>

<script>
  export default {
    data() {
      return {
        form: {
          name: '',
          password:''
        }
      }
    },
    methods: {
      onSubmit() {
      },
      login(){
        if(!this.form.name) {
          this.$toast("用户名不能为空")
          return
        }

        if(!this.form.password) {
          this.$toast("密码不能为空")
          return
        }
          this.$api.user.adminLogin(this.form.name, this.form.password)
          .then(res => {
            if(res.data.status === 'success') {
              localStorage.setItem("token", res.data.data.token)
              this.$toast("登录成功!")
              this.$router.push({ name: 'Home'})
            }else if(res.data.status === 'fail') {
                this.$toast(res.data.data.errMsg)
            }
            
          })
          .catch(err => {
              
          })
      }
    }
  }
</script>

<style lang="less" scoped>
    .login {
        position: fixed;
        right: 0;
        left: 0;
        bottom: 0;
        top: 0;
        .imgss {
          position: absolute;
          width: 100%;
          height: 100%;
          filter: blur(0.5px);
        }
    }
</style>
