package com.santiago.rcs.controller;


import com.google.gson.Gson;
import com.santiago.commons.dto.req.UnionInnerReq;
import com.santiago.commons.util.JsonUtil;
import org.apache.http.Header;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ClearBookCtrlTest {

    @Autowired
    ClearBookCtrl clearBookCtrl;
    @Autowired
    MockMvc mockMvc;

    @Test
    public void receiveParamsError() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/api/clearBooks")
                .content("{}")
                .contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("400"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void update() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch("/api/clearBooks").content(""))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("200"))
                .andDo(MockMvcResultHandlers.print());
    }
}