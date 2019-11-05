package com.bawei.hongyang20191105.utils;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetWorkUtils {
    private NetWorkUtils() {

    }

    private static class NetHolder {
        private static final NetWorkUtils mNet = new NetWorkUtils();
    }

    public static NetWorkUtils getInstance() {
        return NetHolder.mNet;
    }

    public void postInfo(final String urlStr, final String phone, final String pwd, final NetCallback netCallback) {

        new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... strings) {
                try {
                    URL url = new URL(strings[0]);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    String parms = "phone=" + phone + "&pwd=" + pwd;
                    connection.getOutputStream().write(parms.getBytes());
                    if (connection.getResponseCode() == 200) {
                        return getInputStr(connection.getInputStream());
                    } else {

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s.isEmpty()) {
                    netCallback.onError("请求失败");
                } else {
                    netCallback.onSuccess(s);
                }

            }
        }.execute(urlStr);

    }

    public void getInfo(final String urlStr, final NetCallback netCallback) {
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... strings) {
                try {
                    URL url = new URL(strings[0]);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    if (connection.getResponseCode() == 200) {
                        return getInputStr(connection.getInputStream());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s.isEmpty()) {
                    netCallback.onError("请求失败");
                } else {
                    netCallback.onSuccess(s);
                }

            }
        }.execute(urlStr);

    }

    private String getInputStr(InputStream inputStream) throws IOException {
        StringBuffer sb = new StringBuffer();
        int leng = 0;
        byte[] bytes = new byte[1024];
        while ((leng = inputStream.read(bytes)) != -1) {
            String str = new String(bytes, 0, leng);
            sb.append(str);
        }

        return sb.toString();
    }

    public interface NetCallback {
        void onSuccess(String json);

        void onError(String error);
    }
}
