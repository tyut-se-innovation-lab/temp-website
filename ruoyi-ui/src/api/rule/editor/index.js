import request from '@/utils/request'
import {Message} from 'element-ui'
//增减分数
export function scoreController(params){
  return request.put('http://localhost/dev-api/rule/score',{
    userId:1,
    scoreChange:1
  }).then((result)=>{
    Message.success('添加成功')
  }).catch(()=>{
    Message.error('请求失败')
  })
}

