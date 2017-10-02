package com.epicodus.shoppinglist;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class WalmartService {

    public static void findItems(String item, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.WALMART_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.WALMART_ITEM_QUERY_PARAMETER, item);
        urlBuilder.addQueryParameter("format", "json");
        urlBuilder.addQueryParameter("apiKey", Constants.WALMART_APP_KEY);

        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);

        //http://api.walmartlabs.com/v1/search?query=ipod&format=json&apiKey=qv9ccdh494tzypuurayrdgwg
    }
}
