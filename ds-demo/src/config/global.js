// 本地化删除
export const removeLocalStore = (name) => {
    if (!name) return;
    return window.localStorage.removeItem(name);
}

// 根据name读取cookie
export const getCookie = (name) => {
    var arr = document.cookie.replace(/\s/g, "").split(';');
    for (var i = 0; i < arr.length; i++) {
        var tempArr = arr[i].split('=');
        if (tempArr[0] == name) {
            return decodeURIComponent(tempArr[1]);
        }
    }
    return '';
}

// 设置Cookie
export const setCookie = (name, value, days) => {
    var date = new Date();
    date.setDate(date.getDate() + days);
    document.cookie = name + '=' + value + ';expires=' + date;
}

// 根据name删除cookie
export const removeCookie = (name) => {
    // 设置已过期，系统会立刻删除cookie
    setCookie(name, '1', -1);
}
