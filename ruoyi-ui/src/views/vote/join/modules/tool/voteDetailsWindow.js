/** 点击粗略节点后显示的详细节点 */
import './voteDetailsWindow.css'

class VoteDetailsWindowInfo {
  /** {HTMLDivElement} voteNode 详细投票节点 */
  _voteNode
  /** {HTMLDivElement} _roughVoteNode 粗略投票节点 */
  _roughVoteNode
  /** {VoteRoughList} voteRoughList VoteRoughList实例 */
  _voteRoughList
  height
  _upLong
}

class VoteDetailsWindow extends VoteDetailsWindowInfo {

  /**
   *
   * @param {HTMLDivElement} roughVoteNode 粗略投票节点
   * @param {number} upLong 粗略投票节点上移距离，用于详细节点位置校准
   * @param {Object} info 详细信息
   * @param {VoteRoughList} voteRoughList VoteRoughList实例
   */
  constructor (roughVoteNode, upLong, info, voteRoughList) {
    super()
    this._voteRoughList = voteRoughList
    this._roughVoteNode = roughVoteNode
    this._upLong = upLong
    this.#create()
    info
  }

  /**
   * 创建节点
   */
  #create () {
    this._voteNode = document.createElement('div')
    this._voteNode.classList.add('xj-details-vote-node')
    this._voteNode.style['top'] = - this._upLong + 'px'
    this._roughVoteNode.appendChild(this._voteNode)
    this.height = parseFloat(getComputedStyle(this._voteNode).height)
    this.#appear()
    this._voteNode.addEventListener('click', () => {
      this.#disappear()
    })
  }

  /**
   * 节点出现
   */
  #appear () {
    requestAnimationFrame(() => {
      this._voteNode.style['opacity'] = 1
      this._voteNode.style['top'] = 'calc(100% + 10px)'
    })
  }

  /**
   * 节点消失
   */
  #disappear () {
    this._voteNode.style['opacity'] = 0
    this._voteNode.style['top'] = - this._upLong + 'px'
    setTimeout(() => {
      this._voteRoughList.nodeClickFinish()
    }, 10)
    setTimeout(() => {
      this._voteNode.remove()
    }, 600)
  }

}

export default VoteDetailsWindow
