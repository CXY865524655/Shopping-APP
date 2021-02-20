export const state = {

    // 登录请求状态
    status: '',

    // 登录token
    token: localStorage.getItem('token') || '',

    // 用户信息
    user: {},

    // 上一次登录用户信息
    lastLoginUser: {},

    // 收货地址
    addressList: [],

    // 收藏列表
    collectList: [],

    // loading 显示
    loading: false,
    
    // 当前的 category 
    categoryNow: {},

    isChooseCategory: false,

    goodDetail: [],

    cart: [],

    orderList: [],

    // 全部
    bought: [],

    // 待支付
    unpaid: [],

    // 待收货
    unreceived: [],

    // 待评价
    unassess: [],

    active: 0,

    
}