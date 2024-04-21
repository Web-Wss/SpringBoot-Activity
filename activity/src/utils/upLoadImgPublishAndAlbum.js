import axios from "axios";
import { Toast } from "vant";
import store from "../store";

// 失物招领图片上传

function upLoadImgPublishAndAlbum(
  file,
  activity_id,
  staff_id,
  activity_name,
  time_id,
  time_name
) {
  // file为 你读取成功的回调文件信息
  let params = new FormData();
  if (file instanceof Array && file.length) {
    console.log(file);
    file.forEach((item, i) => {
      params.append("uploadFile", item.file);
    });
  }
  params.append("activity_id", activity_id);
  params.append("staff_id", staff_id);
  params.append("activity_name", activity_name);
  params.append("time_id", time_id);
  params.append("time_name", time_name);
  let config = {
    headers: {
      // 添加请求头
      "Content-Type": "multipart/form-data",
      Authorization: store.getters.getToken,
    },
  };
  return new Promise((resolve, reject) => {
    axios
      .post("/api/albumadmin/createalbum", params, config)
      .then((res) => {
        console.log(res);
        if (res.status == 200) {
          resolve(res.data);
        } else {
          reject(res.data.data);
        }
      })
      .catch((err) => {
        Toast.file("系统异常");
        reject(err);
      });
  });
}

export default upLoadImgPublishAndAlbum;
