<template>
  <div class="data">
    <h2 class="title" @click="goToDetails">{{ brief.title }}</h2>
    <p class="content" @click="goToDetails">{{ brief.context }}</p>
    <el-dropdown class="icon" trigger="click">
      <span class="el-dropdown-link">
        <svg
          viewBox="0 0 1024 1024"
          version="1.1"
          xmlns="http://www.w3.org/2000/svg"
          p-id="4227"
          width="30"
          height="30"
        >
          <path
            d="M512 704c35.2 0 64 28.8 64 64s-28.8 64-64 64-64-28.8-64-64 28.8-64 64-64z m-64-192c0 35.2 28.8 64 64 64s64-28.8 64-64-28.8-64-64-64-64 28.8-64 64z m0-256c0 35.2 28.8 64 64 64s64-28.8 64-64-28.8-64-64-64-64 28.8-64 64z"
            p-id="4228"
            fill="#cdcdcd"
            class="path"
          ></path>
        </svg>
      </span>
      <el-dropdown-menu slot="dropdown" class="dropdown">
        <el-dropdown-item class="dropdown_item">撤回</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
    <span class="state"
      ><b>{{ state }}</b></span
    >
    <span class="time">{{ brief.creatTime }}--{{ brief.deadline }}</span>
  </div>
</template>

<script>
export default {
  props: ["brief"],
  data() {
    return {
      stateType: ["进行中", "已截止", "弃用"],
    };
  },
  computed: {
    state() {
      return this.stateType[this.brief.state];
    },
  },
  methods: {
    goToDetails() {
      this.$router.push({
        name: "joindetails",
        query: {
          id: this.brief.id,
        },
      });
    },
  },
};
</script>

<style scoped>
.data {
  width: 62vw;
  height: 190px;
  margin: 40px auto;
  position: relative;
  padding: 30px 40px;
  border-radius: 8px;
  border: solid grey 1px;
  cursor: pointer;
  box-sizing: border-box;
}
.data:hover {
  border: solid rgba(40, 40, 255, 0.6) 3px;
  background-color: rgba(135, 207, 235, 0.056);
}
.title {
}
.content {
  width: 80%;
  height: 40px;
  margin: 10px 0;
  color: grey;
  word-wrap: break-word;
  overflow: hidden;
  display: -webkit-box; /*弹性伸缩盒子*/
  -webkit-box-orient: vertical; /*子元素垂直排列*/
  -webkit-line-clamp: 2; /*可以显示的行数，超出部分用...表示*/
  text-overflow: ellipsis; /*（多行文本的情况下，用省略号“…”隐藏溢出范围的文本)*/
}
.time {
  position: absolute;
  right: 15px;
  bottom: 15px;
}
.state {
  position: absolute;
  top: 30%;
  right: 30px;
}
.el-dropdown-link {
  width: 30px;
  height: 30px;
  cursor: pointer;
  color: #409eff;
}
.el-icon-arrow-down {
  font-size: 12px;
}
.icon {
  position: absolute;
  top: 12px;
  right: 15px;
}
.icon:hover .path {
  fill: #000000b7;
}
.dropdown {
  margin: 0;
  padding: 2px 0;
}
.dropdown_item {
  margin: 0;
}
</style>