import lombok.*;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> extends BaseEntity {

    private boolean success;
    private T data;
    private String message;
    private int code;

    public static <T> Result<T> success() {
        return success(null, "");
    }

    public static <T> Result<T> success(T data) {
        return success(data, "");
    }

    public static <T> Result<T> success(T data, String message) {
        Result<T> result = new Result<>();
        result.setCode(0).setSuccess(true).setMessage(message).setData(data);
        return result;
    }
    public static <T> Result<T> failed(String message,int code) {
        return failed(null, message,code);
    }
    public static <T> Result<T> failed(String message) {
        return failed(null, message,-1);
    }
    public static <T> Result<T> failed(T data,int code) {
        return failed(data, null,code);
    }
    public static <T> Result<T> failed(T data,String message) {
        return failed(data, message,-1);
    }
    public static <T> Result<T> failed(T data) {
        return failed(data, null,-1);
    }

    public static <T> Result<T> failed(T data, String message,int code) {
        Result<T> result = new Result<>();
        return result.setCode(code).setSuccess(false).setMessage(message).setData(data);
    }

    public boolean failed() {
        return !success;
    }
}
