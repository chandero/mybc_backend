package services;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import daos.SiguemeDao;
import dtos.SiguemeDto;
import models.Sigueme;

public class FollowmeService {

    public static Boolean getFollowmeStatus(String extension){
        return SiguemeDao.getInstance().getSiguemeStatus(extension);
    }

    public static Boolean setFollowmeStatus(String extension, Boolean status){
        return SiguemeDao.getInstance().setSiguemeStatus(extension, status);
    }

    public static Sigueme get(String extension){
        return SiguemeDao.getInstance().getSigueme(extension);
    }

    public static Sigueme setFollowmeData(Sigueme sigueme){
        if (sigueme.sigu_id > 0) {
            return SiguemeDao.getInstance().update(sigueme);
        } else {
            return SiguemeDao.getInstance().add(sigueme);
        }
    }
 
}