<template>
    <div class="content">
        <div class="list">
            <ul>
                <li ref="lis"
                >
                    <van-card
                        price="category.mallprice%1===0?category.mallprice+'.00':category.mallprice.toString().split('.')[1].length===1?category.mallprice+'.0':category.mallprice"
                        origin-price="category.price%1===0?category.price+'.00':category.price.toString().split('.')[1].length===1?category.price+'.0':category.price"
                        desc="category.describes"
                        title="category.goodsname"
                        thumb="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574227536070&di=40b0dae73c365befe4290cacdb0a78e3&imgtype=0&src=http%3A%2F%2Fwww.360changshi.com%2Fuploadfile%2F2015%2F1208%2F20151208073837635.jpg"
                    >
                        <!-- <div slot="tags">
                            <van-tag plain type="danger">标签</van-tag>
                            <van-tag plain type="danger">标签</van-tag>
                        </div>
                        <div slot="footer">
                            <van-button size="mini">按钮</van-button>
                            <van-button size="mini">按钮</van-button>
                        </div> -->
                    </van-card>
                    
                </li>

            </ul>
        </div>
    </div>
</template>

<script>
export default {
    name: 'Content',
    data() {
        return {
            
        }
    }
}
</script>

<style lang="less" scoped>
.content {
    position: fixed;
    // 设置下面四行 才能滑动（否则为自适应，才不会滑动哦~）
    left: 0;
    top: 0;
    right: 0;
    bottom: 0;
    .list {
        width: 100%;
        height: 100%;
        
    }
}
</style>