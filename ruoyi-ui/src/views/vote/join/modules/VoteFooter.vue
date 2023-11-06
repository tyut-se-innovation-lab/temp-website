<script>
export default {
  name: 'voteFooter',
  props: ['show'],
  watch: {
    show (nV) {
      if (nV) {
        this.$refs['voteFooter'].style['transform'] = ' translateY(0)'
      } else {
        this.$refs['voteFooter'].style['transform'] = ' translateY(100%)'
      }
    }
  }
}
</script>

<template>
  <div ref="voteFooter" class="voteFooter">
    <slot>

    </slot>
  </div>
</template>

<style scoped>
.voteFooter {
  width: 100%;
  //height: 6em;
  position: fixed;
  transition: all .5s;
  bottom: 0;
  padding: 10px;
  background-color: rgba(255, 255, 255, .7);
  backdrop-filter: blur(10px);
  -webkit-backdrop-filter: blur(10px);
}
</style>
