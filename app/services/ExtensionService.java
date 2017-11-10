package services;

import daos.ExtensionDao;
import dtos.ExtensionDto;
import models.Extension;

public class ExtensionService {

	public static ExtensionDto find(String username){
		Extension e =ExtensionDao.getInstance().get(username);
		return Extension.createDto(e);
	}
	
}
