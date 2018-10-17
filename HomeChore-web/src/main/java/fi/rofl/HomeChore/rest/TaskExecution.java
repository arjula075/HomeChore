package fi.rofl.HomeChore.rest;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fi.rofl.HomeChore.Controller.SessionController;
import fi.rofl.HomeChore.model.Member;
import fi.rofl.HomeChore.model.Task;
import fi.rofl.HomeChore.service.MemberService;
import fi.rofl.HomeChore.service.TaskExecutionService;
import fi.rofl.HomeChore.util.ChoreRestValidator;



@Path("/taskexecution")
public class TaskExecution {

	@Context
	private HttpServletRequest request;

	@Inject
	private Validator validator;

	@Inject
	private Logger log;

	@Inject
	private TaskExecutionService taskService;
	
	@Inject
	private MemberService memberService;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateTask(Task task) {

		Response.ResponseBuilder builder = null;

		ChoreRestValidator valid = new ChoreRestValidator(request, log, validator, null);


		try {
			// Validates member using bean validation
			valid.validateTask(task);
			Member member = SessionController.getSessionMember(request);
			System.out.println(task);
			task.setCompleteDate(new Timestamp(System.currentTimeMillis()));
			System.out.println(task);
			taskService.updateTask(task, member);

			// Create an "ok" response
			builder = Response.ok();
		} catch (ConstraintViolationException ce) {
			// Handle bean validation issues
			builder = createViolationResponse(ce.getConstraintViolations());
		} catch (ValidationException e) {
			// Handle the unique constrain violation
			Map<String, String> responseObj = new HashMap<>();
			responseObj.put("email", "Email taken");
			builder = Response.status(Response.Status.CONFLICT).entity(responseObj);
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
	public Response getTasks() {

		Response.ResponseBuilder builder = null;


		try {
			Member member = SessionController.getSessionMember(request);

			String tasks = taskService.getTasks(member);
			
			System.out.println(memberService.getMemberCollective(member));

			builder = Response.ok(tasks, MediaType.APPLICATION_JSON);
		} catch (ConstraintViolationException ce) {
			// Handle bean validation issues
			builder = createViolationResponse(ce.getConstraintViolations());
		} catch (ValidationException e) {
			// Handle the unique constrain violation
			Map<String, String> responseObj = new HashMap<>();
			responseObj.put("email", "Email taken");
			builder = Response.status(Response.Status.CONFLICT).entity(responseObj);
		} catch (Exception e) {
			// Handle generic exceptions
			Map<String, String> responseObj = new HashMap<>();
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}

		return builder.build();
	}

	@POST
	@Path("/createTestData")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createTestData(List<Task> tasks) {

		Response.ResponseBuilder builder = null;

		ChoreRestValidator valid = new ChoreRestValidator(request, log, validator, null);

		try {
			// Validates member using bean validation
			tasks.forEach(t -> valid.validateTask(t));
			taskService.updateMultipleItems(tasks);

			// Create an "ok" response
			builder = Response.ok();
		} catch (ConstraintViolationException ce) {
			// Handle bean validation issues
			builder = createViolationResponse(ce.getConstraintViolations());
		} catch (ValidationException e) {
			// Handle the unique constrain violation
			Map<String, String> responseObj = new HashMap<>();
			responseObj.put("email", "Email taken");
			builder = Response.status(Response.Status.CONFLICT).entity(responseObj);
		} catch (Exception e) {
			// Handle generic exceptions
			Map<String, String> responseObj = new HashMap<>();
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}

		return builder.build();
	}

	private Response.ResponseBuilder createViolationResponse(Set<ConstraintViolation<?>> violations) {
		log.fine("Validation completed. violations found: " + violations.size());

		Map<String, String> responseObj = new HashMap<>();

		for (ConstraintViolation<?> violation : violations) {
			responseObj.put(violation.getPropertyPath().toString(), violation.getMessage());
		}

		return Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
	}

}
