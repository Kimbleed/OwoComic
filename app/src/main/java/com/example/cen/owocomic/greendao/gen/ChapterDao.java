package com.example.cen.owocomic.greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.example.cen.owocomic.greendao.bean.Chapter;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "CHAPTER".
*/
public class ChapterDao extends AbstractDao<Chapter, Void> {

    public static final String TABLENAME = "CHAPTER";

    /**
     * Properties of entity Chapter.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Comic = new Property(0, String.class, "comic", false, "COMIC");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property PageCount = new Property(2, int.class, "pageCount", false, "PAGE_COUNT");
    }


    public ChapterDao(DaoConfig config) {
        super(config);
    }
    
    public ChapterDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"CHAPTER\" (" + //
                "\"COMIC\" TEXT," + // 0: comic
                "\"NAME\" TEXT UNIQUE ," + // 1: name
                "\"PAGE_COUNT\" INTEGER NOT NULL );"); // 2: pageCount
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CHAPTER\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Chapter entity) {
        stmt.clearBindings();
 
        String comic = entity.getComic();
        if (comic != null) {
            stmt.bindString(1, comic);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
        stmt.bindLong(3, entity.getPageCount());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Chapter entity) {
        stmt.clearBindings();
 
        String comic = entity.getComic();
        if (comic != null) {
            stmt.bindString(1, comic);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
        stmt.bindLong(3, entity.getPageCount());
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public Chapter readEntity(Cursor cursor, int offset) {
        Chapter entity = new Chapter( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // comic
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // name
            cursor.getInt(offset + 2) // pageCount
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Chapter entity, int offset) {
        entity.setComic(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setPageCount(cursor.getInt(offset + 2));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(Chapter entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(Chapter entity) {
        return null;
    }

    @Override
    public boolean hasKey(Chapter entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
