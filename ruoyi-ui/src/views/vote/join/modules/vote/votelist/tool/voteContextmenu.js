/** 创建vote右键上下文菜单 */

import './voteContextmenu.scss'

class VoteContextmenuInfo {
  /** {string} equipment 设备类型 */
  _equipment
  /** {{string: Function}} contextmenuList 菜单列表 */
  _contextmenuList
  /** {HTMLDivElement} contextmenu 上下文菜单节点 */
  _contextmenu
}

class VoteContextmenu extends VoteContextmenuInfo{

  /**
   * @param {string} equipment
   * @param {List<{'message': string, 'onclick': Function}>} contextmenuList
   */
  constructor ({ equipment, contextmenuList }) {
    super()
    this._equipment = equipment
    this._contextmenuList = contextmenuList
    this.#create()
  }

  /**
   * 创建
   */
  #create () {
    /*
     * this.userId === this.loginUserId 撤回
     * this.userId != this.loginUserId 举报
     * 管理员  删除结束的，处理冻结的（内部）
     */
    const contextmenu = document.createElement('div')
    contextmenu.classList.add('xj-contextmenu')
    contextmenu.classList.add('xj-' + this._equipment)

    this._contextmenuList.forEach(item => {
      const menu = document.createElement('div')
      menu.classList.add('xj-contextmenu-menu')
      menu.appendChild(document.createTextNode(item['message']))
      menu.addEventListener('click', item['onclick'])
      contextmenu.appendChild(menu)
    })

    this._contextmenu = contextmenu

    document.body.appendChild(contextmenu)
  }

  /**
   * 出现
   * @param {number} x
   * @param {number} y
   */
  appear (x, y) {
    this._contextmenu.style['display'] = 'block'
    const { width, height } = getComputedStyle(this._contextmenu)
    const menuWidth = parseInt(width)
    const menuHeight = parseInt(height)
    const html = document.getElementsByTagName('html')[0]
    if (menuWidth + x >= html.clientWidth - 10) {
      x -= menuWidth
    }
    if (menuHeight + y >= html.clientHeight - 10) {
      y -= menuHeight
    }
    this._contextmenu.style['top'] = y + 'px'
    this._contextmenu.style['left'] = x + 'px'
    requestAnimationFrame(() => {
      this._contextmenu.style['opacity'] = '1'
    })
  }

  disappear () {
    this._contextmenu.style['opacity'] = '0'
    setTimeout(() => {
      this._contextmenu.remove()
    }, 200)
  }
}


export default VoteContextmenu
