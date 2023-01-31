<template>
  <div class="deadline">
    <h4>截止时间：{{ deadline }}</h4>
  </div>
</template>

<script>
export default {
  name: "deadline",
  props: ["deadline"],
  data() {
    return {};
  },
};
</script>

<style scoped>
.deadline {
  font-size: 15px;
  color: red;
  position: absolute;
  right: 0;
}
</style>