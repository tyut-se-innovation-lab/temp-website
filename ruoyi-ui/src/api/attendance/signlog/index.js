import request from '@/utils/request';

class Log {
    constructor() {

    }

    weekLog() {
        return request({
            url: '/selab/attendance/log/week',
            method: 'get',
        })
    }

    /**
     * 
     * @returns 
     */
    getFileList() {
        return request({
            url: '/selab/attendance/log/log',
            method: 'get',
        })
    }

    getFile(fileName) {
        return request({
            url: `/selab/attendance/log/${fileName}`,
            method: 'get',
        })
    }
}

export {
    Log
};