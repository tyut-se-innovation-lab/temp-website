import request from '@/utils/request'


// 发起投票
export  function getInitiate(data){
    return request({
      url: '/selab/vote/initiate',
      method: 'post',
      data:data
    })

}
