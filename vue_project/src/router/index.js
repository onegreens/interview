import Vue from 'vue'
import Router from 'vue-router'
import user from '../components/admin.vue'
//登录页面 登路,注册,忘记密码
import login from '../components/account/login/Login.vue'
import register from '../components/account/login/Register.vue'
import forgit from '../components/account/login/Forgit.vue'
import dxlogin from '../components/account/login/dxLogin.vue'
//右上角 用户信息,
import changepassword from '../components/account/userR_hight/ChangePassword.vue'
import userLook from '../components/account/userR_hight/User.vue'

//左侧二级菜单栏
import manager from '../components/account/menu/manager.vue'
import question from '../components/account/menu/question.vue'
import exam from '../components/account/menu/exam.vue'
import book from '../components/account/menu/book.vue'
import bookChapter from '../components/account/menu/bookChapter.vue'
import bookContent from '../components/account/menu/bookContent.vue'
//左侧菜单
const MENU = [{
    name: 'exam',
    path: 'exam',
    component: exam
}, {
    name: 'book',
    path: 'book',
    component: book
}, {
    name: 'bookChapter',
    path: 'bookChapter',
    component: bookChapter
}, {
    name: 'bookContent',
    path: 'bookContent',
    component: bookContent
}, {
    name: 'question',
    path: 'question',
    component: question
}, {
    name: 'manager',
    path: 'manager',
    component: manager
}];
//首页
import hearder from '../components/account/content/Hearder.vue'



Vue.use(Router)
let router = new Router({
    //去除地址栏里的#/
    // base:'/dmp/',
    mode: 'history',
    routes: [{
            name: 'user',
            path: '/user',
            component: user,
            children: [{
                    name: 'changepassword',
                    path: 'changepassword',
                    component: changepassword
                },
                {
                    name: 'userLook',
                    path: 'userLook',
                    component: userLook
                },
                {
                    name: 'hearder',
                    path: 'hearder',
                    component: hearder,
                    meta: {
                        requireAuth: true, // 添加该字段，表示进入这个路由是需要登录的
                    }
                },
                ...MENU
            ]
        },
        {
            name: 'login',
            path: '/login',
            component: login
        },
        {
            name: 'register',
            path: '/register',
            component: register
        },
        {
            name: 'forgit',
            path: '/forgit',
            component: forgit
        },
        {
            name: 'dxlogin',
            path: '/dxlogin',
            component: dxlogin
        },
        {
            name: 'mode',
            path: '*',
            redirect: "/login"
        },
        {
            name: 'mode1',
            path: '/',
            redirect: "/login"
        }
    ]
})

export default router