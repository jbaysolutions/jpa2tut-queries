package com.jbaysolutions.blog;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.jbaysolutions.blog.entity.EmployeeEntity;
import com.jbaysolutions.blog.entity.CompanyEntity;
import org.junit.*;

import java.util.Date;
import java.util.List;


/**
 *
 * 
 */
public class QueryTesting {
    private static EntityManager em = null;

    @BeforeClass
    public static void setUpClass() throws Exception {
        if (em == null) {
            em = (EntityManager) Persistence.createEntityManagerFactory("jpa2tut-test").createEntityManager();
        }
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testSimpleQuery(){
        Query query = em.createQuery("SELECT c FROM CompanyEntity AS c");
        for ( CompanyEntity ce : (List<CompanyEntity>)query.getResultList()) {
            System.out.println(" -> Company : " + ce.getName() );
        }
    }

    @Test
    public void testSimpleQueryWhere(){
        Query query = em.createQuery("SELECT c FROM CompanyEntity AS c WHERE c.name='JBay Solutions'");
        for ( CompanyEntity ce : (List<CompanyEntity>)query.getResultList()) {
            System.out.println(" -> Company : " + ce.getName() );
        }
    }

    @Test
    public void testSimpleTypedQuery() {
        TypedQuery<CompanyEntity> query = em.createQuery("SELECT c FROM CompanyEntity AS c", CompanyEntity.class);
        for (CompanyEntity ce : query.getResultList()) {
            System.out.println(" -> Company : " + ce.getName());
        }
    }

    @Test
    public void testSimpleTypedQueryWhere() {
        TypedQuery<CompanyEntity> query = em.createQuery("SELECT c FROM CompanyEntity AS c WHERE c.name='JBay Solutions'", CompanyEntity.class);
        for (CompanyEntity ce : query.getResultList()) {
            System.out.println(" -> Company : " + ce.getName());
        }
    }

    @Test
    public void testSimpleTypedQueryWhereInputNamed() {
        TypedQuery<CompanyEntity> query = em.createQuery("SELECT c FROM CompanyEntity AS c WHERE c.name=:nameParam", CompanyEntity.class);

        query.setParameter("nameParam", "JBay Solutions");
        for (CompanyEntity ce : query.getResultList()) {
            System.out.println(" Query JBay -> Company : " + ce.getName());
        }

        query.setParameter("nameParam", "Google");
        for (CompanyEntity ce : query.getResultList()) {
            System.out.println(" Query Google -> Company : " + ce.getName());
        }

        query.setParameter("nameParam", "Blah");
        for (CompanyEntity ce : query.getResultList()) {
            System.out.println(" Query Blah -> Company : " + ce.getName());
        }

        query.setParameter("nameParam", null);
        for (CompanyEntity ce : query.getResultList()) {
            System.out.println(" Query Null  -> Company : " + ce.getName());
        }
    }

    @Test
    public void testSimpleTypedQueryWhereInputNumbered() {
        TypedQuery<CompanyEntity> query = em.createQuery("SELECT c FROM CompanyEntity AS c WHERE c.name=?1 OR c.name=?2", CompanyEntity.class);
        query.setParameter(1, "JBay Solutions");
        query.setParameter(2, "Google");
        for (CompanyEntity ce : query.getResultList()) {
            System.out.println(" Ordered -> Company : " + ce.getName());
        }

        query.setParameter(2, "Google");
        query.setParameter(1, "JBay Solutions");
        for (CompanyEntity ce : query.getResultList()) {
            System.out.println(" Unordered -> Company : " + ce.getName());
        }
    }

    @Test
    public void testMultipleEntities1() {
        TypedQuery<EmployeeEntity> query =
                em.createQuery("SELECT employee FROM CompanyEntity AS company, EmployeeEntity as employee " +
                        "WHERE company.name=:name " +
                        "and employee.company = company", EmployeeEntity.class);

        query.setParameter("name", "JBay Solutions");
        for (EmployeeEntity client : query.getResultList()) {
            System.out.println(" -> Q1 : " + client.getName());
        }

        query.setParameter("name", "JetBrains");
        for (EmployeeEntity client : query.getResultList()) {
            System.out.println(" -> Q2 : " + client.getName());
        }
    }

    @Test
    public void testMultipleEntities2() {
        TypedQuery<EmployeeEntity> query =
                em.createQuery("SELECT employee FROM CompanyEntity AS company, EmployeeEntity as employee " +
                        "WHERE company.name=:name " +
                        "and employee MEMBER OF company.employeeCollection ", EmployeeEntity.class);

        query.setParameter("name", "JBay Solutions");
        for (EmployeeEntity client : query.getResultList()) {
            System.out.println(" -> Q1 : " + client.getName());
        }

        query.setParameter("name", "JetBrains");
        for (EmployeeEntity client : query.getResultList()) {
            System.out.println(" -> Q2 : " + client.getName());
        }
    }

    @Test
    public void testBetweenCreateYear1() {
        TypedQuery<CompanyEntity> query =
                em.createQuery("SELECT company FROM CompanyEntity AS company " +
                        "WHERE company.createdYear >= 1995 " +
                        "AND company.createdYear <= 2005 ", CompanyEntity.class);

        for (CompanyEntity company : query.getResultList()) {
            System.out.println(" -> Q1 : " + company.getName());
        }
    }

    @Test
    public void testBetweenCreateYear2() {
        TypedQuery<CompanyEntity> query =
                em.createQuery("SELECT company FROM CompanyEntity AS company " +
                        "WHERE  company.createdYear BETWEEN :minimum AND :maximum ", CompanyEntity.class);
        query.setParameter("minimum", 1995);
        query.setParameter("maximum", 2000);
        for (CompanyEntity company : query.getResultList()) {
            System.out.println(" -> Q1 : " + company.getName());
        }
    }

    @Test
    public void testBetweenDates1() {
        TypedQuery<EmployeeEntity> query =
                em.createQuery("SELECT employee FROM EmployeeEntity AS employee " +
                        "WHERE  employee.birthday BETWEEN :minimum AND :maximum ", EmployeeEntity.class);
        query.setParameter("minimum", "1979-01-01");
        query.setParameter("maximum", new Date());
        for (EmployeeEntity employee : query.getResultList()) {
            System.out.println(" -> Q1 : " + employee.getName());
        }
    }

    @Test
    public void testLike1() {
       TypedQuery<CompanyEntity> query =
               em.createQuery("SELECT company FROM CompanyEntity AS company " +
                       "WHERE  company.name = 'Google' ", CompanyEntity.class);
       for (CompanyEntity company : query.getResultList()) {
           System.out.println(" -> Q1 : " + company.getName());
       }
    }

    @Test
    public void testLike2() {
       TypedQuery<CompanyEntity> query =
               em.createQuery("SELECT company FROM CompanyEntity AS company " +
                       "WHERE  company.name LIKE '%oo_' ", CompanyEntity.class);
       for (CompanyEntity company : query.getResultList()) {
           System.out.println(" -> Q1 : " + company.getName());
       }
    }

    @Test
    public void testEmpty1() {
       TypedQuery<CompanyEntity> query =
               em.createQuery("SELECT company FROM CompanyEntity AS company " +
                       "WHERE  company.employeeCollection IS EMPTY ", CompanyEntity.class);
       for (CompanyEntity company : query.getResultList()) {
           System.out.println(" -> Q1 : " + company.getName());
       }
    }

    @Test
    public void testEmpty2() {
       TypedQuery<CompanyEntity> query =
               em.createQuery("SELECT company FROM CompanyEntity AS company " +
                       "WHERE  company.employeeCollection IS NOT EMPTY ", CompanyEntity.class);
       for (CompanyEntity company : query.getResultList()) {
           System.out.println(" -> Q1 : " + company.getName());
       }
    }

    @Test
    public void testNull1() {
       TypedQuery<EmployeeEntity> query =
               em.createQuery("SELECT employee FROM EmployeeEntity AS employee " +
                       "WHERE  employee.address IS NULL ", EmployeeEntity.class);
       for (EmployeeEntity employee : query.getResultList()) {
           System.out.println(" -> Q1 : " + employee.getName());
       }
    }

}
