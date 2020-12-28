package com.example.demo.util;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.Base64Utils;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class RequestTester {

	private MockMvc mvc;
	
	private MediaType contentType;
	
	private HttpHeaders headers;
	
	private ObjectMapper mapper;
	
	public RequestTester(MockMvc mvc) throws Exception {
		this.mvc = mvc;
		this.contentType = MediaType.APPLICATION_JSON;
		this.headers = new HttpHeaders();
		this.mapper = new ObjectMapper();
	}
	
	public RequestTester auth(String username, String password) {
		String token = "Basic " + username + ":" + password;
		String encodedToken = Base64Utils.encodeToString(token.getBytes());
		this.headers.put("Authorization", List.of(encodedToken));
		return this;
	}
	
	public ServletResponse get(String path, HttpStatus expectedResponseStatus) throws Exception {
		ResultMatcher resultMatcher = MockMvcResultMatchers.status().is(expectedResponseStatus.value());
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.get(path)
				.contentType(this.contentType)
				.headers(this.headers);
		MvcResult mvcResult = this.mvc.perform(request).andExpect(resultMatcher).andReturn();
		this.headers.remove("Authorization");
		return new ServletResponse(mvcResult.getResponse());
	}
	
	public ServletResponse post(String path, Object body, HttpStatus expectedResponseStatus) throws Exception {
		if (body == null) {
			throw new RuntimeException("Body for POST HTTP method cannot be null (IETF reference).");
		}
		String content = mapper.writeValueAsString(body);
		ResultMatcher resultMatcher = MockMvcResultMatchers.status().is(expectedResponseStatus.value());
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.post(path)
				.contentType(this.contentType)
				.content(content)
				.headers(this.headers);
		MvcResult mvcResult = this.mvc.perform(request).andExpect(resultMatcher).andReturn();
		this.headers.remove("Authorization");
		return new ServletResponse(mvcResult.getResponse());
	}
	
	public ServletResponse put(String path, Object body, HttpStatus expectedResponseStatus) throws Exception {
		if (body == null) {
			throw new RuntimeException("Body for PUT HTTP method cannot be null (IETF reference).");
		}
		String content = mapper.writeValueAsString(body);
		ResultMatcher resultMatcher = MockMvcResultMatchers.status().is(expectedResponseStatus.value());
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.put(path)
				.contentType(this.contentType)
				.content(content)
				.headers(this.headers);
		MvcResult mvcResult = this.mvc.perform(request).andExpect(resultMatcher).andReturn();
		this.headers.remove("Authorization");
		return new ServletResponse(mvcResult.getResponse());
	}
	
	public ServletResponse patch(String path, Object body, HttpStatus expectedResponseStatus) throws Exception {
		if (body == null) {
			throw new RuntimeException("Body for PATCH HTTP method cannot be null (IETF reference).");
		}
		String content = mapper.writeValueAsString(body);
		ResultMatcher resultMatcher = MockMvcResultMatchers.status().is(expectedResponseStatus.value());
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.patch(path)
				.contentType(this.contentType)
				.content(content)
				.headers(this.headers);
		MvcResult mvcResult = this.mvc.perform(request).andExpect(resultMatcher).andReturn();
		this.headers.remove("Authorization");
		return new ServletResponse(mvcResult.getResponse());
	}
	
	public ServletResponse delete(String path, HttpStatus expectedResponseStatus) throws Exception {
		return this.delete(path, null, expectedResponseStatus);
	}
	
	public ServletResponse delete(String path, Object body, HttpStatus expectedResponseStatus) throws Exception {
		String content = null;
		if (body != null) {
			content = mapper.writeValueAsString(body);
		}
		ResultMatcher resultMatcher = MockMvcResultMatchers.status().is(expectedResponseStatus.value());
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.patch(path)
				.contentType(this.contentType)
				.content(content)
				.headers(this.headers);
		MvcResult mvcResult = this.mvc.perform(request).andExpect(resultMatcher).andReturn();
		this.headers.remove("Authorization");
		return new ServletResponse(mvcResult.getResponse());
	}
	
	public ServletResponse head(String path, HttpStatus expectedResponseStatus) throws Exception {
		ResultMatcher resultMatcher = MockMvcResultMatchers.status().is(expectedResponseStatus.value());
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.head(path)
				.contentType(this.contentType)
				.headers(this.headers);
		MvcResult mvcResult = this.mvc.perform(request).andExpect(resultMatcher).andReturn();
		this.headers.remove("Authorization");
		return new ServletResponse(mvcResult.getResponse());
	}
	
	public ServletResponse options(String path, HttpStatus expectedResponseStatus) throws Exception {
		ResultMatcher resultMatcher = MockMvcResultMatchers.status().is(expectedResponseStatus.value());
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.options(path)
				.contentType(this.contentType)
				.headers(this.headers);
		MvcResult mvcResult = this.mvc.perform(request).andExpect(resultMatcher).andReturn();
		this.headers.remove("Authorization");
		return new ServletResponse(mvcResult.getResponse());
	}
	
}
