import request from '@/utils/request'

// 获取随即课表
export default class initiate{
  constructor() {

  }
  // 发起投票
  getInitiate(data){
    return request({
      url: '/selab/vote/initiate',
      method: 'post',
      data:data,
    })
  }
}
