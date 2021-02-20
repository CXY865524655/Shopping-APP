import baseURL from '@/config/API/baseURL'
import axios from '@/config/request/http2.js'
import qs from 'qs'
const goods = {

    initOrder(){
        return axios.get(`${baseURL.goods.initOrder}`)
    },

    initAdminGoods(){
        return axios.get(`${baseURL.goods.initAdminGoods}`)
    },

    delGood(goodsid){
        return axios.post(`${baseURL.goods.delGood}`,qs.stringify({
            goodsid
        }))
    },

    saveGood(goodDetail){
        let g = JSON.parse(JSON.stringify(goodDetail))
        return axios.post(`${baseURL.goods.saveGood}`,qs.stringify({
            goodDetail: JSON.stringify(g)
        }))
    },

    saveOrder(orderDetail){
        let g = JSON.parse(JSON.stringify(orderDetail))
        return axios.post(`${baseURL.goods.saveOrder}`,qs.stringify({
            orderDetail: JSON.stringify(g)
        }))
    },
    

    addGood(goodDetail){
        let g = JSON.parse(JSON.stringify(goodDetail))
        return axios.post(`${baseURL.goods.addGood}`,qs.stringify({
            goodDetail: JSON.stringify(g)
        }))
    },

    saveGoodImg(file){
        return axios.post(`${baseURL.goods.saveGoodImg}`,file)
    },

    saveDetail(file){
        return axios.post(`${baseURL.goods.saveDetail}`,file)
    },

    updateGoodImgPath(filePath, goodid){
        return axios.post(`${baseURL.goods.updateGoodImgPath}`,qs.stringify({
            filePath,
            goodid
        }))
    },

    updateDetailPath(filePath, goodid){
        return axios.post(`${baseURL.goods.updateDetailPath}`,qs.stringify({
            filePath,
            goodid
        }))
    },
    

    delGoodImgPath(path, goodid){
        return axios.post(`${baseURL.goods.delGoodImgPath}`,qs.stringify({
            path,
            goodid
        }))
    },

    delGoodImgPathDetail(path, goodid){
        return axios.post(`${baseURL.goods.delGoodImgPathDetail}`,qs.stringify({
            path,
            goodid
        }))
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
    adminSearchGoods(content){
        return axios.post(`${baseURL.goods.adminSearchGoods}`,qs.stringify({
            content
        }))
    },

    searchOrder(content){
        return axios.post(`${baseURL.goods.searchOrder}`,qs.stringify({
            content
        }))
    },
    
    delOrder(userid, orderID, orderStatus, goodname){
        return axios.post(`${baseURL.goods.delOrder}`,qs.stringify({
            userid,
            orderID,
            orderStatus,
            goodname
        }))
    },
}

export default goods
