import {
    default as api
} from '../../utils/api'
import store from '../../store'
import router from '../../router'
import axios from 'axios';

const login = {
    state: {
        token: '',
        roles: null,
        isMasterAccount:true,
      },
      mutations: {
        SET_TOKEN: (state, token) => {
          state.token ="Bearer " +token 
        },
      },
    actions:{
        Login({commit,state},userInfo){
            axios.defaults.headers['Authorization'] = `bearer ${page.Authorization}`
        
            return new Promise((reject,resolve)=>{
                api({
                    url: `/user/login?username=${userInfo.username}&password=${userInfo.password}`,
                    method: 'post'
                }).then(data=>{
                    commit('SET_TOKEN', data.token);
                    resolve(data)
                }).catch(erro=>{
                    reject(error)
                })
            })
        }
    }
}