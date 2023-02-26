<template>
  <div id="search">
    <van-search
      v-model="value"
      shape="round"
      background="#f7f7f7"
      placeholder="在此搜索活动"
    />
  </div>
</template>

<script>
export default {
  name: "Search",
  data() {
    return {
      value: "",
    };
  },
  methods: {
    search() {},
    cancel() {},
  },
};
</script>

<style scoped>
.van-search__content {
  background-color: white;
}
</style>
