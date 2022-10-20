import request from '@/utils/request'

// 获取随即课表
export function listleisure(data) {
    return request({
      url:'/selab/schedule/leisure/display',
      method: 'post',
      data: data
    })
  }
