<!-- 选项 -->
<template>
  <div>
    <el-container>
      <el-header>{{headers.vote_name}}</el-header>
      <el-main>
        <div class="context">
          <!-- 标题 -->
            <titleVue @childTitle="getTitle" />
            <!-- 截止时间 -->
            <deadlineVue @childDeadline="getDeadline" />
            <!-- 投票简介 -->
            <contentVue @childContent="getContent"/>
<!--          题目-->
            <optionType @getOption="getOption"/>
           <!-- 发布 -->
            <submit :sendVote="sendVote"/>
        </div>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import titleVue from "./option_modules/title.vue";
import submit from "./option_modules/submit.vue";
import deadlineVue from "./option_modules/deadline.vue"
import contentVue from "./option_modules/content.vue";
import optionType from "./option_type.vue";
import {getInitiate} from "../../../api/vote/initiate/initiate";
export default {
    name:'voteOption',
    components:{
      titleVue,
      submit,
      deadlineVue,
      contentVue,

      optionType
    },
    data(){
      return{
        // 标题
        headers:{
          vote_name: '设置投票'
        },
        // 发送的数据
        data:{
          title:'',
          deadline:'',
          context:'',
          voteQues:[],
        },
      }
    },
    methods:{
      // 获取标题
      getTitle(val){
        this.data.title = val;
      },
      // 获取截止时间
      getDeadline(val){
        this.data.deadline = val;
      },
      //获取投票简介
      getContent(val){
        this.data.context =val
      },

      getOption(val){
        this.data.voteQues =[]
        this.data.voteQues =[...val.tempSingle,...val.tempMultiple,...val.tempFill,]
      },
      // 发布投票
      async sendVote() {
         console.log(this.data)
         let info = await getInitiate(this.data);
         if(info.msg === "OK"){
           this.$modal.alertSuccess("提交成功啦");
           this.$router.go(0)
         }
       },

    },
  }
</script>

<style scoped>
*{
  margin: 0;
  padding: 0;
}
.el-header, .el-footer {
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
  line-height: 12vh;
  height: 12vh !important;
  font-size: x-large;
  font-weight:bolder;
  letter-spacing: 10px;
  font-family:'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
}


.el-main {
  background-color: white;
}
.context{
  width: 1000px;
  background: #F2F6FC;
  margin: 0 auto;
  padding: 0;
  height: auto;
}

</style>
