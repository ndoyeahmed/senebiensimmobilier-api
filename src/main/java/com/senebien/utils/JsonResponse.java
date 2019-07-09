package main.java.com.senebien.utils;

import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class JsonResponse {
    private Gson gson = null;

    public Gson getGsonInstance() {
        if (this.gson == null) {
            this.gson = new Gson();
            return this.gson;
        } else return this.gson;
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

    public String onPost(HttpServletRequest request) throws IOException {
        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {

            StringBuilder buffer = new StringBuilder();
            BufferedReader reader = request.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            return buffer.toString();
        }
        return null;
    }
}
