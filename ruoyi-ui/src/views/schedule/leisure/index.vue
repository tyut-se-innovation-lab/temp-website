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
                        <el-select v-model="params.roleIds" multiple collapse-tags style="width:200px" placeholder="选择角色">
                            <el-option v-for="item in deptNum" :key="item.value" :label="item.label "
                                :value="item.value">
                            </el-option>
                        </el-select>
                    </div>
                    <div v-for="(contect,index) in params.timeFrames" :key='index'>
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
                                    <el-popconfirm title="确定删除吗？" @confirm="deletetime(index)">
                                        <el-button slot="reference" style="background-color:#F56C6C  ; color:aliceblue">
                                            删除</el-button>
                                    </el-popconfirm>
                                </template>
                            </div>
                        </div>
                    </div>
                    <br /><br />
                    <template v-if="show">
                        <el-table :data="tableData" style="width: 100%" :cell-style="{ textAlign: 'center' }"
                            :header-cell-style="{ textAlign: 'center' }" border stripe >
                            <el-table-column prop="username" label="用户名称">
                            </el-table-column>
                            <el-table-column prop="name" label="姓名" >
                            </el-table-column>
                            <el-table-column prop="root" label="管理权限">
                            </el-table-column>
                              <el-table-column prop="deptName" label="小组">
                            </el-table-column>
                            <el-table-column prop="sex" label="性别">
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
// 为添加一个数组的全局变量

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
                timeFrames: [
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
            this.params.timeFrames.push(
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
            this.params.timeFrames.splice(index, 1)
        },
        // 查询空课
        check() {
           
            // console.log(this.contect);
            this.weekgroups = [];
            this.daygroups = [];
            this.timegroup = [];
            for (let i = 0; i < this.params.timeFrames.length; i++) {
                this.weekgroups.push(
                    this.params.timeFrames[i].weekNo
                )
            }
            for (let i = 0; i < this.params.timeFrames.length; i++) {
                this.daygroups.push(
                    this.params.timeFrames[i].week
                )
            } for (let i = 0; i < this.params.timeFrames.length; i++) {
                this.timegroup.push(
                    this.params.timeFrames[i].period
                )
            }
            
            this.isnull()
        },
        // 获取空课人员
        getmessage() {       
            // console.log(this.params.timeFrames);
            this.show = true;
            listleisure(this.params).then((res) =>{
                console.log(res); 
                this.tableData = [];
                for(let i=0 ;i<res.data.length;i++){
                   
                    this.tableData.push(
                        {
                            name:res.data[i].nickName,
                            root:res.data[i].roleNames.join(", "),
                            sex:this.checksex(res.data[i].sex),
                            username: res.data[i].userName,
                            deptName: res.data[i].deptName      
                        }
                    )
                }
            })
        },
        // 获取性别
        checksex(val){
            if(val == 0){
                return `男`
            }
            else if(val == 1){
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
        // 加选项一个判断
        isnull(){
                // console.log(this.params.TimeFrame[0].weekNo);
            if(this.judge( this.params.timeFrames))
            {
                
                this.$modal.msgError("时间不能为空！！！");
            }
            else{
                this.getmessage()
            }
        },
        // 判断封装
        judge(val){ 
             console.log(val);
            for(let i=0 ;i<val.length;i++){
                if(val[i].weekNo == ''){
                    return true
                } 
                else if(val[i].week == ''){
                    return true
                } 
                else if(val[i].period == ''){
                    return true
                } 
            }
            return false;
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
    width:1200px;
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
    width:28 0px

}
</style>
