package com.example.task1.model.constants;

import com.example.task1.model.Role;

public  abstract class RoleConstatns {

    public static final Role ROLE_ADMIN = new  Role(1L, "ROLE_ADMIN");
    public static final Role ROLE_MODERATOR = new Role(2L, "ROLE_MODERATOR");
    public static final Role ROLE_USER = new Role(3L,"ROLE_USER");
 }
