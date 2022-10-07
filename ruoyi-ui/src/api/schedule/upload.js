import request from '@/utils/request'

// 上传课表
export function uploadSchedule(query) {
    return request({
        url: '/selab/schedule/add',
        method: 'post',
        data: query
    })
}