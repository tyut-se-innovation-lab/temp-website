<template>
  <div>
    <div>
      <el-button type="">下 载</el-button>
      <el-dialog
        title="请选择下载时间"
        :visible.sync="downloadVisible"
        width="500px"
      >
        <!-- <el-form :model="form">
          <el-form-item label="姓名" :label-width="formLabelWidth">
            <el-input v-model="form.name" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="电话" :label-width="formLabelWidth">
            <el-input v-model="form.tel" autocomplete="off"></el-input>
          </el-form-item>
        </el-form> -->
        <el-select>
          <el-option></el-option>
        </el-select>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="dialogFormVisible = false"
            >下 载</el-button
          >
        </div>
      </el-dialog>
    </div>
    <el-table :data="loginRecord" id="table">
      <el-table-column
        prop="userName"
        label="用户名"
        align="center"
        width="120"
      ></el-table-column>
      <el-table-column
        prop="signTimes[0]"
        label="开始时间"
        align="center"
        width="260"
      ></el-table-column>
      <el-table-column
        prop="signTimes[1]"
        label="结束时间"
        align="center"
        width="260"
      ></el-table-column>
      <el-table-column
        prop="signTime"
        label="经历时间(h)"
        align="center"
        width="120"
      ></el-table-column>
    </el-table>
  </div>
</template>

<script>
import { Log } from "@/api/attendance/log/index.js";
export default {
  name: "log",
  data() {
    return {
      log: new Log(),
      loginRecord: [],
      tmpRecord: [],
      fileList: [],
      downloadVisible: false,
    };
  },
  methods: {
    /**
     * 获取记录
     */
    weekLog() {
      // this.$nextTick(() => {
      this.log.weekLog().then((res) => {
        this.loginRecord = this.filterData(res.data);
      });
      // });
    },

    /**
     * 过滤数据
     */
    filterData(data) {
      for (let i = 0; i < data.length; i++) {
        data[i].signTimes[0] = this.fixTime(data[i].signTimes[0]);
        data[i].signTimes[1] = this.fixTime(data[i].signTimes[1]);
      }
      return data.filter((data) => {
        return data.signTime != null;
      });
    },

    /**
     * 修正时间
     * @param {String} dateString
     */
    fixTime(dateString) {
      let date = new Date(dateString);
      return `${
        date.getMonth() + 1
      }-${date.getDate()} ${date.toLocaleTimeString()}`;
    },

    /**
     * 获取文件列表
     */
    getFileList() {
      this.log.getFileList().then((res) => {
        this.fileList = res.data;
      });
    },

    downloadFile() {},

    /**
     * 获取文件
     */
    getFile() {
      this.log.getFile().then((res) => {
        console.log(res);
      });
    },
  },
  created() {
    this.getFileList();
    this.weekLog();
  },
};
</script>

<style>
#table {
  width: 760px;
}
</style>