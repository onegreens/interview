//import {getInfo, login, logout} from '@/api/login'
import {
  default as api
} from '../../utils/api'
import store from '../../store'
import router from '../../router'
import axios from 'axios';

const menu = {
  state: {},
  mutations: {

  },
  actions: {
    // 获取角色菜单列表
    menuList({commit,state}, page) {
    axios.defaults.headers['Authorization']=`bearer ${page.Authorization}`
      return new Promise((reject, resolve) => {
        api({
          url: `dmp/menu/getMenuByRole?pageNo=${page.pageNo}&pageSize=${page.pageSize}`,
          method: "get"
        }).then(data => {
          resolve(data);
        }).catch(err => {
          reject(err)
        })
      })
    },
    //创建菜单
    createMenu({commit,state}, page) {
      axios.defaults.headers['Authorization']=`bearer ${page.Authorization}`
        return new Promise((reject, resolve) => {
          api({
            url: `dmp/menu/create`,
            method: "post",
            data:{"name": `${page.name}`,"icon": `${page.icon}`,"url": `${page.url}`}
          }).then(data => {
            resolve(data);
          }).catch(err => {
            reject(err)
          })
        })
      },
      //删除菜单
      deleteMenu({commit,state}, page) {
        axios.defaults.headers['Authorization']=`bearer ${page.Authorization}`
          return new Promise((reject, resolve) => {
            api({
              url: `dmp/menu/${page.id}/delete`,
              method: "post",
            }).then(data => {
              resolve(data);
            }).catch(err => {
              reject(err)
            })
          })
        },
        //修改菜单
      updateMenu({commit,state}, page) {
        axios.defaults.headers['Authorization']=`bearer ${page.Authorization}`
          return new Promise((reject, resolve) => {
            api({
              url: `dmp/menu/update`,
              method: "post",
              data:{"id": `${page.id}`,"name": `${page.name}`,"icon": `${page.icon}`,"url": `${page.url}`}
            }).then(data => {
              resolve(data);
            }).catch(err => {
              reject(err)
            })
          })
        },
         //绑定菜单到角色
         bindMenu({commit,state}, page) {
           let menuIdList = `${page.menuIdList}`
        axios.defaults.headers['Authorization']=`bearer ${page.Authorization}`
          return new Promise((reject, resolve) => {
            api({
              url: `dmp/menu/${page.roleId}/bindMenu?menuIdList=${menuIdList}`,
              method: "post",
            }).then(data => {
              resolve(data);
            }).catch(err => {
              reject(err)
            })
          })
        },
        //菜单列表
        Menu({commit,state}, page) {
          axios.defaults.headers['Authorization']=`bearer ${page.Authorization}`
            return new Promise((reject, resolve) => {
              api({
                url: `dmp/menu/list?pageNo=${page.pageNo}&pageSize=${page.pageSize}`,
                method: "get",
              }).then(data => {
                resolve(data);
              }).catch(err => {
                reject(err)
              })
            })
          },
  }
}
export default menu
