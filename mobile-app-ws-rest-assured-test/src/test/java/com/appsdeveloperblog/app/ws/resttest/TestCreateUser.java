package com.appsdeveloperblog.app.ws.resttest;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

@TestMethodOrder(MethodOrderer.Alphanumeric.class )
class TestCreateUser {

	private final String JSON = "application/json";
	private final String CONTEXT_PATH="/mobile-app-ws";
	private static String userId;
	private static List<Map<String, String>> addresses;
	private final String EMAIL_ADDRESS = "ssergey.kargsopdoslofkv@swiftdeveloperblog.com";
	
	List<Map<String, String>> storedAddresses;
	@BeforeEach  
	void setUp() throws Exception {
		RestAssured.baseURI="http://localhost";
		RestAssured.port=8080;
	}

	@Test
	void testCreateUser() {
		
		List<Map<String, Object>> userAddresses = new ArrayList<>();

        Map<String, Object> shippingAddress = new HashMap<>();
        shippingAddress.put("city", "Vancouver");
        shippingAddress.put("country", "Canada");
        shippingAddress.put("streetName", "123 Street name");
        shippingAddress.put("postalCode", "123456");
        shippingAddress.put("type", "shipping");
        
        Map<String, Object> billingAddress = new HashMap<>();
        billingAddress.put("city", "Vancouver");
        billingAddress.put("country", "Canada");
        billingAddress.put("streetName", "123 Street name");
        billingAddress.put("postalCode", "123456");
        billingAddress.put("type", "billing");

        userAddresses.add(shippingAddress);
        userAddresses.add(billingAddress);
        
        Map<String, Object> userDetails = new HashMap<>();
        userDetails.put("firstName", "gabi");
        userDetails.put("lastName", "Kargopolov");
        userDetails.put("email", "ssergey.kasfsddfdfxrdsgsodddperwdsdssblog.com");
        userDetails.put("password", "123");
        userDetails.put("addresses", userAddresses);
		
		
		
		
		Response response= given().
		contentType("application/json").
		accept("application/json").
		body(userDetails).
		when().
		post(CONTEXT_PATH+"/users").
		then().
		statusCode(200).
		contentType("application/json").
		extract().
		response();
		
		userId=response.jsonPath().getString("userId");
		assertNotNull(userId);
		
		String bodyString=response.body().asString();
		try {
			JSONObject responseBodyJson=new JSONObject(bodyString);
			JSONArray addresses= responseBodyJson.getJSONArray("addresses");
			assertNotNull(addresses);
			assertTrue(addresses.length()==2);
			String addressId = addresses.getJSONObject(0).getString("addressId");
			assertNotNull(addressId);
			assertTrue(addressId.length()==30);
			
			assertTrue(userId.length()==30);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			fail(e.getMessage()	);
		}
	}
	
	
	@Test
	void testGetUserDetails() {
		Response response = given().
		pathParam("id",userId).
		accept(JSON).
		when().
		get(CONTEXT_PATH+"/users/{id}").
		then().
		statusCode(200).
		contentType(JSON).
		extract().
		response();
		
		String userPublicId=response.jsonPath().getString("userId");
		String userEmail = response.jsonPath().getString("email");
		String firstName = response.jsonPath().getString("firstName");
        String lastName = response.jsonPath().getString("lastName");
        addresses = response.jsonPath().getList("addresses");
       String addressId = addresses.get(0).get("addressId");
        addresses.get(0).get("addressId");
		
		assertNotNull(userPublicId);
		assertNotNull(userEmail);
		assertNotNull(firstName);
		assertNotNull(lastName);
		//assertEquals(EMAIL_ADDRESS, userEmail);
		assertTrue(addresses.size()==2);
		assertTrue(addressId.length()==30);		
	}
	
	
	@Test
	void testUpdateUserDetails() {
		
		Map<String, Object> userDetails=new HashMap<>();
		userDetails.put("firstName", "Gabrel");
		userDetails.put("lastName", "Druuu");
		
		Response response = given().
				pathParam("id","RJLzWwXeFqP37qxjMGzTD3FMBMvw5l").
				contentType(JSON).
				accept(JSON).
				body(userDetails).
				when().
				put(CONTEXT_PATH+"/users/{id}").
				then().
				statusCode(200).
				contentType(JSON).
				extract().
				response();
		
		
		String firstName = response.jsonPath().getString("firstName");
        String lastName = response.jsonPath().getString("lastName");
         
     storedAddresses = response.jsonPath().getList("addresses");
        
		assertNotNull(firstName);
		assertNotNull(lastName);
		assertEquals("Gabrel",firstName);
		assertEquals(userDetails.get("lastName"),lastName);
		assertNotNull(storedAddresses); 
		assertTrue(addresses.size()==storedAddresses.size());
	}
	
	
	@Test
	void testZeleteUserDetails() {
	
		Response response = given().
				pathParam("id",userId).
				accept(JSON).
				when().
				delete(CONTEXT_PATH+"/users/{id}").
				then(). 
				statusCode(200).
				contentType(JSON).
				extract().
				response();
		
			String operationResult = response.jsonPath().getString("operationResult");
			assertEquals("SUCCESS",operationResult);
	}
}