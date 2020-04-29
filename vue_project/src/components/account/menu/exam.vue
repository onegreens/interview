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
    <el-dialog title="测试" :visible.sync="dialogFormVisibleExam" center>
      <el-form :model="formExam" ref="ruleFormExam">
        <el-form-item label="标题" :label-width="formLabelWidth" class="item100" prop="title">
          <el-input v-model="formExam.title" auto-complete="off" disabled></el-input>
        </el-form-item>
        <el-form-item
          label="解答"
          :v-show="showAnswer"
          :label-width="formLabelWidth"
          class="item100"
          prop="answer"
        >
          <el-input type="textarea" disabled v-model="formExam.answer" autosize auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item
          label="参考资料"
          :v-show="showAnswer"
          :label-width="formLabelWidth"
          class="item100"
          prop="title"
        >
          <el-link :href="formExam.href" target="_blank">{{formExam.href}}</el-link>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="showExam(-1)">上一题</el-button>
        <el-button @click="ruleFormExamNo">取 消</el-button>
        <el-button type="primary" @click="showExam(1)">下一题</el-button>
        <el-button @click="viewAnswer">参考</el-button>
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
      formLabelWidth: "80px",
      formExam: {},
      tableData: [],
      totalCount: 0,
      examIndex: 0,
      dialogFormVisibleExam: false,
      cateId: "",
      showAnswer: false
    };
  },

  computed: {
    spanlength() {
      return 24 / this.cates.length - 1;
    }
  },
  components: {},
  methods: {
    ruleFormExamNo() {
      this.dialogFormVisibleExam = false;
      this.tableData = [];
      this.totalCount = 0;
      this.page.pageNo = 1;
      this.page.pageSize = 20;
    },
    toPractice(cateId) {
      this.page.pageNo = 1;
      this.page.pageSize = 20;
      this.cateId = cateId;
      this.dialogFormVisibleExam = true;
      this.pageList();
    },
    viewAnswer() {
      this.showAnswer = true;
    },
    showExam(num) {
      this.showAnswer = false;
      console.info(num);
      var index = this.examIndex + num;
      if (index == -1) {
        this.$message({
          message: "没有上一题了",
          type: "warning"
        });
        return;
      } else if (index == this.tableData.length) {
        this.$message({
          message: "没有下一题了",
          type: "warning"
        });
        return;
      }
      this.examIndex = index;
      this.formExam = this.tableData[this.examIndex];
    },
    pageList() {
      this.page = {
        cateId: this.cateId,
        pageNo: this.page.pageNo,
        pageSize: this.page.pageSize,
        Authorization: this.access_token
      };
      this.$store
        .dispatch("examList", this.page)
        .then(res => {
          if (res.code == 0) {
            console.log(res.data.result);
            this.tableData = res.data.result;
            this.totalCount = res.data.totalCount;
            this.showExam(0);
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
@import "./css/exam.css";
</style>