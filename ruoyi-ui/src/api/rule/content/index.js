import request from '@/utils/request'

//添加内容
export function contextContorller(data){
  return request({
    url:'/file/deliver',
    methods:"get",
    params:data,
  })
}
