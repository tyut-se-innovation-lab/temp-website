//分页组件的api
import request from '@/utils/request';
import { createPaginationIndexArr } from './util.js';
class Pagination {
    /**
    * @param {number} pagerCount 设置最大页码按钮数。 页码按钮的数量，当总页数超过该值时会折叠
    * @param {number} currentPage 当前页数,默认为第一页	
    * @param {number} totalPages 总页数 
   */
    constructor({ currentPage, pagerCount, totalPages }) {
        if (pagerCount % 2 == 0) {
            pagerCount--;
        }
        // this.root = root;
        this.pageSize = pageSize || 10;
        this.total = total;
        this.pagerCount = pagerCount || 7;
        this.currentPage = currentPage || 1;
        // this.currentChange = currentChange;

        this.totalPages = totalPages;
        this.initPagination();
    }

    initPagination() {
        this.indexArr = createPaginationIndexArr(this.currentPage, this.pagerCount, this.totalPages);
        this.indexArr = this.fixIndexArr(this.indexArr);
    }

    fixIndexArr(indexArr) {
        for (let i = 0; i < indexArr.length; i++) {
            if (i != indexArr.length - 1 && indexArr[i] + 1 !== indexArr[i + 1]) {
                indexArr.splice(i, 0, '...');
            }
        }
        return indexArr;
    }

    getPageData() {
        return request({

        })
    }
}

moudle.exports = {
    Pagination
}