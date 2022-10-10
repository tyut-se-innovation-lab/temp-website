import request from '@/utils/request'

// 获取随即课表
export function listleisure(params) {
    return request({
      url:'/selab/schedule/leisure/display',
      method: 'get',
      params: params
    })
  }