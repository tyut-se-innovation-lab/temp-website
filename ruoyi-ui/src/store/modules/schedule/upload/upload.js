import { uploadSchedule } from "@/api/schedule/upload";
//求和功能相关的配置
export default {
    //开启命名空间
    namespaced: true,
    actions: {

    },
    mutations: {
        //改变课表和表单的比例，出现表单
        changeScale(state) {
            state.scale1 = 18;
            state.scale2 = 6;
        },
        //改变课表和表单的比例，表单消失
        restoreScale(state) {
            state.scale1 = 24;
            state.scale2 = 0;
        },
        //传递时间
        sendTime(state, time) {
            let currentTime = new Date();
            //时间
            state.period = time.period;
            state.week = time.week;
            //夏冬季不同，计算的方式也不同
            if (time.row < 4) {
                state.dayTime = `${time.row + 8}点~${time.row + 9}点`;
            } else {
                state.dayTime = `${time.row + 10}点~${time.row + 11}点`;
            }
            //一周中的那一天
            state.whatDay = time.whatDay;
        },
        // 存储临时数据
        storeInputData(state, data) {
            console.log(data.index);
            state.inputData[data.index] = data.scheduleData;
        },
        //清空临时数据
        clearInputData(state) {
            state.inputData = [];
        },
        //提交并存储数据
        storeScheduleData(state) {
            state.scheduleData[state.tableCoordinate.row].courseData[state.tableCoordinate.column] = state.inputData;
            for (let i = 0; i < state.inputData.length; i++) {
                for (let j = state.inputData[i].startWeek; j <= state.inputData[i].endWeek; j++) {
                    state.sendedData.push({
                        period: state.period,
                        week: state.period,
                        weekNo: j,
                        title: state.inputData[i].courseName,
                    })
                }

            }
        },
        //修改数据
        modifyScheduleData(state, index) {
            state.inputData = state.scheduleData[index.row].courseData[index.column - 1];
            console.log(state.inputData);
        },
        //发送数据

        //修改数据为可读可写
        writableData(state) {
            state.writable = !state.writable;
        }
    },
    state: {
        //输入表单的比例
        scale1: 24,
        scale2: 0,
        //表格坐标
        tableCoordinate: {
            row: 0,
            column: 0
        },
        //定义是否可读或者可写
        writable: false,
        //课表数据
        period: 0,
        week: 0,
        whatDay: "",
        dayTime: "",
        //临时数据
        inputData: [],
        sendedData: [],
        scheduleData: [
            {
                // 第一行
                num: "1",
                courseData: [
                    //第一列
                    [
                        //存储多组数据
                        //第一组
                        {
                            courseName: "",
                            startWeek: 0,
                            endWeek: 0,
                        },
                    ],
                ],
            },
            {
                //冬季和夏季时间不一样，不能一概而论
                num: "2",
                courseData: [
                    //第一列
                    [
                        //存储多组数据
                        //第一组
                        {
                            courseName: "",
                            startWeek: 0,
                            endWeek: 0,
                        },
                    ],
                ],
            },
            {
                num: "3",
                courseData: [
                    //第一列
                    [
                        //存储多组数据
                        //第一组
                        {
                            courseName: "",
                            startWeek: 0,
                            endWeek: 0,
                        },
                    ],
                ],
            },
            {
                num: "4",
                courseData: [
                    //第一列
                    [
                        //存储多组数据
                        //第一组
                        {
                            courseName: "",
                            startWeek: 0,
                            endWeek: 0,
                        },
                    ],
                ],
            },
            {
                num: "5",
                courseData: [
                    //第一列
                    [
                        //存储多组数据
                        //第一组
                        {
                            courseName: "",
                            startWeek: 0,
                            endWeek: 0,
                        },
                    ],
                ],
            },
            {
                num: "6",
                courseData: [
                    //第一列
                    [
                        //存储多组数据
                        //第一组
                        {
                            courseName: "",
                            startWeek: 0,
                            endWeek: 0,
                        },
                    ],
                ],
            },
            {
                num: "7",
                courseData: [
                    //第一列
                    [
                        //存储多组数据
                        //第一组
                        {
                            courseName: "",
                            startWeek: 0,
                            endWeek: 0,
                        },
                    ],
                ],
            },
            {
                num: "8",
                courseData: [
                    //第一列
                    [
                        //存储多组数据
                        //第一组
                        {
                            courseName: "",
                            startWeek: 0,
                            endWeek: 0,
                        },
                    ],
                ],
            },
            {
                num: "9",
                courseData: [
                    //第一列
                    [
                        //存储多组数据
                        //第一组
                        {
                            courseName: "",
                            startWeek: 0,
                            endWeek: 0,
                        },
                    ],
                ],
            },
            {
                num: "10",
                courseData: [
                    //第一列
                    [
                        //存储多组数据
                        //第一组
                        {
                            courseName: "",
                            startWeek: 0,
                            endWeek: 0,
                        },
                    ],
                ],
            },
        ],
    },
    getters: {
    }
}
