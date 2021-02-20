<template>
    <div class="goodForm">
        <GoodForm :goodData="goodData"></GoodForm>
    </div>
</template>


<script>
import GoodForm from '@/views/Admin/components/GoodForm'
  export default {
    data() {
      return {
        goodData: JSON.parse(this.$route.query.value)
      }
    },
    methods: {
      
    },
    components:{
        GoodForm
    },

    created() {
        
    }
  }
</script>

<style lang="less" scoped>

.goodForm {
    
}

</style>