<template>
  <div>
    <div>
      <el-button type="" @click="setVisible">下 载</el-button>
      <el-dialog
        title="请选择下载文件名称"
        :visible.sync="downloadVisible"
        width="500px"
      >
        <el-select v-model="fileName" placeholder="请选择文件">
          <el-option
            v-for="(item, index) of fileList"
            :key="index"
            :value="item"
            :label="item"
          ></el-option>
        </el-select>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="getFile">下 载</el-button>
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
import { Log } from "@/api/attendance/signlog/index.js";
export default {
  name: "log",
  data() {
    return {
      log: new Log(),
      loginRecord: [],
      tmpRecord: [],
      fileList: [],
      fileName: "",
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

    setVisible() {
      this.downloadVisible = true;
    },

    downloadFile() {},

    /**
     * 获取文件
     */
    getFile() {
      if (this.fileName !== "") {
        this.log.getFile(this.fileName).then((res) => {
          console.log(res);
        });
      }
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