/**
 * 创建粗略投票信息
 */
import './roughVote.scss'
import store from '@/store'
import auth from '@/plugins/auth'
// import Notification from '@/plugins/modules/xjTool/xj-notification'

class RoughInfo {
  /** {number} voteId 投票ID */
  voteId
  /** {string} title 标题 */
  title
  /** {string} content 内容 */
  content
  /** {number} status 状态 */
  status
  /** {string} createTime 创建时间 */
  createTime
  /** {string} deadTime 结束时间 */
  deadTime
  /** {string} userName 发起者 */
  userName
  /** {number} userId 发起者ID */
  userId
  /** {number} loginUserId 当前用户ID */
  loginUserId
  /** {HTMLDivElement} voteNode 粗略投票节点 */
  voteNode = null
  /** {HTMLDivElement} voteNodeMain 粗略投票主节点 */
  voteNodeMain = null
  /** {HTMLDivElement} voteNodeButton 粗略投票按钮节点 */
  voteNodeButton = null
  /** {number} ranking 排名 */
  ranking
  /** {VoteRoughList} VoteRoughList 实例 */
  VoteRoughList
}

const scroll = (e) => {
  e.preventDefault()
}

class VoteRough extends RoughInfo {
  /**
   * @param {number} voteId 投票ID
   * @param {string} title 标题
   * @param {string} content 内容
   * @param {number} status 状态
   * @param {string} createTime 创建时间
   * @param {string} deadTime 结束时间
   * @param {string} userName 发起者
   * @param {number} userId 发起者ID
   * @param {VoteRoughList} VoteRoughList 实例
   */
  constructor ({ voteId, title, content, status, createTime, deadTime, userName, userId }, VoteRoughList) {
    super()
    this.voteId = voteId
    this.title = title
    this.content = content
    this.status = status
    this.createTime = createTime
    this.deadTime = deadTime
    this.userName = userName
    this.userId = userId
    this.loginUserId = store.getters.userId
    this.VoteRoughList = VoteRoughList
    this.#create()
  }

  /**
   * 创建粗略投票节点
   */
  #create () {
    /* 创建节点 */
    this.voteNode = document.createElement('div')
    this.voteNode.classList.add('xj-rough-vote-node')
    this.voteNode.classList.add('xj-' + this.VoteRoughList.equipment)
    /* 创建主显示节点 */
    this.voteNodeMain = document.createElement('div')
    this.voteNodeMain.classList.add('xj-rough-vote-main')
    this.voteNodeMain.classList.add('xj-' + this.VoteRoughList.equipment)
    this.voteNode.appendChild(this.voteNodeMain)
    /* 创建左滑按钮节点节点 */
    if (this.VoteRoughList.equipment === 'mobile') {
      this.voteNodeButton = document.createElement('div')
      this.voteNodeButton.classList.add('xj-rough-vote-button')
      this.voteNodeButton.classList.add('xj-' + this.VoteRoughList.equipment)
      this.voteNode.appendChild(this.voteNodeButton)
    }
    /* 创建标题 */
    const titleBox = document.createElement('div')
    titleBox.classList.add('xj-rough-vote-title')
    titleBox.classList.add('xj-' + this.VoteRoughList.equipment)
    titleBox.appendChild(document.createTextNode(this.title))
    this.voteNodeMain.appendChild(titleBox)
    /* 创建文本 */
    const contentBox = document.createElement('div')
    contentBox.classList.add('xj-rough-vote-content')
    contentBox.classList.add('xj-' + this.VoteRoughList.equipment)
    contentBox.appendChild(document.createTextNode(this.content))
    this.voteNodeMain.appendChild(contentBox)
    /* 判断类型，设置颜色和右侧显示内容 */
    let voteNodeBackgroundColor = null
    let voteNodeBackgroundColorHover = null
    let voteNodeColor = null
    let firstText = null
    switch (this.status) {
      case 1:
        voteNodeBackgroundColor = '#beffa4b2'
        voteNodeBackgroundColorHover = '#beffa4'
        voteNodeColor = '#000000'
        firstText = '进行中 ' + '至 ' +  this.deadTime.slice(0, 16).replace(/-/g, '/')
        break
      case 2:
        voteNodeBackgroundColor = '#d0d0d0b2'
        voteNodeBackgroundColorHover = '#d0d0d0'
        voteNodeColor = '#000000a2'
        firstText = '已结束'
        break
      case 3:
        voteNodeBackgroundColor = '#ffcb7db2'
        voteNodeBackgroundColorHover = '#ffcb7d'
        voteNodeColor = '#000000a2'
        firstText = '举报冻结 ' + '至 ' +  this.deadTime.slice(0, 16).replace(/-/g, '/')
        break
      case 4:
        voteNodeBackgroundColor = '#ffcb7db2'
        voteNodeBackgroundColorHover = '#ffcb7d'
        voteNodeColor = '#000000a2'
        firstText = '请求撤回 ' + '至 ' +  this.deadTime.slice(0, 16)
        break
      case 5:
        voteNodeBackgroundColor = '#d0d0d0b2'
        voteNodeBackgroundColorHover = '#d0d0d0'
        voteNodeColor = '#000000a2'
        firstText = '已关闭'
        break
      default:
        new Error('不存在的投票状态')
    }
    /* 创建颜色 */
    this.voteNodeMain.style['color'] = voteNodeColor
    this.voteNode.style.setProperty('--backgroundC', voteNodeBackgroundColor)
    this.voteNode.style.setProperty('--backgroundCH', voteNodeBackgroundColorHover)
    // this.voteNode.style['border-color'] = '#00000000'
    /* 创建右侧第一行文本 */
    const firstTextBox = document.createElement('div')
    firstTextBox.classList.add('xj-rough-vote-firstText')
    firstTextBox.classList.add('xj-' + this.VoteRoughList.equipment)
    firstTextBox.appendChild(document.createTextNode(firstText))
    this.voteNodeMain.appendChild(firstTextBox)
    /* 创建右侧发起人文本 */
    const userNameBox = document.createElement('div')
    userNameBox.classList.add('xj-rough-vote-userName')
    userNameBox.classList.add('xj-' + this.VoteRoughList.equipment)
    userNameBox.appendChild(document.createTextNode('由 <' + this.userName + '> 发起'))
    this.voteNodeMain.appendChild(userNameBox)
    this.#runCreateEvent()
    this.#addEvent()
  }

  /** 创建监听时触发的方法 */
  #createEvent () {

    /* 生成上下文菜单内容 */
    let contextmenu = null
    const contextmenuList = []
    if (this.status === 1 && this.userId === this.loginUserId) {
      /* 撤回 */
      contextmenuList.push({
        message: '撤回',
        onclick: () => {
          console.log('撤回')
        }
      })
    } else if (this.status === 1 && this.userId !== this.loginUserId) {
      /* 举报 */
      contextmenuList.push({
        message: '举报',
        onclick: () => {
          console.log('举报')
        }
      })
    } else if (this.status === 2 && auth.hasPermiOr(['vote:history:delete'])) {
      contextmenuList.push({
        message: '删除',
        onclick: () => {
          console.log('删除')
        }
      })
    } else if (this.status === 3 && auth.hasPermiOr(['vote:error:dispose'])) {
      contextmenuList.push({
        message: '处理',
        onclick: () => {
          console.log('处理举报冻结')
        }
      })
    } else if (this.status === 4 && auth.hasPermiOr(['vote:error:dispose'])) {
      contextmenuList.push({
        message: '处理',
        onclick: () => {
          console.log('处理撤回')
        }
      })
    }
    /*contextmenuList.push({
      message: '上下文菜单测试',
      onclick: () => {
        const notification = new Notification({
          title: '上下文菜单测试',
          message: '上下文菜单测试',
          callback: v => {
            console.log(v)
            return v
          }
        })
        notification
          .then(v => {
            console.log(v)
          })
          .catch(err => {
            console.error(err.message)
          })
      }
    })*/

    /* 生成左滑按钮 */
    const touchInfo = {
      isTouch: false,
      x: 0,
      exist: false,
      isClick: false,
      startTouch: { x: 0, y: 0 , isFirst: true}
    }
    if (this.voteNodeButton && contextmenuList.length > 0) {
      this.voteNodeButton.appendChild(document.createTextNode(contextmenuList[0]['message']))
      this.voteNodeButton.addEventListener('click', (e) => {
        e.stopPropagation()
        contextmenuList[0]['onclick']()
      })
      touchInfo['exist'] = true
    } else if (this.voteNodeButton) {
      this.voteNodeButton.appendChild(document.createTextNode('什么都没有'))
    }


    return {
      click: (e) => {
        if (e.button !== 0) return
        this.VoteRoughList._nodeClick(this.ranking, { voteId: this.voteId })
      },
      contextmenu: (e) => {
        e.preventDefault()

        contextmenu = this.VoteRoughList._contextmenu({
          contextmenuList
        })
        contextmenu.appear(e.clientX, e.clientY)

        const closeContextmenu = () => {
          contextmenu.disappear()
          window.removeEventListener('mouseup', closeContextmenu)
          window.removeEventListener('scroll', closeContextmenu)
        }

        window.addEventListener('mouseup', closeContextmenu)
        window.addEventListener('scroll', closeContextmenu)
      },
      mouseenter: () => {

      },
      mouseleave: () => {

      },
      touchstart: (e) => {
        /* 触控点大于1直接退出 */
        if (e.touches.length !== 1 || !this.voteNodeButton) return
        /* 记录开始点击位置 */
        touchInfo['x'] = parseFloat(e.touches[0].clientX)
        touchInfo['startTouch']['x'] = parseFloat(e.touches[0].clientX)
        touchInfo['startTouch']['y'] = parseFloat(e.touches[0].clientY)
      },
      touchmove: (e) => {
        /* 触控点大于1直接退出 */
        if (e.touches.length !== 1 || !this.voteNodeButton) return
        /* 初次move判定时 */
        if (!touchInfo['isTouch'] && touchInfo['startTouch']['isFirst']) {
          touchInfo['startTouch']['isFirst'] = false
          /* 如果横向位移大于纵向位移，则 isTouch = true ，button 可以呼出
           * 否则直接退出
           */
          if (Math.abs(touchInfo['startTouch']['x'] - parseFloat(e.touches[0].clientX)) > Math.abs(touchInfo['startTouch']['y'] - parseFloat(e.touches[0].clientY))) {
            touchInfo['isTouch'] = true
            window.addEventListener('touchmove', scroll, { passive: false })
          }
          else return
        } else if (!touchInfo['isTouch']) return

        /* isTouch = true 时
         * 当左滑小于一半时，跟手滑动
         * 当没有任何操作时，为优化手感，全程跟手滑动
         */
        if (parseFloat(getComputedStyle(this.voteNodeMain)['left']) >= -parseFloat(getComputedStyle(this.voteNodeMain)['width']) / 2 || !touchInfo['exist']) {
          this.voteNodeMain.style['transition'] = 'none'
          this.voteNodeButton.style['transition'] = 'background-color .1s, color .1s'
          const buttonLeft = parseFloat(getComputedStyle(this.voteNodeMain)['left'])
          this.voteNodeMain.style['left'] = buttonLeft - touchInfo['x'] + parseFloat(e.touches[0].clientX) + 'px'
          // touchInfo['x'] = parseFloat(e.touches[0].clientX)
          this.voteNodeButton.style['width'] = - buttonLeft - 10 + 'px'
          if (parseFloat(getComputedStyle(this.voteNodeMain)['left']) > -parseFloat(getComputedStyle(this.voteNodeMain)['width']) / 3 && parseFloat(getComputedStyle(this.voteNodeMain)['left']) < 0) {
            this.voteNodeButton.style['opacity'] = buttonLeft / (-parseFloat(getComputedStyle(this.voteNodeMain)['width']) / 3) + ''
          } else if (parseFloat(getComputedStyle(this.voteNodeMain)['left']) > 0) {
            this.voteNodeButton.style['opacity'] = '0'
          } else {
            this.voteNodeButton.style['opacity'] = '1'
          }
          this.voteNodeButton.style['transition'] = 'background-color .1s, color .1s'
          touchInfo['isClick'] = false
        }
        /* 当左滑大于一半时 */
        else if (parseFloat(getComputedStyle(this.voteNodeMain)['left']) < -parseFloat(getComputedStyle(this.voteNodeMain)['width']) / 2 && touchInfo['exist']) {
          /* 左滑则选中，跳至最左 */
          if (touchInfo['x'] > parseFloat(e.touches[0].clientX)) {
            touchInfo['left'] = parseFloat(e.touches[0].clientX)
            if (!touchInfo['isClick']) {
              this.voteNodeMain.style['transition'] = 'all .3s'
              this.voteNodeButton.style['transition'] = 'background-color .1s, color .1s, width .3s'
              this.voteNodeMain.style['left'] = -parseFloat(getComputedStyle(this.voteNodeMain)['width']) - 30 + 'px'
              this.voteNodeButton.style['width'] = parseFloat(getComputedStyle(this.voteNodeMain)['width']) + 20 + 'px'
              window.navigator.vibrate && window.navigator.vibrate(100)
              this.voteNodeButton.style['opacity'] = '1'
              touchInfo['isClick'] = true
            }
          }
          /* 右滑则取消选中，跳至中间位置 */
          else {
            /* 为优化手感，当右滑距离大于40px时执行 */
            if (touchInfo['left'] + 40 < parseFloat(e.touches[0].clientX)) {
              this.voteNodeMain.style['left'] = -parseFloat(getComputedStyle(this.voteNodeMain)['width']) / 2 + 'px'
              this.voteNodeButton.style['width'] = parseFloat(getComputedStyle(this.voteNodeMain)['width']) / 2 - 10 + 'px'
              touchInfo['isClick'] = false
            }
          }
        }
        /* 更新当前x位置 */
        touchInfo['x'] = parseFloat(e.touches[0].clientX)
      },
      touchend: () => {
        if (!this.voteNodeButton) return
        /* 恢复原位置 */
        touchInfo['isTouch'] = false
        touchInfo['startTouch']['isFirst'] = true
        window.removeEventListener('touchmove', scroll)
        this.voteNodeMain.style['transition'] = 'all .3s'
        this.voteNodeButton.style['transition'] = 'background-color .1s, color .1s, width .3s, opacity .3s'
        this.voteNodeMain.style['left'] = '0'
        this.voteNodeButton.style['width'] = '0'
        this.voteNodeButton.style['opacity'] = '0'
        /* 若选中，则触发按钮 */
        if (touchInfo['isClick']) this.voteNodeButton.click()
      },
    }
  }

  /**
   * 创建事件
   */
  #runCreateEvent () {
    this.#event = this.#createEvent()
  }

  /** 存放监听时触发的方法 */
  #event = null

  /**
   * 添加事件
   */
  #addEvent () {
    for (let key in this.#event) {
      this.voteNode.addEventListener(key, this.#event[key])
    }
    this.voteNode.style['cursor'] = 'pointer'
  }

  /**
   * 删除事件
   */
  #removeEvent () {
    for (let key in this.#event) {
      this.voteNode.removeEventListener(key, this.#event[key])
    }
    this.voteNode.style['cursor'] = 'default'
  }

  /**
   * 添加事件
   */
  addEvent () {
    this.#addEvent()
  }

  /**
   * 删除事件
   */
  removeEvent () {
    this.#removeEvent()
  }

  /**
   * 设置位置
   * @param {string} location
   */
  setLocation (location) {
    this.voteNode.style['top'] = location
  }

  /**
   * 获取节点dom
   */
  getVoteNode () {
    return this.voteNode
  }

  /**
   * 设置 排名
   * @param {number} ranking
   */
  set ranking (ranking) {
    this.ranking = ranking
  }

  /**
   * 获取 排名
   * @returns {number} ranking
   */
  get ranking () {
    return this.ranking
  }

}

export default VoteRough
