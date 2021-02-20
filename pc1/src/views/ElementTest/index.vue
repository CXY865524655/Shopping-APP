<template>
    <div class="test">
        <el-container style="height: 100%; border: 1px solid #eee;">
        <el-aside width="210px" height='100%' style="background-color: #304156">
            <el-menu 
                :default-active="this.$router.name"
                router 
                background-color="#304156" 
                text-color="white">
            <el-submenu index="1" :class="{wid: true}" >
                <template slot="title">
                    <svg-icon iconClass="shopping"
                              style="width: 3.0vw; height: 3.0vh;margin-right:5px;margin-left:-10px;" 
                    />商品</template>
                <el-menu-item index="GoodList"><svg-icon iconClass="list"
                              style="width: 2.2vw; height: 2.5vh;margin-right:5px;" 
                    />商品列表</el-menu-item>
                <el-menu-item index="GoodAdd"><svg-icon iconClass="add9"
                              style="width: 2.2vw; height: 2.5vh;margin-right:5px;"
                              
                    />添加商品</el-menu-item>
            </el-submenu>   
            <el-submenu index="2" :class="{wid: true}">
                <template slot="title"><svg-icon iconClass="detail"
                              style="width: 3.0vw; height: 3.0vh;margin-right:5px;margin-left:-10px;" 
                    />订单</template>
                <el-menu-item index="OrderList"><svg-icon iconClass="list"
                              style="width: 2.2vw; height: 2.5vh;margin-right:5px;" 
                    />订单列表</el-menu-item>
                
            </el-submenu>
            <el-submenu index="3" :class="{wid: true}">
                <template slot="title"><svg-icon iconClass="permission"
                              style="width: 3.0vw; height: 3.0vh;margin-right:5px;margin-left:-10px;" 
                    />权限</template>
                <el-menu-item index="UserList"><svg-icon iconClass="list"
                              style="width: 2.2vw; height: 2.5vh;margin-right:5px;" 
                    />用户列表</el-menu-item>
                
            </el-submenu>
            </el-menu>
        </el-aside>
        
        <el-container>
            <!-- <el-header style="text-align: right; font-size: 12px">
            <el-dropdown>
                <i class="el-icon-setting" style="margin-right: 15px"></i>
                <el-dropdown-menu slot="dropdown">
                <el-dropdown-item>查看</el-dropdown-item>
                <el-dropdown-item>新增</el-dropdown-item>
                <el-dropdown-item>删除</el-dropdown-item>
                </el-dropdown-menu>
            </el-dropdown>
            <span>王小虎</span>
            </el-header> -->
            
                <el-main>
                    <transition name="fade">
                        <router-view id="content"></router-view>
                    </transition>

                    <!-- <el-table :data="tableData">
                        <el-table-column prop="date" label="日期" width="140">
                        </el-table-column>
                        <el-table-column prop="name" label="姓名" width="120">
                        </el-table-column>
                        <el-table-column prop="address" label="地址">
                        </el-table-column>
                    </el-table> -->
                </el-main>
            </el-container>
        </el-container>
    </div>
    
</template>


<script>
  export default {
    data() {
      const item = {
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }
      return {
        tableData: Array(20).fill(item)
      }
    },
    methods: {
        goToPage(name) {
            debugger
            this.$router.push({name})
        }
    }
  }
</script>

<style>
.wid {
    width:210px;
    background-color: #1f2d3d;
}
.big{
    width: 3.0vw; 
    height: 3.0vh;
    margin-right:5px;
    margin-left:-5px;
}
    .test {
        position: fixed;
        left: 0;
        right: 0;
        top: 0;
        bottom: 0;
    }
  .el-header {
    background-color: #B3C0D1;
    color: #333;
    line-height: 50px;
  }
  
  .el-aside {
    color: #333;
  }


  #content {
    width: 100%;
    height: 100%;
        position: relative;
}
.van-tabbar-item--active {
    color: transparent;
}
.fade-enter {
    opacity: 0;
}

.fade-leave {
    opacity: 1;
}

.fade-enter-active {
    transition: opacity 0.3s;
}

.fade-leave-active {
    opacity: 0;
    transition: opacity 0s;
}
#view {
    width: 100%;
    height: 100%;
    transition: all 0.5s cubic-bezier(0.55, 0, 0.1, 1);
}
.slide-left-enter,
.slide-right-leave-active {
    opacity: 0;
    transform: translate(100%, 0);
}
.slide-left-leave-active,
.slide-right-enter {
    opacity: 0;
    transform: translate(-100%, 0);
}
.slide-top-enter,
.slide-bottom-leave-active {
    opacity: 0;
    transform: translate(0, 100%);
}
.slide-top-leave-active,
.slide-bottom-enter {
    opacity: 0;
    transform: translate(0, -100%);
}
</style>
