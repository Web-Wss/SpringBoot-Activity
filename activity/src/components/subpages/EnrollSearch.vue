<template>
  <div id="enrollsearch">
    <!-- 报名列表 -->
    <van-divider
      :style="{ color: '#1989fa', borderColor: '#1989fa', padding: '0 16px' }"
    >
      报名列表
    </van-divider>
    <van-card
      v-for="i in 3"
      :key="i"
      desc="参与者姓名:位爽爽"
      title="2023天狼暑期夏令营"
      thumb="https://img0.baidu.com/it/u=1844749691,2659758486&fm=253&fmt=auto&app=138&f=JPEG?w=742&h=500"
    >
      <template #tags>
        <van-tag plain type="danger">姓名:位爽爽</van-tag><br /><br />
        <van-tag plain type="danger">参与者证件:342225199902256232</van-tag>
      </template></van-card
    >
    <van-divider
      :style="{ color: '#1989fa', borderColor: '#1989fa', padding: '0 16px' }"
    >
      报名列表
    </van-divider>
    <van-steps :active="active">
      <van-step>用户报名</van-step>
      <van-step>公司统筹</van-step>
      <van-step>现场签到</van-step>
      <van-step>活动结束</van-step>
    </van-steps>
    <div class="btn">已签到</div>
  </div>
</template>

<script>
export default {
  name: "EnrollSearch",
  data() {
    return {
      active: 2,
    };
  },
};
</script>

<style scoped>
.btn {
  color: white;
  border-radius: 50px;
  text-align: center;
  line-height: 70px;
  margin: 0 auto;
  width: 70px;
  height: 70px;
  background-color: rgb(78, 183, 225);
}
</style>
