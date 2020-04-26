//import {getInfo, login, logout} from '@/api/login'
import { default as api } from '../../utils/api'
import store from '../../store'
import router from '../../router'
import axios from 'axios';

const user = {
    state: {
        nickname: "",
        userId: "",
        roles: null,
        menus: null,
        permissions: [],
        isShow: true
    },
    mutations: {
        changeIsShow(state) {
            state.isShow = !state.isShow;
        },
        SET_USER: (state, userInfo) => {
            state.nickname = userInfo.nickname;
            state.userId = userInfo.id;
            state.role = userInfo.roles;
            state.menus = userInfo.menus;
            state.permissions = userInfo.permissions;
        },
        RESET_USER: (state) => {
            state.nickname = "";
            state.userId = "";
            state.roles = [];
            state.menus = null;
            state.permissions = [];
        }
    },
    actions: {
        // 登录
        Login({ commit, state }, loginForm) {
            return new Promise((reject, resolve) => {

                resolve({ access_token: "123456", uname: "张三" })
                    // api({
                    //     url: `oauth/token?grant_type=password&username=${loginForm.username}&password=${loginForm.password}`,
                    //     method: "post",
                    // }).then(data => {
                    //     // if (data.result === "success") {
                    //     //   //cookie中保存前端登录状态
                    //     //   setToken();
                    //     // }
                    //     // console.log('success ====> ');
                    //     resolve(data);
                    // }).catch(err => {
                    //     // console.log('error ==== > '+JSON.stringify(err))
                    //     reject(err)
                    // })
            })
        },
        // 获取用户信息
        userList({ commit, state }, page) {
            axios.defaults.headers['Authorization'] = `bearer ${page.Authorization}`
            return new Promise((reject, resolve) => {

                api({
                    url: `dmp/user/list?pageNo=${page.pageNo}&pageSize=${page.pageSize}`,
                    method: 'get'
                }).then(data => {
                    // //储存用户信息
                    // commit('SET_USER', data.userInfo);
                    // //cookie保存登录状态,仅靠vuex保存的话,页面刷新就会丢失登录状态
                    // setToken();
                    // //生成路由
                    // let userMenus = data.userInfo.menus ;
                    // store.dispatch('GenerateRoutes', userMenus).then(() => {
                    //   //生成该用户的新路由json操作完毕之后,调用vue-router的动态新增路由方法,将新路由添加
                    //   router.addRoutes(store.getters.addRouters)
                    // })
                    resolve(data)
                }).catch(error => {
                    reject(error)
                })
            })
        },
        // 创建用户
        createUser({ commit, state }, page) {
            axios.defaults.headers['Authorization'] = `bearer ${page.Authorization}`
            return new Promise((reject, resolve) => {
                api({
                    url: `dmp/user/create`,
                    method: 'post',
                    // data: {"account": `${page.account}`,"email":`${page.email}`,"msisdn": `${page.msisdn}`,"pwd": `${page.pwd}`,"userName": `${page.userName}`}
                    data: page
                }).then(data => {
                    resolve(data)
                }).catch(error => {
                    reject(error)
                })
            })
        },
        // 修改用户
        updateUser({ commit, state }, page) {
            axios.defaults.headers['Authorization'] = `bearer ${page.Authorization}`
            return new Promise((reject, resolve) => {
                api({
                    url: `dmp/user/update`,
                    method: 'post',
                    // data: {"account": `${page.account}`,"email":`${page.email}`,"msisdn": `${page.msisdn}`,"pwd": `${page.pwd}`,"userName": `${page.userName}`,"userId": `${page.userId}`}
                    data: page
                }).then(data => {
                    resolve(data)
                }).catch(error => {
                    reject(error)
                })
            })
        },

        // 删除用户
        deleteUser({ commit, state }, page) {
            axios.defaults.headers['Authorization'] = `bearer ${page.Authorization}`
            return new Promise((reject, resolve) => {
                api({
                    url: `dmp/user/${page.userId}/delete`,
                    method: 'post',
                }).then(data => {
                    resolve(data)
                }).catch(error => {
                    reject(error)
                })
            })
        },
        // 角色列表
        roleList({ commit, state }, page) {
            axios.defaults.headers['Authorization'] = `bearer ${page.Authorization}`
            return new Promise((reject, resolve) => {
                api({
                    url: `dmp/role/list?pageNo=${page.pageNo}&pageSize=${page.pageSize}`,
                    method: 'get',
                }).then(data => {
                    resolve(data)
                }).catch(error => {
                    reject(error)
                })
            })
        },
        // 删除角色
        deleteRole({ commit, state }, page) {
            axios.defaults.headers['Authorization'] = `bearer ${page.Authorization}`
            return new Promise((reject, resolve) => {
                api({
                    url: `dmp/role/${page.id}/delete`,
                    method: 'post',
                }).then(data => {
                    resolve(data)
                }).catch(error => {
                    reject(error)
                })
            })
        },
        // 创建角色
        createRole({ commit, state }, page) {
            axios.defaults.headers['Authorization'] = `bearer ${page.Authorization}`
            return new Promise((reject, resolve) => {
                api({
                    url: `dmp/role/create?name=${page.name}`,
                    method: 'post',
                }).then(data => {
                    resolve(data)
                }).catch(error => {
                    reject(error)
                })
            })
        },
        // 修改角色
        updateRole({ commit, state }, page) {
            axios.defaults.headers['Authorization'] = `bearer ${page.Authorization}`
            return new Promise((reject, resolve) => {
                api({
                    url: `dmp/role/updateRole`,
                    method: 'post',
                    data: { "name": `${page.name}`, "id": `${page.id}` }
                }).then(data => {
                    resolve(data)
                }).catch(error => {
                    reject(error)
                })
            })
        },
        // 修改 密码
        userSetPassword({ commit, state }, page) {
            axios.defaults.headers['Authorization'] = `bearer ${page.Authorization}`
            return new Promise((reject, resolve) => {
                api({
                    url: `dmp/user/${page.id}/setPassword`,
                    method: 'post',
                    data: page.form
                }).then(data => {
                    resolve(data)
                }).catch(error => {
                    reject(error)
                })
            })
        },
        // 查询用户名是否重复
        loadByUserName({ commit, state }, page) {
            axios.defaults.headers['Authorization'] = `bearer ${page.Authorization}`
            return new Promise((reject, resolve) => {
                api({
                    url: `dmp/user/loadByUserName?userName=${page.userName}`,
                    method: 'get',
                }).then(data => {
                    resolve(data)
                }).catch(error => {
                    reject(error)
                })
            })
        },
        // 首页统计数据
        homeAdmin({ commit, state }, page) {
            axios.defaults.headers['Authorization'] = `bearer ${page.Authorization}`
            return new Promise((reject, resolve) => {
                api({
                    url: `dmp/home/admin`,
                    method: 'get',
                }).then(data => {
                    resolve(data)
                }).catch(error => {
                    reject(error)
                })
            })
        },
        // 首页统计图表数据
        telemetryAttributesStatisByDay({ commit, state }, page) {
            axios.defaults.headers['Authorization'] = `bearer ${page.Authorization}`
            return new Promise((reject, resolve) => {
                api({
                    url: `dmp/telemetryAttributes/statisByDay?startTime=${page.startTime}&endTime=${page.endTime}`,
                    method: 'get',
                }).then(data => {
                    resolve(data)
                }).catch(error => {
                    reject(error)
                })
            })
        },
        //按属性名称统计遥测数量(统计分析页面)
        statisByDevAndAttributeName({ commit, state }, page) {
            axios.defaults.headers['Authorization'] = `bearer ${page.Authorization}`
            return new Promise((reject, resolve) => {
                api({
                    url: `dmp/telemetryAttributes/${page.devId}/statisByDevAndAttributeName?startDate=${page.startDate}&endDate=${page.endDate}&attributeName=${page.attributeName}`,
                    method: 'get',
                }).then(data => {
                    resolve(data)
                }).catch(error => {
                    reject(error)
                })
            })
        },
        // // 登出
        // LogOut({commit}) {
        //   return new Promise((resolve) => {
        //     api({
        //       url: "login/logout",
        //       method: "post"
        //     }).then(data => {
        //       commit('RESET_USER')
        //       removeToken()
        //       resolve(data);
        //     }).catch(() => {
        //       commit('RESET_USER')
        //       removeToken()
        //     })
        //   })
        // },
        // // 前端 登出
        // FedLogOut({commit}) {
        //   return new Promise(resolve => {
        //     commit('RESET_USER')
        //     removeToken()
        //     resolve()
        //   })
        // }
    }
}
export default user