package albion.shabani.channelmessaging.Request;

import java.util.HashMap;

/**
 * Created by shabania on 19/01/2018.
 */
public class PostRequest {
    private String url;
    private HashMap<String,String> params;

    public HashMap<String, String> getParams() {
        return params;
    }

    public PostRequest(String url, HashMap<String, String> params) {
        this.url = url;
        this.params = params;
    }

    public void setParams(HashMap<String, String> params) {
        this.params = params;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

