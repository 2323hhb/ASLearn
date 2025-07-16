package com.apex.databasetest;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.apex.databasetest.Helper.MyDataBaseHelper;

public class DatabaseProvider extends ContentProvider {
    public static final int BOOK_DIR = 0;
    public static final int BOOK_ITEM = 1;
    public static final int CATEGORY_DIR = 2;
    public static final int CATEGORY_ITEM = 3;

    public static final String AUTHORITY = "com.apex.databasetest.provider";
    private static UriMatcher uriMatcher;
    private MyDataBaseHelper dbHelper;

    static {
        //获得UriMatcher实例
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY,"book",BOOK_DIR);
        uriMatcher.addURI(AUTHORITY,"book/#",BOOK_ITEM);
        uriMatcher.addURI(AUTHORITY,"category",CATEGORY_DIR);
        uriMatcher.addURI(AUTHORITY,"category/#",CATEGORY_ITEM);
    }
    public DatabaseProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int deleteRows = 0; //影响的行数

        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                deleteRows = db.delete("Book", selection, selectionArgs);
                break;
            case BOOK_ITEM:
                String bookId =  uri.getPathSegments().get(1);
                deleteRows = db.delete("Book", "id=?", new String[]{ bookId});
                break;
            case CATEGORY_DIR:
                deleteRows = db.delete("Category", selection, selectionArgs);
                break;
            case CATEGORY_ITEM:
                String categoryId =  uri.getPathSegments().get(1);
                deleteRows = db.delete("Category", "id=?", new String[]{ categoryId});
                break;
        }
        return  deleteRows;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                return "vnd.android.cursor.dir/vnd.com.apex.databasetest.provider.book";
            case BOOK_ITEM:
                return "vnd.android.cursor.item/vnd.com.apex.databasetest.provider.book";
            case CATEGORY_DIR:
                return "vnd.android.cursor.dir/vnd.com.apex.databasetest.provider.category";
            case CATEGORY_ITEM:
                return "vnd.android.cursor.item/vnd.com.apex.databasetest.provider.category";
        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Uri retunUrl = null;

        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
            case BOOK_ITEM:
                long newbookId = db.insert("Book", null, values);
                retunUrl = Uri.parse("content://" + AUTHORITY + "/book/" + newbookId);
                break;

            case CATEGORY_DIR:
            case CATEGORY_ITEM:
                long newcategoryId = db.insert("Category", null, values);
                retunUrl = Uri.parse("content://" + AUTHORITY + "/category/" + newcategoryId);
                break;
        }

        return  retunUrl;
    }

    @Override
    public boolean onCreate() {
        //获取 SQLiteOpenHelper 实例，以便操作数据库
        dbHelper = new MyDataBaseHelper(getContext(), "BookStore.db", null, 2);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();//获取数据库实例
        Cursor cursor = null;
        //match()方法，返回对应的自定义代码
        switch(uriMatcher.match(uri)){
            case BOOK_DIR:
                cursor = db.query("Book",projection,selection,selectionArgs,null,null,sortOrder);
                break;

            case BOOK_ITEM:
                //getPathSegments()，将内容URI 权限之后的部分以"/"符号进行分割，得到字符串列表
                String bookID = uri.getPathSegments().get(1);
                cursor = db.query("Book",projection,"id = ?",new String[]{bookID},null,null,sortOrder);
                break;

            case CATEGORY_DIR:
                cursor = db.query("Category",projection,selection,selectionArgs,null,null,sortOrder);
                break;

            case CATEGORY_ITEM:
                String categoryID = uri.getPathSegments().get(1);
                cursor = db.query("Category",projection,"id = ?",new String[]{categoryID},null,null,sortOrder);
        }
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int updateRows = 0; //影响的行数

        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                updateRows = db.update("Book", values, selection, selectionArgs);
                break;
            case BOOK_ITEM:
                String bookId =  uri.getPathSegments().get(1);
                updateRows = db.update("Book", values, "id=?", new String[]{ bookId});
                break;
            case CATEGORY_DIR:
                updateRows = db.update("Category", values, selection, selectionArgs);
                break;
            case CATEGORY_ITEM:
                String categoryId =  uri.getPathSegments().get(1);
                updateRows = db.update("Category", values, "id=?", new String[]{ categoryId});
                break;
        }
        return  updateRows;
    }
}