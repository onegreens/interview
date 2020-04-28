//import {getInfo, login, logout} from '@/api/login'
import {
    default as api
} from '../../utils/api'
import store from '..'
import router from '../../router'
import axios from 'axios';

const bookChapter = {
    state: {
        chapterId: "",
        chapterName: "",
    },
    mutations: {
        SET_BOOK_CHAPTER: (state, chapter) => {
            state.chapterId = chapter.id;
            state.chapterName = chapter.name;
        }
    },
    getters: {
        getChapterId(state) {
            return state.chapterId
        },
        getChapterName(state) {
            return state.chapterName
        }
    },
    actions: {
        bookChapterList({
            commit,
            state
        }, page) {
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
                    url: `/bookChapter/list?pageNo=${page.pageNo}&search=${page.search}&bookId=${page.bookId}&pageSize=${page.pageSize}`,
                    method: 'get'
                }).then(data => {
                    resolve(data)
                }).catch(error => {
                    reject(error)
                })
            })
        },
        bookChapterTreeData({
            commit,
            state
        }, page) {
            axios.defaults.headers['Authorization'] = `bearer ${page.Authorization}`
            return new Promise((reject, resolve) => {
                api({
                    url: `/bookChapter/treeData?bookId=${page.bookId}`,
                    method: 'get'
                }).then(data => {
                    resolve(data)
                }).catch(error => {
                    reject(error)
                })
            })
        },
        createBookChapter({
            commit,
            state
        }, page) {
            axios.defaults.headers['Authorization'] = `bearer ${page.Authorization}`
            return new Promise((reject, resolve) => {
                api({
                    url: `/bookChapter/create`,
                    method: 'post',
                    // data: {"account": `${page.account}`,"email":`${page.email}`,"msisdn": `${page.msisdn}`,"pwd": `${page.pwd}`,"bookChapterName": `${page.bookChapterName}`}
                    data: page
                }).then(data => {
                    resolve(data)
                }).catch(error => {
                    reject(error)
                })
            })
        },
        updateBookChapter({
            commit,
            state
        }, page) {
            axios.defaults.headers['Authorization'] = `bearer ${page.Authorization}`
            return new Promise((reject, resolve) => {
                api({
                    url: `/bookChapter/update`,
                    method: 'post',
                    data: page
                }).then(data => {
                    resolve(data)
                }).catch(error => {
                    reject(error)
                })
            })
        },
        deleteBookChapter({
            commit,
            state
        }, page) {
            axios.defaults.headers['Authorization'] = `bearer ${page.Authorization}`
            return new Promise((reject, resolve) => {
                api({
                    url: `/bookChapter/${page.id}/delete`,
                    method: 'post',
                }).then(data => {
                    resolve(data)
                }).catch(error => {
                    reject(error)
                })
            })
        },

        bookChapterSerializable({
            commit,
            state
        }, page) {
            axios.defaults.headers['Authorization'] = `bearer ${page.Authorization}`
            return new Promise((reject, resolve) => {
                api({
                    url: `/bookChapter/doSerializable`,
                    method: 'post',
                }).then(data => {
                    resolve(data)
                }).catch(error => {
                    reject(error)
                })
            })
        },
        bookChapterImportExcel({
            commit,
            state
        }, page) {
            let Authorization = page.get("Authorization")
            axios.defaults.headers['Authorization'] = `bearer ${Authorization}`
            return new Promise((reject, resolve) => {
                api({

                    url: `/bookChapter/importExcel`,
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
export default bookChapter