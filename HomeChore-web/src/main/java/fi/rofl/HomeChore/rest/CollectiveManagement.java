package fi.rofl.HomeChore.rest;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fi.rofl.HomeChore.Controller.SessionController;
import fi.rofl.HomeChore.model.Member;
import fi.rofl.HomeChore.service.CollectiveService;

@Path("/collective")
public class CollectiveManagement {
	
	@Inject
	CollectiveService collectiveService;
	
	@Context
	private HttpServletRequest request;

	public CollectiveManagement() {
		// TODO Auto-generated constructor stub
	}
	
	@GET
	@Path("/{collectiveId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCollectiveData(@PathParam("collectiveId") int collectiveId) {

		Response.ResponseBuilder builder = null;
		
		try {
			// Create an "ok" response
        	builder = Response.ok(collectiveService.getCollectiveData(collectiveId, request), MediaType.APPLICATION_JSON);
			
		} catch (Exception e) {
			// Handle generic exceptions
			Map<String, String> responseObj = new HashMap<>();
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}
		return builder.build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMemberCollections() {

		Response.ResponseBuilder builder = null;
		
		try {
			// Create an "ok" response
			Member member = SessionController.getSessionMember(request);
        	builder = Response.ok(collectiveService.getPossibleCollectives(member, request), MediaType.APPLICATION_JSON);
			
		} catch (Exception e) {
			// Handle generic exceptions
			Map<String, String> responseObj = new HashMap<>();
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}
		return builder.build();
	}

}
