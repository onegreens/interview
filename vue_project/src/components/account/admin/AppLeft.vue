<template>
    <div class="left">
        <el-menu class="el-menu-vertical-demo" background-color="#fffff8" text-color="#409eff" active-text-color="#ffd04b" unique-opened>
            <el-submenu v-for="item in adminMenu" :index="item.name" :key="item.name" v-if="item.children">
                <template slot="title">
                    <router-link :to="item.url">
                        <i :class="item.icon"></i>
                        <span>{{item.name}}</span>
                    </router-link>
                </template>
                <el-menu-item v-for="items in item.children" :index="items.name" :key="items.name" class="item">
                    <router-link :to="items.url">
                        <i :class="items.icon"></i>
                        <span>{{items.name}}</span>
                    </router-link>
                </el-menu-item>
            </el-submenu>
            <el-menu-item :index="item.name" :key="item.name" v-else>
                <router-link :to="item.url">
                    <i :class="item.icon"></i>
                    <span>{{item.name}}</span>
                </router-link>
            </el-menu-item>
        </el-menu>

    </div>
</template>
 
 <script>
import { mapMutations } from "vuex";
export default {
    name: "AppLeft",
    data() {
        return {
            adminMenu: [
                {
                    name: "用户管理",
                    icon: "iconfont icon-people_fill",
                    url: "/user/manager"
                },
                {
                    name: "习题管理",
                    icon: "iconfont icon-people_fill",
                    url: "/user/question"
                },
                {
                    name: "书籍管理",
                    icon: "iconfont icon-people_fill",
                    url: "/user/book"
                },
                {
                    name: "章节管理",
                    icon: "iconfont icon-people_fill",
                    url: "/user/bookChapter"
                },
                {
                    name: "笔记管理",
                    icon: "iconfont icon-people_fill",
                    url: "/user/bookContent"
                },
               
            ],
            page: {},
            access_token: "",
            pageSize: 50,
            pageNo: 1,
            usname: ""
        };
    },
    methods: {
        
    },
    computed: {},
    created() {
        //获取菜单
        this.access_token = localStorage.getItem("access_token");
        this.usname = localStorage.getItem("uname");
    }
};
</script>
<style scoped lang='less'>
@import "./css/AppLeft.css";
</style>