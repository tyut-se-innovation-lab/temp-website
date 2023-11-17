import request from '@/utils/request'

// 获取路由
export const getRouters = (query) => {
  return request({
    url: '/getRouters',
    method: 'get',
    params: query
  })
}
