package com.example.task1.service.impl;

import com.example.task1.model.Role;
import com.example.task1.repository.RoleRepository;
import com.example.task1.service.RoleService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RoleServiceImplTest {

    @Mock
    RoleRepository roleRepository;

    @InjectMocks
    RoleService roleService = new RoleServiceImpl(roleRepository);

    @Before
    public void setUp() {
        Mockito.when(roleRepository.getRoleById(1L)).thenReturn(new Role(1L, "ROLE_TEST"));
    }

    @Test
    public void getRoleByIdTest() {
        Role testRole = roleService.getRoleById(1L);
        Assert.assertEquals(1, testRole.getId().intValue());
        Assert.assertEquals("ROLE_TEST", testRole.getRole());
    }
}