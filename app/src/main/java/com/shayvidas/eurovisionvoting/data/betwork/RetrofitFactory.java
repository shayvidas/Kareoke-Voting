package com.shayvidas.eurovisionvoting.data.betwork;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by roman on 15/11/2017.
 */


public interface RetrofitFactory {

    String baseUrl = "http://ec2-34-231-240-113.compute-1.amazonaws.com:8080/TG17/streaming-api/0.0.2/";

    //String baseUrl = "https://instance-alb-1093314815.us-east-1.elb.amazonaws.com/TG17/streaming-api/0.0.2/";

//    @GET("api/v1/alignment/best-position")
//    Call<BestPositionPojo> getBestPositionCallData();

//    @Headers({ "Accept: */*", })
//    @POST("upload")
//    @Multipart
//    Call<Void> uploadImage(@Part("idn") RequestBody idn, @Part MultipartBody.Part image);

    /**
     * factory will be used to differentiate calls
     */

    //compile 'com.squareup.retrofit2:retrofit:2.0.1'
    //compile 'com.squareup.retrofit2:converter-gson:2.0.1'


    class Factory {
        private static RetrofitFactory service;
        private static String ipAddress = "192.168.10.1";

        public static RetrofitFactory getInstance() {
            if (service == null) {
                OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
                builder.addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
//                        Request request = chain.request().newBuilder().addHeader("token", TokenHolder.getInstance().getJwtToken()).build();
                        Request request = chain.request().newBuilder().build();
                        return chain.proceed(request);
                    }
                });

                Retrofit retrofit = new Retrofit.Builder()
                        .client(builder.build())
                        .baseUrl(baseUrl)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                service = retrofit.create(RetrofitFactory.class);
                return service;
            } else {
                return service;
            }

        }

        public static RetrofitFactory getOneTimeInstance(String url) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            return retrofit.create(RetrofitFactory.class);
        }

        public static void changeApiBaseUrlWithtoken(String newIpAddress, String newToken) {

            if(newIpAddress != null && !ipAddress.equals(newIpAddress)) {
                ipAddress = newIpAddress;

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://" + ipAddress + "/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                service = retrofit.create(RetrofitFactory.class);
            }
        }

        public static void changeApiBaseUrl(String newIpAddress) {

            if(newIpAddress != null && !ipAddress.equals(newIpAddress)) {
                ipAddress = newIpAddress;
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://" + ipAddress + "/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                service = retrofit.create(RetrofitFactory.class);
            }
        }
    }
}
