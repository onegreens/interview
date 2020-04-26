<template>
<div class="header">
    <div class="topnav" id="topnav">
      <h1>
					<img src="/static/login_title_2.jpeg" style="width: 48px; margin-right: 6px;margin-left: 5px;margin-top: 6px;float:left">
					<a style="color: #fbb706; font-size: 18px; line-height: 60px;float:left">快乐习题</a>
				</h1>
        <ul class="top-menu pandect-menu" style="float:left;height:60px;line-height:60px;">
						<li class="common_list active" style="height:100%">
							<router-link class="hearder_titer" :to="{path:'hearder'}">首页</router-link>
						</li>
					</ul>
    </div>

    <el-dropdown :hide-on-click="true" class="dropdown_left" @command="handleCommand">
    <span class="el-dropdown-link">
      hi, {{usname}}
      <i class="el-icon-arrow-down el-icon--right"></i>
    </span>
    <el-dropdown-menu slot="dropdown" class="dropdown_leftMenu">
      <el-dropdown-item command="账户信息">
        <a>账户信息</a>
      </el-dropdown-item>
      <el-dropdown-item command="修改密码">
        <a>修改密码</a>
      </el-dropdown-item>
      <el-dropdown-item command="退出登录">
        <a>退出登录</a>
      </el-dropdown-item>
   </el-dropdown-menu>
 </el-dropdown>
 </div>
</template>
 <script>
 import {mapGetters} from 'vuex'
   export default {
     name:'AppHeader',
     inject:['reload'],
     data () {
       return { 
         usname:''
       }
     },
     methods: {
       handleCommand(val){
          console.log(val)
          if(val == '账户信息'){
            this.$router.push({path:'/user/userLook'})
          }else if (val == '修改密码'){
            this.$router.push({path:'/user/changepassword'})
          }else if (val == '退出登录'){
            this.$router.push({path:"/login"});
            localStorage.clear();
            window.location.reload();
          }
       },
     },
     created () {
        this.usname = localStorage.getItem('uname');
     },
     computed: {
       
     },
     destroyed () {
     }
   }
 </script>


<style scoped lang='less'>
@import './css/AppHeader.css';
</style>