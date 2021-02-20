import { mapActions, mapGetters, mapMutations, mapState } from 'vuex'
export const vuexData = {
    data(){
        return {

        }
    },
    components: {
        
    },
    computed: {
        ...mapState([
            'user',
            'shippingAddress',
            'loading',
            'addressList',
            'categoryNow'
        ]),
        ...mapGetters([
            'USER_SEX',  // *
            'USER_SEX',
            'USER_NAME',
            'USER_ID',
            'USER_BIRTHDAY',
            'USER_TELEPHONE',
            'LOADING',
            'GET_USER',
            'GET_CATEGORY_NOW',
            'GET_COLLECT_LIST',
            'GET_GOOD_DETAIL',
            'GET_CART',
            'GET_ORDER_LIST',
            'GET_BOUGHT',
            'GET_UNPAID',
            'GET_UNRECEIVED',
            'GET_UNASSESS'
        ]),
    },
    methods: {
        
        back() {
            this.$router.animate = 1
            if (window.history.length <= 2) { 
                this.$router.push({ name: 'AA' })
            } else {
                this.$router.go(-1)
            }
        },
        ...mapActions([
            'Login',
            'change_username',  // 修改用户 name
            'change_sex',       // 修改用户 sex
            'change_birthday',   // 修改用户 birthday
            'updateAddress',
            'delectUserAddress',
            'get_collect_list',
            'get_cart_list',
            'add_to_cart',
            'removeOne_from_cart',
            'set_cart_item',
            'delete_cart_choose_item',
            'getGoodAssess',
            'del_unassess'
        ]),
        

        ...mapMutations([
            'LOG_OUT',    // 注销
            'USER_INFO_BRITHDAY',
            'LOGIN_OUT', // *
            'INIT_USER_SHOPPING_ADDRESS', 
            'USER_SEX_CHANGE',   // 修改用户sex
            'SET_LOADING',
            'CHANGE_USER_ADDRESS',
            'DELETE_USER_ADDRESS',
            'ADD_COLLECT',
            'DELETE_COLLECT',
            'SET_CATEGORY_NOW',
            'SET_IS_CHOOSE_CATEGORY',
            'ADD_TO_CART',
            'SET_CART',
            'SET_ORDER_LIST',
            'ADD_BOUGHT',
            'ADD_UNPAID',
            'ADD_UNRECEIVED',
            'ADD_UNASSESS',
            'DEL_UNASSESS'
        ]),
    }
}