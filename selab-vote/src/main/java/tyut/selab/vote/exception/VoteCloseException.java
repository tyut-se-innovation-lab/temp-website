package tyut.selab.vote.exception;

/**
 * @className: VoteCloseException
 * @author: lizhichao
 * @description: TODO
 * @date: 2023/11/1 12:12
 * @version: 1.0
 */
public class VoteCloseException extends VoteException{
    public VoteCloseException() {
    }

    public VoteCloseException(String message) {
        super(message);
    }

    public VoteCloseException(String message, Throwable cause) {
        super(message, cause);
    }

    public VoteCloseException(Throwable cause) {
        super(cause);
    }

    public VoteCloseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
