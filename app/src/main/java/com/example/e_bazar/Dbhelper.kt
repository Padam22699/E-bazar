package com.example.e_bazar

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Dbhelper(context: Context):SQLiteOpenHelper(context,DB_Name,null,1) {
    companion object{
        private const val DB_Name="Login"
    }
    override fun onCreate(MyDb: SQLiteDatabase?) {
        MyDb?.execSQL("CREATE TABLE users(id integer PRIMARY KEY AUTOINCREMENT,username Text , password Text)")

    }

    override fun onUpgrade(MyDB: SQLiteDatabase? , oldVersion: Int , newVersion: Int) {
       MyDB?.execSQL("drop Table if exists users")
    }

fun insertData(username:String , password:String):Boolean{
   var MyDB:SQLiteDatabase = this.writableDatabase
    var contaivalue:ContentValues= ContentValues()
    contaivalue.put("username",username)
    contaivalue.put("password",password)

    var result:Long =MyDB.insert("users",null,contaivalue)
    if(result .equals(-1) ) return false
  else
      return true

}
    fun checkUsername(username:String):Boolean{
      var MyDB=this.writableDatabase
        var curser:Cursor = MyDB.rawQuery("Select * from users where username = ?" , arrayOf<String>(username) )
    if(curser.count > 0){
        return true
    }else
       return false
    }
    fun checkusernamepassword(username:String,password:String):Boolean{
         var MyDB=this.writableDatabase
        var curser:Cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?" , arrayOf<String>(username , password) )
        if(curser.count >0){
            return  true
        }
        else
            return false
    }

fun upadte(n:Int):Boolean{
    var db=this.writableDatabase
    var cv=ContentValues()
    cv.put("login",n)
    db.update("users",cv,"login = ?", arrayOf<String>(n.toString()))
    return true
}


}

