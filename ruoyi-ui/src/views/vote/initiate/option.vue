<!-- 选项 -->
<template>
  <div>
    <el-container>
      <el-header>{{ headers.vote_name }}</el-header>
      <hr />
      <el-main>
        <div class="context">
          <!-- 标题 -->
          <titleVue @childTitle="getTitle" />
          <!-- 截止时间 -->
          <deadlineVue @childDeadline="getDeadline" />
          <!-- 投票简介 -->
          <contentVue @childContent="getContent" />
          <!--          题目-->
          <optionType @getOption="getOption" />
          <!-- 发布 -->
          <submit :sendVote="sendVote" />
        </div>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import titleVue from "./option_modules/title.vue";
import submit from "./option_modules/submit.vue";
import deadlineVue from "./option_modules/deadline.vue";
import contentVue from "./option_modules/content.vue";
import optionType from "./option_type.vue";
import { getInitiate } from "../../../api/vote/initiate/initiate";
import tree from "element-ui/packages/tree";
export default {
  name: "voteOption",
  components: {
    titleVue,
    submit,
    deadlineVue,
    contentVue,

    optionType,
  },
  data() {
    return {
      // 标题
      headers: {
        vote_name: "设置投票",
      },
      // 发送的数据
      data: {
        title: "",
        deadline: "",
        context: "",
        voteQues: [],
      },
    };
  },
  methods: {
    // 获取标题
    getTitle(val) {
      this.data.title = val;
    },
    // 获取截止时间
    getDeadline(val) {
      this.data.deadline = val;
    },
    //获取投票简介
    getContent(val) {
      this.data.context = val;
    },

    getOption(val) {
      this.data.voteQues = [];
      this.data.voteQues = [
        ...val.tempSingle,
        ...val.tempMultiple,
        ...val.tempFill,
      ];
    },
    depthMeasurement(data) {
      if (data instanceof Array) {
        for (let i = 0; i < data.length; i++) {
          if (data[i].options instanceof Array) {
            for (let j = 0; j < data[i].options.length; j++) {
              if (data[i].options[j].content === "") {
                return true;
              }
            }
          }
        }
      }
    },
    // 发布投票
    async sendVote() {
      if (this.depthMeasurement(this.data.voteQues)) {
        this.$modal.alertWarning("题目或者选项不能为空");
      } else if (this.data.title === "") {
        this.$modal.alertWarning("标题不能为空");
      } else if (this.data.deadline === "") {
        this.$modal.alertWarning("截止日期不能为空");
      } else {
        let info = await getInitiate(this.data);
        console.log(info);
        if (info.code === 200) {
          this.$modal.alertSuccess("提交成功啦");
          // this.$router.go(0);
        }
      }
    },
  },
};
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
}
.el-header,
.el-footer {
  margin: 20px;
  color: #333;
  text-align: left;
  line-height: 30px;
  height: 30px !important;
  font-size: x-large;
  font-weight: bolder;
  letter-spacing: 10px;
  font-family: "Lucida Sans", "Lucida Sans Regular", "Lucida Grande",
  "Lucida Sans Unicode", Geneva, Verdana, sans-serif;
}

.context {
  width: 90%;
  margin: 0 auto;
  padding: 0;
  height: auto;
  text-align: center;
  color: #707070;
}
</style>
