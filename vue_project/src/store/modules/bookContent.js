//import {getInfo, login, logout} from '@/api/login'
import { default as api } from '../../utils/api'
import store from '..'
import router from '../../router'
import axios from 'axios';

const bookContent = {
    state: {

    },
    mutations: {

    },
    actions: {
        bookContentList({ commit, state }, page) {
            axios.defaults.headers['Authorization'] = `bearer ${page.Authorization}`
                //避免reload时没有参数
            if (!page.pageNo) {
                page.pageNo = 1;
            }
            if (!page.pageSize) {
                page.pageSize = 20;
            }
            return new Promise((reject, resolve) => {
                api({
                    url: `/bookContent/list?pageNo=${page.pageNo}&bookId=${page.bookId}&chapterId=${page.chapterId}&search=${page.search}&pageSize=${page.pageSize}`,
                    method: 'get'
                }).then(data => {
                    resolve(data)
                }).catch(error => {
                    reject(error)
                })
            })
        },
        createBookContent({ commit, state }, page) {
            axios.defaults.headers['Authorization'] = `bearer ${page.Authorization}`
            return new Promise((reject, resolve) => {
                api({
                    url: `/bookContent/create`,
                    method: 'post',
                    // data: {"account": `${page.account}`,"email":`${page.email}`,"msisdn": `${page.msisdn}`,"pwd": `${page.pwd}`,"bookContentName": `${page.bookContentName}`}
                    data: page
                }).then(data => {
                    resolve(data)
                }).catch(error => {
                    reject(error)
                })
            })
        },
        updateBookContent({ commit, state }, page) {
            axios.defaults.headers['Authorization'] = `bearer ${page.Authorization}`
            return new Promise((reject, resolve) => {
                api({
                    url: `/bookContent/update`,
                    method: 'post',
                    // data: {"account": `${page.account}`,"email":`${page.email}`,"msisdn": `${page.msisdn}`,"pwd": `${page.pwd}`,"bookContentName": `${page.bookContentName}`,"id": `${page.id}`}
                    data: page
                }).then(data => {
                    resolve(data)
                }).catch(error => {
                    reject(error)
                })
            })
        },
        deleteBookContent({ commit, state }, page) {
            axios.defaults.headers['Authorization'] = `bearer ${page.Authorization}`
            return new Promise((reject, resolve) => {
                api({
                    url: `/bookContent/${page.id}/delete`,
                    method: 'post',
                }).then(data => {
                    resolve(data)
                }).catch(error => {
                    reject(error)
                })
            })
        },

        bookContentSerializable({ commit, state }, page) {
            axios.defaults.headers['Authorization'] = `bearer ${page.Authorization}`
            return new Promise((reject, resolve) => {
                api({
                    url: `/bookContent/doSerializable`,
                    method: 'post',
                }).then(data => {
                    resolve(data)
                }).catch(error => {
                    reject(error)
                })
            })
        },
        bookContentImportExcel({ commit, state }, page) {
            let Authorization = page.get("Authorization")
            axios.defaults.headers['Authorization'] = `bearer ${Authorization}`
            return new Promise((reject, resolve) => {
                api({
                    url: `/bookContent/importExcel`,
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
export default bookContent