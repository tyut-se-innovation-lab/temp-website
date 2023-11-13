import request from '@/utils/request'

/**
 * @typedef {{page: number, per: number}} PaginatedData
 */

/**
 * 查看冻结投票（分页查询）
 * @param {PaginatedData} data
 * @returns {AxiosPromise}
 */
export const vote_viewFreeze = (data) => {
  return request({
    url: '/vote/query/untreated',
    method: 'POST',
    data
  })
}

/**
 * 查询我举报的投票（分页查询）
 * @param {PaginatedData} data
 * @returns {AxiosPromise}
 */
export const vote_myReport = (data) => {
  return request({
    url: '/vote/query/myreport',
    method: 'POST',
    data
  })
}

/**
 * 查询我发起的投票（分页查询）
 * @param {PaginatedData} data
 * @returns {AxiosPromise}
 */
export const vote_myLaunch = (data) => {
  return request({
    url: '/vote/query/mylaunch',
    method: 'POST',
    data
  })
}

/**
 * 查询我参与的投票（分页查询）
 * @param {PaginatedData} data
 * @returns {AxiosPromise}
 */
export const vote_myCommit = (data) => {
  return request({
    url: '/vote/query/mycommit',
    method: 'POST',
    data
  })
}

/**
 * 查询我参与的投票（分页查询）
 * @param {{voteId: number}} voteId 投票id
 * @returns {AxiosPromise}
 */
export const vote_queryById = ({ voteId }) => {
  return request({
    url: `/vote/query/${ voteId }`,
    method: 'GET'
  })
}
