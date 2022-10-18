package tyut.selab.schedule.domain.vo;

public class UploadScheduleByCookieRequest {
    private String cookie;
    private String token;

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
