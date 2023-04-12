//本文件夹用来创建过滤数据的函数

class Filter {
    constructor() {
        if (new.target === Filter) {
            throw new Error("Filter类不能被实例化");
        }
    }
}

export {
    Filter
}