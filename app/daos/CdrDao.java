package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import commons.ConnectionFactory;
import models.Cdr;
import models.Extension;

public class CdrDao {

	private static CdrDao instance;
	
	public static CdrDao getInstance(){
		if (instance == null){
			instance = new CdrDao();
		}
		return instance;
	}
	
	public List<Cdr> get( String extension , String starDate, String finalDate){
		List<Cdr> cdrList = new ArrayList<>();
		
		Connection conn = ConnectionFactory.getConnection();
/*
		Calendar _sDate = Calendar.getInstance();
		_sDate.setTimeInMillis(startDate.getTime());
		_sDate.set(Calendar.HOUR, 0);
		_sDate.set(Calendar.MINUTE, 0);
		_sDate.set(Calendar.SECOND, 0);

		Calendar _eDate = Calendar.getInstance();
		_eDate.setTimeInMillis(finalDate.getTime());
		_eDate.set(Calendar.HOUR,23);
		_eDate.set(Calendar.MINUTE,59);
		_eDate.set(Calendar.SECOND, 59);

		java.sql.Date iniDate = new java.sql.Date(_sDate.getTimeInMillis());
		java.sql.Date endDate = new java.sql.Date(_eDate.getTimeInMillis());

		System.out.println("Fecha Inicial:"+_sDate);
		System.out.println("Fecha Final:"+_eDate);
*/		
		try {
			conn.setAutoCommit(false);
			//PreparedStatement stmt = conn.prepareStatement("SELECT c.* FROM cdr c WHERE IF(LOCATE('@',c.channel) > 0,SUBSTRING_INDEX(SUBSTRING_INDEX(SUBSTRING_INDEX(c.channel, '/', -1),'-',1),'@',1),SUBSTRING_INDEX(SUBSTRING_INDEX(c.channel, '/', -1),'-',1)) = ? and c.calldate between ? and ? ORDER BY c.calldate DESC");
			PreparedStatement stmt = conn.prepareStatement("SELECT c.* FROM cdr c WHERE (c.src = ? or c.dst = ?) and c.calldate between ? and ? ORDER BY c.calldate DESC");
			stmt.setString(1, extension);
			stmt.setString(2, extension);
			stmt.setString(3, starDate);
			stmt.setString(4, finalDate);
			System.out.println(stmt.toString());
			ResultSet rs = stmt.executeQuery();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
			while (rs.next()){
				Cdr cdr = new Cdr();
				
				cdr.accountcode = rs.getString("accountcode");
				cdr.amaflags = rs.getInt("amaflags");
				cdr.billsec = rs.getInt("billsec");
				cdr.calldate = sdf.parse(rs.getString("calldate"));
				cdr.channel = rs.getString("channel");
				cdr.clid = rs.getString("clid");
				cdr.dcontext = rs.getString("dcontext");
				cdr.disposition = rs.getString("disposition");
				cdr.dst = rs.getString("dst");
				cdr.dstchannel = rs.getString("dstchannel");
				cdr.duration = rs.getInt("duration");
				//cdr.end = new Date(rs.getDate("end").getTime());
				cdr.lastapp = rs.getString("lastapp");
				cdr.lastdata = rs.getString("lastdata");
				//cdr.linkeid = rs.getString("linkeid");
				//cdr.sequence = rs.getInt("sequence");
				cdr.src = rs.getString("src");
				//cdr.start = new Date(rs.getDate("start").getTime());
				cdr.uniqueid = rs.getString("uniqueid");
				cdr.userfield = rs.getString("userfield");
				cdrList.add(cdr);
				System.out.println("Date: "+ cdr.calldate);
			}
			conn.commit();
		} catch (Exception e){
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		System.out.println("Lista CDR : "+ cdrList.toString());
		return cdrList;
		
	}
	
}
