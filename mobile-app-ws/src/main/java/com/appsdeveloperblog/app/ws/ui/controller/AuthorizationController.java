package com.appsdeveloperblog.app.ws.ui.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.appsdeveloperblog.app.ws.ui.model.request.LoginRequestModel;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;

@RestController
public class AuthorizationController {

	@ApiOperation("User Login")
	@ApiResponses(value= {
	@ApiResponse(code = 200, 
			message="REsponse Headers",
			responseHeaders= {
					@ResponseHeader(name="authorization",
							description="Bearer <JWT value here>",
							response=String.class),
					@ResponseHeader(name="userId",
							description="<Public userId value here>",
							response=String.class )
			})
	})
	
	
	
	@PostMapping("/login")
	public void theFakeLogin(@RequestBody LoginRequestModel loginRequstModel) {
		throw new IllegalStateException("This method should not be called, Thise method is implemented by spring Security");
	}
}
