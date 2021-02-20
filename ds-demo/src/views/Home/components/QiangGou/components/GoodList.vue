<template>
    <div class="goodList">
        <ul style="height: 204.06px">
            <li 
            v-for="(i,index) in goodPrice"
            :key="index"
            @click.stop='goToPro(i)'
            style="display:inline-block; margin-left: 4px;">
                <div class="item">
                    <div class="img">
                        <van-image
                            v-if="i.imageShow"
                            radius="5"
                            :src="i.imageShow"
                            style="margin:auto;width:27vw;height:27vw;"
                        />
                    </div>
                    <div class="gname">
                        <font style="width:27vw;height:100%;margin:0 auto; font-size: 13px; overflow: hidden;">
                            {{i.goodsname}}
                        </font>
                    </div>

                    <div class="other">
                        <div style="width:27vw;margin:0 auto; ">
                            <div style="height:100%;width:70%;float:left;">
                                <div style="width:100%;height:50%; display:flex;">
                                    <div style="margin:auto 0; color: #FF00FF;">
                                        <font style="font-size: 5px; margin-right: 3px;">¥</font>
                                        <font style="font-size: 18px;">{{i.mallprice}}</font>
                                    </div>
                                </div>
                                <div style="width:100%;height:50%;">
                                    <div style="text-decoration:line-through; font-size: 8px; color:#999999;">¥{{i.price}}</div>
                                </div>
                            </div>
                            
                                <svg-icon iconClass="qiang"
                                    style="margin: auto;width: 7.5vw; height: 7.5vw; " 
                                />
                        </div>
                    </div>
                </div>
            </li>
            
            
        </ul>

        
    </div>
</template>

<script>
export default {
    props:{
        list: Array
    },
    data() {
        return {
            goodPrice: []
        }
    },
    components: {

    },
    created() {   
    },
    methods: {
        goToPro(i) {
            this.$router.push({ name: 'Product', query:{
                goodInfo: JSON.stringify(i),
                imgs: JSON.stringify(i.imgss),
                detailsShow: JSON.stringify(i.detailsShow)
            }})
        }
    },
    watch: {
       list(newVal) {
           this.goodPrice = newVal
       }
    }
}
</script>

<style lang="less" scoped>
// 隐藏 scroll 滚动条
.goodList::-webkit-scrollbar {
      display: none  
}
.goodList {
    width: 100%;
    height: 100%;
    white-space:nowrap;
    overflow-x: auto;
    
    .item{
        width: 32vw;;    
        height: 100%;
        background-color: white;
        word-break:break-all;
        overflow: hidden;
        
        .img{
            width: 100%;
            height: 18vh;
            background-color: white; 
            display: flex;   
        }
        .gname{
            width: 100%;
            height: 5.5vh;
            margin-top: -5px;
            margin-bottom: 10px;
            background-color: white; 
            display: flex;
            white-space:pre-line;
            line-height: 18px;
        }
        .other {
            width: 100%;
            height: 6.1vh;
            background-color: white; 
            display: flex;
        }
    }
}

</style>