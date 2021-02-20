<template>
    <div class="qiangGouHeader">
        <div style="margin:auto 0; margin-left: 5vw; border-left: 5px solid #4169E1; display: flex;">
            <div style="float: left; font-size: 20px; margin-left: 1.5vw;">推荐商品</div>
            
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
            // console.log(86400000 - (new Date().getTime() - start))
            let now = new Date().getTime() - start
            let remainTime = 86400000 - now
            // console.log(remainTime)
            // let all = 86400000
            // let now = new Date().getTime()
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
    margin-top: 3vh;
    width: 100%;
    height: 46.7px;
    display: flex;
    background: #FFD700;
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
  background-color: #1989fa;
}

</style>