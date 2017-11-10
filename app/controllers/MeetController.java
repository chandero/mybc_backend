package controllers;

import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by Alexander on 19/03/2017.
 */
public class MeetController extends Controller {

    public Result getMeetList(String extension){
        return ok();
    }

    public Result getMeet(String extension, String file){
        return ok();
    }

    public Result delMeet(String extension, String file){
        return ok();
    }
}
