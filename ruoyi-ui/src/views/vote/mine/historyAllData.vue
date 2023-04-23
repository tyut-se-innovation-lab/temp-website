<template>
  <div>
    <el-container>
      <el-header>{{ headers.my_vote_name }}</el-header>
      <hr/>
      <div class="button">
        <el-button @click="deleteVote">撤回</el-button>
        <el-button @click="earlyTermination">提前结束</el-button>
      </div>
      <el-main>
        <div class="context">
          <historyTitle :getTitle="getTitle"/>
          <hr/>
          <historyContent :getContent="getContent"/>
          <historyQues :getQuestion="getQuestion"></historyQues>
        </div>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import historyQues from "./history_modules/historyQues.vue";
import historyTitle from "./history_modules/historyTitle.vue";
import historyContent from "./history_modules/historyContent.vue";
import {mineDelete, mineEarlyTermination} from "@/api/vote/mine";

export default {
  components: {
    historyQues,
    historyTitle,
    historyContent
  },
  data() {
    return {
      headers: {
        my_vote_name: "我的提议",
      },
      voteData: {
        "id": 0,
        "title": "踢出疯羊",
        "content": "test测试",
        "createdTime": this.dateTimeTOString("2022,11,30"),
        "deadline": this.dateTimeTOString("2022,12,01"),
        "authority": "李元芳",
        // 是否撤销
        "isWithdraw": false,
        // 状态
        "status": 0,
        "voteQues": [
          {
            "id": 0,
            "type": "S",	//类型，S表示单选，M表示多选，T表示文本框
            "queContent": "踢出疯羊",
            "options": [
              //单选，多选
              {
                "id": 0,
                "content": "踢掉",
                "isSelect": 0,
                "percentage": "",
                "type": "S",
                "other": ""
              },
              {
                "id": 1,
                "content": "不踢",
                "isSelect": 1,
                "percentage": "",
                "type": "S",
                "other": ""
              },
            ]
          },
          {
            "id": 2,
            "type": "S",	//类型，S表示单选，M表示多选，T表示文本框
            "queContent": "踢出疯羊",
            "options": [
              //单选，多选
              {
                "id": 0,
                "content": "踢掉",
                "isSelect": 0,
                "percentage": "",
                "type": "S",
                "other": ""
              },
              {
                "id": 1,
                "content": "不踢",
                "isSelect": 1,
                "percentage": "",
                "type": "S",
                "other": ""
              },
            ]
          },

          {
            "id": 3,
            "type": "S",	//类型，S表示单选，M表示多选，T表示文本框
            "queContent": "踢出疯羊",
            "options": [
              //单选，多选
              {
                "id": 0,
                "content": "踢掉",
                "isSelect": 0,
                "percentage": "",
                "type": "S",
                "other": ""
              },
              {
                "id": 1,
                "content": "不踢",
                "isSelect": 1,
                "percentage": "",
                "type": "S",
                "other": ""
              },
            ]
          },
          {
            "id": 4,
            "type": "S",	//类型，S表示单选，M表示多选，T表示文本框
            "queContent": "踢出疯羊",
            "options": [
              //单选，多选
              {
                "id": 0,
                "content": "踢掉",
                "isSelect": 0,
                "percentage": "",
                "type": "S",
                "other": ""
              },
              {
                "id": 1,
                "content": "不踢",
                "isSelect": 1,
                "percentage": "",
                "type": "S",
                "other": ""
              },
            ]
          },
          {
            "id": 5,
            "type": "M",	//类型，S表示单选，M表示多选，T表示文本框
            "queContent": "踢出疯羊",
            "options": [
              //单选，多选
              {
                "id": 0,
                "content": "踢掉",
                "isSelect": 0,
                "percentage": "",
                "type": "M",
                "other": ""
              },
              {
                "id": 1,
                "content": "不踢",
                "isSelect": 1,
                "percentage": "",
                "type": "M",
                "other": ""
              },
            ]
          },
          {
            "id": 6,
            "type": "M",	//类型，S表示单选，M表示多选，T表示文本框
            "queContent": "踢出疯羊",
            "options": [
              //单选，多选
              {
                "id": 0,
                "content": "踢掉",
                "isSelect": 0,
                "percentage": "",
                "type": "M",
                "other": ""
              },
              {
                "id": 1,
                "content": "不踢",
                "isSelect": 1,
                "percentage": "",
                "type": "M",
                "other": ""
              },
            ]
          },

          {
            "id":7 ,
            "type": "M",	//类型，S表示单选，M表示多选，T表示文本框
            "queContent": "踢出疯羊",
            "options": [
              //单选，多选
              {
                "id": 0,
                "content": "踢掉",
                "isSelect": 0,
                "percentage": "",
                "type": "M",
                "other": ""
              },
              {
                "id": 1,
                "content": "不踢",
                "isSelect": 1,
                "percentage": "",
                "type": "M",
                "other": ""
              },
            ]
          },
          {
            "id": 8,
            "type": "M",	//类型，S表示单选，M表示多选，T表示文本框
            "queContent": "踢出疯羊",
            "options": [
              //单选，多选
              {
                "id": 0,
                "content": "踢掉",
                "isSelect": 0,
                "percentage": "",
                "type": "M",
                "other": ""
              },
              {
                "id": 1,
                "content": "不踢",
                "isSelect": 1,
                "percentage": "",
                "type": "M",
                "other": ""
              },
            ]
          },
          {
            //文本题
            queContent: "",
            id: this.Msg, //题号
            type: "T", // 类型 ----文本框
            options: [], //文本内容
          }
        ]
      }
    }
  },
  methods: {
    dateTimeTOString: function (dateTime) {
      let source = new Date(dateTime);
      let yy = source.getFullYear().toString();
      let mm = source.getMonth() + 1;
      mm = mm < 10 ? ('0' + mm.toString()) : mm.toString();
      let dd = source.getDate();
      dd = dd < 10 ? ('0' + dd.toString()) : dd.toString();
      let hh = source.getHours();
      hh = hh < 10 ? ('0' + hh.toString()) : hh.toString();
      let MM = source.getMinutes();
      MM = MM < 10 ? ('0' + MM.toString()) : MM.toString();
      let ss = source.getSeconds();
      ss = ss < 10 ? ('0' + ss.toString()) : ss.toString();
      let aa = yy + '-' + mm + '-' + dd + ' ' + hh + ':' + MM + ':' + ss;
      return aa;
    },
    async deleteVote(){
      let data = await mineDelete()
      console.log(data)
    },
    async earlyTermination(){
      let data = await mineEarlyTermination()
      console.log(data)
    }


  },
  computed: {
    getContent: function () {
      return {
        content: this.voteData.content == "" ? "无" : this.voteData.content,
        createdTime: this.voteData.createdTime,
        deadline: this.voteData.deadline,
        isWithdraw: this.voteData.isWithdraw,
        status: this.voteData.status
      }
    },
    getTitle: function () {
      return {
        title: this.voteData.title,
        authority: this.voteData.authority,
      }
    },
    getQuestion: function () {
      return {
        question: this.voteData.voteQues
      }
    }
  }
}
</script>

<style lang="css" scoped>
* {
  margin: 0;
  padding: 0;
}
.el-header, .el-footer {
  margin: 20px;
  color: #333;
  text-align: left;
  line-height: 30px;
  height: 30px !important;
  font-size: x-large;
  font-weight:bolder;
  letter-spacing: 10px;
  font-family:'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
}

.el-main {
  background-color: white;
}
.context {
  width: 1000px;

  margin: 0 auto;
  padding: 0;
  height: auto;
}
.button{
  width: 80%;
  height: 40px;
  text-align: right;
  line-height: 40px;
}
.button>button{
  display:inline-block;
  width: 100px;
  height: 25px;
}
.button>button:hover{
  display:inline-block;
  width: 105px;
  height: 30px;
  background-color: #C03639;
  color: white;
}
</style>
