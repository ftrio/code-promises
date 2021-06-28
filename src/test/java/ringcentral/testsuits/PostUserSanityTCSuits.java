package ringcentral.testsuits; /*
 * @author : Shahnwaz
 *on 28-06-2021
 */

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.*;
import ringcentral.common.Helper;
import ringcentral.common.dto.UserCommonErrorDto;

import java.util.HashMap;
import java.util.Map;

public class PostUserSanityTCSuits extends Helper {

    @BeforeMethod
    public void initalteAllClassed(){
        intiateClass();
    }

    /**
     * @TEST: FUTUREDATE VALIDATION CHECK ON DOB
     */

    @Test(priority = 0)
    public void tc_01_post_saveUser_dob_format_future_date_negative_case(){
        String contextPath="/api/users";
        Map<String,Object> headerMap=new HashMap<String, Object>();
        headerMap.put("Content-Type","application/json");
        Map<String,Object> bodyMap = globalApiHelper.requestbuilder(randomGenrator(6)+"@gmail.com",randomGenrator(6),randomGenrator(6),"2022-01-01");
        Response response= globalApiHelper.postMethod(contextPath,headerMap,bodyMap);
        softAssert.assertEquals(response.getStatusCode(),400);
        softAssert.assertAll();
    }

    /**
     * @TEST: INVALID DATE FORMAT VALIDATION CHECK ON DOB
     */

    @Test(priority = 1)
    public void tc_02_post_saveUser_dob_format_validation_negative_case(){
        String contextPath="/api/users";
        Map<String,Object> headerMap=new HashMap<String, Object>();
        headerMap.put("Content-Type","application/json");
        Map<String,Object> bodyMap = globalApiHelper.requestbuilder(randomGenrator(6)+"@gmail.com",randomGenrator(6),randomGenrator(6),"2021-JAN-01");
        Response response= globalApiHelper.postMethod(contextPath,headerMap,bodyMap);
        softAssert.assertEquals(response.getStatusCode(),400);
        softAssert.assertAll();
    }

    /**
     * @TEST: DUPLICATE RECORDS INSERTION VALIDATION CHECK
     */
    @Test(priority = 2)
    public void tc_03_duplicate_recrds_insertion_negative_case(){
        String contextPath="/api/users";
        Map<String,Object> headerMap=new HashMap<String, Object>();
        headerMap.put("Content-Type","application/json");
        Map<String,Object> bodyMap = globalApiHelper.requestbuilder(randomGenrator(6)+"@gmail.com",randomGenrator(6),randomGenrator(6),"2021-01-01");
        Response response=null;
        for(int i=0;i<2;i++) {
            response = globalApiHelper.postMethod(contextPath, headerMap, bodyMap);
        }
        softAssert.assertEquals(response.getStatusCode(),409);
        softAssert.assertAll();
    }

    /**
     * @TEST: PERFORMANCE INSERTION OF USER WITH 10 USER 2 PER TIME
     */

    @Test(priority = 3,invocationCount = 10,threadPoolSize = 2)
    public void tc_04_post_mutiple_record_posting_simultaneously(){
        String contextPath="/api/users";
        Map<String,Object> headerMap=new HashMap<String, Object>();
        headerMap.put("Content-Type","application/json");
        Map<String,Object> bodyMap = globalApiHelper.requestbuilder(randomGenrator(6)+"@gmail.com",randomGenrator(6),randomGenrator(6),"2021-01-01");
        Response response= globalApiHelper.postMethod(contextPath,headerMap,bodyMap);
        softAssert.assertEquals(response.getStatusCode(),201);
        softAssert.assertAll();
    }

    /**
     * @TEST: email validation : passing invalid email id
     */

    @Test(priority = 4)
    public void tc_05_email_validation_without_attherate_doube_dot(){
        String contextPath="/api/users";
        Map<String,Object> headerMap=new HashMap<String, Object>();
        headerMap.put("Content-Type","application/json");
        Map<String,Object> bodyMap = globalApiHelper.requestbuilder(randomGenrator(6)+"gmail.com",randomGenrator(6),randomGenrator(6),"2021-01-01");
        Response response1= globalApiHelper.postMethod(contextPath,headerMap,bodyMap);
        softAssert.assertEquals(response1.getStatusCode(),400);
        bodyMap.put("email",randomGenrator(6)+"@gmail..com");
        Response response2= globalApiHelper.postMethod(contextPath,headerMap,bodyMap);
        softAssert.assertEquals(response2.getStatusCode(),400);
        softAssert.assertAll();
    }

    /**
     * @TEST: registring user with already existing email id but with fresh params like fname,lname,dob
     */

    @Test(priority = 5)
    public void tc_06_email_validation_with_already_existing_email_id(){
        String contextPath="/api/users";
        Map<String,Object> headerMap=new HashMap<String, Object>();
        headerMap.put("Content-Type","application/json");
        Map<String,Object> bodyMap=globalApiHelper.requestbuilder(randomGenrator(6)+"@gmail.com",randomGenrator(6),randomGenrator(6),"2021-01-01");
        Object emailid=bodyMap.get("email");
        Response response= globalApiHelper.postMethod(contextPath,headerMap,bodyMap);
        softAssert.assertEquals(response.getStatusCode(),201);
        bodyMap.put("email",emailid);
        Response response1= globalApiHelper.postMethod(contextPath,headerMap,bodyMap);
        softAssert.assertNotEquals(response1.getStatusCode(),201);
        softAssert.assertAll();
    }

    @Test(priority = 6)
    public void tc_07_all_field_blank_validation_check(){
        String contextPath="/api/users";
        Map<String,Object> headerMap=new HashMap<String, Object>();
        headerMap.put("Content-Type","application/json");
        Map<String,Object> bodyMap=globalApiHelper.requestbuilder("","","","");
        Response response= globalApiHelper.postMethod(contextPath,headerMap,bodyMap);
        softAssert.assertEquals(response.getStatusCode(),400);
        softAssert.assertAll();
    }

    @Test(priority = 7)
    public void tc_08_all_field_null_validation_check(){
        String contextPath="/api/users";
        Map<String,Object> headerMap=new HashMap<String, Object>();
        headerMap.put("Content-Type","application/json");
        Map<String,Object> bodyMap=globalApiHelper.requestbuilder(null,null,null,null);
        Response response= globalApiHelper.postMethod(contextPath,headerMap,bodyMap);
        softAssert.assertEquals(response.getStatusCode(),400);
        softAssert.assertAll();
    }

    @Test(priority = 8)
    public void tc_09_all_field_length_validation_check(){
        String contextPath="/api/users";
        Map<String,Object> headerMap=new HashMap<String, Object>();
        headerMap.put("Content-Type","application/json");
        Map<String,Object> bodyMap=globalApiHelper.requestbuilder(randomGenrator(5)+"@gmail.com",randomGenrator(5),randomGenrator(1),"2019-01-01");
        Response response= globalApiHelper.postMethod(contextPath,headerMap,bodyMap);
        UserCommonErrorDto userCommonErrorDto=responseParser.getResponse(response);
        softAssert.assertEquals(userCommonErrorDto.getMessage(),"Validation failed");
        softAssert.assertEquals(userCommonErrorDto.getStatus(),"BAD_REQUEST");
        softAssert.assertAll();
    }
}
