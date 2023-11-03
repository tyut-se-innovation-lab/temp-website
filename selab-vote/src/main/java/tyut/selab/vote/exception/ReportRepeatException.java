/**
 * Copyright (C), 2000-2023, XXX有限公司
 * FileName: ReportRepeatException
 * Author: 22932
 * Date: 2023/11/3 16:39:14
 * Description:
 * <p>
 * History:
 * <author> 作者姓名
 * <time> 修改时间
 * <version> 版本号
 * <desc> 版本描述
 */
package tyut.selab.vote.exception;

/**
 * @ClassName: ReportRepeatException
 * @Description: java类描述
 * @Author: 22932
 * @Date: 2023/11/3 16:39:14
 */
public class ReportRepeatException extends VoteException {
    public ReportRepeatException() {
    }

    public ReportRepeatException(String message) {
        super(message);
    }

    public ReportRepeatException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReportRepeatException(Throwable cause) {
        super(cause);
    }

    public ReportRepeatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

