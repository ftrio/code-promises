package ringcentral.common.dto; /*
 * @author : Shahnwaz
 *on 28-06-2021
 */

public class ObjectErrorDetls {
    private String respobject;
    private String field;
    private String rejectedValue;
    private String message;

    public String getRespobject() {
        return respobject;
    }

    public void setRespobject(String respobject) {
        this.respobject = respobject;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getRejectedValue() {
        return rejectedValue;
    }

    public void setRejectedValue(String rejectedValue) {
        this.rejectedValue = rejectedValue;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
