//分页组件的api
import { createPaginationIndexArr } from './util.js';
class Pagination {
    /**
    * @param {number} pagerCount 设置最大页码按钮数。 页码按钮的数量，当总页数超过该值时会折叠
    * @param {number} currentPage 当前页数,默认为第一页	
    * @param {number} totalPages 总页数 
   */
    constructor({ currentPage, pagerCount, totalPages }) {
        if (pagerCount && pagerCount % 2 == 0) {
            pagerCount--;
        }
        this.totalPages = totalPages;
        this.pagerCount = pagerCount;
        this.currentPage = currentPage;

        this.initPagination();
    }

    /**
     * 初始化按钮数组
     * @returns {Array} 按钮数组
     */
    initPagination() {
        this.indexArr = createPaginationIndexArr(this.currentPage, this.totalPages, this.pagerCount);
        return this.fixIndexArr(this.indexArr);
    }

    /**
     * 修复按钮数组(自定义)
     * @param {Array} indexArr 按钮数组 
     * @returns 
     */
    fixIndexArr(indexArr) {
        let arr = [];   //记录数组
        for (let i = 0; i < indexArr.length; i++) {
            if (i !== 0 && indexArr[i] - 1 !== indexArr[i - 1]) {
                //存储相对于数组最后元素的下标
                arr[arr.length] = indexArr.length - 1 - i;
            }
        }
        //splice使用前插法，记录相对于最后一个元素的下标
        //可以使得元素在插入时不受数组变化的干扰
        //数组 1 2 3 4 5
        //下标 4 3 2 1 0
        arr.map((index) => { indexArr.splice(indexArr.length - 1 - index, 0, '...') })
        return indexArr;
    }
}

export {
    Pagination
}