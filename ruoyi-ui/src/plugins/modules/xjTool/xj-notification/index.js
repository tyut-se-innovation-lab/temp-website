import './index.css'

/*
 * notification 通知弹窗
 * @param {string} title 标题 选填
 * @param {string} message 信息 必填
 * @param {string} type 类型 选填 默认normal   success warning error
 * @param {string} location 位置 选填 默认left-bottom   left-top right-top right-bottom left-bottom-1...
 * @param {number} duration 持续时间 默认3000 如果是0 则不会自动关闭 除 0 外 最小值为 1000
 * @param {boolean} showClose 是否可以手动关闭 默认可以 开启时，鼠标移入会停止自动关闭计时
 * @param {boolean} userSelect 是否限制文本选中 默认 true 禁止选中文本
 * @param {Function | null} callback 点击通知框时运行的回调函数，callback 接收一个 notification 位置的参数
 *
 * 示例:
 * // 创建一个位于右上角的第二格位置的不会自动消失的通知
 * const notify1 = this.$xjNotify({
        title: 'notification title测试-3',
        message: 'notification message测试-3',
        type: 'warning',
        location: 'right-top',
        duration: 0,
        callback: () => {
          console.log('@3 async callback 运行了')
          this.$router.push({
            path: `/user/noticePop`,
          })
          return '@3 callback 运行 的返回值'
        }
      })
 * // 输出 callback 的返回值
 * notify1.then(v => { console.log(v) })
 * // 将通知从第二格改到第五格
 * notify1.setLocation(5)
 * // 关闭该通知
 * notify1.close()
 */

class NotificationList {
  static #length = {
    'left-bottom': { length: 0, list:[] },
    'left-top': { length: 0, list:[] },
    'right-bottom': { length: 0, list:[] },
    'right-top': { length: 0, list:[] }
  }

  /**
   * 修改 #length
   * @param {[string, string, number]} location
   * @param {'add' | 'reduce'} action 动作
   * @param {Notification} Notification Notification 实例
   */
  static setLength (location, action, Notification) {
    action === 'add' ? this.#length[location[0] + '-' + location[1]].length++ : this.#length[location[0] + '-' + location[1]].length--
    this.#setList(location, action, Notification)
    return this.#length[location[0] + '-' + location[1]].length
  }

  /**
   * 修改 NotificationList 数据
   * @param {[string, string, number]} location
   * @param {'add' | 'reduce'} action
   * @param {Notification} Notification
   */
  static #setList (location, action,Notification) {
    if (action === 'add') {
      this.#length[location[0] + '-' + location[1]].list.push({Notification, location})
      return
    }
    this.#length[location[0] + '-' + location[1]].list.splice(location[2] - 1, 1)
    for (let i = location[2] - 1; i < this.#length[location[0] + '-' + location[1]].list.length; ++i) {
      this.#length[location[0] + '-' + location[1]].list[i].Notification._setLocation([location[0], location[1], i + 1])
    }
  }

  /**
   *  获取 #length
   * @param {[string, string, number]} location
   * @returns {number} 当前位置数量
   */
  static getLength (location) {
    return this.#length[location[0] + '-' + location[1]].length
  }
}

class Notification {
  // only read:number px
  static #ROOT_HEIGHT = 93
  // only read:number px
  static #ROOT_WIDTH = 363

  /* 是否接入通知列表 NotificationList */
  #isAccessList

  /* 当前通知在当前所在位置的排位，不包括绝对位置的通知 */
  #ranking = {}

  /* 通知栏 根div: HTMLDivElement */
  #rootBox

  /* 关闭按钮 */
  #closeSvg = null

  /* 通知栏坐标
   * {'left | right': number, 'top | bottom': number, ranking: number}
   */
  #location = {}

  /* 保存计时器 */
  #timeout = {}

  /*
   * 记录通知点击触发的函数的Promise
   * { Promise: Promise, resolve: Function, reject: Function }
   */
  #callbackP = {
    Promise: null,
    resolve: null,
    reject: null
  }
  /* 是否被关闭 */
  #isClose = false

  /**
   * notification 通知弹窗
   * @param {string} title 标题 选填
   * @param {string} message 信息 必填
   * @param {string} type 类型 选填 默认normal   success warning error
   * @param {string} location 位置 选填 默认left-bottom   left-top right-top right-bottom left-bottom-1...
   * @param {number} duration 持续时间 默认3000 如果是0 则不会自动关闭 除 0 外 最小值为 1000
   * @param {boolean} showClose 是否可以手动关闭 默认可以 开启时，鼠标移入会停止自动关闭计时
   * @param {boolean} userSelect 是否限制文本选中 默认 true 禁止选中文本
   * @param {Function | null} callback 点击通知框时运行的回调函数，callback 接收一个 notification 位置的参数
   */
  constructor ({title, message, type = 'normal' , location = 'left-bottom', duration = 3000, showClose = true, userSelect = true, callback = null}) {
    /* 检查数据合法性 */
    const {_type, _location, _duration, _showClose} = this.#examine(type, message, location, duration, showClose)
    if (this.#isAccessList) {
      /* 未设置绝对位置
       * 加入通知列表
       */
      this.#ranking[_location[0] + '-' + _location[1]] = NotificationList.setLength([_location[0], _location[1], NotificationList.getLength(_location)], 'add', this)
    } else {
      /* 设置了绝对位置 */
      this.#ranking[_location[0] + '-' + _location[1]] = _location[2]
    }
    this.#create(title, message, _type, _duration, _showClose, userSelect)
    this.#positionInitialization(_location, callback)
    this.#setLocation(_location)
    this.#addEvent(_location, _duration, _showClose, callback)
    _duration !== 0 && (this.#timeout['close'] = setTimeout(() => {
      this.#remove(_location)
    }, _duration))
  }

  /**
   * 验证和处理数据
   * @param {string} type
   * @param {string} message
   * @param {string} location
   * @param {number} duration
   * @param {boolean} showClose
   * @returns {{_type: string, _location: [string, string, number], _duration: number, _showClose: boolean}}
   */
  #examine (type, message, location, duration, showClose) {
    /* 检查 duration 和 showClose */
    if (duration === 0) {
      /* 如果 duration 为0  则无视传入的 showClose 强制可以手动关闭 */
      if (showClose === false) throw new Error('Notification => duration 为0时, showClose 必须为 true')
      showClose = true
    } else if (duration < 1000) {
      throw new Error(`Notification => duration 的值小于1000 > ${ duration }`)
    }

    if (!message) throw new Error('Notification => message为必填内容')

    /* 检查 location */
    const _l = location.split('-')
    if (_l[2]) {
      (_l[2] = Number(_l[2]))
      this.#isAccessList = false
    } else {
      _l[2] = void 0
      this.#isAccessList = true
    }
    /* 判断 location 是否合法 */
    const isIllegal = !((_l[0].includes('left') || _l[0].includes('right')) && (_l[1].includes('bottom') || _l[1].includes('top')) && (typeof _l[2] === 'undefined' || (Number(_l[2]).toString() !== 'NaN' && Number(_l[2]) >= 0)))
    if (isIllegal) {
      throw new Error(`Notification => location 内容非法 > ${ location }`)
    }

    /* 检查 type */
    const _t = ['success', 'normal', 'warning', 'error'].filter(item => {
      if (item === type) return item
    })
    if (_t.length < 1) throw new Error(`Notification => type 内容非法 > ${ type }`)
    return { _type: type, _location: _l, _duration: duration, _showClose: showClose}
  }

  /**
   * 创建 notification
   * @param {string} title 标题
   * @param {string} message 信息
   * @param {string} type 类型
   * @param {number} duration 持续时间
   * @param {boolean} showClose 是否可以手动关闭
   * @param {boolean} userSelect 是否限制文本选中
  // * @param {Function | null} callback 点击通知框时运行的回调函数，默认为 null
   */
  #create (title, message, type, duration, showClose, userSelect) {
    // 创建 notification 盒子
    this.#rootBox = document.createElement('div')
    this.#rootBox.classList.add('xj-notification')
    this.#rootBox.classList.add(`xj-notification-${ type }`)
    this.#rootBox.style.transformOrigin = 'center center'
    userSelect && (this.#rootBox.style['cursor'] = 'default')
    userSelect && (this.#rootBox.style['userSelect'] = 'none')
    /* 标题 div */
    if (title) {
      const titleBox = document.createElement('div')
      titleBox.classList.add('xj-notification-title')
      titleBox.appendChild(document.createTextNode(title))
      this.#rootBox.appendChild(titleBox)
    }
    /* 信息节点 div */
    const messageBox = document.createElement('div')
    /*
     * 如果传入了 title，message 样式为 message
     * 否则 message 样式为 title
     */
    if (title) messageBox.classList.add('xj-notification-message')
    else messageBox.classList.add('xj-notification-title')
    messageBox.appendChild(document.createTextNode(message))
    this.#rootBox.appendChild(messageBox)
    switch (type) {
      case 'success':
      case 'warning':
      case 'error':
      case 'normal':
        this.#rootBox.classList.add(`xj-notification-${ type }`)
        break
    }
    /* 关闭节点 svg */
    if (showClose) {
      this.#closeSvg = document.createElement('svg')
      this.#closeSvg.classList.add('xj-notification-close')
      this.#closeSvg.classList.add('icon')
      this.#closeSvg.classList.add('xj-icon-close')
      this.#closeSvg.classList.add('svg-icon')
      this.#closeSvg.setAttribute('aria-hidden', 'true')
      this.#rootBox.appendChild(this.#closeSvg)
      this.#rootBox.innerHTML += ''
    }
  }

  /**
   * 初始化 notification 位置
   * 创建 callback Promise
   * @param {[string, string, number]} location 位置
   * @param {Function | null} callback
   */
  #positionInitialization (location, callback) {
    typeof location[2] === 'undefined' ? (this.#location['ranking'] = this.#ranking[location[0] + '-' + location[1]]) : this.#location['ranking'] = location[2]
    this.#location[location[0]] = - Notification.#ROOT_WIDTH // px
    this.#location[location[1]] = 16 + (this.#location['ranking'] - 1) * Notification.#ROOT_HEIGHT // px
    this.#rootBox.style[location[0]] = this.#location[location[0]] + 'px'
    this.#rootBox.style[location[1]] = this.#location[location[1]] + 'px'
    document.body.appendChild(this.#rootBox)
    callback && (this.#callbackP['Promise'] = new Promise((resolve, reject) => {
      this.#callbackP['resolve'] = resolve
      this.#callbackP['reject'] = reject
    }))
  }

  /**
   * 提供给 NotificationList 使用的，用于修改通知位置的方法
   * @param {[string, string, number | undefined]} location
   */
  _setLocation (location) {
    this.#setLocation(location)
    this.#ranking[location[0] + '-' + location[1]] = location[2]
  }

  /**
   * 修改 notification 位置
   * @param {[string, string, number | undefined]} location 位置 left-top right-top right-bottom left-bottom-1...
   */
  #setLocation (location) {
    const _oldLocation = JSON.parse(JSON.stringify(this.#location))
    /* 设置 notification 位置 */
    !location[2] && (location[2] = this.#ranking[location[0] + '-' + location[1]])
    /* 设置位置 */
    this.#location[location[0]] = 18 // px
    this.#location[location[1]] = 16 + (location[2] - 1) * Notification.#ROOT_HEIGHT // px
    this.#locationUpdate(_oldLocation)
  }

  /**
   * 带过渡的位置更新
   * @param {{'left | right': number, 'top | bottom': number}} _oldLocation 旧的位置
   */
  #locationUpdate (_oldLocation) {
    const _nowLocation = JSON.parse(JSON.stringify(_oldLocation))
    const _change = {}
    for (let key in _oldLocation) {
      if (key === 'ranking') continue
      _change[key] = this.#location[key] - _oldLocation[key]
    }
    const _run = () => {
      for (let key in _nowLocation) {
        if (key === 'ranking') continue
        this.#rootBox.style[key] = this.#location[key] + 'px'
      }
    }
    requestAnimationFrame(_run)
  }

  /**
   * 删除 notification
   * @param {[string, string, number | undefined]} location
   */
  #remove (location) {
    this.#timeout['close'] && clearTimeout(this.#timeout['close'])
    this.#timeout['close'] = null
    const _oldLocation = JSON.parse(JSON.stringify(this.#location))
    this.#location['ranking'] = this.#ranking[location[0] + '-' + location[1]]
    this.#location[location[0]] = - Notification.#ROOT_WIDTH // px
    this.#location[location[1]] = 16 + (this.#location['ranking'] - 1) * Notification.#ROOT_HEIGHT // px
    this.#locationUpdate(_oldLocation)
    if (this.#isAccessList) NotificationList.setLength([location[0], location[1], this.#ranking[location[0] + '-' + location[1]]], 'reduce', this)
    this.#deleteEvent()
    this.#isClose = true
    setTimeout(() => {
      this.#rootBox.remove()
    }, 300)
  }

  /**
   * 用于生成事件监听的执行事件
   * @param {[string, string, number]} location
   * @param {number} duration
   * @param {boolean} showClose
   * @param {Function} callback
   * @returns {{mouseenter: Function, mouseleave: Function, mouseup: Function, mousedown: Function, click: Function}}
   */
  #generateEvents (location, duration, showClose, callback) {
    return {
      mouseenter: () => {
        clearTimeout(this.#timeout['close'])
        this.#timeout['close'] = null
      },
      mouseleave: () => {
        this.#timeout['close'] && clearTimeout(this.#timeout['close'])
        this.#timeout['close'] = null
        duration !== 0 && (this.#timeout['close'] = setTimeout(() => {
          this.#remove(location)
        }, duration))
        this.#rootBox.style['transition-duration'] = '.4s'
        this.#rootBox.style['transform'] = 'scale(100%)'
      },
      mousedown: (e) => {
        if (e.target.classList.contains('xj-notification-close')) return
        this.#rootBox.style['transition-duration'] = '.1s'
        this.#rootBox.style['transform'] = 'scale(105%)'
      },
      mouseup: () => {
        this.#rootBox.style['transition-duration'] = '.4s'
        this.#rootBox.style['transform'] = 'scale(100%)'
      },
      click: (e) => {
        if (e.target.classList.contains('xj-notification-close') && showClose) {
          this.#remove(location)
          return
        }
        if (callback) {
          this.#runCallback(callback)
          this.#remove(location)
        }
      }
    }
  }

  /**
   * 处理回调函数的运行
   * @param {Function} callback
   * @return {null}
   */
  async #runCallback (callback) {
    let _v = null
    const _l = {}
    for (let key in this.#ranking) {
      _l['location'] = key.split('-')
      _l['ranking'] = this.#ranking[key]
    }

    this.#callbackP['resolve'](await callback(_l))
    await this.#callbackP['Promise'].then(v => {
      _v = v
    })

    this.#then && this.#then(_v)
  }

  /* 保存生成的事件 */
  #event

  /**
   * 为监听添加事件
   * @param {[string, string, number]} location
   * @param {number} duration
   * @param {boolean} showClose
   * @param {Function} callback
   */
  #addEvent (location, duration, showClose, callback) {
    const { mouseenter, mouseleave, mousedown, mouseup, click } = this.#event = this.#generateEvents(location, duration, showClose, callback)
    this.#rootBox.addEventListener('mouseenter', mouseenter)
    this.#rootBox.addEventListener('mouseleave', mouseleave)
    this.#rootBox.addEventListener('mousedown', mousedown)
    this.#rootBox.addEventListener('mouseup', mouseup)
    this.#rootBox.addEventListener('click', click)
  }

  /**
   * 删除监听的事件
   */
  #deleteEvent () {
    for (let key in this.#event) {
      this.#rootBox.removeEventListener(key, this.#event[key])
    }
  }

  /**
   * 提供给 NotificationList 使用的，用于修改通知位置的方法
   * @param {number} ranking 新位置
   */
  setLocation (ranking) {
    if (this.#isClose) {
      console.warn('[WARN]', 'Notification => 通知已关闭, setLocation 方法被拒绝')
      return
    }
    if (this.#isAccessList) {
      console.error('[ERROR]', 'Notification => 该通知没有启用自定义定位, 不能使用 setLocation 修改位置', this)
      return 'Notification => 该通知没有启用自定义定位, 不能使用 setLocation 修改位置'
    }
    console.log(this.#rootBox)
    let _l = []
    for (let key in this.#ranking) {
      _l = key.split('-')
    }
    _l.push(ranking)
    console.log('前', this.#location)
    this.#location['ranking'] = this.#ranking[_l[0] + '-' + _l[1]] = ranking
    console.log('后', this.#location)
    this.#setLocation(_l)
  }

  /* 保存then传入的函数: Function */
  #then = null

  /**
   * sCallback 用于处理 callback 的返回值
   * fCallback 用于处理错误
   * @param {Function} sCallback
   */
  then (sCallback) {
    if (this.#isClose) {
      console.warn('[WARN]', 'Notification => 通知已关闭, then 方法被拒绝')
      return
    }
    if (!this.#callbackP['Promise']) {
      console.error('[ERROR]', 'Notification => 该通知没有传入 callback 回调函数, 不能使用 then 获取返回值', this)
      return 'Notification => 该通知没有传入 callback 回调函数, 不能使用 then 获取返回值'
    }
    this.#then = sCallback
  }

  /**
   * 关闭 notification
   */
  close () {
    if (this.#isClose) {
      console.warn('[WARN]', 'Notification => 通知已关闭, close 方法被拒绝')
      return
    }
    let _l = []
    for (let key in this.#ranking) {
      _l = key.split('-')
      _l[2] = this.#ranking[key]
    }
    this.#remove(_l)
  }
}

export { Notification }
