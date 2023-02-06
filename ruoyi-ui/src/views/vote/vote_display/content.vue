<template>
  <div class="content">
    <h2>投票简介</h2>
    <div>
      <h3>{{ contenttext }}</h3>
    </div>
  </div>
</template>

<script>
export default {
  name: "quecontent",
  props: ["contenttext"],
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