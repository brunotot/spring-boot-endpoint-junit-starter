package com.example.demo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.util.RequestTester;
import com.example.demo.util.ServletResponse;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DemoControllerTests {

	@Autowired
	private RequestTester requestTester;

	@Test
	public void getTest_testGetHttpMethod_shouldReturnOk() throws Exception {
		ServletResponse response = requestTester.get("/test", HttpStatus.OK);
		String expected = "Test";
		String actual = response.getContent(String.class);
		Assert.assertEquals(expected, actual);
	}
	
}
