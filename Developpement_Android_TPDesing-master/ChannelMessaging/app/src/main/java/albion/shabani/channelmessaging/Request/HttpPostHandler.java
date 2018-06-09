package albion.shabani.channelmessaging.Request;

import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by shabania on 26/02/2018.
 */
public class HttpPostHandler extends AsyncTask<PostRequest, Integer, String> {

    private Context myContext;
    private ArrayList<OnDownloadListener> listeners;

    public HttpPostHandler(){
        listeners = new ArrayList<OnDownloadListener>();
    }
    public void setHttpPostHandler(Context myContext) {
        this.myContext = myContext;
    }



    public void addOnDownloadListener(OnDownloadListener listener) {
        listeners.add(listener);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected String doInBackground(PostRequest... params) {

        return performPostCall(params[0].getUrl(), params[0].getParams());
    }

    public String performPostCall(String requestURL, HashMap<String, String> postDataParams) {
        URL url;
        String response = "";
        try {
            url = new URL(requestURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString(postDataParams));
            writer.flush();
            writer.close();
            os.close();
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response += line;
                }
            } else {
                response = "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (first) first = false;
            else result.append("&");
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }
        return result.toString();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        for (OnDownloadListener listener: listeners) {
            String code = "500";
            if(!s.equals("") && !s.toLowerCase().contains(code.toLowerCase()))
                listener.onDownloadComplete(s);
            else{
                listener.onDownloadError(s);
            }

        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

    }



}


