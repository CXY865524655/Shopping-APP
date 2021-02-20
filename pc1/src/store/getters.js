import { state } from "./state"


export const USER_SEX = state => {
    if (state.user.sex == 1) {
        return '男'
    }
    if (state.user.sex == 2 ) {
        return '女'
    }
    return '未填写'
}

// 获取用户 name
export const USER_NAME = state => {
    if(state.user.username) {
        return state.user.username
    }
    return '未填写'
}

// 获取用户 ID
export const USER_ID = state => {
    return state.user.id
}

// 获取用户 birthday
export const USER_BIRTHDAY = state => {
    if(state.user.birthday) {
        return state.user.birthday
    }
    return '未填写'
}

export const USER_TELEPHONE = state => {
    if(state.user.telephone) {
        return state.user.telephone
    }
    return '未填写'
}

// 获取当前用户信息
export const GET_USER = state => {
    return state.user
}


// loading 状态
export const LOADING = state => {
    return state.loading
}

// 获取当前用户收藏列表
export const GET_COLLECT_LIST = state => {
    return state.collectList
}

// 获取当前用户收货地址列表
export const GET_CATEGORY_NOW = state => {
    return state.categoryNow
}

export const GET_IS_CHOOSE_CATEGORY = state => {
    return state.isChooseCategory
}

// 获取当前用户 购物车 商品列表
export const GET_GOOD_DETAIL = state => {
    return state.goodDetail
}

// 获取当前用户 购物车 商品列表
export const GET_CART = state => {
    return state.cart
}


export const GET_ORDER_LIST = state => {
    return state.orderList
}

export const GET_BOUGHT = state => {
    return state.bought
}

export const GET_UNPAID = state => {
    return state.unpaid
}

export const GET_UNRECEIVED = state => {
    return state.unreceived
}

export const GET_UNASSESS = state => {
    return state.unassess
}






