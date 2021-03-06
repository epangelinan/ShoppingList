package com.epicodus.shoppinglist.services;


import android.util.Log;


import com.epicodus.shoppinglist.Constants;
import com.epicodus.shoppinglist.models.Store;
import com.epicodus.shoppinglist.models.Item;

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
        urlBuilder.addQueryParameter(Constants.WALMART_ITEM_QUERY_PARAMETER, searchItem);
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

                    longDescription = longDescription.replaceAll("&lt;", "");
                    longDescription = longDescription.replaceAll("&gt;", "");
                    longDescription = longDescription.replaceAll("lili", "");
                    longDescription = longDescription.replaceAll("bulli", "");
                    longDescription = longDescription.replaceAll("br&gt;", "");
                    longDescription = longDescription.replaceAll("b&gt;", "");
                    longDescription = longDescription.replaceAll("ul&gt;", "");
                    longDescription = longDescription.replaceAll("li&gt;", "");
                    longDescription = longDescription.replaceAll("brb", "");
                    longDescription = longDescription.replaceAll("bbr", "");
                    longDescription = longDescription.replaceAll("/li/ul", "");

                    String mediumImage = itemJSON.getString("mediumImage");

                    String stock = itemJSON.getString("stock");

                    String offerType = itemJSON.getString("offerType");

                    String addToCartUrl = itemJSON.getString("addToCartUrl");

                    Item item = new Item(name, itemId, salePrice, longDescription, mediumImage, stock, offerType, addToCartUrl);
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

    public static void findStores(String location, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.WALMART_BASE_URL_STORE).newBuilder();
        urlBuilder.addQueryParameter(Constants.WALMART_FORMAT_KEY, Constants.WALMART_FORMAT_VALUE);
        urlBuilder.addQueryParameter(Constants.WALMART_STORE_ZIP_PARAMETER, location);
        urlBuilder.addQueryParameter("apiKey", Constants.WALMART_API_KEY);

        String url = urlBuilder.build().toString();

        Log.v("The URL is: ", url);
        //Example:  http://api.walmartlabs.com/v1/stores?format=json&zip=98057&apiKey=qv9ccdh494tzypuurayrdgwg

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Store> processStoreResults(Response response) {
        ArrayList<Store> stores = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONArray storesJSON = new JSONArray(jsonData);
                for (int i = 0; i < storesJSON.length(); i++) {

                    String name = storesJSON.getJSONObject(i).getString("name");

                    String country = storesJSON.getJSONObject(i).getString("country");

                    String streetAddress = storesJSON.getJSONObject(i).getString("streetAddress");

                    String city = storesJSON.getJSONObject(i).getString("city");

                    String stateProvCode = storesJSON.getJSONObject(i).getString("stateProvCode");

                    String zip = storesJSON.getJSONObject(i).getString("zip");

                    String phoneNumber = storesJSON.getJSONObject(i).getString("phoneNumber");

                    JSONArray coordinates = (JSONArray) storesJSON.getJSONObject(i).get("coordinates");
                    double latitude = coordinates.getDouble(0);
                    double longitude = coordinates.getDouble(1);

                    Store store = new Store(name, country, streetAddress, city, stateProvCode, zip, phoneNumber, latitude,longitude);
                    stores.add(store);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return stores;
    }
}
