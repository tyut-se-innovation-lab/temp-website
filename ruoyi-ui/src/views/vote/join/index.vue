<script>
import Vue from 'vue'
import VoteFooter from '@/views/vote/join/modules/VoteFooter.vue'

export default {
  name: 'join',
  components: {
    'vote-footer': VoteFooter
  },
  data () {
    return {
      notificationOption: [
      {
        title: 'notification title测试-1',
        message: 'notification message测试-1',
        type: 'success',
        location: 'left-top',
        duration: Math.floor(Math.random() * 5000 + 1000),
        callback: () => {
          this.$store.dispatch('app/toggleDevice', 'desktop')
        }
      }, {
        title: 'notification title测试-2',
        message: 'notification message测试-2',
        type: 'normal',
        location: 'left-bottom',
        showClose: false,
        callback: () => {
          this.$store.dispatch('app/toggleDevice', 'mobile')
        }
      }, {
        title: 'notification title测试-3',
        message: 'notification message测试-3',
        type: 'warning',
        location: 'right-top-2',
        duration: 0,
        callback: () => {
          console.log('@3 async callback 运行了')
          // this.$tab.openPage('通知', '/user/noticePop')
          if (this.$store.getters['device'] === 'mobile') {
            console.log(10)
          } else {
            console.log(this.$store.getters['sidebar']['opened'] ? 210 : 81)
          }
          console.log(this.$store.getters['device'])
          console.log(this.$store.getters['sidebar']['opened'])
          return '@3 callback 运行 的返回值'
        }
      }, {
        title: 'notification title测试-4',
        message: 'notification message测试-4',
        type: 'error',
        location: 'right-bottom',
        callback: () => {
          console.log('@4 callback 运行了')
          this.$store.dispatch('app/toggleSideBarHide', !this.toggleSideBarHide)
          this.toggleSideBarHide = !this.toggleSideBarHide
          return '@4 callback 运行 的返回值'
        }
      }],
      toggleSideBarHide: false,
      show: false,
      dialogOption: {
        speed: 300,
        on: [{
          opacity: '1',
          top: '96vh',
          transform: 'scale(10%, 5%) translate(-50%, 0)',
          'transform-origin': 'left top',
          left: '28px',
        }],
        off: [{
          opacity: '1',
          top: '96vh',
          transform: 'scale(10%, 5%) translate(-50%, 0)',
          'transform-origin': 'left top',
          left: '0%',
        }]
      }
    }
  },
  methods: {
    /**
     * 测试通知
     * @param v
     */
    xj_notification (v) {
      this.$xjNotify(v)
    },
    /**
     * 测试dialog关闭
     * @param done
     */
    handleClose (done) {
      console.log('@dialog close')
      done()
    },
    /**
     * 测试dialog
     */
    openDialog () {
      let _left = null;
      if (this.$store.getters['device'] === 'mobile') {
        _left = 30
      } else {
        _left = this.$store.getters['sidebar']['opened'] ? 230 : 100
      }
      /* 更新dialog动画 */
      Vue.set(this.dialogOption['on'][0], 'left', `${ _left }px`)
      Vue.set(this.dialogOption['off'][0], 'left', `${ _left }px`)
      this.show = true
    }
  },
  watch: {

  }
}
</script>

<template>
  <div class="join">
    <xj-dialog
      :visible.sync="show"
      title="title"
      :modal="true"
      :show-close="true"
      :before-close="handleClose"
      :options="dialogOption"
      :close-on-click-modal="true"
      :close-on-press-escape="true"
      :lock-scroll="true"
      :mob-width="'80%'"
    >
      <p>这是一段信息</p>
      <p>这是一段信息</p>
      <p>这是一段信息</p>
      <p>这是一段信息</p>
      <p>这是一段信息</p>
      <p>这是一段信息</p>
      <p>这是一段信息</p>
      <span slot="footer">
        <el-button @click="show = false">完成</el-button>
      </span>
    </xj-dialog>
    <vote-footer>
      <el-button @click="openDialog">呼出dialog</el-button>
      <el-button @click="xj_notification(notificationOption[0])">呼出1</el-button>
      <el-button @click="xj_notification(notificationOption[1])">呼出2</el-button>
      <el-button @click="xj_notification(notificationOption[2])">呼出3</el-button>
      <el-button @click="xj_notification(notificationOption[3])">呼出4</el-button>
    </vote-footer>
  </div>
</template>

<style scoped>
.join {
  height: 100%;
}
</style>
