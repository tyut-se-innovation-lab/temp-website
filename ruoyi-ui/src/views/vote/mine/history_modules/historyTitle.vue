<template>
  <div>

    <div class="title">
      <h2>{{ this.getTitle.title }}</h2>
      <span>{{ this.getTitle.authority }}</span>
    </div>
  </div>
</template>

<script>
export default {
  props: ['getTitle']
}
</script>

<style scoped>
.title {
  padding: 15px 0px;
  width: 100%;
  height: auto;
  color: #444444;
  margin: 0 auto;
}

span {
  margin-top: -20px;
  display: block;
  width: 170px;
  text-align: right;
  height: auto;
  font-size: small;
}

</style>
