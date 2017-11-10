package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import commons.ConnectionFactory;
import models.Sigueme;

public class SiguemeDao {

	private static SiguemeDao instance;
	
	public static SiguemeDao getInstance(){
		if (instance == null){
			instance = new SiguemeDao();
		}
		return instance;
	}
	
	public Sigueme get(Long id){
		
		Sigueme sigueme = new Sigueme();
		
		Connection conn = ConnectionFactory.getConnection();
		try {
			conn.setAutoCommit(false);
			PreparedStatement stmt = conn.prepareStatement("SELECT s.* FROM sigueme s WHERE s.exte_id = ?");
			stmt.setLong(1,id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()){
				sigueme.sigu_id = rs.getLong("sigu_id");
				sigueme.sigu_activo = rs.getBoolean("sigu_activo");
				sigueme.sigu_anunciollamante = rs.getBoolean("sigu_anunciollamante");
				sigueme.sigu_confllamada = rs.getBoolean("sigu_confllamada");
				sigueme.sigu_destino = rs.getString("sigu_destino");
				sigueme.sigu_extlist = rs.getString("sigu_extlist");
				sigueme.sigu_lista = rs.getString("sigu_lista");
				sigueme.sigu_nombreprefijo = rs.getString("sigu_nombreprefijo");
				sigueme.sigu_tiempotimbre = rs.getInt("sigu_tiempotimbre");
			}
		} catch (Exception e){
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

		
		return sigueme;
		
	}

	public Sigueme add(Sigueme sigueme){
		Connection conn = ConnectionFactory.getConnection();
		try {
			conn.setAutoCommit(false);
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO sigueme (exte_id, moh_id, sigu_confllamada," 
					+ "sigu_tiempotimbre, sigu_lista, sigu_nombreprefijo, sigu_activo, sigu_destino, tide_id, sigu_anunciollamante, sigu_extlist)"
					+ "values (?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			stmt.setLong(1, sigueme.extension.exte_id);
			stmt.setInt(2, 1);
			stmt.setBoolean(3, sigueme.sigu_confllamada);
			stmt.setInt(4, sigueme.sigu_tiempotimbre);
			stmt.setString(5, sigueme.sigu_lista);
			stmt.setString(6, sigueme.sigu_nombreprefijo);
			stmt.setBoolean(7, sigueme.sigu_activo);
			stmt.setString(8, sigueme.sigu_destino);
			stmt.setLong(9, sigueme.tipo_destino.tide_id);
			stmt.setBoolean(10, sigueme.sigu_anunciollamante);
			stmt.setString(11, sigueme.sigu_extlist);
			int count = stmt.executeUpdate();
			if (count > 0){
				ResultSet rs = stmt.getGeneratedKeys();
				rs.next();
				sigueme.sigu_id = rs.getLong(1); 
			}
		} catch (Exception e){
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return sigueme;
	}
	
	public Sigueme update(Sigueme sigueme){
		Connection conn = ConnectionFactory.getConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement("UPDATE sigueme s SET "
					+ "s.sigu_confllamada = ?, "
					+ "s.sigu_tiempotimbre = ?, "
					+ "s.sigu_lista = ?,"
					+ "s.sigu_nombreprefijo = ?,"
					+ "s.sigu_activo = ?, "
					+ "s.sigu_destino = ?, "
					+ "s.tide_id = ?, "
					+ "s.sigu_anunciollamante = ?,"
					+ "s.sigu_extlist = ? "
					+ "where s.sigu_id = ?");
			//stmt.setLong(1, sigueme.extension.exte_id);
			//stmt.setInt(2, 1);
			stmt.setBoolean(3, sigueme.sigu_confllamada);
			stmt.setInt(4, sigueme.sigu_tiempotimbre);
			stmt.setString(5, sigueme.sigu_lista);
			stmt.setString(6, sigueme.sigu_nombreprefijo);
			stmt.setBoolean(7, sigueme.sigu_activo);
			stmt.setString(8, sigueme.sigu_destino);
			stmt.setLong(9, sigueme.tipo_destino.tide_id);
			stmt.setBoolean(10, sigueme.sigu_anunciollamante);
			stmt.setString(11, sigueme.sigu_extlist);
			stmt.setLong(12, sigueme.sigu_id);
		} catch (Exception e){
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return sigueme;
	}
	
	public Boolean delete(Sigueme sigueme){
		Boolean result = false;
		
		return result;
		
	}
	
	
}
