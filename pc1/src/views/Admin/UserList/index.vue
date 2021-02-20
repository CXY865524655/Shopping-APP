<template>
    <div class="userList">
            <el-card class="operate-container" shadow="never" style="margin-bottom:15px;margin-top:-10px;">
                <svg-icon iconClass="datalist"
                    style="width:0.5rem;height:0.5rem;float:left;margin-right:10px;" />
                <span style="font-size:16px;float:left;margin-top: 8px;">用户列表</span>
                
            </el-card>
            <el-input placeholder="请输入用户名" v-model="searchContent" style="width:15vw; margin-right:1vw;"/>
            <el-button type="primary" @click="searchUsers">
                查询
                </el-button>
            <el-table
                :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
                style="width: 100%"
                height="600"
                >
                <el-table-column
                label="用户 ID"
                prop="id"
                fixed
                sortable
                align="center"
                width="100">
                </el-table-column>
                
                <el-table-column
                label="头像"
                prop="imageShow"
                align="center"
                sortable
                width="150">
                    <template slot-scope="scope">
                        <el-image
                            v-if="scope.row.imageShow"
                            style="width: 80px; height: 80px;border-radius:3px;"
                            :src="scope.row.imageShow">
                        </el-image>
                    </template>
                </el-table-column>

                <el-table-column
                label="用户名称"
                prop="username"
                sortable
                align="center"
                width="150">
                </el-table-column>
                <!-- <el-table-column
                label="登录密码"
                prop="password"
                align="center"
                width="150">
                </el-table-column>

                <el-table-column
                label="支付密码"
                prop="payPWD"
                align="center"
                width="150"
                >
                </el-table-column> -->

                <el-table-column
                label="电话"
                prop="telephone"
                align="center"
                width="150">
                </el-table-column>

                <el-table-column
                label="性别"
                prop="sex"
                align="center"
                width="150">
                    <template slot-scope="scope">
                        {{scope.row.sex=='1'? '男': '女'}}
                    </template>
                </el-table-column>

                

                <!-- <el-table-column
                label="库存"
                prop="stock"
                align="center">
                </el-table-column> -->

            

                <el-table-column
                label="生日"
                prop="birthday"
                sortable
                align="center"
                width="150">
                </el-table-column>

                <el-table-column
                align="center"
                label="操作"
                prop="doing"
                fixed="right"
                width="150">
                <template slot-scope="scope">
                    <el-button
                    size="mini"
                    @click="goToEdit(scope.$index, scope.row)">编辑</el-button>
                    <el-button
                    size="mini"
                    type="danger"
                    @click="goToDelete(scope.$index, scope.row)">删除</el-button>
                </template>
                </el-table-column>
            </el-table>

            <!-- <transition name="fade">
                <router-view id="content"></router-view>
            </transition> -->
    </div>
</template>


<script>
import Vue from 'vue'
  export default {
    data() {
      return {
        tableData: [],
        search: '',
        searchContent:''
      }
    },  
    methods: {
        getGoodImg(path){
            this.$api.goods.getGoodImg(path)
            .then(res => {
                return 'data:image/png;base64,' + btoa(
                    new Uint8Array(res.data)
                    .reduce((data, byte) => data + String.fromCharCode(byte), '')
                )
            })
            .then(data => {
                return data
            })
        },

        async searchUsers(){
            await this.$api.user.searchUsers(this.searchContent)
            .then((res) => {
                this.tableData = JSON.parse(JSON.stringify(res.data.data))
                if(this.tableData&&this.tableData.length>=1) {
                    this.tableData.forEach((item,index)=>{
                        Vue.set(item, 'imageShow', '')
                        let imgPath = item.image_url
                        let i = imgPath.indexOf(';')
                        if(i >= 0) {
                            // 多个
                            let paths = imgPath.split(';')
                            let p = paths[0]
                            this.$api.user.getUserImg(p)
                                .then(res => {
                                    return 'data:image/png;base64,' + btoa(
                                        new Uint8Array(res.data)
                                        .reduce((data, byte) => data + String.fromCharCode(byte), '')
                                    )
                                })
                                .then(data => {
                                    Vue.set(item, 'imageShow', data)
                                })

                        }else if (imgPath.length>=1) {
                            // 一个
                            this.$api.user.getUserImg(imgPath)
                                .then(res => {
                                    
                                    return 'data:image/png;base64,' + btoa(
                                        new Uint8Array(res.data)
                                        .reduce((data, byte) => data + String.fromCharCode(byte), '')
                                    )
                                })
                                .then(data => {
                                    Vue.set(item, 'imageShow', data)
                                })
                            
                        }
                    })
                }
            })
        },
         async init(){
            await this.$api.user.initUsers()
            .then((res) => {
                this.tableData = JSON.parse(JSON.stringify(res.data.data))
                console.log(this.tableData);

                if(this.tableData&&this.tableData.length>=1) {
                    this.tableData.forEach((item,index)=>{
                        Vue.set(item, 'imageShow', '')
                        let imgPath = item.image_url
                        let i = imgPath.indexOf(';')
                        if(i >= 0) {
                            // 多个
                            let paths = imgPath.split(';')
                            let p = paths[0]
                            this.$api.user.getUserImg(p)
                                .then(res => {
                                    return 'data:image/png;base64,' + btoa(
                                        new Uint8Array(res.data)
                                        .reduce((data, byte) => data + String.fromCharCode(byte), '')
                                    )
                                })
                                .then(data => {
                                    Vue.set(item, 'imageShow', data)
                                })

                        }else if (imgPath.length>=1) {
                            // 一个
                            this.$api.user.getUserImg(imgPath)
                                .then(res => {
                                    
                                    return 'data:image/png;base64,' + btoa(
                                        new Uint8Array(res.data)
                                        .reduce((data, byte) => data + String.fromCharCode(byte), '')
                                    )
                                })
                                .then(data => {
                                    Vue.set(item, 'imageShow', data)
                                })
                            
                        }
                    })
                }
            })

            

            // await this.$api.goods.initAdminGoods()
            // .then(async res => {

            //     this.tableData = JSON.parse(JSON.stringify(res.data.data))
                
            //     if(this.tableData&&this.tableData.length>=1) {
            //         this.tableData.forEach(async (item,index)=>{
            //             Vue.set(item, 'imageShow', '')
            //             let imgPath = item.image
            //             let i = imgPath.indexOf(';')
            //             if(i >= 0) {
            //                 // 多个
            //                 let paths = imgPath.split(';')
            //                 let p = paths[0]
            //                  this.$api.goods.getGoodImg(p)
            //                     .then(res => {
            //                         return 'data:image/png;base64,' + btoa(
            //                             new Uint8Array(res.data)
            //                             .reduce((data, byte) => data + String.fromCharCode(byte), '')
            //                         )
            //                     })
            //                     .then(data => {
            //                         Vue.set(item, 'imageShow', data)
            //                     })

            //             }else if (imgPath.length>=1) {
            //                 // 一个
            //                  this.$api.goods.getGoodImg(imgPath)
            //                     .then(res => {
            //                         return 'data:image/png;base64,' + btoa(
            //                             new Uint8Array(res.data)
            //                             .reduce((data, byte) => data + String.fromCharCode(byte), '')
            //                         )
            //                     })
            //                     .then(data => {
            //                         Vue.set(item, 'imageShow', data)
            //                     })
                            
            //             }
            //         })
            //     }
            // })
        },
        goToAddGood(){
            this.$router.push({ name: 'GoodAdd' })
        },
      goToEdit(index, row) {
          
          this.$router.push({
            name: 'UserEdit',
            query: {
                index,
                value: JSON.stringify(row)
            }
        })
      },
      goToDelete(index, row) {
        this.$confirm('是否要进行删除操作?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
            this.tableData.splice(index,1)
            this.$api.user.delUser(row.id)
        })
      },
      
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
.home::-webkit-scrollbar {
      display: none  
}
.userList {

    /deep/.el-card__body{
        height: 30px;
    }
}

</style>