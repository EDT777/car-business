package com.edt.service.impl;

import com.edt.service.IPermissionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class PermissionServiceImplTest {

    @Autowired
    private IPermissionService permissionService;
    @Test
    public void reload() {
        permissionService.reload();
    }
}