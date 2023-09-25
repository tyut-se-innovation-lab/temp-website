<template>
  <div>
    <div class="header">文本输入</div>
    <div class="content" v-for="item in getText" :key="item.id">
      <div>{{item.id+1}}、{{item.queContent}}</div>
        <el-input v-model="item.options[0]" type="textarea" disabled/>
    </div>

  </div>
</template>

<script>
export default {
  name: "mineText",
  props:["getText"],
  mounted() {
    console.log(this.getText)
  }
}
</script>

<style scoped>
.content{
  height: 100px;
  width: 95%;
  margin: 0 auto;
}
.header{
  font-size: large;
  width: 96%;
  margin: 0 auto;
  height: 40px;
  font-weight: bold;
}
.option{
  padding: 5px 10px;
}
</style>



