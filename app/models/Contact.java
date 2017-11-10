package models;

import dtos.ContactDto;

public class Contact {

    public String cont_name;
    public String cont_number;

    public static ContactDto createDto(Contact c){
        ContactDto dto = new ContactDto();
        dto.cont_name = c.cont_name;
        dto.cont_number = c.cont_number;

        return dto;
    }

}