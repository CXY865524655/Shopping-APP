import axios from '@/config/request/http2.js'
import api from '@/config/API'
import context from '@/main.js'

// 更新用户信息--NAME
export async function  change_username ({commit, state}, {
    username,
    id
})  {
    return new Promise((resolve, reject) => {
        api.user.updateUserName(username,id)
         .then(res => {
            //  debugger
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
         .catch(err => {
             return err
         })
    })
}

export async function change_sex({commit, state}, {
    id,
    sex
}){
    return new Promise((resolve, reject) => {
        debugger
        api.user.updateUserSex(id, sex)
        .then(res => {
            debugger
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

export async function change_birthday({commit, state}, {
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

function updateUserInfo(res, commit) {
    const token =  res.data.data.token
    const user_Info = res.data.data.user_Info
    // debugger
    // 更新localStorage
    localStorage.setItem('token', 'Bearer ' + token)
    axios.defaults.headers.common['Authorization'] = token
    // debugger
    const data = {
        'token': token,
        'user': user_Info,
    }
    // 更新状态管理 =》 token，user
    commit('AUTH_SUCCESS', data)
}





