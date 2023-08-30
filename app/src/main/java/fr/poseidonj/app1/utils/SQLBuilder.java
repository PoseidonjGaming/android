package fr.poseidonj.app1.utils;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import fr.poseidonj.app1.entity.Note;

public class SQLBuilder extends SQLiteOpenHelper {
    private static final int version = 1;

    public SQLBuilder(@Nullable Context context) {
        super(context, "testDB", null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table Note (" +
                        "note_id Integer primary key autoincrement," +
                        "note_title varchar(255)," +
                        "note_content text);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void add(Note note) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("note_title", note.getTitle());
        values.put("note_content", note.getContent());
        db.insert("Note", null, values);
        db.close();
    }

    public void delete(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("Note", "note_id =?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void update(Note note) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("note_title", note.getTitle());
        values.put("note_content", note.getContent());
        db.update("Note", values, "note_id =?", new String[]{String.valueOf(note.getId())});
    }

    public List<Note> getAll() {
        List<Note> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Note", null);
        if (cursor.moveToFirst()) {
            do {
                list.add(new Note(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;

    }

    public Note getById(int id) {

        Note note = null;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("Note", new String[]{"note_id", "note_title", "note_content"}, "note_id=?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            note = new Note(Integer.parseInt(cursor.getColumnName(0)), cursor.getColumnName(1), cursor.getColumnName(2));
            cursor.close();
        }
        db.close();
        return note;

    }

    public int count() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Note", null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        return count;
    }

    public void createTestData(){
        if(count() == 0){
            add(new Note("Android ListView","Voir exemple MyListView"));
            add(new Note("Android Permissions","Voir exemple Request Permissions"));
            add(new Note("Android Storage","Voir exemple Internal/External storage"));
        }
    }

}
