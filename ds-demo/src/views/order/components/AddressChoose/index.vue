<template>
    <div id="address_choose">
        <van-row style="height: 100%; border-radius: 1.0rem;y">
            <van-col span="4" style="height: 100%; display: flex;">
                <div style="width: 12vw;height:12vh;margin: auto; background-color: white; ">
                    <svg-icon class="icon" iconClass="address"></svg-icon>
                </div>
            </van-col>
            <van-col span="17" style="height: 100%;">
                <van-row style="margin-top: 2.0vh;height: 4.0vh;">
                    <van-col span="6" style="height: 100%; display: flex;"><div style="margin: auto 0 0 0; font-size: 1.05rem;">{{selectedAddress?selectedAddress.name:''}}</div></van-col>
                    
                    <!-- color: #DCDCDC; -->
                    <van-col span="18" style="height: 100%; display: flex;"><div style="margin: auto 0 0 0;font-size: 0.95rem; ">{{selectedAddress?selectedAddress.tel:''}}</div></van-col>
                </van-row>
                <van-row style="margin-top: 1.8vh;">
                    <van-col  style="width: 100%;"><div style="margin: auto 0 0 0; font-size: 0.95rem;line-height:20px;">{{selectedAddress?selectedAddress.address:''}}</div></van-col>
                </van-row>
            </van-col>
            <van-col span="3" style="height: 100%; display: flex;">
                <div 
                    style="width: 12vw;height:12vh;margin:auto; background-color: white;"
                    @click.stop='selectAddress'
                >
                    <svg-icon class="arrow" iconClass="arrow" style=""></svg-icon>
                </div>
            </van-col>
        </van-row>
    </div>  
</template>

<script>  
import { vuexData } from '@/assets/js/mixin.js'
export default {
  name: 'AddressChoose',
  mixins: [ vuexData ],
  props: {
      selectedAddress: Object
  },
  data() {
    return {
        chosenAddressId: '1',
        list: [
            {
            id: '1',
            name: '张三',
            tel: '13000000000',
            address: '浙江省杭州市西湖区文三路 138 号东方通信大厦 7 楼 501 室'
            }
        ]
    }
  },
  methods: {
    selectAddress() {
        this.$router.push({ 
            name: 'MyAddress',
            query: {
                isChooseAddress: true
            }
        })
    },
    onAdd() {
      this.$toast('新增地址');
    },
    onEdit(item, index) {
      this.$toast('编辑地址:' + index);
    }  
  },
  components: {
    
  },
  
  created(){
    
  },
  mounted() {
      
  },
  activated(){
    
  },
  watch: {
      
  },
}
</script>

<style lang="less" scoped>
#address_choose {
    width: 100%;
    height: 114px;
    border-radius: 1.0rem;
    background: white;
    margin-top: 7.0vh;
    box-sizing: border-box;  // 防止 父元素设置 padding 后 子元素溢出
}

.icon {
  fill: #999;
  width: 12vw;
  height:12vh;
}

.arrow {
  fill: #999;
  width: 8vw;
  height:100%;
  margin: auto;
  float: right;
}


</style>
