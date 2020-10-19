<template>
  <!-- 笔记管理 -->
  <div class="page">
    <div class="page_hearder">
      <h3 style="color:#fff;">{{getBookName}} - {{getChapterName}} -笔记管理</h3>
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
              label="标题"
              border
              size="mini"
              style="float:left;margin-bottom:2px;width:100%;text-align:left;padding-top:5px;"
            ></el-checkbox>
          </el-dropdown-item>
          <el-dropdown-item>
            <el-checkbox
              v-model="showArray[1].isShow"
              label="内容"
              border
              size="mini"
              style="float:left;margin-bottom:2px;width:100%;text-align:left;padding-top:5px;"
            ></el-checkbox>
          </el-dropdown-item>
          <el-dropdown-item>
            <el-checkbox
              v-model="showArray[1].isShow"
              label="见解"
              border
              size="mini"
              style="float:left;margin-bottom:2px;width:100%;text-align:left;padding-top:5px;"
            ></el-checkbox>
          </el-dropdown-item>

          <el-dropdown-item>
            <el-checkbox
              v-model="showArray[3].isShow"
              label="重要程度"
              border
              size="mini"
              style="float:left;margin-bottom:2px;width:100%;text-align:left;padding-top:5px;"
            ></el-checkbox>
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>

    <el-dialog title="新建笔记" :visible.sync="dialogFormVisible" center width="80%">
      <el-form :model="formNew" ref="ruleForm" :rules="rulesNew">
        <el-form-item label="书名" :label-width="formLabelWidth" class="item100" prop="bookName">
          <el-input v-model="getBookName" :disabled="true" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item
          label="章节"
          :label-width="formLabelWidth"
          class="item100"
          prop="chapterName"
          v-if="getChapterId"
        >
          <el-input v-model="getChapterName" :disabled="true" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item
          label="章节"
          :label-width="formLabelWidth"
          class="item100"
          prop="name"
          v-if="!getChapterId"
        >
          <el-cascader
            v-model="formNew.chapterId"
            :options="treeData"
            :props="{ checkStrictly: true }"
            :show-all-levels="false"
            @change="handleParentIdChange"
          ></el-cascader>
        </el-form-item>
        <el-form-item label="标题" :label-width="formLabelWidth" class="item100" prop="title">
          <el-input v-model="formNew.title" auto-complete="off"></el-input>
        </el-form-item>

        <el-form-item label="内容" :label-width="formLabelWidth" class="item100" prop="answer">
          <!-- <el-input type="textarea" v-model="formNew.content" autosize auto-complete="off"></el-input> -->
          <mavon-editor v-model="formNew.content" ref="addmd" @imgAdd="onImgAdd" />
        </el-form-item>
        <el-form-item label="见解" :label-width="formLabelWidth" class="item100" prop="answer">
          <!-- <el-input type="textarea" v-model="formNew.think" autosize auto-complete="off"></el-input> -->
          <mavon-editor v-model="formNew.think" />
        </el-form-item>
        <el-form-item label="重要程度" :label-width="formLabelWidth" class="item100" prop="answer">
          <el-input type="number" v-model="formNew.degree" autosize auto-complete="off"></el-input>
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

    <el-dialog title="修改" :visible.sync="dialogFormVisibleUpdate" center width="80%">
      <h3 v-if="multipleSelection.length==0">请先选择一条信息修改</h3>
      <h3 v-else-if="multipleSelection.length>1">请选择一条信息修改</h3>
      <el-form :model="formUpdate" ref="ruleFormUpdate" v-else :rules="rulesUpdate">
        <el-form-item label="书名" :label-width="formLabelWidth" class="item100" prop="bookName">
          <el-input v-model="getBookName" :disabled="true" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item
          label="章节"
          :label-width="formLabelWidth"
          class="item100"
          prop="chapterName"
          v-if="getChapterId"
        >
          <el-input v-model="getChapterName" :disabled="true" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item
          label="章节"
          :label-width="formLabelWidth"
          class="item100"
          prop="name"
          v-if="!getChapterId"
        >
          <el-cascader
            v-model="formUpdate.chapterId"
            :options="treeData"
            :props="{ checkStrictly: true }"
            :show-all-levels="false"
            @change="handleParentIdChange"
          ></el-cascader>
        </el-form-item>
        <el-form-item label="标题" :label-width="formLabelWidth" class="item100" prop="title">
          <el-input v-model="formUpdate.title" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="内容" :label-width="formLabelWidth" class="item100" prop="answer">
          <!-- <el-input type="textarea" v-model="formUpdate.content" autosize auto-complete="off"></el-input> -->
          <mavon-editor v-model="formUpdate.content" ref="updatemd" @imgAdd="onImgAdd2" />
        </el-form-item>
        <el-form-item label="见解" :label-width="formLabelWidth" class="item100" prop="answer">
          <!-- <el-input type="textarea" v-model="formUpdate.think" autosize auto-complete="off"></el-input> -->
          <mavon-editor v-model="formUpdate.think" />
        </el-form-item>
        <el-form-item label="重要程度" :label-width="formLabelWidth" class="item100" prop="answer">
          <el-input type="number" v-model="formUpdate.degree" autosize auto-complete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <div class="div-close-dialog" v-show="closeDialog == '1'">
          <el-radio v-model="closeDialog" label="0">不关闭</el-radio>
        </div>
        <div class="div-close-dialog" v-show="closeDialog == '0'">
          <el-radio v-model="closeDialog" label="1">关闭</el-radio>
        </div>

        <el-button @click="ruleFormUpdateNo">取 消</el-button>
        <el-button
          type="primary"
          @click="ruleFormUpdate('ruleFormUpdate')"
          v-if="multipleSelection.length==1"
        >确 定</el-button>
        <el-button type="primary" @click="nextItem(-1)">上一个</el-button>
        <el-button type="primary" @click="nextItem(1)">下一个</el-button>
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
        prop="title"
        label="标题"
        :show-overflow-tooltip="true"
        width="280"
        v-if="showArray[0].isShow"
      ></el-table-column>

      <el-table-column
        prop="content"
        :show-overflow-tooltip="true"
        label="解答"
        width="360"
        v-if="showArray[1].isShow"
      ></el-table-column>

      <el-table-column
        prop="think"
        :show-overflow-tooltip="true"
        label="见解"
        width="360"
        v-if="showArray[2].isShow"
      ></el-table-column>
      <el-table-column
        prop="degree"
        :show-overflow-tooltip="true"
        label="重要程度"
        width="360"
        v-if="showArray[3].isShow"
      ></el-table-column>
      <el-table-column
        prop="createTime"
        :show-overflow-tooltip="true"
        label="修改时间"
        width="360"
        v-if="showArray[4].isShow"
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
import { mapGetters } from "vuex";
import { mapMutations } from "vuex";
export default {
  name: "manager",
  inject: ["reload"],
  data() {
    return {
      closeDialog: "1",
      showArray: [
        { label: "标题", isShow: true },
        { label: "内容", isShow: true },
        { label: "见解", isShow: true },
        { label: "重要程度", isShow: true },
        { label: "创建时间", isShow: true }
      ],
      tableDataArr: [],
      //右侧选择项
      options: [
        {
          value: "标题",
          label: "标题"
        }
      ],
      value: "标题",
      search: "",
      totalCount: 0,
      //表格数据
      tableData: [],
      treeData: [
        {
          value: "",
          label: "无"
        }
      ],
      //弹出框设置
      dialogTableVisible: false,
      dialogFormVisible: false,
      dialogFormVisibleLook: false,
      dialogFormVisibleUpdate: false,
      pageDelete: false,
      //新建提交的表单内容
      formNew: {
        title: "",
        content: "",
        think: "",
        chapterId: 0,
        degree: ""
      },
      //新建的验证
      rulesNew: {
        title: [{ required: true, message: "请输入标题", trigger: "blur" }],
        chapterId: [{ required: true, message: "请选择章节", trigger: "blur" }],
        content: [{ required: true, message: "请输入内容", trigger: "blur" }]
      },
      //修改的验证
      rulesUpdate: {
        title: [{ required: true, message: "请输入标题", trigger: "blur" }],
        chapterId: [{ required: true, message: "请选择章节", trigger: "blur" }],
        content: [{ required: true, message: "请输入内容", trigger: "blur" }]
      },
      //查看的内容
      formLook: {
        id: "",
        title: "",
        content: "",
        chapter: "",
        think: "",
        degree: ""
      },
      //更新的内容
      formUpdate: {
        id: "",
        title: "",
        content: "",
        chapter: {},
        chapterId: 0,
        think: "",
        degree: ""
      },
      //删除的内容
      formDelete: {
        id: "",
        title: "",
        content: "",
        think: "",
        chapter: {},
        chapterId: 0,
        degree: "",
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
      }
    };
  },
  computed: {
    // 使用对象展开运算符将 getter 混入 computed 对象中
    ...mapGetters([
      "getBookId",
      "getBookName",
      "getChapterId",
      "getChapterName"
      // ...
    ])
  },
  components: {},
  methods: {
    ...mapMutations(["SET_PAGE"]),
    handleParentIdChange(value) {
      console.info(value);
      if (this.dialogFormVisible) {
        this.formNew.chapterId = value[value.length - 1];
      }

      if (this.dialogFormVisibleUpdate) {
        this.formUpdate.chapterId = value[value.length - 1];
      }
    },
    //筛选
    filterHandler(value, row, column) {
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
      // this.$message("click on item " + command);
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
        .dispatch("bookContentImportExcel", fd)
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
        .dispatch("bookContentSerializable", this.page)
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
        chapterId: this.getChapterId,
        pageNo: this.page.pageNo,
        pageSize: this.page.pageSize,
        Authorization: this.access_token
      };
      this.$store
        .dispatch("bookContentList", this.page)
        .then(res => {
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
          this.$alert("未知错误");
        });
    },
    //提交新建信息
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          //提交时验证标题是否重复
          // let page = {
          //   Authorization: this.access_token,
          //   answer: this.formNew.title
          // };
          if (!this.getBookId) {
            this.$alert("未选择书籍");
            return;
          }
          this.formNew.bookId = this.getBookId;
          if (this.getChapterId) {
            this.formNew.chapterId = this.getChapterId;
          }
          this.formNew.Authorization = this.access_token;
          this.$store
            .dispatch("createBookContent", this.formNew)
            .then(res => {
              //console.log(res)
              if (res.code == 0) {
                this.$message({
                  type: "success",
                  message: "新增成功"
                });
                this.reload();
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
        title: "",
        content: "",
        chpaterId: 0,
        think: "",
        degree: ""
      };
      this.reload();
    },
    //取消新建信息时情空
    submitFormNo() {
      this.dialogFormVisible = false;
      this.formNew = {
        title: "",
        chpaterId: 0,
        content: "",
        think: "",
        degree: ""
      };
    },
    //修改
    ruleFormUpdate(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          if (!this.getBookId) {
            this.$alert("未选择书籍");
            return;
          }
          this.formNew.bookId = this.getBookId;
          if (this.getChapterId) {
            this.formNew.chapterId = this.getChapterId;
          }
          this.formUpdate.Authorization = this.access_token;
          this.$store
            .dispatch("updateBookContent", this.formUpdate)
            .then(res => {
              if (res.code == 0) {
                this.$message({
                  type: "success",
                  message: "修改成功"
                });
              } else {
                this.$alert("提交失败");
              }
            })
            .catch(e => {
              this.$alert("未知错误");
              //console.log("chucuole"+JSON.stringify(e))
            });
          if (this.closeDialog == "1") {
            this.dialogFormVisibleUpdate = false;
            this.reload();
          }
        } else {
          return false;
        }
      });
    },
    ruleFormUpdateNo() {
      this.dialogFormVisibleUpdate = false;
      this.reload();
    },
    //删除笔记
    ruleFormDelete(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          let page = {
            id: this.formDelete.id,
            Authorization: this.access_token
          };
          this.$store
            .dispatch("deleteBookContent", page)
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
    nextItem(next) {
      var cur = this.formUpdate;
      var nextIndex = 0;
      var len = this.tableData.length;
      for (let index = 0; index < len; index++) {
        const element = this.tableData[index];
        if (cur.id == element.id) {
          nextIndex = index + next;
          break;
        }
      }
      if (nextIndex == -1) {
        this.$message({
          type: "warning",
          message: "没有上一个了"
        });
      } else if (nextIndex == len) {
        this.$message({
          type: "warning",
          message: "没有下一个了"
        });
      } else {
        this.$refs.multipleTable.toggleRowSelection(this.formUpdate);
        this.handleSelectionChange([this.tableData[nextIndex]]);
        this.$refs.multipleTable.toggleRowSelection(this.formUpdate);
      }
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
    },
    getTreeData() {
      if (!this.getBookId) {
        this.$alert("未选择书籍");
        return;
      }
      this.page = {
        bookId: this.getBookId,
        chapterId: this.getChapterId,
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
    onImgAdd2(pos, file) {
      var _this = this;
      var fd = new FormData();
      fd.append("image", file);
      fd.append("Authorization", this.access_token);
      this.$store
        .dispatch("uploadImage", fd)
        .then(res => {
          if (res.code == 0) {
            this.$message({
              message: "上传成功",
              type: "success"
            });
            console.info(res);
            _this.$refs.updatemd.$imglst2Url([[pos, res.data[0]]]);
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
    onImgAdd(pos, file) {
      var _this = this;
      var fd = new FormData();
      fd.append("image", file);
      fd.append("Authorization", this.access_token);
      this.$store
        .dispatch("uploadImage", fd)
        .then(res => {
          if (res.code == 0) {
            this.$message({
              message: "上传成功",
              type: "success"
            });
            console.info(res);
            _this.$refs.addmd.$imglst2Url([[pos, res.data[0]]]);
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
@import "./css/base.css";
@import "./css/bookContent.css";
</style>