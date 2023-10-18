<script>
console.log(123);
console.log(132);
export default {
  name: "rule",
  data() {
    return {
      pickerOptions: {
        shortcuts: [
          {
            text: "今天",
            onClick(picker) {
              picker.$emit("pick", new Date());
            },
          },
          {
            text: "昨天",
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() - 3600 * 1000 * 24);
              picker.$emit("pick", date);
            },
          },
          {
            text: "一周前",
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit("pick", date);
            },
          },
        ],
      },
      value1: "",
      value2: "",
      value3: "",
      gridData: [
        {
          date: "2016-05-02",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄",
        },
        {
          date: "2016-05-04",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄",
        },
        {
          date: "2016-05-01",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄",
        },
        {
          date: "2016-05-03",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄",
        },
      ],
      dialogTableVisibleDate: false,
      dialogTableVisibleMonth: false,
    };
  },
  methods: {
    handleClose(done) {
      this.$confirm("确认关闭？")
        .then((_) => {
          done();
        })
        .catch((_) => {});
    },
  },
};
</script>

<template>
  <div class="content">
    <el-dialog title="收货地址" :visible.sync="dialogTableVisibleDate">
      <el-table :data="gridData">
        <el-table-column
          property="date"
          label="日期"
          width="150"
        ></el-table-column>
        <el-table-column
          property="name"
          label="姓名"
          width="200"
        ></el-table-column>
        <el-table-column property="address" label="地址"></el-table-column>
      </el-table>
    </el-dialog>
    <el-dialog title="收货地址" :visible.sync="dialogTableVisibleMonth">
      <el-table :data="gridData">
        <el-table-column
          property="date"
          label="日期132"
          width="150"
        ></el-table-column>
        <el-table-column
          property="name"
          label="姓名"
          width="200"
        ></el-table-column>
        <el-table-column property="address" label="地址"></el-table-column>
      </el-table>
    </el-dialog>
    <div class="left">
      <div class="top">
        <div @click="dialogTableVisibleDate = true">
          <el-card shadow="hover" :body-style="{ padding: '0px' }">
            <div class="grid-content grid-con-1">
              <el-icon class="grid-con-icon el-icon-user-solid"> </el-icon>
              <div class="grid-cont-right">
                <div class="grid-num">1234</div>
                <div>今日分数</div>
              </div>
            </div>
          </el-card>
        </div>
        <div @click="dialogTableVisibleMonth = true">
          <el-card shadow="hover" :body-style="{ padding: '0px' }">
            <div class="grid-content grid-con-2">
              <el-icon class="grid-con-icon el-icon-eleme"> </el-icon>
              <div class="grid-cont-right">
                <div class="grid-num">1234</div>
                <div>本月分数</div>
              </div>
            </div>
          </el-card>
        </div>
      </div>

      <div class="input">
        <div class="block">
          <el-date-picker
            v-model="value1"
            type="datetime"
            placeholder="选择日期时间"
            default-value
          >
          </el-date-picker>
        </div>
        <el-button type="primary" icon="el-icon-search">搜索</el-button>
      </div>
      <el-table :data="tableData" style="width: 100%">
        <el-table-column prop="date" label="时间" width="280">
        </el-table-column>
        <el-table-column prop="name" label="分数" width="280">
        </el-table-column>
        <el-table-column prop="address" label="原因"> </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<style scoped>
.content {
  width: 100%;
  height: 100%;
  display: flex;

  .left {
    width: 100%;
    height: 100%;

    .top {
      display: flex;
      width: 100%;
      justify-content: space-around;
      margin-top: 50px;
    }

    .input {
      display: flex;
      margin: 20px auto;
      justify-content: center;
    }
  }
}

.grid-content {
  display: flex;
  align-items: center;
  height: 100px;
  cursor: pointer;
}

.grid-cont-right {
  flex: 1;
  text-align: center;
  font-size: 14px;
  color: #999;
}

.grid-num {
  font-size: 30px;
  font-weight: bold;
}

.grid-con-icon {
  font-size: 50px;
  width: 100px;
  height: 100px;
  text-align: center;
  line-height: 100px;
  color: #fff;
}

.grid-con-1 .grid-con-icon {
  background: rgb(45, 140, 240);
}

.grid-con-1 .grid-num {
  color: rgb(45, 140, 240);
}

.grid-con-2 .grid-con-icon {
  background: rgb(100, 213, 114);
}

.grid-con-2 .grid-num {
  color: rgb(100, 213, 114);
}
</style>
