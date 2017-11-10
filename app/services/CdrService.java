package services;

import daos.CdrDao;
import models.Cdr;

import java.util.List;

/**
 * Created by Alexander on 20/03/2017.
 */
public class CdrService {

    public static List<Cdr> fetch(String e, String startDate, String endDate){
        return CdrDao.getInstance().get(e, startDate, endDate);
    }
}
