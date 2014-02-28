package com.gm.infobus.repository.action;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;  
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;  
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;  
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Calendar;

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
	    
	    @Test  
	    public void test1() throws Exception { 
	    	Calendar c =  Calendar.getInstance();
	    	long now = Calendar.getInstance().getTimeInMillis();
	    	System.out.println("now:" + now);
	    	c.clear();
	    	c.set(Calendar.YEAR, 2014);
	    	c.set(Calendar.MONTH, 4);
	    	c.set(Calendar.DAY_OF_MONTH, 1);
	    	c.set(Calendar.HOUR, 0);
	    	c.set(Calendar.MINUTE, 0);
	    	c.set(Calendar.SECOND, 0);
	    	long toTime = c.getTimeInMillis();
	    	System.out.println("tom:" + toTime);
	    	c.clear();
	    	c.set(Calendar.YEAR, 2014);
	    	c.set(Calendar.MONTH, 3);
	    	c.set(Calendar.DAY_OF_MONTH, 27);
	    	c.set(Calendar.HOUR, 23);
	    	c.set(Calendar.MINUTE, 59);
	    	c.set(Calendar.SECOND, 59);
	    	long yTime = c.getTimeInMillis();
	    	System.out.println("yTime:" + yTime);
	    }  
	
}
