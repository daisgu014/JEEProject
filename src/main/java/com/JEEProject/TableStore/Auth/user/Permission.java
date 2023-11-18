package com.JEEProject.TableStore.Auth.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    MANAGER_READ("management:read"),
    MANAGER_UPDATE("management:update"),
    MANAGER_CREATE("management:create"),
    MANAGER_DELETE("management:delete"),
    SALE_READ("sale:read"),
    SALE_UPDATE("sale:update"),
    SALE_DELETE("sale:delete"),
    SALE_CREATE("sale:create"),

    ;

    @Getter
    private final String permission;
}

