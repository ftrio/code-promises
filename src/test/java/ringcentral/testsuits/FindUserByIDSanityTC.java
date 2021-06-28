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

public class FindUserByIDSanityTC extends Helper {

    @BeforeMethod
    public void initalteAllClassed(){
        intiateClass();
    }

    @Test(priority = 0)
    public void tc_01_findUserByID_get_details_for_existing_user(){
        String contextPathgetApi="/api/users/";
        String contextPathpostApi="/api/users";
        Map<String,Object> headerMap=new HashMap<String, Object>();
        Map<String,Object> bodyMap=new HashMap<String, Object>();
        headerMap.put("Content-Type","application/json");
        bodyMap.put("email",randomGenrator(5)+"@gmail.com");
        bodyMap.put("firstName",randomGenrator(5));
        bodyMap.put("lastName",randomGenrator(5));
        bodyMap.put("dayOfBirth","2021-01-01"); //"yyyy-MM-dd"
        Response response= globalApiHelper.postMethod(contextPathpostApi,headerMap,bodyMap);
        softAssert.assertEquals(response.getStatusCode(),201);
        UserCommonErrorDto userCommonErrorDto=responseParser.getResponse(response);
        Response response1= globalApiHelper.getMethodPathParams(contextPathgetApi+userCommonErrorDto.getId());
        if(response1.getStatusCode()==200){
            UserCommonErrorDto userCommonErrorDto1=responseParser.getResponse(response1);
            softAssert.assertEquals(userCommonErrorDto1.getId(),userCommonErrorDto.getId());
            softAssert.assertEquals(userCommonErrorDto1.getFirstName(),userCommonErrorDto.getFirstName());
            softAssert.assertEquals(userCommonErrorDto1.getLastName(),userCommonErrorDto.getLastName());
            softAssert.assertEquals(userCommonErrorDto1.getEmail(),userCommonErrorDto.getEmail());
            softAssert.assertEquals(userCommonErrorDto1.getDayOfBirth(),userCommonErrorDto.getDayOfBirth());
        }
        softAssert.assertAll();
    }
    @Test(priority = 1)
    public void tc_01_findUserByID_get_details_for_non_existing_user(){
        String contextPathgetApi="/api/users/";
        Response response1= globalApiHelper.getMethodPathParams(contextPathgetApi+randomGenratorNumber(3));
        softAssert.assertNotEquals(response1.getStatusCode(),200);
        softAssert.assertAll();
    }
}
