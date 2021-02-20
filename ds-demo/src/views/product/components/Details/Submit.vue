<template>
  <div>
      <div class="sku-container">
        <van-sku
          v-model="show"
          :sku="skuData.sku"
          :goods="skuData.goods_info"
          :goods-id="skuData.goods_id"
          :hide-stock="skuData.sku.hide_stock"
          :quota="skuData.quota"
          :quota-used="skuData.quota_used"
          :initial-sku="initialSku"       
          reset-stepper-on-hide
          reset-selected-sku-on-hide
          disable-stepper-input
          :close-on-click-overlay="closeOnClickOverlay" 
          :custom-sku-validator="customSkuValidator"  
          @buy-clicked="onBuyClicked"
          @add-cart="onAddCartClicked"
          :show-add-cart-btn="false"
          :buy-text="buy_text"
        />
        <van-button
          block
          type="primary"
          @click.stop="showBase = true"
        >
          xxxx
        </van-button>
      </div>
  </div>
</template>

<script> 
import skuData from './js/data.js'; 
export default { 
  name: 'Submit',
  props: {
      isShow: Boolean
  },
  data() {
    return {
      buy_text: '确定',
      skuData:skuData,
      show: false,    // '' 的话貌似默认为true
      showCustom: false,
      showStepper: false,
      showSoldout: false,
      closeOnClickOverlay: true,
      initialSku: {
        s1: '30349',
        s2: '1193',
        selectedNum: 3
      },
      customSkuValidator: () => '请选择xxx!', 
    };
  },

  methods: {
    onBuyClicked(data) {
    },

    onAddCartClicked(data) {
    }, 
  },
  watch: {
      isShow(newVal, oldVal) {
          this.show = newVal
      },

      show(newVal, oldVal) {
          this.$emit('update:isShow', newVal)
      }
  }
}
</script>

<style lang="less" scoped>
.demo-sku {
  .sku-container {
    padding: 0 15px;
  }
} 
</style>
