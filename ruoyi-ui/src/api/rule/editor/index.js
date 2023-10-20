import request from "@/utils/request";

//增减分数
export const scoreController = (scoreChange, userId) => {
  return request.put("/rule/score", {
    scoreChange,
    userId,
    //非必须参数
    createTime: "",
    menderId: 0,
  });
};
