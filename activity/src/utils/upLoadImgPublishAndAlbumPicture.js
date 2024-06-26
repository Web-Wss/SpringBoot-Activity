import axios from "axios";
import { Toast } from "vant";
import store from "../store";

// 失物招领图片上传

function upLoadImgPublishAndAlbumPicture(file, album_id, staff_id) {
  // file为 你读取成功的回调文件信息
  let params = new FormData();
  // if (file instanceof Array && file.length) {
  //   console.log(file);
  //   file.forEach((item, i) => {
  //     params.append("uploadFile", item.file);
  //   });
  // }
  params.append("uploadFile", file);
  params.append("album_id", album_id);
  params.append("staff_id", staff_id);
  let config = {
    headers: {
      // 添加请求头
      "Content-Type": "multipart/form-data",
      Authorization: store.getters.getToken,
    },
  };
  return new Promise((resolve, reject) => {
    axios
      .post("/api/albumadmin/uploadalbumpicture", params, config)
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

export default upLoadImgPublishAndAlbumPicture;
