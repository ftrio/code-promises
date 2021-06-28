package ringcentral.common;

    /*
    * @author : Shahnwaz
    *on 28-06-2021
    */

import com.google.gson.Gson;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GlobalApiHelper extends Helper {

    public JsonPath getMethodQueryParams(String contextPath,Map<String,Object> headerMap){
        Response response=given().
                queryParam("page",headerMap.get("page")).
                queryParam("size",headerMap.get("size")).
                queryParam("sort",headerMap.get("sort")).log().all().
                when().get(properties.getProperty("base.uri")+contextPath).
                then().log().all().extract().response();
        String rawresponse=response.asString();
        JsonPath  jsonPath=new JsonPath(rawresponse);
        return jsonPath;


    }

    public Response getMethodPathParams(String contextPath){
        Response response=given()
                .log().all().
                when().get(properties.getProperty("base.uri")+contextPath).
                then().log().all().extract().response();
        return response;


    }

    public Response postMethod(String contextPath,Map<String,Object> headerMap,Map<String,Object> body){
        Gson gson = new Gson();
        String requestjsonbody = gson.toJson(body);

        Response response=given().
                headers(headerMap).body(requestjsonbody).log().all().
                when().post(properties.getProperty("base.uri")+contextPath).
                then().log().all().extract().response();

        return response;
    }

    public Response putMethod(String contextPath,Map<String,Object> headerMap,Map<String,Object> body){
        Gson gson = new Gson();
        String requestjsonbody = gson.toJson(body);

        Response response=given().
                headers(headerMap).body(requestjsonbody).log().all().
                when().put(properties.getProperty("base.uri")+contextPath).
                then().log().all().extract().response();

        return response;
    }

    public Map<String,Object> requestbuilder(String email,String firstName,String lastName,String dob){
        Map<String,Object> bodyMap=new HashMap<String, Object>();
        bodyMap.put("email",email);
        bodyMap.put("firstName",firstName);
        bodyMap.put("lastName",lastName);
        bodyMap.put("dayOfBirth",dob); //"yyyy-MM-dd"
        return bodyMap;
    }


}
