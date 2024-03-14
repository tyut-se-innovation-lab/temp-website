<template>
    
    <el-card>
        <el-dialog title="意见箱" :visible.sync="dialogFormVisible"  :before-close="handleClose">
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="意见主题"  :rules="[
      { required: true, message: '意见主题不能为空'},
    ]">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="提交方式">
          <el-col :span="8">
            <el-select v-model="form.region" placeholder="实名或者匿名">
              <el-option label="实名" value="shiming"></el-option>
              <el-option label="匿名" value="niming"></el-option>
            </el-select>
          </el-col>
          <el-col :span="15">
            <el-form-item label="信息" v-if="form.region === 'shiming'">
              <el-input v-model="form.relname" v-if="form.region === 'shiming'" placeholder="学号-姓名"></el-input>
            </el-form-item>
          </el-col>
        </el-form-item>
        <el-form-item label="意见时间">
          <el-col :span="12">
            <el-date-picker type="date" placeholder="选择日期" v-model="form.date1" style="width: 100%;"></el-date-picker>
          </el-col>
        </el-form-item>
        <el-form-item label="意见内容"  :rules="[
      { required: true, message: '意见内容不能为空'},

    ]">
          <el-input type="textarea" v-model="form.desc"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="sumbitSuggest">提交建议</el-button>
      </div>
    </el-dialog>
        <el-table
            :data="currentPageData"
            style="width: 100%" :header-cell-style="{ 'text-align': 'center' }" border>
            <!-- <el-table-column label="主题" prop="title"  width="250px" align="center">
            </el-table-column> -->
            <el-table-column label="提交人" prop="suggestionUser" width="150px" align="center">
            </el-table-column>
            <el-table-column label="时间" prop="creatTime" width="255px" align="center">
            </el-table-column>
            <el-table-column label="内容" prop="suggestionContent" width="855px">
            </el-table-column>
            <el-table-column align="right">
                <template slot-scope="scope">
                    <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">Edit</el-button>
                    <el-button size="mini" type="danger"
                        @click="handleDelete(scope.$index, scope.row)">Delete</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination background layout="total, sizes, prev, pager, next, jumper" :total=total :page-size=pageSize
            :current-page="currentPage" @size-change="handleSizeChange" @current-change="handleCurrentChange">
        </el-pagination>
    </el-card>
</template>

<script>
import { suggestionList, suggestionVerity } from "@/api/suggest"
export default {
    data() {
        return {
            tableData: [],
            search: '',
            total: 100,
            currentPage: 1,
            //每页展示10条
            pageSize: 10,
            dialogFormVisible:false
        };
    },
    computed: {
    // 根据当前页码和每页显示条数计算当前页的数据
    currentPageData() {
      const startIndex = (this.currentPage - 1) * this.pageSize;
      const endIndex = startIndex + this.pageSize;
      return this.tableData.slice(startIndex, endIndex);
    },
  },
    methods: {
        // 每页展示条数
        handleSizeChange(val) {
            this.pageSize = val
            // 再次请求数据
     
        },
        // 当前页改变，处理函数
        handleCurrentChange(newPage) {
            this.currentPage = newPage
      
        },
        handleEdit(index, row) {
            this.dialogFormVisible = true
            console.log(index, row);
        },
        handleDelete(index, row) {
            console.log(index, row);
        },
    },
    created() {
        suggestionList(this.currentPage, this.pageSize).then((res) => {
            this.tableData = res.data.list
            this.total = res.data.total
        })
    }
};
</script>

<style lang="less" scoped></style>