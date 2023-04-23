import request from '@/utils/request'

export default class History {
    constructor() {

    }

    /**
    * 获取投票列表
    * @returns 
    */
    getBriefList() {
        return request({
            url: '/selab/vote/join/list',
            method: 'get',
        })
    }

    /**
    * 获取投票详细信息-
    * @param {Number} id 
    * @returns 
    */
    getDetails(id) {
        return request({
            url: '/selab/vote/join/allInfo',
            method: 'get',
            params: id
        })
    }
}