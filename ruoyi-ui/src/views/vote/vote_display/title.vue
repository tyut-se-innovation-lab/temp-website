<template>
  <div>
    <h1>{{ titletext }}</h1>
    <hr />
  </div>
</template>

<script>
export default {
  name: "quetitle",
  props: ["titletext"],
  data() {
    return {};
  },
  mounted() {},
};
</script>

<style scoped>
h1 {
  margin: 20px 0;
}
</style>