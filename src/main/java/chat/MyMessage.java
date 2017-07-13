package chat;

import java.io.Serializable;

/**
 * Created by zhugongyi on 2017/7/12.
 */
public class MyMessage implements Serializable {

    private long createdAt = System.currentTimeMillis();

    private String uuid;

    private String content;

    private String source;

    private String target;

    public MyMessage() {

    }

    public MyMessage(String uuid, String content, String source, String target) {
        this.uuid = uuid;
        this.content = content;
        this.source = source;
        this.target = target;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MyMessage{");
        sb.append("createdAt=").append(createdAt);
        sb.append(", uuid='").append(uuid).append('\'');
        sb.append(", content='").append(content).append('\'');
        sb.append(", source='").append(source).append('\'');
        sb.append(", target='").append(target).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
