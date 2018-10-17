package fi.rofl.HomeChore.rest;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fi.rofl.HomeChore.data.MemberRepository;
import fi.rofl.HomeChore.model.Member;
import fi.rofl.HomeChore.service.MemberService;
import fi.rofl.HomeChore.util.ChoreRestValidator;

@Path("/register")
@RequestScoped
public class RegistrationRest {
	
	@Context
	private HttpServletRequest request;
	
    @Inject
    private Logger log;

    @Inject
    private Validator validator;

    @Inject
    private MemberRepository repository;

    @Inject
    MemberService memberService;
	
	
    /**
     * Creates a new member from the values provided. Performs validation, and will return a JAX-RS response with either 200 ok,
     * or with a map of fields, and related errors.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createMember(Member member) {

        Response.ResponseBuilder builder = null;
        
        ChoreRestValidator valid = new ChoreRestValidator(request, log, validator, repository);

        try {
            // Validates member using bean validation
        	valid.validateMember(member);

        	// register member
        	memberService.register(member);
        	
        	//check if users email is on pre-register list

            // Create an "ok" response
        	builder = Response.ok(memberService.getPreRegistrations(member), MediaType.APPLICATION_JSON);
        } catch (ConstraintViolationException ce) {
            // Handle bean validation issues
            builder = valid.createViolationResponse(ce.getConstraintViolations());
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

}
