import baseURL from '@/config/API/baseURL'
import axios from '@/config/request/http2.js'
import qs from 'qs'

const user = {
    // 
    checkUser(username) {
        return axios.post(`${baseURL.user.checkUser}`, qs.stringify({
            username
        }))
    },

    // 
    insertUser(telephone, username, password) {
        return axios.post(`${baseURL.user.insertUser}`, qs.stringify({
            telephone, 
            username, 
            password
        }))
    },
    //
    checkTel(telephone) {
        return axios.post(`${baseURL.user.checkTel}`, qs.stringify({
            telephone
        }))
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

    updateUserBirthday(id, birthday) {
        return axios.post(`${baseURL.user.updateUserBirthday}`, qs.stringify({
            id,
            birthday
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

    initOrderDetail(userId) {
        return axios.post(`${baseURL.user.initOrderDetail}`, qs.stringify({
            userId
        }))
    },

    adminLogin(name, pwd) {
        return axios.post(`${baseURL.user.adminLogin}`, qs.stringify({
            name,
            pwd
        }))
    },
    
    initUsers(){
        return axios.get(`${baseURL.user.initUsers}`)
    },

    getUserImg(imgPath){
        return axios.post(`${baseURL.user.getUserImg}`,qs.stringify({
            imgPath
        }),
        {
            responseType: "arraybuffer",   // 返回图片必加
        })
    },

    delUserImgPath(userid){
        return axios.post(`${baseURL.user.delUserImgPath}`,qs.stringify({
            userid
        }))
    },

    delUser(userid){
        return axios.post(`${baseURL.user.delUser}`,qs.stringify({
            userid
        }))
    },
    

    saveUser(userDetail){
        return axios.post(`${baseURL.user.saveUser}`,qs.stringify({
            user:JSON.stringify(userDetail)
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
    searchUsers(content){
        return axios.post(`${baseURL.user.searchUsers}`, qs.stringify({
            content
        }))
    },
}

export default user