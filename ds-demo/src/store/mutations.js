import { removeLocalStore } from '@/config/global.js'
import {
    SET_TOKEN,
    AUTH_REQUEST,
    AUTH_SUCCESS,
    AUTH_ERROR,
    LOG_OUT,
    CART_NUM_UP1,
    SET_LAST_LOGIN_USER,
    SET_LOADING,
    SET_ADDRESS_LIST,
    INSERT_USER_ADDRESS,
    CHANGE_USER_ADDRESS,
    DELETE_USER_ADDRESS,
    SET_COLLECT_LIST,
    ADD_COLLECT,
    DELETE_COLLECT,
    SET_CATEGORY_NOW,
    SET_IS_CHOOSE_CATEGORY,
    SET_CART_ITEM_NUM,
    DELETE_CART_CHOOSE_ITEM,
    ADD_TO_CART,
    REMOVE_ONE_FROM_CART,
    SET_CART,
    SET_ORDER_LIST,
    ADD_BOUGHT,
    ADD_UNPAID,
    ADD_UNRECEIVED,
    ADD_UNASSESS,
    SET_BOUGHT,
    SET_UNPAID,
    SET_UNRECEIVED,
    SET_UNASSESS,
    DEL_UNASSESS,
    SET_ACTIVE,
    SET_USER_IMG,
    SET_USER
} from './mutation-type'
import Vue from 'vue'
import api from '@/config/API'
export default {
    [SET_ACTIVE](state, {
        active
    }){
        Vue.set(state, 'active', active)
    },
    [SET_TOKEN](state, token) {
        Vue.set(state,'token',token)
        localStorage.setItem('token','Bearer ' + token)  // 同步存储 token 到 localStorage
    },

    [AUTH_REQUEST] (state) {
        Vue.set(state,'status','loading')
    },

    [AUTH_SUCCESS] (state, data) {
        
        Vue.set(state,'status','success')
        Vue.set(state,'token',data.token)
        Vue.set(state,'user',data.user)
        let collectList = data.user.collects
        collectList = collectList?collectList.length>=1? collectList.split(';'):[]:[]    
        let goodDetail = data.user.goodDetail
        let bought = data.user.bought ? data.user.bought.length>=1? data.user.bought:[]:[]
        let unpaid = data.user.unpaid ? data.user.unpaid.length>=1? data.user.unpaid:[]:[]
        let unassess = data.user.unassess ? data.user.unassess.length>=1? data.user.unassess:[]:[]
        let unreceived = data.user.unreceived ? data.user.unreceived.length>=1? data.user.unreceived:[]:[]
        Vue.set(state,'bought', JSON.parse(JSON.stringify(bought))) 
        Vue.set(state,'unpaid', JSON.parse(JSON.stringify(unpaid)))
        Vue.set(state,'unassess', JSON.parse(JSON.stringify(unassess)))
        Vue.set(state,'unreceived', JSON.parse(JSON.stringify(unreceived)))
        Vue.set(state, 'cart', (goodDetail&&goodDetail.length>0)?JSON.parse(JSON.stringify(goodDetail)) : [])
        let is = ''
        let yuansu = {}
        if(data.user.address.length > 0) {
            data.user.address.forEach((item,index) => {
                let isDefault = item['isDefault'] == 'true'? true : false
                Vue.set(item, 'isDefault', isDefault)
                if(isDefault) {
                    is = index
                    yuansu = JSON.parse(JSON.stringify(item))
                }
            })
        }
        if(is && yuansu) {
            data.user.address.splice(is, 1)
            data.user.address.unshift(yuansu)
        }
        Vue.set(state,'addressList', JSON.parse(JSON.stringify(data.user.address)))
        Vue.set(state,'collectList', JSON.parse(JSON.stringify(collectList)))
    },

    [AUTH_ERROR] (state) {
        Vue.set(state,'error','error')
    },

    [SET_IS_CHOOSE_CATEGORY](state,{
        choose
    }) {
        Vue.set(state,'isChooseCategory',choose)
    },

    [ADD_COLLECT](state, {
        collect
    }) {
        var index = state.collectList.findIndex(item => item===collect)
        if(index < 0) {
            Vue.set(state,'collectList',[...state.collectList, JSON.parse(JSON.stringify(collect))])
        }
    },

    [DELETE_COLLECT](state,{
        collect
    }) {
        var index = state.collectList.findIndex(item => item === collect)
        if(index >= 0) {
            let c = state.collectList.filter(item => item !== collect)
            Vue.set(state,'collectList',c)
        }
    },

    [DEL_UNASSESS](state, {
        unassess
    }) {
        let asss = JSON.parse(unassess)
        var index = state.unassess.findIndex(item => item.goodsid == asss.goodsid)
        if(index >= 0) {
            let a = state.unassess.filter(item => item.goodsid != asss.goodsid)
            Vue.set(state,'unassess',a)
        }
    },


    // ?
    [DELETE_CART_CHOOSE_ITEM](state, {
        selecteds
    }) {
        let carts = state.cart.filter(item => selecteds.findIndex(i => i === item.productModel.goodsid) < 0)
        Vue.set(state,'cart',JSON.parse(JSON.stringify(carts)))
    },

    [ADD_TO_CART](state, {
        userid,
        cartItem
    }) {
        let detailsShow = cartItem.productModel.detailsShow
        let imageShow = cartItem.productModel.imageShow
        let imgss = cartItem.productModel.imgss
        Vue.set(cartItem,'detailsShow',detailsShow)
        Vue.set(cartItem,'imageShow',imageShow)
        Vue.set(cartItem,'imgss',imgss)
        Vue.set(cartItem,'isChecked',false)
        Vue.set(cartItem,'userid',userid)
        Vue.set(cartItem,'goodsid',cartItem.productModel.goodsid)
        delete cartItem.productModel.detailsShow
        delete cartItem.productModel.imageShow
        delete cartItem.productModel.imgss
        delete cartItem.goodsId
        var index = state.cart.findIndex(item => item.productModel.goodsid===cartItem.productModel.goodsid)  
        if(index < 0) {
            Vue.set(state,'cart',[...state.cart, JSON.parse(JSON.stringify(cartItem))])
        }else {
            let goods = JSON.parse(JSON.stringify(state.cart))
            goods[index].num = goods[index].num + 1
            Vue.set(state,'cart',goods)
        }
    },

    [CART_NUM_UP1](state, {
        goodsid
    }) {
        var index = state.cart.findIndex(item => item.productModel.goodsid===goodsid)  
        let goods = JSON.parse(JSON.stringify(state.cart))
        goods[index].num = goods[index].num + 1
        Vue.set(state,'cart',goods)
    },

    [SET_CART_ITEM_NUM](state, {
        cartItem
    }) {
        var index = state.cart.findIndex(item => item.goodsId===cartItem.goodsId)
        if(index >= 0) {
            let goods = JSON.parse(JSON.stringify(state.cart))
            goods[index].num = cartItem.num
            Vue.set(state,'cart',goods)
        }
    },

    [SET_CART](state, {
        cart
    }) {
        Vue.set(state,'cart',cart)
    },

    [SET_ORDER_LIST](state, {
        order
    }) {
        Vue.set(state,'orderList',JSON.parse(JSON.stringify(order)))
    },

    [ADD_BOUGHT](state, {
        bought
    }) {
        if(!state.bought) {
            Vue.set(state,'bought',[])
        }
        let l = JSON.parse(bought)
        Vue.set(state,'bought',[...state.bought, ...l])
    },

    [ADD_UNPAID](state, {
        unpaid
    }) {

        if(!state.unpaid) {
            Vue.set(state,'unpaid',[])
        }
        let l = JSON.parse(unpaid)
        Vue.set(state,'unpaid',[...state.unpaid, ...l])
    },

    [ADD_UNRECEIVED](state, {
        unreceived
    }) {
        // Vue.set(state,'unreceived',[])

        if(!state.unreceived) {
            Vue.set(state,'unreceived',[])
        }
        let l = JSON.parse(unreceived)
        Vue.set(state,'unreceived',[...state.unreceived, ...l])
    },

    [ADD_UNASSESS](state, {
        unassess
    }) {
        // Vue.set(state,'unassess',[])
        
        if(!state.unassess) {
            Vue.set(state,'unassess',[])
        }
        let l = JSON.parse(unassess)
        Vue.set(state,'unassess',[...state.unassess, ...l])
    },
    [SET_BOUGHT](state, {
        bought
    }) {
        Vue.set(state,'bought',bought?JSON.parse(bought):[])
    },

    [SET_UNPAID](state, {
        unpaid
    }) {
        Vue.set(state,'unpaid',unpaid?JSON.parse(unpaid):[])
    },

    [SET_UNRECEIVED](state, {
        unreceived
    }) {
        Vue.set(state,'unreceived',unreceived?JSON.parse(unreceived):[])
    },

    [SET_UNASSESS](state, {
        unassess
    }) {
        Vue.set(state,'unassess',unassess?JSON.parse(unassess):[])
    },
    
    
    // ?
    [REMOVE_ONE_FROM_CART](state, {
        cartItem
    }) {
        var index = state.cart.findIndex(item => item.goodsId===cartItem.goodsId)
        if(index >= 0) {
            let goods = JSON.parse(JSON.stringify(state.cart))
            goods[index].num = goods[index].num - 1
            Vue.set(state,'cart',goods)
            let ls = JSON.parse(JSON.stringify(state.cart))
            let i = ls.findIndex(item => item.goodsid === cartItem.goodsId)
            if (i > -1) {
                ls[i].num = ls[i].num - 1
            }
            Vue.set(state,'cart',ls)
        }
    },

    
    

    [LOG_OUT] (state) {
        Vue.set(state,'status','')
        Vue.set(state,'token','')
        Vue.set(state,'user',{})
        Vue.set(state,'addressList',[])
        Vue.set(state,'collectList',[])
        Vue.set(state,'loading',false)
        Vue.set(state,'categoryNow',{})
        Vue.set(state,'isChooseCategory',false)
        Vue.set(state,'cart',[])

        removeLocalStore('token')
    },


    // 获取上一次登录的用户登录信息
    [SET_LAST_LOGIN_USER](state, {
        lastLoginUser
    }) {
        Vue.set(state,'lastLoginUser',JSON.parse(JSON.stringify(lastLoginUser)))
    },

  // 修改 Loading 状态
    [SET_LOADING](state, {
        loading
    }) {
        Vue.set(state,'loading',loading)
    },

  // 设置当前用户的收货地址列表
    [SET_ADDRESS_LIST](state, {
        address
    }) {
        Vue.set(state,'addressList',[...state.addressList, JSON.parse(JSON.stringify(address))])
    },

// 设置当前用户的收藏列表
    [SET_COLLECT_LIST](state, {
        collects
    }) {
        Vue.set(state,'collectList',[...state.collectList, JSON.parse(JSON.stringify(collects))])
    },

    

    // 新增一个用户收货地址
    [INSERT_USER_ADDRESS](state, {
        content
    }) {
        let isDefault = content['isDefault']
        let addressId = content['id']
        if(state.addressList.length >= 1) {
            // 判断是否默认
            if(isDefault) {
                state.addressList = state.addressList.map((item) => {
                    if(item['id']!==addressId) {
                        item['isDefault'] = false
                    }
                    return item
                })
                state.addressList.splice(0, 0, content)
                return
            }
            state.addressList.splice(state.addressList.length, 0, content)
            return
        }
        state.addressList.splice(0, 0, content)
    },

// 修改用户收货地址列表
    [CHANGE_USER_ADDRESS](state, {
        content
    }) {
        let isDefault = content['isDefault']
        let addressId = content['id']
        const index = state.addressList.findIndex(item => item.id === addressId)
        if(isDefault) {
            state.addressList = state.addressList.map((item) => {
                if(item['id']!==addressId) {
                    item['isDefault'] = false
                }
                return item
            })
            var i = content
            Vue.set(state.addressList, index, state.addressList[0])
            Vue.set(state.addressList, 0, i)
            return
        }
        state.addressList.splice(index, 1, content)
    },

    // 删除一个用户收货地址
    [DELETE_USER_ADDRESS](state,{
        id
    }) {
        const index = state.addressList.findIndex(item => item.id === id)
        state.addressList.splice(index, 1)
    },

    // 设置当前 category
    [SET_CATEGORY_NOW](state,{
        categoryNow
    }) {
        Vue.set(state, 'categoryNow', categoryNow)
    },

    // 设置当前 category
    [SET_USER_IMG](state,{
        imageShow
    }) {
        Vue.set(state.user, 'imageShow', imageShow)
    },

    [SET_USER](state,{
        user
    }) {
        Vue.set(state, 'user', user)
    },
    
}
