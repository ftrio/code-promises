package ringcentral.common; /*
 * @author : Shahnwaz
 *on 28-06-2021
 */

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import ringcentral.common.dto.ObjectErrorDetls;
import ringcentral.common.dto.UserCommonErrorDto;
import org.json.*;

import java.util.*;

public class ResponseParser extends Helper{

    public UserCommonErrorDto getResponse( Response response) {
        UserCommonErrorDto userCommonErrorDto=new UserCommonErrorDto();
        String rawResp=response.asString();
        JsonPath jsonPath=new JsonPath(rawResp);
        if(response.getStatusCode() == 400){
            userCommonErrorDto.setStatus(jsonPath.getString("status"));
            userCommonErrorDto.setMessage(jsonPath.getString("message"));
        }else if(response.getStatusCode() == 201){
            userCommonErrorDto.setId(jsonPath.getString("id"));
            userCommonErrorDto.setFirstName(jsonPath.getString("firstName"));
            userCommonErrorDto.setLastName(jsonPath.getString("lastName"));
            userCommonErrorDto.setDayOfBirth(jsonPath.getString("dayOfBirth"));
            userCommonErrorDto.setEmail(jsonPath.getString("email"));
        }else if(response.getStatusCode() == 200){
            userCommonErrorDto.setId(jsonPath.getString("id"));
            userCommonErrorDto.setFirstName(jsonPath.getString("firstName"));
            userCommonErrorDto.setLastName(jsonPath.getString("lastName"));
            userCommonErrorDto.setDayOfBirth(jsonPath.getString("dayOfBirth"));
            userCommonErrorDto.setEmail(jsonPath.getString("email"));
        }
        return userCommonErrorDto;
    }
}
