package com.jbaysolutions.blog;

import java.util.Vector;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import com.jbaysolutions.blog.entity.ClientEntity;
import com.jbaysolutions.blog.entity.CompanyEntity;
import org.junit.*;


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
    public void testCompanyWithClient1(){
        // Start a transaction
        em.getTransaction().begin();
        // ------------

        CompanyEntity c = createTestCompany();
        ClientEntity client = createTestClient();
        em.persist(c);
        em.persist(client);
        em.flush();

        c.getClientCollection().add(client);

        em.merge(c);
        em.flush();
        
        // Commit the Transaction
        em.getTransaction().commit();

    }
    
    
    
    private CompanyEntity createTestCompany(){
        CompanyEntity c = new CompanyEntity();
        c.setName("JBay Solutions");
        c.setAddress("Av. 5 October, Lisbon");
        return c;
    }
    
    private ClientEntity createTestClient(){
        ClientEntity c = new ClientEntity();
        c.setName("Rui");
        c.setAddress("Home address");
        return c;
    }
    
}
