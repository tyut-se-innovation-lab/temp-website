import request from '@/utils/request';
import History from './index';

export default class JoinDetails {

    constructor() {

    }

    /**
    * 获取投票详细信息-
    * @param {Number} id 
    * @returns 
    */
    getDetails(id) {
        return request({
            url: `/selab/vote/join/allInfo`,
            method: 'get',
            params: {
                id
            }
        })
    }

    /**
    * 传输投票的选项
    * @param {Object} data 
    * @returns 
    */
    sendVoteSelect(data) {
        return request({
            url: '/selab/vote/join',
            method: 'post',
            data: data
        })
    }

    /**
     * 修正数据
     * @param {Object} data 
     * @returns 
     */
    fixDateData(data) {
        data.creatTime = this.dateToString(data.creatTime);
        data.deadline = this.dateToString(data.deadline);
        return data;
    }


    /**
     * 生成Date对象并修正时间字符串
     * @param {String} string 
     */
    dateToString(string) {
        let history = new History();
        return history.dateToString(string);
    }

}