<template>
    <div>
        <van-tree-select
            height="55vw"
            :items="items"
            :main-active-index.sync="active"
        >
        <!-- <template slot="content">
            <van-tabs v-model="active2" v-if="active === 0">
                <van-tab title="标签 1">内容 1</van-tab>
                <van-tab title="标签 2">内容 2</van-tab>
                <van-tab title="标签 3">内容 3</van-tab>
                <van-tab title="标签 4">内容 4</van-tab>
            </van-tabs>
            <van-image v-if="active === 1" src="https://img.yzcdn.cn/vant/apple-2.jpg" />
        </template> -->
        </van-tree-select>
    </div>
</template>

<script>
export default {
    name: 'MenuList',
    data() {
        return {
            active: 0,
            active2: 0,
            items: [{ text: '分组 1' }, { text: '分组 2' }]
        }
    }
}
</script>

<style scoped>
    
</style>