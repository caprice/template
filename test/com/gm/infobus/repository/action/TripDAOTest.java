package com.gm.infobus.repository.action;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;  
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;  
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;  
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.gm.infobus.repository.BaseConfigurtionTest;

public class TripDAOTest extends BaseConfigurtionTest {
	
	  @Autowired  
	    private WebApplicationContext wac;  
	  
	    private MockMvc mockMvc;  
	    @Before  
	    public void setup() {  
	        // webAppContextSetup 注意上面的static import  
	        // webAppContextSetup 构造的WEB容器可以添加filter 但是不能添加listenCLASS  
	        // WebApplicationContext context =  
	        // ContextLoader.getCurrentWebApplicationContext();  
	        // 如果控制器包含如上方法 则会报空指针  
	        this.mockMvc = webAppContextSetup(this.wac).build();  
	    }  
	    
	    @Test  
	    public void test() throws Exception {  
	        mockMvc.perform((post("/crs/user/login.do").param("userName", "spring01").param("password", "password"))).andExpect(status().isOk())  
	                .andDo(print())  
	                .andExpect(model().attributeHasNoErrors("1212"));  
	    }  
	
}
