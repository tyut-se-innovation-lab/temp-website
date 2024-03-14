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
        suggestionKey : data
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

//重置密码接口
export function suggestionreVerity(data) {
  return request({
    url: '/suggestion/revise',
    method: 'post',
    data: {
      suggestionKey : data
    }
  })
}
//获取详细信息
export function suggestionMessage(data) {
  return request({
    url: '/suggestion/list/user',
    method: 'post',
    data: {
      suggestionKey : data
    }
  })
}
