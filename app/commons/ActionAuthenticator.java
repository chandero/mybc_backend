package commons;

import dtos.ExtensionDto;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;
import services.ExtensionService;

public class ActionAuthenticator extends Security.Authenticator {
    @Override
    public String getUsername(Http.Context ctx) {
        String username = getTokenFromHeader(ctx);
        if (username != null) {
            ExtensionDto extension = ExtensionService.find(username);
            if (extension != null) {
                return extension.exte_descripcion;
            }
        }
        return null;
    }

    @Override
    public Result onUnauthorized(Http.Context context) {
        return super.onUnauthorized(context);
    }

    private String getTokenFromHeader(Http.Context ctx) {
        String[] authTokenHeaderValues = ctx.request().headers().get("X-AUTH-TOKEN");
        if ((authTokenHeaderValues != null) && (authTokenHeaderValues.length == 1) && (authTokenHeaderValues[0] != null)) {
            return authTokenHeaderValues[0];
        }
        return null;
    }

}
