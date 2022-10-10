<template>
  <el-table
    :data="scheduleData"
    style="width: 100%"
    border
    :span-method="combineRowColumn"
    :cell-class-name="tableRoColumn"
    @cell-click="getTime"
  >
    <el-table-column prop="num" label="课表" min-width="80" align="center">
    </el-table-column>
    <el-table-column
      v-for="(item, index) of week"
      :key="index"
      :label="item"
      min-width="80"
      align="center"
      class-name="appear"
    >
      <template slot-scope="scope">
        <!-- 这个scheduleData在数据接受并且存到state中无法再传到子组件中 不能子组件直接去stroe取吗？能，但是我还是想解决这个问题 -->
        <!-- 必须读取Vuex中的数据，或者调用其中的方法，或者拖一下页面才行，好像是触发了某种更新机制 -->
        <SchedulePart :row="scope.row.index" :column="scope.column.index">
          <template>
            <el-button
              :class="{ isAppear: writable, isAppear2: !writable }"
              @click="addSchedule"
              >添加</el-button
            >
          </template>
        </SchedulePart>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
import { mapMutations, mapState } from "vuex";
import SchedulePart from "./schedule-part.vue";
export default {
  name: "WriteSchedule",
  data() {
    return {
      week: [
        "星期一",
        "星期二",
        "星期三",
        "星期四",
        "星期五",
        "星期六",
        "星期日",
      ],
      data: [],
    };
  },
  components: {
    SchedulePart,
  },
  //按理说，这里会出问题吗，还是我的理解错误
  computed: {
    ...mapState("scheduleupload", ["dayTime", "scheduleData", "writable"]),
  },
  methods: {
    //简化Vuex中的Mutations的方法
    ...mapMutations("scheduleupload", [
      "changeScale",
      "sendTime",
      "clearInputData",
    ]),
    //className回调方法
    tableRoColumn({ row, column, rowIndex, columnIndex }) {
      row.index = rowIndex;
      column.index = columnIndex;
    },

    getTime(row, column, cell, event) {
      //显示每个表格点击后的行和列
      console.log(row.index);
      console.log(column.index);

      let time = {
        //一天中的第几节
        period: row.index + 1,
        //一周中的星期几
        week: column.index,
        //表格的行
        row: row.index,
        //表格的列
        column: column.index,
        //星期几
        whatDay: this.week[column.index - 1],
      };
      //传递当前点击课表位置的列数，由此推出时间
      //传递是一个星期的第几天
      this.sendTime(time);
    },
    //添加新的课表
    addSchedule() {
      //清空 Vuex 中的数据
      this.clearInputData();
      //显示 input 表单
      this.changeScale();
      //清除输入的数据
      this.$bus.$emit("clear");
    },

    //合并行和列的回调方法
    combineRowColumn({ row, column, rowIndex, columnIndex }) {},
  },
  mounted() {
    console.log(this.scheduleData); //这个可以输出Vuex里面的，用了setTImeout输出的是存储过的，但是页面不更新
    // 获取scheduleData是异步的 同步获取不到是正常的 你去子组件直接从store取数据就行了，OK，行我试试
  },
};
</script>

<style scoped>
.appear button {
  margin: 0 auto;
}
.appear:hover .isAppear {
  display: block;
  transition: all 0.5s;
}
.isAppear {
  display: none;
}
.isAppear2 {
  display: none;
}
</style>