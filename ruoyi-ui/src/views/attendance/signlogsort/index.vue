<template>
  <div>
    <div>
      <div id="logheader">
        <div>
          时间：
          <el-date-picker
            type="daterange"
            v-model="filterDate"
            popper-class="datePicker"
            unlink-panels
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
          ></el-date-picker>
        </div>
        <div>
          部门：
          <el-select v-model="deptId" clearable placeholder="全部">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
              change="">
            </el-option>
          </el-select>
        </div>
        <el-button @click="setVisible">下 载</el-button>
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
        label="姓名"
        align="center"
        min-width="130"
      ></el-table-column>
      <el-table-column
        prop="attStartTime"
        label="签到时间"
        align="center"
        min-width="270"
      ></el-table-column>
      <el-table-column
        prop="attEndTime"
        label="签退时间"
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
import {Log} from "@/api/attendance/signlogsort/index.js";
import {Download} from "@/api/extendsion/download/index.js";
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
      options: [
      {
        value: '203',
        label: '开发挂件小分队'
      }, {
        value: '204',
        label: '塔罗会'
      }, {
        value: '205',
        label: 'NGC2237'
      }, {
        value: '206',
        label: 'GG Bond 粉丝群'
      }, {
        value: '207',
        label: '原神理工大学家教群'
      }, {
        value: '208',
        label: '瑞克五专卖(魔仙堡店)'
      }, {
        value: '209',
        label: '羊村'
      }, {
        value: '210',
        label: 'CT阵营'
      }, {
        value: '211',
        label: '狼堡'
      }, {
        value: '212',
        label: '算法提前批'
      }, {
        value: '213',
        label: '真假🖐🏻👌🏻🐟'
      }, {
        value: '214',
        label: 'T阵营'
      }], // 部门选项
      deptId: ""      // 部门id
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
      this.deptTime();
    },

    /**
     * 获取记录
     * @param {Number} currentPage 自定义参数:当前页数
     * @param {Number} pageCount 自定义参数:每页最大展示条数
     */
    deptTime(currentPage, pageCount) {
      let tmpObj = {
        attStartTime: this.filterDate ? this.filterDate[0].getTime() : null,
        attEndTime: this.filterDate ? this.filterDate[1].getTime() : null,
        currentPage: currentPage || this.currentPage,
        pageCount: pageCount || 15,
        deptId: this.deptId || 1
      };

      this.log.deptTime(tmpObj).then((res) => {
        console.log(res);
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
      this.pageData.totalPages = res.data.pages;
      this.pageData.currentPage = this.currentPage;
      this.pageData.pagerCount = this.pagerCount ? this.pagerCount : 7;
      // console.log(this.pageData.totalPages);
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
      return `${date.getMonth() + 1}-${date.getDate()} ${date.toLocaleTimeString()}`;
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
     * 获取文件
     */
    getFile() {
      if (this.fileName !== "") {
        this.log.getFile(this.fileName).then((res) => {
          new Download().downloadBlob(
            res,
            "application/vnd.ms-excel",
            this.fileName
          );
        });
      }
    },

    /**
     * 按钮按下
     */
    pageClick(index) {
      this.currentPage = index;
    },
  },
  watch: {
    filterDate(newVal, oldVal) {
      //更新数据
      if (this.currentPage !== 1) {
        this.currentPage = 1;
      }
      this.deptTime();
    },
    currentPage(newVal, oldVal) {
      this.deptTime();
    },
    deptId(newVal, oldVal) {
      this.deptTime()
    }
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

.datePicker{
  background-color: black;
}

/deep/ .is-right {
  display: none;
}
</style>
