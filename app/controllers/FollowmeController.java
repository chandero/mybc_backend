package controllers;

import com.fasterxml.jackson.databind.JsonNode;

import play.mvc.Controller;
import play.mvc.Result;

public class FollowmeController extends Controller {

    public Result get(String extension){
        return ok();
    }

    public Result set(){
       JsonNode json = request().body().asJson();
       return ok();
    }

}