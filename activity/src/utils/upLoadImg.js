//upLoaderImg.js
import axios from "axios"; //引入axios
import { Toast } from "vant";
import store from "../store";

function upLoaderImg(file, name, describe) {
  //file为 你读取成功的回调文件信息
  //new 一个FormData格式的参数
  let params = new FormData();
  params.append("file", file);
  params.append("user_id", 1);
  params.append("name", name);
  params.append("describe", describe);
  let config = {
    headers: {
      //添加请求头
      "Content-Type": "multipart/form-data",
    },
  };
  return new Promise((resolve, reject) => {
    //把 uploadUrl 换成自己的 上传路径
    axios
      .post("/api/my/lostarticle", params, config)
      .then((res) => {
        console.log(res);
        if (res.status == 200) {
          //如果为真 resolve出去
          resolve(res.data);
        } else {
          //否则 Toast 提示
          // Toast.fail(res.data && res.data.msg);
          reject(res.data.data);
        }
      })
      .catch((err) => {
        Toast.fail("系统异常");
        reject(err);
      });
  });
}
export default upLoaderImg;
