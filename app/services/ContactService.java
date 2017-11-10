package services;

import java.util.ArrayList;
import java.util.List;

import daos.ContactDao;
import dtos.ContactDto;
import models.Contact;

public class ContactService {

    public static List<ContactDto> getList(String extension){
        List<Contact> contactList = ContactDao.getInstance().getList();
        List<ContactDto> dtoList = new ArrayList<ContactDto>();
        for (Contact c : contactList){
            ContactDto dto = Contact.createDto(c);
            dtoList.add(dto);
        }

        return dtoList;
    }
}