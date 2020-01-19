package com.santiago.portal;

import com.santiago.commons.util.JsonUtil;
import com.santiago.portal.entity.dto.request.MenuInsertRequest;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MenuCtrlTest extends BaseJunit {


    private MenuInsertRequest createMenuInsertRequest() {
        MenuInsertRequest request = new MenuInsertRequest();
        request.setInsertMenuName("添加菜单");
        request.setInsertMenuLevel(Short.valueOf("3"));
        request.setInsertMenuUrl("/pms/menu/page");
        request.setInsertPid(2L);
        return request;
    }

    private void mock(MenuInsertRequest request, String code) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/pms/menu/insert")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JsonUtil.obj2JsonStrExcludeNull(request))
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().string(Matchers.containsString(code)));
    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void successCase() throws Exception {
        MenuInsertRequest request = createMenuInsertRequest();
        mock(request, "000000");
    }

}
