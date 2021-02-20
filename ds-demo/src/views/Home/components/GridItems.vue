<template>
    <div class="grids">
       <van-grid  :style="{width:'100%',height: gridHeight}">
            <van-grid-item 
                v-for="(v,i) in list"
                :key="i"
                @click='goToCategory(v.categoryid,1)'
            >
                <!-- <van-image 
                    slot="icon"
                    style="width:12vw;height:12vw; "
                    :src="v.image" 
                /> -->
                <svg-icon 
                    slot="icon"
                    :iconClass="svgs[i]"
                    style="width:12vw;height:12vw; "
                />

                <font
                    slot="text"
                    style="margin-top: 1.5vh; font-size: 12px;"
                >
                    {{ v.categoryname }}
                </font>
            </van-grid-item>
        </van-grid>
    </div>
</template>

<script>
import Vue from 'vue'
import { vuexData } from '@/assets/js/mixin.js'

export default {
    mixins: [ vuexData ],
    data() {
        return {
            images: [
                'https://img.yzcdn.cn/vant/apple-1.jpg',
                'https://img.yzcdn.cn/vant/apple-2.jpg'
            ],
            list:[],
            svgs:[
                'fruit',
                'jiushui',
                'lingshi',
                'haixian',
                'shucai',
                'mianbao',
                'shenghuo'
            ]
        }
    },
    computed:{
        gridHeight(){
            let n1 = this.svgs.length / 4
            let n2 = this.svgs.length % 4
            if(n2 >= 0) {
                n1 = n1 + 1
            }
            return (n1 * 20) + 'vw'
        }
    },
    components: {

    },
    created() {

    },
    mounted() {
        this.$nextTick(()=> {
            this.init()
        })
    },
    methods: {
        init(){
            this.$api.goods.getCategory(8)
            .then(res => {
                this.list = JSON.parse(JSON.stringify(res.data.data))
            })
        },
        goToCategory(category, rightCategory){
            let xz = {}
            Vue.set(xz, 'categoryId', category)
            Vue.set(xz, 'subCategoryId', rightCategory)
            xz = JSON.stringify(xz)
            this.SET_ACTIVE({
                active: 1 
            })
            
            this.$router.push({ name: 'Category', query:{
                xz
            }})
        }
    },
    watch: {
       
    }
}
</script>

<style lang="less" scoped>
.grids {
    
}
// .my-swipe .van-swipe-item {
//   color: #fff;
//   font-size: 20px;
//   line-height: 150px;
//   text-align: center;
//   background-color: #39a9ed;
// }
</style>