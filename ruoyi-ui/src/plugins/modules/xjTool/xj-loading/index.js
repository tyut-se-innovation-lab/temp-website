import './index.css'

class LoadingInfo {
  /** {HTMLDivElement} div 要添加加载的节点 */
  _div
  /** {string} color */
  _color
  /** {HTMLDivElement} loading 加载节点 */
  _loading
}

class Loading extends LoadingInfo {

  /**
   * @param {HTMLDivElement} div 要添加加载的节点
   * @param {string} color
   */
  constructor (div, color = '#ec9595') {
    super()
    this._div = div
    this._color = color
    this.#create()
  }

  /**
   * 创建加载
   */
  #create () {
    if (getComputedStyle(this._div)['position'] === 'static') this._div.style['position'] = 'relative'
    this._loading = document.createElement('div')
    this._loading.classList.add('xj-loader')
    const primaryLoading = document.createElement('div')
    primaryLoading.classList.add('xj-jimu-primary-loading')
    primaryLoading.style.setProperty('--color', this._color)
    this._loading.appendChild(primaryLoading)
  }

  addLoading () {
    requestAnimationFrame(() => {
      // this._loading.style['transform'] = 'scaleX(1)'
      this._loading.style['opacity'] = '1'
    })
    this._div.appendChild(this._loading)
  }

  removeLoading () {
    requestAnimationFrame(() => {
      // this._loading.style['transform'] = 'scaleX(0)'
      this._loading.style['opacity'] = '0'
    })
    setTimeout(() => {
      this._loading.remove()
    }, 300)
  }

}

export default Loading



