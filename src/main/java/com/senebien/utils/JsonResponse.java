package main.java.com.senebien.utils;

import com.google.gson.Gson;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class JsonResponse {
    private Gson _gson = null;

    public Gson getGsonInstance() {
        if (this._gson == null) {
            return this._gson = new Gson();
        } else return this._gson;
    }
    //a utility method to send object
    //as JSON response
    public void sendAsJson(
            HttpServletResponse response,
            Object obj) throws IOException {

        response.setContentType("application/json");

        String res = this.getGsonInstance().toJson(obj);

        PrintWriter out = response.getWriter();

        out.print(res);
        out.flush();
    }
}
