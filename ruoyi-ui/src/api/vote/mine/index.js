import request from '@/utils/request'

/**
 * 我参与的投票列表
 * @returns {*}
 */
export  function mineJoin(){
  return request({
    url: 'selab/vote/mine/joined/lists',
    method: 'get',
  })
}

/**
 * 我创建的
 * @returns {*}
 */
export  function mineCreate(){
  return request({
    url: '/selab/vote/mine/launched/lists',
    method: 'get',
  })
}

/**
 * 撤回
 * @returns {*}
 */
export  function mineDelete(){
  return request({
    url: '/selab/vote/mine/delete',
    method: 'delete',
  })
}

/**
 * 提前结束
 * @returns {*}
 */
export  function mineEarlyTermination(){
  return request({
    url: '/selab/vote/mine/finish',
    method: 'put',
  })
}
