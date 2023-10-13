<template>
  <div>
    <mineRadio v-if="getRadios" :getRadios="getRadios"></mineRadio>
    <mineMultiple v-if="getMultiples" :getMultiples="getMultiples"></mineMultiple>
    <mineText  v-if="getText" :getText="getText"></mineText>
  </div>
</template>

<script>
import mineRadio from "./mineRadio.vue";
import mineMultiple from "./mineMultiple.vue"
import mineText from "@/views/vote/mine/history_modules/mineText.vue";
import el from "element-ui/src/locale/lang/el";
export default {
  props:["getQuestion"],
  components:{
    mineRadio,
    mineMultiple,
    mineText
  },
  mounted() {
    console.log(this.getQuestion)
    console.log("getMultiples")
    console.log(this.getMultiples)
    console.log("getText")
    console.log(this.getText)
  },
  computed:{
    getRadios:function(){
      let question = this.getQuestion.question
      let radioArr = new Array()
      console.log( question.length)
      for (let i = 0; i < question.length; i++) {
        if(question[i].type === "S"){
          radioArr.push(question[i])
        }
      }
      console.log(radioArr)
      if(radioArr.length === 0){
        return false
      }
      return radioArr
    },
    getMultiples:function(){
      let question = this.getQuestion.question
      let multipleArr = new Array()
      for (let i = 0; i < question.length; i++) {
        if(question[i].type === "M"){
          multipleArr.push(question[i])
        }
      }
      if(multipleArr.length === 0){
        return false
      }
      return multipleArr
    },
    getText:function(){
      let question = this.getQuestion.question
      let textArr = new Array()
      for (let i = 0; i < question.length; i++) {
        if(question[i].type === "T"){
          textArr.push(question[i])
        }
      }
      if(textArr.length === 0){
        return false
      }
      return  textArr
    },
  }
}
</script>

<style scoped>

</style>
