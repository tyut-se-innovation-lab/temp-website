<template>
  <div class="box">
    <Success v-if="isSuccess"></Success>
    <div class="page">
      <Title :titletext="vote_data.title"></Title>
      <Deadline :deadline="vote_data.deadline"></Deadline>
      <Content :contenttext="vote_data.content"></Content>
      <div v-for="(item, index) in vote_data.voteQues" :key="index">
        <component
          :is="type[item.type]"
          :options="item.options"
          :content="item.content"
          @sendSingleAnswer="sendSingleAnswer"
          @sendContent="sendContent"
          @sendMultipleAnswer="sendMultipleAnswer"
          :question="item.queContent"
          :index="Number(index)"
          :isSend="isSend"
        ></component>
      </div>
      <Submit @submitMethod="submitData"></Submit>
    </div>
  </div>
</template>
<script>
import JoinDetails from "@/api/vote/join/details.js";
import Title from "@/views/vote/vote_display/title.vue";
import Content from "@/views/vote/vote_display/content.vue";
import Deadline from "@/views/vote/vote_display/deadline.vue";
import Submit from "@/views/vote/vote_display/submit.vue";
import Dropdown from "@/views/vote/vote_display/dropdown.vue";
import Fill from "@/views/vote/vote_display/fill.vue";
import Multiple from "@/views/vote/vote_display/multiple.vue";
import Single from "@/views/vote/vote_display/single.vue";
import Progress from "@/views/vote/vote_display/progress.vue";
import Success from "@/views/vote/join/success/index.vue";
export default {
  name: "",
  data() {
    return {
      joindetails: new JoinDetails(),
      vote_data: {
        title: "一些问题", //标题
        content: "开始尝试投票", //简介
        deadline: "2021-03-21", //截止时间
        createdTime: "",
        persons: "",
        voteQues: [
          {
            id: "0", //题号  从0开始   0，1，2，3，4……
            type: "S", //类型
            queContent: "最喜欢的水果", //问题内容
            //选项
            options: [
              //第一个选项
              {
                id: "0", //题号  从0开始   0，1，2，3，4……
                content: "苹果", //内容
                select: 0, //选项内容  用户是否选择  0 未选, 1 选中
                type: "S", //类型和问题一样   如果需要文本框就改为文本框
                other: "",
              },
              //第一个选项
              {
                id: "1", //题号  从0开始   0，1，2，3，4……
                content: "橙子", //内容
                select: 0, //选项内容  用户是否选择  0 未选, 1 选中
                type: "S", //类型和问题一样   如果需要文本框就改为文本框
                other: "",
              },
            ],
          },
          {
            id: "1", //题号  从0开始   0，1，2，3，4……
            type: "M", //类型
            queContent: "最喜欢的音乐", //问题内容
            //选项
            options: [
              //第一个选项
              {
                id: "0", //题号  从0开始   0，1，2，3，4……
                content: "云烟成雨", //内容
                select: 0, //选项内容  用户是否选择  0 未选, 1 选中
                type: "S", //类型和问题一样   如果需要文本框就改为文本框
                other: "",
              },
              //第一个选项
              {
                id: "1", //题号  从0开始   0，1，2，3，4……
                content: "夜空中最亮的星", //内容
                select: 0, //选项内容  用户是否选择  0 未选, 1 选中
                type: "S", //类型和问题一样   如果需要文本框就改为文本框
                other: "",
              },
            ],
          },
          {
            id: "2", //题号  从0开始   0，1，2，3，4……
            type: "T", //类型
            queContent: "你对这件事的看法", //问题内容
            //选项
            content: "",
          },
        ],
      },
      type: {
        S: "Single",
        M: "Multiple",
        T: "Fill",
      },
      isSend: false,
      isSuccess: false,
    };
  },
  components: {
    Title,
    Content,
    Deadline,
    Submit,
    Dropdown,
    Fill,
    Single,
    Multiple,
    Progress,
    Success,
  },
  methods: {
    /**
     * 获取当前投票详细数据
     */
    getDetails() {
      let id = this.$route.query.id;
      this.joindetails.getDetails(id).then((res) => {
        this.vote_data = this.joindetails.fixDateData(res.data);
      });
    },
    /**
     * 传递单项选择答案
     * @param {Number} selindex 选中的选项下标
     * @param {String} index 题目下标
     */
    sendSingleAnswer(selindex, index) {
      let voteQues = this.vote_data.voteQues[index];
      //清空
      for (let i = 0; i < voteQues.options.length; i++) {
        voteQues.options[i].isSelect = "0";
      }
      //赋值选项
      voteQues.options[selindex].isSelect = "1";
    },
    /**
     * 传递多选选择答案
     * @param {Object} option 多选题对象
     * @param {String} index 题目下标
     */
    sendMultipleAnswer(option, index) {
      //赋值选项
      this.vote_data.voteQues[index] = option;
    },
    /**
     * 传递文本题答案
     * @param {Number} content 内容
     * @param {String} index 题目下标
     */
    sendContent(content, index) {
      this.vote_data.voteQues[index].content = content;
    },
    /**
     * 提交数据
     */
    submitData() {
      this.joindetails.sendVoteSelect(this.vote_data).then((res) => {
        if (res.code === 200) {
          this.submitDataSuccessly();
        }
      });
    },
    /**
     * 提交成功
     */
    submitDataSuccessly() {
      this.isSuccess = true;
    },
  },
  created() {
    this.getDetails();
  },
};
</script>
<style>
* {
  margin: 0;
  padding: 0;
  list-style: none;
}
.box {
  /* margin: 20px 0; */
  position: relative;
}
-- > .page {
  width: 72vw;
  margin: 0px auto;
  position: relative;
}
</style>