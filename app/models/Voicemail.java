package models;

import java.text.SimpleDateFormat;
import java.util.Date;

import dtos.VoicemailDto;

public class Voicemail {

    public String  voic_id;
    public String  voic_file;
    public String  voic_origin;
    public Date    voic_date;
    public Integer voic_duration;

    public static VoicemailDto createDto(Voicemail v){
        
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

        VoicemailDto dto = new VoicemailDto();

        dto.voic_id = v.voic_id;
        dto.voic_file = v.voic_file;
        dto.voic_origin = v.voic_origin;
        dto.voic_date = sdf.format(v.voic_date);
        dto.voic_duration = v.voic_duration;

        return dto;
    } 
}