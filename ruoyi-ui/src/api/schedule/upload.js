import request from '@/utils/request'

// 上传课表
export function uploadSchedule(query) {
    return request({
        url: '/selab/schedule/mine/upload',
        method: 'post',
        data: query
    })
}