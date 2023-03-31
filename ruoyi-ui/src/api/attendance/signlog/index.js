import request from '@/utils/request';

class Log {
    constructor() {

    }

    /**
     * 获取日志
     * @returns axios请求
     */
    weekLog() {
        return request({
            url: '/selab/attendance/log/week',
            method: 'get',
        })
    }

    /**
     * 获取文件列表
     * @returns axios请求
     */
    getFileList() {
        return request({
            url: '/selab/attendance/log/log',
            method: 'get',
        })
    }

    /**
     * 获取指定文件
     * @param {String} fileName 文件名称
     * @returns axios请求
     */
    getFile(fileName) {
        return request({
            url: `/selab/attendance/log/${fileName}`,
            method: 'get',
            header: {
                headers: { 'Content-Type': 'application/x-download' }
            },
            responseType: 'blob'
        })
    }
}

export {
    Log
};