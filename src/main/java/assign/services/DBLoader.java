package assign.services;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import assign.domain.Meeting;
import assign.domain.Project;

import java.util.logging.*;

public class DBLoader {
	private SessionFactory sessionFactory;

	Logger logger;

	@SuppressWarnings("deprecation")
	public DBLoader() {
		// A SessionFactory is set up once for an application
		sessionFactory = new Configuration().configure() // configures settings from hibernate.cfg.xml
				.buildSessionFactory();

		logger = Logger.getLogger("EavesdropReader");
	}

	public void loadData(Map<String, List<String>> data) {
		logger.info("Inside loadData.");
	}

	public void addProject(Project p) throws Exception {
		Session session = sessionFactory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			session.save(p);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
				throw e;
			}
		} finally {
			session.close();
		}
	}

	public void addMeeting(Meeting m, Long projectId) throws Exception {
		Session session = sessionFactory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();

			Criteria criteria = session.createCriteria(Project.class).add(Restrictions.eq("id", projectId));

			List<Project> projects = criteria.list();

			Project p = projects.get(0);

			m.setProject(p);

			session.save(m);

			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
				throw e;
			}
		} finally {
			session.close();
		}

	}

	public Project getProject(Long projectId) throws Exception {
		Session session = sessionFactory.openSession();

		session.beginTransaction();

		Criteria criteria = session.createCriteria(Project.class).add(Restrictions.eq("id", projectId));

		List<Project> projects = criteria.list();

		session.close();

		return projects.get(0);
	}

	public void updateMeeting(Long projectId, Long meetingId, Meeting newMeeting) throws Exception {

		newMeeting.setId(meetingId);

		Session session = sessionFactory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();

			Criteria criteria = session.createCriteria(Meeting.class).add(Restrictions.eq("id", meetingId));

			List<Meeting> meetings = criteria.list();
			Meeting m = meetings.get(0);

			m.setName(newMeeting.getName());
			m.setYear(newMeeting.getYear());

			session.update(m);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
				throw e;
			}
		} finally {
			session.close();
		}
	}

	public void deleteProject(Long projectId) throws Exception {

		Session session = sessionFactory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();

			Criteria criteria = session.createCriteria(Project.class).add(Restrictions.eq("id", projectId));

			List<Project> projects = criteria.list();
			Project p = projects.get(0);

			session.delete(p);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
				throw e;
			}
		} finally {
			session.close();
		}
	}

	public boolean projectIdExists(Long projectId) {
		Session session = sessionFactory.openSession();

		session.beginTransaction();

		Criteria criteria = session.createCriteria(Project.class).add(Restrictions.eq("id", projectId));

		List<Project> projects = criteria.list();

		session.close();

		if (projects.size() == 0) {
			return false;
		} else {
			return true;
		}
	}

	public boolean projectHasMeeting(Long meetingId, Long projectId) {
		Session session = sessionFactory.openSession();

		session.beginTransaction();

		Criteria criteria = session.createCriteria(Meeting.class).add(Restrictions.eq("id", meetingId));

		List<Meeting> meetings = criteria.list();

		session.close();

		if (meetings.size() == 0) {
			return false;
		}

		Meeting m = meetings.get(0);

		if (!m.getProject().getId().equals(projectId)) {
			return false;
		} else {
			return true;
		}
	}
}
