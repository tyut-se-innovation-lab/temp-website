import request from '@/utils/request';

class Log {
    constructor() {

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

    /**
     * 获取日志
     * @param {Array} timeGap 起止时间
     * @param {Number} pageCount 每页的最大记录数
     * @param {Number} currentPage 当前页数 
     */
    weekLog({ attStartTime, attEndTime, currentPage, pageCount }) {
        return request({
            url: `/selab/attendance/log/week`,
            method: 'get',
            params: {
                attStartTime,
                attEndTime,
                pageNum: currentPage,
                pageSize: pageCount,
            }
        })
    }
}

export {
    Log
};