import request from '@/utils/request'

/**
 * 撤回投票
 * @returns {AxiosPromise}
 */
export const vote_withdraw = () => {
  return request({
    url: '/vote/deal/withdraw',
    method: 'POST'
  })
}

// jsDoc: VotePostData
//#region
/**
 * @typedef {{
 *   optionType: 's'|'c',
 *   content: string
 * }} VoteOption
 */
/**
 * @typedef {{
 *   roleId: number,
 *   weight: number
 * }} VoteWeight
 */
/** @typedef {{
 *   type: 1|2|3,
 *   title: string
 *   content: string,
 *   deadTime: string,
 *   isRealTime: '0'|'1',
 *   voteOptionVoList: Array<VoteOption>,
 *   voteWeights: Array<VoteWeight>
 * }} VotePostData
 */
//#endregion

/**
 * 发布投票
 * 发布投票选择权重和角色人群应先发送请求（GET http://192.168.1.134:8080/system/role/list&pageNum=1&pageSize=10）获取实时的角色信息
 * @param {VotePostData} data
 * @returns {AxiosPromise}
 */
export const vote_post = (data) => {
  return request({
    url: '/vote/deal/launch',
    method: 'POST',
    data
  })
}

/**
 * 处理冻结投票
 * @param {{voteId: number}} voteId 投票id
 * @param {{voteId: number, handel: 0|1}} params handel: 0为关闭,1为恢复正常
 * @returns {AxiosPromise}
 */
export const vote_handlingFrozen = ({ voteId }, params) => {
  return request({
    url: `/vote/deal/handle/${ voteId }`,
    method: 'GET',
    params
  })
}

/**
 * 投票结束后删除投票
 * @param {{voteId: number}} voteId 投票id
 * @returns {AxiosPromise}
 */
export const vote_delete = ({ voteId }) => {
  return request({
    url: `/vote/deal/delete/${ voteId }`,
    method: 'GET'
  })
}
