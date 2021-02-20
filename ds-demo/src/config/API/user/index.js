import baseURL from '@/config/API/baseURL'
import axios from '@/config/request/http2.js'
import qs from 'qs'

const user = {
    // login(username, password){
    //     return axios.post(`${baseURL.user}`, qs.stringify({
    //         username,
    //         password
    //     }))
    // }


    getUserImg(imgPath){
        return axios.post(`${baseURL.user.getUserImg}`,qs.stringify({
            imgPath
        }),
        {
            responseType: "arraybuffer",   // 返回图片必加
        })
    },

    
    getUserNameByID(userid){
        return axios.post(`${baseURL.user.getUserNameByID}`,qs.stringify({
            userid
        }))
    },
    saveUserImg(file){
        return axios.post(`${baseURL.user.saveUserImg}`,file)
    },

    updateUserImgPath(path, userid){
        return axios.post(`${baseURL.user.updateUserImgPath}`,qs.stringify({
            path,
            userid
        }))
    },

    delUserImgPath(userid){
        return axios.post(`${baseURL.user.delUserImgPath}`,qs.stringify({
            userid
        }))
    },

    loginSendCode(telephone) {
        return axios.post(`${baseURL.user.loginSendCode}`, qs.stringify({
            telephone
        }))
    },

    registerSendCode(telephone) {
        return axios.post(`${baseURL.user.registerSendCode}`, qs.stringify({
            telephone
        }))
    },

    checkRegisterCode(telephone, code, relCode) {
        return axios.post(`${baseURL.user.checkRegisterCode}`, qs.stringify({
            telephone,
            code,
            relCode
        }))
    },

    checkUser(username) {
        return axios.post(`${baseURL.user.checkUser}`, qs.stringify({
            username
        }))
    },

    cartAdd(userId, cart) {
        return axios.post(`${baseURL.user.cartAdd}`, qs.stringify({
            userId,
            cart
        }))
    },

    cartDel(userid, cart) {
        return axios.post(`${baseURL.user.cartDel}`, qs.stringify({
            userid,
            cart
        }))
    },

    getCartItem(userid, goodsId) {
        return axios.post(`${baseURL.user.getCartItem}`, qs.stringify({
            userid,
            goodsId
        }))
    },

    

    removePro(userid, selecteds) {
        return axios.post(`${baseURL.user.removePro}`, qs.stringify({
            userid,
            selecteds
        }))
    },
    

    checkPayPwd(id, pwd) {
        return axios.post(`${baseURL.user.checkPayPwd}`, qs.stringify({
            id,
            pwd
        }))
    },

    insertUser(telephone, username, password) {
        return axios.post(`${baseURL.user.insertUser}`, qs.stringify({
            telephone, 
            username, 
            password
        }))
    },
    
    checkTel(telephone) {
        return axios.post(`${baseURL.user.checkTel}`, qs.stringify({
            telephone
        }))
    },

    loginByAccount(username, password) {
        return axios.post(`${baseURL.user.loginByAccount}`, qs.stringify({
            username,
            password
        }))
    },

    loginByTel(telephone, code) {
        return axios.post(`${baseURL.user.loginByTel}`, qs.stringify({
            telephone,
            code
        }))
    },
    getMessage() {
        return axios.post(`${baseURL.user.getMessage}`)
    },

    login(user) {
        return axios.post(`${baseURL.user.login}`, qs.stringify({
            'user': JSON.stringify(user)
        }))
    },
    logOut() {
        return axios.get(`${baseURL.user.logOut}`)
    },
    test() {
        return axios.post('/user/test')
    },

    updateUserName(username, id) {
        return axios.post(`${baseURL.user.updateUserName}`, qs.stringify({
            username,
            id
        }))
    },

    updateUserSex(id, sex) {
        return axios.post(`${baseURL.user.updateUserSex}`, qs.stringify({
            id,
            sex
        }))
    },

    cart_num_up1(userid, goodsid) {
        return axios.post(`${baseURL.user.cart_num_up1}`, qs.stringify({
            userid,
            goodsid
        }))
    },
    


    updateUserBirthday(id, birthday) {
        return axios.post(`${baseURL.user.updateUserBirthday}`, qs.stringify({
            id,
            birthday
        }))
    },
    searchUsers(content){
        return axios.post(`${baseURL.user.searchUsers}`, qs.stringify({
            content
        }))
    },

    insertAddress(userId, address) {
        return axios.post(`${baseURL.user.insertAddress}`, qs.stringify({
            userId,
            address
        }))
    },

    addAddress(userId, address) {
        return axios.post(`${baseURL.user.addAddress}`, qs.stringify({
            userId,
            address
        }))
    },

    ediAddress(userid, address) {
        return axios.post(`${baseURL.user.ediAddress}`, qs.stringify({
            userid,
            address
        }))
    },
    
    delAddress(userid, id) {
        return axios.post(`${baseURL.user.delAddress}`, qs.stringify({
            userid,
            id
        }))
    },
    
    getAddressList(userId) {
        return axios.post(`${baseURL.user.getAddressList}`, qs.stringify({
            userId
        }))
    },

    updateAddress(userId, addressList) {
        return axios.post(`${baseURL.user.updateAddress}`, qs.stringify({
            userId,
            addressList
        }))
    },

    setUserCollects(userId, collect) {
        return axios.post(`${baseURL.user.setUserCollects}`, qs.stringify({
            userId,
            collect
        }))
    },
    setUserCart(userId, cart) {
        return axios.post(`${baseURL.user.setUserCart}`, qs.stringify({
            userId,
            cart
        }))
    },
    
    getCollectList(collectStr) {
        return axios.post(`${baseURL.user.getCollectList}`, qs.stringify({
            collectStr
        }))
    },

    getCartList(userid) {
        return axios.post(`${baseURL.user.getCartList}`, qs.stringify({
            userid
        }))
    },

    hasPayPwd(id){
        return axios.post(`${baseURL.user.hasPayPwd}`, qs.stringify({
            userID: id
        }))
    },

    setPayPwd(id, payPwd){
        return axios.post(`${baseURL.user.setPayPwd}`, qs.stringify({
            userID: id,
            payPwd
        }))
    },

    checkStar(userId, goodsid) {
        return axios.post(`${baseURL.user.checkStar}`, qs.stringify({
            userId,
            goodsid
        }))
    },

    sendByMail(tel, msg) {
        return axios.post(`${baseURL.user.sendByMail}`, qs.stringify({
            tel,
            msg
        }))
    },
    

    pay_success(userId, bought, unreceived, unassess, selecteds) {
        return axios.post(`${baseURL.user.pay_success}`, qs.stringify({
            userId,
            bought,
            unreceived,
            unassess,
            selecteds: JSON.stringify(selecteds)
        }))
    },

    initOrderDetail(userId) {
        return axios.post(`${baseURL.user.initOrderDetail}`, qs.stringify({
            userId
        }))
    },

    set_unassess({
        userId,
        unassess
    }) {
        return axios.post(`${baseURL.user.set_unassess}`, qs.stringify({
            userId,
            unassess
        }))
    },

    updateCart(userid, selecteds) {
        return axios.post(`${baseURL.user.updateCart}`, qs.stringify({
            userid,
            selecteds
        }))
    },
    
    
    

    

}

export default user