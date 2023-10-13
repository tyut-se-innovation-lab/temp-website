<!-- 提交 -->
<template>
  <div>
    <div class="button">
      <el-button type="primary" @click="confirm">发布投票</el-button>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    sendVote: {
      type: Function,
      default: () => {
        return Function;
      },
    },
  },
  methods: {
    //确认按钮
    confirm() {
      this.sendVote();
    },
  }
}
</script>

<style scoped>
.button{
  margin-top: 10px;
  text-align: right;
  margin-right: 100px;
  padding-bottom: 20px;
}
</style>
