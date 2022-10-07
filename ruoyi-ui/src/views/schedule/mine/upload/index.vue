<template>
  <el-container>
    <el-main>
      <el-cow>
        <el-col :span="scale1">
          <write-schedule></write-schedule>
          <div class="buttons">
            <el-button type="primary" v-if="!modifybutton" @click="sendData"
              >提交</el-button
            >
            <el-button type="primary" v-if="modifybutton" @click="writeData"
              >修改</el-button
            >
          </div>
        </el-col>
        <el-col :span="scale2">
          <schedule-input></schedule-input>
        </el-col>
      </el-cow>
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
    ...mapMutations("scheduleupload", ["writableData", "getScheduleData"]),
    writeData() {
      //出现提交按钮
      this.modifybutton = !this.modifybutton;
      //修改数据为可读可写
      this.writableData();
    },
    //发送数据
    sendData() {
      //修改数据为只读
      this.writableData();
      //显示修改框
      this.modifybutton = !this.modifybutton;
      uploadSchedule(JSON.stringify(this.sendedData));
      //获取数据
      setTimeout(() => {
        this.getScheduleData();
        //更新数据
        setTimeout(() => {
          this.writableData();
          this.writableData();
        }, 500);
      }, 500);
    },
  },
  mounted() {
    //获取数据
    this.getScheduleData();
    //更新数据
    // setTimeout(() => {
    //   this.$bus.$emit("getdata");
    // }, 500);
    setTimeout(() => {
      this.writableData();
      this.writableData();
    }, 500);
  },
};
</script>

<style scoped>
.buttons {
  position: absolute;
  margin-top: 20px;
  right: 50px;
}
</style>
