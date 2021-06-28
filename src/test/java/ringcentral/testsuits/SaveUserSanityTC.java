package ringcentral.testsuits; /*
 * @author : Shahnwaz
 *on 28-06-2021
 */

import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ringcentral.common.Helper;
import ringcentral.common.dto.UserCommonErrorDto;

import java.util.HashMap;
import java.util.Map;

public class SaveUserSanityTC extends Helper {

    @BeforeMethod
    public void initalteAllClassed(){
        intiateClass();
    }

    @Test(priority = 0)
    public void tc_01_saveUserByID_updating_userEmailId_Already_Existing_For_Other(){
        String contextPath="/api/users/";
        Map<String,Object> headerMap=new HashMap<String, Object>();
        headerMap.put("Content-Type","application/json");
        Map<String,Object> bodyMap1 = globalApiHelper.requestbuilder(randomGenrator(6)+"@gmail.com",randomGenrator(6),randomGenrator(6),"2021-01-01");
        Response response= globalApiHelper.postMethod(contextPath,headerMap,bodyMap1);
        UserCommonErrorDto userCommonErrorDto=responseParser.getResponse(response);


        Map<String,Object> bodyMap2 = globalApiHelper.requestbuilder(randomGenrator(6)+"@gmail.com",randomGenrator(6),randomGenrator(6),"2021-01-01");
        Response response1= globalApiHelper.postMethod(contextPath,headerMap,bodyMap2);
        softAssert.assertEquals(response.getStatusCode(),201);
        UserCommonErrorDto userCommonErrorDto1=responseParser.getResponse(response1);

        /**
         *Setting Updating email id of 1st user with 2nd user
         */

        bodyMap2.put("email",userCommonErrorDto.getEmail());
        Response response2= globalApiHelper.putMethod(contextPath+userCommonErrorDto1.getId(),headerMap,bodyMap2);
        softAssert.assertEquals(response2.getStatusCode(),409);
        softAssert.assertAll();
    }
}
