<template>
  <div class="page">
    <Title :title="vote_data.title"></Title>
    <Deadline :deadline="vote_data.deadline"></Deadline>
    <span></span>
    <Content :content="vote_data.content"></Content>
    <Progress
      v-for="item in vote_data.voteQues"
      :key="item.id"
      :data="item"
    ></Progress>
  </div>
</template>

<script>
// import { getVoteDetails } from "@/api/vote/join/detail.js";
import Title from "@/views/vote_display/title.vue";
import Content from "@/views/vote_display/content.vue";
import Deadline from "@/views/vote_display/deadline.vue";
import fill from "@/views/vote_display/fill.vue";
import Progress from "@/views/vote_display/progress.vue";

export default {
  name: "",
  data() {
    return {
      vote_data: {
        title: "一些问题", //标题
        content: "开始尝试投票", //简介
        createdTime: "",
        persons: "",
        deadline: "2021-03-21", //截止时间
        voteQues: [
          {
            //单选，多选
            id: "0", //题号  从0开始   0，1，2，3，4……
            type: "S", //类型
            queContent: "最喜欢的水果", //问题内容
            //选项
            options: [
              //第一个选项
              {
                id: "0", //题号  从0开始   0，1，2，3，4……
                content: "苹果", //内容
                type: "P", //类型和问题一样   如果需要文本框就改为文本框
                percentage: 54.32,
                other: "",
              },
              //第一个选项
              {
                id: "1", //题号  从0开始   0，1，2，3，4……
                content: "橙子", //内容
                select: 0, //选项内容  用户是否选择  0 未选, 1 选中
                type: "P", //类型和问题一样   如果需要文本框就改为文本框
                percentage: 45.68,
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
                type: "P", //类型和问题一样   如果需要文本框就改为文本框
                percentage: 21.85,
                other: "",
              },
              //第一个选项
              {
                id: "1", //题号  从0开始   0，1，2，3，4……
                content: "夜空中最亮的星", //内容
                select: 0, //选项内容  用户是否选择  0 未选, 1 选中
                type: "P", //类型和问题一样   如果需要文本框就改为文本框
                percentage: 36.23,
                other: "",
              },
              {
                id: "2", //题号  从0开始   0，1，2，3，4……
                content: "夜空中最亮的星", //内容
                select: 0, //选项内容  用户是否选择  0 未选, 1 选中
                type: "P", //类型和问题一样   如果需要文本框就改为文本框
                percentage: 41.92,
                other: "",
              },
            ],
          },
        ],
      },
      //当前可以被展示的类型
      voteType: {
        S: "single",
        M: "multiple",
      },

      isSend: false,
    };
  },
  components: {
    Title,
    Content,
    Deadline,
    fill,
    Progress,
  },
  methods: {
    //获取当前投票详细信息
    // getDetails() {
    //   getVoteDetails(this.$route.query.id).then((response) => {
    //     this.vote_data = response;
    //   });
    // },
    sendSingleAnswer(select, id) {
      //清空
      for (let i = 0; i < this.vote_data.options[id].answer.length; i++) {
        console.log(this.vote_data.options[id].answer.length);
        this.vote_data.options[id].answer[i].select = "0";
      }
      //赋值选项
      this.vote_data.options[id].answer[select].select = "1";
    },
    sendMultipleAnswer(answer, id) {
      //赋值选项
      this.vote_data.options[id].answer = answer;
    },
    sendContent(content, id) {
      this.vote_data.options[id].content = content;
    },
    filterVoteQues() {
      let voteQues = this.vote_data.voteQues;
      let voteType = Object.keys(this.voteType);
      for (let i = 0; i < voteQues.length; i++) {
        //不为展示类型就删除
        if (!voteType.includes(voteQues[i].type)) {
          voteQues.splice(i, 1);
        }
      }
    },
  },
  beforeCreate() {
    // getDetails();
  },
};
</script>

<style>
* {
  margin: 0;
  padding: 0;
  list-style: none;
}
.page {
  width: 72vw;
  margin: 50px auto;
  position: relative;
}
</style>




