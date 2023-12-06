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
//上传规章制度的文件

export function upLoad(formData) {
  return request({
    url: "/rule/file/upload",
    method: "post",
    data: formData,
    headers: {
      "Content-Type": `multipart/form-data; boundary=${Math.random()
        .toString(36)
        .substr(2)}`,
    },
  });
}
//重置规章制度的文件
export const reLoad = () => {
  return request.get("/rule/file/reset");
};
//权限设置
export const authSet = () => {
  return request.get("/rule/file/markcheck");
};
