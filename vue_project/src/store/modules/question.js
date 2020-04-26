//import {getInfo, login, logout} from '@/api/login'
import { default as api } from '../../utils/api'
import store from '../../store'
import router from '../../router'
import axios from 'axios';

const question = {
    state: {
      
    },
    mutations: {
       
    },
    actions: {
        questionList({ commit, state }, page) {
            axios.defaults.headers['Authorization'] = `bearer ${page.Authorization}`
            return new Promise((reject, resolve) => {
                api({
                    url: `/question/list?pageNo=${page.pageNo}&search=${page.search}&pageSize=${page.pageSize}`,
                    method: 'get'
                }).then(data => {
                    resolve(data)
                }).catch(error => {
                    reject(error)
                })
            })
        },
        createQuestion({ commit, state }, page) {
            axios.defaults.headers['Authorization'] = `bearer ${page.Authorization}`
            return new Promise((reject, resolve) => {
                api({
                    url: `/question/create`,
                    method: 'post',
                    // data: {"account": `${page.account}`,"email":`${page.email}`,"msisdn": `${page.msisdn}`,"pwd": `${page.pwd}`,"questionName": `${page.questionName}`}
                    data: page
                }).then(data => {
                    resolve(data)
                }).catch(error => {
                    reject(error)
                })
            })
        },
        updateQuestion({ commit, state }, page) {
            axios.defaults.headers['Authorization'] = `bearer ${page.Authorization}`
            return new Promise((reject, resolve) => {
                api({
                    url: `/question/update`,
                    method: 'post',
                    // data: {"account": `${page.account}`,"email":`${page.email}`,"msisdn": `${page.msisdn}`,"pwd": `${page.pwd}`,"questionName": `${page.questionName}`,"id": `${page.id}`}
                    data: page
                }).then(data => {
                    resolve(data)
                }).catch(error => {
                    reject(error)
                })
            })
        },
        deleteQuestion({ commit, state }, page) {
            axios.defaults.headers['Authorization'] = `bearer ${page.Authorization}`
            return new Promise((reject, resolve) => {
                api({
                    url: `/question/${page.id}/delete`,
                    method: 'post',
                }).then(data => {
                    resolve(data)
                }).catch(error => {
                    reject(error)
                })
            })
        },
        
        questionSerializable({ commit, state }, page) {
            axios.defaults.headers['Authorization'] = `bearer ${page.Authorization}`
            return new Promise((reject, resolve) => {
                api({
                    url: `/question/doSerializable`,
                    method: 'post',
                }).then(data => {
                    resolve(data)
                }).catch(error => {
                    reject(error)
                })
            })
        },
        questionImportExcel({commit,state}, page) {
            let Authorization = page.get("Authorization")
            axios.defaults.headers['Authorization']=`bearer ${Authorization}`
            return new Promise((reject, resolve) => {
              api({
                
                url: `/question/importExcel`,
                method: "post",
                data:page,
              }).then(data => {
                resolve(data);
              }).catch(err => {
                reject(err)
              })
            })
          },
       
    }
}
export default question