/**
 * Copyright (C), 2000-2023, XXX有限公司
 * FileName: VoteDeletedException
 * Author: 22932
 * Date: 2023/11/3 12:57:51
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
 * @ClassName: VoteDeletedException
 * @Description: java类描述
 * @Author: 22932
 * @Date: 2023/11/3 12:57:51
 */
public class VoteDeletedException extends VoteException {
    public VoteDeletedException() {
    }

    public VoteDeletedException(String message) {
        super(message);
    }

    public VoteDeletedException(String message, Throwable cause) {
        super(message, cause);
    }

    public VoteDeletedException(Throwable cause) {
        super(cause);
    }

    public VoteDeletedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

