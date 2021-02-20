<template>
  <div id="myAddress">
    <!--导航栏-->
    <van-nav-bar title="收货地址"
                 left-arrow
                 :fixed=true
                 :border=true
                 @click-left="onClickLeft"
    />
    <!-- <div style="width:100%; height:6.5vh;"></div> -->
    
    <!-- 没有数据 -->
    <div class="noDataPlaceHold"
         v-show="GET_ADDRESS_LIST.length<=0">
      <img src="@/assets/images/order/noAddress2.png"
           alt=""
           style="width:30vw;height:15vh;">
      <span class="desc" style="font-size:15px;margin-top:1vh;">还没有添加地址呢</span>
    </div>
    
      <van-address-list v-model="selectId"
                        :list="GET_ADDRESS_LIST"
                        style="margin-top: 3rem"
                        @add="onAdd"
                        @edit="onEdit"
                        @select="onBackAddress"
                        show-set-default
                        add-button-text="添加地址"
                        default-tag-text="默认"
                        :switchable='true'
                        >
      </van-address-list>
    <!-- 路由出口 -->
    <transition name="router-slider"
                mode="out-in">
      <router-view></router-view>
    </transition>
  </div>
</template>
<script type="text/javascript">
import { mapState, mapGetters } from 'vuex'
export default {
  name: 'MyAddress',
  created() {
  },
  mounted(){

  },
  data () {
    return {
      isEmpty: true,
      list: [],
      // 不能送到的地方（暂时不用）
      disabledList: []
    }
  },
  created(){
  },
  computed: {
    ...mapState(['addressList']),
    ...mapGetters(['GET_ADDRESS_LIST']),
    selectId:{
      get(){
        return this.GET_ADDRESS_LIST.length <= 0? '':this.GET_ADDRESS_LIST[0]['id']
      },set(){

      }
    }
  },
  components: {

  },
  methods: {
    async initAddress() {
    },
    onClickLeft () {
      this.$router.back();
    },
    onAdd () {
      this.$router.push({ name: 'AddAddress', params: { editorType: '新增地址'} });
    },
    onEdit (item) {
      this.$router.push({ name: 'AddressEditor', params: { editorType: '编辑地址', address: JSON.stringify(item)} });
    },
    onBackAddress (item) {
      if(this.$route.query && this.$route.query.isChooseAddress){
          this.$router.push({
              name: 'Order',
              query: {
                  selectedAddress: JSON.stringify(item)
              }
          })
      }
    }
  }
}
</script>

<style lang="less" scoped>
#myAddress {

  position: fixed;
  left: 0;
  top: 0;
  right: 0;
  bottom: 0;
  background-color: #ffffff;
  z-index: 99999;  // 设置堆叠顺序

  // 默认加了 position: fixed; 无法滑动 ，增加下面2行代码可以解决
    -webkit-overflow-scrolling: touch;
    overflow-y: scroll;
    // overflow-y: auto;
    
  .noDataPlaceHold {
    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    img {
      width: 40%;
      height: 20%;
    }
    .desc {
      color: grey;
      font-size: 0.6rem;
    }
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
  .van-address-list__add {
    bottom: 1rem;
  }
  .van-button--large {
    left: 8%;
    width: 85%;
    // height: 8%;
    font-size: 14px;
    border-radius: 2rem;
    // background-color: #45c763;
    border: none;
  }
}

// 隐藏 scroll 滚动条
#myAddress::-webkit-scrollbar {
      display: none  
}


</style>
