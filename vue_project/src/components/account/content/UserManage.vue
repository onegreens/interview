<template>
<!-- 用户管理 -->
<div class="userManage">
    <div class="userManage_hearder">
      <h3>用户管理</h3>
    </div>
    <el-button type="primary" plain @click="dialogFormVisible = true" class="userManageNew">
      <i class="el-icon-plus"></i>
      新建
    </el-button>
 <el-dialog title="新建用户" :visible.sync="dialogFormVisible" center>
   <el-form :model="form" ref="ruleForm">
     <el-form-item label="登录名" :label-width="formLabelWidth" class="userNameNew">
       <el-input v-model="form.userName" auto-complete="off"></el-input>
     </el-form-item>
     <el-form-item label="用户姓名" :label-width="formLabelWidth" class="roleNameNew">
       <el-input v-model="form.roleName" auto-complete="off"></el-input>
     </el-form-item>
     <el-form-item label="登录密码" :label-width="formLabelWidth" class="password">
       <el-input v-model="form.password" auto-complete="off"></el-input>
     </el-form-item>
     <el-form-item label="密码确认" :label-width="formLabelWidth" class="passwordTwo">
       <el-input v-model="form.passwordTwo" auto-complete="off"></el-input>
     </el-form-item>
     <el-form-item label="联系电话" :label-width="formLabelWidth" class="telephone">
       <el-input v-model="form.telephone" auto-complete="off"></el-input>
     </el-form-item>
     <el-form-item label="邮箱" :label-width="formLabelWidth" class="eamil">
       <el-input v-model="form.eamil" auto-complete="off"></el-input>
     </el-form-item>
     <el-form-item label="详情地址" :label-width="formLabelWidth" class="adress">
       <el-input v-model="form.adress" auto-complete="off"></el-input>
     </el-form-item>
   </el-form>
   <div slot="footer" class="dialog-footer">
     <el-button @click="dialogFormVisible = false">取 消</el-button>
     <el-button type="primary" @click="dialogFormVisible = false;submitForm('ruleForm')">确 定</el-button>
   </div>
 </el-dialog>

 <el-button type="primary" plain @click="dialogFormVisibleLook = true" class="userManageLook">
      <i class="el-icon-plus"></i>
      查看
    </el-button>
 <el-dialog title="查看" :visible.sync="dialogFormVisibleLook">
   <h3 v-if="multipleSelection.length==0">请先选择一条信息查看</h3> 
   <el-form :model="formLook" v-else>
     <el-form-item label="姓名" :label-width="formLabelWidth">
       <el-input v-model="formLook.userName" auto-complete="off" disabled></el-input>
     </el-form-item>
     <el-form-item label="活动区域" :label-width="formLabelWidth">
       <el-select v-model="formLook.address" placeholder="请选择活动区域">
         <el-option label="区域一" value="shanghai"></el-option>
         <el-option label="区域二" value="beijing"></el-option>
       </el-select>
     </el-form-item>
   </el-form>
   <div slot="footer" class="dialog-footer">
     <el-button @click="dialogFormVisibleLook = false">取 消</el-button>
   </div>
 </el-dialog>

  <el-button type="primary" plain @click="dialogFormVisibleUpdate = true" class="userManageUpdate">
      <i class="el-icon-plus"></i>
      修改
    </el-button>
 
 <el-dialog title="修改" :visible.sync="dialogFormVisibleUpdate">
   <h3 v-if="multipleSelection.length==0">请先选择一条信息修改</h3> 
   <el-form :model="formUpdate" ref="ruleFormUpdate" v-else>
     <el-form-item label="姓名" :label-width="formLabelWidth">
       <el-input v-model="formUpdate.userName" auto-complete="off"></el-input>
     </el-form-item>
     <el-form-item label="活动区域" :label-width="formLabelWidth">
       <el-select v-model="formUpdate.address" placeholder="请选择活动区域">
         <el-option label="区域一" value="shanghai"></el-option>
         <el-option label="区域二" value="beijing"></el-option>
       </el-select>
     </el-form-item>
   </el-form>
   <div slot="footer" class="dialog-footer">
     <el-button @click="dialogFormVisibleUpdate = false">取 消</el-button>
     <el-button type="primary" @click="dialogFormVisibleUpdate = false;ruleFormUpdate('ruleFormUpdate')">确 定</el-button>
   </div>
 </el-dialog>

<el-button type="primary" plain @click="userManageDelete = true" class="userManageDelete">
      <i class="el-icon-plus"></i>
      删除
</el-button>

<el-dialog title="删除" :visible.sync="userManageDelete">
   <h3 v-if="multipleSelection.length==0">请先选择一条信息删除</h3> 
   <el-form :model="formDelete" ref="formDelete" v-else> 
     <h3>确定删除这条信息吗?</h3>
   </el-form>
   <div slot="footer" class="dialog-footer">
     <el-button @click="userManageDelete = false">取 消</el-button>
     <el-button type="primary" @click="userManageDelete = false;ruleFormDelete('formDelete')" v-if="multipleSelection.length>0">确 定</el-button>
   </div>
 </el-dialog>

    <el-table
    ref="multipleTable"
      :data="tableData"
      tooltip-effect="dark"
      style="width: 100%;margin-top:60px;"
      @selection-change="handleSelectionChange"
      border>
      <el-table-column
        type="selection"
        width="55">
      </el-table-column>
      <el-table-column
        prop="userName"
        label="登录名"
        :show-overflow-tooltip="true"
        width="180"
     >
     </el-table-column>
     <el-table-column
       prop="roleName"
       label="用户姓名"
       :show-overflow-tooltip="true"
       width="180">
     </el-table-column>
     <el-table-column
       prop="tenantId"
       :show-overflow-tooltip="true"
       label="联系电话">
     </el-table-column>
     <el-table-column
       prop="email"
       :show-overflow-tooltip="true"
       label="邮箱">
     </el-table-column>
     
   </el-table>

   <!-- 分页 -->
   <div class="block">
     <el-pagination
       @size-change="handleSizeChange"
       @current-change="handleCurrentChange"
       :current-page="currentPage4"
       :page-sizes="[10, 20, 40, 60]"
       :page-size="page.pageSize"
       layout="total, sizes, prev, pager, next, jumper"
       :total="totalCount">
     </el-pagination>
   </div>
   </div>
</template>
 
 <script>
export default {
  name:'UserManage',
  data() {
    return {
      totalCount: 0,
      tableData: [
        {
          userName: "王一虎",
          roleName: "王小虎",
          tenantId: "12345678911",
          email: "88888888",
          id: 1
        },
        {
          userName: "王二虎",
          roleName: "王小虎",
          tenantId: "12345678922",
          email: "88888888",
          id: 5
        },
        {
          userName: "王三虎",
          roleName: "王小虎",
          tenantId: "12345678933",
          email: "88888888"
        },
        {
          userName: "王四虎",
          roleName: "王小虎",
          tenantId: "12345678944",
          email: "88888888"
        }
      ],
      dialogTableVisible: false,
      dialogFormVisible: false,
      dialogFormVisibleLook: false,
      dialogFormVisibleUpdate: false,
      userManageDelete: false,
      form: {
        userName: "",
        roleName: "",
        password: "",
        passwordTwo: "",
        eamil: "",
        telephone: "",
        address: ""
      },
      formLook: {
        userName: "",
        roleName: "",
        password: "",
        passwordTwo: "",
        eamil: "",
        telephone: "",
        address: ""
      },
      formUpdate: {
        userName: "",
        roleName: "",
        password: "",
        passwordTwo: "",
        eamil: "",
        telephone: "",
        address: ""
      },
      formDelete: {
        userName: "",
        roleName: "",
        password: "",
        passwordTwo: "",
        eamil: "",
        telephone: "",
        address: ""
      },
      formLabelWidth: "80px",
      multipleSelection: [],
      currentPage4: 1,
      access_token: "",
      page: {
        pageNo: 1,
        pageSize: 10,
        Authorization: ""
      }
    };
  },
  components: {},
  methods: {
    //请求数据
    userList() {
      this.page = {
        pageNo: this.page.pageNo,
        pageSize: this.page.pageSize,
        Authorization: this.access_token
      };
      // this.$store
      //   .dispatch("userList", this.page)
      //   .then(res => {
      //     if (res.code == 0) {
      //       this.tableData = res.data.result;
      //       this.totalCount = res.data.totalCount;
      //     }
      //   })
      //   .catch(e => {
      //     this.$alert(e);
      //   });
    },
    //提交新建
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          //console.log(valid);
          //console.log(this.form);
        } else {
          //console.log("error submit!!");
          return false;
        }
      });
    },
    ruleFormUpdate(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          //console.log(this.formUpdate);
        } else {
          return false;
        }
      });
    },
    ruleFormDelete(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          //console.log(this.formDelete);
        } else {
          return false;
        }
      });
    },
    //选中行的数据;
    handleSelectionChange(val) {
      this.multipleSelection = val;
      let Selection = Array.prototype.slice.call(val);
      this.formLook = Selection[0];
      this.formUpdate = Selection[0];
      this.formDelete = Selection[0];
    },
    formatter(row, column) {
      return row.address;
    },
    filterTag(value, row) {
      return row.tag === value;
    },
    filterHandler(value, row, column) {
      const property = column["property"];
      return row[property] === value;
    },
    //分页
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.page.pageSize = val;
      this.page = {
        pageNo: this.page.pageNo,
        pageSize: this.page.pageSize,
        Authorization: this.access_token
      };
      this.$store
        .dispatch("userList", this.page)
        .then(res => {
          if (res.code == 0) {
            this.tableData = res.data.result;
            this.totalCount = res.data.totalCount;
          }
        })
        .catch(e => {
          this.$alert(e);
        });
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.page.pageNo = val;
      this.page = {
        pageNo: this.page.pageNo,
        pageSize: this.page.pageSize,
        Authorization: this.access_token
      };
      this.$store
        .dispatch("userList", this.page)
        .then(res => {
          if (res.code == 0) {
            this.tableData = res.data.result;
            this.totalCount = res.data.totalCount;
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
  },
  destroyed() {}
};
</script>
<style scoped lang='less'>
@import './css/UserManage.css';
</style>