/**
 * Copyright (C), 2000-2023, XXX有限公司
 * FileName: VoteFreezedException
 * Author: 22932
 * Date: 2023/11/3 12:53:17
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
 * @ClassName: VoteFreezedException
 * @Description: java类描述
 * @Author: 22932
 * @Date: 2023/11/3 12:53:17
 */
public class VoteFreezedException extends VoteException{
    public VoteFreezedException() {
    }

    public VoteFreezedException(String message) {
        super(message);
    }

    public VoteFreezedException(String message, Throwable cause) {
        super(message, cause);
    }

    public VoteFreezedException(Throwable cause) {
        super(cause);
    }

    public VoteFreezedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

