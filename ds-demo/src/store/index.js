import Vue from 'vue'
import Vuex from 'vuex'
import * as getters from './getters'
import mutations from './mutations'
import * as actions from './actions'
import * as state from './state'
import createPersistedState from "vuex-persistedstate"

Vue.use(Vuex)


const store = new Vuex.Store({
    state,  
    getters,
    mutations,
    actions,
    plugins: [createPersistedState(
        {
            storage: window.localStorage,
            reducer(data) {
                return {
                    token: data.token,
                    user: data.user,
                    shippingAddress: data.shippingAddress,
                    lastLoginUser: data.lastLoginUser,
                    loading: data.loading,
                    addressList: data.addressList,
                    collectList: data.collectList,
                    goodDetail: data.goodDetail,
                    categoryNow: data.categoryNow,
                    cart: data.cart,
                    orderList: data.orderList,
                    bought: data.bought,
                    unpaid: data.unpaid,
                    unreceived: data.unreceived,
                    unassess: data.unassess,
                    active: data.active
                }
            }
        }
    )]
});

export default store
