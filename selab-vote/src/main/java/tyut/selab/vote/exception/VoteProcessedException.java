/**
 * Copyright (C), 2000-2023, XXX有限公司
 * FileName: VoteProcessedException
 * Author: 22932
 * Date: 2023/11/3 12:56:07
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
 * @ClassName: VoteProcessedException
 * @Description: java类描述
 * @Author: 22932
 * @Date: 2023/11/3 12:56:07
 */
public class VoteProcessedException extends VoteException {
    public VoteProcessedException() {
    }

    public VoteProcessedException(String message) {
        super(message);
    }

    public VoteProcessedException(String message, Throwable cause) {
        super(message, cause);
    }

    public VoteProcessedException(Throwable cause) {
        super(cause);
    }

    public VoteProcessedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

