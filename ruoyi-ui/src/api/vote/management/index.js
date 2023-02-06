import request from '@/utils/request';

export default class Management {
    constructor() {

    }

    //获取权值
    getManageValue() {
        return request({
            url: '/selab/vote/weight/get',
            method: 'get',
        })
    }

    //修改权值
    modifManageValue(data) {
        return request({
            url: '/selab/vote/weight/modify',
            method: 'post',
            param: data
        })
    }
}