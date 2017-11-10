package controllers;

import java.io.InputStream;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.VoicemailService;

/**
 * Created by Alexander on 19/03/2017.
 */
public class VoicemailController extends Controller {


    public Result jsonResult(Result httpResponse) {
        return httpResponse.as("application/json; charset=utf-8");
    }
    public Result getVoicemailList(String extension){
        return jsonResult(ok(Json.toJson(VoicemailService.fetch(extension))));
    }

    public Result getVoicemail(String extension, String file) {
        Long lenght = VoicemailService.getLength(extension, file);
        InputStream is = VoicemailService.getStream(extension, file);
        return ok(is).withHeaders("Content-lenght", lenght.toString()).withHeader("Content-Disposition", "attachment; filename="+file+".wav");
    }

    public Result delVoicemail(String extension, String file){
        return jsonResult(ok(Json.toJson(VoicemailService.del(extension, file))));
    }

}
