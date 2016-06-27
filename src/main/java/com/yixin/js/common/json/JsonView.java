package com.yixin.js.common.json;

import javax.xml.bind.annotation.XmlRootElement;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@XmlRootElement
public class JsonView
{
    private Logger logger;
    private String status;
    private Object content;
    private String errorCode;
    private String message;

    public JsonView()
    {
        this.logger = LoggerFactory.getLogger(super.getClass());
    }

    public String getStatus()
    {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getContent() {
        return this.content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void successPack(Object result)
    {
        setContent(result);
        setStatus("success");
    }

    public void messagePack(String message)
    {
        this.logger.info(message);
        setStatus("failed");
        setMessage(message);
    }

    public void messagePack(String message, String errorCode)
    {
        this.logger.info(message);
        setStatus("failed");
        setMessage(message);
        setErrorCode(errorCode);
    }

    public void errorPack(String errorLog, Exception e)
    {
        setErrorCode("system_error");
        setContent("");
        setStatus("error");
        this.logger.error(errorLog, e);
    }

    public void failedPack(String errCode)
    {
        setErrorCode(errCode);
        setContent("");
        setStatus("failed");
        this.logger.info(" errCode : " + errCode);
    }
}