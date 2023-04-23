
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

<!--<style>-->
<!--=======-->
<!--<template>-->
<!--  <div>-->
<!--    <History></History>-->
<!--  </div>-->
<!--</template>-->

<!--<script>-->
<!--import History from "@/views/vote/history/brief/index.vue";-->
<!--export default {-->
<!--  components: {-->
<!--    History,-->
<!--  },-->
<!--  methods: {},-->
<!--};-->
<!--</script>-->

<!--<style>-->
<!--&gt;>>>>>> 8f433acc9b4d473e4bb26eaa132e3f01530f6d5b-->
<!--</style>-->
