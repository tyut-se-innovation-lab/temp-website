import request from '@/utils/request'


/**
 * 查看投票细节 【目前暂定发起人和管理员都可以实时看到投票信息，也就是都发这个请求↔️
 * @param {{voteId: number}} voteId 投票id
 * @returns {AxiosPromise}
 */
export const vote_viewDetails = ({ voteId }) => {
  return request({
    url: `/vote/result/showDetails/${ voteId }`,
    method: 'GET'
  })
}
