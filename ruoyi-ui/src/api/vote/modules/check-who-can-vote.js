import request from '@/utils/request'

/**
 * 查看所有能投票的人员
 * @param {{voteId: number}} voteId 投票id
 * @returns {AxiosPromise}
 */
export const vote_whoCanVote = ({ voteId }) => {
  return request({
    url: `/vote/showPeople/${ voteId }`,
    method: 'GET'
  })
}
