import request from "@/utils/request";
//查询用户当天的增减分情况
export const userController = (data) => {
  return request({
    url: "/rule/day",
    method: "get",
    data: data,
  });
};
//查询用户本月的增减分情况
export const monthUserController = (data) => {
  return request({
    url: "/rule/month",
    method: "get",
    data: data,
  });
};
