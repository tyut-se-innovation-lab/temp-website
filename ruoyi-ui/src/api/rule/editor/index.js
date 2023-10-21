import request from "@/utils/request";

//增减分数
export const scoreController = (reason, scoreChange, userId) => {
  return request.put("/rule/score", {
    //必须参数

    reason,
    scoreChange,
    userId,
    //非必须参数
    createTime: "",
    menderId: 0,
  });
};

//获取用户操作日志
export const ruleController = (userId) => {
  return request({
    url: "/rule/getOperationInfo/" + userId,
    method: "get",
  });
};

//删除用户操作日志
export const logController = (logId) => {
  return request({
    url: "/rule/log/deleteLog/" + logId,
    method: "delete",
  });
};
