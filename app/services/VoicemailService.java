package services;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import daos.VoicemailDao;
import dtos.VoicemailDto;
import models.Voicemail;

public class VoicemailService {
    public static List<VoicemailDto> fetch(String extension) {
        String directoryName = getDir() + extension + "/INBOX/";
        List<Voicemail> voicemailList = VoicemailDao.getInstance().getList(directoryName);
        List<VoicemailDto> dtoList = new ArrayList<VoicemailDto>();
        for (Voicemail v : voicemailList) {
            VoicemailDto dto = Voicemail.createDto(v);
            dtoList.add(dto);
        }
        return dtoList;
    }

    public static Boolean del(String extension, String file) {
        String directoryName = getDir() + extension + "/INBOX/";
        return VoicemailDao.getInstance().del(directoryName, file);
    }

    public static InputStream getStream(String extension, String file) {
        String directoryName =  getDir() + extension + "/INBOX/";
        return VoicemailDao.getInstance().getStream(directoryName, file);
    }

    public static Long getLength(String extension, String file) {
        String directoryName =  getDir() + extension + "/INBOX/";
        return VoicemailDao.getInstance().getLength(directoryName, file);
    }

    public static Boolean getVoicemailStatus(String extension){
        return VoicemailDao.getInstance().getVoicemailStatus(extension);
    }

    public static Boolean setVoicemailStatus(String extension, Boolean status){
        return VoicemailDao.getInstance().setVoicemailStatus(extension, status);
    }

    private static String getDir() {
        String result = "/";
        String currDir = Paths.get(".").toAbsolutePath().normalize().toString();
        Properties prop = new Properties();
        InputStream input = null;
        try {
            File f = new File(currDir+"/dao.properties");
            input = new FileInputStream(f);
            prop.load(input);
            result = prop.getProperty("voicemaildir");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}