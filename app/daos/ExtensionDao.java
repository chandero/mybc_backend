package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import commons.ConnectionFactory;
import models.Extension;
import models.Protocolo;
import models.Reglas_extension;

public class ExtensionDao {

	private static ExtensionDao instance;
	
	protected ExtensionDao() {
	}

	public static ExtensionDao getInstance() {
		if (instance == null){
			instance = new ExtensionDao();
		}
		return instance;
	}

	public Extension get(String username) {
		Extension exten = new Extension();
		Connection conn = ConnectionFactory.getConnection();
		try {
			conn.setAutoCommit(false);
			PreparedStatement stmt = conn.prepareStatement("SELECT e.* FROM extension e WHERE e.exte_numero = ?");
			stmt.setString(1,  username);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()){
				exten.exte_id = rs.getLong("exte_id");
				exten.exte_numero = rs.getString("exte_numero");
				exten.exte_alias = rs.getString("exte_alias");
				exten.exte_clave = rs.getString("exte_clave");				
				exten.exte_claveweb = rs.getString("exte_claveweb");
				exten.exte_estado = rs.getBoolean("exte_estado");
				exten.exte_descripcion = rs.getString("exte_descripcion");
				exten.reglas_extensions = new ArrayList<Reglas_extension>();
				PreparedStatement stmt2 = conn.prepareStatement("SELECT r.* FROM reglas_extension r WHERE r.exte_id = ?");
				stmt2.setLong(1, rs.getLong("exte_id"));
				ResultSet rsr = stmt2.executeQuery();
				while (rsr.next()){
					Reglas_extension re = new Reglas_extension();
					re.reex_id = rsr.getLong("reex_id");
					re.reex_paramentro = rsr.getString("reex_parametro");
					re.reex_valor = rsr.getString("reex_valor");
					re.reex_bandera = rsr.getInt("reex_bandera");
					exten.reglas_extensions.add(re);
				}
				
				PreparedStatement stmt3 = conn.prepareStatement("SELECT p.* FROM protocolo p WHERE p.prot_id = ?");
				stmt3.setLong(1, rs.getLong("prot_id"));
				ResultSet rsp = stmt3.executeQuery();
				while (rsp.next()){
					Protocolo pt = new Protocolo();
					pt.prot_id = rsp.getLong("prot_id");
					pt.prot_nombre = rsp.getString("prot_nombre");
					exten.protocolo = pt;
				}
			}
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		
		return exten;
	}

	public Extension get(Long id) {
		Extension exten = new Extension();
		Connection conn = ConnectionFactory.getConnection();
		try {
			conn.setAutoCommit(false);
			PreparedStatement stmt = conn.prepareStatement("SELECT e.* FROM extension e WHERE e.exte_id = ?");
			stmt.setLong(1,  id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()){
				exten.exte_id = rs.getLong("exte_id");
				exten.exte_numero = rs.getString("exte_numero");
				exten.exte_alias = rs.getString("exte_alias");
				exten.exte_clave = rs.getString("exte_clave");
				exten.exte_claveweb = rs.getString("exte_claveweb");
				exten.exte_estado = rs.getBoolean("exte_estado");
				exten.reglas_extensions = new ArrayList<Reglas_extension>();
				PreparedStatement stmt2 = conn.prepareStatement("SELECT r.* FROM reglas_extension r WHERE r.exte_id = ?");
				stmt2.setLong(1, rs.getLong("exte_id"));
				ResultSet rsr = stmt2.executeQuery();
				while (rsr.next()){
					Reglas_extension re = new Reglas_extension();
					re.reex_id = rsr.getLong("reex_id");
					re.reex_paramentro = rsr.getString("reex_parametro");
					re.reex_valor = rsr.getString("reex_valor");
					re.reex_bandera = rsr.getInt("reex_bandera");
					exten.reglas_extensions.add(re);
				}
				
				PreparedStatement stmt3 = conn.prepareStatement("SELECT p.* FROM protocolo p WHERE p.prot_id = ?");
				stmt3.setLong(1, rs.getLong("prot_id"));
				ResultSet rsp = stmt3.executeQuery();
				while (rsp.next()){
					Protocolo pt = new Protocolo();
					pt.prot_id = rsp.getLong("prot_id");
					pt.prot_nombre = rsp.getString("prot_nombre");
					exten.protocolo = pt;
				}
			}
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		
		return exten;
	}

}
