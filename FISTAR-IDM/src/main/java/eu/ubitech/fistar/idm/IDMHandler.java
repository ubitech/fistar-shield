package eu.ubitech.fistar.idm;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import eu.ubitech.fistar.rest.RESTClientProvider;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import org.json.JSONObject;

/**
 *
 * @author Christos Paraskeva
 */
public class IDMHandler {

    final RESTClientProvider restClientProvider = new RESTClientProvider("http://localhost:8080/openidm");

    //Fetch all registered users from OpenIDM Server
    public void getAllManagedUsers() {

        MultivaluedMap<String, String> params = new MultivaluedMapImpl();
        params.add("_queryId", "query-all-ids");
        params.add("_prettyPrint", "true");
        ClientResponse clientRespone = restClientProvider.getRestService().path("managed").path("user").queryParams(params).header("X-OpenIDM-Username", "openidm-admin").header("X-OpenIDM-Password", "openidm-admin").accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        String JSONString = clientRespone.getEntity(String.class);
        System.out.println(JSONString);

//        JSONObject json = new JSONObject(JSONString);
//        System.out.println(json.get("result").toString());      
    }

    public void getManagedUser(String DN) {

    }

    public void createManagedUser(String username,String password,String DN) {
        MultivaluedMap<String, String> params = new MultivaluedMapImpl();
        params.add("_action", "create");

        User user = new User();
        user.setId("tese");
        user.setUserName("tese");
        user.setPassword("User1test");
        user.setGivenName("test User");
        user.setMail("testUser@idm.com");
        user.setSn("testUser");
        user.setTelephoneNumber("5646543");

        JSONObject json = new JSONObject(user);

        System.out.println(json.toString().replace("id", "_id"));

        ClientResponse clientRespone = restClientProvider.getRestService().path("managed").path("user").queryParams(params).entity(json.toString().replace("id", "_id")).header("Content-Type", "application/json; charset=utf-8").header("X-OpenIDM-Username", "openidm-admin").header("X-OpenIDM-Password", "openidm-admin")
                .accept(MediaType.APPLICATION_JSON).post(ClientResponse.class);

        String JSONString = clientRespone.getEntity(String.class);
        System.out.println(JSONString);
    }

    public static void main(String[] args) {
        IDMHandler idm = new IDMHandler();
        // idm.getAllManagedUsers();
        idm.createManagedUser("","","");

    }

}
