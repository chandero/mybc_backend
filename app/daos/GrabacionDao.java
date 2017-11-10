package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import commons.ConnectionFactory;
import models.Grabacion;

public class GrabacionDao {

	private static GrabacionDao instance;
	
	public static GrabacionDao getInstance(){
		if (instance == null){
			instance = new GrabacionDao();
		}
		return instance;
	}
	
	
	public Grabacion get(Long id){
		 Connection conn = ConnectionFactory.getConnection();
		 Grabacion g = null;
		 try {
			 conn.setAutoCommit(false);
			 PreparedStatement stmt = conn.prepareStatement("SELECT g.* FROM grabacion g WHERE g.grab_id = ?");
			 stmt.setLong(1, id);
			 ResultSet rs = stmt.executeQuery();
			 if (rs.next()){
				 g = new Grabacion();
				 g.grab_id = rs.getLong("grab_id");
				 g.grab_nombre = rs.getString("grab_nombre");
				 g.grab_descripcion = rs.getString("grab_descripcion");
			 }
			 conn.commit();
		 } catch (Exception e){
			 try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		 }
		 return g;
	}
	
}
