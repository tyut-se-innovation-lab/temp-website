import request from "@/utils/request";

//添加内容
export const contextContorller = () => {
  return request.get("/file/deliver", {
    fille: "",
  });
};
