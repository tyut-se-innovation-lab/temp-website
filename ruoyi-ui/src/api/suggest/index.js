import request from '@/utils/request'
//提交建议
export function suggestion(data) {
    return request({
      url: '/suggestion/add',
      method: 'post',
      data: {
        suggestionContent : data
      }
    })
  }
export function suggestionVerity(data) {
    return request({
      url: '/suggestion/verify',
      method: 'post',
      data: {
        password : data
      }
    })
  }
export function suggestionList(pageSize,pageNum) {
    return request({
      url: '/suggestion/list',
      method: 'post',
      data: {
        pageSize : pageSize,
        pageNum:pageNum
      }
    })
  }