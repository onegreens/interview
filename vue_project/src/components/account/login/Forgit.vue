<template>
    <div class="login">
        <section>
            <!-- 表单内容 -->
            <div class="login_title">
                <div class="login_img"><img src="/static/login_title.jpg" alt="" style="width:270px;margin-left:20px;"></div>
                </div>
            <!-- model用来设置表单所有字段, rules用来设置表单校验规则的(可省略, 但是如果需要表单校验功能, 必须写)  -->
            <el-form :model="ruleForm2" :rules="rules2" status-icon show-message ref="abc" label-width="100px" class="demo-ruleForm">
                <div>
                    <h3>忘记密码</h3>
                </div>
                        <!-- label用来设置表单提示文字, prop用来指定当前表单代表的字段名(可省略, 但是如果需要表单校验与重置功能, 必须写) -->

                <el-form-item label="电话号码" prop="telephone">
                    <!-- v-model双向数据绑定, 需要绑定data里的数据, 将来要把这些数据提交给后端 -->
                    <el-input type="text" v-model="ruleForm2.telephone" auto-complete="off" class="login_input" style="width: 150px;left:-12px;">
                    </el-input>
                    <el-button type="success" @click="submitForm('abc')" style="width: 100px">发送验证</el-button>
                </el-form-item>
                <el-form-item label="验证码" prop="yz_register">
                    <!-- v-model双向数据绑定, 需要绑定data里的数据, 将来要把这些数据提交给后端 -->
                    <el-input type="text" v-model="ruleForm2.yz_register" auto-complete="off">
                    </el-input>
                </el-form-item>

                <el-form-item label="密码" prop="password">
                    <!-- v-model双向数据绑定, 需要绑定data里的数据, 将来要把这些数据提交给后端 -->
                    <el-input type="password" v-model="ruleForm2.password" auto-complete="off" class="login_input">
                    </el-input>
                </el-form-item>

                <el-form-item label="确认密码" prop="password_two">
                    <!-- v-model双向数据绑定, 需要绑定data里的数据, 将来要把这些数据提交给后端 -->
                    <el-input type="password" v-model="ruleForm2.password_two" auto-complete="off" class="login_input">
                    </el-input>
                </el-form-item>

                <el-form-item>
                    <el-button type="success" @click="submitForm('abc')">提交</el-button>
                    <el-button @click="$router.go(-1)">返回</el-button>
                </el-form-item>
            </el-form>
        </section>
    </div>
</template>

<script>
export default {
  name:'Forgit',
  data() {
    return {
      // 这里的字段要按照接口文档进行修改
      ruleForm2: {
        password: "",
        password_two:"",
        telephone:"",
        yz_forgit:""
      },
      // 这里添加的校验规则, 每个字段可以加多个, 所以是个数组
      rules2: {
        //分别为必填,不填的提示,
        password: [{ required: true, message: "请输入密码", trigger: "blur" }],
        password_two: [{ required: true, message: "请再次输入密码", trigger: "blur" }],
        telephone: [{ required: true, message: "请输入手机号", trigger: "blur" }],
        yz_forgit: [{ required: true, message: "请输入验证码", trigger: "blur" }],
      }
    };
  },
  methods: {
    login() {
      // post请求的第二个参数是发送的数据, 这里直接把data里的表单对象传过去
      // this.$http.post(this.$api.login, this.ruleForm2).then((res) => {
      //     if(res.data.status==0){
      //         this.$alert('登录成功',"马上要跳转",{callback:()=>{
      //                 //存储用户的uname
      //             localStorage.setItem('uname',res.data.message.uname)
      //                 // 跳转页面,就是切换路由,跳转到上一次查询的页面,如果上一次没有查询,则调到登录页面;
      //             this.$router.push({ path:this.$route.query.next || '/shop/goods/list' })
      //          }
      //         })
      //     }else{
      //         this.$alert('账号密码错误');
      //     }
      // });
      this.$router.push({ path: "/admin/contentone" });
    },
    // 登陆按钮方法
    // this.$refs用来获取页面中的元素或组件, 这里通过它拿到form表单组件, 调用validate方法, 给所有表单进行校验
    submitForm(abc) {
      // validate传入一个回调, 如果valid为true, 证明通过校验, 否则不通过
      this.$refs[abc].validate(valid => {
        if (valid) {
          this.login();
        } else {
          this.$alert("请填写完毕!");
        }
      });
    }
  }
};
</script>

<style scoped lang="less">
@import './css/Forgit.css';
</style>