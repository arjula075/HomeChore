package fi.rofl.HomeChore.rest;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fi.rofl.HomeChore.DTO.UserDTO;

import fi.rofl.HomeChore.service.LoginService;

@Path("/login")
public class LoginRest {
	
	@Context
	private HttpServletRequest request;
	
	@Inject
	private LoginService login;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(UserDTO user) {
		


		Response.ResponseBuilder builder = null;

		try {
			login.login(user, request);
			builder = Response.ok();
		}  catch (Exception e) {
			// Handle generic exceptions
			Map<String, String> responseObj = new HashMap<>();
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}

		return builder.build();
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response logOut() {
		


		Response.ResponseBuilder builder = null;

		try {
			HttpSession session = request.getSession(false);
			if (session != null) {
				session.invalidate();
			}
			builder = Response.ok();
		}  catch (Exception e) {
			// Handle generic exceptions
			Map<String, String> responseObj = new HashMap<>();
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}

		return builder.build();
	}

}
