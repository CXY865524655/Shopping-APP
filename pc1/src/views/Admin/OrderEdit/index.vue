<template>
    <div class="orderForm">
        <OrderForm :orderData="orderData"></OrderForm>
    </div>
</template>


<script>
import OrderForm from '@/views/Admin/components/OrderForm'
  export default {
    data() {
      return {
        orderData: JSON.parse(this.$route.query.value)
      }
    },
    methods: {
      
    },
    components:{
        OrderForm
    },

    created() {
        
    }
  }
</script>

<style lang="less" scoped>
.orderForm {
    
}
</style>