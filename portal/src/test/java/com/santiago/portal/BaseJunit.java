package com.santiago.portal;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author sean
 * @Description: ${TODO}
 * @date 18-6-22 下午2:50
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PortalApplication.class)
public class BaseJunit {

    protected MockMvc mockMvc;

    @Autowired
    WebApplicationContext context;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
}