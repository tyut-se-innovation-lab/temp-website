<template>
  <div class="formarea">
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
        <div class="button-box" v-for="index of scheduleNum" :key="index">
          <label class="close" @click="deleteData(index)">x</label>
          <el-button
            type="primary"
            plain
            circle
            size="samll"
            @click="switchScheduleData(index)"
            >{{ index }}</el-button
          >
        </div>

        <el-button
          type="primary"
          plain
          circle
          size="samll"
          @click="addSwitchButton"
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
        <el-button type="primary" @click="onSubmit">保存</el-button>
        <el-button @click="cancelSubmit">取消</el-button>
      </div>
    </el-form>
  </div>
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
        isGet: false, //是否为后端获取的数据
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
      "deleteScheduleData",
    ]),
    onSubmit() {
      //返回原始的课表比例
      this.restoreScale();

      let data = {
        index: this.scheduleIndex - 1,
        scheduleData: this.scheduleData,
        _this: this,
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
      //如果数据为空，存储为有课
      if (this.scheduleData.courseName === "") {
        this.scheduleData.courseName = "有课";
      }

      let data = {
        index: this.scheduleIndex - 1,
        scheduleData: this.scheduleData,
        _this: this,
      };
      this.storeInputData(data);

      //读取当前点击组的课表的数据
      this.readScheduleData(index);
      //如何删除表单

      //切换到点击后的第 n 组课表
      this.scheduleIndex = index;
    },
    addSwitchButton() {
      this.scheduleNum++;
      //切换到新加的选项中
      this.switchScheduleData(this.scheduleNum);
    },
    readScheduleData(index) {
      if (index <= this.inputData.length) {
        this.scheduleData = this.inputData[index - 1];
      } else {
        this.scheduleData = {
          courseName: "",
          startWeek: 0,
          endWeek: 0,
        };
      }
    },
    deleteData(index) {
      if (this.scheduleNum !== 1) {
        this.scheduleNum--;
        this.scheduleData = {
          courseName: "",
          startWeek: 0,
          endWeek: 0,
        };
      }
      if (this.scheduleIndex !== 1) {
        this.scheduleIndex--;
      }
      let data = {
        index: index - 1,
        scheduleData: this.inputData[index - 1],
      };
      //调用方法删除
      this.deleteScheduleData(data);
      //切换
      this.readScheduleData(this.scheduleIndex);
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
.button-box {
  margin-right: 8px;
  position: relative;
}
.close {
  width: 10px;
  height: 10px;
  font-size: 8px;
  position: absolute;
  top: 0;
  right: 0;
  color: rgb(219, 226, 232);
  background-color: rgba(98, 94, 94, 0.2);
  text-align: center;
  line-height: 10px;
  border-radius: 50%;
  display: none;
}
.button-box:hover .close {
  display: block;
}
.close:hover {
  color: black;
}
.fromarea {
  height: 80%;
  border: 1px black solid;
}
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