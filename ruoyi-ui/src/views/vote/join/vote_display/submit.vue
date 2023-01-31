<template>
  <div class="submit">
    <el-button @click="submit">提交</el-button>
  </div>
</template>

<script>
export default {
  name: "submit",
  data() {
    return {};
  },
  methods: {
    submit() {
      this.$emit("sendData");
    },
  },
};
</script>

<style scoped>
.submit {
  position: absolute;
  right: 0;
}
</style>