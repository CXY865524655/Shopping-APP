<template>
    <div class="qiangGou">
        <Header style="width:100%; height: 7vh; background-color: #FFD700;" @finish='finish'></Header>
        <GoodList style="background-color: white; " :list='goodPrice'> </GoodList>
    </div>
</template>

<script>
import Header from './components/Header'
import GoodList from './components/GoodList'

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
        Header,
        GoodList
    },
    created() {

    },
    methods: {
        finish() {
            this.$emit('finish')
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
.qiangGou {
    
}
</style>