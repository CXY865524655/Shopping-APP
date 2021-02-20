const baseURL = '/user'
const user = {
    checkUser: `${baseURL}/checkUser`,
    insertUser: `${baseURL}/insertUser`,
    checkTel: `${baseURL}/checkTel`,
    updateUserName: `${baseURL}/updateUserName`,
    updateUserSex: `${baseURL}/updateUserSex`,
    updateUserBirthday: `${baseURL}/updateUserBirthday`,
    hasPayPwd: `${baseURL}/hasPayPwd`, 
    setPayPwd: `${baseURL}/setPayPwd`, 
    initOrderDetail: `${baseURL}/initOrderDetail`,
    searchUsers: `${baseURL}/searchUsers`,
    delUser:`${baseURL}/delUser`,
    initUsers: `${baseURL}/initUsers`,
    getUserImg:`${baseURL}/getUserImg`,
    delUserImgPath:`${baseURL}/delUserImgPath`,
    saveUser:`${baseURL}/saveUser`,
    saveUserImg:`${baseURL}/saveUserImg`,
    updateUserImgPath:`${baseURL}/updateUserImgPath`,
    adminLogin:`${baseURL}/adminLogin`
}
export default user