/** 点击粗略节点后显示的详细节点 */
import './voteDetailsWindow.scss'

class VoteDetailsWindowInfo {
  /** {HTMLDivElement} voteNode 详细投票节点 */
  _voteNode
  /** {HTMLDivElement} main 详细投票主信息节点 */
  limb = {
    _main: null,
    _title: null,
    _closeButton: null,
    _voteOption: null,
    _submit: null,
    _rulesRemainingQuantity: null,
    _rulesWeights: null,
    _weightDom: null,
    _weightCloseButton: null,
    _weightList: null
  }
  /** {HTMLDivElement} _roughVoteNode 粗略投票节点 */
  _roughVoteNode
  /** {number} upLong 粗略投票节点上移距离，用于详细节点位置校准 */
  _upLong
  /** {Object} info 详细信息 */
  _info
  /** {VoteRoughList} voteRoughList VoteRoughList实例 */
  _voteRoughList
  /** {number} height 详细投票节点高度 */
  height
  /** {boolean} 是否已经销毁 */
  isDisappear = false
}

class VoteDetailsWindow extends VoteDetailsWindowInfo {

  /**
   * @param {HTMLDivElement} roughVoteNode 粗略投票节点
   * @param {number} upLong 粗略投票节点上移距离，用于详细节点位置校准
   * @param {Object} info 详细信息
   * @param {VoteRoughList} voteRoughList VoteRoughList实例
   */
  constructor (roughVoteNode, upLong, info, voteRoughList) {
    super()
    this._voteRoughList = voteRoughList
    this._upLong = upLong
    this._info = info
    this._roughVoteNode = roughVoteNode
    console.log(info)
    this.#create()
    this.#runCreateEvent()
    this.#addEvent(['_main', '_closeButton', '_voteOption', '_rulesWeights', '_weightCloseButton'])
  }

  /**
   * @description 创建节点
   */
  #create () {
    this._voteNode = document.createElement('div')
    this._voteNode.classList.add('xj-details-vote-node')
    this._voteNode.style['top'] = - this._upLong + 'px'
    /* 创建标题节点 */
    this.limb._title = document.createElement('div')
    this.limb._title.classList.add('xj-details-vote-title')
    this.limb._title.appendChild(document.createTextNode(this._info['title']))
    this._voteNode.appendChild(this.limb._title)
    this._roughVoteNode.appendChild(this._voteNode)
    /* 创建规则节点 */
    const rules = document.createElement('div')
    rules.classList.add('xj-details-vote-rules')
    if (this._info['status'] === 1) {
      this.limb._rulesRemainingQuantity = document.createElement('div')
      this.limb._rulesRemainingQuantity.classList.add('xj-details-vote-remainder')
      this.limb._rulesRemainingQuantity.innerHTML = `剩余可选: ${ this._info['optionNum'] } 个`
    }
    this.limb._rulesWeights = document.createElement('div')
    this.limb._rulesWeights.classList.add('xj-details-vote-weights')
    this.limb._rulesWeights.innerHTML = `角色权重 >`
    rules.appendChild(this.limb._rulesRemainingQuantity)
    rules.appendChild(this.limb._rulesWeights)
    this.limb._weightDom = this.#createWeight()
    /* 创建关闭节点 */
    this.limb._closeButton = document.createElement('div')
    this.limb._closeButton.classList.add('xj-details-vote-close')
    const closeButtonSpan = document.createElement('span')
    closeButtonSpan.classList.add('xj-iconfont')
    closeButtonSpan.classList.add('xj-icon-close')
    this.limb._closeButton.appendChild(closeButtonSpan)
    this.limb._title.appendChild(this.limb._closeButton)

    /* 创建主节点 */
    this.limb._main = document.createElement('div')
    this.limb._main.classList.add('xj-details-vote-main')
    this.limb._main.style['top'] = `calc(2% + ${ parseFloat(getComputedStyle(this.limb._title)['height']) }px + 28px)`

    /* 创建content节点 */
    const content = document.createElement('p')
    content.appendChild(document.createTextNode(this._info['content']))
    /* 创建投票选项节点 */
    const voteOptions = this.#createVoteOptions()
    /* 创建确认节点 */
    if (this._info['status'] === 1) {
      this.limb._submit = this.#createSubmit()
      this.limb._main.appendChild(this.limb._submit)
    }
    this.limb._title.appendChild(rules)
    this.limb._main.appendChild(content)
    this.limb._main.appendChild(voteOptions)
    this._voteNode.appendChild(this.limb._main)
    this._voteNode.appendChild(this.limb._weightDom)

    // this._roughVoteNode.appendChild(this._voteNode)
    this.height = parseFloat(getComputedStyle(this._voteNode)['height'])
    this.#appear()

  }

  /**
   * @description 创建投票选项
   * @returns {HTMLDivElement}
   */
  #createVoteOptions () {
    const voteOptions = document.createElement('div')
    voteOptions.classList.add('xj-details-vote-options')
    this.limb._voteOption = []
    this._info['voteOptionLaunchDTOs'].forEach((item, index) => {
      this.limb._voteOption[index] = document.createElement('div')
      this.limb._voteOption[index].classList.add('xj-details-vote-option')
      this.limb._voteOption[index].setAttribute('data-index', index)
      /* 已投票且选中的选项高亮 */
      if (this._info['isComplete'] === 1 && item['isSelect'] === true) {
        this.limb._voteOption[index].style['background-color'] = '#ffffff55'
        this.limb._voteOption[index].style['border-color'] = '#6ce2fc'
        this.limb._voteOption[index].style.setProperty('--beforeBC', 'rgba(108,226,252,0.75)')
        this.limb._voteOption[index].style.setProperty('--afterColor', 'rgba(0,0,0,1)')
      }
      /* 已投票未或不是进行中的投票不参与交互 */
      if (this._info['isComplete'] === 1 || this._info['status'] !== 1) {
        this.limb._voteOption[index].style.setProperty('--isCompleteBG', '#00000005')
        this.limb._voteOption[index].style.setProperty('--isCompleteBC', '#00000022')
        this.limb._voteOption[index].style['cursor'] = 'default'
      }
      /* 如果已投票且投票实时 */
      if (this._info['isComplete'] === 1 && this._info['isRealTime'] === '1' && item.percentage && item.voteNum) {
        this.limb._voteOption[index].style.setProperty('--beforeWidth', item.percentage)
        this.limb._voteOption[index].setAttribute('data-afterContent', item.voteNum)
      }
      const text = document.createElement('p')
      text.appendChild(document.createTextNode(item['content']))
      this.limb._voteOption[index].appendChild(text)
      voteOptions.appendChild(this.limb._voteOption[index])
    })

    return voteOptions
  }

  /**
   * @description 创建文本框
   * @returns {HTMLDivElement}
   */
  #createTextBox () {

  }

  /**
   * @description 创建提交节点
   * @returns {HTMLDivElement}
   */
  #createSubmit () {
    const submitDom = document.createElement('div')
    submitDom.classList.add('xj-details-vote-submitDom')
    return submitDom
  }

  /**
   * @description 创建权重展示节点
   * @returns {HTMLDivElement}
   */
  #createWeight () {
    const weightDom = document.createElement('div')
    weightDom.classList.add('xj-details-vote-weightDom')
    this.limb._weightList = document.createElement('div')
    this.limb._weightList.classList.add('xj-details-vote-weightList')
    /* 创建顶栏 */
    const weightTop = document.createElement('div')
    weightTop.innerHTML = '角色权重'
    weightTop.classList.add('xj-details-vote-weightTop')
    /* 创建返回按钮 */
    this.limb._weightCloseButton = document.createElement('div')
    this.limb._weightCloseButton.innerHTML = '< 投票'
    this.limb._weightCloseButton.classList.add('xj-details-vote-weightCloseButton')
    weightTop.appendChild(this.limb._weightCloseButton)
    this.limb._weightList.appendChild(weightTop)
    weightDom.appendChild(this.limb._weightList)

    /* 创建列表 */
    console.log(this._info['voteWeights'])

    return weightDom
  }

  /** {Object} #event 保存生成的事件 */
  #event = {
    _main: {},
    _closeButton: {},
    _voteOption: {}
  }

  /**
   * @description 创建监听时触发的方法
   * @param {string} type
   * @returns {{}}
   */
  #createEvent (type) {
    const _eventInfo = {
      voteHeight: parseFloat(getComputedStyle(this._voteNode)['height']),
      voteTitleHeight: parseFloat(getComputedStyle(this.limb._title)['height']),
      voteMainHeight: parseFloat(getComputedStyle(this.limb._main)['height']),
      'main-top': parseFloat(getComputedStyle(this.limb._main).top),
      wheel_move: {
        isAvailable: false
      },
      touch: {
        isAvailable: false,
        startTop: 0,
        oldTop: 0,
        speed: 0
      },
      event: {
        /**
         * @description 标题遮挡
         * @param {boolean} isOcclusion 是否启用遮挡
         */
        titleOcclusion: (isOcclusion) => {
          if (isOcclusion) {
            this._voteNode.style['box-shadow'] = '0 0 10px #00000033 inset'
            this.limb._title.style['box-shadow'] = '0 0 10px #00000033'
            this.limb._title.style['background-color'] = '#f7f7f7b2'
          } else {
            this._voteNode.style['box-shadow'] = ''
            this.limb._title.style['box-shadow'] = ''
            this.limb._title.style['background-color'] = '#f7f7f700'
          }
        }
      }
    }
    switch (type) {
      case '_main': return {
        wheel: (e) => {
          this.limb[type].style['transition'] = 'all .2s'
          if (_eventInfo.voteMainHeight + parseFloat(getComputedStyle(this.limb[type])['top']) > _eventInfo.voteHeight) _eventInfo.wheel_move['isAvailable'] = true
          if (!_eventInfo.wheel_move['isAvailable']) return
          if (parseFloat(getComputedStyle(this.limb[type])['top']) - e.deltaY >= _eventInfo['main-top']) {
            this.limb[type].style['top'] = _eventInfo['main-top'] + 'px'
            _eventInfo.event.titleOcclusion(false)
            return
          } else if (parseFloat(getComputedStyle(this.limb[type])['top']) - e.deltaY + _eventInfo.voteMainHeight * 1.06 < _eventInfo.voteHeight && e.deltaY > 0) {
            this.limb[type].style['top'] = _eventInfo.voteHeight - _eventInfo.voteMainHeight * 1.06 + 'px'
            _eventInfo.event.titleOcclusion(true)
            return
          }
          this.limb[type].style['top'] = parseFloat(getComputedStyle(this.limb[type])['top']) - e.deltaY + 'px'
          _eventInfo.event.titleOcclusion(parseFloat(getComputedStyle(this.limb[type])['top']) - e.deltaY + 1 <= _eventInfo['main-top'])
        },
        touchstart: (e) => {
          if (_eventInfo.voteMainHeight + parseFloat(getComputedStyle(this.limb[type])['top']) > _eventInfo.voteHeight) _eventInfo.touch['isAvailable'] = true
          this.limb[type].style['transition'] = 'none'
          if (e.changedTouches > 1) return
          _eventInfo.touch.startTop = e.changedTouches[0].clientY
          _eventInfo.touch.oldTop = e.changedTouches[0].clientY
        },
        touchmove: (e) => {
          if (e.changedTouches > 1 || !_eventInfo.touch['isAvailable']) return
          this.limb[type].style['top'] = parseFloat(getComputedStyle(this.limb[type])['top']) - _eventInfo.touch.oldTop + e.changedTouches[0].clientY + 'px'
          _eventInfo.touch.speed = Math.abs(e.changedTouches[0].clientY - _eventInfo.touch.oldTop)
          _eventInfo.touch.oldTop = e.changedTouches[0].clientY
          _eventInfo.event.titleOcclusion(parseFloat(getComputedStyle(this.limb[type])['top']) + 1 <= _eventInfo['main-top'])
        },
        touchend: () => {
          this.limb[type].style['transition'] = 'all .3s'
          if (parseFloat(getComputedStyle(this.limb[type])['top']) >= _eventInfo['main-top']) {
            this.limb[type].style['top'] = _eventInfo['main-top'] + 'px'
          } else if (parseFloat(getComputedStyle(this.limb[type])['top']) + _eventInfo.voteMainHeight <= _eventInfo.voteHeight * 0.94) {
            this.limb[type].style['top'] = _eventInfo.voteHeight * 0.94  - _eventInfo.voteMainHeight + 'px'
          }
        }
      }
      case '_closeButton': return {
        click: () => {
          this.#disappear()
        }
      }
      case '_voteOption': return {
        click: (e) => {
          console.log(e.srcElement.parentElement.getAttribute('data-index'))
        }
      }
      case '_rulesWeights': return {
        click: () => {
          this.limb._weightDom.style['left'] = '0'
        }
      }
      case  '_weightCloseButton': return {
        click: () => {
          this.limb._weightDom.style['left'] = '100%'
        }
      }
    }
  }

  /**
   * @description 创建事件
   */
  #runCreateEvent () {
    this.#event['_main'] = this.#createEvent('_main')
    this.#event['_closeButton'] = this.#createEvent('_closeButton')
    this.#event['_voteOption'] = this.#createEvent('_voteOption')
    this.#event['_rulesWeights'] = this.#createEvent('_rulesWeights')
    this.#event['_weightCloseButton'] = this.#createEvent('_weightCloseButton')
    // this.#event['_weightList'] = this.#createEvent('_weightList')
  }

  /**
   * @description 添加事件
   * @param {Array<string|null>} type
   */
  #addEvent (type = []) {
    type.forEach(item => {
      if (item === '_voteOption') {
        this.limb[item].forEach(voteOption => {
          for (let key in this.#event[item]) {
            voteOption.addEventListener(key, this.#event[item][key])
          }
        })
      } else {
        for (let key in this.#event[item]) {
          this.limb[item].addEventListener(key, this.#event[item][key])
        }
      }
    })
  }

  /**
   * @description 节点出现
   */
  #appear () {
    // this.limb._title.style['transition'] = 'all .15s'
    // this.limb._title.style['backdrop-filter'] = 'blur(10px)'
    // this.limb._title.style['-webkit-backdrop-filter'] = 'blur(10px)'
    requestAnimationFrame(() => {
      this._voteNode.style['opacity'] = 1
      this._voteNode.style['top'] = 'calc(100% + 10px)'
    })
  }

  /**
   * @description 销毁详细投票列表
   */
  #disappear () {
    if (this.isDisappear) return
    this.#onclick && this.#onclick(this._info)
    requestAnimationFrame(() => {
      this._voteNode.style['opacity'] = 0
      this._voteNode.style['top'] = - this._upLong + 'px'
      this._voteRoughList.nodeClickFinish()
    })
    this.isDisappear = true
    setTimeout(() => {
      this._voteNode.remove()
    }, 600)
  }

  /** {Function} 闭时触发的回调 */
  #onclick = null

  /**
   * 传入关闭时触发的回调
   * @param {Function} onclose
   */
  onclose (onclose) {
    this.#onclick = onclose
  }

  /**
   * @description 销毁详细投票列表
   */
  disappear () {
    this.#disappear()
  }

  get detailsDisappear () {
    return this.disappear
  }

}

export default VoteDetailsWindow
