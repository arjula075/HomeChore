package fi.rofl.HomeChore.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import fi.rofl.HomeChore.Controller.SessionController;
import fi.rofl.HomeChore.DTO.CollectiveDTO;
import fi.rofl.HomeChore.DTO.MemberDTO;
import fi.rofl.HomeChore.DTO.TaskDTO;
import fi.rofl.HomeChore.model.Member;
import fi.rofl.HomeChore.model.Task;
import fi.rofl.HomeChore.model.Collective;
import fi.rofl.HomeChore.util.ExceptionGenerator;
import fi.rofl.HomeChore.util.NoSessionException;
import fi.rofl.HomeChore.util.UserNotFoundException;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class CollectiveService {
	
	@Inject
	private Logger log;

	@Inject
	private EntityManager em;

	@Resource
	private UserTransaction  tx;

	public CollectiveService() {
		// TODO Auto-generated constructor stub
	}
	
	public CollectiveDTO getCollectiveData(int collectiveId, HttpServletRequest request) throws NoSessionException, NotSupportedException, SystemException, UserNotFoundException {
		
		CollectiveDTO result = new CollectiveDTO();
		Member origMember = SessionController.getSessionMember(request);
		tx.begin();
		Collective collective = em.find(Collective.class, collectiveId);
		if (collective != null) {
			if (!collective.getMembers().isEmpty()) {
				if (collective.getMembers().contains(origMember)) {
					result.setId(collective.getId());
					result.setName(collective.getName());
					result.setType(collective.getType());
					
					List<MemberDTO> members = new ArrayList<>();
					Iterator<Member> iter = collective.getMembers().iterator();
					while (iter.hasNext()) {
						Member member = iter.next();
						MemberDTO memberDTO = new MemberDTO();
						memberDTO.setEmail(member.getEmail());
						memberDTO.setId(member.getId());
						memberDTO.setPhoneNumber(member.getPhoneNumber());
						memberDTO.setName(member.getName());
						
						List<TaskDTO> tasks = new ArrayList<>();
						Iterator<Task> iter2 = member.getTasks().iterator();
						while (iter2.hasNext()) {
							TaskDTO taskDTO = new TaskDTO();
							Task task = iter2.next();
							taskDTO.setApproved(task.isApproved());
							taskDTO.setComplete(task.isComplete());
							taskDTO.setCompleteDate(task.getCompleteDate());
							taskDTO.setDeadline(task.getDeadline());
							taskDTO.setId(task.getId());
							taskDTO.setMainTask(task.getMainTask());
							taskDTO.setName(task.getName());
							taskDTO.setShowActions(task.isShowActions());
							taskDTO.setTimeInterval(task.getTimeInterval());
							taskDTO.setType(task.getType());
							tasks.add(taskDTO);
						}
						members.add(memberDTO);
					}
					result.setMembers(members);
					return result;
				}
				else {
					throw ExceptionGenerator.createUserNotFoundException("userNotInCollective");
				}
				
			}
			else {
				throw ExceptionGenerator.createUserNotFoundException("collectiveNotFound");
			}
			
		}
		else {
			throw ExceptionGenerator.createUserNotFoundException("userNotFoundFromDB");
		}
		
	}

	public List<CollectiveDTO> getPossibleCollectives(Member origMember, HttpServletRequest request) throws NotSupportedException, SystemException, SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
		
		List<CollectiveDTO> result = new ArrayList<>();
		tx.begin();
		Member member = em.find(Member.class, origMember.getId());
		if (member != null) {
			 if (member.getCollectives() != null) {
				 if (!member.getCollectives().isEmpty()) {
					 Iterator<Collective> iter = member.getCollectives().iterator();
					 while(iter.hasNext()) {
						 CollectiveDTO dto = new CollectiveDTO();
						 Collective collective = iter.next();
						 dto.setId(collective.getId());
						 dto.setName(collective.getName());
						 dto.setType(collective.getType());
						 result.add(dto);
					 }
				 }
			 }
		}
		tx.commit();
		return result;
	}


}
