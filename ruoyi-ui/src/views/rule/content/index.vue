<script>
// import axios from "axios";
import VueMarkdown from "vue-markdown";
import { selectAllLog, downLoad, upLoad, reLoad } from "@/api/rule/content";
import { saveAs } from "file-saver";
export default {
  name: "index",
  data() {
    return {
      //存储文件
      file: null,
      //markde
      markdown: "",
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
      //用户表格数据
      tableData: null,
    };
  },
  created() {
    this.getList();
    this.getloadFile();
  },
  methods: {
    handleFileChange(event) {
      this.file = event.target.files[0];
    },
    uploadFile() {
      const formData = new FormData();
      formData.append("file", this.file);
      upLoad(formData)
        .then((response) => {
          console.log("文件上传成功", response);
          location.reload(); // 在文件上传成功后刷新页面
        })
        .catch((error) => {
          console.error("上传失败");
        });
    },
    //获取所有用户的操作日志
    getList() {
      selectAllLog(this.queryParams.pageNum, this.queryParams.pageSize).then(
        (response) => {
          this.tableData = response.data.list;
          this.total = response.data.total;
        }
      );
    },
    //重置文件内容
    resetFile() {
      reLoad();
      location.reload();
    },
    //获取文件内容 解析marked
    getloadFile() {
      downLoad().then((response) => {
        this.markdown = response;
      });
    },
    downloadFile() {
      const blob = new Blob([this.markdown], {
        type: "text/markdown;charset=utf-8",
      });
      saveAs(blob, "创新实验室奖惩制度.md");
    },
    //监听 pagesize 改变的事件
    handleSizeChange(newsize) {
      //这里conso 选中第几页 最新的值
      console.log(newsize);
      //最新的条数（newsize）赋值给 动态的 pagesie
      this.queryParams.pageSize = newsize;
      //获取到最新一页显示的数据  重新发送axios请求 这里是封装好的请求方法
      this.getList();
    },

    // 监听 页码值 改变的事件
    handleCurrentChange(newPage) {
      console.log(newPage);
      //把最新的页码（newPage）赋值给 动态的 pagenum
      this.queryParams.pageNum = newPage;
      //获取到最新显示的页码值  重新发送axios请求 这里是封装好的请求方法
      this.getList();
    },
  },
  components: {
    VueMarkdown,
  },
};
</script>

<template>
  <div class="content">
    <el-card class="left box-card">
      <b v-if="false"><i>只允许上传markdown文档</i></b>
      <br />
      <input
        v-if="false"
        type="file"
        @change="handleFileChange"
        style="width: 250px; height: 20px"
      />

      <el-button @click="uploadFile" v-if="false">上传</el-button>
      <el-button @click="resetFile" v-if="false">重置</el-button>
      <el-button @click="downloadFile">下载</el-button>
      <hr />
      <vue-markdown :source="markdown"></vue-markdown>
    </el-card>
    <el-card class="box-card right">
      <h1>公告栏</h1>
      <el-table :data="tableData" style="width: 100%">
        <el-table-column
          prop="createTime"
          key="createTime"
          label="日期"
          width="100"
        >
        </el-table-column>
        <el-table-column prop="nickName" key="nickName" label="姓名" width="70">
        </el-table-column>
        <el-table-column prop="reasonContent" key="reasonContent" label="原因">
        </el-table-column>
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
    </el-card>
  </div>
</template>

<style scoped>
.content {
  width: 100%;
  height: 100%;
  display: flex;
}

.left {
  width: 75%;
}

.right {
  width: 35%;
}
</style>
