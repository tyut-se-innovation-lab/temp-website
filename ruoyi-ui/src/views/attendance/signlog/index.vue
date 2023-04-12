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
    <el-table :data="signLogShowData" id="table">
      <el-table-column
        prop="userName"
        label="用户名"
        align="center"
        min-width="130"
      ></el-table-column>
      <el-table-column
        prop="attStartTime"
        label="开始时间"
        align="center"
        min-width="270"
      ></el-table-column>
      <el-table-column
        prop="attEndTime"
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
    <Page :pageData="pageData" @pageClick="pageClick" ref="child"></Page>
  </div>
</template>

<script>
import { Log } from "@/api/attendance/signlog/index.js";
import Page from "@/views/components/pages/index.vue";
export default {
  name: "log",
  data() {
    return {
      log: new Log(),
      signLog: {},
      signLogShowData: [], //要展示的数据
      fileList: [],
      fileName: "",
      filterDate: null,
      currentPage: 1,
      pageData: {
        totalPages: 0,
        currentPage: 0,
        pagerCount: 0,
      },
      downloadVisible: false,
    };
  },
  compute: {
    pageData() {
      return a;
    },
  },
  components: {
    Page,
  },
  methods: {
    /**
     * 初始化
     */
    init() {
      this.getFileList();
      this.weekLog();
    },

    /**
     * 获取记录
     * @param {Number} currentPage 自定义参数:当前页数
     * @param {Number} pageCount 自定义参数:每页最大展示条数
     */
    weekLog(currentPage, pageCount) {
      let tmpObj = {
        attStartTime: this.filterDate ? this.filterDate[0].getTime() : null,
        attEndTime: this.filterDate ? this.filterDate[1].getTime() : null,
        currentPage: currentPage || this.currentPage,
        pageCount: pageCount || 15,
      };

      this.log.weekLog(tmpObj).then((res) => {
        this.signLog = res.data;
        this.signLogShowData = res.data.list;

        this.initPageData(res);

        // console.log(this.$refs.child.init());
      });
    },

    /**
     * 初始化pageData
     */
    initPageData(res) {
      // let a = {};
      // a.totalPages = res.data.pageNum;
      // a.currentPage = this.currentPage;
      // a.pagerCount = this.pagerCount ? this.pagerCount : 7;
      // this.pageData = a;
      // this.$set(this.pageData, "totalPages", res.data.pageNum);
      // this.$set(this.pageData, "currentPage", this.currentPage);
      // this.$set(this.pageData, "pagerCount", 7);
      this.pageData.totalPages = 5;
      this.pageData.currentPage = 2;
      this.pageData.pagerCount = this.pagerCount ? this.pagerCount : 7;
      console.log(this.pageData.totalPages);
    },

    /**
     * 过滤数据
     *
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
          this.downloadFile(res);
        });
      }
    },

    /**
     * 按钮按下
     */
    pageClick(index) {
      this.currentPage = index;
      console.log(index);
    },
  },
  watch: {
    filterDate(newVal, oldVal) {
      //更新数据
      if (this.currentPage !== 1) {
        this.currentPage = 1;
      }
      this.weekLog();
    },
    currentPage(newVal, oldVal) {
      this.weekLog();
    },
  },
  created() {
    this.init();
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