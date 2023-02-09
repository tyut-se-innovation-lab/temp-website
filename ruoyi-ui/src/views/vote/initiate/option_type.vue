<template>
  <div>
<!--    单选框-->
    <single v-if="isShow.singleShow"  ref="singleUse" @childSingle="getSingle"/>
<!--    多选框-->
    <multiple v-if="isShow.multipleShow" ref = "multipleUse" @childMultiple="getMultiple" :dataMsg="dataMsg"/>
<!--    填空问卷-->
    <fill v-if="isShow.fillShow" ref = "fillUse"  @childFill="getFill" :Msg="Msg" />

<!--按钮组群-->
    <div class="buttonGroups">
      <el-button type="primary" @click="addSingle">添加单选题</el-button>
      <el-button type="primary" @click="addMultiple">添加多选题</el-button>
      <el-button type="primary" @click="addFill">添加文本框</el-button>
    </div>
  </div>
</template>

<script>
import single from "./option_modules/single.vue";
import multiple from "./option_modules/multiple.vue"
import fill from "./option_modules/fill.vue";
export default {
  data(){
    return {
      // 页面展示
      isShow:{
        singleShow:false,
        multipleShow:false,
        fillShow:false
      },
      dataMsg:1,
      // 多选题号
      Msg:2,
      // 临时数据存放
      temp:{
        tempFill:[],
        tempSingle:[],
        tempMultiple:[]
      }
    }
  },
  components:{
    single,
    multiple,
    fill
  },
  methods:{
    addSingle(){
      if(this.isShow.singleShow ){
        this.$refs.singleUse.addSubject()
      }else {
        this.isShow.singleShow = true;
      }
    },
    addMultiple(){
      // console.log("lala1")
      if(this.isShow.multipleShow ){
        this.$refs.multipleUse.addMultiple()
      }else {
        this.isShow.multipleShow = true;
      }
    },
    addFill(){
      if(this.isShow.fillShow ){
        this.$refs.fillUse.addFill()
      }else {
        this.isShow.fillShow = true;
      }
    },
    getSingle(val){
      this.temp.tempSingle= val
      this.dataMsg = this.temp.tempSingle.length ;
      // if(this.tempMultiple )
      this.Msg = this.temp.tempMultiple.length + this.dataMsg ;
      if (this.temp.tempSingle.length === 0)
        this.isShow.singleShow = false
    },
    getMultiple(val){

      this.temp.tempMultiple = val;
      this.dataMsg = this.temp.tempSingle.length ;
      this.Msg = this.temp.tempMultiple.length + this.dataMsg ;
      if (this.temp.tempMultiple.length === 0)
        this.isShow.multipleShow = false
    },
    getFill(val){
      this.temp.tempFill = val;
      this.dataMsg = this.temp.tempSingle.length ;
      this.Msg = this.temp.tempMultiple.length + this.dataMsg ;
      if (this.temp.tempFill.length === 0)
        this.isShow.fillShow = false
    },
  },
  watch:{
    temp:{
      handler(newVal,oldVal){
        this.$emit("getOption",newVal)
      },
      deep: true,//深度监视
      immediate:true,//页面第一次渲染触监听器
    }
  }
}
</script>

<style  scoped>
.buttonGroups{
  width: 500px;
  height: 50px;
  margin: 0 auto;
}
</style>
