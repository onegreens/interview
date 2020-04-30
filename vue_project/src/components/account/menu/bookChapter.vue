<template>
  <!-- 书籍管理 -->
  <div class="page">
    <div class="page_hearder">
      <h3 style="color:#fff;">{{getBookName}} - 章节管理</h3>
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
              label="章节"
              border
              size="mini"
              style="float:left;margin-bottom:2px;width:100%;text-align:left;padding-top:5px;"
            ></el-checkbox>
          </el-dropdown-item>
          <el-dropdown-item>
            <el-checkbox
              v-model="showArray[1].isShow"
              label="页码"
              border
              size="mini"
              style="float:left;margin-bottom:2px;width:100%;text-align:left;padding-top:5px;"
            ></el-checkbox>
          </el-dropdown-item>
          <el-dropdown-item>
            <el-checkbox
              v-model="showArray[2].isShow"
              label="排序"
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
        <el-form-item label="书名" :label-width="formLabelWidth" class="item100" prop="bookName">
          <el-input v-model="getBookName" :disabled="true" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="章节" :label-width="formLabelWidth" class="item100" prop="name">
          <el-input v-model="formNew.name" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="来源" :label-width="formLabelWidth" class="item100" prop="name">
          <el-cascader
            v-model="formNew.parentId"
            :options="treeData"
            :props="{ checkStrictly: true }"
            @change="handleParentIdChange"
          ></el-cascader>
        </el-form-item>
        <el-form-item label="页码" :label-width="formLabelWidth" class="item100" prop="page">
          <el-input v-model="formNew.page" autosize auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="排序" :label-width="formLabelWidth" class="item100" prop="sort">
          <el-input v-model="formNew.sort" auto-complete="off"></el-input>
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
    <el-button type="primary" plain @click="toContent()" class="pageUpdate" size="mini">
      <i class="el-icon-edit"></i>
      笔记
    </el-button>
    <el-dialog title="修改" :visible.sync="dialogFormVisibleUpdate" center>
      <h3 v-if="multipleSelection.length==0">请先选择一条信息修改</h3>
      <h3 v-else-if="multipleSelection.length>1">请选择一条信息修改</h3>
      <el-form :model="formUpdate" ref="ruleFormUpdate" v-else :rules="rulesUpdate">
        <el-form-item label="书名" :label-width="formLabelWidth" class="item100" prop="bookName">
          <el-input v-model="getBookName" :disabled="true" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="章节" :label-width="formLabelWidth" class="item100" prop="name">
          <el-input v-model="formUpdate.name" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="来源" :label-width="formLabelWidth" class="item100" prop="name">
          <el-cascader
            v-model="formUpdate.parentId"
            :options="treeData"
            :props="{ checkStrictly: true }"
          ></el-cascader>
        </el-form-item>
        <el-form-item label="页码" :label-width="formLabelWidth" class="item100" prop="page">
          <el-input v-model="formUpdate.page" autosize auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="排序" :label-width="formLabelWidth" class="item100" prop="sort">
          <el-input v-model="formUpdate.sort" auto-complete="off"></el-input>
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
      style="width: 100%;positon:relative;top:10px;"
      row-key="id"
      border
      @selection-change="handleSelectionChange"
      :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
    >
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column
        prop="name"
        label="章节"
        :show-overflow-tooltip="true"
        width="580"
        v-if="showArray[0].isShow"
      ></el-table-column>

      <el-table-column
        prop="page"
        :show-overflow-tooltip="true"
        label="页码"
        width="360"
        v-if="showArray[1].isShow"
      ></el-table-column>

      <el-table-column
        prop="sort"
        :show-overflow-tooltip="true"
        label="排序"
        width="360"
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
import { default as util } from "../../../utils/util.js";
import { mapGetters } from "vuex";
import { mapMutations } from "vuex";
export default {
  name: "manager",
  inject: ["reload"],
  data() {
    return {
      left: "left",
      showArray: [
        { label: "章节", isShow: true },
        { label: "页码", isShow: true },
        { label: "排序", isShow: true }
      ],
      treeData: [
        {
          value: "",
          label: "无"
        }
      ],
      tableDataArr: [],
      //右侧选择项
      options: [
        {
          value: "章节",
          label: "章节"
        }
      ],
      value: "章节",
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
        parentId: "",
        page: "",
        sort: ""
      },
      //新建的验证
      rulesNew: {
        name: [{ required: true, message: "请输入章节", trigger: "blur" }],
        page: [{ required: true, message: "请输入页码", trigger: "blur" }],
        sort: [{ required: true, message: "请输入排序", trigger: "blur" }]
      },
      //修改的验证
      rulesUpdate: {
        name: [{ required: true, message: "请输入章节", trigger: "blur" }],
        page: [{ required: true, message: "请输入书籍名", trigger: "blur" }],

        sort: [{ required: true, message: "请输入排序", trigger: "blur" }]
      },
      //查看的内容
      formLook: {
        id: "",
        name: "",
        page: "",
        eamil: ""
      },
      //更新的内容
      formUpdate: {
        id: "",
        name: "",
        page: "",
        sort: "",
        cateId: ""
      },
      //删除的内容
      formDelete: {
        id: "",
        name: "",
        page: "",
        sort: "",
        cateId: "",
        Authorization: this.access_token
      },
      formLabelWidth: "120px",
      multipleSelection: [],
      currentPage4: 1,
      access_token: "",
      id: "",
      page: {
        pageNo: 1,
        pageSize: 20,
        Authorization: ""
      }
    };
  },
  computed: {
    // 使用对象展开运算符将 getter 混入 computed 对象中
    ...mapGetters([
      "getBookId",
      "getBookName"
      // ...
    ])
  },
  components: {},
  methods: {
    ...mapMutations(["SET_PAGE", "SET_BOOK_CHAPTER"]),
    toContent() {
      if (this.multipleSelection.length == 0) {
        this.$message({
          type: "warning",
          message: "请先选择一章节查看"
        });
        return;
      }
      if (this.multipleSelection.length > 1) {
        this.$message({
          type: "warning",
          message: "请选择一章节查看"
        });
        return;
      }
      this.SET_BOOK_CHAPTER(this.formLook);
      this.$router.push({ path: "/user/bookContent" });
    },
    handleParentIdChange(value) {
      if (this.dialogFormVisible) {
        this.formNew.parentId = value[0];
      }

      if (this.dialogFormVisibleUpdate) {
        this.formUpdate.parentId = value[0];
      }
    },
    //筛选
    filterHandler(value, row, column) {
      // console.log(column)
      // console.log(row)
      // console.log(row)
      const property = column["property"];
      return row[property] === value;
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
        .dispatch("bookChapterImportExcel", fd)
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
    outData() {
      this.$store
        .dispatch("bookChapterSerializable", this.page)
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
      if (!this.getBookId) {
        this.$alert("未选择书籍");
        return;
      }
      this.SET_PAGE(this.page); //保存一下页面信息  避免reload参数为空
      this.page = {
        search: this.search,
        bookId: this.getBookId,
        pageNo: this.page.pageNo,
        pageSize: this.page.pageSize,
        Authorization: this.access_token
      };
      this.$store
        .dispatch("bookChapterList", this.page)
        .then(res => {
          if (res.code == 0) {
            console.log(res.data.result);
            var tableData = res.data.result;
            for (let index = 0; index < tableData.length; index++) {
              const element = tableData[index];
              if (element.children) {
                util.bubbleSortObj(element.children, "sort");
              }
            }
            this.tableData = tableData;
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
          this.$alert("未知错误");
        });
    },
    //请求数据
    getTreeData() {
      if (!this.getBookId) {
        this.$alert("未选择书籍");
        return;
      }
      this.page = {
        bookId: this.getBookId,
        Authorization: this.access_token
      };
      this.$store
        .dispatch("bookChapterTreeData", this.page)
        .then(res => {
          if (res.code == 0) {
            var treeData = res.data || [];
            treeData.push({
              value: "",
              label: "无"
            });
            this.treeData = treeData;
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
    //提交新建信息
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          //提交时验证章节是否重复
          this.formNew.bookId = this.getBookId;
          this.formNew.Authorization = this.access_token;
          this.$store
            .dispatch("createBookChapter", this.formNew)
            .then(res => {
              //console.log(res)
              if (res.code == 0) {
                this.$message({
                  type: "success",
                  message: "新增成功"
                });
                this.reload();
                this.getTreeData();
              } else {
                this.$alert(res.message);
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
        page: "",
        sort: ""
      };
    },
    //取消新建信息时情空
    submitFormNo() {
      this.dialogFormVisible = false;
      this.formNew = {
        name: "",
        page: "",
        sort: ""
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
            .dispatch("updateBookChapter", this.formUpdate)
            .then(res => {
              if (res.code == 0) {
                this.$message({
                  type: "success",
                  message: "修改成功"
                });
                this.reload();
                this.getTreeData();
              } else {
                this.$alert(res.message);
              }
            })
            .catch(e => {
              this.$alert("未知错误");
              //console.log("chucuole"+JSON.stringify(e))
            });
          this.dialogFormVisibleUpdate = false;
        } else {
          return false;
        }
      });
    },
    ruleFormUpdateNo() {
      this.dialogFormVisibleUpdate = false;
      this.reload();
      this.getTreeData();
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
            .dispatch("deleteBookChapter", page)
            .then(res => {
              if (res.code == 0) {
                this.$message({
                  type: "success",
                  message: "删除成功"
                });
                this.reload();
                this.getTreeData();
              } else {
                this.$alert(res.message);
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
    this.getTreeData();
  },
  destroyed() {}
};
</script>
<style scoped lang='less'>
@import "./css/bookChapter.css";
</style>