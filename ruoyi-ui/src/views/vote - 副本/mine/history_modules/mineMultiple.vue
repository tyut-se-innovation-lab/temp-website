<template>
  <div>
    <div class="header">多项选择</div>
    <div class="content" v-for="item  in getMultiples" :key="item.id">
      <div>{{item.id+1}}、{{item.queContent}}</div>
      <div class="option" v-for="(option , optionIndex) in item.options" :key="optionIndex">
        <el-radio v-model="option.isSelect" disabled :label="1">{{option.content}}</el-radio>
      </div>
    </div>

  </div>
</template>

<script>
export default {
  name: "mineMultiple",
  props:["getMultiples"],
  mounted() {
    console.log("getMultiples")
    console.log(this.getMultiples)
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

