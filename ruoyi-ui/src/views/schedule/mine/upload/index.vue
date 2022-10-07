<template>
  <el-container>
    <el-main>
      <el-cow>
        <el-col :span="scale1">
          <write-schedule></write-schedule>
        </el-col>
        <el-col :span="scale2">
          <schedule-input></schedule-input>
        </el-col>
      </el-cow>
      <div>
        <el-button v-if="!modifybutton" @click="sendData">提交</el-button>
        <el-button v-if="modifybutton" @click="writeData">修改</el-button>
      </div>
    </el-main>
  </el-container>
</template>

<script>
import { uploadSchedule } from "@/api/schedule/upload";
import { mapMutations, mapState } from "vuex";
import ScheduleInput from "./schedule-input.vue";
import WriteSchedule from "./write-schedule.vue";
export default {
  name: "upload",
  components: {
    WriteSchedule,
    ScheduleInput,
  },
  data() {
    return {
      modifybutton: true,
    };
  },
  computed: {
    //引入Vuex数据为计算数据
    ...mapState("scheduleupload", ["scale1", "scale2", "sendedData"]),
  },
  methods: {
    ...mapMutations("scheduleupload", ["writableData"]),
    writeData() {
      //出现提交按钮
      this.modifybutton = !this.modifybutton;
      //修改数据为可读可写
      this.writableData();
    },
    //发送数据
    sendData() {
      //显示修改框
      this.modifybutton = !this.modifybutton;
      uploadSchedule(this.sendedData);
    },
  },
};
</script>

<style scoped>
</style>
