<template>
    <div>
        <el-container>

            <el-header>
                随机排课
            </el-header>
            <el-container>
                <el-main>

                        <div class="choose">
                            <span>选择部门:</span>
                            <el-select v-model="role"  placeholder="请选择部门">
                                <el-option v-for="item in roleNum" :key="item.value" :label="item.label "
                                    :value="item.value">
                                </el-option>
                            </el-select>
                            <!-- <span>请选择小组:</span>
                            <el-select v-model="group"  placeholder="请选择小组">
                                <el-option v-for="item in caregroups" :key="item.value" :label="item.label | fifgroup"
                                    :value="item.value">
                                </el-option>
                            </el-select> -->
                            <span>选择第几周:</span>
                            <el-select v-model="week"  placeholder="请选择第几周">
                                <el-option v-for="item in WeekNum" :key="item.value" :label="item.label | fifweek"
                                    :value="item.value">
                                </el-option>
                            </el-select>
                            <br/>
                            <span>排课小时:</span>
                            <el-select v-model="chooseday"  placeholder="请选择需要排课的小时">
                                <el-option v-for="item in needday" :key="item.value" :label="item.label | fifday"
                                    :value="item.value">
                                </el-option>
                            </el-select>
                            <span>选择座位数:</span>

                            <el-input-number v-model="seat" controls-position="right"  :min="1"  style="width:230px" ></el-input-number>


                            <div class="button">
                                <el-button type="primary" @click="sout(role,week,chooseday,seat)">排课</el-button>
                            </div>
                        </div>
                         <br/>
                            <el-table :data="tableData" stripe border :cell-style="{ textAlign: 'center' }"
                                :header-cell-style="{ textAlign: 'center' }">
                                <el-table-column prop="date" label="时间/星期">
                                </el-table-column>
                                <el-table-column prop="week.Mon" label="周一">
                                </el-table-column>
                                <el-table-column prop="week.Tue" label="周二">
                                </el-table-column>
                                <el-table-column prop="week.Wed" label="周三">
                                </el-table-column>
                                <el-table-column prop="week.Thu" label="周四">
                                </el-table-column>
                                <el-table-column prop="week.Fir" label="周五">
                                </el-table-column>
                                <el-table-column prop="week.Sat" label="周六">
                                </el-table-column>
                                <el-table-column prop="week.Sun" label="周日">
                                </el-table-column>
                            </el-table>
                            <br><br>

                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>

import request from '@/utils/request'
import {listDept} from '@/api/system/dept.js'
export default {
    data() {
        return {
            // 斑马纹
            stripe: true,
            // 左右边框
            border: true,
            // 排课的那一周
            week: '',
            // 需要的天数
            needday:[],
            // 需要的几天
            chooseday:'',
            // 总共排课所需要的周数
            WeekNum:[],
            //座位数
            seat:'',
            // 部门选择
            role:'',
            // 全部部门
            roleNum:[
                {
                    value:1,
                    label:"开发组"
                },
                {
                    value:2,
                    label:"网安组"

                }
            ],
            // 小组选择
            // group:'',
            // 具体小组选择
            // caregroups:[],
            // 数据展示
            tableData: [{
                date: '第一节课',
                week: {
                    Tue: '',
                    Mon: '',
                    Wed: '',
                    Thu: '',
                    Fir: '',
                    Sat: '',
                    Sun: ''
                }
            }, {
                date: '第二节课',
                week: {
                    Mon: "",
                    Tue: '',
                    Wed: '',
                    Thu: '',
                    Fir: '',
                    Sat: '',
                    Sun: ''
                }
            }, {
                date: '第三节课',
                week: {
                    Mon: "",
                    Tue: '',
                    Wed: '',
                    Thu: '',
                    Fir: '',
                    Sat: '',
                    Sun: ''
                }
            }, {
                date: '第四节课',
                week: {
                    Mon: "",
                    Tue: '',
                    Wed: '',
                    Thu: '',
                    Fir: '',
                    Sat: '',
                    Sun: ''
                }
            }, {
                date: '第五节课',
                week: {
                    Mon: "",
                    Tue: '',
                    Wed: '',
                    Thu: '',
                    Fir: '',
                    Sat: '',
                    Sun: ''
                }
            }, {
                date: '第六节课',
                week: {
                    Mon: "",
                    Tue: '',
                    Wed: '',
                    Thu: '',
                    Fir: '',
                    Sat: '',
                    Sun: ''
                }
            }, {
                date: '第七节课',
                week: {
                    Mon: "",
                    Tue: '',
                    Wed: '',
                    Thu: '',
                    Fir: '',
                    Sat: '', 
                date: '第八节课',
                week: {
                    Mon: "",
                    Tue: '',
                    Wed: '',
                    Thu: '',
                    Fir: '',
                    Sat: '',
                    Sun: ''
                }
            },
          
                date: '第十节课',
                week: {
                    Mon: "",
                    Tue: '',
                    Wed: '',
                    Thu: '',
                    Fir: '',
                    Sat: '',
                    Sun: ''
                }
            },
            ]

        }
    },
    mounted() {
        // 渲染下拉框
        this.addDay();
    },
    methods: {

        // 请求
        axiosRequest() {
            request({
                method:'get',
                params:{
                    needTime:this.chooseday,
                    seatNum:this.seat,
                    weekNo:this.week,
                    roleId:this.role
                },
                url:'http://kuangshen.xyz:8080/'
            })
                .then(function (response) {
                    console.log(response);
                })
                .catch(function (error) {
                    console.log(error);
                });

        },
        // 插入周，时间，座位数
        addDay(){
            for(let i=1 ;i<21;i++){
                this.WeekNum.push(
                    {
                        value:i,
                        label:i
                    }
                )
            }
            for(let m=1 ;m<16;m++){
                this.needday.push(
                    {
                        value:m,
                        label:m
                    }
                )
            }

        },
        // 写请求
        sout(role,week,day,seat){
            console.log(`rele${role},week${week},day${day},seat${seat}`);
            this.axiosRequest()
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

    },
    filters:{
        fifweek(val){
            return `第${val}周`
        },
        fifday(val){
            return `${val}个小时`
        },
        fifgroup(val){
            return `第${val}组`
        }
    }
}
</script>

<style lang="css" scoped>
.el-header {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 10vh;
    height: 10vh !important;
    font-size: larger;
}


.el-main {
    color: #333;
    text-align: center;
    width: 100%;
    height: auto;
}
.choose{
    width: 1200px;
    margin: 0 auto;
    height: auto;
    text-align: left;

}
.button{
    width: 400px;
    display: inline-block;
    text-align: right;
    height: auto;
}
</style>
