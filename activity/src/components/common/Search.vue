<template>
  <div id="search">
    <van-search
      v-model="value"
      shape="round"
      background="#f7f7f7"
      placeholder="在此搜索活动"
      clearable
      @search="onSearch"
    />
  </div>
</template>

<script>
export default {
  name: "Search",
  data() {
    return {
      value: "",
      list: [],
    };
  },
  methods: {
    // 发送活动模糊搜索
    async onSearch() {
      console.log(this.value);
      await this.$http
        .post(`/activity/searchactivitynamelike?name=${this.value}`)
        .then((res) => {
          console.log(res);
          for (let i = 0; i < res.data.data.length; i++) {
            // res.data.data[i].timeId = res.data.data[i].timeId.split(",").length;
            res.data.data[i].headerList =
              res.data.data[i].headerList[0].headerPath;
            res.data.data[i].imgList = res.data.data[i].imgList[0].imgPath;

            res.data.data[i].timeId = JSON.parse(res.data.data[i].timeId);
          }
          this.list = res.data.data;
        });
      this.$emit("data", this.list);
    },
  },
};
</script>

<style scoped>
.van-search__content {
  background-color: white;
}
</style>
