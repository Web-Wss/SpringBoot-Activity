<template>
  <div id="notice">
    <div class="content">
      <el-table :data="tableData" stripe border style="width: 100%">
        <el-table-column
          prop="noticeId"
          label="通知ID"
          width="180"
          align="center"
        >
        </el-table-column>
        <el-table-column
          prop="noticeContent"
          label="通知内容"
          min-width="180"
          align="center"
        >
        </el-table-column>
        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <el-button
              type="primary"
              icon="el-icon-edit"
              circle
              @click="editBtn(scope.row)"
            ></el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <!-- 修改弹窗 -->
    <el-dialog title="修改通知内容" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="ID">
          <el-input v-model="form.id" autocomplete="off" disabled></el-input>
        </el-form-item>
        <el-form-item label="分类名称">
          <el-input v-model="form.content" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="editBtn1()">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "Notice",
  data() {
    return {
      // 修改弹窗
      dialogFormVisible: false,
      form: {
        id: 0,
        content: "",
      },
      tableData: [
        // {
        //   noticeId: "1",
        //   noticeContent: "2023年天狼暑期夏令营招生报名开始啦！！！",
        // },
      ],
    };
  },
  mounted() {
    // 获取通知内容
    this.getNoticeList();
  },
  methods: {
    // 修改通知内容
    editBtn(i) {
      console.log(i);
      this.form.id = i.noticeId;
      this.form.content = i.noticeContent;
      this.dialogFormVisible = true;
    },
    // 确认修改
    async editBtn1() {
      await this.$http
        .post(
          `/dataadmin/eidtnoticecontent?id=${this.form.id}&content=${this.form.content}`
        )
        .then((res) => {
          console.log(res);
          this.dialogFormVisible = false;
          // 重新获取通知列表
          this.getNoticeList();
        });
    },
    // 获取通知内容
    async getNoticeList() {
      await this.$http.get(`/dataadmin/getnoticelist`).then((res) => {
        console.log(res);
        this.tableData = res.data.data;
      });
    },
  },
};
</script>

<style></style>
