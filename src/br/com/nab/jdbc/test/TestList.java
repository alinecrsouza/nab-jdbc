package br.com.nab.jdbc.test;

import java.util.List;

import br.com.nab.jdbc.dao.ContactDao;
import br.com.nab.jdbc.model.Contact;

public class TestList {
	public static void main(String[] args) {
			//Create a ContactDao
			ContactDao dao = new ContactDao();
			
			//List contacts with DAO
			List<Contact> contacts = dao.getList();
			
			//Iterate in this list and print contact information
			for (Contact contact : contacts) {
			  System.out.println("Name: " + contact.getName());
			  System.out.println("Email: " + contact.getEmail());
			  System.out.println("Address: " + contact.getAddress());
			  System.out.println("Date of Birth: " + 
			              contact.getBirthDate().getTime() + "\n");
			}
	}
}
