<template>
    <div class="login">
        <section>
            <!-- 表单内容 -->
            <!-- 左侧 -->
            <div class="login_left">
              <img src="/static/login_title_2.jpeg" alt="">
              <p>Sunpro IoT</p>
            </div>
            <!-- model用来设置表单所有字段, rules用来设置表单校验规则的(可省略, 但是如果需要表单校验功能, 必须写)  -->
            <el-form :model="ruleForm1" :rules="rules2" status-icon show-message ref="abc" label-width="100px" class="demo-ruleForm">
                        <!-- label用来设置表单提示文字, prop用来指定当前表单代表的字段名(可省略, 但是如果需要表单校验与重置功能, 必须写) -->
                        <div class="title_right_top">
                            <p>快乐习题</p>
                        </div>
                <!-- <el-form-item label="" prop="username"> -->
                    <!-- v-model双向数据绑定, 需要绑定data里的数据, 将来要把这些数据提交给后端 -->
                  <div class="login_input_box">
                    <i class="iconfont icon-group"></i>
                    <input type="text" v-model="ruleForm1.username" auto-complete="off" class="login_input" placeholder="用户名"/>
                    </div>
                <!-- </el-form-item> -->

                <!-- <el-form-item label="" prop="yz_password" style=""> -->
                    <!-- 双向数据绑定对象的密码,这个对象就是传入后台的参数对象 -->
                    <div class="yanzhengma_input_box">
                      <i class="iconfont icon-jilu"></i>
                    <input type="text" placeholder="验证码" class="yanzhengma_input" v-model="ruleForm1.password" @keyup.enter="Login">
                     </div>
                    <el-button type="primary" id="code" @click="createCode" class="verification1">获取验证码</el-button>
                    <!-- <span class="tishixiaoxi"></span> -->
                   
                    <!-- <a class="user_login" @click="Login">登录</a> -->
                <!-- </el-form-item> -->

                <!-- <el-form-item style=""> -->
                    <router-link :to="{path:'/login'}" style="" class="dxLogin">账号登录</router-link> 
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
            message: "账号或验证码不合格"
          });
          return;
      }
          //请求登录是否正确
           let ruleFormData = this.ruleForm1;
            this.$store.dispatch('Login',ruleFormData).then(res=>{
              //  console.log(res)
              if(res.access_token){
                // console.log(res)
                let uname = this.ruleForm1.username
                localStorage.setItem('uname',uname);
                localStorage.setItem('access_token',res.access_token);
                this.$router.push({ path: "/user/hearder" });
              }else{
                this.$alert('账号或密码错误')
              }
          }).catch(e=>{
            // this.$alert(e);
            console.log("chucuole"+JSON.stringify(e))
            if(e.access_token){
                console.log(e)
                 let uname = this.ruleForm1.username
                localStorage.setItem('uname',uname);
                localStorage.setItem('access_token',e.access_token);
                this.$router.push({ path: "/user/hearder" });
              }else{
                this.$alert('账号或密码错误')
              }
          })
    },
    // 图片验证码
createCode(){
      console.log('获取验证码')
    },
  },
  created() {
    // this.reload();
   
  }
};
</script>

<style scoped lang="less">
@import './css/dxlogin.css';
</style>