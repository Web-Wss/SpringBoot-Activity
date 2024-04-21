<template>
  <div id="activitymanage">
    <div class="header">
      <el-select
        v-model="classification_id"
        placeholder="活动分类"
        @change="getActivityListByClassification()"
      >
        <el-option
          v-for="item in classification_list"
          :key="item.classificationId"
          :label="item.classificationName"
          :value="item.classificationId"
        >
        </el-option>
      </el-select>
      &nbsp;

      <!-- 添加按钮 -->
      &nbsp;
      <el-button type="primary" @click="addActivity">添加活动</el-button>
    </div>
    <!-- 内容 -->
    <div class="content">
      <el-table :data="tableData" stripe border style="width: 100%">
        <el-table-column
          prop="activityId"
          label="ID"
          width="180"
          align="center"
        >
        </el-table-column>
        <el-table-column
          prop="classification.classificationName"
          label="分类"
          width="160"
          align="center"
        >
        </el-table-column>
        <el-table-column prop="photo" label="照片" width="80" align="center">
          <template slot-scope="scope">
            <img :src="scope.row.headerList[0].headerPath" alt="" />
          </template>
        </el-table-column>
        <el-table-column
          prop="activityName"
          label="活动名称"
          min-width="150"
          align="center"
        >
        </el-table-column>
        <el-table-column
          prop="timeId"
          label="活动时间"
          width="180"
          align="center"
        >
        </el-table-column>
        <el-table-column
          prop="activityStaffId"
          label="活动职工"
          width="180"
          align="center"
        >
        </el-table-column>
        <el-table-column
          prop="activityDescribe"
          label="活动描述"
          width="180"
          align="center"
        >
        </el-table-column>
        <el-table-column p label="活动状态" width="180" align="center">
          <template slot-scope="scope">
            <el-switch
              v-model="scope.row.activityStatus"
              active-color="#13ce66"
              inactive-color="#ff4949"
              @change="editActivityStatus(scope.row)"
            >
            </el-switch>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <el-button
              type="primary"
              @click="editActivity(scope.row)"
              icon="el-icon-edit"
              circle
            ></el-button>
            <el-button
              type="danger"
              @click="deleteActivity(scope.row)"
              icon="el-icon-delete"
              circle
            ></el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <!-- 分页 -->
    <!-- <div class="fy">
      <el-pagination background layout="prev, pager, next" :total="1000">
      </el-pagination>
    </div> -->

    <!-- 添加活动 -->
    <el-dialog title="添加活动" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="活动名称" :label-width="formLabelWidth">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <!-- 活动头图 -->
        <el-form-item label="活动头图" :label-width="formLabelWidth">
          <van-uploader v-model="form.fileListHeader" multiple :max-count="2" />
        </el-form-item>
        <!-- 活动分类 -->
        <el-form-item label="活动分类" :label-width="formLabelWidth">
          <el-select v-model="form.classification" placeholder="请选择">
            <el-option
              v-for="item in classification_list"
              :key="item.classificationId"
              :label="item.classificationName"
              :value="item.classificationId"
            >
            </el-option>
          </el-select>
        </el-form-item>

        <!-- 活动时间 -->
        <el-form-item label="活动时间" :label-width="formLabelWidth">
          <el-select v-model="form.time" multiple placeholder="请选择">
            <el-option
              v-for="item in time_list"
              :key="item.timeId"
              :label="item.timeContent"
              :value="item.timeId"
            >
            </el-option>
          </el-select>
        </el-form-item>

        <!-- 活动职工 -->
        <el-form-item label="活动职工" :label-width="formLabelWidth">
          <el-select v-model="form.staff" multiple placeholder="请选择">
            <el-option
              v-for="item in staff_list"
              :key="item.staffId"
              :label="item.staffName"
              :value="item.staffId"
            >
            </el-option>
          </el-select>
        </el-form-item>

        <!-- 活动价格 -->
        <el-form-item label="活动价格" :label-width="formLabelWidth">
          <el-input v-model="form.price" placeholder="活动价格"></el-input>
        </el-form-item>

        <!-- 活动描述 -->
        <el-form-item label="活动描述" :label-width="formLabelWidth">
          <el-input
            type="textarea"
            placeholder="请输入内容"
            v-model="form.textarea"
            maxlength="30"
            show-word-limit
          >
          </el-input>
        </el-form-item>

        <!-- 活动图片 -->
        <el-form-item label="活动图片" :label-width="formLabelWidth">
          <van-uploader v-model="form.fileListImg" multiple :max-count="2" />
        </el-form-item>

        <!-- 协议id -->
        <el-form-item label="协议选择" :label-width="formLabelWidth">
          <el-select v-model="form.xy" placeholder="请选择">
            <el-option
              v-for="item in xyList"
              :key="item.agreementId"
              :label="item.agreementName"
              :value="item.agreementId"
            >
            </el-option>
          </el-select>
        </el-form-item>

        <!-- 职工id（发布者id） -->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="fbBtn()">确 定</el-button>
      </div>
    </el-dialog>

    <!-- 修改活动 -->
    <!-- 添加活动 -->
    <el-dialog title="修改活动" :visible.sync="dialogFormVisible1">
      <el-form :model="form1">
        <el-form-item label="活动名称" :label-width="formLabelWidth">
          <el-input v-model="form1.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="活动描述" :label-width="formLabelWidth">
          <el-input v-model="form1.describe" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="editActivityInfoBtn()"
          >确 定</el-button
        >
      </div>
    </el-dialog>
  </div>
</template>

<script>
import store from "../../store/index";
export default {
  name: "ActivityManage",
  data() {
    return {
      formLabelWidth: "120px",
      dialogFormVisible: false,
      // 活动状态
      value: false,
      // 活动列表
      tableData: [
        // {
        //   id: 6000001,
        //   classification: "一日营",
        //   header_img: "https://",
        //   name: "天狼2023年3月11号一日活动",
        //   date: "2023/3/11",
        //   staff_name: "白鲨",
        //   describe: "我是活动描述",
        //   states: 1,
        // },
        // {
        //   id: 6000002,
        //   classification: "国庆营",
        //   header_img: "https://",
        //   name: "天狼2023年10月国庆活动",
        //   date: "[2023/10/1~2023/10/6]",
        //   staff_name: "白鲨",
        //   describe: "我是活动描述",
        //   states: 1,
        // },
      ],
      // 职工列表
      options: [
        {
          value: "选项1",
          label: "黄金糕",
        },
        {
          value: "选项2",
          label: "双皮奶",
        },
        {
          value: "选项3",
          label: "蚵仔煎",
        },
        {
          value: "选项4",
          label: "龙须面",
        },
        {
          value: "选项5",
          label: "北京烤鸭",
        },
      ],
      // 活动分类列表
      classification_list: [
        {
          value: 1,
          label: "一日营",
        },
        {
          value: 2,
          label: "国庆营",
        },
        {
          value: 3,
          label: "冬令营",
        },
        {
          value: 4,
          label: "夏令营",
        },
        {
          value: 5,
          label: "节假营",
        },
      ],
      // 活动时间列表
      time_list: [],
      // 职工列表
      staff_list: [],
      // 分类id
      classification_id: "",
      value2: "",
      xyList: [],
      // 添加活动
      form: {
        // 活动名称
        name: "",
        // 活动分类
        classification: "",
        time: "",
        // 活动职工
        staff: [],
        // 活动价格
        price: "",
        // 活动描述
        textarea: "",
        // 协议id
        xy: "",
        fileListHeader: [],
        fileListImg: [],
      },

      // 修改活动
      dialogFormVisible1: false,
      form1: {
        id: "",
        name: "",
        describe: "",
      },
    };
  },
  mounted() {
    // 获取活动分类列表
    this.getClassification();
    // 获取活动列表
    this.getActivityList();
    // 获取活动时间列表
    this.getTimeList();
    // 获取职工列表
    this.getStaffList();
    // 获取协议列表
    this.getXyList();
  },
  methods: {
    // 删除活动
    async deleteActivity(i) {
      console.log(i);
      await this.$http
        .post(
          `/activityadmin/deleteactivitybyactivityid?activity_id=${i.activityId}`
        )
        .then((res) => {
          console.log(res);
          this.getActivityList();
        });
    },
    // 修改活动信息
    editActivity(i) {
      this.form1.id = i.activityId;
      this.form1.name = i.activityName;
      this.form1.describe = i.activityDescribe;
      this.dialogFormVisible1 = true;
      // console.log(i);
    },
    async editActivityInfoBtn() {
      console.log("发送修改请求");
      await this.$http
        .post(
          `/activityadmin/editactivityinfobyid?activity_id=${this.form1.id}&activity_name=${this.form1.name}&activity_describe=${this.form1.describe}`
        )
        .then((res) => {
          console.log(res);
          this.getActivityList();
          this.dialogFormVisible1 = false;
        });
    },
    // 修改活动状态
    async editActivityStatus(i) {
      console.log(i);
      let status = 0;
      if (i.activityStatus == true) {
        status = 1;
      } else {
        status = 0;
      }
      await this.$http
        .post(
          `/activityadmin/updateactivitystatsbyid?activity_id=${i.activityId}&status=${status}`
        )
        .then((res) => {
          console.log(res);
        });
    },
    // 发布按钮
    async fbBtn() {
      console.log(this.form);
      // 创建活动
      let params = new FormData();
      params.append("name", this.form.name);
      params.append("classification", this.form.classification);
      params.append("time", this.form.time);
      params.append("staff", this.form.staff);
      params.append("price", this.form.price);
      params.append("textarea", this.form.textarea);
      params.append("xy", this.form.xy);
      params.append("staff_id", 1);

      this.form.fileListHeader.forEach((item) => {
        params.append("headerimg", item.file);
      });
      this.form.fileListImg.forEach((item) => {
        params.append("imgimg", item.file);
      });

      // params.append("headerimg", this.form.fileListHeader);
      // params.append("imgimg", this.form.fileListImg);
      let config = {
        // 添加请求头
        "Content-Type": "multipart/form-data",
        Authorization: store.getters.getToken,
      };
      await this.$http
        .post(`/activityadmin/createactivity`, params, config)
        .then((res) => {
          console.log(res);
        });
      this.getActivityList();
      this.dialogFormVisible = false;
    },
    // 获取协议列表
    async getXyList() {
      console.log(1);
      await this.$http.get(`/contractadmin/getagreemenetlist`).then((res) => {
        console.log(res);
        this.xyList = res.data.data;
      });
    },
    // 获取职工列表
    async getStaffList() {
      await this.$http.get(`/useradmin/getstafflist`).then((res) => {
        console.log(res);
        this.staff_list = res.data.data;
      });
    },
    // 获取活动时间列表
    async getTimeList() {
      await this.$http.get(`/activityadmin/gettimelist`).then((res) => {
        console.log(res);
        // this.time_list = res.data.data;
        for (let i = 0; i < res.data.data.length; i++) {
          if (res.data.data[i].activityId == null) {
            this.time_list.push(res.data.data[i]);
          }
        }
      });
    },

    // 获取活动列表根据分类id
    async getActivityListByClassification() {
      console.log(this.classification_id);
      await this.$http
        .post(
          `/activityadmin/getactivitylistbyclassificationid?classification_id=${this.classification_id}`
        )
        .then((res) => {
          console.log(res);
          for (let i = 0; i < res.data.data.length; i++) {
            if (res.data.data[i].activityStatus == 1) {
              res.data.data[i].activityStatus = true;
            } else {
              res.data.data[i].activityStatus = false;
            }
          }
          this.tableData = res.data.data;
        });
    },
    // 获取活动分类列表
    async getClassification() {
      await this.$http
        .get(`/activityadmin/getclassificationlist`)
        .then((res) => {
          console.log(res);
          this.classification_list = res.data.data;
        });
    },
    // 获取活动列表
    async getActivityList() {
      await this.$http.get(`/activityadmin/getactivitylist`).then((res) => {
        console.log(res);
        for (let i = 0; i < res.data.data.length; i++) {
          if (res.data.data[i].activityStatus == 1) {
            res.data.data[i].activityStatus = true;
          } else {
            res.data.data[i].activityStatus = false;
          }
        }
        this.tableData = res.data.data;
      });
    },
    addActivity() {
      this.dialogFormVisible = true;
    },
  },
};
</script>

<style scoped>
.content {
  margin-top: 10px;
}
img {
  width: 100%;
}

/* 分页 */
.fy {
  text-align: center;
  margin: 10px auto;
}
</style>
