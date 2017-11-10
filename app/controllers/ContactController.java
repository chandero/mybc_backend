package controllers;

import java.util.List;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.ContactService;
import dtos.ContactDto;

public class ContactController extends Controller {

    public Result jsonResult(Result httpResponse) {
        return httpResponse.as("application/json; charset=utf-8");
    }
    public Result getList(String extension){
        List<ContactDto> contactListDto = ContactService.getList(extension);
        return jsonResult(ok(Json.toJson(contactListDto)));
    }

    public Result getContact(String extension, String id){
        return jsonResult(ok());
    }

}