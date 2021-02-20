
// 获取Cookie
export function getCookie(c_name){
    if (c_name && document.cookie.length>0){
        let c = document.cookie
        let c_start=document.cookie.indexOf(c_name + "=")
        if (c_start!=-1){ 
            if(c_start>=1) {
                let y = document.cookie[c_start-1]
                let m = document.cookie[c_start-1]===' ' ||  document.cookie[c_start-1] === ';' ? true : false
                if(m) {
                    c_start=c_start + c_name.length+1 
                    let c_end=document.cookie.indexOf(";",c_start)
                    if (c_end==-1){
                        c_end=document.cookie.length
                    }
                    let aa = unescape(document.cookie.substring(c_start,c_end))
                    return aa
                }else {
                    return ''
                }
            }else {
                    c_start=c_start + c_name.length+1 
                    let c_end=document.cookie.indexOf(";",c_start)
                    if (c_end==-1){
                        c_end=document.cookie.length
                    }
                    let aa = unescape(document.cookie.substring(c_start,c_end))
                    return aa
            }
            
        }
    }
    return ''
}

// 设置Cookie
export const setCookie = (cName, value, expiredays) => {
    var exdate = new Date()
    exdate.setDate(exdate.getDate() + expiredays)
    document.cookie = cName + '=' + decodeURIComponent(value) + 
    ((expiredays == null) ? '' : ';expires=' + exdate.toGMTString())
}

// 删除Cookie
export const removeCookie = (cookieName) => {
    setCookie(cookieName, '', -1)
}