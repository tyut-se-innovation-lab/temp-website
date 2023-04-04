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
  methods: {
    /**
     * 获取记录
     */
    weekLog() {
      this.log.weekLog().then((res) => {
        this.loginRecord = res.data;
        this.tmpRecord = this.filterData(res.data);
      });
    },

    /**
     * 过滤数据
     */
    filterData(data) {
      let tmpdata = data;
      for (let i = 0; i < tmpdata.length; i++) {
        tmpdata[i].signInTime = this.fixTime(tmpdata[i].signTimes[0]);

        tmpdata[i].signOutTime =
          tmpdata[i].signTimes[1] === null
            ? null
            : this.fixTime(tmpdata[i].signTimes[1]);
      }
      return tmpdata;
    },

    /**
     * 筛选指定时间内记录
     * @param {Array} date 时间区间数组
     */
    filterByDate(date) {
      let date1 = new Date(date[0]);
      let date2 = new Date(date[1]);
      return this.tmpRecord.filter((data) => {
        let compare = new Date(data.signTimes[0]);
        return compare >= date1 && compare <= date2;
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

    /**
     * 设置是否可见
     */
    setVisible() {
      this.downloadVisible = true;
    },

    /**
     * 下载
     * @param {Blob} bolbFlow Blob流
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
  watch: {
    filterDate(newVal, oldVal) {
      this.tmpRecord = this.filterByDate(newVal);
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