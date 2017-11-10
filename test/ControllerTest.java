import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.running;
import static play.test.Helpers.testServer;

import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

import org.dbunit.JndiDatabaseTester;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import play.Application;
import play.libs.ws.WS;
import play.libs.ws.WSResponse;

public class ControllerTest {
    int timeout = 4000;
    JndiDatabaseTester databaseTester;
    Application app;
    ObjectNode dataOk;
    ObjectNode dataError1;
    ObjectNode dataError2;

    public ControllerTest() {
    }

    // Data needed for create the fake
    private static HashMap<String, String> settings() {
        HashMap<String, String> settings = new HashMap<String, String>();
        settings.put("db.default.url", "jdbc:mysql://localhost:3306/ippbx");
        settings.put("db.default.username", "ippbxuser");
        settings.put("db.default.password", "5[NfC@W}*6d>97J");
        return(settings);
    }

    @Before
    public void initializeData() throws Exception {
    }

    @After
    public void closeDB() throws Exception {

    }

    @Test
    public void testFindExtension() {
        running(testServer(3333, app), () -> {
        	CompletionStage<WSResponse> response = WS.newClient(2020)
                .url("http://localhost:3333/exten/2020")
                .get().whenComplete((r,e) -> {
                	Optional.ofNullable(r).ifPresent(res -> {
                		
                        assertEquals(OK, res.getStatusText());
                        assertEquals("application/json; charset=utf-8", res.getHeader("Content-Type"));

                        JsonNode responseJson = res.asJson();
                        assertTrue(responseJson.isObject());

                	});
                });
        });
    }




}
