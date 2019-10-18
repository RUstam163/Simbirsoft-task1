package com.example.task1.service.impl;

import java.util.*;

public class RoleMapServise {

    public static List<String> getListRole(Long role) {
        Map<Long, List<String>> map = new HashMap<>();
        map.put(1L, Arrays.asList("ROLE_ADMIN", "ROLE_MODERATOR", "ROLE_USER"));
        map.put(2L, Arrays.asList("ROLE_MODERATOR", "ROLE_USER"));
        map.put(3L, Collections.singletonList("ROLE_USER"));
        return map.get(role);
    }
}
