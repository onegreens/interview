<template>
    <div class="login">
        <section>
            <!-- 表单内容 -->
            <!-- 左侧 -->
            <!-- <div class="login_left">
              <img src="/static/login_title_2.jpeg" alt="">
              <p>Sunpro IoT</p>
            </div> -->
            <!-- model用来设置表单所有字段, rules用来设置表单校验规则的(可省略, 但是如果需要表单校验功能, 必须写)  -->
            <el-form :model="ruleForm1" :rules="rules1" status-icon show-message ref="abc" label-width="100px" class="demo-ruleForm">
                        <!-- label用来设置表单提示文字, prop用来指定当前表单代表的字段名(可省略, 但是如果需要表单校验与重置功能, 必须写) -->
                        <div class="title_right_top">
                            <img src="/static/login_title_2.jpeg" alt="">
                            <p>快乐习题</p>
                        </div>
                <!-- <el-form-item label="" prop="username"> -->
                    <!-- v-model双向数据绑定, 需要绑定data里的数据, 将来要把这些数据提交给后端 -->
                  <div class="login_input_box">
                    <i class="iconfont icon-group"></i>
                    <input type="text" v-model="ruleForm1.username" auto-complete="off" class="login_input" placeholder="用户名"/>
                    </div>
                <!-- </el-form-item> -->

                <!-- <el-form-item label="" prop="password"> -->
                    <!-- 双向数据绑定对象的密码,这个对象就是传入后台的参数对象 -->
                    <div class="login_password_box">
                      <i class="iconfont icon-lock_fill"></i>
                     <input type="password" v-model="ruleForm1.password" auto-complete="off" class="login_password" placeholder="密码"/>
                     </div>
                <!-- </el-form-item> -->

                <!-- <el-form-item label="" prop="yz_password" style=""> -->
                    <!-- 双向数据绑定对象的密码,这个对象就是传入后台的参数对象 -->
                    <div class="yanzhengma_input_box">
                      <i class="iconfont icon-jilu"></i>
                    <input type="text" placeholder="验证码" class="yanzhengma_input" @blur="checkLpicma" v-model="picLyanzhengma" @keyup.enter="Login">
                    </div>
                    <input type="button" id="code" @click="createCode" class="verification1" v-model="checkCode"/> <br>
                    <!-- <span class="tishixiaoxi"></span> -->
                    
                    <!-- <a class="user_login" @click="Login">登录</a> -->
                <!-- </el-form-item> -->

                <!-- <el-form-item style=""> -->
                    <router-link :to="{path:'/dxlogin'}" style="" class="dxLogin">短信登录</router-link> 
                <!-- </el-form-item> -->

                <!-- <el-form-item style=""> -->
                    <el-button type="success" style="" class="Login_button" @click="Login"><a class="user_login" style="color:#fff;">登&nbsp;&nbsp;录</a></el-button>
                
                <!-- </el-form-item> -->

                <!-- <el-form-item style=""> -->
                  <div class="register_box">
                    <router-link :to="{path:'/register'}" style="" class="register">注册账号</router-link>    
                    <router-link :to="{path:'/forgit'}" style="" class="forgit">忘记密码?</router-link>
                    </div>
                <!-- </el-form-item>  -->
            </el-form>
        </section>
    </div>
</template>

<script>
import qs from 'qs'
import md5 from 'js-md5'
export default {
  name:'login',
  inject:['reload'],
  data() {
    return {
      // 这里的字段要按照接口文档进行修改
      ruleForm1: {
        username: "",
        password: "",
        grant_type: "password",
        client_id: "iotstp",
        client_secret: "zaq1XSW2"
      },
      ruleForm2:{
          telephone: "",
        yz_telephone: "",
      },
      picLyanzhengma:'',
      checkCode:"",
      // 这里添加的校验规则, 每个字段可以加多个, 所以是个数组
      rules1:{
            username: [
          { required: true, message: "请输入账户名", trigger: "blur" }
        ],
        password: [{ required: true, message: "请输入密码", trigger: "blur" }],
      },
      rules2: {
        //分别为必填,不填的提示,
        telephone: [
          { required: true, message: "请输入手机号", trigger: "blur" }
        ],
        yz_telephone: [
          { required: true, message: "请输入正确验证码", trigger: "blur" }
        ]
      }
    };
  },
  components: {},
  mounted() {
    //  $(".tishixiaoxi").text("请输入验证码")
  },
  methods: {
    Login(){
      if(!this.ruleForm1.username || !this.ruleForm1.password){
        this.$message({
            type: "warning",
            message: "账号或密码不合格"
          });
          return;
      }
      if(this.checkLpicma()){
          //请求登录是否正确
           let ruleFormData = this.ruleForm1;
            // ruleFormData.password  = md5(this.ruleForm1.password)
            this.$store.dispatch('Login',ruleFormData).then(res=>{
               console.log(res)
              if(res.access_token){
                // console.log(res)
                let uname = this.ruleForm1.username
                localStorage.setItem('uname',uname);
                localStorage.setItem('access_token',res.access_token);
                // let redirect = decodeURIComponent(this.$route.query.redirect || '/user/product');  //获取登录成功后要跳转的路由。
                // console.log(typeof redirect)
                // this.$router.push({
                // path: redirect
                //  })
                this.$router.push({ path: "/user/hearder" })
              }else{
                this.$message({
                type: "warning",
                 message: "账号或密码错误"
                 });
              }
          }).catch(e=>{
            // this.$alert(e);
            if(e.access_token){
                // console.log(e)
                 let uname = this.ruleForm1.username
                localStorage.setItem('uname',uname);
                localStorage.setItem('access_token',e.access_token);
                // let redirect = decodeURIComponent(this.$route.query.redirect || '/user/product');  //获取登录成功后要跳转的路由。
                //  console.log(typeof redirect)
                // this.$router.push({
                // path: redirect
                //  })
                this.$router.push({ path: "/user/hearder" })
              }else{
                this.$message({
                type: "warning",
                 message: "账号或密码错误"
                 });
              }
          })
      }else{
        return;
      }
    },
    // 图片验证码
createCode(){
  let _this = this;
  let code = ""; 
  var codeLength = 4;//验证码的长度 
  var random = new Array(0,1,2,3,4,5,6,7,8,9,'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R', 
     'S','T','U','V','W','X','Y','Z');//随机数 
  for(var i = 0; i < codeLength; i++) {
   //循环操作 
   var index = Math.floor(Math.random()*36);//取得随机数的索引（0~35） 
   code += random[index];//根据索引取得随机数加到code上 
  } 
  _this.checkCode = code;//把code值赋给验证码 
},
// 失焦验证图和密码
checkLpicma(){
  let _this = this;   
  _this.picLyanzhengma.toUpperCase();//取得输入的验证码并转化为大写   
  if(_this.picLyanzhengma == '') {
   //代码是直接复制项目内容，这里选择器选择时 请根据自己的需求来实现提示语效果，很简单，我懒，就不改了 ~
  _this.$message({
            type: "warning",
            message: "请输入验证码"
          });
  }else if(_this.picLyanzhengma.toUpperCase() != _this.checkCode ) { 
   //若输入的验证码与产生的验证码不一致时 
  _this.$message({
            type: "warning",
            message: "验证码不正确"
          });
   _this.createCode();//刷新验证码 
   _this.picLyanzhengma = '';
  }else {
   //输入正确时 
   //代码是直接复制项目内容，这里选择器选择时 请根据自己的需求来实现提示语效果，很简单，我懒，就不改了 ~  
   return true;
  } 
    },
  },
  created() {
    // this.reload();
    this.createCode();
  }
};
</script>

<style scoped lang="less">
@import './css/Login.css';
</style>