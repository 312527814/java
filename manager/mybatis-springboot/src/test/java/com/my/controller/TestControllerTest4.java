package com.my.controller;

import com.my.MybatisSpringboot;
import com.my.mapper.flowerMapper;
import com.my.service.MyService;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 描 述: <br/>
 * 作 者: liuliang14<br/>
 * 创 建:2022年03月14⽇<br/>
 * 版 本:v1.0.0<br> *
 * 历 史: (版本) 作者 时间 注释 <br/>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MybatisSpringboot.class)
public class TestControllerTest4 {

    @Autowired
    private TestController testController;
    private MockMvc mvc;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(testController)
                .build();
    }


    @Transactional
    @Test
    public void aliveTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/alive2"))
                .andExpect(status().isOk()).andDo(print());
    }


}