//import {getInfo, login, logout} from '@/api/login'
import {
    default as api
} from '../../utils/api'
import store from '..'
import router from '../../router'
import axios from 'axios';

const practice = {
    state: {},
    mutations: {},
    getters: {},
    actions: {
        practiceList({
            commit,
            state
        }, page) {
            axios.defaults.headers['Authorization'] = `bearer ${page.Authorization}`
                //避免reload时没有参数
            return new Promise((resolve,reject) => {
                api({
                    url: `/practice/list?pageNo=${page.pageNo}&cateId=${page.cateId}&score=${page.score}&pageSize=${page.pageSize}`,
                    method: 'get'
                }).then(data => {
                    resolve(data)
                }).catch(error => {
                    reject(error)
                })
            })
        },
        savePractice({
            commit,
            state
        }, page) {
            axios.defaults.headers['Authorization'] = `bearer ${page.Authorization}`
            return new Promise((resolve,reject) => {
                api({
                    url: `/practice/save`,
                    method: 'post',
                    data: page
                }).then(data => {
                    resolve(data)
                }).catch(error => {
                    reject(error)
                })
            })
        },

    }
}
export default practice