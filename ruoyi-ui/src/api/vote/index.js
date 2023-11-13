import { vote_whoCanVote } from './modules/check-who-can-vote'
import { vote_viewDetails } from './modules/voting-details-display'
import { vote_viewReports, vote_submitReport } from './modules/voting-reporting-information'
import { vote_viewFreeze, vote_myReport, vote_myLaunch, vote_myCommit, vote_queryById } from './modules/query-votes'
import { vote_withdraw, vote_post, vote_handlingFrozen, vote_delete } from './modules/process-votes'
import { vote_commit } from './modules/commit-vote'
import { vote_queryNotify, vote_readNotify, vote_messagePopUps } from './modules/vote-notification'

// api 总和
//#region
/**
 * @typedef {Promise} AxiosPromise
 */
/**
 * @typedef {{myReport: ((function(PaginatedData): AxiosPromise)|*), messagePopUps: ((function(): AxiosPromise)|*), commit: ((function({未知: *}): AxiosPromise)|*), whoCanVote: ((function({voteId: {voteId: number}}): AxiosPromise)|*), viewFreeze: ((function(PaginatedData): AxiosPromise)|*), handlingFrozen: ((function({voteId: {voteId: number}}, {voteId: number, handel: (0|1)}): AxiosPromise)|*), delete: ((function({voteId: {voteId: number}}): AxiosPromise)|*), myLaunch: ((function(PaginatedData): AxiosPromise)|*), post: ((function(VotePostData): AxiosPromise)|*), myCommit: ((function(PaginatedData): AxiosPromise)|*), viewReports: ((function({voteId: {voteId: number}}): AxiosPromise)|*), queryNotify: ((function(): AxiosPromise)|*), viewDetails: ((function({voteId: {voteId: number}}): AxiosPromise)|*), readNotify: ((function(): AxiosPromise)|*), submitReport: ((function({voteId: number, description: string}): AxiosPromise)|*), queryById: ((function({voteId: {voteId: number}}): AxiosPromise)|*), withdraw: ((function(): AxiosPromise)|*)}} VoteAPI
 */
/**
 * @typedef {{myReport: ((function(PaginatedData): AxiosPromise)|*), messagePopUps: ((function(): AxiosPromise)|*), myCommit: ((function(PaginatedData): AxiosPromise)|*), viewReports: ((function({voteId: {voteId: number}}): AxiosPromise)|*), queryNotify: ((function(): AxiosPromise)|*), viewDetails: ((function({voteId: {voteId: number}}): AxiosPromise)|*), whoCanVote: ((function({voteId: {voteId: number}}): AxiosPromise)|*), viewFreeze: ((function(PaginatedData): AxiosPromise)|*), myLaunch: ((function(PaginatedData): AxiosPromise)|*), queryById: ((function({voteId: {voteId: number}}): AxiosPromise)|*)}} VoteInquireAPI
 */
/**
 * @typedef {{post: ((function(VotePostData): AxiosPromise)|*), commit: ((function({未知: *}): AxiosPromise)|*), handlingFrozen: ((function({voteId: {voteId: number}}, {voteId: number, handel: (0|1)}): AxiosPromise)|*), delete: ((function({voteId: {voteId: number}}): AxiosPromise)|*), readNotify: ((function(): AxiosPromise)|*), submitReport: ((function({voteId: number, description: string}): AxiosPromise)|*), withdraw: ((function(): AxiosPromise)|*)}} VoteOperateAPI
 */
/**
 * @typedef {{messagePopUps: ((function(): AxiosPromise)|*), queryNotify: ((function(): AxiosPromise)|*), readNotify: ((function(): AxiosPromise)|*)}} VoteNotifyAPI
 */
//#endregion

/**
 * 全部
 * @type {VoteAPI}
 */
const voteAllAPI = {
  whoCanVote: vote_whoCanVote,
  viewDetails: vote_viewDetails,
  viewReports: vote_viewReports,
  submitReport: vote_submitReport,
  viewFreeze: vote_viewFreeze,
  myReport: vote_myReport,
  myLaunch: vote_myLaunch,
  myCommit: vote_myCommit,
  queryById: vote_queryById,
  withdraw: vote_withdraw,
  post: vote_post,
  handlingFrozen: vote_handlingFrozen,
  delete: vote_delete,
  commit: vote_commit,
  queryNotify: vote_queryNotify,
  readNotify: vote_readNotify,
  messagePopUps: vote_messagePopUps
}

/**
 * 查询
 * @type {VoteInquireAPI}
 */
const voteInquireAPI = {
  whoCanVote: vote_whoCanVote,
  viewDetails: vote_viewDetails,
  viewReports: vote_viewReports,
  viewFreeze: vote_viewFreeze,
  myReport: vote_myReport,
  myLaunch: vote_myLaunch,
  myCommit: vote_myCommit,
  queryById: vote_queryById,
  queryNotify: vote_queryNotify,
  messagePopUps: vote_messagePopUps
}

/**
 * 操作
 * @type {VoteOperateAPI}
 */
const voteOperateAPI = {
  submitReport: vote_submitReport,
  withdraw: vote_withdraw,
  post: vote_post,
  handlingFrozen: vote_handlingFrozen,
  delete: vote_delete,
  commit: vote_commit,
  readNotify: vote_readNotify
}

/**
 * 通知
 * @type {VoteNotifyAPI}
 */
const voteNotifyAPI = {
  queryNotify: vote_queryNotify,
  readNotify: vote_readNotify,
  messagePopUps: vote_messagePopUps
}

/**
 * @description 投票接口
 * @author XunJi - 常
 * @version 1.0.0
 */
class VoteAPIInterface {
  constructor () {
    throw Error('VoteAPIInterface 没有实例方法，不需要创建实例')
  }

  /**
   * 全部投票相关 api
   * @returns {VoteAPI}
   * @constructor
   */
  static get All () {
    return voteAllAPI
  }

  /**
   * 投票查询相关 api
   * @returns {VoteInquireAPI}
   * @constructor
   */
  static get Inquire () {
    return voteInquireAPI
  }

  /**
   * 投票操作相关 api
   * @returns {VoteOperateAPI}
   * @constructor
   */
  static get Operate () {
    return voteOperateAPI
  }

  /**
   * 投票通知相关 api
   * @returns {VoteNotifyAPI}
   * @constructor
   */
  static get Notify () {
    return voteNotifyAPI
  }
}

const All = VoteAPIInterface.All
const Inquire = VoteAPIInterface.Inquire
const Operate = VoteAPIInterface.Operate
const Notify = VoteAPIInterface.Notify

export { All, Inquire, Operate, Notify }

export default VoteAPIInterface
