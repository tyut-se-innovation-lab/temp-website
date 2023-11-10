import request from '@/utils/request'

/**
 * 查看举报信息
 * @param {{voteId: number}} voteId 投票id
 * @returns {AxiosPromise}
 */
export const vote_viewReports = ({ voteId }) => {
  return request({
    url: `/vote/report/view/${ voteId }`,
    method: 'POST'
  })
}

/**
 * 提交举报信息
 * @param {{voteId: number, description: string}} data {voteId: 投票ID, description: 原因简述}
 * @returns {AxiosPromise}
 */
export const vote_submitReport = (data) => {
  return request({
    url: `/vote/report/submit`,
    method: 'POST',
    data
  })
}
