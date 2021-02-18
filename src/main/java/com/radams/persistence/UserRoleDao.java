package com.radams.persistence;

import com.radams.entity.UserRole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserRoleDao {

    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();


    /**
     * Gets all userRoles and returns them in a list
     * @return list of results
     */
    public List<UserRole> getAllUserRoles() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UserRole> query = builder.createQuery(UserRole.class);
        Root<UserRole> root = query.from(UserRole.class);
        List<UserRole> userRoles = session.createQuery(query).getResultList();
        session.close();
        return userRoles;
    }

    /**
     * gets a userRoles by a specified last name and puts them into a list
     * @param lastName last name to search
     * @return list of results
     */
    public List<UserRole> getUserRolesByLastName(String lastName) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UserRole> query = builder.createQuery(UserRole.class);
        Root<UserRole> root = query.from(UserRole.class);
        Expression<String> propertyPath = root.get("lastName");
        query.where(builder.like(propertyPath, "%" + lastName + "%"));
        List<UserRole> userRoles = session.createQuery(query).getResultList();
        session.close();
        return userRoles;
    }

    /**
     * gets specific userRole by id
     * @param id id of searched userRole
     * @return userRole with specified id
     */
    public UserRole getUserRoleById(int id) {
        Session session = sessionFactory.openSession();
        UserRole userRole = session.get(UserRole.class, id);
        session.close();
        return userRole;
    }

    /**
     * update userRole
     * @param userRole  UserRole to be inserted or updated
     */
    public void saveOrUpdate(UserRole userRole) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(userRole);
        transaction.commit();
        session.close();
    }

    /**
     * update userRole
     * @param userRole  UserRole to be inserted or updated
     */
    public int insert(UserRole userRole) {
        int id = 0;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        id = (int)session.save(userRole);
        transaction.commit();
        session.close();
        return id;
    }

    /**
     * Delete a userRole
     * @param userRole UserRole to be deleted
     */
    public void delete(UserRole userRole) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(userRole);
        transaction.commit();
        session.close();
    }
}
