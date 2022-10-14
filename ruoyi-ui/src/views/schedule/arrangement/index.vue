<template>
    <div>
      <el-container>

        <el-header>
          随机排课
        </el-header>
        <el-container>
          <el-main>

            <div class="choose">
              <span>选择角色:</span>
              <el-select v-model="data.roleId" placeholder="请选择角色">
                <el-option v-for="item in roleNum" :key="item.value" :label="item.label " :value="item.value">
                </el-option>
              </el-select>
              <!-- <span>请选择小组:</span>
                            <el-select v-model="group"  placeholder="请选择小组">
                                <el-option v-for="item in caregroups" :key="item.value" :label="item.label | fifgroup"
                                    :value="item.value">
                                </el-option>
                            </el-select> -->
              <span>选择第几周:</span>
              <el-select v-model="data.weekNo" placeholder="请选择第几周">
                <el-option v-for="item in WeekNum" :key="item.value" :label="item.label | fifweek" :value="item.value">
                </el-option>
              </el-select>
              <br />
              <span>排课小时:</span>
              <el-select v-model="data.needTime" placeholder="请选择需要排课的小时">
                <el-option v-for="item in needday" :key="item.value" :label="item.label | fifday" :value="item.value">
                </el-option>
              </el-select>
              <span>选择座位数:</span>

              <el-input-number v-model="data.seatNum" controls-position="right" :min="1" style="width:230px">

              </el-input-number>


              <div class="button">
                <el-button type="primary" @click="axiosRequest">排课</el-button>
              </div>
            </div>
            <br />
            <div class="table">
              <el-table :data="tableData" stripe style="width: 100%" border :cell-style="{ textAlign: 'center' }"
                :header-cell-style="{ textAlign: 'center' }">
                <el-table-column prop="data" label="课时/星期">
                </el-table-column>
                <el-table-column prop="week.Mon" label="星期一">
                </el-table-column>
                <el-table-column prop="week.Tue" label="星期二">
                </el-table-column>
                <el-table-column prop="week.Wed" label="星期三">
                </el-table-column>
                <el-table-column prop="week.Thu" label="星期四">
                </el-table-column>
                <el-table-column prop="week.Fir" label="星期五">
                </el-table-column>
                <el-table-column prop="week.Sat" label="星期六">
                </el-table-column>
                <el-table-column prop="week.Sun" label="星期日">
                </el-table-column>
              </el-table>
              <br><br>
            </div>
          </el-main>
        </el-container>
      </el-container>
    </div>
  </template>

  <script>
  import {listAttange} from '@/api/schedule/arrange'
  import {listRole} from '@/api/system/role.js'
  export default {
    data() {
        return {
            // 斑马纹
            stripe: true,
            // 左右边框
            border: true,
            // 需要的天数
            needday:[],
            // 总共排课所需要的周数
            WeekNum:[],
            // 全部部门
            roleNum:[],
            // 数据展示
            tableData: [],
            // 请求数据
            data:{
                needTime:'',
                weekNo:'',
                seatNum:'',
                roleId:''
            },

        }
    },
    mounted() {
        // 增加天数
        this.addDay()
        // 获取部门
        this.getselablistRole()


    },
    methods: {
        // 请求
        axiosRequest() {
            listAttange(this.data).then( (res)=>{
                console.log(res)
            }
         )
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
            for(let k=0;k<10;k++){
              this.tableData.push(
                  {
                    data:`第${k+1}节课`,
                    week:{
                      Mon:'',
                      Tue:'',
                      Wed:'',
                      Thu:'',
                      Fir:'',
                      Sat:'',
                      Sun:''
                    }
                  }
              )
            }
        },
        // 获取角色
        getselablistRole(){
            listRole().then((res)=>{
                for(let i=0;i<res.rows.length;i++){
                this.roleNum.push(
                    {
                        value: res.rows[i].roleId,
                        label: res.rows[i].roleName

                    })   
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
        },
        fifDataWeek(val){
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
  .table{
    width:1200px;
    margin: 0 auto;
  }
  </style>
