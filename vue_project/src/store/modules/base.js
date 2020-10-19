//import {getInfo, login, logout} from '@/api/login'
import {
    default as api
} from '../../utils/api'
import store from '..'
import router from '../../router'
import axios from 'axios';

const base = {
    state: {
        pageNo: 1,
        pageSize: 20,
    },
    mutations: {
        SET_PAGE: (state, page) => {
            state.pageNo = page.pageNo;
            state.pageSize = page.pageSize;
        },
    },
    getters: {
        getPageNo(state) {
            return state.pageNo
        },
        getPageSize(state) {
            return state.pageSize
        }
    },
    actions: {
        toJson({
            commit,
            state
        }, params) {
            return new Promise((resolve, reject) => {
                api({
                    url: `/util/doJson?alias=`+params.alias,
                    method: 'get'
                }).then(data => {
                    resolve(data)
                }).catch(error => {
                    reject(error)
                })
            })
        },
        uploadImage({ commit, state }, page) {
            let Authorization = page.get("Authorization")
            axios.defaults.headers['Authorization'] = `bearer ${Authorization}`
            return new Promise((resolve,reject) => {
                api({
                    url: `/util/uploadImage`,
                    method: "post",
                    data: page,
                }).then(data => {
                    resolve(data);
                }).catch(err => {
                    reject(err)
                })
            })
        },
    }
}
export default base