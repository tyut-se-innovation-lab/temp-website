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
          <el-select v-model="deptId" clearable placeholder="请选择部门" @change="selectionPage" @clear="selectionPageClear">
            <el-option
              v-for="item in options"
              :key="item.deptId"
              :label="item.deptName"
              :value="item.deptId">
            </el-option>
          </el-select>
        </div>
      </div>
    </div>
    <el-skeleton :loading="loading" animated>
        <template #template>
            <div style="margin: 44px 60px 0 60px">
                <div v-for="index in 5">
                    <el-skeleton-item variant="text" style="height: 35px;width: 50%; margin-bottom: 15px"/>
                    <el-skeleton-item variant="text" style="height: 35px;margin-bottom: 15px"/>
                </div>
            </div>
        </template>
    </el-skeleton>
    <div class="tableAnimation">
      <el-table v-if="!loading" :data="signLogShowData" id="table">
        <el-table-column
            prop="userName"
            label="姓名"
            align="center"
            min-width="130"
        ></el-table-column>
        <el-table-column
            prop="attEndTime"
            label="占位1"
            align="center"
            min-width="270"
        ></el-table-column>
        <el-table-column
            prop="attStartTime"
            label="占位2"
            align="center"
            min-width="270"
        ></el-table-column>
        <el-table-column
            prop="signTime"
            label="总计有效时间(h)"
            align="center"
            min-width="130"
        ></el-table-column>
      </el-table>
      <Page v-if="!loading" :pageData="pageData" @pageClick="pageClick" ref="child" id="page"></Page>
    </div>
  </div>
</template>

<script>
import {Log} from "@/api/attendance/signlogsort/index.js";
import Page from "@/views/components/pages/index.vue";
import {listDept} from "@/api/system/dept";

export default {
  name: "log",
  data() {
    return {
      log: new Log(),
      signLog: {},
      signLogShowData: [], //要展示的数据
      filterDate: null,
      zhan1: "不加这个显得很空旷但又让我美化",
      zhan2: "但我也不知道美化啥所以我随便写点吧",
      currentPage: 1,
      pageData: {
        totalPages: 0,
        currentPage: 0,
        pagerCount: 0,
      },
      options: [], // 部门选项
      deptId: "",      // 部门id
      selectionPageNum: 0,  // 选的哪个分页
      loading: true
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
    // 选择的哪个分页
    selectionPage() {
      this.selectionPageNum = 1;
      this.loading = true;
    },
    selectionPageClear() {
      this.selectionPageNum = 0;
      this.allDeptTime();
      this.loading = true;
    },

    /** 查询部门列表 */
    getList() {
      listDept(this.queryParams).then(response => {
        this.options = response.data
        this.options.splice(0, 3)
      });
    },

    /**
     * 初始化
     */
    init() {
      this.allDeptTime();
    },

    /**
     * 获取部门记录
     * @param {Number} currentPage 自定义参数:当前页数
     * @param {Number} pageCount 自定义参数:每页最大展示条数
     */
    deptTime(currentPage, pageCount) {
      let tmpObj = {
        attStartTime: this.filterDate ? this.filterDate[0].getTime() : null,
        attEndTime: this.filterDate ? this.filterDate[1].getTime() : null,
        currentPage: currentPage || this.currentPage,
        pageCount: pageCount || 15,
        deptId: this.deptId || 0
      };

      if (this.selectionPageNum === 1) {
        this.log.deptTime(tmpObj).then((res) => {
          // 为了好看
          setTimeout(() => {
              this.loading = false;
          },200)
          this.signLog = res.data;
          this.signLogShowData = res.data.list;
          this.signLogShowData.forEach(val => {
            val.signTime = Math.floor(val.signTime * 100) / 100
            val.attStartTime = this.zhan2
            val.attEndTime = this.zhan1
          })
          this.initPageData(res);

          // console.log(this.$refs.child.init());
        });
      }
    },

    /** 获取所有部门的总计时间 */
    allDeptTime(currentPage, pageCount) {
      let tmpObj = {
        attStartTime: this.filterDate ? this.filterDate[0].getTime() : null,
        attEndTime: this.filterDate ? this.filterDate[1].getTime() : null,
        currentPage: currentPage || this.currentPage,
        pageCount: pageCount || 15,
      };

      this.log.deptAllTime(tmpObj).then((res) => {
        // 为了好看
        setTimeout(() => {
            this.loading = false;
        },200)
        this.signLog = res.data.attendancePageInfo;
        this.signLogShowData = res.data.attendancePageInfo.list;
        this.signLogShowData.forEach(val => {
          val.signTime = Math.floor(val.signTime * 100) / 100
          val.attStartTime = this.zhan2
          val.attEndTime = this.zhan1
        })
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
      if (this.selectionPageNum === 0) {
        this.pageData.totalPages = Math.floor(res.data.total / 15) + 1;
      } else if (this.selectionPageNum === 1) {
        this.pageData.totalPages = res.data.pages;
      }
      // this.pageData.totalPages = res.data.attendancePageInfo.pages;
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
          tmpdata[i].signTimes[1] === null ? null : this.fixTime(tmpdata[i].signTimes[1]);
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
      if (this.selectionPageNum === 0) {
        this.allDeptTime();
      } else if (this.selectionPageNum === 1) {
        this.deptTime();
      }
    },
    deptId(newVal, oldVal) {
      this.deptTime()
    }
  },
  created() {
    this.init();
    this.getList()
  },
};
</script>

<style scoped>
#logheader {
  display: flex;
  justify-content: flex-start;
  margin: 20px;
}

#logheader > div:nth-child(2) {
  margin-left: 150px;
}

#table {
  margin: 0 auto;

  z-index: 100;
  overflow: hidden;
}

.tableAnimation{
  animation-name: table;
  animation-duration: 1.5s;
}

@keyframes table {
  from {
    opacity: .4;
    transform: translateY(100%);
  }
  to {
    opacity: 1;
    transform: translateY(0);

  }
}



.datePicker {
  background-color: black;
}

/deep/ .is-right {
  display: none;
}
</style>
