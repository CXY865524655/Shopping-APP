<template>
    <div class="userForm">
        <div class='content'>
            <el-form 
                :label-position="labelPosition" 
                label-width="80px" 
                :model="userDetail"  
                ref="userDetail"
                :rules="rules"
                >

                <el-form-item label="用户 ID" >
                    <el-input v-model.number="userDetail.id" readOnly></el-input>
                </el-form-item>

                <el-form-item label="用户头像" prop='previewImage'>
                    <el-upload
                        ref='upload'
                        :multiple="true"
                        action="string"
                        :file-list="userDetail.previewImage"
                        list-type="picture-card"
                        :on-preview="handlePictureCardPreview"
                        :on-exceed="handleExceed"
                        :on-change="onChange"
                        :limit="limit"
                        :before-upload="beforeupload"
                        :before-remove="beforeRemove"
                        :auto-upload="false"
                    >
                    <i class="el-icon-plus"></i>
                    </el-upload>
                    <!-- <img 
                    v-for="(v,index) in previewImage"
                    :key="index"
                    :src="v.url"/> -->
                    <el-dialog :visible.sync="dialogVisible">
                    <img width="100%" :src="dialogImageUrl" alt="">
                    </el-dialog>
                    <!-- <el-input v-model="userDetail.image"></el-input> -->
                </el-form-item>

                <el-form-item label="用户昵称" prop='username'>
                    <el-input v-model="userDetail.username"></el-input>
                </el-form-item>

                <el-form-item label="登录密码" prop='password' style="width: 55vw;" disabled>
                    <el-input v-model="Pword" style="width: 23vw;" :disabled="changePWD"/>
                    <el-checkbox style="margin-left:1vw;" v-model="isChangePWD">修改</el-checkbox>
                </el-form-item>
                
                <el-form-item label="支付密码" prop='payPWD' style="width: 55vw;">
                    <el-input v-model="payPassword" style="width: 23vw;" :disabled="changePayPWD" type="number"/>
                    <el-checkbox style="margin-left:1vw;" v-model="isChangePayPWD">修改</el-checkbox>
                </el-form-item>

                <el-form-item label="移动电话" prop='telephone'>
                    <el-input v-model="userDetail.telephone"></el-input>
                </el-form-item>

                <el-form-item label="性别" prop='sex'>
                        <el-select v-model="userDetail.sex" placeholder="请选择">
                            <el-option
                            v-for="item in userOption"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                            </el-option>
                        </el-select>
                </el-form-item>


                <el-form-item label="生日" prop='birthday'>

                        <el-date-picker
                            v-model="userDetail.birthday"
                            type="date"
                            placeholder="选择日期"
                            format="yyyy 年 MM 月 dd 日"
                            value-format="yyyy-MM-dd">
                        </el-date-picker>
                    
                </el-form-item>

                <el-form-item>
                    <el-button type="primary" @click="save">保存</el-button>
                    <el-button @click="resetForm()">重置</el-button>
                </el-form-item>

            </el-form>
        </div>
    </div>
</template>


<script>
import Vue from 'vue'
import md5 from 'js-md5'
  export default {
    name:'GoodForm',
    props:{
        userData: Object
    },
    watch:{
        isChangePayPWD(newVal){
            if(newVal){
                this.changePayPWD = false
            }else{
                this.changePayPWD = true
            }
        },
        isChangePWD(newVal){
            if(newVal){
                this.changePWD = false
            }else{
                this.changePWD = true
            }
        },
    },
    computed:{
       
    },
    data() {
        var validateImage = (rule, value, callback) => {
            
            if (this.userDetail.previewImage.length<=0) {
                callback(new Error('选择图片'))
            } else {
                callback()
            }
        }

        var validateType = (rule, value, callback) => {
            
            if (this.userDetail.goodType.length !== 2) {
                callback(new Error('请选择具体类型'))
            } else {
                callback()
            }
        }

        var validateSex = (rule, value, callback) => {
            
            if (!this.userDetail.sex) {
                callback(new Error('请选择性别'))
            } else {
                callback()
            }
        }

        var validateUsername = (rule, value, callback) => {

            // 判断是否改动了 userName
            if(this.oldUserName == this.userDetail.username) {
                callback()
            }else if(this.oldUserName != this.userDetail.username){
            // api 检查 是否重名
                this.$api.user.checkUser(this.userDetail.username)
                .then(res => {
                    if(res.data.status ==  'success') {
                        callback()
                    }else if(res.data.status == 'fail') {
                        callback(new Error('用户名已存在'))
                    }
                })
            }

        }
        

        var validateTel = (rule, value, callback) => {
            
            var telReg=/^[1][3,4,5,7,8][0-9]{9}$/;
            
            if (!this.userDetail.telephone) {
                callback(new Error('请输入手机号码'))
            } else if(!telReg.test(this.userDetail.telephone)){
                callback(new Error('请输入正确的手机号格式'))
            }
            else {
                callback()
            }
        }
        
        
        
      return {
        payPassword:'',
        Pword:'',
        changePayPWD:true,
        changePWD:true,
        isChangePWD:false,
        isChangePayPWD:false,
        oldUserName:'',
         userOption: [{
          value: '1',
          label: '男'
        }, {
          value: '2',
          label: '女'
        },],

        isRemove:true,
        limit:1,
        path:['C:/Users/cxy/Desktop/imgs/home.png','C:/Users/cxy/Desktop/imgs/cat.png'],
        previewImage:[],
        tmpImg:[],
        fileList:[],
        uploadForm: null,
        dialogImageUrl: '',
        dialogVisible: false,
        labelPosition:'right',
        userDetail: {
            id:'',
            username: '',
            image_url: '',
            age: '',
            birthday: '',
            imageShow: '',
            password: '',
            payPWD: '',
            previewImage:[],
            sex:'',
            telephone:'',
        },
        rules: {
          username: [
            { validator:validateUsername,required: true, trigger: 'blur' },
            // { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
          ],
          previewImage: [
            { validator:validateImage,required: true, trigger: 'blur' }
          ],
          
          sex: [
            { validator:validateSex,required: true, message: '请选择性别', trigger: 'blur' }
          ],
          telephone: [
            {  validator:validateTel,required: true,  trigger: 'blur' }
          ],
        //   payPWD: [
        //     { required: true, message: '请输入支付密码', trigger: 'blur' }
        //   ],
        //   password: [
        //     { required: true, message: '请输入登陆密码', trigger: 'blur' }
        //   ],
          birthday: [
            { required: true, message: '请选择生日', trigger: 'blur' }
          ]
        }


      }
    },
    methods: {
        changePassword(){
            if(this.changePWD){
                this.changePWD = false
                console.log(this.changePWD);
            }else{
                this.changePWD = true
                console.log(this.changePWD);
            }
        },
        changePayPassword(){

        },
        handleChange(value) {
            // this.userDetail.goodType = value
        },
        onChange(file, fileList) {
            this.userDetail.previewImage.push(file)
         },
        beforeupload(file) {
            this.uploadForm.append('file',file)
            return false
      },



    handleExceed(file, fileList) {
        this.$toast('最多上传'+this.limit+"张图片")
    },

    beforeRemove(file, fileList) {
                        if(this.isRemove) {
                            
                            this.$api.user.delUserImgPath(this.userDetail.id)
                            .then(res => {
                                
                                this.userDetail.previewImage = this.userDetail.previewImage.filter((item,index) => {
                                    if(item.name !== file.name) {
                                        return item
                                    }
                                })

                                Vue.set(this.userDetail,'image_url','')
                            })
                        }
                        return false
      },
    
      handlePictureCardPreview(file) {
        this.dialogImageUrl = file.url;
        this.dialogVisible = true;
      },
      resetForm() {
        this.$refs['userDetail'].resetFields();
      },
      async init() {
          if(this.userData) {
              this.userDetail = JSON.parse(JSON.stringify(this.userData))
              this.oldUserName = this.userDetail.username
              let imgShow = this.userDetail.imageShow
              let path = this.userDetail.image_url

              Vue.set(this.userDetail, 'previewImage', [])

              if(imgShow) {
                let li=path.lastIndexOf('/')
                let name = path.substring(li+1)
                this.userDetail.previewImage.push({url:imgShow,name,path})
              }
          }
      },
        aa(){
                    this.isRemove = false
                    this.$refs.upload.submit()  
                    this.isRemove = true

                    if(this.Pword && this.Pword!=' '){
                        Vue.set(this.userDetail, 'password',md5(this.Pword))
                    }
                    
                    if(this.payPassword && this.payPassword!=' '){
                        Vue.set(this.userDetail, 'payPWD',md5(this.payPassword))
                    }
        },
        subM1(){
            this.uploadForm = new FormData()
            this.$confirm('是否要进行保存操作?', '提示', {
                            confirmButtonText: '确定',
                            cancelButtonText: '取消',
                            type: 'warning'
                        }).then(async() => {
                            await this.aa()
                            
                            await this.$api.user.saveUser(this.userDetail)
                            .then(async (res)=>{
                                let dataF = this.uploadForm
                                if(dataF.getAll("file").length>0){
                                    await this.$api.user.saveUserImg(dataF)
                                    .then(async ()=>{
                                        let str = ''
                                        let ds = this.userDetail.previewImage
                                        if(ds.length >= 1) {
                                            ds.forEach((item,index) => {
                                                if(index===0) {
                                                    str = str + item.name
                                                }else {
                                                    str = str + ';' + item.name
                                                }
                                            })
                                            
                                        }
                                        await this.$api.user.updateUserImgPath(str, this.userDetail.id)
                                        .then(()=>{
                                            this.$router.push({ name: 'UserList'})
                                        })
                                    })
                                    
                                }
                                this.$router.push({ name: 'UserList'})
                            })
                        })
        },
      async subM() {
            await this.subM1()

      },
      save(){
        this.$refs['userDetail'].validate((valid) => {
            if (valid) {
                this.subM()
            } else {
            
            }
        })
      },
    },
    mounted(){
        this.$nextTick(() => {
            this.init()
        })
    },
    created() {

    }
  }
</script>

<style lang="less" scoped>

.userForm {
    width:100%;
    height:100%;
    display: flex;
    .content {
        width: 40%;
        height: 80%;
        margin:auto;
    }
}

</style>