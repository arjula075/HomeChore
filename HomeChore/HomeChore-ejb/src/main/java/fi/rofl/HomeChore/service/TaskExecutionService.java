package fi.rofl.HomeChore.service;

import java.sql.Timestamp;
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
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fi.rofl.HomeChore.model.Member;
import fi.rofl.HomeChore.model.Task;
import fi.rofl.HomeChore.model.TaskHistory;
import fi.rofl.HomeChore.util.DateUtils;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class TaskExecutionService {

	@Inject
	private Logger log;

	@Inject
	private EntityManager em;

	@Resource
	private UserTransaction  tx;

	public void updateMultipleItems(List<Task> tasks) throws Exception {

		tasks.forEach(t -> persist(t));

	}

	private void persist(Task task) {

		log.info("updating " + task.getName());
		try {
			tx.begin();
			em.persist(task);
			tx.commit();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HeuristicMixedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HeuristicRollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getTasks(Member member) throws NotSupportedException, SystemException, SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException {


		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

		tx.begin();
		member = em.find(Member.class, member.getId());
		List<Task> tasks = member.getTasks();
		List<Task> result = new ArrayList<Task>();
		Iterator<Task> iter = tasks.iterator();
		while (iter.hasNext()) {
			Task task = iter.next();
			if (task.isComplete()) {
				if (isAfterDeadline(task)) {
					task.setComplete(false);
					task.setActuallyDid(0);
					task.setCompleteDate(null);
					task.setApproved(false);
					task.setDeadline(DateUtils.addDaysToTimestamp(new Timestamp(System.currentTimeMillis()), task.getTimeInterval()));
					em.merge(task);
				}
			}
			result.add(task);
		}
		String json = gson.toJson(result);
		tx.commit();
		return json;
	}

	public void updateTask(Task task, Member member) {

		try {
			tx.begin();
			Task origTask = em.find(Task.class, task.getId());
			origTask.setApproved(task.isApproved());
			origTask.setComplete(task.isComplete());
			if (task.isComplete()) {
				origTask.setDeadline(DateUtils.addDaysToTimestamp(DateUtils.getNowAsTimestamp(), origTask.getTimeInterval()));
			}
			origTask.setCompleteDate(task.getCompleteDate());
			origTask.setActuallyDid(member.getId());
			origTask.setShowActions(task.isShowActions());
			tx.commit();

			createHistoryRow(task, member);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HeuristicMixedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HeuristicRollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void createHistoryRow(Task task, Member member) throws NotSupportedException, SystemException, SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException {

		if (task.isComplete()) {
			if (isAfterDeadline(task)) {
				tx.begin();
				TaskHistory th = new TaskHistory();
				th.setActualDate(new Timestamp(System.currentTimeMillis()));
				th.setRegistrantId(member.getId());
				th.setTaskId(task.getId());
				em.persist(th);
				tx.commit();
			}
		}

	}

	private boolean isAfterDeadline(Task task) {

		if (task.getCompleteDate() == null) {
			return true;
		}

		Timestamp now = DateUtils.getNowAsTimestamp();
		Timestamp deadline = task.getDeadline();
		
		if (deadline == null) {
			return true;
		}
		
		if (deadline.before(now)) {
			System.out.println(true);
			return true;
		}
		else {
			System.out.println(false);
			return false;
		}

	}

}
