<template>
  <div class="part">
    <!-- 插槽 -->
    <slot
      v-if="!(partdata !== undefined && partdata[dataIndex].courseName)"
    ></slot>
    <div v-if="partdata !== undefined && partdata[dataIndex].courseName">
      <el-button class="isAppear1" @click="modifyData">修改</el-button>
      <div>
        <h5>{{ partdata[dataIndex].courseName }}</h5>
        <p>
          {{ partdata[dataIndex].startWeek }}周~{{
            partdata[dataIndex].endWeek
          }}周
        </p>
      </div>

      <ul class="dots">
        <li
          v-for="index of partdata.length"
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
  props: ["row", "column", "partdata"],
  data() {
    return {
      dataIndex: 0,
    };
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
      this.$bus.$emit("modify", this.partdata.length);
    },
  },
};
</script>

<style scoped>
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
.dots {
  display: flex;
  justify-content: center;
  margin-top: 5px;
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