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
    </el-main>
  </el-container>
</template>

<script>
import { mapState } from "vuex";
import ScheduleInput from "./schedule-input.vue";
import WriteSchedule from "./write-schedule.vue";
export default {
  name: "upload",
  components: {
    WriteSchedule,
    ScheduleInput,
  },
  data() {
    return {};
  },
  computed: {
    //引入Vuex数据为计算数据
    ...mapState("scheduleupload", ["scale1", "scale2"]),
  },
};
</script>

<style scoped>
</style>
