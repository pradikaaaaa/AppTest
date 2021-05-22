package com.example.apptest.helper

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.apptest.fragment.FavoritFragment

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE $TABLE_NAME (id INTEGER PRIMARY KEY AUTOINCREMENT, judul TEXT, img_url TEXT, slug TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
       db?.execSQL("DROP TABLE IF EXISTS"+ TABLE_NAME)
        onCreate(db)
    }

    fun insertData(judul : String, url_img : String, slug : String){
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_2,judul)
        contentValues.put(COL_3,url_img)
        contentValues.put(COL_4,slug)
        db.insert(TABLE_NAME,null,contentValues)
    }

    fun deleteData(id : String) : Int{
        val db = this.writableDatabase
        return db.delete(TABLE_NAME,"id = ?", arrayOf(id))
    }

    val allData : Cursor
    get() {
        val db = this.writableDatabase
        val res = db.rawQuery("SELECT * FROM "+ TABLE_NAME, null)
        return res
    }

    companion object{
        val DATABASE_NAME = "recipe.db"
        val TABLE_NAME = "favorit_db"
        val COL_1 = "id"
        val COL_2 = "judul"
        val COL_3 = "img_url"
        val COL_4 = "slug"
    }

}