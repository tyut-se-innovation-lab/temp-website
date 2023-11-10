import request from '@/utils/request'

/**
 * 进行投票
 * @param {{
 * 未知: any
 * }} data
 * @returns {AxiosPromise}
 */
export const vote_commit = (data) => {
  return request({
    url: '/vote/commit/dovote',
    method: 'POST',
    data
  })
}
