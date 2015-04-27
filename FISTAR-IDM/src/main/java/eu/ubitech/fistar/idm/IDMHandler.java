package eu.ubitech.fistar.idm;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import eu.ubitech.fistar.rest.RESTClientProvider;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Christos Paraskeva
 */
public class IDMHandler {

    final RESTClientProvider restClientProvider = new RESTClientProvider("http://192.168.7.196:8080/openidm");

    /**
     * Fetch all registered users IDs from OpenIDM Server.
     */
    public JSONObject getAllManagedUsersIDs() {
        MultivaluedMap<String, String> params = new MultivaluedMapImpl();
        params.add("_queryId", "query-all-ids");
        params.add("_prettyPrint", "true");
        ClientResponse clientRespone = restClientProvider.getRestService().path("managed").path("user").queryParams(params).header("X-OpenIDM-Username", "openidm-admin").header("X-OpenIDM-Password", "openidm-admin").accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        String JSONString = clientRespone.getEntity(String.class);
        System.out.println(JSONString);
        JSONObject json = new JSONObject(JSONString);
        System.out.println(json.get("result").toString());
        return json;
    }

    /**
     * Fetch all registered users from OpenIDM Server.
     */
    public JSONObject getAllManagedUsers() {
        MultivaluedMap<String, String> params = new MultivaluedMapImpl();
        params.add("_queryId", "query-all");
        params.add("_prettyPrint", "true");
        ClientResponse clientRespone = restClientProvider.getRestService().path("managed").path("user").queryParams(params).header("X-OpenIDM-Username", "openidm-admin").header("X-OpenIDM-Password", "openidm-admin").accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        String JSONString = clientRespone.getEntity(String.class);
        System.out.println(JSONString);
        JSONObject json = new JSONObject(JSONString);
        System.out.println(json.get("result").toString());
        return json;
    }

    /**
     *
     * @param DN Distinguished Name of Users' certificate
     * @return
     */
    public JSONObject getManagedUser(String DN) {
        MultivaluedMap<String, String> params = new MultivaluedMapImpl();
        params.add("_prettyPrint", "true");
        ClientResponse clientRespone = restClientProvider.getRestService().path("managed").path("user").path(DN).queryParams(params).header("Content-Type", "application/json; charset=utf-8").header("X-OpenIDM-Username", "openidm-admin").header("X-OpenIDM-Password", "openidm-admin").accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        String JSONString = clientRespone.getEntity(String.class);
        System.out.println(JSONString);
        JSONObject json = new JSONObject(JSONString);
        System.out.println(clientRespone.getStatusInfo().getStatusCode() + " - " + clientRespone.getStatusInfo().getReasonPhrase());
        // return (clientRespone.getStatusInfo().getStatusCode() == 200);

        return json;
    }

    /**
     *
     * @param user
     * @return
     */
    public boolean createManagedUser(IDMUser user) {
        MultivaluedMap<String, String> params = new MultivaluedMapImpl();
        params.add("_action", "create");
        JSONObject json = new JSONObject(user);
        ClientResponse clientRespone = restClientProvider.getRestService().path("managed").path("user").queryParams(params).entity(json.toString().replace("id", "_id")).header("Content-Type", "application/json; charset=utf-8").header("X-OpenIDM-Username", "openidm-admin").header("X-OpenIDM-Password", "openidm-admin").accept(MediaType.APPLICATION_JSON).post(ClientResponse.class);
        String JSONString = clientRespone.getEntity(String.class);
        System.out.println(JSONString);
        System.out.println(clientRespone.getStatusInfo().getStatusCode() + " - " + clientRespone.getStatusInfo().getReasonPhrase());
        return (clientRespone.getStatusInfo().getStatusCode() == 201);
    }

    /**
     * Delete a user from Identity Management Server
     *
     * @param DN Distinguished Name of Users' certificate
     * @return
     */
    public boolean deleteManagerUser(String DN) {
        ClientResponse clientRespone = restClientProvider.getRestService().path("managed").path("user").path(DN).header("Content-Type", "application/json; charset=utf-8").header("X-OpenIDM-Username", "openidm-admin").header("X-OpenIDM-Password", "openidm-admin").accept(MediaType.APPLICATION_JSON).delete(ClientResponse.class);
        //String JSONString = clientRespone.getEntity(String.class);
        // System.out.println(JSONString);
        System.out.println(clientRespone.getStatusInfo().getStatusCode() + " - " + clientRespone.getStatusInfo().getReasonPhrase());
        return (clientRespone.getStatusInfo().getStatusCode() == 200);
    }

    /**
     * Fetch all registered users from OpenIDM Server.
     */
    public JSONObject getAllManagedRoles() {
        MultivaluedMap<String, String> params = new MultivaluedMapImpl();
        params.add("_prettyPrint", "true");
        params.add("_queryId", "query-all-ids");

        ClientResponse clientRespone = restClientProvider.getRestService().path("managed").path("role").queryParams(params).header("X-OpenIDM-Username", "openidm-admin").header("X-OpenIDM-Password", "openidm-admin").accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        String JSONString = clientRespone.getEntity(String.class);
        System.out.println(JSONString);
        JSONObject json = new JSONObject(JSONString);
        //System.out.println(json.get("result").toString());
        return json;
    }

    /**
     * Get all roles of IDM
     *
     * @return
     */
    public boolean createManagedRole(String roleName) {
        String entityBody = "{"
                + "   \"properties\": {"
                + "     \"description\": \"an example role\""
                + "   },"
                + "   \"assignments\": {"
                + "     \"ldap\": {"
                + "       \"attributes\": ["
                + "         {"
                + "           \"name\": \"ldapGroups\","
                + "           \"assignmentOperation\": \"mergeWithTarget\","
                + "           \"unassignmentOperation\": \"removeFromTarget\","
                + "           \"value\": ["
                + "             \"CN=staff,O=fistar\""
                + "           ]"
                + "         }"
                + "       ],"
                + "       \"onAssignment\": {"
                + "         \"file\": \"roles\\/onAssignment_ldap.js\","
                + "         \"type\": \"text\\/javascript\""
                + "       },"
                + "       \"onUnassignment\": {"
                + "         \"file\": \"roles\\/onUnassignment_ldap.js\","
                + "         \"type\": \"text\\/javascript\""
                + "       }"
                + "     }"
                + "   }"
                + " }";

        ClientResponse clientRespone = restClientProvider.getRestService().path("managed").path("role").path(roleName).entity(entityBody).header("Content-Type", "application/json; charset=utf-8").header("X-OpenIDM-Username", "openidm-admin").header("X-OpenIDM-Password", "openidm-admin").header("If-None-Match", "*").accept(MediaType.APPLICATION_JSON).put(ClientResponse.class);
        String JSONString = clientRespone.getEntity(String.class);
        System.out.println(JSONString);
        System.out.println(clientRespone.getStatusInfo().getStatusCode() + " - " + clientRespone.getStatusInfo().getReasonPhrase());
        return (clientRespone.getStatusInfo().getStatusCode() == 201);
    }

    public void deleteManagedRole(String roleName) {
        ClientResponse clientRespone = restClientProvider.getRestService().path("managed").path("role").path(roleName).header("X-OpenIDM-Username", "openidm-admin").header("X-OpenIDM-Password", "openidm-admin").accept(MediaType.APPLICATION_JSON).delete(ClientResponse.class);
        String JSONString = clientRespone.getEntity(String.class);
        System.out.println(JSONString);
        //  JSONObject json = new JSONObject(JSONString);
        //System.out.println(json.get("result").toString());
    }

    /**
     * Assign a Role to User
     *
     * @param roleName
     * @param DN
     * @return
     */
    public boolean assignRoleToUser(String roleName, String DN) {

        JSONObject jsonObject = getManagedUser(DN);
//        System.out.println(jsonObject.toString());
        JSONArray roles = jsonObject.getJSONArray("roles");
        roles.put(roleName);
        jsonObject.remove("roles");
        jsonObject.put("roles", roles);
        String entityBody = jsonObject.toString();
        ClientResponse clientRespone = restClientProvider.getRestService().path("managed").path("user").path(DN).entity(entityBody).header("Content-Type", "application/json; charset=utf-8").header("X-OpenIDM-Username", "openidm-admin").header("X-OpenIDM-Password", "openidm-admin").header("If-Match", "*").accept(MediaType.APPLICATION_JSON).put(ClientResponse.class);
//        String JSONString = clientRespone.getEntity(String.class);
//        System.out.println(JSONString);
        System.out.println(clientRespone.getStatusInfo().getStatusCode() + " - " + clientRespone.getStatusInfo().getReasonPhrase());
        return (clientRespone.getStatusInfo().getStatusCode() == 200);
    }

    /**
     * Assign a Role to User
     *
     * @param roleName
     * @param DN
     * @return
     */
    public boolean unAssignRoleToUser(String roleName, String DN) {

        JSONObject jsonObject = getManagedUser(DN);
        System.out.println(jsonObject.toString());
        JSONArray roles = jsonObject.getJSONArray("roles");

        JSONArray newRoles = new JSONArray();
        for (int i = 0; i < roles.length(); i++) {
            if (!roles.get(i).toString().equals(roleName)) {
                newRoles.put(roles.get(i));
            }
        }
        jsonObject.remove("roles");
        jsonObject.put("roles", newRoles);
        String entityBody = jsonObject.toString();
        ClientResponse clientRespone = restClientProvider.getRestService().path("managed").path("user").path(DN).entity(entityBody).header("Content-Type", "application/json; charset=utf-8").header("X-OpenIDM-Username", "openidm-admin").header("X-OpenIDM-Password", "openidm-admin").header("If-Match", "*").accept(MediaType.APPLICATION_JSON).put(ClientResponse.class);
        String JSONString = clientRespone.getEntity(String.class);
        System.out.println(JSONString);
        System.out.println(clientRespone.getStatusInfo().getStatusCode() + " - " + clientRespone.getStatusInfo().getReasonPhrase());
        return (clientRespone.getStatusInfo().getStatusCode() == 200);
    }

    public static void main(String[] args) {
        //Create new Identity Management Handler Instance
        //IDMHandler idm = new IDMHandler();
        //Show all Users
        // idm.getAllManagedUsersIDs();
//        //Sample Method to add User
        // IDMUser dummyUser = new IDMUser("CN=Clayton Fleming", "test", "Cr@t55jA@Rhc", "Pat", "Pat Barlow", "shiplots@somema1l.org");
        //    idm.createManagedUser(dummyUser);
        //Sample Method to get User
        //idm.getManagedUser(dummyUser.getId());
        //Delete a User
        //idm.deleteManagerUser(dummyUser.getId());
        //Add a managed role
        //idm.createManagedRole("fistar-user");
        //Get all managed roles
        //idm.getAllManagedRoles();
        //Delete a role
//        //idm.deleteManagedRole("role1");
        // idm.assignRoleToUser("fistar-admin", "DN=tes1");
//        idm.getAllManagedRoles();
        //    idm.unAssignRoleToUser("fistar-admin", "DN=tes1");

    }

}
