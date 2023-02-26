<template>
  <div id="headeradmin">
    <div class="left">
      <img
        src="https://img1.baidu.com/it/u=1637591443,2879807930&fm=253&app=138&size=w931&n=0&f=JPEG&fmt=auto?sec=1676826000&t=42cc567b024504b3b92436ee8343a30f"
        alt=""
      />
    </div>
    <div class="center">天狼教育活动发布管理平台</div>
    <div class="right">
      <div class="img">
        <img
          src="https://img1.baidu.com/it/u=1637591443,2879807930&fm=253&app=138&size=w931&n=0&f=JPEG&fmt=auto?sec=1676826000&t=42cc567b024504b3b92436ee8343a30f"
          alt=""
        />
      </div>
      <div class="name">管理员1</div>
    </div>
  </div>
</template>

<script>
export default {
  name: "HeaderAdmin",
  data() {
    return {};
  },
};
</script>

<style scoped>
#headeradmin {
  width: 100%;
  height: 60px;
  display: flex;
}

.left {
  width: 33%;
  text-align: left;
  padding-left: 60px;
}
.left img {
  width: 60px;
}
.center {
  font-size: 20px;
  color: rgb(0, 13, 255);
  width: 33%;
  text-align: center;
  font-weight: 600;
}
.right {
  width: 33%;
  display: flex;
  justify-content: flex-end;
}
.right img {
  padding: 10px;
  width: 40px;
  height: 40px;
  border-radius: 50px;
}
.right .name {
  font-size: 14px;
  color: red;
  line-height: 60px;
  text-align: right;
}
</style>
