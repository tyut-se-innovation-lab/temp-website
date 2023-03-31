import request from '@/utils/request';

class Sign {
    constructor() {

    }

    signIn() {
        return request({
            url: '/selab/attendance/sign',
            method: 'post',
        })
    }

    couldSignOut() {
        return request({
            url: '/selab/attendance/could',
            method: 'get',
        })
    }

    signOut() {
        return request({
            url: '/selab/attendance/sign/out',
            method: 'post',
            header: {
                headers: { 'Content-Type': 'application/x-download' }
            },
            responseType: 'blob'
        })
    }
}

export {
    Sign
};