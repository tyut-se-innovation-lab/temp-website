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
                    <div class="top">
                        <span>部门:</span>
                        <el-select v-model="params.deptIds" multiple style="width:200px" collapse-tags placeholder="选择部门">
                            <el-option v-for="item in roleNum" :key="item.value" :label="item.label "
                                :value="item.value">
                            </el-option>
                        </el-select>
                        <span>角色:</span>
                        <el-select v-model="params.roleIds" style="width:200px" placeholder="选择角色">
                            <el-option v-for="item in deptNum" :key="item.value" :label="item.label "
                                :value="item.value">
                            </el-option>
                        </el-select>
                    </div>
                    <template v-for="contect in params.TimeFrame">
                        <div class="checkbox">

                            <span>选择第几周</span>
                            <el-select v-model="contect.weekNo" style="width:200px" placeholder="选择第几周">
                                <el-option v-for="item in week" :key="item.value" :label="item.label"
                                    :value="item.value">
                                </el-option>
                            </el-select>
                            <span>选择具体一天</span>
                            <el-select v-model="contect.week" style="width:200px" placeholder="选择具体一天">
                                <el-option v-for="item in day" :key="item.value" :label="item.label | fifweekday"
                                    :value="item.value ">
                                </el-option>
                            </el-select>
                            <span>课时</span>
                            <el-select v-model="contect.period" style="width:200px" placeholder="课时">
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

// import request from '@/utils/request'
import {listDept} from '@/api/system/dept.js'
import {listRole} from '@/api/system/role.js'
import {listleisure} from '@/api/schedule/leisure.js'

export default {
    data() {
        return {
            // 发送的对象
            timeFrames: {},
            // 多选
            multiple: true,
            // 斑马纹
            stripe: true,
            // 一个学期的列表
            week: [],
            // 一星期的列表
            day: [],
            // 课时列表
            time: [],
            // 小组列表
            roleNum: [],
            // 选择的小组
            chooserole:[],
            // 展示表格
            show: false,
            // 左右边框
            border: true,           
            // 角色称呼
            deptNum: [],
            // 发送的id组群
            rolegroups: "",
            // 发送的周组群
            weekgroups: [],
            // 发送的day组群
            daygroups: [],
            // 发送时间组群
            timegroup: [],
            // 数据展示
            tableData: [],
            //
            params:{
                TimeFrame: [
                {
                    // 具体那一周
                    weekNo: '',
                    // 具体周几
                    week: '',
                    // 具体课时
                    period: '',
                }
            ],
                deptIds:'',
                roleIds:''
            }
        }
    },
    created(){
        // 获取部门信息
        this.getselablistDept()
        // 获取角色信息
        this.getselablistRole()
    },
    mounted() {
        this.addoption();


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
            this.params.TimeFrame.push(
                {
                    // 具体那一周
                    weekNo: '',
                    // 具体周几
                    week: '',
                    // 具体课时
                    period: '',
                }
            )
        },
        // 删除元素
        deletetime(index) {
            this.params.TimeFrame.splice(index, 1)
        },
        // 查询空课
        check() {
            this.show = true;
            // console.log(this.contect);
            this.weekgroups = [];
            this.daygroups = [];
            this.timegroup = [];
            for (let i = 0; i < this.params.TimeFrame.length; i++) {
                this.weekgroups.push(
                    this.params.TimeFrame[i].weekNo
                )
            }
            for (let i = 0; i < this.params.TimeFrame.length; i++) {
                this.daygroups.push(
                    this.params.TimeFrame[i].week
                )
            } for (let i = 0; i < this.params.TimeFrame.length; i++) {
                this.timegroup.push(
                    this.params.TimeFrame[i].period
                )
            }
            this.getmessage();

        },
        // 获取空课人员
        getmessage() {       
            listleisure(this.params).then((res) =>{
                console.log(res); 
                this.tableData = [];
                for(let i=0 ;i<res.data.length;i++){
                   
                    this.tableData.push(
                        {
                            name:res.data[i].nickName,
                            more:`管理权限：${this.getusername(res.data[i].roleNames)}性别：${this.checksex(res.data[i].sex)}`
                        }
                    )
                }
            })
        },
        // 获取性别
        checksex(val){
            if(val == 1){
                return `男`
            }
            else if(val == 0){
                return `女`
            }
        },
        // 获取部门信息
       getselablistDept(){
        listDept().then((res)=>{

            for(let i=0;i<res.data.length;i++){
                this.roleNum.push(
                    {
                        value: res.data[i].deptId,
                        label: res.data[i].deptName
                    }
                )
            }

        })
       },
        // 获取角色
        getselablistRole(){
            listRole().then((res)=>{
                // console.log(res);
                for(let i=0;i<res.rows.length;i++){
                this.deptNum.push(
                    {
                        value: res.rows[i].roleId,
                        label: res.rows[i].roleName

                    })   
                }
            })
        },
        // 便利管理权限
        getusername(val){
            let temp='';
            if(val instanceof Array){
                for(let a=0; a<val.length;a++){
                    temp =temp+val[a]+','
                }
                return temp+" "
            }
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

<style lang="css" scoped>
.el-header {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 100px;
    height: 100px !important;
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

.top {
    margin-left: 10px;
    text-align: left;
    height: 60px;
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
