package tyut.selab.vote.enums;

import tyut.selab.vote.tools.AnonymousControl;

public class User {
    private String userId;

    public User(String userId) {
        this.userId = userId;
    }

    public User() {
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return  userId;
    }
}
