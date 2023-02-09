<template>
  <div class="submit">
    <el-button @click="submit" class="button">提交</el-button>
  </div>
</template>

<script>
export default {
  name: "submit",
  data() {
    return {};
  },
  methods: {
    /**
     * 提交
     */
    submit() {
      this.callSubmitMethod();
      // this.goToSuccess();
    },
    callSubmitMethod() {
      this.$emit("submitMethod");
    },
    goToSuccess() {
      this.$router.push({
        path: "/vote/join/success",
      });
    },
  },
};
</script>

<style scoped>
.submit {
  width: 100%;
  position: relative;
}
.button {
  margin: 10px;
  position: absolute;
  right: 0;
}
</style>