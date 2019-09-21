package com.example.notesclone.presenter;

import com.example.notesclone.api.ApiClient;
import com.example.notesclone.api.ApiInterface;
import com.example.notesclone.model.DeletePojo;
import com.example.notesclone.model.Note;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiPresenter {

    private OnUpdatedListener updatedListener;
    private OnGetNoteListener getNoteListener;
    private OnGetAllNotesListener getAllNotesListener;
    private OnSavedListener savedListener;
    private OnDeletedListener deletedListener;

    private ApiInterface apiInterface;

    public ApiPresenter() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public void getAllNotes() {
        apiInterface.getAllNotes().enqueue(new Callback<ArrayList<Note>>() {
            @Override
            public void onResponse(Call<ArrayList<Note>> call, Response<ArrayList<Note>> response) {
                if (response.isSuccessful()) {
                    getAllNotesListener.onGetAllNotes(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Note>> call, Throwable t) {

            }
        });
    }

    public void getNote(String id) {
        apiInterface.getNote(id).enqueue(new Callback<Note>() {
            @Override
            public void onResponse(Call<Note> call, Response<Note> response) {
                if (response.isSuccessful()) {
                    getNoteListener.onGetNote(response.body());
                }
            }

            @Override
            public void onFailure(Call<Note> call, Throwable t) {

            }
        });
    }

    public void saveNote(String title, String note) {
        JsonObject object = new JsonObject();
        object.addProperty("title", title);
        object.addProperty("note", note);
        apiInterface.createNote(object).enqueue(new Callback<Note>() {
            @Override
            public void onResponse(Call<Note> call, Response<Note> response) {
                if (response.isSuccessful()) {
                    savedListener.onSave();
                }
            }

            @Override
            public void onFailure(Call<Note> call, Throwable t) {

            }
        });
    }

    public void deleteNote(String id) {
        apiInterface.deleteNote(id).enqueue(new Callback<DeletePojo>() {
            @Override
            public void onResponse(Call<DeletePojo> call, Response<DeletePojo> response) {
                if (response.isSuccessful()) {
                    deletedListener.onDelete();
                }
            }

            @Override
            public void onFailure(Call<DeletePojo> call, Throwable t) {

            }
        });
    }

    public void updateNote(String id, String title, String note) {
        JsonObject object = new JsonObject();
        object.addProperty("title", title);
        object.addProperty("note", note);
        apiInterface.updateNote(id, object).enqueue(new Callback<Note>() {
            @Override
            public void onResponse(Call<Note> call, Response<Note> response) {
                if (response.isSuccessful()) {
                    updatedListener.onUpdate();
                }
            }

            @Override
            public void onFailure(Call<Note> call, Throwable t) {

            }
        });
    }

    public void setOnDeletedListener(OnDeletedListener listener) {
        deletedListener = listener;
    }

    public void setOnUpdatedListener(OnUpdatedListener listener) {
        updatedListener = listener;
    }

    public void setOnGetAllNotesListener(OnGetAllNotesListener listener) {
        getAllNotesListener = listener;
    }

    public void setGetNoteListener(OnGetNoteListener listener) {
        getNoteListener = listener;
    }

    public void setOnSaveNoteListener(OnSavedListener listener) {
        savedListener = listener;
    }


    public interface OnUpdatedListener {
        void onUpdate();
    }

    public interface OnSavedListener {
        void onSave();
    }

    public interface OnDeletedListener {
        void onDelete();
    }

    public interface OnGetNoteListener {
        void onGetNote(Note note);
    }

    public interface OnGetAllNotesListener {
        void onGetAllNotes(ArrayList<Note> notes);
    }
}
