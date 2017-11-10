package daos;

import java.util.List;

import commons.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Contact;

public class ContactDao {

    private static ContactDao instance;
	
	public static ContactDao getInstance(){
		if (instance == null){
			instance = new ContactDao();
		}
		return instance;
	}

    public List<Contact> getList(){
        List<Contact> listContact = new ArrayList<Contact>();

        Connection conn = ConnectionFactory.getConnection();

        try {
			conn.setAutoCommit(false);
			PreparedStatement stmt = conn.prepareStatement("SELECT c.* FROM extension c");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Contact c = new Contact();
                c.cont_name = rs.getString("exte_descripcion");
                c.cont_number = rs.getString("exte_numero");
                listContact.add(c);
            }
            conn.commit();
        }catch (Exception e){
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
        System.out.println("Lista Contact : "+ listContact.toString());
        return listContact;

    }
}