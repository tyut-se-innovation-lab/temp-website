<!-- 进度条 -->
<template>
  <div class="progress">
    <el-progress :percentage="num" :format="format"></el-progress>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        num:87
      }
    },
    methods: {
      format(percentage) {
        return percentage === 100 ? '满' : `${percentage}%`;
      }
    }
  }
</script>

<style lang="css" scoped>
.progress{
  display: inline-block;
  width: 400px;
  height: 20px;
}
</style>
