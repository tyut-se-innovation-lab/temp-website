import request from '@/utils/request';

class Sign {
    constructor() {

    }

  /**
   * 签到
   * @returns axios请求
   */
  userWeekTime() {
    return request({
      url: '/selab/attendance/log/userWeekTime',
      method: 'get',
    })
  }

    /**
     * 签到
     * @returns axios请求
     */
    signIn() {
        return request({
            url: '/selab/attendance/sign',
            method: 'post',
        })
    }

    /**
     * 是否能够签退
     * @returns axios请求
     */
    couldSignOut() {
        return request({
            url: '/selab/attendance/could',
            method: 'get',
        })
    }

    /**
     * 签退
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
