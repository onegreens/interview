<template>
<!-- 用户管理 -->
<div class="userManage">
    <div class="userManage_hearder">
      <h3 style="color:#fff;">用户管理</h3>
    </div>
    <el-button type="primary" plain @click="dialogFormVisible = true" class="userManageNew" size="mini">
      <i class="el-icon-plus"></i>
      新建
    </el-button>

    <!-- 右侧选择框 -->
    <div style="float:right;">
      <div style="float:left;margin-right:25px;">
    <el-select v-model="value" placeholder="请选择查询条件" @change="changeValue" style="width:120px;left:10px;" size="mini">
    <el-option
      v-for="item in options"
      :key="item.value"
      :label="item.label"
      :value="item.value">
    </el-option>
  </el-select>
  <el-input v-model="input" placeholder="请输入查询内容" style="width:140px;left:10px;" size="mini"></el-input>
  <el-button @click="blurInput" type="primary" style="width:80px;position:relative;right:-16px;" size="mini" icon="el-icon-search">查询</el-button>
  </div>

<el-dropdown type="primary" @click="handleClick" style="float:left;margin-left:3px;" @command="handleCommand">
  <el-button type="primary" szie="mini" style="height:29px;padding-bottom:5px;box-sizing: border-box;">
    <span style="position:relative;top:-5px;">更多菜单<i class="el-icon-arrow-down el-icon--right"></i></span>
  </el-button>
  <el-dropdown-menu slot="dropdown">
    <el-dropdown-item command="导入">
      <el-button size="small" type="text">导入</el-button>
    </el-dropdown-item>
    <el-dropdown-item command="导出">
      <el-button size="small" type="text">导出</el-button>
    </el-dropdown-item>
    <el-dropdown-item command="下载模板">
      <el-button size="small" type="text">下载模板</el-button>
    </el-dropdown-item>
  </el-dropdown-menu>
</el-dropdown>

<!-- 列筛选 -->
  <!-- 列筛选 -->
    <el-dropdown :hide-on-click="false" style="float:right;">
  <el-button type="primary" size="mini">
    列筛选<i class="el-icon-arrow-down el-icon--right"></i>
  </el-button>
  <el-dropdown-menu slot="dropdown" style="backgroundColor:#f5f5f5;">
    <el-dropdown-item><el-checkbox v-model="showArray[0].isShow" label="登录名" border size="mini" style="float:left;margin-bottom:2px;width:100%;text-align:left;padding-top:5px;"></el-checkbox></el-dropdown-item>
   <el-dropdown-item><el-checkbox v-model="showArray[1].isShow" label="用户姓名" border size="mini" style="float:left;margin-bottom:2px;width:100%;text-align:left;padding-top:5px;"></el-checkbox></el-dropdown-item>
   <el-dropdown-item><el-checkbox v-model="showArray[2].isShow" label="角色名" border size="mini" style="float:left;margin-bottom:2px;width:100%;text-align:left;padding-top:5px;"></el-checkbox></el-dropdown-item>
   <el-dropdown-item><el-checkbox v-model="showArray[3].isShow" label="联系电话" border size="mini" style="float:left;margin-bottom:2px;width:100%;text-align:left;padding-top:5px;"></el-checkbox></el-dropdown-item>
   <el-dropdown-item><el-checkbox v-model="showArray[4].isShow" label="邮箱" border size="mini" style="float:left;margin-bottom:2px;width:100%;text-align:left;padding-top:5px;"></el-checkbox></el-dropdown-item>
  </el-dropdown-menu>
</el-dropdown>
    </div>

 <el-dialog title="新建用户" :visible.sync="dialogFormVisible" center>
   <el-form :model="form" ref="ruleForm" :rules="rulesNew">
     <el-form-item label="登录名" :label-width="formLabelWidth" class="userNameNew" prop="account">
       <el-input v-model="form.account" auto-complete="off" @blur="loadByUserNameNew"></el-input>
     </el-form-item>
     <el-form-item label="角色名" :label-width="formLabelWidth" class="userNameNew" prop="roleId">
       <el-select v-model="form.roleId" placeholder="请选择查询条件" @change="changeValue" style="">
    <el-option
      v-for="item in roleNew"
      :key="item.id"
      :label="item.name"
      :value="item.id">
    </el-option>
  </el-select>
     </el-form-item>
     <el-form-item label="用户姓名" :label-width="formLabelWidth" class="roleNameNew" prop="userName">
       <el-input v-model="form.userName" auto-complete="off"></el-input>
     </el-form-item>
     <el-form-item label="登录密码" :label-width="formLabelWidth" class="password" prop="pwd">
       <el-input v-model="form.pwd" auto-complete="off" type="password"></el-input>
     </el-form-item>
     <el-form-item label="密码确认" :label-width="formLabelWidth" class="passwordTwo" prop="passwordTwo">
       <el-input v-model="form.passwordTwo" auto-complete="off" type="password"></el-input>
     </el-form-item>
     <el-form-item label="联系电话" :label-width="formLabelWidth" class="telephone" prop="msisdn">
       <el-input v-model="form.msisdn" auto-complete="off" type="telephone"></el-input>
     </el-form-item>
     <el-form-item label="邮箱" :label-width="formLabelWidth" class="eamil" prop="email">
       <el-input v-model="form.email" auto-complete="off" type="email"></el-input>
     </el-form-item>
   </el-form>
   <div slot="footer" class="dialog-footer">
     <el-button @click="submitFormNo">取 消</el-button>
     <el-button type="primary" @click="submitForm('ruleForm')">确 定</el-button>
   </div>
 </el-dialog>

  <el-button type="primary" plain @click="dialogFormVisibleUpdate = true" class="userManageUpdate" size="mini">
      <i class="el-icon-edit"></i>
      修改
    </el-button>
 
 <el-dialog title="修改" :visible.sync="dialogFormVisibleUpdate">
   <h3 v-if="multipleSelection.length==0">请先选择一条信息修改</h3> 
   <h3 v-else-if="multipleSelection.length>1">请选择一条信息修改</h3> 
   <el-form :model="formUpdate" ref="ruleFormUpdate" v-else :rules="rulesUpdate">
     <el-form-item label="登录名" :label-width="formLabelWidth" class="userNameNew" prop="account">
       <el-input v-model="formUpdate.account" auto-complete="off" disabled></el-input>
     </el-form-item>
     <el-form-item label="角色名" :label-width="formLabelWidth" class="userNameNew" prop="roleId">
       <el-select v-model="formUpdate.roleId" placeholder="请选择查询条件" @change="changeValue" style="">
    <el-option
      v-for="item in roleUpdate"
      :key="item.id"
      :label="item.name"
      :value="item.id">
    </el-option>
  </el-select>
     </el-form-item>
     <el-form-item label="用户姓名" :label-width="formLabelWidth" class="roleNameNew" prop="userName">
       <el-input v-model="formUpdate.userName" auto-complete="off"></el-input>
     </el-form-item>
     <!-- <el-form-item label="登录密码" :label-width="formLabelWidth" class="password" prop="pwd">
       <el-input v-model="formUpdate.pwd" auto-complete="off" type="password"></el-input>
     </el-form-item>
     <el-form-item label="密码确认" :label-width="formLabelWidth" class="passwordTwo" prop="passwordTwo">
       <el-input v-model="formUpdate.passwordTwo" auto-complete="off" type="password"></el-input>
     </el-form-item> -->
     <el-form-item label="联系电话" :label-width="formLabelWidth" class="telephone" prop="msisdn">
       <el-input v-model="formUpdate.msisdn" auto-complete="off" type="telephone"></el-input>
     </el-form-item>
     <el-form-item label="邮箱" :label-width="formLabelWidth" class="" prop="email">
       <el-input v-model="formUpdate.email" auto-complete="off" type="email" style="width:40%;float:left;margin-left:10px;"></el-input>
     </el-form-item>
   </el-form>
   <div slot="footer" class="dialog-footer">
     <el-button @click="ruleFormUpdateNo">取 消</el-button>
     <el-button type="primary" @click="ruleFormUpdate('ruleFormUpdate')" v-if="multipleSelection.length==1">确 定</el-button>
   </div>
 </el-dialog>

<el-button type="primary" plain @click="userManageDelete = true" class="userManageDelete" size="mini">
      <i class="el-icon-delete"></i>
      删除
</el-button>

<el-dialog title="删除" :visible.sync="userManageDelete">
   <h3 v-if="multipleSelection.length==0">请先选择一条信息删除</h3> 
   <el-form :model="formDelete" ref="formDelete" v-else> 
     <h3>确定删除这条信息吗?</h3>
   </el-form>
   <div slot="footer" class="dialog-footer">
     <el-button @click="userManageDelete = false">取 消</el-button>
     <el-button type="primary" @click="ruleFormDelete('formDelete')" v-if="multipleSelection.length>0">确 定</el-button>
   </div>
 </el-dialog>
    <!-- 内容展示表格 -->
    <el-table
    ref="multipleTable"
      :data="tableDataArr"
      tooltip-effect="dark"
      style="width: 100%;positon:relative;top:10px;"
      @selection-change="handleSelectionChange"
      :header-cell-style="getRowClass"
      border>
      <el-table-column
        type="selection"
        width="55">
      </el-table-column>
      <el-table-column
        prop="account"
        label="登录名"
        :show-overflow-tooltip="true"
        width="180"
        v-if="showArray[0].isShow"
     >
     </el-table-column>
     <el-table-column
       prop="userName"
       :show-overflow-tooltip="true"
       label="用户姓名"
       width="180"
       v-if="showArray[1].isShow">
     </el-table-column>
      <el-table-column
       prop="roleName"
       :show-overflow-tooltip="true"
       label="角色名"
      :filters="[{text: '超级管理员', value: '超级管理员'},{text: '租户', value: '租户'},{text: '普通用户', value: '普通用户'},]"
      :filter-method="filterHandler"
       width="180"
        v-if="showArray[2].isShow">
     </el-table-column>
     <el-table-column
       prop="msisdn"
       :show-overflow-tooltip="true"
       label="联系电话"
        v-if="showArray[3].isShow">
     </el-table-column>
     <el-table-column
       prop="email"
       :show-overflow-tooltip="true"
       label="邮箱"
        v-if="showArray[4].isShow">
     </el-table-column>
   </el-table>
   

   <!-- 分页 -->
   <div class="block">
     <el-pagination
       @size-change="handleSizeChange"
       @current-change="handleCurrentChange"
       :current-page="currentPage4"
       :page-sizes="[20, 50, 100, 200]"
       :page-size="page.pageSize"
       layout="total, sizes, prev, pager, next, jumper"
       :total="totalCount">
     </el-pagination>
   </div>
   </div>
</template>
 
 <script>
export default {
  name:'manager',
  inject:['reload'],
  data() {
    return {
      showArray:[{label:'登录名',isShow:true},{label:'用户姓名',isShow:true},{label:'角色名',isShow:true},{label:'联系电话',isShow:true},{label:'邮箱',isShow:true}],
      tableDataArr:[],
      //右侧选择项
      options: [{
          value: '登录名',
          label: '登录名'
        }, {
          value: '用户姓名',
          label: '用户姓名'
        }],
        value: '登录名',
        input: '',
      totalCount: 0,
      //表格数据
      tableData: [
      ],
      //弹出框设置
      dialogTableVisible: false,
      dialogFormVisible: false,
      dialogFormVisibleLook: false,
      dialogFormVisibleUpdate: false,
      userManageDelete: false,
      //新建提交的表单内容
      form: {
        account: "",
        userName: "",
        pwd: "",
        passwordTwo: "",
        email: "",
        msisdn: "",
        roleId:''
      },
      //新建的验证
      rulesNew:{
          account: [
              { required: true, message: '请输入登录名', trigger: 'blur' }
            ],
            userName: [
              { required: true, message: '请输入用户姓名', trigger: 'blur' }
           ],
            pwd: [
              {required: true, message: '请输入密码', trigger: 'blur' }
            ],
           passwordTwo: [
              {required: true, message: '请确认密码', trigger: 'blur' }
            ],
            email: [
              { required: true, message: '请输入邮箱', trigger: 'blur' },
              {type:'email',message: '请输入正确的邮箱'}
            ],
            roleId: [
              {required: true, message: '请选择角色', trigger: 'blur' }
            ],
            msisdn: [
              { required: true, message: '请输入联系电话', trigger: 'blur' },
              {validator:function(rule,value,callback){
            if(/^1[34578]\d{9}$/.test(value) == false){
                callback(new Error("请输入正确的手机号"));
            }else{
                callback();
            }
        }, trigger: 'blur'}
            ]
      },
      //修改的验证
      rulesUpdate:{
            userName: [
              { required: true, message: '请输入用户名', trigger: 'blur' }
           ],
           pwd: [
              {required: true, message: '请输入密码', trigger: 'blur' }
            ],
           passwordTwo: [
              {required: true, message: '请确认密码', trigger: 'blur' }
            ],
            email: [
              { required: true, message: '请输入邮箱', trigger: 'blur' },
              {type:'email',message: '请输入正确的邮箱'}
            ],
            roleId: [
              {required: true, message: '请选择角色', trigger: 'blur' }
            ],
            msisdn: [
              { required: true, message: '请输入联系电话', trigger: 'blur' },
              {validator:function(rule,value,callback){
            if(/^1[34578]\d{9}$/.test(value) == false){
                callback(new Error("请输入正确的手机号"));
            }else{
                callback();
            }
        }, trigger: 'blur'}
            ]
      },
      //查看的内容
      formLook: {
        account:"",
        userName: "",
        eamil: "",
        msisdn: "",
      },
      //更新的内容
      formUpdate: {
        account: "",
        userName: "",
        pwd: "",
        passwordTwo: "",
        email: "",
        msisdn: "",
        roleId:''
      },
      //删除的内容
      formDelete: {
        userName: "",
        roleName: "",
        password: "",
        passwordTwo: "",
        eamil: "",
        telephone: "",
        address: "",
        Authorization: this.access_token
      },
      formLabelWidth: "80px",
      multipleSelection: [],
      currentPage4: 1,
      access_token: "",
      userId:'',
      page: {
        pageNo: 1,
        pageSize: 20,
        Authorization: ""
      },
      roleNew:[],
      roleUpdate:[]
    };
  },
  components: {
  },
  methods: {
    //筛选
    filterHandler(value, row, column) {
      // console.log(column)
      // console.log(row)
      // console.log(row)
        const property = column['property'];
        return row[property] === value;
      },
    //后端排序
    sortChange(column){
        console.log(column);
    },
    //检查登录名是否重复
    loadByUserNameNew(){
      if(this.form.account){
        let page = {
        Authorization: this.access_token,
        userName:this.form.account
      }
      this.$store
        .dispatch("loadByUserName", page)
        .then(res => {
          if (res) {
           this.$alert('登录名已存在');
           return
          }else{
          //   this.$message({
          //   type: "success",
          //   message: "登录名已可用"
          // });
          }
        })
        .catch(e => {
          this.$alert("未知错误");
        });
      }
    },
    //表头样式
    getRowClass({ row, column, rowIndex, columnIndex }){
      				if (rowIndex == 0) {
					        return 'background:#efeff7'
				      } else {
					        return ''
				      }
    },

    //最右边的点击导出,导入,下载模板参数
    handleCommand(command) {
        this.$message('click on item ' + command);
    },
    handleClick() {
        // this.$alert('请选择条件');
      },
    //右侧选择条件
    changeValue(val){
      //console.log(this.value)
    },
    //点击搜索的时候
    blurInput(){
      console.log(this.input)
      if(this.value == '登录名'){
            if(this.input){
              this.tableDataArr = this.tableData.filter((e)=>{
              return e.account.indexOf(this.input)!=-1;
              })
            }else{
             this.$alert('请输入查询内容')
             this.userList();
            }
        }else if(this.value == '用户姓名'){
            if(this.input){
              this.tableDataArr = this.tableData.filter((e)=>{
              return e.userName.indexOf(this.input)!=-1;
              })
            }else{
              this.$alert('请输入查询内容')
              this.userList();
            }
        }else if(!this.value){
              this.$alert('请选择查询条件')
              this.userList();
        }
    },
    //请求数据
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
            console.log(res.data.result)
            this.tableData = res.data.result;
            this.tableDataArr = res.data.result;
            this.totalCount = res.data.totalCount;
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
    //提交新建信息
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
            //全部填写
            if(this.form.pwd !== this.form.passwordTwo){ 
                this.$alert('两次密码不一致');
                return;
                }; 
            //提交时验证登录名是否重复 
        let page = {
        Authorization: this.access_token,
        userName:this.form.account
      }
      this.$store
        .dispatch("loadByUserName", page)
        .then(res => {
          if (res.userId) {
           this.$alert('登录名已存在');
           return
          }else{
          //   this.$message({
          //   type: "success",
          //   message: "登录名已可用"
          // });
          this.form.Authorization = this.access_token;
            this.$store.dispatch('createUser',this.form).then(res=>{
               //console.log(res)
               if(res.code == 0){
                 this.$message({
                   type:'success',
                   message:'新增成功'
                 })
                this.userList();
               }else {
                 this.$alert("提交失败")
               }
          }).catch(e=>{
            this.$alert("未知错误");
            //console.log("chucuole"+JSON.stringify(e))
            })
          }
        })
        .catch(e => {
          this.$alert("未知错误");
        });
          this.dialogFormVisible = false;
          this.form = {
            account: "",
            userName: "",
            pwd: "",
            passwordTwo: "",
            email: "",
            msisdn: "",}
            this.reload();
      }else {
          return false;
        }
      });
    },
    //取消新建信息时情空
    submitFormNo(){
      this.dialogFormVisible = false;
      this.form = {
            account: "",
            userName: "",
            pwd: "",
            passwordTwo: "",
            email: "",
            msisdn: "",}
    },
    //修改
    ruleFormUpdate(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          // if(this.formUpdate.pwd !== this.formUpdate.passwordTwo){ 
          //       this.$alert('两次密码不一致');
          //       return;
          //     };
          this.formUpdate.Authorization = this.access_token;
          this.$store.dispatch('updateUser',this.formUpdate).then(res=>{
              if(res.code ==0){
                  this.$message({
                   type:'success',
                   message:'修改成功'
                 })
                this.userList();
              }else{
                  this.$alert('提交失败')
              }
          }).catch(e=>{
            this.$alert("未知错误");
            //console.log("chucuole"+JSON.stringify(e))
          })  
          this.dialogFormVisibleUpdate = false;
          this.reload();
        } else {
          return false;
        }
      });
    },
    ruleFormUpdateNo(){
      this.dialogFormVisibleUpdate = false;
      this.reload();
    },
    //删除用户
    ruleFormDelete(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          let page  = {
              userId:this.formDelete.userId,
              Authorization: this.access_token
          }
          this.$store.dispatch('deleteUser',page).then(res=>{
              if(res.code ==0){
                  this.$message({
                   type:'success',
                   message:'删除成功'
                 })
                this.userList();
                this.reload();
              }else{
                this.$message({
                   type:'warning',
                   message:'系统错误'
                 })
              }
              
          }).catch(e=>{
            // this.$alert(e);
            this.$message({
                   type:'warning',
                   message:'系统错误'
                 })
          })
            this.userManageDelete = false;
        } else {
          return false;
        }
      });
    },
    //选中行的数据;
    handleSelectionChange(val) {
      console.log(val)
      this.multipleSelection = val;
      let Selection = Array.prototype.slice.call(val);
      this.formLook = Selection[0];
      this.formUpdate = Selection[0];
      this.formDelete = Selection[0];
    },
    //分页
    handleSizeChange(val) {
      //console.log(`每页 ${val} 条`);
      this.page.pageSize = val;
      this.userList();
    },
    handleCurrentChange(val) {
      //console.log(`当前页: ${val}`);
      this.page.pageNo = val;
      this.userList();
    },
    //获取角色列表
    RoleList(){
       this.page = {
        pageNo: this.page.pageNo,
        pageSize: this.page.pageSize,
        Authorization: this.access_token
      };
      this.$store
        .dispatch("roleList", this.page)
        .then(res => {
          if (res.code == 0) {
            console.log(res)
            this.roleNew = res.data.result;
            this.roleUpdate = res.data.result;
          }else{
            this.$message({
            type: "warning",
            message: "系统错误"
          });
          }
        })
        .catch(e => {
          this.$alert(e);
        });
    }
  },
  created() {
    //请求数据
    this.access_token = localStorage.getItem("access_token");
    this.userList();
    this.RoleList();
  },
  destroyed() {}
};
</script>
<style scoped lang='less'>
@import './css/manager.css';
</style>