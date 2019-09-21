package com.example.notesclone.api;

import com.example.notesclone.model.DeletePojo;
import com.example.notesclone.model.Note;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("notes/")
    Call<ArrayList<Note>> getAllNotes();

    @POST("notes/")
    Call<Note> createNote(@Body JsonObject note);

    @PUT("notes/{id}")
    Call<Note> updateNote(@Path("id") String id, @Body JsonObject note);

    @GET("notes/{id}")
    Call<Note> getNote(@Path("id") String id);

    @DELETE("notes/{id}")
    Call<DeletePojo> deleteNote(@Path("id") String id);
}
