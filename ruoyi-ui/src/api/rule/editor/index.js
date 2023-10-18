import request from "@/utils/request";

//增减分数
export const scoreController = (userId, scoreChange) => {
  return request.put("/rule/score", {
    userId: 1,
    scoreChange: 1,
  });
};
