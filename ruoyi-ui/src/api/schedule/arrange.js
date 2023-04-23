import request from '@/utils/request'

// 获取随机课表
export function listAttange(data) {
  return request({
    url: '/selab/schedule/arrange',
    method: 'post',
    data: data
  })
}
