import { uploadSchedule } from "@/api/schedule/upload";
import { getSchedule } from "@/api/schedule/display"
import Vue from "vue";
//求和功能相关的配置
export default {
    //开启命名空间
    namespaced: true,
    actions: {
        getData(context) {
            getSchedule().then(response => {
                //填充数据扔到mutation
                context.commit("getScheduleData", response)
            })
        }

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
            //存储坐标
            state.tableCoordinate.row = time.row;
            state.tableCoordinate.column = time.column - 1;
            console.log(state.tableCoordinate.row, state.tableCoordinate.column);
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
            //判断是否该写的数据都写了,判断开始周是否小于结束周
            if (data.courseName != "" && data.startWeek != 0 && data.endWeek != 0 && data.startWeek < data.endWeek) {
                state.inputData[data.index] = data.scheduleData;
            } else {
                alert("输入内容格式错误，请重新输入");
            }
        },
        //清空临时数据
        clearInputData(state) {
            state.inputData = [];
        },

        //提交并存储数据
        storeScheduleData(state) {
            state.scheduleData[state.tableCoordinate.row].courseData[state.tableCoordinate.column] = state.inputData;
            let arr = [];
            for (let i = 0; i < state.inputData.length; i++) {
                arr[i] = state.inputData[i].startWeek;
            }
            for (let i = 0; i < state.inputData.length; i++) {

                //冒泡排序，将时间的大小排出来
                if (i !== state.inputData.length - 1) {
                    for (let j = 0; j < arr.length - 1 - i; j++) {
                        // 白话解释：如果前面的数大，放到后面(当然是从小到大的冒泡排序)
                        if (arr[j] > arr[j + 1]) {
                            let temp = arr[j];
                            arr[j] = arr[j + 1];
                            arr[j + 1] = temp;
                        }
                    }
                }

                //存入数据
                for (let j = state.inputData[i].startWeek; j <= state.inputData[i].endWeek; j++) {
                    state.sendedData.push({
                        period: state.period,
                        week: state.week,
                        weekNo: j,
                        courseTitle: state.inputData[i].courseName,
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
        },

        //获取课表数据
        getScheduleData(state, response) {
            //扔到actions
            if (response != undefined) {
                //整理发回来的数据
                for (let i = 0; i < response.length; i++) {
                    //如果是第一个就将这个数据压入栈中
                    console.log(response[i].period);
                    if (i === 0) {
                        state.responData.push({
                            courseName: response[i].courseTitle,
                            startWeek: response[i].weekNo,
                            endWeek: response[i].weekNo,
                            row: response[i].period - 1,
                            column: response[i].week - 1,
                        })
                    } else {
                        //循环responData的数据
                        for (let j = 0; j < state.responData.length; j++) {
                            //如果三个数据相等，就合并
                            if (response[i].courseTitle === state.responData[j].courseName && response[i].period - 1 === state.responData[j].row && response[i].week - 1 === state.responData[j].column) {
                                //判断大小，如果大了，替换结束周，如果小了，替换开始周
                                if (response[i].weekNo > state.responData[j].endWeek) {
                                    state.responData[j].endWeek = response[i].weekNo;
                                }
                                if (response[i].weekNo < state.responData[j].startWeek) {
                                    state.responData[j].startWeek = response[i].weekNo;
                                }
                            } else {
                                console.log("fjakfaf");
                                //如果遍历完后数据不相等，就往数组push一个新的数据
                                if (j === state.responData.length - 1) {
                                    console.log("进来函数了");
                                    state.responData.push({
                                        courseName: response[i].courseTitle,
                                        startWeek: response[i].weekNo,
                                        endWeek: response[i].weekNo,
                                        row: response[i].period - 1,
                                        column: response[i].week - 1,
                                    })
                                }
                            }
                        }
                    }
                }
                console.log(state.responData);
                //存储整理后的数据
                for (let i = 0; i < state.responData.length; i++) {
                    //创建一个数组
                    state.scheduleData[state.responData[i].row].courseData[state.responData[i].column] = [];

                    //把元素压入栈中
                    state.scheduleData[state.responData[i].row].courseData[state.responData[i].column].push({
                        courseName: state.responData[i].courseName,
                        startWeek: state.responData[i].startWeek,
                        endWeek: state.responData[i].endWeek,
                    })
                }
                console.log(state.scheduleData);
            }
        },
        //control加一
        addControl(state) {
            state.control++;
        }
    },
    state: {
        //控制发请求的次数
        control: 0,
        //输入表单的比例
        scale1: 24,
        scale2: 0,
        //表格坐标
        tableCoordinate: {
            row: 0,
            column: 0
        },
        //深度优先配合标记已经走过
        steps: [],
        //定义是否可读或者可写
        writable: false,
        //课表数据
        period: 0,
        week: 0,
        whatDay: "",
        dayTime: "",
        //课表名
        responData: [],
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
