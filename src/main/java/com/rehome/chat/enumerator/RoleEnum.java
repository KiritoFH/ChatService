package com.rehome.chat.enumerator;

import java.util.Arrays;

public enum RoleEnum {

	ROLE_USER("ROLE_USER"), ROLE_HANDYMAN("ROLE_HANDYMAN");

    private final String role;
    RoleEnum(String role) {
        this.role = role;
    }

    public String getString() {
        return this.role;
    }

    public static RoleEnum getRoleEnumByString(String role) {
        return Arrays.stream(RoleEnum.values())
            .filter(e -> e.role.equalsIgnoreCase(role))
            .findFirst()
            .get();
    }

}
