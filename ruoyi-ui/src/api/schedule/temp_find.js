import request from '@/utils/request'


// 获取随即课表
export function tempfind(data) {
    return request({
      url:'/selab/schedule/mine/upload/cookie',
      method: 'post',
      data: data
    })
  }
