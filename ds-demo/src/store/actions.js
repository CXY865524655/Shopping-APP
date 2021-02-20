import axios from '@/config/request/http2.js'
import api from '@/config/API'
import context from '@/main.js'
import Vue from 'vue'

export const modifyCount = ({commit}, name) => {
    commit('addss', name)
}

const getLength = (user) => {
    return Object.keys(user).length
}

export const Login = ({commit}, user) => {
    return new Promise(async (resolve, reject) => {
        var userLength = getLength(user)
        commit('AUTH_REQUEST')
        await api.user.login(user)
        .then(async res => {
            if (res.data.status == 'success') {
                await updateUserInfo(res, commit)
                // 确定是否为账号登录形式
                if(userLength == 2 && user.hasOwnProperty("username") && user.hasOwnProperty("password")) {
                    // 消除验证码错误次数
                    localStorage.removeItem("err" + res.data.data.user_Info.username)
                }
                // 确定是否为账号登录形式
                if(userLength == 3 && user.hasOwnProperty("telephone") && user.hasOwnProperty("code") && user.hasOwnProperty("relCode")) {
                    // 消除验证码错误次数
                    localStorage.removeItem('loginCode')
                }
                resolve(res)  // resolve 下面还是会执行，最好加个 return
                return 
            }
            if (res.data.status == 'fail') {
                
                context.$toast(res.data.data.errMsg)
                // 确定是否为账号登录形式
                if(userLength == 2 && user.hasOwnProperty("username") && user.hasOwnProperty("password")) {
                    if(!localStorage.getItem("err" + user.username) || localStorage.getItem("err" + user.username) == 0) {
                            localStorage.setItem("err" + user.username, 0)
                    }
                    localStorage.setItem("err" + user.username, parseInt(localStorage.getItem("err" + user.username)) + 1)
                }

                // 确定是否为账号登录形式
                if(userLength == 3 && user.hasOwnProperty("telephone") && user.hasOwnProperty("code") && user.hasOwnProperty("relCode")) {
                    // 消除验证码错误次数
                    localStorage.removeItem('loginCode')
                }
                reject(res)
                return 
            } 
        })
        .catch(err => {
            return err
        })
    })
}

export const log_out = ({commit}) => {
    return new Promise((resolve) => {
        commit('LOG_OUT')
        resolve()
        return
    })
}

export const add_collect = ({commit, state},{
    userId,
    collect
}) => {
    return new Promise((resolve) => {
        commit('ADD_COLLECT',{
            collect
        })
        apiUpdateCollect(state, userId)
        resolve()
        return
    })
}

export const add_to_cart = ({commit, state},{
    userId,
    cart
}) => {
    return new Promise((resolve) => {
        commit('ADD_TO_CART',{
            userid: userId,
            cartItem: JSON.parse(JSON.stringify(cart))
        })
        let c = JSON.parse(JSON.stringify(cart))
        Vue.set(cart, "goodsid", cart.productModel.goodsid)
        api.user.cartAdd(userId, JSON.stringify(c))
        // apiUpdateCart(state, userId)
        resolve()
        return
    })
}

export const set_cart_item = ({commit, state},{
    userId,
    cart
}) => {
    return new Promise((resolve) => {
        commit('SET_CART_ITEM_NUM',{
            cartItem: cart
        })
        apiUpdateCart(state, userId)
        resolve()
        return
    })
}

export const sendAssess = ({},{
    goodsid,
    assess,
    num,
    buyNum
}) => {
    return new Promise((resolve) => {
        api.goods.sendAssess(
            goodsid,
            assess,
            num,
            buyNum
        )
        .then(res => {
            resolve(res)
        })
        return
    })
}

export const del_unassess = ({commit, state},{
    userId,
    unassess
}) => {
    return new Promise(async (resolve) => {
        await commit('DEL_UNASSESS',{
            unassess
        })
        await api.user.set_unassess({
            userId,
            unassess: JSON.stringify(state.unassess)
        })
        .then(res => {
            resolve(res)
        })
        
        
        return
    })
}

export const getGoodAssess = ({},{
    goodsid
}) => {
    return new Promise((resolve, reject) => {
        api.goods.getGoodAssess(
            goodsid
        )
        .then(res => {
            resolve(res)
        })
        .catch(err => {
            reject(err)
        })
        
        return
    })
}

export const pay_success = ({commit, state},{
    userId,
    list
}) => {
    return new Promise((resolve) => {

            api.user.initOrderDetail(userId)
                .then(res => {
                    let l = JSON.parse(JSON.stringify(res.data.data))
                    let bought = l.bought ? JSON.parse(l.bought) : []
                    if (bought && bought.length >= 1) {
                        bought.forEach((item) => {
                            Vue.set(item, 'imageShow', '')
                            Vue.set(item, 'imgss', [])
                            let imgPath = ''

                            if (item.image) {
                                imgPath = item.image
                            } else {
                                imgPath = item.productModel.image
                            }
                            let i = imgPath.indexOf(';')
                            if (i >= 0) {
                                // 多个
                                let paths = imgPath.split(';')
                                let p = paths[0]
                                api.goods.getGoodImg(p)
                                    .then(res => {
                                        return 'data:image/png;base64,' + btoa(
                                            new Uint8Array(res.data)
                                                .reduce((data, byte) => data + String.fromCharCode(byte), '')
                                        )
                                    })
                                    .then(data => {
                                        Vue.set(item, 'imageShow', data)
                                        item.imgss.push(data)
                                    })

                            } else if (imgPath.length >= 1) {
                                // 一个
                                api.goods.getGoodImg(imgPath)
                                    .then(res => {
                                        return 'data:image/png;base64,' + btoa(
                                            new Uint8Array(res.data)
                                                .reduce((data, byte) => data + String.fromCharCode(byte), '')
                                        )
                                    })
                                    .then(data => {
                                        Vue.set(item, 'imageShow', data)
                                        item.imgss.push(data)
                                    })

                            }
                        })
                    }


                    let unpaid = l.unpaid ? JSON.parse(l.unpaid) : []
                    if (unpaid && unpaid.length >= 1) {
                        unpaid.forEach((item) => {
                            Vue.set(item, 'imageShow', '')
                            Vue.set(item, 'imgss', [])
                            let imgPath = ''

                            if (item.image) {
                                imgPath = item.image
                            } else {
                                imgPath = item.productModel.image
                            }
                            let i = imgPath.indexOf(';')

                            if (i >= 0) {
                                // 多个
                                let paths = imgPath.split(';')
                                let p = paths[0]
                                api.goods.getGoodImg(p)
                                    .then(res => {

                                        return 'data:image/png;base64,' + btoa(
                                            new Uint8Array(res.data)
                                                .reduce((data, byte) => data + String.fromCharCode(byte), '')
                                        )
                                    })
                                    .then(data => {
                                        Vue.set(item, 'imageShow', data)
                                        item.imgss.push(data)
                                    })

                            } else if (imgPath.length >= 1) {
                                // 一个
                                api.goods.getGoodImg(imgPath)
                                    .then(res => {
                                        return 'data:image/png;base64,' + btoa(
                                            new Uint8Array(res.data)
                                                .reduce((data, byte) => data + String.fromCharCode(byte), '')
                                        )
                                    })
                                    .then(data => {
                                        Vue.set(item, 'imageShow', data)
                                        item.imgss.push(data)
                                    })

                            }
                        })
                    }

                    let unreceived = l.unreceived ? JSON.parse(l.unreceived) : []

                    if (unreceived && unreceived.length >= 1) {
                        unreceived.forEach((item) => {
                            Vue.set(item, 'imageShow', '')
                            Vue.set(item, 'imgss', [])
                            let imgPath = ''
                            if (item.image) {
                                imgPath = item.image
                            } else {
                                imgPath = item.productModel.image
                            }
                            let i = imgPath.indexOf(';')

                            if (i >= 0) {
                                // 多个
                                let paths = imgPath.split(';')
                                let p = paths[0]
                                api.goods.getGoodImg(p)
                                    .then(res => {

                                        return 'data:image/png;base64,' + btoa(
                                            new Uint8Array(res.data)
                                                .reduce((data, byte) => data + String.fromCharCode(byte), '')
                                        )
                                    })
                                    .then(data => {
                                        Vue.set(item, 'imageShow', data)
                                        item.imgss.push(data)
                                    })

                            } else if (imgPath.length >= 1) {
                                // 一个
                                api.goods.getGoodImg(imgPath)
                                    .then(res => {
                                        return 'data:image/png;base64,' + btoa(
                                            new Uint8Array(res.data)
                                                .reduce((data, byte) => data + String.fromCharCode(byte), '')
                                        )
                                    })
                                    .then(data => {
                                        Vue.set(item, 'imageShow', data)
                                        item.imgss.push(data)
                                    })

                            }
                        })
                    }
                    let unassess = l.unassess ? JSON.parse(l.unassess) : []

                    if (unassess && unassess.length >= 1) {
                        unassess.forEach((item) => {
                            Vue.set(item, 'imageShow', '')
                            Vue.set(item, 'imgss', [])
                            let imgPath = ''
                            if (item.image) {
                                imgPath = item.image
                            } else {
                                imgPath = item.productModel.image
                            }
                            let i = imgPath.indexOf(';')

                            if (i >= 0) {
                                // 多个
                                let paths = imgPath.split(';')
                                let p = paths[0]
                                api.goods.getGoodImg(p)
                                    .then(res => {

                                        return 'data:image/png;base64,' + btoa(
                                            new Uint8Array(res.data)
                                                .reduce((data, byte) => data + String.fromCharCode(byte), '')
                                        )
                                    })
                                    .then(data => {
                                        Vue.set(item, 'imageShow', data)
                                        item.imgss.push(data)
                                    })

                            } else if (imgPath.length >= 1) {
                                // 一个
                                api.goods.getGoodImg(imgPath)
                                    .then(res => {
                                        return 'data:image/png;base64,' + btoa(
                                            new Uint8Array(res.data)
                                                .reduce((data, byte) => data + String.fromCharCode(byte), '')
                                        )
                                    })
                                    .then(data => {
                                        Vue.set(item, 'imageShow', data)
                                        item.imgss.push(data)
                                    })

                            }
                        })
                    }
                    Vue.set(state, "bought", bought)
                    Vue.set(state, "unassess", unassess)

        commit('ADD_BOUGHT',{
            bought: list
        })
        commit('ADD_UNASSESS',{
            unassess: list
        })
        let selecteds = JSON.parse(list).map(item => item.goodsid)
        let sales = JSON.parse(list).map(item => {
            return {
                goodsid: item.goodsid,
                num: item.num
            }
        })
        apiUpdateOrderDetail(state, userId, sales)

        commit('DELETE_CART_CHOOSE_ITEM',{
            selecteds
        })
        api.user.updateCart(userId, selecteds.join(";"))
        resolve()
        return
        })
    })
}



export const removeOne_from_cart = ({commit, state},{
    userid,
    cart
}) => {
    return new Promise((resolve) => {
        commit('REMOVE_ONE_FROM_CART',{
            cartItem: cart
        })
        api.user.cartDel(userid, JSON.stringify(cart))
        // apiUpdateCart(state, userId)
        resolve()
        return
    })
}

export const delete_cart_choose_item = ({commit, state},{
    userId,
    selecteds
}) => {
    return new Promise((resolve) => {
        commit('DELETE_CART_CHOOSE_ITEM',{
            selecteds
        })

        api.user.removePro(userId, selecteds.join(";"))
        // apiUpdateCart(state, userId)
        resolve()
        return
    })
}

export const delete_collect = ({commit, state},{
    userId,
    collect
}) => {
    return new Promise((resolve) => {
        commit('DELETE_COLLECT',{
            collect
        })
        apiUpdateCollect(state, userId)
        resolve()
        return
    })
}

export const get_collect_list = ({},{
    collect
}) => {
    return new Promise((resolve) => {
        api.user.getCollectList(collect)
        .then(res => {
            resolve(res)
        })
        return
    })
}

export const get_cart_list = ({},{
    userid
}) => {
    return new Promise((resolve) => {
        api.user.getCartList(userid)
        .then(res => {
            resolve(res)
        })
        return
    })
}

// export const initCategory = ({commit, state}) => {
//     return new Promise((resolve) => {
//         commit('DELETE_COLLECT',{
//             collect
//         })
//         apiUpdateCollect(state, userId)
//         resolve()
//         return
//     })
// }

async function apiUpdateCollect(state, userId) {
    let collects = state.collectList.join(';')
    await api.user.setUserCollects(userId, collects)
        
}

async function apiUpdateCart(state, userId) {
    let goods = state.goodDetail.map(item => JSON.stringify(item))
    
    let cart = goods.join(';')
    if(cart) {
        cart += ';'
    }
       await api.user.setUserCart(userId, cart)
        
}
// 更新用户信息--NAME
export async function  change_username ({commit}, {
    username,
    id
})  {
    return new Promise((resolve, reject) => {
        

        api.user.updateUserName(username,id)
         .then(res => {
             if (res.data.status == 'success') {
                updateUserInfo(res,commit)
                //  const token =  res.data.data.token
                //  const user_Info = res.data.data.user_Info
                 
                //  // 更新localStorage
                //  localStorage.setItem('token', 'Bearer ' + token)
                //  axios.defaults.headers.common['Authorization'] = token
                
                //  const data = {
                //      'token': token,
                //      'user': user_Info
                //  }
                //  // 更新状态管理 =》 token，user
                //  commit('AUTH_SUCCESS', data)
                 
                //  context.$toast(res.data.data.success_msg)
                 resolve(res)  // resolve 下面还是会执行，最好加个 return
                 return 
             }
 
             if (res.data.status == 'fail') {
                 context.$toast(res.data.data.errMsg)
                 reject(res)
                 return 
             }
 
 
             
         })
         .catch(err => {
             return err
         })
    })
       
}

export async function change_sex({commit}, {
    id,
    sex
}){
    return new Promise((resolve, reject) => {
        api.user.updateUserSex(id, sex)
        .then(res => {
            if (res.data.status == 'success') {
                updateUserInfo(res,commit)
                
                resolve(res)  // resolve 下面还是会执行，最好加个 return
                return 
            }

            if (res.data.status == 'fail') {
                context.$toast(res.data.data.errMsg)
                reject(res)
                return 
            }
        })
    })
}

export async function change_birthday({commit}, {
    id,
    birthday
}){
    return new Promise((resolve, reject) => {
        api.user.updateUserBirthday(id, birthday).
        then(res => {
            if (res.data.status == 'success') {
                updateUserInfo(res,commit)
                
                resolve(res)  // resolve 下面还是会执行，最好加个 return
                return 
            }

            if (res.data.status == 'fail') {
                context.$toast(res.data.data.errMsg)
                reject(res)
                return 
            }
        })
    })
}

export const insertAddress = ({commit, state}, {
    userId,
    content
}) =>{
    return new Promise((resolve) => {
        commit('INSERT_USER_ADDRESS', {
            content
        })
        api.user.addAddress(userId, JSON.stringify(content))
        // apiUpdateAddress(state, userId)
        resolve()
        return
    })
}

export const updateAddress = ({commit, state}, {
    userid,
    content
}) =>{
    return new Promise((resolve) => {
        commit('CHANGE_USER_ADDRESS', {
            content
        })
        api.user.ediAddress(userid, JSON.stringify(content))
        // apiUpdateAddress(state, id)
        resolve()
        return
    })
}

export const delectUserAddress = ({commit, state}, {
    id,
    userid
}) =>{
    return new Promise((resolve) => {
        commit('DELETE_USER_ADDRESS',{
            id
        })
        api.user.delAddress(userid, id)
        // apiUpdateAddress(state, userId)
        resolve()
        return
    })
}

async function  updateUserInfo(res, commit) {
                const token =  res.data.data.token
                let user_Info = await getUimg(res, commit)
                localStorage.setItem('token', 'Bearer ' + token)
                axios.defaults.headers.common['Authorization'] = token
                const data22 = {
                    'token': token,
                    'user': user_Info
                }
                // 更新状态管理 =》 token，user
                commit('AUTH_SUCCESS', data22)
}

async function getUimg(res){
    const user_Info = JSON.parse(JSON.stringify(res.data.data.user_Info))
    Vue.set(user_Info, 'imageShow', '')
    let imgP = user_Info.image_url

    let tou = imgP.substring(0,4)
                if(tou && tou!='http') {
                    await api.user.getUserImg(imgP)
                    .then(res => {
                        return 'data:image/png;base64,' + btoa(
                            new Uint8Array(res.data)
                            .reduce((data, byte) => data + String.fromCharCode(byte), '')
                        )
                    })
                    .then(data => {
                        Vue.set(user_Info, 'imageShow', data)
                        // 更新localStorage
                        
                    })
                }
        return user_Info
}

async function apiUpdateAddress(state, userId) {
    let addressList = state.addressList.map(item => JSON.stringify(item)).join(';')
    if(addressList) {
       addressList += ';'
    }
    await api.user.updateAddress(userId, addressList)
}

    async function apiUpdateOrderDetail(state, userId, sales) {
        let bought = JSON.stringify(state.bought)
        let unreceived = JSON.stringify(state.unreceived)
        let unassess = JSON.stringify(state.unassess)
        await api.user.pay_success(userId, bought, unreceived, unassess, sales)
    }
