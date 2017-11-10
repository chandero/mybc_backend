package services;

import daos.ConferenciaDao;
import models.Extension_Conferencia;

public class ConferenciaService {

	public static Extension_Conferencia find(Long id){
		return ConferenciaDao.getInstance().get(id);
	}
	
	public static Boolean update(Extension_Conferencia exco){
		return ConferenciaDao.getInstance().update(exco);
	}
	
	public static Boolean add(Extension_Conferencia exco){
		return ConferenciaDao.getInstance().add(exco);
	}
	
}
