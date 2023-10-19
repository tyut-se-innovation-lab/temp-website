<script>
export default {
  name: 'voteFooter',
}
</script>

<template>
  <div class="voteFooter">
    <slot>

    </slot>
  </div>
</template>

<style scoped>
.voteFooter {
  width: 100%;
  //height: 6em;
  position: fixed;
  bottom: 0;
  padding: 10px;
  background-color: rgba(255, 255, 255, .7);
  backdrop-filter: blur(10px);
  -webkit-backdrop-filter: blur(10px);
}
</style>
