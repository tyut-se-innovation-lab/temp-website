<template>
  <div class="content">
    <h2>投票简介</h2>
    <div>
      <h3>{{ content }}</h3>
    </div>
  </div>
</template>

<script>
export default {
  name: "content",
  props: ["content"],
  data() {
    return {};
  },
};
</script>

<style scoped>
* {
  margin-top: 20px;
}
</style>