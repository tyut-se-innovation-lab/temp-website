<script>
import Vue from 'vue'
import VoteFooter from '@/views/vote/join/modules/VoteFooter.vue'
import VoteRoughList from '@/views/vote/join/modules/tool/roughVoteList'

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
        onclick: () => {
          this.$store.dispatch('app/toggleDevice', 'desktop')
        }
      }, {
        title: 'notification title测试-2',
        message: 'notification message测试-2',
        type: 'normal',
        location: 'left-bottom',
        showClose: false,
        onclick: () => {
          this.$store.dispatch('app/toggleDevice', 'mobile')
        }
      }, {
        title: 'notification title测试-3',
        message: 'notification message测试-3',
        type: 'warning',
        location: 'right-top-2',
        duration: 0,
        onclick: () => {
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
        onclick: () => {
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
      },
      voteData: [
        {
          voteId: 0,
          title: 'title-0000000000000000000000000000000000000000000000000',
          content: 'content-0 00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000',
          status: 1,
          createTime: '2023-10-03 19:20:24',
          deadTime: '2023-11-15 24:00:00',
          userName: '迅疾0',
          userId: 0
        },
        {
          voteId: 1,
          title: 'title-1',
          content: 'content-1',
          status: 1,
          createTime: '2023-10-05 19:20:24',
          deadTime: '2023-11-25 24:00:00',
          userName: '迅疾1',
          userId: 1
        },
        {
          voteId: 2,
          title: 'title-2',
          content: 'content-2',
          status: 2,
          createTime: '2023-10-05 19:20:24',
          deadTime: '2023-11-25 24:00:00',
          userName: '迅疾2',
          userId: 2
        },
        {
          voteId: 3,
          title: 'title-3',
          content: 'content-3',
          status: 3,
          createTime: '2023-10-05 19:20:24',
          deadTime: '2023-11-25 24:00:00',
          userName: '迅疾3',
          userId: 3
        },
        {
          voteId: 4,
          title: 'title-4',
          content: 'content-4',
          status: 4,
          createTime: '2023-10-05 19:20:24',
          deadTime: '2023-11-25 24:00:00',
          userName: '迅疾4',
          userId: 4
        },
        {
          voteId: 5,
          title: 'title-5',
          content: 'content-5',
          status: 5,
          createTime: '2023-10-05 19:20:24',
          deadTime: '2023-11-25 24:00:00',
          userName: '迅疾5',
          userId: 5
        },
        {
          voteId: 6,
          title: 'title-6',
          content: 'content-6',
          status: 1,
          createTime: '2023-10-05 19:20:24',
          deadTime: '2023-11-25 24:00:00',
          userName: '迅疾6',
          userId: 6
        },
        {
          voteId: 7,
          title: 'title-7',
          content: 'content-7',
          status: 1,
          createTime: '2023-10-05 19:20:24',
          deadTime: '2023-11-25 24:00:00',
          userName: '迅疾7',
          userId: 7
        },
        {
          voteId: 8,
          title: 'title-8',
          content: 'content-8',
          status: 2,
          createTime: '2023-10-05 19:20:24',
          deadTime: '2023-11-25 24:00:00',
          userName: '迅疾8',
          userId: 8
        },
        {
          voteId: 9,
          title: 'title-9',
          content: 'content-9',
          status: 3,
          createTime: '2023-10-05 19:20:24',
          deadTime: '2023-11-25 24:00:00',
          userName: '迅疾9',
          userId: 9
        },
        {
          voteId: 10,
          title: 'title-10',
          content: 'content-10',
          status: 4,
          createTime: '2023-10-05 19:20:24',
          deadTime: '2023-11-25 24:00:00',
          userName: '迅疾10',
          userId: 10
        },
        {
          voteId: 11,
          title: 'title-11',
          content: 'content-11',
          status: 5,
          createTime: '2023-10-05 19:20:24',
          deadTime: '2023-11-25 24:00:00',
          userName: '迅疾11',
          userId: 11
        },
        {
          voteId: 12,
          title: 'title-12',
          content: 'content-12',
          status: 1,
          createTime: '2023-10-05 19:20:24',
          deadTime: '2023-11-25 24:00:00',
          userName: '迅疾12',
          userId: 12
        },
        {
          voteId: 13,
          title: 'title-13',
          content: 'content-13',
          status: 1,
          createTime: '2023-10-05 19:20:24',
          deadTime: '2023-11-25 24:00:00',
          userName: '迅疾13',
          userId: 13
        },
        {
          voteId: 14,
          title: 'title-14',
          content: 'content-14',
          status: 1,
          createTime: '2023-10-05 19:20:24',
          deadTime: '2023-11-25 24:00:00',
          userName: '迅疾14',
          userId: 14
        }
      ],
      voteList: null
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
  mounted () {
    console.log(this.$store.getters['device'])
    this.voteList = new VoteRoughList(this.$refs['vote-list'], this.voteData, this.$store.getters['device'])
    this.voteList.onclick(({ voteId }) => {
      // return require.voteId(voteId)
      voteId
      // 理论上这里返回应该发送的亲求
      return new Promise(resolve => {
        setTimeout(() => {
          resolve({
            voteId: 0,
            userName: '迅疾',
            voteType: 3,
            title: '详细标题11111111111111111111111111111111111111aweaweqwe青蛙恶趣味啊是大多数',
            content: '详细介绍埃斯库嘎斯的空间噶噶啥的客户嘎斯的空间和按时灯笼裤飞机哈桑法律噶啥的客户嘎斯的空间和按时灯笼裤飞机哈桑法律噶啥的客户嘎斯的空间和按时灯笼裤飞机哈桑法律噶啥的客户嘎斯的空间和按时灯笼裤飞机哈桑法律啥的客户嘎斯的空间和按时灯笼裤飞机哈桑法律框架哈桑打开链接',
            status: 1,
            createTime: '2023-10-03 19:20:24',
            deadTime: '2023-10-13 19:20:24',
            optionNum: 3,
            isRealTime: '',
            voteOptionLaunchDTOs: [
              {
                id: 0,
                voteId: 0,
                optionType: 'S',
                content: '000',
                percentage: '10%',
                voteNum: 13,
                isSelect: false
              },
              {
                id: 1,
                voteId: 0,
                optionType: 'S',
                content: '111',
                percentage: '20%',
                voteNum: 26,
                isSelect: false
              },
              {
                id: 2,
                voteId: 0,
                optionType: 'S',
                content: '222',
                percentage: '40%',
                voteNum: 52,
                isSelect: false
              },
              {
                id: 3,
                voteId: 0,
                optionType: 'S',
                content: '333',
                percentage: '30%',
                voteNum: 39,
                isSelect: false
              },
              {
                id: 4,
                voteId: 0,
                optionType: 'S',
                content: '444',
                percentage: '50%',
                voteNum: 52,
                isSelect: false
              }
            ],
            isComplete: 0,
            voteWeights: [
              {
                roleId: 0,
                roleName: '超管',
                weight: 10
              },
              {
                roleId: 100,
                roleName: '人',
                weight: 5
              },
              {
                roleId: 200,
                roleName: '贱民',
                weight: 1
              }
            ],
            delFlag: '0'
          })
        }, 500)
      })
    })
  }
}
</script>

<template>
  <div class="join">
    <div id="voteList" ref="vote-list"></div>
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
