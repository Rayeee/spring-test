package chat;

import java.io.Serializable;

/**
 * Created by zhugongyi on 2017/7/12.
 */
public class ShakeHandDto implements Serializable {

    private String account;

    private String host;

    private int port;

    public ShakeHandDto() {

    }

    public ShakeHandDto(String account, String host, int port) {
        this.account = account;
        this.host = host;
        this.port = port;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ShakeHandDto{");
        sb.append("account='").append(account).append('\'');
        sb.append(", host='").append(host).append('\'');
        sb.append(", port=").append(port);
        sb.append('}');
        return sb.toString();
    }
}
