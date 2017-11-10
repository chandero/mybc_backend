package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import commons.ConnectionFactory;
import models.Conferencia_invitado;
import models.Conferencia_programa;
import models.Extension;
import models.Extension_Conferencia;
import models.Grabacion;

public class ConferenciaDao {

	private static ConferenciaDao instance;

	public static ConferenciaDao getInstance() {
		if (instance == null) {
			instance = new ConferenciaDao();
		}
		return instance;
	}

	public Extension_Conferencia get(Long id) {
		Connection conn = ConnectionFactory.getConnection();
		Extension_Conferencia exco = new Extension_Conferencia();
		try {
			conn.setAutoCommit(false);
			PreparedStatement stmt = conn
					.prepareStatement("SELECT c.* FROM extension_conferencia c WHERE c.exco_id = ?");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				exco.exco_id = rs.getLong("exco_id");
				exco.exco_pinusuario = rs.getString("exco_pinusuario");
				exco.exco_pinadmin = rs.getString("exco_pinadmin");
				exco.exco_esperarlider = rs.getBoolean("exco_esperarlider");
				exco.exco_modosilencioso = rs.getBoolean("exco_modosilencioso");
				exco.exco_conteousuario = rs.getBoolean("exco_conteousuario");
				exco.exco_usuarionotificar = rs.getBoolean("exco_usuarionotificar");
				exco.exco_menu = rs.getBoolean("exco_menu");
				exco.exco_grabar = rs.getBoolean("exco_grabar");
				exco.exco_numero = rs.getString("exco_numero");
				Grabacion g = GrabacionDao.getInstance().get(rs.getLong("grab_id"));
				exco.grabacion = g;
				Extension e = ExtensionDao.getInstance().get(rs.getLong("exte_id"));
				exco.extension = e;
			}
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

		return exco;
	}

	public Boolean update(Extension_Conferencia exco) {
		Boolean result = false;
		Connection conn = ConnectionFactory.getConnection();

		try {
			conn.setAutoCommit(false);
			PreparedStatement stmt = conn.prepareStatement(
					"UPDATE extension_conferencia c SET " + "c.grab_id = ?, " + "c.exco_pinusuario = ?, "
							+ "c.exco_pinadmin = ?," + "c.exco_esperarlider = ?," + "c.exco_modosilencioso = ?,"
							+ "c.exco_conteousuario = ?," + "c.exco_usuarionotificar = ?," + "c.exco_menu = ?, "
							+ "c.exco_grabar = ?, " + "c.exte_id = ?," + "c.exco_numero = ? " + "where c.exco_id = ?");
			stmt.setLong(1, exco.grabacion.grab_id);
			stmt.setString(2, exco.exco_pinusuario);
			stmt.setString(3, exco.exco_pinadmin);
			stmt.setBoolean(4, exco.exco_esperarlider);
			stmt.setBoolean(5, exco.exco_modosilencioso);
			stmt.setBoolean(6, exco.exco_conteousuario);
			stmt.setBoolean(7, exco.exco_usuarionotificar);
			stmt.setBoolean(8, exco.exco_menu);
			stmt.setBoolean(9, exco.exco_grabar);
			stmt.setLong(10, exco.extension.exte_id);
			stmt.setString(11, exco.exco_numero);
			stmt.setLong(12, exco.exco_id);
			int count = stmt.executeUpdate();
			if (count > 0) {
				result = true;
			}
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

	public Boolean add(Extension_Conferencia exco) {
		Boolean result = false;
		Connection conn = ConnectionFactory.getConnection();

		try {
			conn.setAutoCommit(false);
			PreparedStatement stmt = conn.prepareStatement("INSERT extension_conferencia(" + "grab_id,"
					+ "exco_pinusuario," + "exco_pinadmin," + "exco_esperarlider," + "exco_modosilencioso,"
					+ "exco_conteousuario," + "exco_usuarionotificar," + "exco_menu," + "exco_grabar," + "exte_id,"
					+ "exco_numero) VALUES (" + "?, " + "?, " + "?," + "?," + "?," + "?," + "?," + "?, " + "?, " + "?,"
					+ "?)");
			stmt.setLong(1, exco.grabacion.grab_id);
			stmt.setString(2, exco.exco_pinusuario);
			stmt.setString(3, exco.exco_pinadmin);
			stmt.setBoolean(4, exco.exco_esperarlider);
			stmt.setBoolean(5, exco.exco_modosilencioso);
			stmt.setBoolean(6, exco.exco_conteousuario);
			stmt.setBoolean(7, exco.exco_usuarionotificar);
			stmt.setBoolean(8, exco.exco_menu);
			stmt.setBoolean(9, exco.exco_grabar);
			stmt.setLong(10, exco.extension.exte_id);
			stmt.setString(11, exco.exco_numero);
			int count = stmt.executeUpdate();
			if (count > 0) {
				result = true;
			}
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

	public Conferencia_programa addSchedule(Conferencia_programa cp) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		Connection conn = ConnectionFactory.getConnection();

		try {
			conn.setAutoCommit(false);
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO Conferencia_programa("
					+ "exco_id, copr_fecha, copr_duracion, copr_activa, copr_descripcion) values (?,?,?,?,?)");
			stmt.setInt(1, cp.exco_id);
			stmt.setDate(2, new java.sql.Date(sdf.parse(cp.copr_fecha).getTime()));
			stmt.setInt(3, cp.copr_duracion);
			stmt.setBoolean(4, true);
			stmt.setString(5, cp.copr_descripcion);
			int count = stmt.executeUpdate();
			if (count > 0) {
				ResultSet rs = stmt.getGeneratedKeys();
				rs.next();
				cp.copr_id = rs.getInt(1);
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

		return cp;
	}

	public List<Conferencia_programa> getList(String extension) {
		Connection conn = ConnectionFactory.getConnection();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		List<Conferencia_programa> listMeet = new ArrayList<Conferencia_programa>();

		Integer exco_id = null;

		try {
			conn.setAutoCommit(false);

			// Busco Id de la extension

			PreparedStatement stmtExten = conn.prepareStatement("SELECT e.* FROM Extension e WHERE e.exte_numero = ?");
			stmtExten.setString(1, extension);
			ResultSet rs = stmtExten.executeQuery();
			if (rs.next()) {
				exco_id = rs.getInt("exte_id");
			} else {
				exco_id = null;
			}

			if (exco_id != null) {
				PreparedStatement stmt = conn
						.prepareStatement("SELECT c.* FROM Conferencia_programa c WHERE c.exco_id = ?");
				stmt.setInt(1, exco_id);
				rs = stmt.executeQuery();
				while (rs.next()) {
					Conferencia_programa cp = new Conferencia_programa();
					cp.copr_id = rs.getInt("copr_id");
					cp.exco_id = rs.getInt("exco_id");
					cp.copr_fecha = sdf.format(rs.getDate("copr_fecha"));
					cp.copr_activa = rs.getBoolean("copr_activa");
					cp.copr_descripcion = rs.getString("copr_descripcion");
					// Leer invitados
					PreparedStatement stmtInvitado = conn.prepareStatement("SELECT c.* FROM Conferencia_invitado c WHERE c.copr_id = ?");
					stmtInvitado.setInt(1, cp.copr_id);
					ResultSet rsInv = stmtInvitado.executeQuery();
					while (rsInv.next()){
						Conferencia_invitado ci = new Conferencia_invitado();
						ci.coin_id = rs.getInt(1);
						ci.coin_numero = rs.getString(2);
						ci.coin_tipo = rs.getInt(3);
						ci.copr_id = rs.getInt(4);

						cp.invitados.add(ci);
					}
					listMeet.add(cp);
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

		return listMeet;
	}

	

}
