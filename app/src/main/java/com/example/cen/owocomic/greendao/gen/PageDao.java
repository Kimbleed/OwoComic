package com.example.cen.owocomic.greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.example.cen.owocomic.greendao.bean.Page;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "PAGE".
*/
public class PageDao extends AbstractDao<Page, Void> {

    public static final String TABLENAME = "PAGE";

    /**
     * Properties of entity Page.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Comic = new Property(0, String.class, "comic", false, "COMIC");
        public final static Property Chapter = new Property(1, String.class, "chapter", false, "CHAPTER");
        public final static Property Url = new Property(2, String.class, "url", false, "URL");
        public final static Property Position = new Property(3, int.class, "position", false, "POSITION");
    }


    public PageDao(DaoConfig config) {
        super(config);
    }
    
    public PageDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"PAGE\" (" + //
                "\"COMIC\" TEXT," + // 0: comic
                "\"CHAPTER\" TEXT," + // 1: chapter
                "\"URL\" TEXT UNIQUE ," + // 2: url
                "\"POSITION\" INTEGER NOT NULL );"); // 3: position
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PAGE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Page entity) {
        stmt.clearBindings();
 
        String comic = entity.getComic();
        if (comic != null) {
            stmt.bindString(1, comic);
        }
 
        String chapter = entity.getChapter();
        if (chapter != null) {
            stmt.bindString(2, chapter);
        }
 
        String url = entity.getUrl();
        if (url != null) {
            stmt.bindString(3, url);
        }
        stmt.bindLong(4, entity.getPosition());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Page entity) {
        stmt.clearBindings();
 
        String comic = entity.getComic();
        if (comic != null) {
            stmt.bindString(1, comic);
        }
 
        String chapter = entity.getChapter();
        if (chapter != null) {
            stmt.bindString(2, chapter);
        }
 
        String url = entity.getUrl();
        if (url != null) {
            stmt.bindString(3, url);
        }
        stmt.bindLong(4, entity.getPosition());
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public Page readEntity(Cursor cursor, int offset) {
        Page entity = new Page( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // comic
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // chapter
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // url
            cursor.getInt(offset + 3) // position
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Page entity, int offset) {
        entity.setComic(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setChapter(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setUrl(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setPosition(cursor.getInt(offset + 3));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(Page entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(Page entity) {
        return null;
    }

    @Override
    public boolean hasKey(Page entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
