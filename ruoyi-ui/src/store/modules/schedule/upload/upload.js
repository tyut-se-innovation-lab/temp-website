import request from "@/utils/request";
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
            //表格坐标
            state.tableCoordinate.row = time.row;
            state.tableCoordinate.column = time.column - 1;
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
            console.log(state.inputData);
            state.scheduleData[state.tableCoordinate.row].courseData[state.tableCoordinate.column] = state.inputData;
        },
        //修改数据
        modifyScheduleData(state, index) {
            state.inputData = state.scheduleData[index.row].courseData[index.column - 1];
            console.log(state.inputData);
        },
        //发送数据
        sendData(state) {
            request({
                url: '/lottery/p'
            }).then(response => {
                console.log(response);
                self.data = response;
                console.log(self.data.stock);
            })
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
        //课表数据
        dayTime: "",
        whatDay: "",
        //临时数据
        inputData: [],
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
