package lu.uni.bicslab.greenbot.android.other;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import lu.uni.bicslab.greenbot.android.R;

public class ServerConnection {

    static String  BASIC_URL = "https://goodnessgroceries.com/";
    //static String BASIC_URL = "http://ec2-35-156-126-224.eu-central-1.compute.amazonaws.com:8000/";
    static String request_user_access = "request_user_access/";
    static String fetch_user_status = "fetch_user_status/";
    static String post_product_review = "post_product_review/";
    static String post_monitoring_data = "post_monitoring_data/";
    static String get_bought_products = "get_bought_products/";

    public interface ServerConnectionListner {
        public void onServerConnectionActionComplete(String value);
    }

    public static void postRequestUserAccess(final Context mContext,final JSONObject object, final ServerConnectionListner mServerConnectionListner) {//working
        // Post Request For JSONObject
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
       /* JSONObject object = new JSONObject();
        try {
            //input your API parameters
            object.put("participant_id", 25055678);
            object.put("product_category_1", "local_organic");
            object.put("product_category_2", "local_conventional");
            object.put("product_category_3", "imported_organic");
            object.put("product_category_4", "imported_conventional");
            object.put("indicator_category_1", "ind_cat_environment");
            object.put("indicator_category_2", "ind_cat_social");
            object.put("indicator_category_3", "ind_cat_governance");
            object.put("indicator_category_4", "ind_cat_economic");

        } catch (JSONException e) {
            e.printStackTrace();
        }*/
        Log.e("object", "" + object);
        // Enter the correct url for your api service site
        String postUrl = BASIC_URL + request_user_access;
        Log.e("url", "" + postUrl);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, postUrl, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println(response);
                getDataFetchUserStatus(mServerConnectionListner,mContext,object.optString("participant_id"));
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof NetworkError) {
                } else if (error instanceof ServerError) {
                } else if (error instanceof AuthFailureError) {
                } else if (error instanceof ParseError) {
                } else if (error instanceof NoConnectionError) {
                } else if (error instanceof TimeoutError) {
                    System.out.println(error);

                }
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    // Get Request For JSONObject
    public static void getDataFetchUserStatus(final ServerConnectionListner mServerConnectionListner, final Context mContext, String userid) {//working
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        try {
            String url = BASIC_URL + fetch_user_status + userid + "/";
            Log.e("url", "" + url);
            JSONObject object = new JSONObject();
            StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.e("response", response);
                    // Toast.makeText(mContext,"response"+response,Toast.LENGTH_SHORT).show();
                    //mServerConnectionListner.onServerConnectionActionComplete(response);
                    try {
                        JSONObject object = new JSONObject(response);
                        String status = object.optString("status");
                        Log.e("status", status);
                        Profile profileData = Utils.readProfileData(mContext);
                        if(status.equalsIgnoreCase(mContext.getResources().getString(R.string.requested))) {
                            profileData.setLogedin(Utils.user_requested);
                        }else{
                            profileData.setLogedin(Utils.user_loggedin);
                        }
                        Utils.saveProfile(mContext, profileData);
                        mServerConnectionListner.onServerConnectionActionComplete(status);
                        //https://www.tutorialspoint.com/how-to-read-volley-json-array-object-elements-in-android
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("error", error.toString());
                    // Toast.makeText(mContext,"Error getting response",Toast.LENGTH_SHORT).show();
                }
            });
            requestQueue.add(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void postPostProductReview(final Context mContext) { //working
        // Post Request For JSONObject
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        JSONObject object = new JSONObject();
        try {
            //input your API parameters
            object.put("participant_id", 12345678);
            object.put("product_ean", "98127634");
            object.put("timestamp", "2020-11-14 17:11:14");
            object.put("selected_indicator_main_id", "ind_animal");
            object.put("selected_indicator_secondary_id", "ind_biodiv");
            object.put("free_text_indicator", "This is a review.");
            object.put("price_checkbox_selected", true);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        // Enter the correct url for your api service site
        Log.e("object", "" + object);
        // Enter the correct url for your api service site
        String url = BASIC_URL + post_product_review;
        Log.e("url", "" + url);
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("response", response);
                // Toast.makeText(mContext,"response"+response,Toast.LENGTH_SHORT).show();

                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray array = object.getJSONArray("users");
                    //https://www.tutorialspoint.com/how-to-read-volley-json-array-object-elements-in-android
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error", error.toString());
                // Toast.makeText(mContext,"Error getting response",Toast.LENGTH_SHORT).show();
            }
        });

       /* JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, object,
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
        });*/
        requestQueue.add(request);
    }

    public static void postPostMonitoringData(final Context mContext) {//working
        // Post Request For JSONObject
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        JSONObject object = new JSONObject();
        try {
            //input your API parameters
            object.put("participant_id", 12345678);
            object.put("timestamp", "2020-11-14 17:11:14");
            object.put("activity_name", "page_viewed");
            object.put("metadata", "os=android,ean=98127634");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        // Enter the correct url for your api service site
        String url = BASIC_URL + post_monitoring_data;
        Log.e("url", "" + url);
        Log.e("object", "" + object);
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("response", response);
                // Toast.makeText(mContext,"response"+response,Toast.LENGTH_SHORT).show();

                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray array = object.getJSONArray("users");
                    //https://www.tutorialspoint.com/how-to-read-volley-json-array-object-elements-in-android
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error", error.toString());
                //  Toast.makeText(mContext,"Error getting response",Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(request);
    }

    public static void getDataGetBoughtProducts(final Context mContext, String products) {
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        try {
            String url = BASIC_URL + get_bought_products + products + "/";
            Log.e("url", "" + url);

            StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.e("response", response);

                    try {
                        JSONObject object = new JSONObject(response);
                        JSONArray array = object.getJSONArray("users");
                        //https://www.tutorialspoint.com/how-to-read-volley-json-array-object-elements-in-android
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("error", error.toString());
                }
            });
            requestQueue.add(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
