package fi.rofl.HomeChore.Controller;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import fi.rofl.HomeChore.DTO.SessionDTO;
import fi.rofl.HomeChore.model.Member;
import fi.rofl.HomeChore.util.Constants;
import fi.rofl.HomeChore.util.ExceptionGenerator;
import fi.rofl.HomeChore.util.NoSessionException;

public class SessionController {
	
	public static void setSessionMember(HttpServletRequest request, Member member) throws NoSessionException {
		
		SessionDTO dto = getSessionObject(request);
		dto.setMember(member);
		setSessionObject(request, dto);
	}
	
	public static Member getSessionMember(HttpServletRequest request) throws NoSessionException {
		
		SessionDTO dto = getSessionObject(request);
		return dto.getMember();

	}
	
	
	
	private static SessionDTO getSessionObject(HttpServletRequest request) throws NoSessionException {
		
		HttpSession session = request.getSession(false);
		
		if (session != null) {
			
			SessionDTO dto = (SessionDTO) session.getAttribute(Constants.SESSION_OBJECT);
			if (dto == null) {
				dto = new SessionDTO();
				setSessionObject(request, dto);
			}
			return dto;
			
		}
		else {
			throw ExceptionGenerator.createNoSessionException("sessionWasNull");
		}
		
	}
	
	private static void setSessionObject(HttpServletRequest request, SessionDTO dto) throws NoSessionException {
		
		HttpSession session = request.getSession(false);
		
		if (session != null) {
			
			if (dto == null) {
				dto = new SessionDTO();
				
			}
			
			session.setAttribute(Constants.SESSION_OBJECT, dto);

		}
		else {
			throw ExceptionGenerator.createNoSessionException("sessionWasNull");
		}
		
	}

}
