<template>
<div>
    <div class="userManage_hearder">
      <h3>账户信息</h3>
    </div>
    <div style="">
    <el-form :model="form" ref="ruleForm">
     <el-form-item label="登录名" :label-width="formLabelWidth" class="" style="width:48%;float:left;">
       <el-input v-model="form.account" auto-complete="off" disabled></el-input>
     </el-form-item>
     <el-form-item label="用户姓名" :label-width="formLabelWidth" class="" prop="oldPassword" style="width:48%;float:left;">
       <el-input v-model="form.userName" auto-complete="off" disabled></el-input>
     </el-form-item>
     <el-form-item label="联系电话" :label-width="formLabelWidth" class="" prop="password" style="width:48%;float:left;">
       <el-input v-model="form.msisdn" auto-complete="off" disabled></el-input>
     </el-form-item>
     <el-form-item label="邮箱" :label-width="formLabelWidth" class="" prop="queryPassword" style="width:48%;float:left;">
       <el-input v-model="form.email" auto-complete="off" disabled></el-input>
     </el-form-item>
     <el-form-item label="详细地址" :label-width="formLabelWidth" class="" prop="queryPassword">
       <el-input v-model="form.address" auto-complete="off" disabled style="width:96%;float:left;"></el-input>
     </el-form-item>
   </el-form>
   <div slot="footer" class="dialog-footer">
     <el-button @click="submitFormNo">关 闭</el-button>
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
        account:'',//登录名
        userName:'',//用户姓名
        msisdn:'',//电话
        email:'',//邮箱
        address:''//详细地址
    },
    access_token:'',
    idArr:[],
    id:'',
    page: {
        pageNo: 1,
        pageSize: 10,
        Authorization: ""
      },
      formLabelWidth:'100px'
};
},
methods: {
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
            console.log(res);
            this.idArr = res.data.result;
            this.idArr.forEach((e)=>{
                if(e.account == this.form.account){
                    // this.id = e.userId
                    this.form = e;
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
    this.form.account = localStorage.getItem('uname');
    this.userList();
}
};
</script>
<style scoped lang='less'>
@import './css/user.css';
</style>