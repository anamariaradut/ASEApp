package com.ase.aseapp;

/**
 * Created by alex on 31.01.17.
 *
 * This class enables the communication between the api and the app
 *
 * The communication is controlled by a retrofit object
 */


import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.squareup.otto.Produce;

public class Communicator {
    private static  final String TAG = "Communicator";
    private static final String SERVER_URL = "http://automatedattendancetracker.appspot.com/api/";

    public void loginPost(String username, String password){

        //Here a logging interceptor is created
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        //The logging interceptor will be added to the http client
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.addInterceptor(logging);

        //The Retrofit builder will have the client attached, in order to get connection logs
        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(SERVER_URL)
                .build();

        Interface service = retrofit.create(Interface.class);

        Call<Person> call = service.post(username,password);

        call.enqueue(new Callback<Person>() {

            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                BusProvider.getInstance().post(new ServerEvent(response.body()));
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {
                // handle execution failures like no internet connectivity
                BusProvider.getInstance().post(new ErrorEvent(-2,t.getMessage()));

            }
        });
    }

    @Produce
    public ServerEvent produceServerEvent(Person person) {
        return new ServerEvent(person);
    }

    @Produce
    public ErrorEvent produceErrorEvent(int errorCode, String errorMsg) {
        return new ErrorEvent(errorCode, errorMsg);
    }

    public void getSessions(String token, String groupId){
        //Here a logging interceptor is created
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        //The logging interceptor will be added to the http client
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        //The Retrofit builder will have the client attached, in order to get connection logs
        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(SERVER_URL)
                .build();

        Interface service = retrofit.create(Interface.class);

        Call<List<Session>> call = service.getSessions(token, groupId);

        call.enqueue(new Callback<List<Session>>() {

            @Override
            public void onResponse(Call<List<Session>> call, Response<List<Session>> response) {
                BusProvider.getInstance().post(new ServerEvent(response.body()));
            }

            @Override
            public void onFailure(Call<List<Session>> call, Throwable t) {
                // handle execution failures like no internet connectivity
                BusProvider.getInstance().post(new ErrorEvent(-2,t.getMessage()));
            }
        });
        }
    @Produce
    public ServerEvent produceServerEvent(List<Session> groupSessions) {

        return new ServerEvent(groupSessions);
    }

    public void getAttendanceCode(String token, String sessionID){
        //Here a logging interceptor is created
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        //The logging interceptor will be added to the http client
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        //The Retrofit builder will have the client attached, in order to get connection logs
        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(SERVER_URL)
                .build();

        Interface service = retrofit.create(Interface.class);

        Call<QRBasisCode> call = service.getAttendanceCode(token, sessionID);

        call.enqueue(new Callback<QRBasisCode>() {

            @Override
            public void onResponse(Call<QRBasisCode> call, Response<QRBasisCode> response) {
                BusProvider.getInstance().post(new ServerEvent(response.body()));
            }

            @Override
            public void onFailure(Call<QRBasisCode> call, Throwable t) {
                // handle execution failures like no internet connectivity
                BusProvider.getInstance().post(new ErrorEvent(-2,t.getMessage()));
            }
        });
    }
    @Produce
    public ServerEvent produceServerEvent(QRBasisCode QRBasisCode) {

        return new ServerEvent(QRBasisCode);
    }

    public void getPresentationCode(String token, String sessionID){
        //Here a logging interceptor is created
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        //The logging interceptor will be added to the http client
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        //The Retrofit builder will have the client attached, in order to get connection logs
        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(SERVER_URL)
                .build();

        Interface service = retrofit.create(Interface.class);

        Call<QRBasisCode> call = service.getPresentationCode(token, sessionID);

        call.enqueue(new Callback<QRBasisCode>() {

            @Override
            public void onResponse(Call<QRBasisCode> call, Response<QRBasisCode> response) {
                BusProvider.getInstance().post(new ServerEvent(response.body()));
            }

            @Override
            public void onFailure(Call<QRBasisCode> call, Throwable t) {
                // handle execution failures like no internet connectivity
                BusProvider.getInstance().post(new ErrorEvent(-2,t.getMessage()));
            }
        });
    }
}

