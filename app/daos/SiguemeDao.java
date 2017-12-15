package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import commons.ConnectionFactory;
import models.Sigueme;
import models.Extension;

import commons.Aplicador;

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
				Extension e = ExtensionDao.getInstance().get(rs.getLong("exte_id"));
				sigueme.extension = e;
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

    public Boolean setSiguemeStatus(String extension, Boolean status) {
        Connection conn = ConnectionFactory.getConnection();
        System.out.println("Estableciendo estado de followme de exten:" + extension + " a "+ status);
        Boolean result = false;
        try {
            conn.setAutoCommit(false);
            Extension e = ExtensionDao.getInstance().get(extension);
            if (e != null) {
                PreparedStatement stmt = conn.prepareStatement(
                        "UPDATE ippbx.sigueme s set s.sigu_activo  = ? WHERE s.exte_id = ?");
                stmt.setBoolean(1, status);
                stmt.setLong(2, e.exte_id);
                int rs = stmt.executeUpdate();
                if (rs > 0) {
                    result = true;
                }
            }
            conn.commit();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return result;
    }	

	public Boolean getSiguemeStatus(String exten){
		Boolean result = false;

		Connection conn = ConnectionFactory.getConnection();
		try {
			conn.setAutoCommit(false);
			PreparedStatement stmt = conn.prepareStatement("SELECT s.sigu_activo FROM sigueme s INNER JOIN extension e ON s.exte_id = e.exte_id WHERE e.exte_numero = ?");
			stmt.setString(1,exten);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()){
				result = rs.getBoolean("sigu_activo");
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

		
		return result;		
	}

	public Sigueme getSigueme(String exten){
		
		Sigueme sigueme = new Sigueme();
		
		Connection conn = ConnectionFactory.getConnection();
		try {
			conn.setAutoCommit(false);
			PreparedStatement stmt = conn.prepareStatement("SELECT s.* FROM sigueme s INNER JOIN extension e ON s.exte_id = e.exte_id WHERE e.exte_numero = ?");
			stmt.setString(1,exten);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()){
				sigueme.sigu_id = rs.getLong("sigu_id");
				sigueme.sigu_activo = rs.getBoolean("sigu_activo");
				sigueme.sigu_extlist = rs.getString("sigu_extlist");
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
			System.out.println("Extension:"+ sigueme.extension.exte_numero);
			Extension e = ExtensionDao.getInstance().get(sigueme.extension.exte_numero);
			System.out.println("Extension: e.id="+ e.exte_id);
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO sigueme (exte_id, moh_id, sigu_confllamada," 
					+ "sigu_tiempotimbre, sigu_lista, sigu_nombreprefijo, sigu_activo, sigu_destino, tide_id, sigu_anunciollamante, sigu_extlist)"
					+ "values (?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			stmt.setLong(1, e.exte_id);
			stmt.setInt(2, 1);
			stmt.setBoolean(3, false);
			stmt.setInt(4, 15);
			stmt.setString(5, "");
			stmt.setString(6, "");
			stmt.setBoolean(7, sigueme.sigu_activo);
			stmt.setString(8, "Hangup");
			stmt.setLong(9, 1);
			stmt.setBoolean(10, false);
			stmt.setString(11, sigueme.sigu_extlist);
			int count = stmt.executeUpdate();
			if (count > 0){
				ResultSet rs = stmt.getGeneratedKeys();
				rs.next();
				sigueme.sigu_id = rs.getLong(1); 
			}
			conn.commit();
			Aplicador.aplicar();
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
		Sigueme data = get(sigueme.sigu_id);
		try {
			conn.setAutoCommit(false);
			PreparedStatement stmt = conn.prepareStatement("UPDATE sigueme s SET "
					+ "s.sigu_activo = ?, "
					+ "s.sigu_extlist = ? "
					+ "where s.sigu_id = ?");
			//stmt.setLong(1, sigueme.extension.exte_id);
			//stmt.setInt(2, 1);
			stmt.setBoolean(1, sigueme.sigu_activo);
			stmt.setString(2, sigueme.sigu_extlist);
			stmt.setLong(3, sigueme.sigu_id);
			int result = stmt.executeUpdate();
			conn.commit();
			data.sigu_activo = sigueme.sigu_activo;
			data.sigu_extlist = sigueme.sigu_extlist;
			Aplicador.aplicar();
		} catch (Exception e){
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return data;
	}
	
	public Boolean delete(Sigueme sigueme){
		Boolean result = false;
		
		return result;
		
	}
	
	
}
