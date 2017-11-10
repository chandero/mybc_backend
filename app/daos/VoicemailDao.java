package daos;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import models.Voicemail;

public class VoicemailDao {

    private static VoicemailDao instance;

    public static VoicemailDao getInstance() {
        if (instance == null) {
            instance = new VoicemailDao();
        }
        return instance;
    }

    public List<Voicemail> getList(String directoryName) {
        System.out.println("Directory Voicemail:" + directoryName);
        List<Voicemail> messageList = new ArrayList<Voicemail>();
        File directory = new File(directoryName);
        //get all the files from a directory
        SimpleDateFormat sdf = new SimpleDateFormat("EE MMM dd HH:mm:ss a z yyyy",
                                            Locale.ENGLISH);
        System.out.println("Current Date:"+ sdf.format(new Date()));
        File[] fList = directory.listFiles();
        if (fList != null) {
            for (File file : fList) {
                if (file.isFile()) {
                    if (getFileExtension(file).equals("txt")) {
                        Properties prop = new Properties();
                        InputStream input = null;
                        try {
                            System.out.println("Leiendo el archivo:"+file.toString());
                            input = new FileInputStream(file);
                            prop.load(input);
                            Voicemail v = new Voicemail();
                            v.voic_id = prop.getProperty("msg_id");
                            v.voic_file = getFileNameWithoutExtension(file);
                            v.voic_origin = prop.getProperty("callerid");
                            v.voic_date = sdf.parse(prop.getProperty("origdate"));
                            v.voic_duration = Integer.valueOf(prop.getProperty("duration"));
                            messageList.add(v);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } else {
            System.out.println("La carpeta está vacía");
        }
        return messageList;
    }

    public Boolean del(String directoryName, String fileName) {
        Boolean result = false;

        try {
            File directory = new File(directoryName);
            if (directory.exists()) {
                for (File f : directory.listFiles())
                    if (f.getName().startsWith(fileName))
                        f.delete();
            }
            result = true;
        } catch (Exception e) {
            result = false;
        }

        return result;
    }

    public InputStream getStream(String directoryName, String fileName) {
        InputStream is = null;
        try {
            File f = new File(directoryName + fileName + ".wav");
            if (f.exists()) {
            }
            is = new FileInputStream(f);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return is;
    }

    public Long getLength(String directoryName, String fileName) {
        Long l = null;
        try {
            File f = new File(directoryName + fileName + ".wav");
            if (f.exists()) {
            }
            l = f.length();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return l;
    }

    private String getFileExtension(File file) {
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        } else {
            return "";
        }
    }

    private String getFileNameWithoutExtension(File file) {
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            return fileName.substring(0, fileName.lastIndexOf("."));
        } else {
            return fileName;
        }
    }
}