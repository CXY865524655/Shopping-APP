<template>
    <div class="goodList">
            <el-card class="operate-container" shadow="never" style="margin-bottom:15px;margin-top:-10px;">
                <svg-icon iconClass="datalist"
                    style="width:0.5rem;height:0.5rem;float:left;margin-right:10px;" />
                <span style="font-size:16px;float:left;margin-top: 8px;">订单列表</span>
                
            </el-card>
            <el-input placeholder="请输入订单编号" v-model="searchContent" style="width:15vw; margin-right:1vw;"/>
            <el-button type="primary" @click="searchOrder">
                查询
            </el-button>
            
            <el-table
                :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
                style="width: 100%"
                height="600"
                >
                <el-table-column
                    label="订单编号"
                    prop="orderID"
                    fixed
                    sortable
                    align="center"
                    width="200">
                </el-table-column>

                <el-table-column
                label="商品图片"
                prop="imageShow"
                sortable
                align="center"
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
                label="商品名称"
                prop="goodName"
                sortable
                align="center"
                width="150">
                </el-table-column>

            <el-table-column
                label="下单数量"
                prop="num"
                sortable
                align="center"
                width="150">
                
                </el-table-column>

                <el-table-column
                    label="合计"
                    prop="mallPrice"
                    sortable
                    align="center"
                    width="150">
                    <template slot-scope="scope">
                        <font style="color:#FF2D2D; font-size: 20px;">￥</font><font style="color:#FF2D2D; font-size: 15px;">{{ (scope.row.mallPrice * scope.row.num)%1===0?(scope.row.mallPrice * scope.row.num)+'.00':((scope.row.mallPrice * scope.row.num)+'').split('.')[1].length===1?(scope.row.mallPrice * scope.row.num)+'0': (scope.row.mallPrice * scope.row.num)}}</font>
                    </template>
                </el-table-column>

                <el-table-column
                label="收货人"
                prop="getGoodPeople"
                sortable
                align="center"
                width="150"
                height="150"
                :show-overflow-tooltip="true"
                >
                </el-table-column>

                <el-table-column
                label="下单时间"
                prop="orderDate"
                sortable
                align="center"
                width="150">
                </el-table-column>

                <el-table-column
                label="收货地址"
                prop="address"
                align="center"
                width="300"
                height="150"
                :show-overflow-tooltip="true"
                >
                </el-table-column>

                 <el-table-column
                label="电话"
                prop="telephone"
                align="center"
                width="150"
                height="150"
                :show-overflow-tooltip="true"
                >
                </el-table-column>

                <el-table-column
                label="订单状态"
                prop="orderStatus"
                align="center"
                sortable
                width="150"
                height="150"
                :show-overflow-tooltip="true"
                >
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
        searchContent:'',
        searchResult:'',
      }
    },  
    methods: {
        goToEdit(index, row) {
          let aa = [] 
          let orderStatus = row.orderStatus
          aa.push(orderStatus)
          Vue.set(row, "orderStatus", aa)  
          Vue.set(row, "totalPrice", row.mallPrice * row.num)  
          
          this.$router.push({
            name: 'OrderEdit',
            query: {
                index,
                value: JSON.stringify(row)
            }
        })
      },
        searchOrder(){
            if(!this.searchContent || this.searchContent==' '){
                this.init()
            }else{
                this.tableData = []
            this.$api.goods.searchOrder(this.searchContent)
            .then(res => {
                this.tableData = JSON.parse(JSON.stringify(res.data.data))
                
                if(this.tableData&&this.tableData.length>=1) {
                    this.tableData.forEach((item,index)=>{
                        Vue.set(item, 'imageShow', '')
                        let imgPath = item.image
                        let i = imgPath.indexOf(';')
                        if(i >= 0) {
                            // 多个
                            let paths = imgPath.split(';')
                            let p = paths[0]
                            this.$api.goods.getGoodImg(p)
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
                            this.$api.goods.getGoodImg(imgPath)
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
            }
            
        },
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
         init(){
            this.$api.goods.initOrder()
            .then(res => {
                this.tableData = JSON.parse(JSON.stringify(res.data.data))
                
                if(this.tableData&&this.tableData.length>=1) {
                    this.tableData.forEach((item)=>{
                        Vue.set(item, 'imageShow', '')
                        let imgPath = item.image
                        let i = imgPath.indexOf(';')
                        if(i >= 0) {
                            // 多个
                            let paths = imgPath.split(';')
                            let p = paths[0]
                            this.$api.goods.getGoodImg(p)
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
                            this.$api.goods.getGoodImg(imgPath)
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
      goToDelete(index, row) {
        this.$confirm('是否要进行删除操作?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
            this.tableData.splice(index,1)
            this.$api.goods.delOrder(row.userid, row.orderID, row.orderStatus, row.goodName)
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
.goodList {
    

    /deep/.el-card__body{
        height: 30px;
    }
}

</style>
