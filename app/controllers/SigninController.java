package controllers;

import commons.BCryptPasswordSecurity;
import commons.PasswordSecurity;
import dtos.ExtensionDto;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.ExtensionService;

public class SigninController extends Controller {

    private PasswordSecurity passwordSecutiry;

	/**
     * Add the content-type json to response
     *
     * @param Result httpResponse
     *
     * @return Result
     */
	
	public SigninController(){
		super();
		this.passwordSecutiry = new BCryptPasswordSecurity();
	}
	
    public Result jsonResult(Result httpResponse) {
        return httpResponse.as("application/json; charset=utf-8");
    }
    
    public Result validateUser(String numberOrEmail) {
    	ExtensionDto e = ExtensionService.find(numberOrEmail);
    	if (e != null){
			e.exte_claveweb = "***";
			e.exte_clave = "***";
    		return jsonResult(ok(Json.toJson(e)));
    	} else {
    		e = new ExtensionDto();
    		e.exte_estado = false;
    		return jsonResult(unauthorized());
    	}
		
    }
    
    public Result validateSignIn(String numberOrEmail, String password){
    	ExtensionDto e = ExtensionService.find(numberOrEmail);
    	if (e.exte_claveweb == null){
			return jsonResult(unauthorized());
		} else
    	//if (passwordSecutiry.check(password, e.exte_claveweb)){
		if (password.equals(e.exte_claveweb)) {
    		return jsonResult(ok(Json.toJson(e)));
    	} else {
    		return jsonResult(unauthorized());
    	}
    	
    }
}
