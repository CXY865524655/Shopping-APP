<template>
    <div class="qiangGouHeader">
        <div style="margin:auto 0; margin-left: 5vw; border-left: 5px solid #4169E1; display: flex;">
            <div style="float: left; font-size: 20px; margin-left: 1.5vw;">特价专区</div>
            <div style="float: left; margin:auto 0; margin-left: 4.0vw; ">
                <van-count-down :time="time" millisecond @finish='finish'>
                    <template v-slot="timeData">
                       
                        <span class="item">{{ timeData.hours >= 10 ? timeData.hours : '0' + timeData.hours }} </span>
                        <span class="mao">:</span>
                        <span class="item">{{ timeData.minutes >= 10 ? timeData.minutes: '0' + timeData.minutes }} </span>
                        <span class="mao">:</span>
                        <span class="item">{{ timeData.seconds >= 10 ? timeData.seconds : '0' + timeData.seconds }} </span>
                        <span class="mao">:</span>
                        <span class="item">
                            {{ timeData.milliseconds >= 100 && timeData.milliseconds < 1000 ? 
                              (timeData.milliseconds-timeData.milliseconds%100)/10+(timeData.milliseconds%100-timeData.milliseconds%10)/10 : timeData.milliseconds >= 10 && timeData.milliseconds < 100 ? timeData.milliseconds : timeData.milliseconds < 10 ? '0' + timeData.milliseconds: ''}} 
                        </span>
                    </template>
                </van-count-down>
            </div>
        </div>

        
    </div>
</template>

<script>
export default {
    data() {
        return {
            
        }
    },
    computed: {
        time() {
            var start = new Date(new Date(new Date().toLocaleDateString()).getTime()) // 当天0点
            let now = new Date().getTime() - start
            let remainTime = 86400000 - now
            return remainTime
        }
    },
    components: {

    },
    created() {

    },
    methods: {
        finish() {
            this.$emit('finish')
        }
    },
    watch: {
       
    }
}
</script>

<style lang="less" scoped>
.qiangGouHeader {
    width: 100%;
    height:100%;
    display: flex;

    /deep/ .item{
        border-radius: 2px;
    }

    /deep/ .mao{
        margin-right: 5px;
    }
    
}

.item {
  display: inline-block;
  width: 22px;
  margin-right: 5px;
  color: #fff;
  font-size: 12px;
  text-align: center;
  background-color: green;
}

</style>