import request from '@/utils/request';

class Sign {
    constructor() {

    }

    /**
     * 登录
     * @returns axios请求
     */
    signIn() {
        return request({
            url: '/selab/attendance/sign',
            method: 'post',
        })
    }

    /**
     * 是否能够登出
     * @returns axios请求
     */
    couldSignOut() {
        return request({
            url: '/selab/attendance/could',
            method: 'get',
        })
    }

    /**
     * 登出
     * @returns axios请求
     */
    signOut() {
        return request({
            url: '/selab/attendance/sign/out',
            method: 'post',
        })
    }
}

export {
    Sign
};