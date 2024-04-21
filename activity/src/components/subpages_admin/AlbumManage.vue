<template>
  <div id="albummanage">
    <el-button type="primary" @click="addAlbum">创建相册</el-button>
    <!-- 相册列表 -->
    <div style="margin-top: 10px"></div>
    <el-row :gutter="20">
      <el-col :span="6" v-for="(o, index) in album_list" :key="o.albumId">
        <el-card :body-style="{ padding: '0px' }">
          <img :src="o.albumHeaderPath" class="image" />
          <div style="padding: 14px">
            <span style="color: red">活动ID:</span>{{ o.albumId }}
            <div class="bottom clearfix">
              <div>
                <span style="color: red">活动名称:</span>{{ o.activityName }}
              </div>
              <br />
              <div>
                <span style="color: red">活动时间:</span
                >{{ o.time.timeContent }}
              </div>
              <br />
              <el-button type="text" class="button" @click="goPicture(o)"
                >进入相册</el-button
              ><br /><br />
              <el-button type="text" class="button" @click="DeleteAlbumById(o)"
                >删除相册</el-button
              >
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <!-- 创建相册 -->
    <el-dialog title="创建相册" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item
          label="时间ID"
          prop="region"
          :label-width="formLabelWidth"
        >
          <el-select
            v-model="form.time_id"
            placeholder="请选择活动时间ID"
            @change="searchInfoByTimeId()"
          >
            <el-option
              v-for="i in timeIdList"
              :label="i"
              :value="i"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="相册封面图" :label-width="formLabelWidth">
          <van-uploader v-model="fileList" multiple :max-count="1" />
        </el-form-item>
        <el-form-item label="活动名称" :label-width="formLabelWidth">
          <el-input
            v-model="form.activity_name"
            autocomplete="off"
            disabled
          ></el-input>
        </el-form-item>
        <el-form-item label="活动时间" :label-width="formLabelWidth">
          <el-input
            v-model="form.time_name"
            autocomplete="off"
            disabled
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="createAlbum">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import upLoadImgPublishAndAlbum from "../../utils/upLoadImgPublishAndAlbum";
export default {
  name: "AlbumManage",
  data() {
    return {
      currentDate: new Date(),
      // 相册列表
      album_list: [],
      // 创建相册
      dialogFormVisible: false,
      formLabelWidth: "120px",
      timeIdList: [],
      form: {
        time_id: "",
        time_name: "",
        activity_id: "",
        activity_name: "",
        header_img: "",
        staff_id: "",
      },
      fileList: [],
    };
  },
  mounted() {
    // 获取相册列表
    this.getAlbumList();
  },
  methods: {
    // 删除相册
    async DeleteAlbumById(o) {
      console.log(o);
      await this.$http
        .post(
          `/albumadmin/deletealbumbyid?album_id=${o.albumId}&album_header_img=${o.albumHeaderPath}`
        )
        .then((res) => {
          console.log(res);
          this.getAlbumList();
        });
    },
    // 进入相册
    goPicture(o) {
      console.log(o);
      this.$router.push({
        path: "/homeadmin/picturemanage",
        query: {
          albumId: o.albumId,
          header: o.albumHeaderPath,
          name: o.activityName,
          time: o.time.timeContent,
        },
      });
    },
    // 获取相册列表
    async getAlbumList() {
      await this.$http.get(`/albumadmin/getalbumlist`).then((res) => {
        console.log(res);
        this.album_list = res.data.data;
      });
    },
    // 创建相册
    addAlbum() {
      this.getTImeId();
      this.dialogFormVisible = true;
    },

    // 获取未被创建的时间id
    async getTImeId() {
      console.log(1);
      await this.$http.get(`/albumadmin/gettimeidlist`).then((res) => {
        console.log(res);
        this.timeIdList = res.data.data;
      });
    },

    // 根据时间id获取信息并自动写入
    async searchInfoByTimeId() {
      console.log(this.form.time_id);
      await this.$http
        .post(`/albumadmin/getinfobytimeid?time_id=${this.form.time_id}`)
        .then((res) => {
          console.log(res);
          this.form.activity_id = res.data.data.activity.activityId;
          this.form.activity_name = res.data.data.activity.activityName;
          this.form.time_name = res.data.data.timeContent;
        });
    },

    // 提交
    async createAlbum() {
      console.log("图片", this.fileList);
      await upLoadImgPublishAndAlbum(
        this.fileList,
        this.form.activity_id,
        1,
        this.form.activity_name,
        this.form.time_id,
        this.form.time_name
      );
    },
  },
};
</script>

<style scoped>
.time {
  font-size: 13px;
  color: #999;
}

.bottom {
  margin-top: 13px;
  /* line-height: 12px; */
}

.button {
  padding: 0;
  float: right;
}

.image {
  width: 100%;
  display: block;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both;
}
</style>
