<template>
    <div class="orderForm">
        <div class='content'>
            <el-form 
                :label-position="labelPosition" 
                label-width="80px" 
                :model="orderDetail"  
                ref="orderDetail"
                :rules="rules"
                >

                <el-form-item label="订单编号">
                    <el-input v-model.number="orderDetail.orderID" readOnly></el-input>
                </el-form-item>

                <el-form-item label="商品图片" prop='previewImage'>
                    <el-upload
                        :disabled="true"
                        ref='upload'
                        :multiple="true"
                        action="string"
                        :file-list="orderDetail.previewImage"
                        list-type="picture-card"
                        :on-preview="handlePictureCardPreview"
                        :on-exceed="handleExceed"
                        :limit="limit"
                        :auto-upload="false"
                    >
                    <i class="el-icon-plus"></i>
                    </el-upload>
                    
                    <el-dialog :visible.sync="dialogVisible">
                    <img width="100%" :src="dialogImageUrl" alt="">
                    </el-dialog>
                </el-form-item>

                <el-form-item label="下单时间" prop='orderDate'>
                    <el-input v-model.number="orderDetail.orderDate" readOnly></el-input>
                </el-form-item>
                <el-form-item label="商品名称" prop='goodName'>
                    <el-input v-model="orderDetail.goodName" readOnly></el-input>
                </el-form-item>

                <el-form-item label="订单状态" prop='orderStatus'>
                    <el-input v-model="orderDetail.orderStatus" readOnly></el-input>
                </el-form-item>
                
                <el-form-item label="收货地址" prop='address'>
                    <el-input v-model="orderDetail.address"></el-input>
                </el-form-item>

                <el-form-item label="下单数量" prop='totalPrice'>
                    <el-input v-model.number="orderDetail.num" readOnly></el-input>
                </el-form-item>
                <el-form-item label="合计" prop='totalPrice'>
                    <el-input v-model.number="orderDetail.totalPrice" readOnly></el-input>
                </el-form-item>

                <el-form-item label="收货人" prop='getGoodPeople'>
                    <el-input v-model.number="orderDetail.getGoodPeople"></el-input>
                </el-form-item>

                 <el-form-item label="电话" prop='telephone'>
                    <el-input v-model.number="orderDetail.telephone"></el-input>
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
  export default {
    name:'orderForm',
    props:{
        orderData: Object
    },
    watch:{
        
    },
    computed:{
       
    },
    data() {
        var validateType = (rule, value, callback) => {
            if (!this.orderDetail.orderStatus || 
                this.orderDetail.orderStatus===' '
                ) {
                callback(new Error('请选择具体类型'))
            } else {
                callback()
            }
        }
        var validateTel = (rule, value, callback) => {
            
            var telReg=/^[1][3,4,5,7,8][0-9]{9}$/;
            
            if (!this.orderDetail.telephone) {
                callback(new Error('请输入手机号码'))
            } else if(!telReg.test(this.orderDetail.telephone)){
                callback(new Error('请输入正确的手机号格式'))
            }
            else {
                callback()
            }
        }
        
      return {
        isRemove:true,
        isRemoveDetail:true,

        limit: 3,
        limitDetail: 6,

        path:['C:/Users/cxy/Desktop/imgs/home.png','C:/Users/cxy/Desktop/imgs/cat.png'],

        previewImage:[],
        previewDetail:[],

        tmpImg:[],
        tmpImgDetail:[],

        uploadForm: null,
        uploadFormDetail: null,

        dialogImageUrl: '',
        dialogDetailImageUrl:'',

        dialogVisible: false,
        dialogVisibleDetail: false,

        labelPosition:'right',
        orderDetail: {
            goodsid:'',
            goodsname: '',
            image: '',
            describes: '',
            mallprice: 0,
            price: 0,
            sales: 0,
            stock: 0,
            previewImage:[],
            previewDetail:[],
            goodType:[],
            goodsType:'',
            subType:''
        },

        options:[
            {
                value:'已完成',
                label:'已完成',
            },
            {
                value:'未评价',
                label:'未评价'
            },
        ],

        rules: {
          goodsname: [
            // { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
          ],
          address: [
            { required: true, message: '请输入收货地址', trigger: 'blur' }
          ],
          previewImage: [
          ],
          orderStatus: [
          ],
          price: [      
            { type: 'number',required: true, message: '请输入商品 price', trigger: 'blur' }
          ],
          mallprice: [
            {  type: 'number',required: true, message: '请输入商品 mallPrice', trigger: 'blur' }
          ],
          telephone: [
            {  validator:validateTel,required: true,  trigger: 'blur' }
          ],
          getGoodPeople: [
            { required: true, message: '请输入收货人姓名', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
        handleChange(value) {
            // this.orderDetail.goodType = value
        },


        onChange(file, fileList) {
            this.tmpImg.push(file)
            this.orderDetail.previewImage.push(file)
         },
         onChangeDetail(file, fileList) {
            this.tmpImgDetail.push(file)
            this.orderDetail.previewDetail.push(file)
         },


    handleExceed(file, fileList) {
        this.$toast('最多上传'+this.limit+"张图片")
    },

    handleExceedDetail(file, fileList) {
        this.$toast('最多上传'+this.limitDetail+"张图片")
    },


    
      handlePictureCardPreview(file) {
        this.dialogImageUrl = file.url;
        this.dialogVisible = true;
      },

      resetForm() {
        this.$refs['orderDetail'].resetFields();
      },
      async init() {
          if(this.orderData) {
              this.orderDetail = JSON.parse(JSON.stringify(this.orderData))
              Vue.set(this.orderDetail, 'previewImage', [])
              Vue.set(this.orderDetail, 'goodType', [])

              await this.setGImg()
              await this.setDetailImg()
          }
      },


      async setGImg(){
          let imgs = this.orderDetail.image
              if(imgs.indexOf(';') < 0 && imgs.length >= 1) {
                    let path = imgs
                    await this.$api.goods.getGoodImg(imgs)
                            .then(res => {
                                let aa = 'data:image/png;base64,' + btoa(
                                    new Uint8Array(res.data)
                                    .reduce((data, byte) => data + String.fromCharCode(byte), '')
                                )
                                let li=path.lastIndexOf('/')
                                let name = path.substring(li+1)
                                this.orderDetail.previewImage.push({url:aa,name,path})
                                this.tmpImg.push({url:aa,name,path})
                            })
                            

              }else if(imgs.indexOf(';') >= 0){
                  let s = imgs.split(';')
                  s.forEach(async(item,index) => {
                      let path = item
                        await this.$api.goods.getGoodImg(item)
                                .then(res => {
                                    let aa = 'data:image/png;base64,' + btoa(
                                        new Uint8Array(res.data)
                                        .reduce((data, byte) => data + String.fromCharCode(byte), '')
                                    )
                                    let li1=path.lastIndexOf('/')
                                    let name1 = path.substring(li1+1)
                                    this.orderDetail.previewImage.push({url:aa,name:name1,path})
                                    this.tmpImg.push({url:aa,name:name1,path})
                                })
                    })
              }
      },

        async aa(){
            let a = this.orderDetail.orderStatus[0]
            Vue.set(this.orderDetail, "orderStatus", a)
        },
        subM1(){

            this.$confirm('是否要进行保存操作?', '提示', {
                            confirmButtonText: '确定',
                            cancelButtonText: '取消',
                            type: 'warning'
                        }).then(async() => {
                            await this.aa()
                            await this.$api.goods.saveOrder(this.orderDetail)
                            .then(async()=>{
                                this.$router.push({ name: 'OrderList'})
                            })
                        })
        },

        async sGoodImg(){
            let dataF = this.uploadForm
            if(dataF.getAll("file").length > 0){
                await this.$api.goods.saveGoodImg(dataF)
            }

            let str = ''
            let ds = this.tmpImg
            
            if(ds.length >= 1) {
                ds.forEach((item,index) => {
                    if(index===0) {
                        str = str + item.name
                    }else {
                        str = str + ';' + item.name
                    }
                })
                await this.$api.goods.updateGoodImgPath(str, this.orderDetail.goodsid)
            }else {
                await this.$api.goods.updateGoodImgPath("", this.orderDetail.goodsid)
            }
            
        },

      async subM() {
            await this.subM1()
      },
      save(){
        this.$refs['orderDetail'].validate((valid) => {
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

.orderForm {
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