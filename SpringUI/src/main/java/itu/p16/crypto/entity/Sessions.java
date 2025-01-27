package itu.p16.crypto.entity;

import jakarta.persistence.*;

@Entity
public class Sessions {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false, length = 255)
    private String id;
    @Basic
    @Column(name = "user_id", nullable = true)
    private Long userId;
    @Basic
    @Column(name = "ip_address", nullable = true, length = 45)
    private String ipAddress;
    @Basic
    @Column(name = "user_agent", nullable = true, length = -1)
    private String userAgent;
    @Basic
    @Column(name = "payload", nullable = false, length = -1)
    private String payload;
    @Basic
    @Column(name = "last_activity", nullable = false)
    private Integer lastActivity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public Integer getLastActivity() {
        return lastActivity;
    }

    public void setLastActivity(Integer lastActivity) {
        this.lastActivity = lastActivity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sessions sessions = (Sessions) o;

        if (id != null ? !id.equals(sessions.id) : sessions.id != null) return false;
        if (userId != null ? !userId.equals(sessions.userId) : sessions.userId != null) return false;
        if (ipAddress != null ? !ipAddress.equals(sessions.ipAddress) : sessions.ipAddress != null) return false;
        if (userAgent != null ? !userAgent.equals(sessions.userAgent) : sessions.userAgent != null) return false;
        if (payload != null ? !payload.equals(sessions.payload) : sessions.payload != null) return false;
        if (lastActivity != null ? !lastActivity.equals(sessions.lastActivity) : sessions.lastActivity != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (ipAddress != null ? ipAddress.hashCode() : 0);
        result = 31 * result + (userAgent != null ? userAgent.hashCode() : 0);
        result = 31 * result + (payload != null ? payload.hashCode() : 0);
        result = 31 * result + (lastActivity != null ? lastActivity.hashCode() : 0);
        return result;
    }
}
