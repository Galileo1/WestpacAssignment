package org.galileo1.bdd.datamodel;

public class InfoMessageDataModel {


    private String field;
    private String message;
    public InfoMessageDataModel(String field, String message) {
        this.setField(field);
        this.setMessage(message);
    }

    public String getField() {
        return this.field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}