<template>
  <!-- 习题管理 -->
  <div class="page">
    <div class="page_hearder">
      <h3 style="color:#fff;">习题测试</h3>
    </div>
    <el-row>
      <el-col
        :span="spanlength"
        v-for="(item, index) in cates"
        :key="index"
        :offset="index > 0 ? 1 : 0"
      >
        <el-card :body-style="{ padding: '0px' }">
          <img :src="item.url" class="image" />
          <div style="padding: 14px;">
            <span>{{item.text}}</span>
            <div class="bottom clearfix">
              <el-button type="text" class="button" @click="toPractice(item.value)">开始练习</el-button>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>
    <el-dialog title="测试" :visible.sync="dialogFormVisiblePractice" center>
      <el-form :model="formPractice" ref="ruleFormPractice">
        <el-form-item label="标题" :label-width="formLabelWidth" class="item100" prop="title">
          <el-input
            type="textarea"
            autosize
            v-model="formPractice.title"
            auto-complete="off"
            disabled
          ></el-input>
        </el-form-item>
        <el-form-item
          label="解答"
          v-if="showAnswer"
          :label-width="formLabelWidth"
          class="item100"
          prop="answer"
        >
          <el-input
            type="textarea"
            disabled
            v-model="formPractice.answer"
            autosize
            auto-complete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="参考资料" v-if="showAnswer" :label-width="formLabelWidth" class="item100">
          <el-link :href="formPractice.href" target="_blank">{{formPractice.href}}</el-link>
        </el-form-item>

        <el-form-item label="掌握程度" :label-width="formLabelWidth" class="item100">
          <el-rate v-model="formPractice.score" show-text :colors="colors" class="form-rate"></el-rate>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer" v-if="!loading">
        <el-button type="primary" @click="showPractice(-1)">上一题</el-button>
        <el-button type="primary" @click="showPractice(1)">下一题</el-button>
        <el-button v-if="!showAnswer" @click="viewAnswer">参考</el-button>
        <el-button v-if="showAnswer" @click="hideAnswer">隐藏</el-button>
        <el-button @click="ruleFormPracticeNo">完成</el-button>
      </div>
    </el-dialog>
  </div>
</template>
 
 <script>
import { mapMutations, mapGetters } from "vuex";
export default {
  name: "manager",
  inject: ["reload"],
  data() {
    return {
      cates: [
        {
          value: "JAVA",
          text: "JAVA",
          url:
            "https://static.leetcode-cn.com/cn-assets/webpack_bundles/images/algorithm_bg.90562ce29.png"
        },
        {
          value: "HTML",
          text: "HTML",
          url:
            "https://static.leetcode-cn.com/cn-assets/webpack_bundles/images/database_bg.f3bec933a.png"
        },
        {
          value: "DB",
          text: "DB",
          url:
            "https://static.leetcode-cn.com/cn-assets/webpack_bundles/images/shell_bg.c91db4414.png"
        },
        {
          value: "VUE",
          text: "VUE",
          url:
            "https://static.leetcode-cn.com/cn-assets/webpack_bundles/images/concurrency_bg.6ea4e46e1.png"
        }
      ],
      page: {
        pageNo: 1,
        pageSize: 20,
        Authorization: ""
      },
      colors: ["#99A9BF", "#F7BA2A", "#FF9900"],
      formLabelWidth: "80px",
      formPractice: {},
      tableData: [],
      totalCount: 0,
      formIndex: 0,
      dialogFormVisiblePractice: false,
      cateId: "",
      score: 0,
      showAnswer: false,
      loading: false
    };
  },

  computed: {
    spanlength() {
      return 24 / this.cates.length - 1;
    }
  },
  components: {},
  methods: {
    ruleFormPracticeNo() {
      var data = [];
      var num = 0;
      for (let index = 0; index < this.tableData.length; index++) {
        const element = this.tableData[index];
        if (element.score) {
          data.push({
            questionId: element.id,
            practiceId: element.practiceId,
            score: element.score
          });
        } else {
          num++;
        }
      }
      if (num > 0) {
        this.$confirm(
          "还有" + num + "道题未填写掌握程度,是否继续保存",
          "提示",
          {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning"
          }
        )
          .then(() => {
            this.savePractice(data);
          })
          .catch(() => {});
      } else {
        this.savePractice(data);
      }
    },
    resetForm() {
      this.tableData = [];
      this.totalCount = 0;
      this.page.pageNo = 1;
      this.page.pageSize = 10;
      this.dialogFormVisiblePractice = false;
      this.formIndex = 0;
      this.formPractice = {};
    },
    toPractice(cateId) {
      this.cateId = cateId;
      this.resetForm();
      this.pageList();
    },
    viewAnswer() {
      this.showAnswer = true;
    },
    hideAnswer() {
      this.showAnswer = false;
    },
    showPractice(num) {
      this.showAnswer = false;
      if (this.tableData.length == this.totalCount) {
        this.$message({
          message: "题库没有数据,请录入数据",
          type: "warning"
        });
        return;
      }
      var index = this.formIndex + num;
      if (index == -1) {
        this.$message({
          message: "没有上一题了",
          type: "warning"
        });
        return;
      }
      if (index == this.tableData.length) {
        var that = this;
        this.$confirm("没有下一题了,是否加载下一组", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        })
          .then(() => {
            that.page.pageNo += 1;
            that.formIndex += 1;
            that.pageList();
          })
          .catch(() => {});
      } else {
        this.formIndex = index;
        this.formPractice = this.tableData[this.formIndex];

        this.dialogFormVisiblePractice = true;
      }
    },
    //提交新建信息

    savePractice(data) {
      this.resetForm();
      var obj = {
        params: data,
        Authorization: this.access_token
      };
      this.$store
        .dispatch("savePractice", obj)
        .then(res => {
          //console.log(res)
          if (res.code == 0) {
            this.$message({
              type: "success",
              message: "保存成功"
            });
          } else {
            this.$alert("提交失败");
          }
        })
        .catch(e => {
          this.$alert("未知错误");
          //console.log("chucuole"+JSON.stringify(e))
        });
    },
    pageList() {
      this.page = {
        cateId: this.cateId,
        score: this.score,
        pageNo: this.page.pageNo,
        pageSize: this.page.pageSize,
        Authorization: this.access_token
      };
      this.loading = true;
      this.$store
        .dispatch("practiceList", this.page)
        .then(res => {
          this.loading = false;
          if (res.code == 0) {
            console.log(res.data.result);
            var array = res.data.result;
            if (array.length == 0) {
              this.$message({
                type: "warning",
                message: "没有更多题库了"
              });
            } else {
              for (let index = 0; index < array.length; index++) {
                const element = array[index];
                var question = element[0];
                var practice = element[1];
                if (practice) {
                  question.score = practice.score;
                  question.practiceId = practice.id;
                } else {
                  question.score = 0;
                  question.practiceId = "";
                }
                this.tableData.push(question);
              }
              this.totalCount = res.data.totalCount;
              this.showPractice(0);
            }
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
    }
  },
  created() {
    //请求数据
  },
  destroyed() {}
};
</script>
<style scoped lang='less'>
@import "./css/base.css";
@import "./css/practice.css";
</style>