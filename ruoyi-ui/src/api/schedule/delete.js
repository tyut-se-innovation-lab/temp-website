import request from '@/utils/request'

// 获取随即课表
export function deleteSchedule(data) {
    return request({
        url: '/selab/schedule/mine/delete',
        method: 'delete',
        data: data
    })
}