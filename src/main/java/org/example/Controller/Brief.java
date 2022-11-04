package org.example.Controller;

import java.time.LocalDate;

public class Brief {

    public String briefName;
    public StringBuilder briefBody;
    public Integer deadline;
    public String formateurName;
    public Boolean destrubier;

    public String promotionName;

    public Brief(String briefName, StringBuilder briefBody, Integer deadline, String formateurName, Boolean destrubier, String promotionName) {
        this.briefName = briefName;
        this.briefBody = briefBody;
        this.deadline = deadline;
        this.formateurName = formateurName;
        this.destrubier = destrubier;
        this.promotionName = promotionName;
    }

    public String getBriefName() {
        return briefName;
    }

    public void setBriefName(String briefName) {
        this.briefName = briefName;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

    public StringBuilder getBriefBody() {
        return briefBody;
    }

    public void setBriefBody(StringBuilder briefBody) {
        this.briefBody = briefBody;
    }

    public Integer getDeadline() {
        return deadline;
    }

    public void setDeadline(Integer deadline) {
        this.deadline = deadline;
    }

    public String getFormateurName() {
        return formateurName;
    }

    public void setFormateurName(String formateurName) {
        this.formateurName = formateurName;
    }

    public Boolean getDestrubier() {
        return destrubier;
    }

    public void setDestrubier(Boolean destrubier) {
        this.destrubier = destrubier;
    }
}
