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

//查询用户当天的增减分原因
export const dayUserControll = (data) => {
  return request({
    url: "/rule/day/operations",
    method: "get",
    data: data,
  });
};

//查询用户本月的增减分原因
export const monthUserControll = (data) => {
  return request({
    url: "/rule/month/operations",
    method: "get",
    data: data,
  });
};

//条件查询某天的日志
export const userdayLog = (pageNum, pageSize, startTime) => {
  return request.get(
    "/rule/dayLog?pageNum=" +
      pageNum +
      "&pageSize=" +
      pageSize +
      "&startTime=" +
      startTime
  );
};
