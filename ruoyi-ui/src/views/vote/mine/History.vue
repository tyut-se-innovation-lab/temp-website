<template>
  <div>
    <el-container>
<!--      标题-->
      <el-header>{{ headers.my_vote_name }}</el-header>
      <hr>
      <el-main>
        <div class="context">
          <button @click="mineJoining">我参与的</button> <button @click="mineCreating">我发起的</button>
<!--      内容-->
          <div v-for="(item) in childBriefly" :key="item.id" @click="getPage(item.id,item.state)">
            <historyData :childBriefly="item" />
          </div>
        </div>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import historyData from "./historyData.vue";
import {mineCreate, mineJoin} from "@/api/vote/mine";
export default {
  components: {
    historyData,
  },
  data() {
    return {
      headers: {
        my_vote_name: "我的提议",
      },
      childBriefly: [
        {
          id:20,	//投票id
          title:"踢出疯羊",//title,
          state:0	,//0,1,2状态
          start:this.getTime(new Date("2022-11-30 00:00:00")),	//开始时间
          end:this.getTime(new Date("2022-12-30 00:00:00")),	//结束时间
          content:"暂时没有吧",	//简介
          isjoin:false	//是否已经参与
        },
        {
          id:31,	//投票id
          title: "踢出孙红飞",
          content: "没有原因就是看见他不爽",
          state:2	,//0,1,2状态
          start:this.getTime(new Date("2022-10-20 00:00:00")),
          end: this.getTime(new Date("2022-11-30 00:00:00")),
          isjoin:false
        },
        {
          id:30,
          title: "踢出孙红飞",
          content: "没有原因就是看见他不爽",
          state:1	,//0,1,2状态
          start:this.getTime(new Date("2022-09-30 00:00:00")),
          end: this.getTime(new Date("2022-11-10 00:00:00")),
          isjoin:false
        },
        {
          id:32,
          title: "踢出孙红飞",
          content: "没有原因就是看见他不爽",
          state:1	,//0,1,2状态
          start:this.getTime(new Date("2022-10-17 00:00:00")),
          end: this.getTime(new Date("2022-11-30 00:00:00")),
          isjoin:true
        },
      ],
    };
  },
  methods: {
    async mineJoining(){
     let content = await mineJoin()
      console.log(content)
    },
    async mineCreating(){
      let content = await mineCreate()
      console.log(content)
    },
    // 设置时间 后端返回时间之后实际上就没用了
    getTime(timestamp) {
      let time = new Date(timestamp);
      let year = time.getFullYear();
      let month = time.getMonth() + 1;
      let date = time.getDate();
      let hours = time.getHours();
      let minute = time.getMinutes();
      let second = time.getSeconds();
      if (month < 10) {
        month = "0" + month;
      }
      if (date < 10) {
        date = "0" + date;
      }
      if (hours < 10) {
        hours = "0" + hours;
      }
      if (minute < 10) {
        minute = "0" + minute;
      }
      if (second < 10) {
        second = "0" + second;
      }
      return year + "-" + month + "-" + date + " " + hours + ":" + minute + ":" + second;
    },
    // 点击页面跳转
    getPage(index,statusVal) {
      // console.log(index,statusVal);
      if(statusVal === 2){
        return
      }else {
        this.$router.push({ name: "historyAllData", params: { index : index } });
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
</style>
