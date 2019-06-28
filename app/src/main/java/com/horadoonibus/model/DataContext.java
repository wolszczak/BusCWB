package com.horadoonibus.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Wolszczak on 28/06/2019.
 */
public class DataContext extends OrmLiteSqliteOpenHelper {

    private static final String DB_NAME = "x.db";
    private static final int DB_VERSION = 1;

    public DataContext(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource cs) {
        try {
            TableUtils.dropTable(cs,Linha.class,true);
            TableUtils.dropTable(cs,TabelaLinha.class,true);

            TableUtils.createTable(cs, Linha.class);
            TableUtils.createTable(cs, TabelaLinha.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }

    public <T> List<T> getAll(Class<T> clazz) throws SQLException {
        Dao<T, ?> dao = getDao(clazz);
        return dao.queryForAll();
    }

    public <T> List<T> getAllOrdered(Class<T> clazz, String orderBy, Long limit, boolean ascending) throws SQLException {
        Dao<T, ?> dao = getDao(clazz);
        return dao.queryBuilder().orderBy(orderBy, ascending).limit(limit).query();
    }


    public <T> int insert(T entity) throws SQLException {
        Dao<T,?> dao =(Dao<T, ?>) getDao(entity.getClass());
        return dao.create(entity);
    }


}
