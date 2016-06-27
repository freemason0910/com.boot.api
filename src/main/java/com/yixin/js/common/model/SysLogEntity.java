package com.yixin.js.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by apple on 16/6/6.
 */
@Entity
@Table(name = "sys_log")
public class SysLogEntity extends BaseEntity {


    private static final long serialVersionUID = -6648673619180857926L;

    @Column(name = "type")
    private String type;
    @Column(name = "remote_addr")
    private String remoteAddr;
    @Column(name = "user_agent")
    private String userAgent;
    @Column(name = "request_uri")
    private String requertUri;
    @Column(name = "method")
    private  String method;
    @Column(name = "params")
    private  String params;
    @Column(name = "exception")
    private String exception;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemoteAddr() {
        return remoteAddr;
    }

    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getRequertUri() {
        return requertUri;
    }

    public void setRequertUri(String requertUri) {
        this.requertUri = requertUri;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }
}