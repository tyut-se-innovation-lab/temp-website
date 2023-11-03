package tyut.selab.vote.exception;

/**
 * @className: VoteException
 * @author: lizhichao
 * @description: TODO
 * @date: 2023/10/11 18:05
 * @version: 1.0
 */
public class VoteException extends RuntimeException{
    public VoteException() {
    }

    public VoteException(String message) {
        super(message);
    }

    public VoteException(String message, Throwable cause) {
        super(message, cause);
    }

    public VoteException(Throwable cause) {
        super(cause);
    }

    public VoteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
