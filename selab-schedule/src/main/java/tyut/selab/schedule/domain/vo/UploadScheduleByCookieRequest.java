package tyut.selab.schedule.domain.vo;

public class UploadScheduleByCookieRequest {
    private String asp;
    private String token;

    public String getAsp() {
        return asp;
    }

    public void setAsp(String asp) {
        this.asp = asp;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
