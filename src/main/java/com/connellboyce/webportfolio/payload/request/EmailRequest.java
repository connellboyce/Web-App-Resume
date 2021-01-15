package com.connellboyce.webportfolio.payload.request;

import javax.validation.constraints.NotNull;

public class EmailRequest {
    @NotNull
    private String returnEmail;

    @NotNull
    private String emailSubject;

    @NotNull
    private String content;

    public String getReturnEmail() {
        return returnEmail;
    }

    public void setReturnEmail(String returnEmail) {
        this.returnEmail = returnEmail;
    }

    public String getEmailSubject() {
        return emailSubject;
    }

    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
