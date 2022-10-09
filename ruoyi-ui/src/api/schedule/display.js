import request from '@/utils/request'

// 拉下来课表
export function getSchedule() {
    return request({
        url: '/selab/schedule/mine/display',
        method: 'get',
    })
}