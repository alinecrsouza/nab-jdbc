package br.com.nab.jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.nab.jdbc.ConnectionFactory;
import br.com.nab.jdbc.model.Contact;

public class ContactDao {

	// database connection
	  private Connection connection;

	  public ContactDao() {
	    this.connection = new ConnectionFactory().getConnection();
	  }
	  
	  public void add(Contact contact) {
		    String sql = "insert into contacts " +
		            "(name,email,address,birthDate)" +
		            " values (?,?,?,?)";

		    try {
		        // prepared statement to insertion
		    	PreparedStatement stmt = connection.prepareStatement(sql);

		        // fill in the values
		        stmt.setString(1,contact.getName());
		        stmt.setString(2,contact.getEmail());
		        stmt.setString(3,contact.getAddress());
		        stmt.setDate(4, new Date(
		                contact.getBirthDate().getTimeInMillis()));

		        // run
		        stmt.execute();
		        stmt.close();
		    } catch (SQLException e) {
		        throw new RuntimeException(e);
		    }
	  }
	  
	  public void update(Contact contact) {
		     String sql = "update contacts set name=?, email=?,"+
		             "address=?, birthDate=? where id=?";
		 
		     try {
		         PreparedStatement stmt = connection
		                 .prepareStatement(sql);
		         stmt.setString(1, contact.getName());
		         stmt.setString(2, contact.getEmail());
		         stmt.setString(3, contact.getAddress());
		         stmt.setDate(4, new Date(contact.getBirthDate()
		                 .getTimeInMillis()));
		         stmt.setLong(5, contact.getId());
		         stmt.execute();
		         stmt.close();
		     } catch (SQLException e) {
		         throw new RuntimeException(e);
		     }
		 }
	  
	  public void remove(Contact contact) {
		     try {
		         PreparedStatement stmt = connection
		                 .prepareStatement("delete from contacts where id=?");
		         stmt.setLong(1, contact.getId());
		         stmt.execute();
		         stmt.close();
		     } catch (SQLException e) {
		         throw new RuntimeException(e);
		     }
		 }
	  
	  public List<Contact> getList() {
		     try {
		         List<Contact> contacts = new ArrayList<Contact>();
		         PreparedStatement stmt = this.connection.
		                 prepareStatement("select * from contacts");
		         ResultSet rs = stmt.executeQuery();
		 
		         while (rs.next()) {
		        	 // creating the Contact object
		             Contact contact = new Contact();
		             contact.setId(rs.getLong("id"));
		             contact.setName(rs.getString("name"));
		             contact.setEmail(rs.getString("email"));
		             contact.setAddress(rs.getString("address"));
		 
		             // setting the date via Calendar
		             Calendar date = Calendar.getInstance();
		             date.setTime(rs.getDate("birthDate"));
		             contact.setBirthDate(date);
		 
		             // adding the object to the list
		             contacts.add(contact);
		         }
		         rs.close();
		         stmt.close();
		         return contacts;
		     } catch (SQLException e) {
		         throw new RuntimeException(e);
		     }
		 }
}
