package com.aprendizado.dscommerce.dto;

public class FieldMessage {

    private String fiedlName;
    private String message;

    public FieldMessage(String fiedlName, String message) {
        this.fiedlName = fiedlName;
        this.message = message;
    }

    public String getFiedlName() {
        return fiedlName;
    }

    public String getMessage() {
        return message;
    }
}
