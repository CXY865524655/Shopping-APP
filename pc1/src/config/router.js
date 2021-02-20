import Vue from 'vue'
import Router from 'vue-router'

// 官方推荐的懒加载 - 又称为按需加载
const GoodList = () =>
    import('@/views/Admin/GoodList')
const GoodEdit = () =>
    import('@/views/Admin/GoodEdit')
const GoodAdd = () =>
    import('@/views/Admin/GoodAdd')

const UserList = () =>
    import('@/views/Admin/UserList')
const UserEdit = () =>
    import('@/views/Admin/UserEdit')

const OrderList = () =>
    import('@/views/Admin/OrderList')
const OrderEdit = () =>
    import('@/views/Admin/OrderEdit')

const ElementTest = () =>
    import('@/views/ElementTest')
    
const Login = () =>
    import('@/views/Admin/Login')

Vue.use(Router)
Router.prototype.goBack = function () { 
    　　this.isBack = true
    　　window.history.go(-1)
}
const router = new Router({
    routes: [
        { path: '/', redirect: '/home' },
        { path: '/home', name:'Home', component: ElementTest, meta: {keepAlive: true, requiresAuth:true}, children:[
                { path: '/GoodList', name:'GoodList', component: GoodList, meta: {keepAlive: true, requiresAuth:true}},
                { path: '/GoodEdit', name:'GoodEdit', component: GoodEdit, meta: {keepAlive: true, requiresAuth:true}},
                { path: '/GoodAdd', name:'GoodAdd', component: GoodAdd, meta: {keepAlive: true, requiresAuth:true}},
                { path: '/UserList', name:'UserList', component: UserList, meta: {keepAlive: true, requiresAuth:true}},
                { path: '/UserEdit', name:'UserEdit', component: UserEdit, meta: {keepAlive: true, requiresAuth:true}},
                { path: '/OrderList', name:'OrderList', component: OrderList, meta: {keepAlive: true, requiresAuth:true}},
                { path: '/OrderEdit', name:'OrderEdit', component: OrderEdit, meta: {keepAlive: true, requiresAuth:true}},
        ]},
        { path: '/Login', name:'Login', component: Login, meta: {keepAlive: true}},

    ]   
})


export default router