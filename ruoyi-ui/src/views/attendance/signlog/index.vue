<template>
  <div>
    <div>
      <div id="logheader">
        <div>
          选择时间：<el-date-picker
            type="daterange"
            v-model="filterDate"
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
          ></el-date-picker>
        </div>
        <el-button type="" @click="setVisible">下 载</el-button>
      </div>

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
    <el-table :data="tmpRecord" id="table">
      <el-table-column
        prop="userName"
        label="用户名"
        align="center"
        min-width="130"
      ></el-table-column>
      <el-table-column
        prop="signInTime"
        label="开始时间"
        align="center"
        min-width="270"
      ></el-table-column>
      <el-table-column
        prop="signOutTime"
        label="结束时间"
        align="center"
        min-width="270"
      ></el-table-column>
      <el-table-column
        prop="signTime"
        label="经历时间(h)"
        align="center"
        min-width="130"
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
      filterDate: "",
      downloadVisible: false,
    };
  },
  watch: {
    filterDate(newVal, oldVal) {
      this.filterByDate(newVal, this.loginRecord);
    },
  },
  methods: {
    /**
     * 获取记录
     */
    weekLog() {
      this.log.weekLog().then((res) => {
        this.loginRecord = this.filterData(res.data);
        this.tmpRecord = this.filterData(res.data);
      });
    },

    /**
     * 过滤数据
     * @param {Array} data 要过滤的数据
     */
    filterData(data) {
      let tmpdata = data.filter((data) => {
        return data.signTime != null;
      });
      for (let i = 0; i < tmpdata.length; i++) {
        tmpdata[i].signInTime = this.fixTime(tmpdata[i].signTimes[0]);
        tmpdata[i].signOutTime = this.fixTime(tmpdata[i].signTimes[1]);
      }
      return tmpdata;
    },

    /**
     * 通过时间范围过滤
     * @param {Array} dates 时间数组
     * @param {Array} compareArr 要进行操作数组
     */
    filterByDate(dates, compareArr) {
      let tmp = compareArr.filter((data) => {
        console.log(data.signTimes[0]);
        return (
          new Date(data.signTimes[0]) > new Date(dates[0]) &&
          new Date(data.signTimes[0]) < new Date(dates[1])
        );
      });
      this.tmpRecord = this.filterData(tmp);
    },

    /**
     * 修正时间到指定字符串格式
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

    /**
     * 设置提示框是否可见
     */
    setVisible() {
      this.downloadVisible = true;
    },

    /**
     * 下载文件
     */
    downloadFile(blobFlow) {
      const blob = new Blob(new Array(blobFlow), {
        //type of excel
        type: "application/vnd.ms-excel",
      });
      if (window.navigator.msSaveOrOpenBlob) {
        navigator.msSaveBlob(blob, this.fileName);
      } else {
        let link = document.createElement("a");
        link.href = window.URL.createObjectURL(blob);
        link.download = this.fileName;
        link.click();
        window.URL.revokeObjectURL(link.href);
      }
    },

    /**
     * 获取文件
     */
    getFile() {
      if (this.fileName !== "") {
        this.log.getFile(this.fileName).then((res) => {
          console.log(res);
          this.downloadFile(res);
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

<style scoped>
#logheader {
  display: flex;
  justify-content: space-between;
  margin: 20px;
}
#table {
  margin: 0 auto;
}
</style>