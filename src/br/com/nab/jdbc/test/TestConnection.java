package br.com.nab.jdbc.test;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.nab.jdbc.ConnectionFactory;

public class TestConnection {

	public static void main(String[] args) throws SQLException {		
		Connection connection = new ConnectionFactory().getConnection();
		System.out.println("Connection open!");
		connection.close();
	}
}
