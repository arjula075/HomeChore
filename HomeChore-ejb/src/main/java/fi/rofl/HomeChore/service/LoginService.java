package fi.rofl.HomeChore.service;

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;

import org.mindrot.jbcrypt.BCrypt;

import fi.rofl.HomeChore.Controller.SessionController;
import fi.rofl.HomeChore.DTO.UserDTO;
import fi.rofl.HomeChore.model.Member;
import fi.rofl.HomeChore.util.DuplicateUidException;
import fi.rofl.HomeChore.util.ExceptionGenerator;
import fi.rofl.HomeChore.util.NoSessionException;
import fi.rofl.HomeChore.util.UserNotFoundException;
import fi.rofl.HomeChore.util.WrongPasswordException;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class LoginService {

	@Inject
	private Logger log;

	@Inject
	private EntityManager em;

	@Resource
	private UserTransaction  tx;


	public String login(UserDTO user, HttpServletRequest request) throws WrongPasswordException, UserNotFoundException, DuplicateUidException, NoSessionException {


		HttpSession session = request.getSession(false);

		Member member = findMemberByUID(user.getUid());

		if (member != null) {

			if (BCrypt.checkpw(user.getPsw(), member.getPsw())) {

				if (session != null) {
					session.invalidate();
				}
				session = request.getSession(true);
				SessionController.setSessionMember(request, member);
				return "ok";
			}
			else {
				log.info("wrong password " + user.getUid());
				throw ExceptionGenerator.createWrongPasswordException("wrongPassword");
			}
		}
		else {
			log.info("user not found " + user.getUid());
			throw ExceptionGenerator.createUserNotFoundException("userNotFound");
		}

	}


	private Member findMemberByUID(String uid) throws DuplicateUidException {

		List<Member> members =  em
				.createQuery("Select a from Member a WHERE a.uid =:uid", Member.class)
				.setParameter("uid", uid)
				.getResultList();

		if (members.isEmpty()) {
			return null;
		}

		if (members.size() > 1) {
			throw ExceptionGenerator.createDuplicateUidException("dublicate uid");
		}

		return members.get(0);

	}

}
