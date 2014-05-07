package ch.alv.components.web.endpoint.filter;

/**
 * The result of a security filter check.
 *
 * @since 1.0.0
 */
public class SecurityFilterResult {

    public static final int OK = 0;
    public static final int NOK = -1;

    private int result;

    private String message;

    public SecurityFilterResult(int result, String message) {
        setResult(result);
        setMessage(message);
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isOk() {
        return result == OK;
    }
}
