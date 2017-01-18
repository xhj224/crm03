package com.tz.testddl;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * project_name : crm04
 * user : xhj224
 * date : 2017/1/14 15:59
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestDDL {

    @Resource
    private LocalSessionFactoryBean lsfb;

    @Test
    public void testDDL() {
        Configuration cfg = lsfb.getConfiguration();
        SchemaExport export = new SchemaExport(cfg);
        export.create(true, true);
    }
}
