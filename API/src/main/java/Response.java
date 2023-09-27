import java.io.Serializable;

public class Response implements Serializable {
    private static final long serialVersionUID = 1L;
    /*
    * status = 200 => success
    * status = 100 => error
    * */
    private int status;
    private Object data;

    public Response(int status, Object data) {
        this.status = status;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
