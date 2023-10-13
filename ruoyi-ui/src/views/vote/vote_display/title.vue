<template>
  <div>
    <p>{{ titletext }}</p>
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
p {
  font-size: 30px;
  font-weight: 600;
}
</style>