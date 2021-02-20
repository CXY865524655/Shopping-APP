<template>
    <div class="swipe">
        <van-swipe 
            :autoplay="3000"
            class="my-swipe"
            indicator-color="#39a9ed"
          >
                <van-swipe-item
                    v-for="(item,index) in lis"
                    :key="index"
                    @click.stop="toDetail(item)"
                >
                    <van-image
                        v-show="item.imageShow"
                        style="margin-top: 4vh;width:40%;height:20vh;"
                        :src="item.imageShow"
                    />
                    <div 
                        v-show="!item.imageShow"
                        style="width:100%;height:207.33px;background-color:white;"
                    />
                </van-swipe-item>
        </van-swipe>
    </div>
</template>

<script>
export default {
    props:{
        list: Array
    },
    data() {
        return {
            lis: [],
            images: [
                'https://img.yzcdn.cn/vant/apple-1.jpg',
                'https://img.yzcdn.cn/vant/apple-2.jpg'
            ]
        }
    },
    components: {

    },
    created() {

    },
    methods: {
        toDetail(item){
            this.$router.push({ 
                name: 'Product',
                query: {
                    goodInfo: JSON.stringify(item),
                    imgs: JSON.stringify(item.imgss),
                    detailsShow: JSON.stringify(item.detailsShow)
                }
            })

        },
    },
    watch: {
        list(newV){
            this.lis = newV
        }
    }
}
</script>

<style lang="less" scoped>
.swipe {
    width: 100%;
    height: 207.33px;
    /deep/ .van-swipe__indicators{
        bottom: 18px;
        line-height:100px;
    }

    /deep/.van-swipe-item[data-v-7eb48a3c] {
        line-height:110px;
    }
}
.my-swipe .van-swipe-item {
  color: #fff;
  font-size: 20px;
  line-height: 150px;
  text-align: center;
//   background-color: #39a9ed;
}
</style>
