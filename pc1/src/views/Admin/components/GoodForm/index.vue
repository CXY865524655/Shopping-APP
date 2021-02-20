<template>
    <div class="goodForm">
        <div class='content'>
            <el-form 
                :label-position="labelPosition" 
                label-width="80px" 
                :model="goodDetail"  
                ref="goodDetail"
                :rules="rules"
                >

                <el-form-item label="商品 ID"  v-show="goodData">
                    <el-input v-model.number="goodDetail.goodsid" readOnly></el-input>
                </el-form-item>

                <el-form-item label="商品图片" prop='previewImage'>
                    <el-upload
                        ref='upload'
                        :multiple="true"
                        action="string"
                        :file-list="goodDetail.previewImage"
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
                    <!-- <el-input v-model="goodDetail.image"></el-input> -->
                </el-form-item>

                <el-form-item label="商品详情" prop='previewDetail'>
                    <el-upload
                        ref='uploadDetail'
                        :multiple="true"
                        action="string"
                        :file-list="goodDetail.previewDetail"
                        list-type="picture-card"
                        :on-preview="handleDetailPreview"
                        :on-exceed="handleExceedDetail"
                        :on-change="onChangeDetail"
                        :limit="limitDetail"
                        :before-upload="beforeuploadDetail"
                        :before-remove="beforeRemoveDetail"
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
                    <!-- <el-input v-model="goodDetail.image"></el-input> -->
                </el-form-item>

                <el-form-item label="商品名称" prop='goodsname'>
                    <el-input v-model="goodDetail.goodsname"></el-input>
                </el-form-item>

                <el-form-item label="商品类型" prop='goodType'>
                    <el-cascader
                        v-model="goodDetail.goodType"
                        :options="options"
                        @change="handleChange"></el-cascader>
                </el-form-item>
                
                <el-form-item label="商品描述" prop='describes'>
                    <el-input v-model="goodDetail.describes"></el-input>
                </el-form-item>

                <el-form-item label="售价" prop='mallprice'>
                    <el-input v-model.number="goodDetail.mallprice"></el-input>
                </el-form-item>

                <el-form-item label="原价" prop='price'>
                    <el-input v-model.number="goodDetail.price"></el-input>
                </el-form-item>

                <el-form-item label="销量" prop='sales'>
                    <el-input v-model.number="goodDetail.sales"></el-input>
                </el-form-item>

                <el-form-item label="库存" prop='stock'>
                    <el-input v-model.number="goodDetail.stock"></el-input>
                </el-form-item>

                <el-form-item>
                    <el-button type="primary" @click="save" v-show="goodData?true:false">保存</el-button>
                    <el-button type="primary" @click="add" v-show="goodData?false:true">新增</el-button>
                    <el-button @click="resetForm()">重置</el-button>
                </el-form-item>

            </el-form>
        </div>
    </div>
</template>


<script>
import Vue from 'vue'
  export default {
    name:'GoodForm',
    props:{
        goodData: Object
    },
    watch:{
        
    },
    computed:{
       
    },
    data() {
        var validateImage = (rule, value, callback) => {
            
            if (this.tmpImg.length<=0) {
                callback(new Error('选择图片'))
            } else {
                callback()
            }
        }

        var validateType = (rule, value, callback) => {
            
            if (this.goodDetail.goodType.length !== 2) {
                callback(new Error('请选择具体类型'))
            } else {
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
        goodDetail: {
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

        options:[{
            value:'1',
            label:'新鲜水果',
            children:[
                {
                    value:'1',
                    label:'热带水果'
                },{
                    value:'2',
                    label:'柑橘橙柚'
                },{
                    value:'3',
                    label:'苹果梨蕉'
                },{
                    value:'4',
                    label:'葡提浆果'
                },
            ]
        },
        {
            value:'2',
            label:'酒水饮料',
            children:[
                {
                    value:'1',
                    label:'饮用水'
                },{
                    value:'2',
                    label:'碳酸饮料'
                },{
                    value:'3',
                    label:'啤酒'
                },{
                    value:'4',
                    label:'葡萄酒'
                },{
                    value:'5',
                    label:'白酒'
                }
            ]
        },{
            value:'3',
            label:'休闲零食',
            children:[
                {
                    value:'1',
                    label:'坚果'
                },{
                    value:'2',
                    label:'肉干小食'
                },{
                    value:'3',
                    label:'膨化食品'
                },{
                    value:'4',
                    label:'饼干糕点'
                }
            ]
        },{
            value:'4',
            label:'海鲜水产',
            children:[
                {
                    value:'1',
                    label:'活鱼活虾'
                },{
                    value:'2',
                    label:'活蟹'
                },{
                    value:'3',
                    label:'活贝'
                },{
                    value:'4',
                    label:'冷冻海鲜'
                }
            ]
        },{
            value:'5',
            label:'新鲜蔬菜',
            children:[
                {
                    value:'1',
                    label:'有机蔬菜'
                },{
                    value:'2',
                    label:'叶菜类'
                },{
                    value:'3',
                    label:'根茎类'
                },{
                    value:'4',
                    label:'瓜果类'
                }
            ]
        },{
            value:'6',
            label:'乳品烘培',
            children:[
                {
                    value:'1',
                    label:'鲜奶'
                },{
                    value:'2',
                    label:'酸奶'
                },{
                    value:'3',
                    label:'面包烘培'
                }
            ]
        },{
            value:'7',
            label:'生活用品',
            children:[
                {
                    value:'1',
                    label:'纸类'
                },{
                    value:'2',
                    label:'保鲜袋'
                },{
                    value:'3',
                    label:'清洁用品'
                }
            ]
        },
            
        ],

        rules: {
          goodsname: [
            { required: true, message: '请输入商品名称', trigger: 'blur' },
            // { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
          ],
          describes: [

          ],
          previewImage: [
            { validator:validateImage,required: true, trigger: 'blur' }
          ],
          goodType: [
            { validator:validateType,required: true, trigger: 'blur' }
          ],
          price: [
            { type: 'number',required: true, message: '请输入商品 price', trigger: 'blur' }
          ],
          mallprice: [
            {  type: 'number',required: true, message: '请输入商品 mallPrice', trigger: 'blur' }
          ],
          sales: [
            { type: 'number',required: true, message: '请输入商品销售量', trigger: 'blur' }
          ],
          stock: [
            { type: 'number',required: true, message: '请输入商品库存', trigger: 'blur' }
          ]
        }


      }
    },
    methods: {
        handleChange(value) {
            // this.goodDetail.goodType = value
        },


        onChange(file, fileList) {
            this.tmpImg.push(file)
            this.goodDetail.previewImage.push(file)
         },
         onChangeDetail(file, fileList) {
            this.tmpImgDetail.push(file)
            this.goodDetail.previewDetail.push(file)
         },


        beforeupload(file) {
            this.uploadForm.append('file',file)
            return false
            // const isJPG = file.type === 'image/jpeg';
            // const isLt2M = file.size / 1024 / 1024 < 2;

            // if (!isJPG) {
            // this.$message.error('上传头像图片只能是 JPG 格式!');
            // }
            // if (!isLt2M) {
            // this.$message.error('上传头像图片大小不能超过 2MB!');
            // }
            // return isJPG && isLt2M;
      },

      beforeuploadDetail(file) {
            this.uploadFormDetail.append('file',file)
            return false
            // const isJPG = file.type === 'image/jpeg';
            // const isLt2M = file.size / 1024 / 1024 < 2;

            // if (!isJPG) {
            // this.$message.error('上传头像图片只能是 JPG 格式!');
            // }
            // if (!isLt2M) {
            // this.$message.error('上传头像图片大小不能超过 2MB!');
            // }
            // return isJPG && isLt2M;
      },



    handleExceed(file, fileList) {
        this.$toast('最多上传'+this.limit+"张图片")
    },

    handleExceedDetail(file, fileList) {
        this.$toast('最多上传'+this.limitDetail+"张图片")
    },

    beforeRemove(file, fileList) {
            if(this.isRemove) {
                this.$api.goods.delGoodImgPath(file.path,this.goodDetail.goodsid)
                .then(res => {
                    this.goodDetail.previewImage = this.goodDetail.previewImage.filter((item,index) => {
                        if(item.uid != file.uid) {
                            return item
                        }
                    })
                    let name=file.name
                    this.tmpImg = this.tmpImg.filter((item,index) => {
                        if(item.name != name) {
                            return item
                        }
                    })
                })
            }
            return false
      },


      beforeRemoveDetail(file, fileList) {
            if(this.isRemoveDetail) {
                this.$api.goods.delGoodImgPathDetail(file.path,this.goodDetail.goodsid)
                .then(res => {
                    this.goodDetail.previewDetail = this.goodDetail.previewDetail.filter((item,index) => {
                        if(item.uid != file.uid) {
                            return item
                        }
                    })
                    let name = file.name
                    this.tmpImgDetail = this.tmpImgDetail.filter((item,index) => {
                        if(item.name != name) {
                            return item
                        }
                    })   
                })
            }
            return false
      },
    
      handlePictureCardPreview(file) {
        this.dialogImageUrl = file.url;
        this.dialogVisible = true;
      },
      handleDetailPreview(file) {
        this.dialogDetailImageUrl = file.url;
        this.dialogVisibleDetail = true;
      },
      resetForm() {
        this.$refs['goodDetail'].resetFields();
      },
      async init() {
          if(this.goodData) {
              this.goodDetail = JSON.parse(JSON.stringify(this.goodData))
              Vue.set(this.goodDetail, 'previewImage', [])
              Vue.set(this.goodDetail, 'previewDetail', [])
              Vue.set(this.goodDetail, 'goodType', [])
              let dd = []
              dd.push(this.goodDetail.goodsType+'')   // 必须时string
              dd.push(this.goodDetail.subType+'')
              Vue.set(this.goodDetail, 'goodType', dd)

              await this.setGImg()
              await this.setDetailImg()
          }
      },


      async setGImg(){
          let imgs = this.goodDetail.image
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
                                this.goodDetail.previewImage.push({url:aa,name,path})
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
                                    this.goodDetail.previewImage.push({url:aa,name:name1,path})
                                    this.tmpImg.push({url:aa,name:name1,path})
                                })
                    })
              }
      },

      async setDetailImg(){
          let details = this.goodDetail.details
              if(details.indexOf(';') < 0 && details.length >= 1) {
                    let path = details
                    await this.$api.goods.getDetailImg(details)
                            .then(res => {
                                let aa = 'data:image/png;base64,' + btoa(
                                    new Uint8Array(res.data)
                                    .reduce((data, byte) => data + String.fromCharCode(byte), '')
                                )
                                let li=path.lastIndexOf('/')
                                let name = path.substring(li+1)
                                this.goodDetail.previewDetail.push({url:aa,name,path})
                                this.tmpImgDetail.push({url:aa,name,path})
                            })
                            

              }else if(details.indexOf(';') >= 0){
                  let s = details.split(';')
                  s.forEach(async(item,index) => {
                      let path = item
                        await this.$api.goods.getDetailImg(item)
                                .then(res => {
                                    let aa = 'data:image/png;base64,' + btoa(
                                        new Uint8Array(res.data)
                                        .reduce((data, byte) => data + String.fromCharCode(byte), '')
                                    )
                                    let li1=path.lastIndexOf('/')
                                    let name1 = path.substring(li1+1)
                                    this.goodDetail.previewDetail.push({url:aa,name:name1,path})
                                    this.tmpImgDetail.push({url:aa,name:name1,path})
                                })
                    })
              }
      },





      async upload1(){
            this.isRemove = false
            await this.$refs.upload.submit()  
            this.isRemove = true
      },

      async upload2(){
            this.isRemoveDetail = false
            await this.$refs.uploadDetail.submit()  
            this.isRemoveDetail = true
      },
    async aa(){
            await this.upload1()
            await this.upload2()
            let de = this.goodDetail.describes
            if(!de) {
                this.goodDetail.describes = '暂无描述'
            }
            let goodsType = this.goodDetail.goodType[0]
            let subType = this.goodDetail.goodType[1]
            Vue.set(this.goodDetail, 'goodsType', goodsType)
            Vue.set(this.goodDetail, 'subType', subType)
        },
        subM1(){
            this.uploadForm = new FormData()
            this.uploadFormDetail = new FormData()
            
            this.$confirm('是否要进行保存操作?', '提示', {
                            confirmButtonText: '确定',
                            cancelButtonText: '取消',
                            type: 'warning'
                        }).then(async() => {
                            await this.aa()
                            await this.$api.goods.saveGood(this.goodDetail)
                            .then(async()=>{
                                await this.sGoodImg()
                                await this.sDetailImg()
                                this.$router.push({ name: 'GoodList'})
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
                await this.$api.goods.updateGoodImgPath(str, this.goodDetail.goodsid)
            }else {
                await this.$api.goods.updateGoodImgPath("", this.goodDetail.goodsid)
            }
            
        },
    async sDetailImg(){
            let uDetail = this.uploadFormDetail
            if(uDetail.getAll("file").length>0){
                await this.$api.goods.saveDetail(uDetail)
            }

            let strD = ''
            let dsD = this.tmpImgDetail
            if(dsD.length >= 1) {
                dsD.forEach((item,index) => {
                    if(index===0) {
                        strD = strD + item.name
                    }else {
                        strD = strD + ';' + item.name
                    }
                })  
                await this.$api.goods.updateDetailPath(strD, this.goodDetail.goodsid)
            }else {
                await this.$api.goods.updateDetailPath('', this.goodDetail.goodsid)
            }
        },


      async subM() {
            await this.subM1()
      },
      save(){
        this.$refs['goodDetail'].validate((valid) => {
            if (valid) {
                this.subM()
             }
        })
      },

    async su1(){
        await this.$refs.upload.submit()
    },

    async su2(){
        await this.$refs.uploadDetail.submit()  
    },

    async sGoodI(){
        let dataF = this.uploadForm
        if(dataF.getAll("file").length>0){
            await this.$api.goods.saveGoodImg(dataF)
            let str = ''
            let ds = dataF.getAll('file')
            if(ds.length >= 1) {
                ds.forEach((item,index) => {
                    if(index===0) {
                        str = str + item.name
                    }else {
                        str = str + ';' + item.name
                    }
                })
            }
            await this.$api.goods.updateGoodImgPath(str, this.goodDetail.goodsid)
        }
    },

    async sDetailI(){
       let dataF = this.uploadFormDetail
        if(dataF.getAll("file").length>0){
            await this.$api.goods.saveDetail(dataF)
            let str = ''
            let ds = dataF.getAll('file')
            if(ds.length >= 1) {
                ds.forEach((item,index) => {
                    if(index===0) {
                        str = str + item.name
                    }else {
                        str = str + ';' + item.name
                    }
                })
            }
            await this.$api.goods.updateDetailPath(str, this.goodDetail.goodsid)
        } 
    },
      add1() {
        
          this.uploadForm = new FormData()
          this.uploadFormDetail = new FormData()
            this.$confirm('是否要进行新增操作?', '提示', {
                            confirmButtonText: '确定',
                            cancelButtonText: '取消',
                            type: 'warning'
                        }).then(async() => {
                            await this.su1()
                            await this.su2()
                            let de = this.goodDetail.describes
                            if(!de) {
                                this.goodDetail.describes = '暂无描述'
                            }
                            let goodsType = this.goodDetail.goodType[0]
                            let subType = this.goodDetail.goodType[1]
                            Vue.set(this.goodDetail, 'goodsType', goodsType)
                            Vue.set(this.goodDetail, 'subType', subType)
                            
                            await this.$api.goods.addGood(this.goodDetail)
                            .then(res => {
                                Vue.set(this.goodDetail, 'goodsid', res.data.data)
                            })

                            await this.sGoodI()
                            await this.sDetailI()
                            
                            // 设置延迟，不然跳转后不刷新数据
                            setTimeout(() => {
                                this.$router.push({ name: 'GoodList'})
                            }, 200);

                            
                        })
      },
      add(){
          this.$refs['goodDetail'].validate((valid) => {
            if (valid) {
                this.add1()
             } 
        })
      }
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

.goodForm {
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
