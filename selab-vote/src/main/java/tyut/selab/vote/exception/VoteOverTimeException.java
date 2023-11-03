/**
 * Copyright (C), 2000-2023, XXX有限公司
 * FileName: VoteOverTimeException
 * Author: 22932
 * Date: 2023/11/3 12:54:53
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
 * @ClassName: VoteOverTimeException
 * @Description: java类描述
 * @Author: 22932
 * @Date: 2023/11/3 12:54:53
 */
public class VoteOverTimeException extends VoteException {
    public VoteOverTimeException() {
    }

    public VoteOverTimeException(String message) {
        super(message);
    }

    public VoteOverTimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public VoteOverTimeException(Throwable cause) {
        super(cause);
    }

    public VoteOverTimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

