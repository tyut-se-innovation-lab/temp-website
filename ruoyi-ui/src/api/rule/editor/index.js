import request from '@/utils/request'

//增减分数
export function scoreController(data){
  return request({
    url:'/rule/score',
    method:'put',
    data:data
  })
}
