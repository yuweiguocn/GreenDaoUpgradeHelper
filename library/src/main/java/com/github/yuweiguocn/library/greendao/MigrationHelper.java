package com.github.yuweiguocn.library.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

/**
 * 
 * please call {@link #migrate(SQLiteDatabase, Class[])}
 * 
 * @author lixi
 * @description <ForgetThisProject>
 * @date 16/4/15
 */
public final class MigrationHelper {

    /**
     *  key: javaType
     *  value: dbType
     */
    private static final Map<Class, String> propertyToDbType = new ArrayMap<>(10);

     /**
     * propertyToDbType = new HashMap<PropertyType, String>();
     * propertyToDbType.put(PropertyType.Boolean, "INTEGER");
     * propertyToDbType.put(PropertyType.Byte, "INTEGER");
     * propertyToDbType.put(PropertyType.Short, "INTEGER");
     * propertyToDbType.put(PropertyType.Int, "INTEGER");
     * propertyToDbType.put(PropertyType.Long, "INTEGER");
     * propertyToDbType.put(PropertyType.Float, "REAL");
     * propertyToDbType.put(PropertyType.Double, "REAL");
     * propertyToDbType.put(PropertyType.String, "TEXT");
     * propertyToDbType.put(PropertyType.ByteArray, "BLOB");
     * propertyToDbType.put(PropertyType.Date, "INTEGER");
     * <p/>
     * <p/>
     * propertyToJavaTypeNullable.put(PropertyType.Boolean, "Boolean");
     * propertyToJavaTypeNullable.put(PropertyType.Byte, "Byte");
     * propertyToJavaTypeNullable.put(PropertyType.Short, "Short");
     * propertyToJavaTypeNullable.put(PropertyType.Int, "Integer");
     * propertyToJavaTypeNullable.put(PropertyType.Long, "Long");
     * propertyToJavaTypeNullable.put(PropertyType.Float, "Float");
     * propertyToJavaTypeNullable.put(PropertyType.Double, "Double");
     * propertyToJavaTypeNullable.put(PropertyType.String, "String");
     * propertyToJavaTypeNullable.put(PropertyType.ByteArray, "byte[]");
     * propertyToJavaTypeNullable.put(PropertyType.Date, "java.util.Date");
     */
    static {
        propertyToDbType.put(Boolean.class, "INTEGER");
        propertyToDbType.put(Byte.class, "INTEGER");
        propertyToDbType.put(Short.class, "INTEGER");
        propertyToDbType.put(Integer.class, "INTEGER");
        propertyToDbType.put(Long.class, "INTEGER");
        propertyToDbType.put(Float.class, "REAL");
        propertyToDbType.put(Double.class, "REAL");
        propertyToDbType.put(String.class, "TEXT");
        propertyToDbType.put(Byte[].class, "BLOB");
        propertyToDbType.put(Date.class, "INTEGER");
    }


    public static void migrate(SQLiteDatabase db, Class<? extends AbstractDao<?, ?>>... daoClasses) {
        generateTempTables(db, daoClasses);
        dropAllTables(db, true, daoClasses);
        createAllTables(db, false, daoClasses);
        restoreData(db, daoClasses);
    }


    private static void generateTempTables(SQLiteDatabase db, Class<? extends AbstractDao<?, ?>>... daoClasses) {
        for (int i = 0; i < daoClasses.length; i++) {
            DaoConfig daoConfig = new DaoConfig(db, daoClasses[i]);

            String tableName = daoConfig.tablename;
            String tempTableName = daoConfig.tablename.concat("_TEMP");

            // take all columns from exists table
            List<String> columns = getColumns(db, tableName);

            // create temp table
            StringBuilder createTableStringBuilder = new StringBuilder();
            createTableStringBuilder.append("CREATE TEMPORARY TABLE ").append(tempTableName).append(" (");

            ArrayList<String> properties = new ArrayList<>();
            boolean isFirstTime = true;
            for (int j = 0; j < daoConfig.properties.length; j++) {
                Property property = daoConfig.properties[j];

                if (columns.contains(property.columnName)) {
                    properties.add(property.columnName);
                    if (isFirstTime) {
                        isFirstTime = false;
                    } else {
                        createTableStringBuilder.append(",");
                    }
                    createTableStringBuilder.append(property.columnName).append(" ");
                    createTableStringBuilder.append(getTypeByClass(property.type));
                    if (property.primaryKey) {
                        createTableStringBuilder.append(" PRIMARY KEY");
                    }
                }
            }
            if (properties.isEmpty()) {
                // the new version are totally different
                continue;
            }
            createTableStringBuilder.append(");");
            db.execSQL(createTableStringBuilder.toString());

            // copy data from origin table 
            StringBuilder insertTableStringBuilder = new StringBuilder();
            insertTableStringBuilder.append(" INSERT INTO ").append(tempTableName);
            insertTableStringBuilder.append(" SELECT * FROM ").append(tableName).append(";");
            db.execSQL(insertTableStringBuilder.toString());
        }
    }


    private static void dropAllTables(SQLiteDatabase db, boolean ifExists, @NonNull Class<? extends AbstractDao<?, ?>>... daoClasses) {
        reflectMethod(db, "dropTable", ifExists, daoClasses);
    }

    private static void createAllTables(SQLiteDatabase db, boolean ifNotExists, @NonNull Class<? extends AbstractDao<?, ?>>... daoClasses) {
        reflectMethod(db, "createTable", ifNotExists, daoClasses);
    }

    /**
     * dao class already define the sql exec method, so just invoke it
     */
    private static void reflectMethod(SQLiteDatabase db, String methodName, boolean isExists, @NonNull Class<? extends AbstractDao<?, ?>>... daoClasses) {
        if (daoClasses.length < 1) {
            return;
        }
        try {
            for (Class cls : daoClasses) {
                Method method = cls.getDeclaredMethod(methodName, SQLiteDatabase.class, boolean.class);
                method.invoke(null, db, isExists);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static void restoreData(SQLiteDatabase db, Class<? extends AbstractDao<?, ?>>... daoClasses) {
        for (int i = 0; i < daoClasses.length; i++) {
            DaoConfig daoConfig = new DaoConfig(db, daoClasses[i]);
            String tableName = daoConfig.tablename;
            String tempTableName = daoConfig.tablename.concat("_TEMP");
            // get all columns from tempTable, take careful to use the columns list
            List<String> columns = getColumns(db, tempTableName);
            ArrayList<String> properties = new ArrayList<>(columns.size());
            for (int j = 0; j < daoConfig.properties.length; j++) {
                String columnName = daoConfig.properties[j].columnName;
                if (columns.contains(columnName)) {
                    properties.add(columnName);
                }
            }
            if (properties.size() > 0) {
                final String columnSQL = TextUtils.join(",", properties);

                StringBuilder insertTableStringBuilder = new StringBuilder();
                insertTableStringBuilder.append("INSERT INTO ").append(tableName).append(" (");
                insertTableStringBuilder.append(columnSQL);
                insertTableStringBuilder.append(") SELECT ");
                insertTableStringBuilder.append(columnSQL);
                insertTableStringBuilder.append(" FROM ").append(tempTableName).append(";");
                db.execSQL(insertTableStringBuilder.toString());
            }
            StringBuilder dropTableStringBuilder = new StringBuilder();
            dropTableStringBuilder.append("DROP TABLE ").append(tempTableName);
            db.execSQL(dropTableStringBuilder.toString());
        }
    }


    /**
     * @param type javaType
     * @return dbType
     */
    private static String getTypeByClass(Class<?> type) {
        return propertyToDbType.get(type);
    }


    private static List<String> getColumns(SQLiteDatabase db, String tableName) {
        List<String> columns = null;
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT * FROM " + tableName + " limit 0", null);
            if (null != cursor && cursor.getColumnCount() > 0) {
                columns = Arrays.asList(cursor.getColumnNames());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
            if (null == columns)
                columns = new ArrayList<>();
        }
        return columns;
    }


}
