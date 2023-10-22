import request from "@/utils/request";

//添加内容
export const contextContorller = () => {
  return request.get("/file/deliver", {
    fille: "",
  });
};

//获取所有用户的操作日志
export const selectAllLog = (query) => {
  return request({
    url: "/rule/log/selectAllLog",
    method: "get",
    params: query,
  });
};
