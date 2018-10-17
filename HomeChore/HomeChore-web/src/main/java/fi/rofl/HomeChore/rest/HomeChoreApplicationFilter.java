package fi.rofl.HomeChore.rest;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fi.rofl.HomeChore.Controller.SessionController;
import fi.rofl.HomeChore.model.Member;
import fi.rofl.HomeChore.util.NoSessionException;

public class HomeChoreApplicationFilter implements Filter {

	
    /**
     * Default constructor. 
     */
    public HomeChoreApplicationFilter() {
        // TODO Auto-generated constructor stub
    }
	
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;
		System.out.println("HomeChoreApplicationFilter " + req.getRequestURI());
		try {
			Member member = SessionController.getSessionMember(req);
			if (member != null) {
				System.out.println(member.getUid());
				
			}
			else {
				System.out.println("No member");
				httpResponse.sendRedirect("/HomeChore-web/");
				return;
			}
		} catch (NoSessionException e) {
			System.out.println("No session!");
			httpResponse.sendRedirect("/HomeChore-web/");
			return;
		}
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}


}
