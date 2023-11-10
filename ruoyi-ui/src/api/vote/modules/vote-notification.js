import request from '@/utils/request'

/**
 * 查询投票通知
 * @returns {AxiosPromise}
 */
export const vote_queryNotify = () => {
  return request({
    url: '/vote/inform/query',
    method: 'GET'
  })
}

/**
 * 修改投票通知为已读
 * @returns {AxiosPromise}
 */
export const vote_readNotify = () => {
  return request({
    url: '/vote/inform/read',
    method: 'GET'
  })
}

/**
 * 查询是否有消息弹窗
 * @returns {AxiosPromise}
 */
export const vote_messagePopUps = () => {
  return request({
    url: '/vote/inform/reminder',
    method: 'GET'
  })
}

