package tyut.selab.vote.exception;

/**
 * @className: VoteDataUnknownException
 * @author: lizhichao
 * @description: TODO
 * @date: 2023/11/1 12:10
 * @version: 1.0
 */
public class VoteDataUnknownException extends VoteException{
    public VoteDataUnknownException() {
    }

    public VoteDataUnknownException(String message) {
        super(message);
    }

    public VoteDataUnknownException(String message, Throwable cause) {
        super(message, cause);
    }

    public VoteDataUnknownException(Throwable cause) {
        super(cause);
    }

    public VoteDataUnknownException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
