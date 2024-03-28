<template>
    <el-card>
        <!-- 修改对话框 -->
        <el-dialog :title="title" :visible.sync="dialogFormVisible" width="680px" append-to-body>
            <el-form ref="form" :model="form" label-width="80px">
                <el-form-item label="意见主题">
                    <el-col :span="12">
                        <el-input v-model="form.title"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="意见人">
                    <el-col :span="12">
                        <el-input v-model="form.name"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="意见时间">
                    <el-col :span="12">
                        <el-input v-model="form.time"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="意见内容">
                    <el-input type="textarea" v-model="form.desc" :rows="15"></el-input>
                </el-form-item>
            </el-form>
        </el-dialog>
        <el-table :data="currentPageData" style="width: 100%" :header-cell-style="{ 'text-align': 'center' }" border>
            <el-table-column label="主题" prop="suggestionTopic" width="250px" align="center">
            </el-table-column>
            <el-table-column label="提交人" prop="suggestionUser" width="150px" align="center">
            </el-table-column>
            <el-table-column label="时间" prop="creatTime" width="255px" align="center">
            </el-table-column>
            <el-table-column label="内容" prop="suggestionContent" width="755px">
            </el-table-column>
            <el-table-column align="center" label="操作">
                <template slot-scope="scope">
                    <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">详情</el-button>
                    <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination background layout="total, sizes, prev, pager, next, jumper" :total=total :page-size=pageSize
            :current-page="currentPage" @size-change="handleSizeChange" @current-change="handleCurrentChange">
        </el-pagination>
    </el-card>
</template>

<script>
import { suggestionVerity, suggestionList, suggestionDeMessage, suggestionMessage } from "@/api/suggest"
export default {
    data() {
        return {
            tableData: [],
            search: '',
            total: 100,
            currentPage: 1,
            //每页展示10条
            pageSize: 10,
            dialogFormVisible: false,
            title: '',
            form: {
                title: '',
                name: '',
                region: '',
                time: '',
                delivery: false,
                type: [],
                resource: '',
                desc: ''
            },
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
            this.reset();
            this.dialogFormVisible = true;
            suggestionMessage(row.suggestionId).then((res) => {
                this.form.title = res.data.suggestionTopic
                this.form.name = res.data.suggestionUser
                this.form.time = res.data.creatTime
                this.form.desc = res.data.suggestionContent
                console.log(res);
            })
            console.log(index, row);
        },
        async handleDelete(index, row) {
            await suggestionDeMessage(row.suggestionId).then((res) => {
                this.loading = true
                this.$message({
                    type: 'success',
                    message: res.msg
                })
                suggestionList(this.currentPage, this.pageSize).then((res) => {
                    this.tableData = res.data.list
                    this.total = res.data.total
                })
            })
        },
        //表单重置
        reset() {
            this.form = {
                name: '',
                region: '',
                time: '',
                delivery: false,
                type: [],
                resource: '',
                desc: ''
            }
        }
    },
    created() {

        this.$prompt('请输入密钥', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
        }).then(({ value }) => {
            suggestionVerity(value).then((res) => {
                this.$message({
                    type: 'success',
                    message: res.msg
                });
                suggestionList(this.currentPage, this.pageSize).then((res) => {
                    this.tableData = res.data.list
                    this.total = res.data.total
                })
            }).catch(() => {
                this.$message.error('验证失败，请重新输入密钥'); // 提示验证失败
                // 在验证失败后重新提示用户输入密码
                this.$prompt('请输入密钥', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                }).then(({ value }) => {
                    // 重新尝试验证
                    suggestionVerity(value).then((res) => {
                        this.$message({
                            type: 'success',
                            message: res.msg
                        });
                        suggestionList(this.currentPage, this.pageSize).then((res) => {
                            this.tableData = res.data.list
                            this.total = res.data.total
                        })
                    }).catch(() => {
                        this.$message({
                            type: 'info',
                            message: '取消输入'
                        });
                    });
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '取消输入'
                    });
                });
            })
        }).catch(() => {
            this.$message({
                type: 'info',
                message: '取消输入'
            });
        });
    }
};
</script>

<style lang="less" scoped></style>