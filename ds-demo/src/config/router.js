import Vue from 'vue'
import Router from 'vue-router'

// 官方推荐的懒加载 - 又称为按需加载
const HomePage = () =>
    import('@/views/HomePage')
const Home = () => 
    import('@/views/Home')
const Login = () => 
    import('@/views/Login.vue')
const Register = () => 
    import('@/views/Register.vue')
const UserCenter = () => 
    import('@/views/personal/UserCenter.vue')
const OrderStatus = () =>
    import('@/views/personal/OrderStatus/OrderStatus.vue')
const MyAddress = () => 
    import('@/views/personal/Address/MyAddress.vue')
const AddAddress = () =>
    import('@/views/personal/Address/AddAddress.vue')
const AddressEditor = () =>
    import('@/views/personal/Address/AddressEditor.vue')
const Cart = () => 
    import('@/views/cart')
const Category = () => 
    import('@/views/category/Category.vue')
const Product = () =>
    import('@/views/product/Product.vue')
const Collects = () =>
    import('@/views/collects')
const Order = () =>
    import('@/views/order')
const ResultPage = () =>
    import('@/views/resultPage')
const right = () =>
    import('@/views/category/components/RightContent.vue')
const rightaa = () =>
    import('@/views/category/components/RightSubCategory.vue')
const Assess = () =>
    import('@/views/assess')
const Personal = () =>
    import('@/views/personal/Personal.vue')


Vue.use(Router)
Router.prototype.goBack = function () { 
    this.isBack = true
    window.history.go(-1)
}
const router = new Router({
    routes: [
        { path: '/', redirect: '/homePage/home' },

        { 
            path: '/homePage', 
            name: 'HomePage', 
            component: HomePage,
            
            children:[
                { path: 'home', name: 'Home', component: Home, meta: { keepAlive: true } },
                { path: 'category', name: 'Category', component: Category, meta: { keepAlive: true, requiresAuth: false }},
                { path: 'cart', name: 'Cart', component: Cart, meta: { keepAlive: true } },
                { path: 'personal', name: 'Personal', component: Personal, meta: { keepAlive: true }, children:[
                    { path: 'userCenter', name: 'UserCenter', component: UserCenter, meta: { keepAlive: true }},
                    { path: 'orderStatus', name: 'OrderStatus', component: OrderStatus, meta: { keepAlive: true, requiresAuth: true }},
                    { path: 'myAddress', name: 'MyAddress', component: MyAddress, meta: { keepAlive: true, requiresAuth: true },
                        children: [
                            { path: 'addAddress', name: 'AddAddress', component: AddAddress, meta: { keepAlive: true, requiresAuth: true }},
                            { path: 'addressEditor', name: 'AddressEditor', component: AddressEditor, meta: { keepAlive: true, requiresAuth: true }}
                        ]},
                    { path: 'collects', name: 'Collects', component: Collects, meta: { keepAlive: true, requiresAuth: true}},
                ]},
            ],
        },
        { path: '/resultPage', name: 'ResultPage', component: ResultPage, meta: { keepAlive: true } },
        { path: '/login', name: 'Login', component: Login, meta:{ keepAlive: true }},
        { path: '/register', name: 'Register', component: Register, meta: { keepAlive: true }},
        { path: '/right', name: 'right', component: right, meta: { keepAlive: true, requiresAuth: false }},
        { path: '/rightaa', name: 'rightaa', component: rightaa, meta: { keepAlive: true, requiresAuth: false }},
        { path: '/product', name: 'Product', component: Product, meta: { keepAlive: true, requiresAuth: false }},
        { path: '/assess', name: 'Assess', component: Assess, meta: { keepAlive: true, requiresAuth: false }},
        { path: '/order', name: 'Order', component: Order, meta: { keepAlive: true, requiresAuth: false }},
    ]   
})


export default router
