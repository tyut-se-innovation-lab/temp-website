/**
 * 创建粗略投票信息
 */
import './roughVote.css'

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
  /** {string} userId 发起者ID */
  userId
  /** {HTMLDivElement} voteNode 粗略投票节点 */
  voteNode
  /** {number} ranking 排名 */
  ranking
  /** {VoteRoughList} VoteRoughList 实例 */
  VoteRoughList
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
   * @param {string} userId 发起者ID
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
    this.voteNode.classList.add(this.VoteRoughList.equipment)
    /* 创建主显示节点 */
    const voteNodeMain = document.createElement('div')
    voteNodeMain.classList.add('xj-rough-vote-main')
    voteNodeMain.classList.add(this.VoteRoughList.equipment)
    this.voteNode.appendChild(voteNodeMain)
    /* 创建左滑按钮节点节点 */
    const voteNodeButton = document.createElement('div')
    voteNodeButton.classList.add('xj-rough-vote-button')
    voteNodeButton.classList.add(this.VoteRoughList.equipment)
    this.voteNode.appendChild(voteNodeButton)
    /* 创建标题 */
    const titleBox = document.createElement('div')
    titleBox.classList.add('xj-rough-vote-title')
    titleBox.classList.add(this.VoteRoughList.equipment)
    titleBox.appendChild(document.createTextNode(this.title))
    voteNodeMain.appendChild(titleBox)
    /* 创建文本 */
    const contentBox = document.createElement('div')
    contentBox.classList.add('xj-rough-vote-content')
    contentBox.classList.add(this.VoteRoughList.equipment)
    contentBox.appendChild(document.createTextNode(this.content))
    voteNodeMain.appendChild(contentBox)
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
        firstText = '进行中 ' + '至 ' +  this.deadTime.slice(0, 16)
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
        firstText = '举报冻结 ' + '至 ' +  this.deadTime.slice(0, 16)
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
    voteNodeMain.style['color'] = voteNodeColor
    this.voteNode.style.setProperty('--backgroundC', voteNodeBackgroundColor)
    this.voteNode.style.setProperty('--backgroundCH', voteNodeBackgroundColorHover)
    this.voteNode.style['border-color'] = '#00000000'
    /* 创建右侧第一行文本 */
    const firstTextBox = document.createElement('div')
    firstTextBox.classList.add('xj-rough-vote-firstText')
    firstTextBox.classList.add(this.VoteRoughList.equipment)
    firstTextBox.appendChild(document.createTextNode(firstText))
    voteNodeMain.appendChild(firstTextBox)
    /* 创建右侧发起人文本 */
    const userNameBox = document.createElement('div')
    userNameBox.classList.add('xj-rough-vote-userName')
    userNameBox.classList.add(this.VoteRoughList.equipment)
    userNameBox.appendChild(document.createTextNode('由 <' + this.userName + '> 发起'))
    voteNodeMain.appendChild(userNameBox)

    this.#addEvent()
  }

  /** 创建监听时触发的方法 */
  #createEvent () {
    return {
      click: (e) => {
        this.VoteRoughList._nodeClick(this.ranking, { voteId: this.voteId })
      },
      contextmenu: () => {

        return false
      },
      mouseenter: () => {

      },
      mouseleave: () => {

      },
      touchstart: () => {

      },
      touchmove: () => {

      },
      touchend: () => {

      },
    }
  }

  /** 存放监听时触发的方法 */
  #event = this.#createEvent()

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
