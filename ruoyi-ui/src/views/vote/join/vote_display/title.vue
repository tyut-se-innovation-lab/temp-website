<template>
  <div>
    <h1>{{ title }}</h1>
    <hr />
  </div>
</template>

<script>
export default {
  name: "title",
  props: ["title"],
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