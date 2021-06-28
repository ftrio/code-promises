package ringcentral.common.dto; /*
 * @author : Shahnwaz
 *on 28-06-2021
 */

import java.util.List;

public class UserCommonErrorDto {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String dayOfBirth;

    private String timestamp;
    private String status;
    private String message;
    private String debugMessage;
    private List<ObjectErrorDetls> subErrors;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDebugMessage() {
        return debugMessage;
    }

    public void setDebugMessage(String debugMessage) {
        this.debugMessage = debugMessage;
    }

    public List<ObjectErrorDetls> getSubErrors() {
        return subErrors;
    }

    public void setSubErrors(List<ObjectErrorDetls> subErrors) {
        this.subErrors = subErrors;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(String dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }
}
