package com.santiago.cg.entity.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "persistent_logins")
public class PersistentLogins {
    @Id
    private String series;

    private String username;

    private String token;

    @Column(name = "last_used")
    private Date lastUsed;

    /**
     * @return series
     */
    public String getSeries() {
        return series;
    }

    /**
     * @param series
     */
    public void setSeries(String series) {
        this.series = series;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return last_used
     */
    public Date getLastUsed() {
        return lastUsed;
    }

    /**
     * @param lastUsed
     */
    public void setLastUsed(Date lastUsed) {
        this.lastUsed = lastUsed;
    }
}