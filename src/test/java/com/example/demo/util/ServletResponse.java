package com.example.demo.util;

import org.springframework.mock.web.MockHttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ServletResponse {
	
	private MockHttpServletResponse response;
	
	private ObjectMapper mapper;
	
	public ServletResponse(MockHttpServletResponse response) {
		this.response = response;
		this.mapper = new ObjectMapper();
	}
	
	public <T> T getContent(Class<T> toValue) throws Exception {
		return mapper.convertValue(this.response.getContentAsString(), toValue);
	}
	
	public String getHeader(String headerName) {
		return this.response.getHeader(headerName);
	}

	public String getErrorMessage() {
		return this.response.getErrorMessage();
	}
	
}
