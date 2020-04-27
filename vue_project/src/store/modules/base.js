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
            console.info(page);
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
    actions: {}
}
export default base