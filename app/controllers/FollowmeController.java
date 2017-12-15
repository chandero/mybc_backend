package controllers;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import com.fasterxml.jackson.databind.JsonNode;

import services.FollowmeService;
import models.Sigueme;
import models.Extension;

public class FollowmeController extends Controller {

    public Result jsonResult(Result httpResponse) {
        return httpResponse.as("application/json; charset=utf-8");
    }

    public Result getFollowmeStatus(String extension){
        return jsonResult(ok(Json.toJson(FollowmeService.getFollowmeStatus(extension))));
    }

    public Result setFollowmeStatus(String extension, Boolean status){
        return jsonResult(ok(Json.toJson(FollowmeService.setFollowmeStatus(extension, status))));
    }

    public Result get(String extension) {
        return jsonResult(ok(Json.toJson(FollowmeService.get(extension))));
    }

    public Result setFollowmeData(){
        JsonNode json = request().body().asJson();
        Sigueme sigueme = new Sigueme();
        Extension extension = new Extension();
        Long id = json.findPath("sigu_id").longValue();
        Boolean status = json.findPath("sigu_activo").booleanValue();
        String extlist = json.findPath("sigu_extlist").textValue();
        JsonNode exten = json.get("extension");
        String exte_numero = String.valueOf(exten.get("exte_numero").intValue());
        extension.exte_numero = exte_numero;
        sigueme.sigu_id = id;
        sigueme.sigu_extlist = extlist;
        sigueme.sigu_activo = status;
        sigueme.extension = extension;
        return jsonResult(ok(Json.toJson(FollowmeService.setFollowmeData(sigueme))));
    }

}