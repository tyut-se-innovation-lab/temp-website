import request from "@/utils/request";

//获取所有用户的操作日志
export const selectAllLog = (pageNum, pageSize) => {
  return request.get(
    "/rule/log/selectAllLog?pageNum=" + pageNum + "&pageSize=" + pageSize
  );
};

//获取规章制度的文件
export const downLoad = () => {
  return request.get("/rule/file/download");
};
