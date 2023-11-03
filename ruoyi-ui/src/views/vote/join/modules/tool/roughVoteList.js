/**
 * 管理所有粗略投票节点
 */
import VoteRough from './createRoughVote'
import VoteDetailsWindow from './voteDetailsWindow'

class RoughListInfo {
  /** {HTMLDivElement} RoughList 列表元素 */
  _RoughList
  /** {List} voteInfos 全部投票粗略信息 */
  _voteInfos
  /** {List} 投票节点 */
  _voteNode = []
  /** {number} roughListHeight 列表高度 */
  _roughListHeight
  /** {number} ROUGH_HEIGHT 投票元素高度 */
  _ROUGH_HEIGHT = 80
  /** {string} _equipment 设备类型 */
  equipment
  /** {Function} _onclick 单个粗略节点被点击时调用的函数 */
  _onclick = null
}

const scroll = (e) => {
  e.preventDefault()
}

class VoteRoughList extends RoughListInfo {
  /**
   * @param {HTMLDivElement} RoughList 列表元素 div
   * @param {[]} voteInfos 全部投票粗略信息
   * @param {'desktop'|'mobile'} equipment 设备类型
   */
  constructor (RoughList, voteInfos, equipment = 'desktop') {
    super()
    this._RoughList = RoughList
    this._voteInfos = voteInfos
    this.equipment = equipment
    this.equipment === 'mobile' && (this._ROUGH_HEIGHT = 160)
    this.#initialization()
  }

  /**
   * 初始化
   */
  #initialization () {
    this._RoughList.style['width'] = '100%'
    this._RoughList.style['position'] = 'relative'
    this._RoughList.style['overflow'] = 'hidden'
    this.#roughListHeightUpdate()
    for (let i = 0; i < this._voteInfos.length; ++i) {
      this._voteNode[i] = new VoteRough(this._voteInfos[i], this)
      this._RoughList.appendChild(this._voteNode[i].getVoteNode())
      this._voteNode[i].setLocation(i * (this._ROUGH_HEIGHT + 16) + 16 + 'px')
      this._voteNode[i].ranking = i + 1
    }
  }

  /**
   * 更新列表高度
   */
  #roughListHeightUpdate () {
    this._roughListHeight = this._voteInfos.length * (this._ROUGH_HEIGHT + 16) + this._ROUGH_HEIGHT
    this._RoughList.style['height'] = this._roughListHeight + 'px'
  }

  /** {{
      detailsVoteInfo,
      topLeaveSpace,
      upLong,
      voteDetailsWindow,
      downLong
    }} 被点击的粗略vote信息 */
  #clickVoteInfo

  /**
   * 某个投票节点被点击时触发
   * 调整附近节点用于留空放置详细信息
   * @param {number} ranking 排名
   * @param {{voteId: number}} voteInfo 粗略投票信息
   * @return null
   */
  async _nodeClick (ranking, voteInfo) {
    let detailsVoteInfo = null
    let data = null
    if (this._onclick) {
      detailsVoteInfo = this._onclick(voteInfo)
      await detailsVoteInfo.then((v) => {
        data = v
      })
    }

    const topLeaveSpace = parseInt(getComputedStyle(document.getElementById('tags-view-container')).height) + 50
    /* 上移px */
    const upLong = this._voteNode[ranking - 1].voteNode.getBoundingClientRect().top - topLeaveSpace - this._ROUGH_HEIGHT - 30 + (this.equipment === 'mobile' ? this._ROUGH_HEIGHT / 3 * 2 : 0)

    const voteDetailsWindow = new VoteDetailsWindow(this._voteNode[ranking - 1].voteNode, upLong, data, this)
    /* 下移px */
    const downLong = voteDetailsWindow.height - upLong + 8

    this.#clickVoteInfo = {
      ranking,
      detailsVoteInfo,
      topLeaveSpace,
      upLong,
      voteDetailsWindow,
      downLong
    }

    window.addEventListener('mousewheel', scroll, {passive: false})
    window.addEventListener('touchmove', scroll, {passive: false})
    document.body.style['overflow-y'] = 'hidden'

    const num = Math.floor(window.innerHeight / (this._ROUGH_HEIGHT + 16))
    for (let i = 0; i < num; ++i) {
      if (!this._voteNode[ranking - i - 1]) break
      this._voteNode[ranking - i - 1].setLocation((ranking - i - 2) * (this._ROUGH_HEIGHT + 16) + 16 - upLong + 'px')
      this._voteNode[ranking - i - 1].removeEvent()
    }
    for (let i = 0; i < num; ++i) {
      if (!this._voteNode[ranking + i]) break
      this._voteNode[ranking + i].setLocation((ranking + i - 1) * (this._ROUGH_HEIGHT + 16) + 16 + downLong + 'px')
      this._voteNode[ranking + i].removeEvent()
    }
  }

  /**
   * 投票节点点击结束关闭详细信息
   */
  nodeClickFinish () {
    const { ranking } = this.#clickVoteInfo
    window.removeEventListener('mousewheel', scroll)
    window.removeEventListener('touchmove', scroll)
    document.body.style['overflow-y'] = 'scroll'

    const num = Math.floor(window.innerHeight / (this._ROUGH_HEIGHT + 16))
    for (let i = 0; i < num; ++i) {
      if (!this._voteNode[ranking - i - 1]) break
      this._voteNode[ranking - i - 1].setLocation((ranking - i - 1) * (this._ROUGH_HEIGHT + 16) + 16 + 'px')
      this._voteNode[ranking - i - 1].addEvent()
    }
    for (let i = 0; i < num; ++i) {
      if (!this._voteNode[ranking + i]) break
      this._voteNode[ranking + i].setLocation((ranking + i) * (this._ROUGH_HEIGHT + 16) + 16 + 'px')
      this._voteNode[ranking + i].addEvent()
    }
  }

  /**
   * 设置单个粗略节点被点击时调用的函数
   */
  onclick (fun) {
    this._onclick = fun
  }

  /**
   * 延长投票列表(瀑布流更新)
   * @param {List} voteInfos 新增的投票粗略信息
   */
  addVotes (voteInfos) {
    voteInfos
  }
}

export default VoteRoughList
