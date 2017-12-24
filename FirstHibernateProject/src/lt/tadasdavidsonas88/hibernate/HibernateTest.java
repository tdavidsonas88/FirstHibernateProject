package lt.tadasdavidsonas88.hibernate;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import lt.tadasdavidsonas88.dto.Address;
import lt.tadasdavidsonas88.dto.UserDetails;

public class HibernateTest {

	public static void main(String[] args) {
		UserDetails user1 = new UserDetails();
//		user1.setUserId(1);
		user1.setUserName("First User");
		
		Address addr1 = new Address();
		addr1.setStreet("Street name 1");
		addr1.setCity("Kaunas");
		addr1.setState("First State");
		addr1.setPincode("100001");
		
		Address officeAddr1 = new Address();
		officeAddr1.setState("Office street name 1");
		officeAddr1.setCity("Klaipëda");
		
		user1.setHomeAddress(addr1);
		user1.setOfficeAddress(officeAddr1);
		user1.setJoinedDate(new Date());
		user1.setDescription("Description of the first user goes here");
		
		UserDetails user2 = new UserDetails();
//		user2.setUserId(2);
		user2.setUserName("Second User");
		
		Address addr2 = new Address();
		addr2.setStreet("Street name 2");
		addr2.setCity("Vilnius");
		addr2.setState("Second State");
		addr2.setPincode("200002");
		
		
		user2.setHomeAddress(addr2);
		user2.setJoinedDate(new Date());
		user2.setDescription("Description of the second user goes here");
		
		user1.getListOfAddresses().add(addr1);
		user1.getListOfAddresses().add(addr2);
		
		try {
		
			SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
			
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(user1);
			session.save(user2);
			session.getTransaction().commit();
		
			session.close();
			
			user1 = null;
			user2 = null;
			// Fetching the data from the database
			session = sessionFactory.openSession();
			session.beginTransaction();
			user1 = session.get(UserDetails.class, 1);
			System.out.println("User1 name retrieved is: " + user1.getUserName());
			user2 = session.get(UserDetails.class, 2);
			System.out.println("User2 name retrieved is: " + user2.getUserName());
			
			session.close();
			System.out.println(user1.getListOfAddresses().size());
		
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
