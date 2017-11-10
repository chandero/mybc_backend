package controllers;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.CdrService;

/**
 * Created by Alexander on 19/03/2017.
 */
public class CdrController extends Controller {

    public Result jsonResult(Result httpResponse) {
        return httpResponse.as("application/json; charset=utf-8");
    }

    public Result getCdrData(String number, String init_date, String end_date){
        return jsonResult(ok(Json.toJson(CdrService.fetch(number, init_date, end_date))));
    }

    public Result getRecord(String uniqueid){
        return ok();
    }

}
