package com.epicodus.shoppinglist;


import android.util.Log;




import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WalmartService {

    public static void findItems(String searchItem, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.WALMART_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter("query", searchItem);
        urlBuilder.addQueryParameter(Constants.WALMART_FORMAT_KEY, Constants.WALMART_FORMAT_VALUE);
        urlBuilder.addQueryParameter("apiKey", Constants.WALMART_API_KEY);

        String url = urlBuilder.build().toString();

        Log.v("The URL is: ", url);
        //Example URL:  http://api.walmartlabs.com/v1/search?query=ipod&format=json&apiKey=qv9ccdh494tzypuurayrdgwg

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Item> processResults(Response response) {
        ArrayList<Item> items = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject walmartJSON = new JSONObject(jsonData);
                JSONArray itemsJSON = walmartJSON.getJSONArray("items");
                for (int i = 0; i < itemsJSON.length(); i++) {
                    JSONObject itemJSON = itemsJSON.getJSONObject(i);

                    String name = itemJSON.getString("name");

                    String itemId = itemJSON.getString("itemId");

                    double salePrice = itemJSON.getDouble("salePrice");

                    String longDescription = itemJSON.getString("longDescription");

                    String mediumImage = itemJSON.getString("mediumImage");

                    String stock = itemJSON.getString("stock");

                    String offerType = itemJSON.getString("offerType");

                    Item item = new Item(name, itemId, salePrice, longDescription, mediumImage, stock, offerType);
                    items.add(item);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return items;
    }
}
