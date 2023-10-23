<script>
import {
  userController,
  monthUserController,
  dayUserControll,
  monthUserControll,
  userdayLog,
} from "@/api/rule/announcement";
export default {
  name: "rule",
  data() {
    return {
      //用户所有日志
      tableDataAll: null,
      //查询参数
      queryParams: {
        qeury: "",
        //当前页数
        pageNum: 1,
        //显示一页多少条数据
        pageSize: 10,
      },
      //总条数
      total: 0,
      //事件记录
      tableData: null,
      gridData: null,
      dayscore: "",
      monthscore: "",
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
      dayvalue: "",
      dialogTableVisibleDate: false,
      dialogTableVisibleMonth: false,
    };
  },
  methods: {
    async userInfo() {
      const month = await monthUserController();
      const day = await userController();
      const monthEvent = await monthUserControll();
      const dayEvent = await dayUserControll();

      this.gridData = dayEvent.data;
      this.tableData = monthEvent.data;
      this.tableDataAll = monthEvent.data;
      this.dayscore = day.data;
      this.monthscore = month.data;
    },
    search() {
      userdayLog(
        this.queryParams.pageNum,
        this.queryParams.pageSize,
        this.dayvalue
      ).then((response) => {
        this.tableDataAll = response.data.list;
        this.total = response.data.total;
      });
    },
    //监听 pagesize 改变的事件
    handleSizeChange(newsize) {
      //这里conso 选中第几页 最新的值
      console.log(newsize);
      //最新的条数（newsize）赋值给 动态的 pagesie
      this.queryParams.pageSize = newsize;
      //获取到最新一页显示的数据  重新发送axios请求 这里是封装好的请求方法
      this.search();
    },

    // 监听 页码值 改变的事件
    handleCurrentChange(newPage) {
      //把最新的页码（newPage）赋值给 动态的 pagenum
      this.queryParams.pageNum = newPage;
      //获取到最新显示的页码值  重新发送axios请求 这里是封装好的请求方法
      this.search();
    },
  },
  created() {
    this.userInfo();
  },
};
</script>

<template>
  <div class="content">
    <el-dialog title="今日记录" :visible.sync="dialogTableVisibleDate">
      <el-table :data="gridData">
        <el-table-column
          property="createTime"
          label="日期"
          width="150"
        ></el-table-column>
        <el-table-column
          property="reasonContent"
          label="原因"
          width="200"
        ></el-table-column>
        <el-table-column property="scoreChange" label="分数"></el-table-column>
      </el-table>
    </el-dialog>
    <el-dialog title="本月记录" :visible.sync="dialogTableVisibleMonth">
      <el-table :data="tableData">
        <el-table-column
          property="createTime"
          label="日期"
          width="150"
        ></el-table-column>
        <el-table-column
          property="reasonContent"
          label="原因"
          width="200"
        ></el-table-column>
        <el-table-column property="scoreChange" label="分数"></el-table-column>
      </el-table>
    </el-dialog>
    <div class="left">
      <div class="top">
        <div @click="dialogTableVisibleDate = true">
          <el-card shadow="hover" :body-style="{ padding: '0px' }">
            <div class="grid-content grid-con-1">
              <el-icon class="grid-con-icon el-icon-user-solid"> </el-icon>
              <div class="grid-cont-right">
                <div class="grid-num">{{ dayscore }}</div>
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
                <div class="grid-num">{{ monthscore }}</div>
                <div>本月分数</div>
              </div>
            </div>
          </el-card>
        </div>
      </div>

      <div class="input">
        <div class="block">
          <el-date-picker
            v-model="dayvalue"
            type="datetime"
            placeholder="选择日期时间"
            default-value
            value-format="yyyy-MM-dd HH:mm:ss"
          >
          </el-date-picker>
        </div>
        <el-button
          type="primary"
          icon="el-icon-search"
          @click="search"
          :disabled="!dayvalue"
          >搜索</el-button
        >
      </div>
      <el-table :data="tableDataAll" style="width: 100%">
        <el-table-column prop="createTime" label="时间" width="280">
        </el-table-column>
        <el-table-column prop="scoreChange" label="分数" width="280">
        </el-table-column>
        <el-table-column prop="reasonContent" label="原因"> </el-table-column>
      </el-table>
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="queryParams.pageNum"
        :page-size="queryParams.pageSize"
        :page-sizes="[10, 20, 30, 40, 50, 60, 70, 80, 90, 100]"
        layout="total, sizes, prev, pager, next ,jumper"
        :total="total"
      >
      </el-pagination>
    </div>
  </div>
</template>

<style scoped lang="scss">
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
