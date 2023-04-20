//本文件提供下载方法

class Download {
    constructor() {

    }

    /**
     * 通过Blob的形式下载
     * @param {String} filetype 文件类型
     * @param {String} data Blob字符串
     * @param {String} fileName 文件名称
     */
    downloadBlob(data, filetype, fileName) {
        const blob = new Blob(new Array(data), {
            type: filetype
        });
        if (window.navigator.msSaveOrOpenBlob) {
            navigator.msSaveBlob(blob, fileName);
        } else {
            let link = document.createElement("a");
            link.href = window.URL.createObjectURL(blob);
            link.download = fileName;
            link.click();
            window.URL.revokeObjectURL(link.href);
        }
    }
}
