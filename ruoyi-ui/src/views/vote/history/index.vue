<template>
  <div>
    <router-view />
  </div>
</template>

<script>
import History from "@/views/vote/history/brief/index.vue";
export default {
  components: {
    History,
  },
  methods: {
    gotoHistory() {
      this.$router.push("/vote/join");
    },
  },
};
</script>

<style>
</style>