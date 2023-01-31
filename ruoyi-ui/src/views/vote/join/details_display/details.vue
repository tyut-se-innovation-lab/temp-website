<template>
  <div class="page">
    <Title :title="vote_data.title"></Title>
    <Deadline :deadline="vote_data.deadline"></Deadline>
    <span></span>
    <Content :content="vote_data.content"></Content>
    <div v-for="item in vote_data.options" :key="item.id">
      <component
        :is="type[item.type]"
        :answer="item.answer"
        :content="item.content"
        @sendSingleAnswer="sendSingleAnswer"
        @sendContent="sendContent"
        @sendMultipleAnswer="sendMultipleAnswer"
        :question="item.question"
        :id="Number(item.id)"
        :isSend="isSend"
      ></component>
    </div>
    <submit @sendData="sendData"></submit>
  </div>
</template>

<script>
import Details from "@/api/vote/join/detail.js";
import Title from "../vote_display/title.vue";
import Content from "../vote_display/content.vue";
import Deadline from "../vote_display/deadline.vue";
import submit from "../vote_display/submit.vue";
import dropdown from "../vote_display/dropdown.vue";
import fill from "../vote_display/fill.vue";
import multiple from "../vote_display/multiple.vue";
import single from "../vote_display/single.vue";
import progress from "../vote_display/progress.vue";
export default {
  name: "",
  data() {
    return {
      details:new Details(),
      vote_data: {
        title: "一些问题", //标题
        content: "开始尝试投票", //简介
        deadline: "2021-03-21", //截止时间
        options: [
          {
            id: "0", //题号  从0开始   0，1，2，3，4……
            type: "S", //类型
            question: "最喜欢的水果", //问题内容
            //选项
            answer: [
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
            question: "最喜欢的音乐", //问题内容
            //选项
            answer: [
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
            question: "你对这件事的看法", //问题内容
            //选项
            content: "",
          },
        ],
      },
      type: {
        S: "single",
        M: "multiple",
        T: "fill",
      },
      isSend:false
    };
  },
  components: {
    Title,
    Content,
    Deadline,
    submit,
    dropdown,
    fill,
    single,
    multiple,
    progress,
  },
  methods: {
    getVoteDetails() {
      this.details.getVoteDetails(this.$route.query.id).then((response) => {
        this.vote_data = response;
      });
    },
    sendSingleSelect(select,id){
      this.details.sendSingleSelect(select,id).bind(this);
    },
    //动态更新多选题选项
    sendMultipleAnswer(answer,id){
      //赋值选项
      this.vote_data.options[id].answer=answer;
    },
    //动态更新简单题内容
    sendContent(content,id){
      this.vote_data.options[id].content=content;
    },
    //提交数据
    sendData(){
      this.details.sendVoteSelect(this.vote_data).bind(this);
    }
  },
  beforeCreate: {
    getDetails();
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