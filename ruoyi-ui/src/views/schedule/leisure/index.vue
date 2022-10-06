<template>
    <div>
        <el-container>
            <div class="button">
                <el-button type="primary" @click="addnewtime">添加查询时间</el-button>
                <el-button type="primary" @click="check">查询空课人员</el-button>
            </div>
            <el-header>
                <strong>查看空课人员</strong>
            </el-header>
            <el-container>
                <el-main>

                    <template v-for="contect in contect">
                        <div class="checkbox">
                            <span>小组:</span>
                            <el-select v-model="contect.role" style="width:150px" placeholder="选择小组">
                                <el-option v-for="item in roleNum" :key="item.value" :label="item.label "
                                    :value="item.value">
                                </el-option>
                            </el-select>
                            <span>选择第几周</span>
                            <el-select v-model="contect.opWeek" style="width:150px" placeholder="选择第几周">
                                <el-option v-for="item in week" :key="item.value" :label="item.label"
                                    :value="item.value">
                                </el-option>
                            </el-select>
                            <span>选择具体一天</span>
                            <el-select v-model="contect.opDay" style="width:150px" placeholder="选择具体一天">
                                <el-option v-for="item in day" :key="item.value" :label="item.label | fifweekday"
                                    :value="item.value ">
                                </el-option>
                            </el-select>
                            <span>课时</span>
                            <el-select v-model="contect.opTime" style="width:150px" placeholder="课时">
                                <el-option v-for="item in time" :key="item.value" :label="item.label | fiftime"
                                    :value="item.value">
                                </el-option>
                            </el-select>

                            <div class="del">
                                <template>
                                    <el-popconfirm title="确定删除吗？" @confirm="deletetime(contect.index)">
                                        <el-button slot="reference" style="background-color:#F56C6C  ; color:aliceblue">
                                            删除</el-button>
                                    </el-popconfirm>
                                </template>
                            </div>
                        </div>
                    </template>
                    <br /><br />
                    <template v-if="show">
                        <el-table :data="tableData" style="width: 100%" :cell-style="{ textAlign: 'center' }"
                            :header-cell-style="{ textAlign: 'center' }" border stripe>
                            <el-table-column prop="name" label="姓名" width="200px">
                            </el-table-column>
                            <el-table-column prop="more" label="详情">
                            </el-table-column>
                        </el-table>
                    </template>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>

import axios from 'axios'
export default {
    data() {
        return {
            // 斑马纹
            stripe: true,
            // 一个学期的列表
            week: [],
            // 一星期的列表
            day: [],
            // 课时列表
            time: [],
            // 小组列表
            roleNum: [
                {
                    value: 1,
                    label: "开发组"
                },
                {
                    value: 2,
                    label: "网安组"
                }
            ],
            // 展示表格
            show: false,
            // 左右边框
            border: true,
            // 动态添加时间，小组，那一周
            contect: [
                {
                    // 具体那一个角色
                    role: '',
                    // 具体那一周
                    opWeek: '',
                    // 具体周几
                    opDay: '',
                    // 具体课时
                    opTime: '',
                }
            ],
            // 发送的id组群
            rolegroups: [],
            // 发送的周组群
            weekgroups: [],
            // 发送的day组群
            daygroups: [],
            // 发送时间组群
            timegroup: [],
            // 数据展示
            tableData:[
                {
                    name:'侯瑞宁',
                    more:"手机号：19928383737，性别：女"
                },
                {
                    name:'冯阳',
                    more:"手机号：19929293737，性别：女"
                }
            ]
        }
    },
    mounted() {
        this.addoption()
    },
    methods: {
        // 为选项值便利
        addoption() {
            for (let i = 1; i < 21; i++) {
                this.week.push({
                    value: i,
                    label: `第${i}周`
                })
            }

            for (let m = 1; m < 8; m++) {
                this.day.push(
                    {
                        value: m,
                        label: m

                    }
                )
            }
            for (let m = 1; m < 11; m++) {
                this.time.push(
                    {
                        value: m,
                        label: m

                    }
                )
            }
        },
        // 添加查询时间
        addnewtime() {
            this.contect.push(
                {
                    // 具体那一个角色
                    role: '',
                    // 具体那一周
                    opWeek: '',
                    // 具体周几
                    opDay: '',
                    // 具体课时
                    opTime: '',
                }
            )
        },
        deletetime(index) {
            this.contect.splice(index, 1)
        },
        check() {
            this.show = true;
            console.log(this.contect);
            this.rolegroups = [];
            this.weekgroups = [];
            this.daygroups = [];
            this.timegroup = [];
            for (let i = 0; i < this.contect.length; i++) {
                this.rolegroups.push(
                    this.contect[i].role
                )
            }
            for (let i = 0; i < this.contect.length; i++) {
                this.weekgroups.push(
                    this.contect[i].opWeek
                )
            }
            for (let i = 0; i < this.contect.length; i++) {
                this.daygroups.push(
                    this.contect[i].opDay
                )
            } for (let i = 0; i < this.contect.length; i++) {
                this.timegroup.push(
                    this.contect[i].opTime
                )
            }
            console.log(`rolegroups：${this.rolegroups}--weekgroups:${this.weekgroups}--daygroups:${this.daygroups}--timegroup:${this.timegroup}`);
        },
        getmessage(){
            axios({
                    method:'get',
                    params:{},
                    url:''
                }).then(function(res){
                    console.log(res);
                    tableData = res
                })
        }
    },
    filters: {
        fifweekday(val) {
            if (val == 1) {
                return '周一'
            }
            else if (val == 2) {
                return '周二'
            }
            else if (val == 3) {
                return '周三'
            }
            else if (val == 4) {
                return '周四'
            }
            else if (val == 5) {
                return '周五'
            }
            else if (val == 6) {
                return '周六'
            }
            else if (val == 7) {
                return '周日'
            }

        },
        fiftime(val) {
            return `第${val}节课`
        }
    }

}
</script>

<style scoped>
.el-header {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 10vh;
    height: 10vh !important;
    font-size: larger;
}

.checkbox {
    width: 1000px;
    margin: 0 auto;
    height: auto;
    line-height: 40px;
    text-align: left;
}

.del {
    display: inline-block;
    margin-left: 20px;
}

.el-main {

    color: #333;
    text-align: center;
    width: 100%;
    height: auto;
}

.button {
    position: absolute;
    top: 25px;
    right: 40px;

}
</style>
