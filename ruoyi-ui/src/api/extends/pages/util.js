/**
 * @description 得到分页数组 indexArr，如[1,2,3,4,10],[1,3,4,5,10],[1,7,8,9,10]
 * @param {number} currentPage 当前页数,默认为第一页	
 * @param {number} totalPages 总的页码数
 * @param {number} pagerCount 设置最大页码按钮数。 页码按钮的数量，当总页数超过该值时会折叠
 * @return {Array} 分页数组 indexArr
*/
const createPaginationIndexArr = (currentPage, totalPages, pagerCount) => {
    let indexArr = [];
    if (totalPages <= pagerCount) {
        for (let i = 0; i < totalPages; i++) {
            indexArr[i] = i + 1;
        }
    } else if (currentPage <= totalPages / 2) {
        if (currentPage <= pagerCount - 2) {
            for (let i = 0; i < totalPages; i++) {
                indexArr[i] = i + 1;
                if (i === pagerCount - 2) {
                    break;
                }
            }
            indexArr[indexArr.length] = totalPages;
        } else {
            indexArr[0] = 1;

            let index666 = Math.floor(pagerCount / 2);
            indexArr[index666] = currentPage;
            for (let i = index666 - 1; i > 0; i--) {

                indexArr[i] = indexArr[i + 1] - 1;

            }
            for (let i = index666 + 1; i < pagerCount; i++) {
                indexArr[i] = indexArr[i - 1] + 1;
            }
            indexArr[pagerCount - 1] = totalPages;
        }
    } else if (currentPage > totalPages / 2) {
        indexArr[0] = 1;
        if (totalPages - currentPage < pagerCount - 2) {
            for (let i = 0; i < totalPages; i++) {
                indexArr[pagerCount - i - 1] = totalPages - i;
                if (i === pagerCount - 2) {
                    break;
                }
            }

        } else {
            let index666 = Math.floor(pagerCount / 2);
            indexArr[index666] = currentPage;
            for (let i = index666 - 1; i > 0; i--) {

                indexArr[i] = indexArr[i + 1] - 1;

            }
            for (let i = index666 + 1; i < pagerCount; i++) {
                indexArr[i] = indexArr[i - 1] + 1;
            }
            indexArr[pagerCount - 1] = totalPages;
        }
    }
    console.log(indexArr);
    return indexArr;
}


module.exports = {
    createPaginationIndexArr
}