package com.mobdeve.s16.lagado.kian.mco2_abelgas_lagado_llamado;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "AnimaExpressDB";
    private static final int DATABASE_VERSION = 1;

    // Anime Table
    private static final String TABLE_ANIME = "anime";
    private static final String COLUMN_ANIME_ID = "mal_id";
    private static final String COLUMN_ANIME_IMAGE_URL = "image_url";
    private static final String COLUMN_ANIME_TITLE = "title";
    private static final String COLUMN_ANIME_EPISODES = "episodes";
    private static final String COLUMN_ANIME_STATUS = "status";
    private static final String COLUMN_ANIME_SCORE = "score";
    private static final String COLUMN_ANIME_SYNOPSIS = "synopsis";
    private static final String COLUMN_ANIME_DATE = "date";
    private static final String COLUMN_ANIME_STUDIOS = "studios";
    private static final String COLUMN_ANIME_GENRES = "genres";

    // Manga Table
    private static final String TABLE_MANGA = "manga";
    private static final String COLUMN_MANGA_ID = "mal_id";
    private static final String COLUMN_MANGA_IMAGE_URL = "image_url";
    private static final String COLUMN_MANGA_TITLE = "title";
    private static final String COLUMN_MANGA_CHAPTERS = "chapters";
    private static final String COLUMN_MANGA_STATUS = "status";
    private static final String COLUMN_MANGA_SCORE = "score";
    private static final String COLUMN_MANGA_SYNOPSIS = "synopsis";
    private static final String COLUMN_MANGA_DATE = "date";
    private static final String COLUMN_MANGA_AUTHORS = "authors";
    private static final String COLUMN_MANGA_GENRES = "genres";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ANIME_TABLE = "CREATE TABLE " + TABLE_ANIME + "("
                + COLUMN_ANIME_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_ANIME_IMAGE_URL + " TEXT,"
                + COLUMN_ANIME_TITLE + " TEXT,"
                + COLUMN_ANIME_EPISODES + " INTEGER,"
                + COLUMN_ANIME_STATUS + " TEXT,"
                + COLUMN_ANIME_SCORE + " TEXT,"
                + COLUMN_ANIME_SYNOPSIS + " TEXT,"
                + COLUMN_ANIME_DATE + " TEXT,"
                + COLUMN_ANIME_STUDIOS + " TEXT,"
                + COLUMN_ANIME_GENRES + " TEXT"
                + ")";

        String CREATE_MANGA_TABLE = "CREATE TABLE " + TABLE_MANGA + "("
                + COLUMN_MANGA_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_MANGA_IMAGE_URL + " TEXT,"
                + COLUMN_MANGA_TITLE + " TEXT,"
                + COLUMN_MANGA_CHAPTERS + " INTEGER,"
                + COLUMN_MANGA_STATUS + " TEXT,"
                + COLUMN_MANGA_SCORE + " TEXT,"
                + COLUMN_MANGA_SYNOPSIS + " TEXT,"
                + COLUMN_MANGA_DATE + " TEXT,"
                + COLUMN_MANGA_AUTHORS + " TEXT,"
                + COLUMN_MANGA_GENRES + " TEXT"
                + ")";

        db.execSQL(CREATE_ANIME_TABLE);
        db.execSQL(CREATE_MANGA_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ANIME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MANGA);
        onCreate(db);
    }

    // CRUD methods for Anime and Manga
    // Implement methods to add, get, update, and delete anime and manga entries
    // Add a new anime entry
    public void addAnime(TestAnime anime) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ANIME_ID, anime.getMal_id());
        values.put(COLUMN_ANIME_IMAGE_URL, anime.getImageUrl());
        values.put(COLUMN_ANIME_TITLE, anime.getTitle());
        values.put(COLUMN_ANIME_EPISODES, anime.getEpisodes());
        values.put(COLUMN_ANIME_STATUS, anime.getStatus());
        values.put(COLUMN_ANIME_SCORE, anime.getScore());
        values.put(COLUMN_ANIME_SYNOPSIS, anime.getSynopsis());
        values.put(COLUMN_ANIME_STUDIOS, anime.getStudios());
        values.put(COLUMN_ANIME_GENRES, anime.getGenres());
        values.put(COLUMN_ANIME_DATE, anime.getDate());

        db.insert(TABLE_ANIME, null, values);
        db.close();
    }

    // Add a new manga entry
    public void addManga(Manga manga) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_MANGA_ID, manga.getMal_id());
        values.put(COLUMN_MANGA_IMAGE_URL, manga.getImageUrl());
        values.put(COLUMN_MANGA_TITLE, manga.getTitle());
        values.put(COLUMN_MANGA_CHAPTERS, manga.getChapters());
        values.put(COLUMN_MANGA_STATUS, manga.getStatus());
        values.put(COLUMN_MANGA_SCORE, manga.getScore());
        values.put(COLUMN_MANGA_SYNOPSIS, manga.getSynopsis());
        values.put(COLUMN_MANGA_AUTHORS, manga.getAuthors());
        values.put(COLUMN_MANGA_GENRES, manga.getGenres());
        values.put(COLUMN_MANGA_DATE, manga.getDate());

        db.insert(TABLE_MANGA, null, values);
        db.close();
    }

    // Get an anime by its ID
    public TestAnime getAnime(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_ANIME, new String[] { COLUMN_ANIME_ID, COLUMN_ANIME_IMAGE_URL, COLUMN_ANIME_TITLE,
                        COLUMN_ANIME_EPISODES, COLUMN_ANIME_STATUS, COLUMN_ANIME_SCORE,
                        COLUMN_ANIME_SYNOPSIS, COLUMN_ANIME_DATE, COLUMN_ANIME_STUDIOS, COLUMN_ANIME_GENRES },
                COLUMN_ANIME_ID + "=?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            @SuppressLint("Range") TestAnime anime = new TestAnime(cursor.getInt(cursor.getColumnIndex(COLUMN_ANIME_ID)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_ANIME_IMAGE_URL)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_ANIME_TITLE)),
                    cursor.getInt(cursor.getColumnIndex(COLUMN_ANIME_EPISODES)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_ANIME_STATUS)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_ANIME_SCORE)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_ANIME_SYNOPSIS)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_ANIME_DATE)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_ANIME_STUDIOS)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_ANIME_GENRES)));

            cursor.close();
            return anime;
        }
        return null;
    }

    // Get a manga by its ID
    public Manga getManga(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_MANGA, new String[] { COLUMN_MANGA_ID, COLUMN_MANGA_IMAGE_URL, COLUMN_MANGA_TITLE,
                        COLUMN_MANGA_CHAPTERS, COLUMN_MANGA_STATUS, COLUMN_MANGA_SCORE,
                        COLUMN_MANGA_SYNOPSIS, COLUMN_MANGA_AUTHORS, COLUMN_MANGA_GENRES, COLUMN_MANGA_DATE },
                COLUMN_MANGA_ID + "=?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            @SuppressLint("Range") Manga manga = new Manga(cursor.getInt(cursor.getColumnIndex(COLUMN_MANGA_ID)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_MANGA_IMAGE_URL)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_MANGA_TITLE)),
                    cursor.getInt(cursor.getColumnIndex(COLUMN_MANGA_CHAPTERS)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_MANGA_STATUS)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_MANGA_SCORE)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_MANGA_SYNOPSIS)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_MANGA_DATE)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_MANGA_AUTHORS)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_MANGA_GENRES)));

            cursor.close();
            return manga;
        }
        return null;
    }

    // Update an anime entry
    public int updateAnime(TestAnime anime) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_ANIME_IMAGE_URL, anime.getImageUrl());
        values.put(COLUMN_ANIME_TITLE, anime.getTitle());
        values.put(COLUMN_ANIME_EPISODES, anime.getEpisodes());
        values.put(COLUMN_ANIME_STATUS, anime.getStatus());
        values.put(COLUMN_ANIME_SCORE, anime.getScore());
        values.put(COLUMN_ANIME_SYNOPSIS, anime.getSynopsis());
        values.put(COLUMN_ANIME_STUDIOS, anime.getStudios());
        values.put(COLUMN_ANIME_GENRES, anime.getGenres());
        values.put(COLUMN_ANIME_DATE, anime.getDate());

        return db.update(TABLE_ANIME, values, COLUMN_ANIME_ID + " = ?", new String[]{String.valueOf(anime.getMal_id())});
    }

    // Update a manga entry
    public int updateManga(Manga manga) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_MANGA_IMAGE_URL, manga.getImageUrl());
        values.put(COLUMN_MANGA_TITLE, manga.getTitle());
        values.put(COLUMN_MANGA_CHAPTERS, manga.getChapters());
        values.put(COLUMN_MANGA_STATUS, manga.getStatus());
        values.put(COLUMN_MANGA_SCORE, manga.getScore());
        values.put(COLUMN_MANGA_SYNOPSIS, manga.getSynopsis());
        values.put(COLUMN_MANGA_AUTHORS, manga.getAuthors());
        values.put(COLUMN_MANGA_GENRES, manga.getGenres());
        values.put(COLUMN_MANGA_DATE, manga.getDate());

        return db.update(TABLE_MANGA, values, COLUMN_MANGA_ID + " = ?", new String[]{String.valueOf(manga.getMal_id())});
    }


    // Delete an anime entry
    public void deleteAnime(int animeId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ANIME, COLUMN_ANIME_ID + " = ?", new String[]{String.valueOf(animeId)});
        db.close();
    }

    // Delete a manga entry
    public void deleteManga(int mangaId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MANGA, COLUMN_MANGA_ID + " = ?", new String[]{String.valueOf(mangaId)});
        db.close();
    }

}
