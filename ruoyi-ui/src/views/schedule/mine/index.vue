<template>
  <el-container>
    <el-main>
      <el-row :gutter="0">
        <el-col :span="scale1">
          <write-schedule></write-schedule>
          <div class="buttons">
            <el-button v-if="!modifybutton" @click="sendData">提交</el-button>
            <el-button type="primary" v-if="modifybutton" @click="writeData"
              >修改</el-button
            >
             <temp_find/>
          </div>
        </el-col>
        <el-col :span="scale2">
          <schedule-input></schedule-input>
        </el-col>
      </el-row>
      
    </el-main>
   
  </el-container>
</template>

<script>
import { uploadSchedule } from "@/api/schedule/upload";
import { mapActions, mapMutations, mapState } from "vuex";
import ScheduleInput from "./schedule-input.vue";
import WriteSchedule from "./write-schedule.vue";
import temp_find from "./temp_find.vue";
export default {
  name: "upload",
  components: {
    WriteSchedule,
    ScheduleInput,
    temp_find
  },
  data() {
    return {
      modifybutton: true,
    };
  },
  computed: {
    //引入Vuex数据为计算数据
    ...mapState("scheduleupload", [
      "scale1",
      "scale2",
      "sendedData",
      "control",
    ]),
  },
  methods: {
    ...mapActions("scheduleupload", ["getData"]),
    ...mapMutations("scheduleupload", [
      "writableData",
      "getScheduleData",
      "addControl",
      "clearSendedData",
      "storeGetedData",
    ]),
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
      this.storeGetedData();
      if (this.sendedData.length != 0) {
        uploadSchedule(JSON.stringify(this.sendedData));
        //清除发送过的数据
        this.clearSendedData();
      } else {
        this.$modal.msgError("数据为空,无法提交");
      }

      //获取数据
      setTimeout(() => {
        this.getData(this);
        setTimeout(() => {
          this.writableData();
          this.writableData();
        }, 200);
      }, 500);
    },
  },
  created() {
    //获取数据
    if (!this.control) {
      this.getData(this);
      //control加一，只进行一次
      this.addControl();
      setTimeout(() => {
        this.writableData();
        this.writableData();
      }, 200);
    }
  },
};
</script>

<style scoped>
.buttons {
  margin-top: 20px;
  right: 50px;
}
</style>
