/** 点击粗略节点后显示的详细节点 */
import './voteDetailsWindow.scss'
import store from '@/store'

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
    _weightList: null,
    _weightTitle: null
  }
  /** _roughVoteNode 粗略投票节点
   * @type {HTMLDivElement} */
  _roughVoteNode
  /** upLong 粗略投票节点上移距离，用于详细节点位置校准
   * @type {number} */
  _upLong
  /** info 详细信息
   * @type {Object} */
  _info
  /** voteRoughList VoteRoughList实例
   * @type {VoteRoughList}*/
  _voteRoughList
  /** height 详细投票节点高度
   * @type {number}*/
  height
  /** isDisappear 是否已经销毁
   * @type {boolean} */
  isDisappear = false
}

/**
 * @description 详细投票展示窗口
 * @author XunJi - 常
 * @version 1.0.0
 */
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
    this.#addEvent(['_main', '_closeButton', '_voteOption', '_rulesWeights', '_weightCloseButton', '_weightList'])
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
    if (this._info['status'] === 1 || this._info['isComplete'] === 0) {
      this.limb._rulesRemainingQuantity = document.createElement('div')
      this.limb._rulesRemainingQuantity.classList.add('xj-details-vote-remainder')
      this.limb._rulesRemainingQuantity.innerHTML = `剩余可选: ${ this._info['optionNum'] } 个`
      rules.appendChild(this.limb._rulesRemainingQuantity)
    }
    this.limb._rulesWeights = document.createElement('div')
    this.limb._rulesWeights.classList.add('xj-details-vote-weights')
    this.limb._rulesWeights.innerHTML = `角色权重 >`
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
    this.limb._main.style['top'] = `calc(${ parseFloat(getComputedStyle(this.limb._title)['height']) + parseFloat(getComputedStyle(this.limb._title)['padding-top']) * 2 }px + 8px)`

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
    weightTop.classList.add('xj-details-vote-weightTop')
    /* 创建返回按钮 */
    this.limb._weightCloseButton = document.createElement('div')
    this.limb._weightCloseButton.innerHTML = '< 投票'
    this.limb._weightCloseButton.classList.add('xj-details-vote-weightCloseButton')
    weightTop.appendChild(this.limb._weightCloseButton)
    weightDom.appendChild(weightTop)


    /* 创建列表 */
    /* 解构当前用户权重 */
    const { roleId: currentRoleId, roleName: currentRoleName, weight: currentWeight } = this._info['voteWeights'].reduce((v, item) => {
      if (store.getters['detailedRoles'][0].roleId === item.roleId) return item
      return v
    }, { currentRoleId: null, currentRoleName: null, currentWeight: null })
    this.limb._weightTitle = document.createElement('p')
    this.limb._weightTitle.appendChild(document.createTextNode(`角色权重`))
    weightTop.appendChild(this.limb._weightTitle)
    // document.createTextNode(`我的角色: ${ currentRoleName }, 权重: ${ currentWeight }`)

    this._info['voteWeights'].forEach(item => {
      const _element = document.createElement('div')
      _element.classList.add('xj-details-vote-weightElement')
      _element.appendChild(document.createTextNode(`${ item.roleName } >> ${ item.weight }`))
      if (item.roleId === currentRoleId) {
        _element.style['background-color'] = '#acdfe7'
        _element.style['box-shadow'] = '0 0 3px #00000022'
      }
      this.limb._weightList.appendChild(_element)
    })


    weightDom.appendChild(this.limb._weightList)
    return weightDom
  }

  /** {Object} #event 保存生成的事件 */
  #event = {
    _main: {},
    _closeButton: {},
    _voteOption: {},
    _rulesWeights: {},
    _weightCloseButton: {},
    _weightList: {}
  }

  /**
   * @description 创建监听时触发的方法
   * @param {string} type
   * @returns {{}}
   */
  #createEvent (type) {
    const createInfo = () => {
      const _weightTitleMove = {
            top: parseFloat(getComputedStyle(this.limb._title)['height']) + parseFloat(getComputedStyle(this.limb._title)['padding-top']) - parseFloat(getComputedStyle(this.limb._rulesWeights)['height']) - 4 + 'px',
            left: - parseFloat(getComputedStyle(this.limb._weightTitle)['width']) / 2 - parseFloat(getComputedStyle(this.limb._title)['padding-right']) - parseFloat(getComputedStyle(this.limb._rulesWeights)['width']) / 2 - 15 + 'px'
          }
      switch (type) {
        case '_main': return {
          voteHeight: parseFloat(getComputedStyle(this._voteNode)['height']),
          voteTitleHeight: parseFloat(getComputedStyle(this.limb._title)['height']),
          voteMainHeight: parseFloat(getComputedStyle(this.limb._main)['height']),
          'main-top': parseFloat(getComputedStyle(this.limb._main)['top']),
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
                this._voteNode.style['box-shadow'] = '0 0 3px #00000033 inset'
                this.limb._title.style['box-shadow'] = '0 0 3px #00000033'
                this.limb._title.style['background-color'] = '#f7f7f7b2'
              } else {
                this._voteNode.style['box-shadow'] = ''
                this.limb._title.style['box-shadow'] = ''
                this.limb._title.style['background-color'] = '#f7f7f700'
              }
            }
          }
        }
        case '_rulesWeights': return {
          move: _weightTitleMove
        }
        case '_weightCloseButton': return {
          move: _weightTitleMove
        }
        case '_weightList': return {
          isInitiateScroll: parseFloat(getComputedStyle(this._voteNode)['height']) < parseFloat(getComputedStyle(this.limb._weightList)['height']) + 50,
          touch: {
            startLocation: { top: 0, left: 0 },
            oldLocation: { top: 0, left: 0 },
            isTouch: false,
          },
          event: {
            start: (e) => {
              this.limb['_weightList'].style['transition'] = 'none'
              _eventInfo.touch.isTouch = true
              _eventInfo.touch.startLocation['left'] = e.clientX ? e.clientX : e.changedTouches[0].clientX
              _eventInfo.touch.startLocation['top'] = e.clientY ? e.clientY : e.changedTouches[0].clientY
              _eventInfo.touch.oldLocation = _eventInfo.touch.startLocation
            },
            move: (e) => {
              if (!_eventInfo.touch.isTouch) return
              this.limb['_weightList'].style['left'] = parseFloat(getComputedStyle(this.limb['_weightList'])['left']) + (e.clientX ? e.clientX : e.changedTouches[0].clientX) - _eventInfo.touch.oldLocation['left'] + 'px'
              this.limb['_weightList'].style['top'] = parseFloat(getComputedStyle(this.limb['_weightList'])['top']) + (e.clientY ? e.clientY : e.changedTouches[0].clientY) - _eventInfo.touch.oldLocation['top'] + 'px'
              _eventInfo.touch.oldLocation['left'] = e.clientX ? e.clientX : e.changedTouches[0].clientX
              _eventInfo.touch.oldLocation['top'] = e.clientY ? e.clientY : e.changedTouches[0].clientY
            },
            end: () => {
              if (!_eventInfo.touch.isTouch) return
              this.limb['_weightList'].style['transition'] = 'all .15s'
              this.limb['_weightList'].style['left'] = '0'
              _eventInfo.touch.isTouch = false
              if (!_eventInfo.isInitiateScroll) this.limb['_weightList'].style['top'] = '36px'
              else
                if (parseFloat(getComputedStyle(this.limb['_weightList'])['top']) > 36) this.limb['_weightList'].style['top'] = '36px'
                else if (parseFloat(getComputedStyle(this.limb['_weightList'])['top']) < parseFloat(getComputedStyle(this._voteNode)['height']) - parseFloat(getComputedStyle(this.limb['_weightList'])['height']) - 7)
                  this.limb['_weightList'].style['top'] = parseFloat(getComputedStyle(this._voteNode)['height']) - parseFloat(getComputedStyle(this.limb['_weightList'])['height']) - 7 + 'px'
            }
          }
        }
      }
    }
    const _eventInfo = createInfo()
    switch (type) {
      case '_main': return {
        /**
         * 鼠标滚轮滚动时的页面滚动
         * 因为此时禁用了全局滚动事件，所以需要写一个简易滚动
         * @param e 事件属性
         */
        wheel: (e) => {
          this.limb['_main'].style['transition'] = 'top .15s cubic-bezier(0.22, 1.11, 1, 1), left .45s ease'
          if (_eventInfo.voteMainHeight + parseFloat(getComputedStyle(this.limb[type])['top']) > _eventInfo.voteHeight) _eventInfo.wheel_move['isAvailable'] = true
          if (!_eventInfo.wheel_move['isAvailable']) return
          if (parseFloat(getComputedStyle(this.limb['_main'])['top']) - e.deltaY >= _eventInfo['main-top']) {
            this.limb['_main'].style['top'] = _eventInfo['main-top'] + 'px'
            _eventInfo.event.titleOcclusion(false)
            return
          } else if (parseFloat(getComputedStyle(this.limb['_main'])['top']) - e.deltaY + _eventInfo.voteMainHeight * 1.06 < _eventInfo.voteHeight && e.deltaY > 0) {
            this.limb['_main'].style['top'] = _eventInfo.voteHeight - _eventInfo.voteMainHeight * 1.06 + 'px'
            _eventInfo.event.titleOcclusion(true)
            return
          }
          this.limb['_main'].style['top'] = parseFloat(getComputedStyle(this.limb['_main'])['top']) - e.deltaY + 'px'
          _eventInfo.event.titleOcclusion(parseFloat(getComputedStyle(this.limb[type])['top']) - e.deltaY + 1 <= _eventInfo['main-top'])
        },
        /**
         * 触摸滑动时的页面滚动
         * 因为此时禁用了全局滚动事件，所以需要写一个简易滚动
         * @param e 事件属性
         */
        touchstart: (e) => {
          if (_eventInfo.voteMainHeight + parseFloat(getComputedStyle(this.limb['_main'])['top']) > _eventInfo.voteHeight) _eventInfo.touch['isAvailable'] = true
          this.limb['_main'].style['transition'] = 'left .45s ease'
          if (e.changedTouches > 1) return
          _eventInfo.touch.startTop = e.changedTouches[0].clientY
          _eventInfo.touch.oldTop = e.changedTouches[0].clientY
        },
        touchmove: (e) => {
          if (e.changedTouches > 1 || !_eventInfo.touch['isAvailable']) return
          this.limb['_main'].style['top'] = parseFloat(getComputedStyle(this.limb['_main'])['top']) - _eventInfo.touch.oldTop + e.changedTouches[0].clientY + 'px'
          _eventInfo.touch.speed = Math.abs(e.changedTouches[0].clientY - _eventInfo.touch.oldTop)
          _eventInfo.touch.oldTop = e.changedTouches[0].clientY
          _eventInfo.event.titleOcclusion(parseFloat(getComputedStyle(this.limb['_main'])['top']) + 1 <= _eventInfo['main-top'])
        },
        touchend: () => {
          this.limb['_main'].style['transition'] = 'top .15s cubic-bezier(0.22, 1.11, 1, 1), left .45s ease'
          if (parseFloat(getComputedStyle(this.limb[type])['top']) >= _eventInfo['main-top']) {
            this.limb['_main'].style['top'] = _eventInfo['main-top'] + 'px'
          } else if (parseFloat(getComputedStyle(this.limb[type])['top']) + _eventInfo.voteMainHeight <= _eventInfo.voteHeight * 0.94) {
            this.limb['_main'].style['top'] = _eventInfo.voteHeight * 0.94  - _eventInfo.voteMainHeight + 'px'
          }
        }
      }
      case '_closeButton': return {
        /**
         * 关闭详细投票页面
         */
        click: () => {
          this.#disappear()
        }
      }
      case '_voteOption': return {
        /**
         *
         * @param e
         */
        click: (e) => {
          console.log(e.srcElement.parentElement.getAttribute('data-index'))
        }
      }
      case '_rulesWeights': return {
        /**
         * 打开子页面时的左滑动作
         */
        click: () => {
          const fontSize = getComputedStyle(this.limb._weightTitle)
          this.limb._weightTitle.style['font-size'] = getComputedStyle(this.limb._rulesWeights)
          this.limb._weightTitle.style['transition'] = 'none'
          this.limb._weightTitle.style['top'] = _eventInfo.move['top']
          this.limb._weightTitle.style['left'] = _eventInfo.move['left']
          this.limb._weightTitle.style['color'] = '#409eff'
          this.limb._rulesWeights.style['opacity'] = '0'
          this.limb._rulesWeights.style['color'] = '#409eff00'
          requestAnimationFrame(() => {
            this.limb._weightTitle.style['font-size'] = fontSize
            this.limb._weightTitle.style['transition'] = 'all .45s'
            this.limb._weightTitle.style['top'] = '0'
            this.limb._weightTitle.style['left'] = '0'
            this.limb._weightTitle.style['color'] = '#000000'
            this.limb._weightDom.style['left'] = '0'
            this.limb._main.style['left'] = '-50%'
            this.limb._title.style['left'] = '-50%'
          })
        }
      }
      case  '_weightCloseButton': return {
        /**
         * 关闭子页面的右滑动作
         */
        click: () => {
          const fontSize = getComputedStyle(this.limb._weightTitle)
          this.limb._weightTitle.style['font-size'] = getComputedStyle(this.limb._rulesWeights)
          this.limb._weightTitle.style['transition'] = 'all .45s'
          this.limb._weightTitle.style['top'] = _eventInfo.move['top']
          this.limb._weightTitle.style['left'] = _eventInfo.move['left']
          this.limb._weightTitle.style['color'] = '#409eff'
          this.limb._title.style['left'] = '0'
          this.limb._main.style['left'] = '0'
          this.limb._weightDom.style['left'] = '100%'
          setTimeout(() => {
            requestAnimationFrame(() => {
              this.limb._rulesWeights.style['color'] = '#409eff'
              this.limb._rulesWeights.style['opacity'] = '1'
              setTimeout(() => {
                requestAnimationFrame(() => {
                  this.limb._weightTitle.style['font-size'] = fontSize
                  this.limb._weightTitle.style['transition'] = 'none'
                  this.limb._weightTitle.style['top'] = '0'
                  this.limb._weightTitle.style['left'] = '0'
                  this.limb._weightTitle.style['color'] = '#000000'
                })
              }, 200)
            })
          }, 450)
        }
      }
      /**
       * 权限菜单拖动查看
       */
      case '_weightList': return {
        touchstart: (e) => {
          if (e.changedTouches > 1) return
          _eventInfo.event.start(e)
        },
        touchmove: (e) => {
          if (e.changedTouches > 1) return
          _eventInfo.event.move(e)
        },
        touchend: (e) => {
          if (e.changedTouches > 1) return
          _eventInfo.event.end(e)
        },
        mousedown: (e) => {
          _eventInfo.event.start(e)
        },
        mousemove: (e) => {
          _eventInfo.event.move(e)
        },
        mouseup: (e) => {
          _eventInfo.event.end(e)
        },
        mouseleave: (e) => {
          _eventInfo.event.end(e)
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
    this.#event['_weightList'] = this.#createEvent('_weightList')
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
      this._voteNode.style['top'] = 'calc(100% + 40px)'
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
