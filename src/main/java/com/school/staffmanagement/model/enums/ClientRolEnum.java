package com.school.staffmanagement.model.enums;

public enum ClientRolEnum {
    CLIENT("client"), ADMIN("admin"), SUPER("super");
    private String value;
    ClientRolEnum(String value) { this.value = value; }
    public String getValue() { return value; }
}
