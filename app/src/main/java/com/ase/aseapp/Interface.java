package com.ase.aseapp;

/**
 * Created by alex on 31.01.17.
 */

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Interface {

    @FormUrlEncoded
    @POST("authenticate")
    Call<Person> post(
            @Field("email") String username,
            @Field("password") String password
    );

    @GET("sessions")
    Call<List<Session>> getSessions(
            @Header("Authorization") String authHeader,
            @Query("groupId") String groupId
    );

    @GET("sessions/{sessionID}/attendance")
    Call<QRBasisCode> getAttendanceCode(
            @Header("Authorization") String authHeader,
            @Path("sessionID") String sessionID
    );

    @GET("sessions/{sessionID}/presentation")
    Call<QRBasisCode> getPresentationCode(
            @Header("Authorization") String authHeader,
            @Path("sessionID") String sessionID
    );

}
