package br.com.nab.jdbc.test;

import java.util.Calendar;

import br.com.nab.jdbc.dao.ContactDao;
import br.com.nab.jdbc.model.Contact;

public class TestInsertContact {

	public static void main(String[] args) {
		
		// ready to store
	      Contact contact = new Contact();
	      contact.setName("Laura");
	      contact.setEmail("laura@gmail.com.br");
	      contact.setAddress("R. Almirante Souza 1807 cs03");
	      contact.setBirthDate(Calendar.getInstance());

	      // store on this connection!!!
	      ContactDao bd = new ContactDao();

	      bd.add(contact);

	      System.out.println("Stored!");

	}

}
