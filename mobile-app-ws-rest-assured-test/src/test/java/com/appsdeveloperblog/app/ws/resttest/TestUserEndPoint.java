package com.appsdeveloperblog.app.ws.resttest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
class TestUserEndPoint {

	private final String JSON = "application/json";
	private final String CONTEXT_PATH="/mobile-app-ws";
	@BeforeEach
	void setUp() throws Exception {
		RestAssured.baseURI="http://localhost";
		RestAssured.port=8888;
	}

	

}
