package cn.ecar.insurance.dao.base;

/**
 *
 * @author ding
 * @date 2018/1/22
 */

public class BaseGson {

    /**
     * login_status : 0
     * responseCode : EC0000
     * sessionId : 6A974108B47EFCB47C98E2F61AA87E08
     * responseMsg : 请求成功
     * status: 0
     */

    private String login_status;
    private String responseCode;
    private String sessionId;
    private String responseMsg;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLogin_status() {
        return login_status;
    }

    public void setLogin_status(String login_status) {
        this.login_status = login_status;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }

    @Override
    public String toString() {
        return "BaseGson{" +
                "login_status='" + login_status + '\'' +
                ", responseCode='" + responseCode + '\'' +
                ", sessionId='" + sessionId + '\'' +
                ", responseMsg='" + responseMsg + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
