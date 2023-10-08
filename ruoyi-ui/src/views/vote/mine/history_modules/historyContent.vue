<template>
  <div class="title">

    <div class="time"><span v-if="getContent.createdTime">{{getContent.createdTime}}</span> -------<span v-if="getContent.deadline">
      {{getContent.deadline}}</span>
    </div>
    <span ><strong>简介:</strong> {{getContent.content}}</span>
    <span v-if="getContent.authority"><strong>发起人:</strong> {{getContent.authority}}</span>
    <div class="voteEnd">
      <p> {{isEnd}}</p>
    </div>
  </div>
</template>

<script>
export default {
  props:["getContent"],
  mounted() {
    // console.log(this.getContent)
  },
  computed:{
    isEnd:function () {
      if(this.getContent.status == 0){
        return "正在进行中..."
      }else {
        return "已结束"
      }
    }
  }
}
</script>

<style scoped>
.title{
  padding:10px 20px;
  width: 100%;
  height: auto;
}
.title> span{
  display: block;
  padding: 10px;
}
.title> .time{
  float: right;
  width: 400px;
  height: auto;
  padding: 10px;
  font-size: 10px;
  text-align: left;
  margin-top: -15px;
}
.voteEnd{
  display: block;
  width: 200px;
  height: 40px;
  float: right;
  font-size: large;
  margin-top: -95px;
  margin-left: 20px;
  color: brown;
}

</style>
