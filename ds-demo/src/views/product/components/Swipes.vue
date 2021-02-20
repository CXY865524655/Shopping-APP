<template>
    <div>
        <van-swipe class="my-swipe" :autoplay="3000" indicator-color="white">
            <van-swipe-item
                v-for="(v,i) in image"
                 :key="i"
            >
                <van-image
                 :src="v"
                 style="width: 100%; height: 100%;" />
            </van-swipe-item>
            <!-- <van-swipe-item>
                <img v-lazy="image" />
            </van-swipe-item> -->
        </van-swipe>
    </div>
</template>

<script>
export default {
    props: {
        image: Array
    },
    created(){
      
    }
}
</script>


<style  scoped>
.my-swipe .van-swipe-item {
    /* color: #fff; */
    /* font-size: 20px; */
    /* line-height: 360px; */
    /* text-align: center; */
    background-color: white;
    
}
</style>