<template>
  <div class="mine">
    <van-cell-group style="">
        <van-cell style="background: #7D7DFF; color:#FFF;height:140px;padding-top:4vh; "
          label-class="labelClass"
          center
        >
        <template slot="title">
          <!-- 已登录状态 -->
          <div class="personDiv">
                 <fieldset
                    class="personMsg"
                    v-if="$store.state.token"
                    @click.stop="goToPage('UserCenter')"
                >
                  <legend>
                    <div class="icon">
                      <van-image class="iconImage"
                        round
                        v-if="GET_USER_IMG"
                        :src="GET_USER_IMG"
                        alt=""/>

                        <van-image class="iconImage"
                        round
                        v-else
                        src="https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3200149520,426458188&fm=26&gp=0.jpg"       
                        alt=""/>
                    </div>
                  </legend>
              <div class="personInfo"
                  v-if="$store.state.token">
                <div class="personInfoName">{{$store.state.user.username}}</div> 
              </div>
            </fieldset>
          </div>
          
          <!-- 未登录状态 -->
          <div class="personDiv">
            <fieldset
              class="personMsg"
              v-if="!$store.state.token"
              @click.stop="login"
            >
            <legend>
              <div class="icon">
                <img class="iconImage"
                 src="https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3200149520,426458188&fm=26&gp=0.jpg"
                 alt=""
                 @click.stop="login">
              </div>
            </legend>
            
            <div class="personInfo"
                 v-if="!$store.state.token">
              <div @click.stop="login">
                立即登录
              </div>
            </div>
            </fieldset>
          </div>
        </template>
      </van-cell>
    </van-cell-group>
    <!-- 订单相关-->
    <van-cell-group>
      <van-cell title="我的订单"
                value=""
                is-link
                @click.stop="goTomyOrder(-1)">
                <template slot="icon">
                            <svg-icon 
                      slot="icon"
                      iconClass="myOrder"
                      style="width:5.5vw;height:5.5vw;margin-left: 0.5vw;margin-right:3.5vw;margin-top:0.3vh; "
                  />
                  </template>
                  
      </van-cell>
      <van-grid :border="false">
        <van-grid-item 
                       v-for="(status,index) in orderStatus"
                       :key="index"
                       :icon="status.icon"
                       :text="status.text"
                       @click.stop="goTomyOrder(index-1)" >
                       
                       </van-grid-item>
        
      </van-grid>
    </van-cell-group>
    <van-cell-group style="margin-top:0.4rem">
      <van-cell title="我的收货地址"
                is-link
                @click.stop="goToPage('MyAddress')" 
        >
                <template slot="icon">
                    <svg-icon 
                      slot="icon"
                      iconClass="address666"
                      style="width:7vw;height:7vw;margin-right:3vw;margin-top:-0.5vh; "
                  />
                  </template>
      </van-cell>
      
      
    </van-cell-group>

    <van-cell-group style="margin-top:0.4rem">
      <van-cell is-link
                icon="vip-card"
                @click.stop="goToPage('Collects')">
        <template slot="title">
          <span>我的收藏</span>
          
        </template>
        <template slot="icon">
                    <svg-icon 
                      slot="icon"
                      iconClass="collect666"
                      style="width:7vw;height:7vw;margin-right:3vw;margin-top:-0.5vh; "
                  />
                  </template>
      </van-cell>
    </van-cell-group>

    <!--路由的出口-->
    <transition name="router-slider"
                mode="out-in">
      <router-view></router-view>
    </transition>
  </div>
</template>

<script type="text/javascript">
// 引入vuex
import { mapState } from 'vuex'
import { vuexData } from '@/assets/js/mixin.js'

export default {
  mixins: [ vuexData ],

  created(){
  },
  data () {
    return {

      // 订单状态
      orderStatus: [
        { icon: 'home-o', text: '全部', val: -1 },
        { icon: 'pending-payment', text: '待付款', val: 0 },
        { icon: 'point-gift-o', text: '待收货', val: 1 },
        { icon: 'chat-o', text: '待评价', val: 2 },
      ],
    }
  },
  computed: {
    ...mapState(['userInfo']),
  },
  methods: {
    // 跳转到我的订单
    goTomyOrder (index) {
      if (index !== 3) {
        return this.$router.push({ name: "OrderStatus", params: { active: index + 1 } });
      }
    },
    goToPage (name) {
      this.$router.push({ name });
    },
    login() {
      this.$router.push({ name: 'Login'})
    }
  }
}
</script>

<style lang="less" scoped>
.mine {
  width: 100%;
  margin-bottom: 3rem;
  
  .version {
    margin: 0 auto;
    text-align: center;
    font-size: 0.6rem;
    height: 2rem;
    color: grey;
    line-height: 2rem;
  }
  .van-nav-bar {
    background-color: #3bba63;
    font-size: 0.6rem;
  }
  .van-nav-bar__title {
    color: white;
  }
  .personDiv {
    position: relative;
    width: 100%;
    
    text-align: center;
    padding: auto;
    .personMsg {
      margin:0 auto;   // 内部DIV居中
      width:95%;  
      height: 100px;
      color: white;
      
      .sex {
          position: absolute;
          top: 3.5rem;
          left: 3.8rem;
          width: 0.1rem;
          height: 0.1rem;
          img {
              width: 1rem;
              height: 1rem;
          }
      }
      .icon{
        .iconImage {  
          width: 4rem;
          height: 4rem;
          border-radius: 50%;
        }
      }
      

      .personInfo {
        
        width: 100%;
        text-align: center;
          display: flex;
          flex-direction: column;
          // margin-left: 0.8rem;
          .personInfoName {
            width: 100%;
            font-size: 1.1rem;
            font-weight: normal;
            text-align: center;
          }
      }
  }
  }
  

  .van-cell__left-icon {
    font-size: 1.2rem;
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
