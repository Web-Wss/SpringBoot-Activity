<template>
  <div id="agreementmanage">
    <el-button type="primary" @click="add">+ 新增协议</el-button>
    <!-- 内容 -->
    <div class="content">
      <el-table :data="tableData" stripe border style="width: 100%">
        <el-table-column
          prop="agreementId"
          label="协议ID"
          width="200"
          align="center"
        >
        </el-table-column>
        <el-table-column
          prop="agreementName"
          label="协议名称"
          min-width="80"
          align="center"
        >
        </el-table-column>
        <el-table-column label="协议内容" min-width="180" align="center">
          <template slot-scope="scope">
            <div v-html="scope.row.agreementContent"></div>
          </template>
        </el-table-column>
        <el-table-column
          prop="createTime"
          label="创建时间"
          width="180"
          align="center"
        >
        </el-table-column>

        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <el-button
              type="primary"
              icon="el-icon-edit"
              circle
              @click="editAgreementBtn(scope.row)"
            ></el-button>
            <el-button
              type="danger"
              icon="el-icon-delete"
              circle
              @click="deleteAgreementBtn(scope.row)"
            ></el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <!-- 添加协议弹窗 -->
    <el-dialog title="新增协议" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="协议名称">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="协议内容">
          <!-- <el-input v-model="form.content" autocomplete="off"></el-input> -->
          <br />
          <EditAgreement @change="getVal" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="addAgreement">确 定</el-button>
      </div>
    </el-dialog>

    <!-- 修改协议弹窗 -->
    <el-dialog title="修改协议" :visible.sync="dialogFormVisible1">
      <el-form :model="form">
        <el-form-item label="协议名称">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="协议内容">
          <!-- <el-input v-model="form.content" autocomplete="off"></el-input> -->
          <br />
          <EditAgreement @change="getVal" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible1 = false">取 消</el-button>
        <el-button type="primary" @click="EditAgreement">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import EditAgreement from "../subpages_admin/EditAgreement.vue";
export default {
  name: "AgreementManage",
  components: {
    EditAgreement,
  },
  data() {
    return {
      // 新增协议
      dialogFormVisible: false,
      dialogFormVisible1: false,
      form: {
        id: 0,
        name: "",
        content: "",
      },
      tableData: [
        // {
        //   agreementId: "1",
        //   agreementName: "2023年天狼暑期夏令营活动协议",
        //   agreementContent: "2023年天狼暑期夏令营活动协议内容",
        //   createTime: "2023-04-09 16:00:00",
        // },
      ],
    };
  },
  mounted() {
    // 获取协议列表
    this.getAgreementList();
  },
  methods: {
    // 删除协议
    async deleteAgreementBtn(i) {
      console.log(i);
      await this.$http
        .post(
          `/contractadmin/deleteagreementbyid?agreement_id=${i.agreementId}`
        )
        .then((res) => {
          console.log(res);
          if (res.data.data == 1) {
            // 删除成功
            this.getAgreementList();
          } else {
            this.$message.error(
              "此协议已经被使用，不能删除，想要删除，请先删除活动"
            );
          }
        });
    },
    // 获取富文本内容并创建协议
    getVal(msg) {
      this.form.content = msg;
    },
    // 新增协议
    async addAgreement() {
      await this.$http
        .post(
          `/contractadmin/addagreement?name=${this.form.name}&content=${this.form.content}`
        )
        .then((res) => {
          console.log(res);
          this.dialogFormVisible = false;
          this.getAgreementList();
        });
    },
    add() {
      this.dialogFormVisible = true;
    },
    // 点击修改按钮
    editAgreementBtn(i) {
      console.log(i);
      this.form.id = i.agreementId;
      this.form.name = i.agreementName;
      this.form.content = i.agreementContent;
      // 传值给子组件
      this.dialogFormVisible1 = true;
    },
    // 修改协议
    async EditAgreement() {
      await this.$http
        .post(
          `/contractadmin/editagreementbyid?id=${this.form.id}&name=${this.form.name}&content=${this.form.content}`
        )
        .then((res) => {
          this.dialogFormVisible1 = false;
          this.getAgreementList();
        });
    },
    // 获取协议列表
    async getAgreementList() {
      await this.$http.get(`/contractadmin/getagreemenetlist`).then((res) => {
        console.log(res);
        this.tableData = res.data.data;
      });
    },
  },
};
</script>

<style scoped>
.content {
  margin-top: 10px;
}
</style>
