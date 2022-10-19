import request from '@/utils/request'

// 获取随即课表
export function listAttange(data) {
  return request({
    url: '/selab/schedule/arrange',
    method: 'post',
    data: data
  })
}
