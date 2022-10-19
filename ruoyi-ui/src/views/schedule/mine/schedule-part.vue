<template>
  <div :class="{ part: writable }">
    <!-- 插槽 -->
    <slot v-if="!scheduleData[row].courseData[column - 1]"></slot>
    <div
      v-if="
        scheduleData[row].courseData[column - 1] &&
        scheduleData[row].courseData[column - 1].length !== 0 &&
        scheduleData[row].courseData[column - 1][dataIndex].courseName
      "
    >
      <el-button class="isAppear1" @click="modifyData">修改</el-button>
      <div class="text">
        <h4>
          {{ scheduleData[row].courseData[column - 1][dataIndex].courseName }}
        </h4>
        <p>
          {{
            scheduleData[row].courseData[column - 1][dataIndex].startWeek
          }}周~{{
            scheduleData[row].courseData[column - 1][dataIndex].endWeek
          }}周
        </p>
      </div>

      <ul class="dots">
        <li
          v-for="index of scheduleData[row].courseData[column - 1].length"
          :key="index"
          class="dot"
          @click="switchScheduleGroup(index)"
        ></li>
      </ul>
    </div>
  </div>
</template>

<script>
import { mapMutations, mapState } from "vuex";
export default {
  name: "schedulepart",
  props: ["row", "column"],
  data() {
    return {
      dataIndex: 0,
    };
  },
  computed: {
    ...mapState("scheduleupload", ["writable", "scheduleData"]),
  },
  methods: {
    ...mapMutations("scheduleupload", ["changeScale", "modifyScheduleData"]),
    //切换多组数据
    switchScheduleGroup(index) {
      this.dataIndex = index - 1;
    },
    //修改数据
    modifyData() {
      //表格坐标
      let index = {
        row: this.row,
        column: this.column,
      };
      //显示输入框
      this.changeScale();
      //将课程数据填充到表单数据中
      this.modifyScheduleData(index);
      //发送数据的组数，进行表单初始化
      this.$bus.$emit(
        "modify",
        this.scheduleData[this.row].courseData[this.column - 1].length
      );
    },
  },
};
</script>

<style scoped>
* {
  list-style: none;
}

.isAppear1 {
  width: 70px;
  left: 0;
  right: 0;
  position: absolute;
  margin: 0 auto;
  display: none;
}
.part:hover .isAppear1 {
  display: block;
  transition: all 0.5s;
}
.text h4,
.text p {
  margin: 10px 0;
}
.dots {
  display: flex;
  justify-content: center;
  margin-top: 5px;
  padding: 0;
}
.dot {
  width: 8px;
  height: 8px;
  border-radius: 50%;
  background: rgb(255, 222, 222);
  transition: all 0.5s;
  margin: 0 2px;
}
.dot:hover {
  background: gray;
}
</style>