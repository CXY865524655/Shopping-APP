import baseURL from '@/config/API/baseURL'
import axios from '@/config/request/http2.js'
import qs from 'qs'
const goods = {
    getGoodsList(){
        return axios.get(`${baseURL.goods.getGoodsList}`)
    },

    initCategory() {
        return axios.get(`${baseURL.goods.initCategory}`)
    },

    getSubProductList(categoryId, subCategoryId) {
        return axios.post(`${baseURL.goods.getSubProductList}`, qs.stringify({
            categoryId,
            subCategoryId
        }))
    },
    searchGoods(searchContent,condition1) {
        return axios.post(`${baseURL.goods.searchGoods}`, qs.stringify({
            searchContent,
            condition1
        }))
    },

    sendAssess(goodsid, assess, num, buyNum) {
        return axios.post(`${baseURL.goods.sendAssess}`, qs.stringify({
            goodsid,
            assess,
            num,
            buyNum
        }))
    },

    getGoodAssess(goodsid) {
        return axios.post(`${baseURL.goods.getGoodAssess}`, qs.stringify({
            goodsid
        }))
    },

    initHomeData() {
        return axios.get(`${baseURL.goods.initHomeData}`)
    },
    
    getGoodImg(file){
        return axios.post(`${baseURL.goods.getGoodImg}`,qs.stringify({
            path: file
        }),
        {
            responseType: "arraybuffer",   // 返回图片必加
        })
    },

    getDetailImg(file){
        return axios.post(`${baseURL.goods.getDetailImg}`,qs.stringify({
            path: file
        }),
        {
            responseType: "arraybuffer",   // 返回图片必加
        })
    },

    

    getCategory(num) {
        return axios.post(`${baseURL.goods.getCategory}`,qs.stringify({
            num
        }))
    },

    
    

    
}

export default goods