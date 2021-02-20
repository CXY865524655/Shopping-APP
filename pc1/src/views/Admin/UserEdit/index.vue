<template>
    <div class="goodForm">
        <UserForm :userData="userData"></UserForm>
    </div>
</template>


<script>
import UserForm from '@/views/Admin/components/UserForm'
  export default {
    data() {
      return {
        userData: JSON.parse(this.$route.query.value)
      }
    },
    methods: {
      
    },
    components:{
        UserForm
    },

    created() {
        
    }
  }
</script>

<style lang="less" scoped>

.userForm {
    
}

</style>