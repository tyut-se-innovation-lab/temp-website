<template>
  <div>
    <Join></Join>
    <el-button @click="xj_notification(notificationOption[0])">呼出1</el-button>
    <el-button @click="xj_notification(notificationOption[1])">呼出2</el-button>
    <el-button @click="xj_notification(notificationOption[2])">呼出3</el-button>
    <el-button @click="xj_notification(notificationOption[3])">呼出4</el-button>
  </div>
</template>

<script>
import Join from "@/views/vote/join/brief/index.vue";
export default {
  components: {
    Join,
  },
  data () {
    return {
      notificationOption: [{
        title: 'notification title测试-1',
        message: 'notification message测试-1',
        type: 'success',
        location: 'left-top',
        duration: Math.floor(Math.random() * 5000 + 1000)
      }, {
        title: 'notification title测试-2',
        message: 'notification message测试-2',
        type: 'normal',
        location: 'left-bottom',
        showClose: false
      }, {
        title: 'notification title测试-3',
        message: 'notification message测试-3',
        type: 'warning',
        location: 'right-top',
        duration: 0,
        async callback () {
          console.log('@3 async callback 运行了')
          await new Promise(resolve => {
            setTimeout(() => {
              resolve()
            }, 3000)
          })
          return '@3 callback 运行 的返回值'
        }
      }, {
        title: 'notification title测试-4',
        message: 'notification message测试-4',
        type: 'error',
        location: 'right-bottom',
        callback () {
          console.log('@4 callback 运行了')
          return '@4 callback 运行 的返回值'
        }
      }]
    }
  },
  methods: {
    xj_notification (v) {
      this.$store.dispatch('xjTool/xjNotification', v)
    }
  }
};
</script>

<style>
</style>
