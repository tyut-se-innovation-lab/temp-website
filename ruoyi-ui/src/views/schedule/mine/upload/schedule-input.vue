<template>
  <el-form
    ref="form"
    :model="scheduleData"
    label-width="80px"
    style="margin-top: 20px"
  >
    <div class="beCenter">
      <h2>添加课程</h2>
    </div>
    <div class="scheduleButton">
      <el-button
        type="primary"
        plain
        circle
        size="samll"
        v-for="index of scheduleNum"
        :key="index"
        @click="switchScheduleData(index)"
        >{{ index }}</el-button
      >
      <el-button
        type="primary"
        plain
        circle
        size="samll"
        @click="scheduleNum++"
        class="addScheduleNum"
        >+</el-button
      >
    </div>
    <el-form-item label="课程名字">
      <el-input
        v-model="scheduleData.courseName"
        placeholder="请输入课程名字"
      ></el-input>
    </el-form-item>
    <el-form-item label="课程时间">
      <el-select
        style="width: 40%"
        size="mini"
        v-model="scheduleData.startWeek"
        placeholder="起始周"
      >
        <el-option v-for="index of 22" :key="index" :value="index">{{
          index
        }}</el-option>
      </el-select>
      周到
      <el-select
        style="width: 40%"
        size="mini"
        v-model="scheduleData.endWeek"
        placeholder="结束周"
      >
        <el-option v-for="index of 22" :key="index" :value="index">{{
          index
        }}</el-option>
      </el-select>
      周
    </el-form-item>
    <div class="dayTime">
      <p>当前添加的课表时间：{{ whatDay }}：{{ dayTime }}</p>
    </div>
    <div class="button">
      <el-button type="primary" @click="onSubmit">提交</el-button>
      <el-button @click="cancelSubmit">取消</el-button>
    </div>
  </el-form>
</template>

<script>
import { mapMutations, mapState } from "vuex";
export default {
  name: "ScheduleInput",
  data() {
    return {
      scheduleNum: 1,
      scheduleIndex: 1,
      scheduleData: {
        courseName: "",
        startWeek: 0,
        endWeek: 0,
      },
    };
  },
  computed: {
    ...mapState("scheduleupload", ["dayTime", "whatDay", "inputData"]),
  },
  methods: {
    ...mapMutations("scheduleupload", [
      "restoreScale",
      "storeInputData",
      "clearInputData",
      "storeScheduleData",
    ]),
    onSubmit() {
      //返回原始的课表比例
      this.restoreScale();
      //存储之前的临时数据
      let data = {
        index: this.scheduleIndex - 1,
        scheduleData: this.scheduleData,
      };
      this.storeInputData(data);
      //存储全部数据到 Vuex
      this.storeScheduleData();
      //清空数据
      this.clearInputData();
    },
    cancelSubmit() {
      //返回原始的课表比例
      this.restoreScale();
      //清空数据
      this.clearInputData();
    },
    //存储同一天同一时间的多组课表
    switchScheduleData(index) {
      //存储上一组数据
      let data = {
        index: this.scheduleIndex - 1,
        scheduleData: this.scheduleData,
      };
      this.storeInputData(data);

      console.log(this.inputData);
      //读取当前点击组的课表的数据
      this.readScheduleData(index);
      //如何删除表单

      //切换到点击后的第 n 组课表
      this.scheduleIndex = index;
    },
    readScheduleData(index) {
      if (index <= this.inputData.length) {
        console.log(666666666);
        this.scheduleData = this.inputData[index - 1];
      } else {
        this.scheduleData = {
          courseName: "",
          startWeek: 0,
          endWeek: 0,
        };
      }
    },
  },
  mounted() {
    //修改时初始化数据
    this.$bus.$on("modify", (length) => {
      //修改存储位置
      this.scheduleIndex = 1;
      //默认展示第一组数据
      this.scheduleData = this.inputData[0];
      console.log(length);
      //修改数据的组数
      this.scheduleNum = length;
    });
    //绑定事件，清空当前表单内的数据
    this.$bus.$on("clear", () => {
      this.scheduleData = {
        courseName: "",
        startWeek: 0,
        endWeek: 0,
      };
      this.scheduleNum = 1;
    });
  },
};
</script>

<style scoped>
.scheduleButton {
  display: flex;
  justify-content: center;
  margin-bottom: 15px;
}
.scheduleButton button {
  text-align: center;
  font-size: 15px;
  padding: 3px;
  width: 30px;
  height: 30px;
  line-height: 20px;
  border-radius: 50%;
}
.dayTime {
  text-align: center;
}
.beCenter {
  text-align: center;
}
.beCenter h2 {
  margin-bottom: 30px;
}
.button {
  width: 60%;
  margin: 0 auto;
  display: flex;
  justify-content: space-around;
  margin-top: 25px;
}
</style>