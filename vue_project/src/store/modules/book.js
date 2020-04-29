//import {getInfo, login, logout} from '@/api/login'
import {
    default as api
} from '../../utils/api'
import store from '..'
import router from '../../router'
import axios from 'axios';

const book = {
    state: {
        bookId: "",
        bookName: "",
    },
    mutations: {
        SET_BOOK: (state, book) => {
            state.bookId = book.id;
            state.bookName = book.name;
        }
    },
    getters: {
        getBookId(state) {
            return state.bookId
        },
        getBookName(state) {
            return state.bookName
        }
    },
    actions: {
        bookList({
            commit,
            state
        }, page) {
            axios.defaults.headers['Authorization'] = `bearer ${page.Authorization}`
                //避免reload时没有参数
            page.pageNo = store.getters.getPageNo;
            page.pageSize = store.getters.getPageSize;
            return new Promise((reject, resolve) => {
                api({
                    url: `/book/list?pageNo=${page.pageNo}&search=${page.search}&pageSize=${page.pageSize}`,
                    method: 'get'
                }).then(data => {
                    resolve(data)
                }).catch(error => {
                    reject(error)
                })
            })
        },
        createBook({
            commit,
            state
        }, page) {
            axios.defaults.headers['Authorization'] = `bearer ${page.Authorization}`
            return new Promise((reject, resolve) => {
                api({
                    url: `/book/create`,
                    method: 'post',
                    // data: {"account": `${page.account}`,"email":`${page.email}`,"msisdn": `${page.msisdn}`,"pwd": `${page.pwd}`,"bookName": `${page.bookName}`}
                    data: page
                }).then(data => {
                    resolve(data)
                }).catch(error => {
                    reject(error)
                })
            })
        },
        updateBook({
            commit,
            state
        }, page) {
            axios.defaults.headers['Authorization'] = `bearer ${page.Authorization}`
            return new Promise((reject, resolve) => {
                api({
                    url: `/book/update`,
                    method: 'post',
                    // data: {"account": `${page.account}`,"email":`${page.email}`,"msisdn": `${page.msisdn}`,"pwd": `${page.pwd}`,"bookName": `${page.bookName}`,"id": `${page.id}`}
                    data: page
                }).then(data => {
                    resolve(data)
                }).catch(error => {
                    reject(error)
                })
            })
        },
        deleteBook({
            commit,
            state
        }, page) {
            axios.defaults.headers['Authorization'] = `bearer ${page.Authorization}`
            return new Promise((reject, resolve) => {
                api({
                    url: `/book/${page.id}/delete`,
                    method: 'post',
                }).then(data => {
                    resolve(data)
                }).catch(error => {
                    reject(error)
                })
            })
        },
        bookToMD({
            commit,
            state
        }, page) {
            axios.defaults.headers['Authorization'] = `bearer ${page.Authorization}`
            return new Promise((reject, resolve) => {
                api({
                    url: `/book/toMDFile?bookId=${page.bookId}`,
                    method: 'get',
                }).then(data => {
                    resolve(data)
                }).catch(error => {
                    reject(error)
                })
            })
        },
        bookSerializable({
            commit,
            state
        }, page) {
            axios.defaults.headers['Authorization'] = `bearer ${page.Authorization}`
            return new Promise((reject, resolve) => {
                api({
                    url: `/book/doSerializable`,
                    method: 'post',
                }).then(data => {
                    resolve(data)
                }).catch(error => {
                    reject(error)
                })
            })
        },
        bookImportExcel({
            commit,
            state
        }, page) {
            let Authorization = page.get("Authorization")
            axios.defaults.headers['Authorization'] = `bearer ${Authorization}`
            return new Promise((reject, resolve) => {
                api({

                    url: `/book/importExcel`,
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
export default book