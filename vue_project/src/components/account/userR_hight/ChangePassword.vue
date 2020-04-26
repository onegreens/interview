<template>
<div>
    <div class="userManage_hearder">
      <h3>修改密码</h3>
    </div>
    <div style="">
    <el-form :model="form" ref="ruleForm" :rules="rulesNew">
     <el-form-item label="登录名" :label-width="formLabelWidth" class="">
       <el-input v-model="form.name" auto-complete="off" disabled style="width:90%;float:left;"></el-input>
     </el-form-item>
     <el-form-item label="初始密码" :label-width="formLabelWidth" class="" prop="oldPassword">
       <el-input v-model="form.oldPassword" auto-complete="off" style="width:90%;float:left;"></el-input>
     </el-form-item>
     <el-form-item label="新密码" :label-width="formLabelWidth" class="" prop="password">
       <el-input v-model="form.password" auto-complete="off" style="width:90%;float:left;"></el-input>
     </el-form-item>
     <el-form-item label="确认密码" :label-width="formLabelWidth" class="" prop="queryPassword">
       <el-input v-model="form.queryPassword" auto-complete="off" style="width:90%;float:left;"></el-input>
     </el-form-item>
   </el-form>
   <div slot="footer" class="dialog-footer">
     <el-button @click="submitFormNo">取 消</el-button>
     <el-button type="primary" @click="submitForm('ruleForm')">确 定</el-button>
   </div>
   </div>
</div>
</template>
<script>
export default {
name:'ChangePassword',
data() {
return {
    form:{
        name:'',
        oldPassword:'',
        password:'',
        queryPassword:''
    },
    access_token:'',
    idArr:[],
    id:'',
    page: {
        pageNo: 1,
        pageSize: 10,
        Authorization: ""
      },
      //修改密码的验证
      rulesNew:{
            oldPassword: [
              { required: true, message: '请输入初始密码', trigger: 'blur' }
           ],
           password: [
              { required: true, message: '请输入新密码', trigger: 'blur' }
           ],
           queryPassword: [
              { required: true, message: '请再次输入密码确认', trigger: 'blur' }
           ],
      },
      formLabelWidth:'100px'
};
},
methods: {
    //提交新建信息
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
            if(this.form.password != this.form.queryPassword){
                this.$alert('两次输入的密码不一致');
                return;
            }
            this.idArr.forEach((e)=>{
                if(e.userName == this.form.name){
                    if(e.pwd != this.form.oldPassword){
                        this.$alert('初始密码错误');
                        return;
                    }
                }
            })
            let page = {
              Authorization:this.access_token,
              form:this.form,
              id:this.id
            }
            this.$store.dispatch('userSetPassword',page).then(res=>{
               if(res.code == 0){
                 this.$message({
                   type:'success',
                   message:'修改成功'
                 })
               }else {
                 this.$alert("提交失败")
               }
          }).catch(e=>{
            this.$alert("未知错误");
          })
        //   this.$router.push({path:'/user/hearder'});
         this.$router.go(-1);
      }else {
          return false;
        }
      });
    },
    //取消新建信息时清空
    submitFormNo(){
        //关闭跳转回
        this.$router.go(-1);
    },
    //请求数据user列表
    userList() {
      this.page = {
        pageNo: this.page.pageNo,
        pageSize: this.page.pageSize,
        Authorization: this.access_token
      };
      this.$store
        .dispatch("userList", this.page)
        .then(res => {
          if (res.code == 0) {
              //console.log(res);
            this.idArr = res.data.result;
            this.idArr.forEach((e)=>{
                if(e.userName == this.form.name){
                    this.id = e.userId
                }
            })
          }else{
            this.$message({
            type: "warning",
            message: "系统错误"
            });
          }
        })
        .catch(e => {
          this.$alert("未知错误");
        });
    },
},
created(){
    //请求数据
    this.access_token = localStorage.getItem("access_token");
    //用户登录名
    // this.form.name = localStorage.getItem('uname');
    this.form.name = 'admin';
    this.userList();
}
};
</script>
<style scoped lang='less'>
@import './css/changePassword.css';
</style>