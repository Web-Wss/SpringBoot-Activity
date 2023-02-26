<template>
  <div id="enrollpage">
    <!-- 活动名称 -->
    <div class="name">2023天狼暑期夏令营（鹰隼）</div>
    <!-- 选择日期 -->
    <div class="wz">
      选择日期
      <span>剩余名额:100人</span>
    </div>

    <div class="date">
      <span>6.25~7.25</span>
      <span>6.25~7.25</span>
      <span>6.25~7.25</span>
      <span>6.25~7.25</span>
      <span>6.25~7.25</span>
      <span>6.25~7.25</span>
      <span>6.25~7.25</span>
      <span>6.25~7.25</span>
      <span>6.25~7.25</span>
      <span>6.25~7.25</span>
      <span>6.25~7.25</span>
      <span>6.25~7.25</span>
      <span>6.25~7.25</span>
      <span>6.25~7.25</span>
    </div>
    <!-- 参与者选择 -->
    <div class="participant">
      <van-divider>选择参与者</van-divider>
      <van-checkbox-group
        v-model="result"
        :max="2"
        v-for="i in participant"
        :key="i.id"
      >
        <van-checkbox :name="i.id">{{ i.name }} - {{ i.card }}</van-checkbox
        ><br />
      </van-checkbox-group>
    </div>

    <!-- 教官选择 -->
    <van-divider>选择教官</van-divider>
    <div class="staff_change">
      <div class="staff" v-for="i in 7" :key="i">
        <div class="img">
          <img src="https://s1.ax1x.com/2022/03/16/qpRzGT.jpg" alt="" />
        </div>
        <div class="introduce">
          <div class="dh"><b>代号:</b>白鲨</div>
          <b>简介:</b
          >我是简介我是简介我是简介我是简介我是简介我是简介我是简介我是简介我是简介我是简介我是简介我是简介我是简介我是简介我是简介
        </div>
        <div class="more">更多介绍>></div>
      </div>
    </div>

    <!-- 提交报名 -->
    <van-submit-bar button-text="提交报名" @submit="onSubmit" />

    <!-- 确认信息 -->
    <van-action-sheet v-model="show" title="确认报名信息">
      <div class="content">
        <van-divider
          :style="{
            color: '#1989fa',
            borderColor: '#1989fa',
            padding: '0 16px',
          }"
          >参加日期</van-divider
        >
        <van-cell value="6.26~8.25">
          <!-- 使用 title 插槽来自定义标题 -->
          <template #title>
            <span class="custom-title">您选择的日期:</span>
          </template>
        </van-cell>
        <van-divider
          :style="{
            color: '#1989fa',
            borderColor: '#1989fa',
            padding: '0 16px',
          }"
          >参与者</van-divider
        >
        <van-cell value="位爽爽、刘庆">
          <!-- 使用 title 插槽来自定义标题 -->
          <template #title>
            <span class="custom-title">您选择的参与者:</span>
          </template>
        </van-cell>
        <van-divider
          :style="{
            color: '#1989fa',
            borderColor: '#1989fa',
            padding: '0 16px',
          }"
          >教官</van-divider
        >
        <van-cell value="白鲨">
          <!-- 使用 title 插槽来自定义标题 -->
          <template #title>
            <span class="custom-title">您选择的教官:</span>
          </template>
        </van-cell>
        <!-- 提交按钮 -->
        <van-button
          round
          size="large"
          color="linear-gradient(to right, #ff6034, #ee0a24)"
        >
          确认报名
        </van-button>
      </div>
    </van-action-sheet>
  </div>
</template>

<script>
export default {
  name: "EnrollPage",

  data() {
    return {
      // 确定面板
      show: true,
      participant: [
        {
          id: 1,
          name: "位爽爽",
          card: 342225199902256232,
        },
        {
          id: 2,
          name: "刘庆",
          card: 341182199801082422,
        },
        {
          id: 3,
          name: "张三",
          card: 341182199801082422,
        },
        {
          id: 4,
          name: "李四",
          card: 341182199801082422,
        },
      ],
      result: [],
    };
  },
  methods: {
    // 提交报名

    onSubmit() {
      this.show = true;
    },
  },
};
</script>

<style scoped>
#enrollpage {
  padding: 10px;
}
.name {
  font-size: 16px;
  font-weight: 600;
  padding-bottom: 10px;
  border-bottom: 1px solid rgb(226, 226, 226);
}
.wz {
  padding: 5px 0 10px 0;
  font-size: 16px;
  font-weight: 600;
}
.wz span {
  font-size: 12px;
  color: rgb(255, 93, 93);
}
.date {
  width: 100%;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
}
.date span {
  font-size: 14px;
  text-align: center;
  border-radius: 25px;
  width: 40%;
  display: block;
  margin: 10px;
  padding: 5px;
  background-color: rgb(232, 232, 232);
}
.date span:hover {
  background-color: rgb(255, 220, 220);
}

/* 选择教官 */
.staff_change {
  margin-bottom: 50px;
  width: 100%;
  display: flex;
  flex-direction: row;
  /* overflow-x: hidden; */
  overflow-y: hidden;
}

.staff_change .staff {
  min-width: 110px;
  margin: 5px;
  height: 220px;
  background-color: rgb(244, 244, 244);
}
.staff_change .staff:hover {
  background-color: rgb(0, 255, 34);
}
.staff_change .staff .img {
  padding-top: 5px;
  text-align: center;
}
.staff_change .staff .img img {
  width: 80px;
  height: 80px;
  border-radius: 50px;
}
.staff_change .staff .introduce {
  overflow: hidden;
  width: 110px;
  height: 95px;
  font-size: 12px;
  text-align: center;
  /* 省略号 */
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 6;
}
.staff_change .staff .more {
  margin-top: 5px;
  width: 100%;
  text-align: center;
  font-size: 14px;
  color: red;
}

/* 确定面板 */
.content {
  padding: 16px 16px 26px;
}
</style>
