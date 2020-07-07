package utill;

public class HttpResponse {
    /**
     * HTTP响应状态码
     */
    private int statusCode;

    /**
     * 响应体
     */
    private String content;

    public HttpResponse(int statusCode, String content) {
        super();
        this.statusCode = statusCode;
        this.content = content;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "HttpResponse [statusCode=" + statusCode + ", content=" + content + "]";
    }
}