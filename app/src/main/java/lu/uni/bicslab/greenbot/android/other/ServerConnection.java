package lu.uni.bicslab.greenbot.android.other;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ServerConnection {

    static String  BASIC_URL = "http://localhost:8000/";
    static  String request_user_access = "request_user_access/";
    static  String fetch_user_status = "fetch_user_status/";
    static  String post_product_review = "post_product_review/";
    static  String post_monitoring_data = "post_monitoring_data/";
    static  String get_bought_products = "get_bought_products/";

    public static void postRequestUserAccess(final Context mContext){
        // Post Request For JSONObject
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        JSONObject object = new JSONObject();
        try {
            //input your API parameters
            object.put("participant_id",12345678);
            object.put("product_category_1","ind_cat_environment");
            object.put("product_category_2","ind_cat_social");
            object.put("product_category_3","ind_cat_governance");
            object.put("product_category_4","ind_cat_economic");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        // Enter the correct url for your api service site

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, BASIC_URL+requestQueue, object,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("String Response : ", response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mContext,"Error getting response",Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
    // Get Request For JSONObject
    public void getDataFetchUserStatus(final Context mContext){
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        try {

            JSONObject object = new JSONObject();
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, BASIC_URL+fetch_user_status, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Toast.makeText(mContext, "I am OK !" + response.toString(), Toast.LENGTH_LONG).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(mContext, "Error", Toast.LENGTH_LONG).show();
                }
            });
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void postPostProductReview(final Context mContext){
        // Post Request For JSONObject
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        JSONObject object = new JSONObject();
        try {
            //input your API parameters
            object.put("participant_id",12345678);
            object.put("product_ean","ind_cat_environment");
            object.put("timestamp","ind_cat_social");
            object.put("selected_indicator_main_id","ind_cat_governance");
            object.put("selected_indicator_secondary_id","ind_cat_economic");
            object.put("free_text_indicator","ind_cat_economic");
            object.put("price_checkbox_selected","ind_cat_economic");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        // Enter the correct url for your api service site

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, BASIC_URL+requestQueue, object,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("String Response : ", response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mContext,"Error getting response",Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
    public static void postPostMonitoringData(final Context mContext){
        // Post Request For JSONObject
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        JSONObject object = new JSONObject();
        try {
            //input your API parameters
            object.put("participant_id",12345678);
            object.put("timestamp","ind_cat_social");
            object.put("activity_name","ind_cat_governance");
            object.put("metadata","ind_cat_economic");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        // Enter the correct url for your api service site

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, BASIC_URL+requestQueue, object,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("String Response : ", response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mContext,"Error getting response",Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    public void getDataGetBoughtProducts(final Context mContext){
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        try {

            JSONObject object = new JSONObject();
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, BASIC_URL+get_bought_products, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Toast.makeText(mContext, "I am OK !" + response.toString(), Toast.LENGTH_LONG).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(mContext, "Error", Toast.LENGTH_LONG).show();
                }
            });
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
