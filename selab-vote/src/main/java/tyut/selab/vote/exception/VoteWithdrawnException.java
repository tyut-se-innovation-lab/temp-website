/**
 * Copyright (C), 2000-2023, XXX有限公司
 * FileName: VoteWithdrawnException
 * Author: 22932
 * Date: 2023/11/3 16:56:19
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
 * @ClassName: VoteWithdrawnException
 * @Description: java类描述
 * @Author: 22932
 * @Date: 2023/11/3 16:56:19
 */
public class VoteWithdrawnException extends VoteException {
    public VoteWithdrawnException() {
    }

    public VoteWithdrawnException(String message) {
        super(message);
    }

    public VoteWithdrawnException(String message, Throwable cause) {
        super(message, cause);
    }

    public VoteWithdrawnException(Throwable cause) {
        super(cause);
    }

    public VoteWithdrawnException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

