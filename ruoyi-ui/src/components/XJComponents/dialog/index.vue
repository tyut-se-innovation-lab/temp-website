<script>
export default {
  name: 'xj-dialog',
  props: {
    // 是否显示 Dialog，支持 .sync 修饰符
    visible: {
      type: Boolean,
      default: false
    },
    // Dialog 的标题
    title: {
      type: String
    },
    // Dialog 的宽度
    width: {
      type: String,
      default: '50%'
    },
    // 当页面宽度太小时的宽度，如移动端
    mobWidth: {
      type: String,
      default: '80%'
    },
    // Dialog CSS 中的 margin-top 值
    top: {
      type: String,
      default: '15vh'
    },
    // 是否需要遮罩层
    modal: {
      type: Boolean,
      default: true
    },
    // 是否在 Dialog 出现时将滚动锁定
    lockScroll: {
      type: Boolean,
      default: true
    },
    // 是否可以通过点击 modal 关闭 Dialog
    closeOnClickModal: {
      type: Boolean,
      default: true
    },
    // 是否可以通过按下 ESC 关闭 Dialog
    closeOnPressEscape: {
      type: Boolean,
      default: true
    },
    // 是否显示关闭按钮
    showClose: {
      type: Boolean,
      default: true
    },
    // 关闭前的回调，会暂停 Dialog 的关闭
    beforeClose: {
      type: Function
    },
    // 开启 关闭 动画配置
    options: {
      // 数组里是动画的过程，若on长度大于1，则最后位置即为停止位置
      type: Object,
      default () {
        return null
      }
    }
  },
  data () {
    return {
      visibleData: false,
      animation: {
        nowLocation: {},
        default() {
          return {
            speed: 300,
            // transitionTimingFunction: 'ease'
          }
        },
        transitionTimingFunction: 'ease',
        modal: { opacity: 0 }
      },
      timeout: [],
    }
  },
  methods: {
    /**
     * 点击遮罩关闭 Dialog
     */
    closeDialogOnModal () {
      if (this.closeOnClickModal) this.closeDialog()
    },
    /**
     * 点击ESC关闭 Dialog
     */
    closeDialogOnESC (e) {
      if (e.code === 'Escape' || e.key === 'Escape' || e.keyCode === 27) this.closeDialog()
    },
    /**
     * 关闭 Dialog
     */
    closeDialog () {
      // 判断是否传入关闭前执行的回调 before-close
      if (this.beforeClose) this.beforeClose(this.done)
      else this.done()
    },
    /**
     * 关闭 Dialog 实际执行
     */
    done () {
      const _oldTransformData = getComputedStyle(this._dialogMain).transform.replace(")","").split(',')
      const _translateData = [Number(_oldTransformData[4]), Number(_oldTransformData[5])]
      _translateData[1] = 0
      this._dialogMain.style.transform = `${_oldTransformData.slice(0, 4).join(',')}, ${_translateData[0]}, ${_translateData[1]})`
      this.$emit('update:visible', false)
    },
    /**
     * 锁定滚动
     */
    scroll (e) {
      e.preventDefault()
      // return false
    },
    /**
     * dialog 超出页面的滚轮滚动支持
     */
    dialogMainScroll (e) {
      if (this.timeout.length > 0) {
        this.timeout.forEach(item => {
          clearTimeout(item)
        })
        this.timeout = []
      }
      const _dialogMainHeight = this._dialogMain.offsetHeight
      const _screenHeight = window.innerHeight / 4 * 3
      if (_dialogMainHeight < window.innerHeight / 8 * 7) return /* dialog未超出边界，忽略滚动 */
      this._dialogMain.style.transitionTimingFunction = 'ease'
      this._dialogMain.offsetTop
      const _oldTransformData = getComputedStyle(this._dialogMain).transform.replace(")","").split(',')
      const _translateData = [Number(_oldTransformData[4]), Number(_oldTransformData[5])]
      if ((_translateData[1] < e.deltaY * 2 && e.deltaY < 0) || (_translateData[1] > -_dialogMainHeight + _screenHeight + e.deltaY * 2 && e.deltaY > 0))
        /* 正常滚动进入该条件 */
        _translateData[1] -= e.deltaY * 2
      else {
        /* 超出滚动许可范围进入该条件 */
        _translateData[1] -= e.deltaY * 2
        /* 超出后的回滚 */
        this.timeout[0] = setTimeout(() => {
          /* 在顶部 */
          if (e.deltaY < 0) _translateData[1] = 0
          /* 在底部 */
          else _translateData[1] = -_dialogMainHeight + _screenHeight
          this._dialogMain.style.transform = `${_oldTransformData.slice(0, 4).join(',')}, ${_translateData[0]}, ${_translateData[1]})`
        },180)
      }
      this._dialogMain.style.transform = `${_oldTransformData.slice(0, 4).join(',')}, ${_translateData[0]}, ${_translateData[1]})`
    },
    /**
     * dialog 超出页面的触摸滚动支持
     */
    dialogMainTouch () {
      const _uuid = this.uuid
      return {
        _dialogMain: null,
        _dialogMainHeight: null,
        _screenHeight: null,
        _oldTransformData: null,
        _translateData: null,
        _timeout: null,
        _isOneTouch: false,
        _startLocation: 0,
        _oldLocation: 0,
        _newLocation: 0,
        start ({ touches: [touch, touchTwo] }) {
          if (this._timeout) {
            clearTimeout(this._timeout)
            this._timeout = null
          }
          if (typeof touchTwo !== 'undefined') return
          if (this._dialogMainHeight < window.innerHeight / 8 * 7) return
          this._isOneTouch = true
          this._oldLocation = touch.clientY
          this._dialogMain = document.getElementsByClassName(_uuid)[0]
          this._dialogMainHeight = this._dialogMain.offsetHeight
          this._screenHeight = window.innerHeight / 4 * 3
          this._dialogMain.style.transitionTimingFunction = 'ease'
          this._oldTransformData = getComputedStyle(this._dialogMain).transform.replace(")","").split(',')
          this._translateData = [Number(this._oldTransformData[4]), Number(this._oldTransformData[5])]
        },
        move ({ touches: [touch, touchTwo] }) {
          if (typeof touchTwo !== 'undefined') return
          if (this._dialogMainHeight < window.innerHeight / 8 * 7) return /* dialog未超出边界，忽略滚动 */
          this._newLocation = touch.clientY
          const heightOfChange = this._newLocation - this._oldLocation
          this._translateData[1] += heightOfChange
          this._dialogMain.style.transform = `${this._oldTransformData.slice(0, 4).join(',')}, ${this._translateData[0]}, ${this._translateData[1]})`
          this._oldLocation = touch.clientY
        },
        end ({ touches }) {
          if (touches.length > 1) return
          if (this._dialogMainHeight < window.innerHeight / 8 * 7) return
          this._isOneTouch = false
          if (this._translateData[1] > 0) {
            this._translateData[1] = 0
          } else if (this._translateData[1] < -this._dialogMainHeight + this._screenHeight) {
            this._translateData[1] = -this._dialogMainHeight + this._screenHeight
          }
          this.timeout = setTimeout(() => {
            this._dialogMain.style.transform = `${this._oldTransformData.slice(0, 4).join(',')}, ${this._translateData[0]}, ${this._translateData[1]})`
          }, 180)
        }
      }
    }
  },
  computed: {
      trueWidth () {
        return /android|harmonyos|huawei|iphone|ipod|ios|AppleWebKit.*Mobile.*/i.test(navigator.userAgent.toLowerCase()) && window.matchMedia('(orientation: portrait)')['matches'] ? this.mobWidth : this.width
      },
      animationOptions () {
        /* 如果 options 传入为null则设置此默认值 */
        if (this.options === null) {
          return {
            on: [{
              opacity: '0',
              top: 'calc(15vh - 10px)',
              speed: 300
            }],
            off: [{
              opacity: '0',
              top: 'calc(15vh - 10px)',
              speed: 300
            }]
          }
        }
        /* 记录 options 外置 speed 值 */
        let _interval_speed = this.options.speed || 300
        /* 缓存 动画数据 */
        const _options = { on: [], off: []}
        for (let key in _options) {
          if (key !== 'on' && key !== 'off') return
          if (this.options[key]) {
            _options[key] = this.options[key].filter(item=> {
              if (!('speed' in item)) item.speed = _interval_speed
              return item
            })
          } else {
            _options[key] = [{
              opacity: '0',
              top: 'calc(15vh - 10px)',
              speed: 300
            }]
          }
        }
        return _options
      }
  },
  // 监听属性支持异步，所以用监听属性
  watch: {
    /**
     * 监控 visible
     */
    visible: {
      async handler (newV, oldV) {
        // 为了动画流畅度，当设置多个动画节点的动画时，transition-timing-function = 'linear'
        /*
         * 判断是开启 dialog 还是关闭 dialog
         * 用于决定动画播放
         */
        if (newV && !oldV) {
          /* 添加 ESC 关闭事件 */
          if (this.closeOnPressEscape) window.addEventListener('keyup', this.closeDialogOnESC)
          /* 判断是否阻止默认滚动 */
          if (this.lockScroll) {
            window.addEventListener('touchmove', this.scroll, {passive: false})
            window.addEventListener('mousewheel', this.scroll, {passive: false})
          }
          /* 判断执行哪个开启动画，并执行 */
          if (this.animationOptions['on'].length === 0) {
            /* 当 options 长度为0时的动画 */
            this.animation.nowLocation = {}
            this.visibleData = newV
            this.animation.modal.opacity = '1'
          } else if (this.animationOptions['on'].length === 1) {
            /* 当 options 长度为1时的动画 */
            this.animation.nowLocation = this.animationOptions['on'][0]
            this.visibleData = newV
            setTimeout(() => {
              this.animation.nowLocation = {}
              this.animation.transitionTimingFunction = 'ease'
              this.animation.modal.opacity = '1'
              setTimeout(() => {
                this.animation.nowLocation = this.animation.default()
              }, 10)
            }, 10)
            // setTimeout(() => {
            //   this.animation.nowLocation = this.animation.default()
            //   this.animation.transitionTimingFunction = 'ease'
            // }, this.animation.nowLocation['speed'])
          } else {
            /* 当 options 长度为大于1时的动画，此时width，top等属性的值可能被忽视 */
            /* 为了动画流畅度，当设置多个动画节点的动画时，transition-timing-function = 'linear' */
            this.animation.transitionTimingFunction = 'linear'
            /* 动画延迟计时 */
            let timeout = 0;
            /* 遍历动画设置的值，逐个动画节点进行设置 */
            this.animationOptions['on'].forEach((item, index) => {
              setTimeout(() => {
                this.animation.nowLocation = item
                if (index === 0) setTimeout(() => {
                  this.visibleData = newV
                  setTimeout(() => this.animation.modal.opacity = '1', 10)
                }, 10)
              }, timeout)
              timeout += item.speed
            })
            setTimeout(() => {
              this.animation.nowLocation = this.animation.default()
              this.animation.transitionTimingFunction = 'ease'
            }, timeout)
          }
        } else {
          /* 移除 ESC 关闭事件 */
          window.removeEventListener('keyup', this.closeDialogOnESC)
          if (this.lockScroll) {
            window.removeEventListener('touchmove', this.scroll)
            window.removeEventListener('mousewheel', this.scroll)
          }
          if (this.animationOptions['off'].length === 0) {
            this.animation.nowLocation = {}
            this.visibleData = newV
            this.animation.modal.opacity = '0'
          } else if (this.animationOptions['off'].length === 1) {
            this.animation.nowLocation = this.animationOptions['off'][0]
            this.animation.modal.opacity = '0'
            setTimeout(() => {
              this.visibleData = newV
              this.animation.transitionTimingFunction = 'ease'
              this.animation.nowLocation = this.animation.default()
            }, this.animationOptions['off'][0].speed)
          } else {
            this.animation.transitionTimingFunction = 'linear'
            this.animation.modal.opacity = '0'
            let timeout = 0;
            this.animationOptions['off'].forEach((item, index) => {
              setTimeout(() => {
                this.animation.nowLocation = item
                if (index === this.animationOptions['off'].length - 1) {
                  setTimeout(() => this.visibleData = newV, 10)
                }
              }, timeout)
              timeout += item.speed
            })
            setTimeout(() => {
              this.animation.transitionTimingFunction = 'ease'
              this.animation.nowLocation = this.animation.default()
            }, timeout)
          }
        }
      }
    }
  },
  mounted () {
    this._dialogModal = this.$refs['dialog-modal']
    this._dialogMain = this.$refs['dialog-main']
    this._dialogMain.classList.add(this.uuid)
    /* 添加dialog滚轮滚动事件 */
    const touchOperate = this.dialogMainTouch()
    this._dialogModal.addEventListener('wheel', this.dialogMainScroll)
    this._dialogMain.addEventListener('wheel', this.dialogMainScroll)
    this._dialogMain.addEventListener('touchstart', touchOperate.start)
    this._dialogMain.addEventListener('touchmove', touchOperate.move)
    this._dialogMain.addEventListener('touchend', touchOperate.end)
  },
  // destroyed() {
  //
  // }
}
</script>

<template>
  <div
    id="dialog"
    v-show="visibleData"
    ref="dialog-root"
    :style="{
      left: '100%'
    }"
  >
    <!--  遮罩层  -->
    <div
      class="dialog-modal"
      ref="dialog-modal"
      v-show="modal"
      @click="closeDialogOnModal"
      :style="animation.modal"
    ></div>
    <!--  主体  -->
    <div
      class="dialog-main"
      ref="dialog-main"
      :style="{
        marginTop: animation.nowLocation['top'] || top,
        width: /*animation.nowLocation['width'] ||*/ trueWidth,
        left: animation.nowLocation['left'] || '50%',
        transform: animation.nowLocation['transform'] || 'translateX(-50%)',
        transformOrigin: animation.nowLocation['transform-origin'] || 'left center',
        opacity: animation.nowLocation['opacity'] || '1',
        transition: animation.nowLocation['transition'] || 'all',
        transitionDuration: animation.nowLocation.speed / 1000 + 's' || '.3s',
        transitionTimingFunction: animation.transitionTimingFunction
      }"
    >
      <!--   标题   -->
      <div
        class="dialog-title"
        ref="dialog-title"
      >
        <div><span v-html="title"></span></div>
      </div>
      <!--   内容  -->
      <div
        class="dialog-body"
        ref="dialog-body"
      >
        <div><slot></slot></div>
      </div>
      <!--   页脚   -->
      <div
        class="dialog-footer"
        ref="dialog-footer"
      >
        <div><slot name="footer"></slot></div>
      </div>
      <!--  右上角自带关闭按钮  -->
      <div
        class="dialog-closeButton"
        ref="dialog-closeButton"
        v-show="showClose"
        @click="closeDialog"
      >
        <span class="iconfont icon-close"></span>
      </div>
    </div>
  </div>
</template>

<style scoped>
@import "./assets/iconfont.css";
#dialog {
  position: fixed;
  z-index: 1500;
  top: 0;
  left: 0;
  height: 0;
  width: 0;
}

.dialog-modal {
  position: fixed;
  top: 0;
  left: 0;
  height: 100vh;
  width: 100vw;
  background: #00000066;
  transition: all .15s;
}

.dialog-main {
  position: fixed;
  top: 0;
  left: 50%;
  transform: translateX(-50%);
  width: 50%;
  background: #ffffff;
  border-radius: 5px;
  box-shadow: 0 0 4px #00000055;
  margin-bottom: 15vh;
  overflow: hidden;
}

.dialog-title {
  font-size: 18px;
  color: #303133;
  padding: 20px 20px 10px;
}


.dialog-body {
  font-size: 14px;
  color: #606266;
  padding: 30px 20px;
}

.dialog-footer {
  font-size: 1.8em;
  padding: 10px 20px 20px;
}

.dialog-footer div {
  float: right;
}

.dialog-footer::after {
  content: "";
  height: 0;
  clear: both;
  overflow: hidden;
  display: block;
  visibility: hidden;
}

.dialog-closeButton {
  position: absolute;
  top: 10px;
  right: 10px;
  cursor: pointer;
  transition: all .2s;
  transform: scale(60%);
  border-radius: 50%;
  width: 20px;
  height: 20px;
  text-align: center;
  line-height: 20px;
  background: #fd4356;
  color: #00000000;
}

.dialog-closeButton:hover {
  color: #000000dd;
}
</style>
