<template>
  <!-- 书籍管理 -->
  <div class="page">
    <div class="page_hearder">
      <h3 style="color:#fff;">书籍管理</h3>
    </div>
    <el-button type="primary" plain @click="dialogFormVisible = true" class="pageNew" size="mini">
      <i class="el-icon-plus"></i>
      新建
    </el-button>

    <!-- 右侧选择框 -->
    <div style="float:right;">
      <div style="float:left;margin-right:25px;">
        <el-select
          v-model="value"
          placeholder="请选择查询条件"
          @change="changeValue"
          style="width:120px;left:10px;"
          size="mini"
        >
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          ></el-option>
        </el-select>
        <el-input v-model="search" placeholder="请输入查询内容" style="width:140px;left:10px;" size="mini"></el-input>
        <el-button
          @click="blurInput"
          type="primary"
          style="width:80px;position:relative;right:-16px;"
          size="mini"
          icon="el-icon-search"
        >查询</el-button>
      </div>

      <el-dropdown
        type="primary"
        @click="handleClick"
        style="float:left;margin-left:3px;"
        @command="handleCommand"
      >
        <el-button
          type="primary"
          szie="mini"
          style="height:29px;padding-bottom:5px;box-sizing: border-box;"
        >
          <span style="position:relative;top:-5px;">
            更多菜单
            <i class="el-icon-arrow-down el-icon--right"></i>
          </span>
        </el-button>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item command="in">
            <el-upload
              class="upload-demo"
              ref="upload"
              action="/api"
              :on-success="handleSuccess"
              :before-upload="beforeUpload"
              multiple
              :limit="3"
              :show-file-list="false"
            >
              <el-button size="small" type="text">导入</el-button>
            </el-upload>
          </el-dropdown-item>
          <el-dropdown-item command="out">
            <el-button size="small" type="text">导出</el-button>
          </el-dropdown-item>
          <el-dropdown-item command="md">
            <el-button size="small" type="text">MD</el-button>
          </el-dropdown-item>
          <el-dropdown-item command="download">
            <el-button size="small" type="text">下载模板</el-button>
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>

      <!-- 列筛选 -->
      <!-- 列筛选 -->
      <el-dropdown :hide-on-click="false" style="float:right;">
        <el-button type="primary" size="mini">
          列筛选
          <i class="el-icon-arrow-down el-icon--right"></i>
        </el-button>
        <el-dropdown-menu slot="dropdown" style="backgroundColor:#f5f5f5;">
          <el-dropdown-item>
            <el-checkbox
              v-model="showArray[0].isShow"
              label="书名"
              border
              size="mini"
              style="float:left;margin-bottom:2px;width:100%;text-align:left;padding-top:5px;"
            ></el-checkbox>
          </el-dropdown-item>

          <el-dropdown-item>
            <el-checkbox
              v-model="showArray[1].isShow"
              label="分类"
              border
              size="mini"
              style="float:left;margin-bottom:2px;width:100%;text-align:left;padding-top:5px;"
            ></el-checkbox>
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>

    <el-dialog title="新建书籍" :visible.sync="dialogFormVisible" center>
      <el-form :model="formNew" ref="ruleForm" :rules="rulesNew">
        <el-form-item label="书名" :label-width="formLabelWidth" class="item100" prop="name">
          <el-input v-model="formNew.name" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="分类" :label-width="formLabelWidth" class="item100" prop="type">
          <el-select v-model="formNew.type" placeholder="请选择分类" @change="changeValue" style>
            <el-option v-for="item in types" :key="item.id" :label="item.text" :value="item.value"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="submitFormNo">取 消</el-button>
        <el-button type="primary" @click="submitForm('ruleForm')">确 定</el-button>
      </div>
    </el-dialog>

    <el-button
      type="primary"
      plain
      @click="dialogFormVisibleUpdate = true"
      class="pageUpdate"
      size="mini"
    >
      <i class="el-icon-edit"></i>
      修改
    </el-button>
    <el-button type="primary" plain @click="toChapter()" class="pageUpdate" size="mini">
      <i class="el-icon-edit"></i>
      章节
    </el-button>

    <el-button type="primary" plain @click="toContent()" class="pageUpdate" size="mini">
      <i class="el-icon-edit"></i>
      笔记
    </el-button>

    <el-dialog title="修改" :visible.sync="dialogFormVisibleUpdate" center>
      <h3 v-if="multipleSelection.length==0">请先选择一条信息修改</h3>
      <h3 v-else-if="multipleSelection.length>1">请选择一条信息修改</h3>
      <el-form :model="formUpdate" ref="ruleFormUpdate" v-else :rules="rulesUpdate">
        <el-form-item label="书名" :label-width="formLabelWidth" class="item100" prop="name">
          <el-input v-model="formUpdate.name" auto-complete="off"></el-input>
        </el-form-item>

        <el-form-item label="分类" :label-width="formLabelWidth" class="item100" prop="type">
          <el-select v-model="formUpdate.type" placeholder="请选择查询条件" @change="changeValue" style>
            <el-option v-for="item in types" :key="item.id" :label="item.name" :value="item.id"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="ruleFormUpdateNo">取 消</el-button>
        <el-button
          type="primary"
          @click="ruleFormUpdate('ruleFormUpdate')"
          v-if="multipleSelection.length==1"
        >确 定</el-button>
      </div>
    </el-dialog>

    <el-button type="primary" plain @click="pageDelete = true" class="pageDelete" size="mini">
      <i class="el-icon-delete"></i>
      删除
    </el-button>

    <el-dialog title="删除" :visible.sync="pageDelete">
      <h3 v-if="multipleSelection.length==0">请先选择一条信息删除</h3>
      <el-form :model="formDelete" ref="formDelete" v-else>
        <h3>确定删除这条信息吗?</h3>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="pageDelete = false">取 消</el-button>
        <el-button
          type="primary"
          @click="ruleFormDelete('formDelete')"
          v-if="multipleSelection.length>0"
        >确 定</el-button>
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
      border
    >
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column
        prop="name"
        label="书名"
        :show-overflow-tooltip="true"
        width="280"
        v-if="showArray[0].isShow"
      ></el-table-column>
      <el-table-column
        prop="type"
        :show-overflow-tooltip="true"
        label="分类"
        :filters="types"
        :filter-method="filterHandler"
        width="180"
        v-if="showArray[2].isShow"
      ></el-table-column>
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
        :total="totalCount"
      ></el-pagination>
    </div>
  </div>
</template>
 
 <script>
import { mapMutations } from "vuex";
export default {
  name: "manager",
  inject: ["reload"],
  data() {
    return {
      showArray: [
        { label: "书名", isShow: true },
        { label: "解答", isShow: true },
        { label: "分类", isShow: true },
        { label: "链接", isShow: true }
      ],
      tableDataArr: [],
      //右侧选择项
      options: [
        {
          value: "书名",
          label: "书名"
        }
      ],
      value: "书名",
      search: "",
      totalCount: 0,
      //表格数据
      tableData: [],
      //弹出框设置
      dialogTableVisible: false,
      dialogFormVisible: false,
      dialogFormVisibleLook: false,
      dialogFormVisibleUpdate: false,
      pageDelete: false,
      //新建提交的表单内容
      formNew: {
        name: "",
        answer: "",
        href: "",
        type: ""
      },
      //新建的验证
      rulesNew: {
        name: [{ required: true, message: "请输入书名", trigger: "blur" }],
        type: [{ required: true, message: "请选择分类", trigger: "blur" }]
      },
      //修改的验证
      rulesUpdate: {
        name: [{ required: true, message: "请输入书名", trigger: "blur" }],
        type: [{ required: true, message: "请选择分类", trigger: "blur" }]
      },
      //查看的内容
      formLook: {
        id: "",
        name: "",
        answer: "",
        eamil: ""
      },
      //更新的内容
      formUpdate: {
        id: "",
        name: "",
        answer: "",
        href: "",
        type: ""
      },
      //删除的内容
      formDelete: {
        id: "",
        name: "",
        answer: "",
        href: "",
        type: "",
        Authorization: this.access_token
      },
      formLabelWidth: "80px",
      multipleSelection: [],
      currentPage4: 1,
      access_token: "",
      id: "",
      page: {
        pageNo: 1,
        pageSize: 20,
        Authorization: ""
      },
      types: [
        { text: "JAVA", value: "JAVA" },
        { text: "JS", value: "JS" },
        { text: "数据库", value: "DB" },
        { text: "LINUX", value: "LINUX" },
        { text: "算法", value: "ARITHMETIC" },
        { text: "HTML/CSS", value: "HTML" },
        { text: "ANDROID", value: "ANDROID" }
      ]
    };
  },
  components: {},
  methods: {
    ...mapMutations(["SET_PAGE", "SET_BOOK"]),
    //筛选
    filterHandler(value, row, column) {
      return row["type"] === value;
    },
    //后端排序
    sortChange(column) {
      console.log(column);
    },
    //表头样式
    getRowClass({ row, column, rowIndex, columnIndex }) {
      if (rowIndex == 0) {
        return "background:#efeff7";
      } else {
        return "";
      }
    },

    //最右边的点击导出,导入,下载模板参数
    handleCommand(command) {
      this.$message("click on item " + command);
      if (command == "out") {
        this.outData();
      }
      if (command == "md") {
        this.outMD();
      }
    },

    handleSuccess() {
      this.$message({
        message: "上传成功",
        type: "success"
      });
    },
    beforeUpload(file) {
      var fd = new FormData();
      fd.append("file", file);
      fd.append("Authorization", this.access_token);
      this.$store
        .dispatch("bookImportExcel", fd)
        .then(res => {
          if (res.code == 0) {
            this.$message({
              message: "上传成功",
              type: "success"
            });
          } else {
            this.$message({
              message: "上传失败",
              type: "warning"
            });
          }
        })
        .catch(e => {
          this.$message({
            message: "上传失败",
            type: "warning"
          });
        });
    },
    outMD() {
      if (this.multipleSelection.length == 0) {
        this.$message({
          type: "warning",
          message: "请先选择一本书籍导出"
        });
        return;
      }
      if (this.multipleSelection.length > 1) {
        this.$message({
          type: "warning",
          message: "请选择一本书籍导出"
        });
        return;
      }
      this.page.bookId = this.formLook.id;
      this.$store
        .dispatch("bookToMD", this.page)
        .then(res => {
          if (res.code == 0) {
            this.$message({
              type: "success",
              message: "导出成功"
            });
          } else {
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
    outData() {
      this.$store
        .dispatch("bookSerializable", this.page)
        .then(res => {
          if (res.code == 0) {
            this.$message({
              type: "success",
              message: "导出成功"
            });
          } else {
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
    handleClick() {
      // this.$alert('请选择条件');
    },
    //右侧选择条件
    changeValue(val) {
      //console.log(this.value)
    },
    //点击搜索的时候
    blurInput() {
      console.log(this.search);
      this.pageList();
    },
    //请求数据
    pageList() {
      this.SET_PAGE(this.page); //保存一下页面信息  避免reload参数为空
      var page = {
        search: this.search,
        Authorization: this.access_token
      };
      this.$store
        .dispatch("bookList", page)
        .then(res => {
          console.info(res);
          if (res.code == 0) {
            console.log(res.data.result);
            this.tableData = res.data.result;
            this.tableDataArr = res.data.result;
            this.totalCount = res.data.totalCount;
          } else {
            this.$message({
              type: "warning",
              message: "系统错误"
            });
          }
        })
        .catch(e => {
          
          console.info("error");
          this.$alert("未知错误");
        });

    },
    //提交新建信息
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          //提交时验证书名是否重复
          // let page = {
          //   Authorization: this.access_token,
          //   name: this.formNew.name
          // };

          this.formNew.Authorization = this.access_token;
          this.$store
            .dispatch("createBook", this.formNew)
            .then(res => {
              //console.log(res)
              if (res.code == 0) {
                this.$message({
                  type: "success",
                  message: "新增成功"
                });
                this.pageList();
              } else {
                this.$alert("提交失败");
              }
            })
            .catch(e => {
              this.$alert("未知错误");
              //console.log("chucuole"+JSON.stringify(e))
            });
        }
      });
      this.dialogFormVisible = false;
      this.formNew = {
        name: "",
        type: ""
      };
      this.reload();
    },
    //取消新建信息时情空
    submitFormNo() {
      this.dialogFormVisible = false;
      this.formNew = {
        name: "",
        type: ""
      };
    },
    //修改
    ruleFormUpdate(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          if (this.formUpdate.id == this.formUpdate.parentId) {
            this.$alert("不能选择自身作为父级");
            return;
          }
          this.formUpdate.Authorization = this.access_token;
          this.$store
            .dispatch("updateBook", this.formUpdate)
            .then(res => {
              if (res.code == 0) {
                this.$message({
                  type: "success",
                  message: "修改成功"
                });
                this.pageList();
              } else {
                this.$alert("提交失败");
              }
            })
            .catch(e => {
              this.$alert("未知错误");
              //console.log("chucuole"+JSON.stringify(e))
            });
          this.dialogFormVisibleUpdate = false;
          this.reload();
        } else {
          return false;
        }
      });
    },
    ruleFormUpdateNo() {
      this.dialogFormVisibleUpdate = false;
      this.reload();
    },
    //删除书籍
    ruleFormDelete(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          let page = {
            id: this.formDelete.id,
            Authorization: this.access_token
          };
          this.$store
            .dispatch("deleteBook", page)
            .then(res => {
              if (res.code == 0) {
                this.$message({
                  type: "success",
                  message: "删除成功"
                });
                this.reload();
              } else {
                this.$message({
                  type: "warning",
                  message: "系统错误"
                });
              }
            })
            .catch(e => {
              // this.$alert(e);
              this.$message({
                type: "warning",
                message: "系统错误"
              });
            });
          this.pageDelete = false;
        } else {
          return false;
        }
      });
    },
    //选中行的数据;
    handleSelectionChange(val) {
      console.log(val);
      this.multipleSelection = val;
      let Selection = Array.prototype.slice.call(val);
      this.formLook = Selection[0];
      this.formUpdate = Selection[0];
      this.formDelete = Selection[0];
    },
    toContent() {
      if (this.multipleSelection.length == 0) {
        this.$message({
          type: "warning",
          message: "请先选择一本书籍查看"
        });
        return;
      }
      if (this.multipleSelection.length > 1) {
        this.$message({
          type: "warning",
          message: "请选择一本书籍查看"
        });
        return;
      }
      this.SET_BOOK(this.formLook);
      this.$router.push({ path: "/user/bookContent" });
    },
    toChapter() {
      if (this.multipleSelection.length == 0) {
        this.$message({
          type: "warning",
          message: "请先选择一本书籍查看"
        });
        return;
      }
      if (this.multipleSelection.length > 1) {
        this.$message({
          type: "warning",
          message: "请选择一本书籍查看"
        });
        return;
      }
      this.SET_BOOK(this.formLook);
      this.$router.push({ path: "/user/bookChapter" });
    },
    //分页
    handleSizeChange(val) {
      //console.log(`每页 ${val} 条`);
      this.page.pageSize = val;
      this.pageList();
    },
    handleCurrentChange(val) {
      //console.log(`当前页: ${val}`);
      this.page.pageNo = val;
      this.pageList();
    }
  },
  created() {
    //请求数据
    this.access_token = localStorage.getItem("access_token");
    this.pageList();
  },
  destroyed() {}
};
</script>
<style scoped lang='less'>
@import "./css/book.css";
</style>