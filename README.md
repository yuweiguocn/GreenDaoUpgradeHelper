# GreenDaoUpgradeHelper([中文版](./README_CH.md))
GreenDaoUpgradeHelper is a database upgrade helper for greenDao.Use GreenDaoUpgradeHelper can easy solve database upgrade that's just need one line code.



##How to use
1.Add it in your root build.gradle at the end of repositories:
```
	allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
```

2.Add the dependency
```
	dependencies {
	        compile 'com.github.yuweiguocn:GreenDaoUpgradeHelper:v0.0.5'
	}
```

3.Add a new class that extends **DaoMaster.OpenHelper**,add a constructor,implement **onUpgrade** method and called **migrate** method,arguments is all of generated Dao class:

```
MigrationHelper.getInstance().migrate(db,TestDataDao.class,TestData2Dao.class,TestData3Dao.class);
```


like this:  
```
public class MySQLiteOpenHelper extends DaoMaster.OpenHelper {
    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        MigrationHelper.getInstance().migrate(db,TestDataDao.class,TestData2Dao.class,TestData3Dao.class);
    }
}

```  

4.Finally,init like this:

```
MySQLiteOpenHelper helper = new MySQLiteOpenHelper(this, "test.db",
                null);
        daoMaster = new DaoMaster(helper.getWritableDatabase());
```


##Thanks
[greenDAO](https://github.com/greenrobot/greenDAO)


##Contact me
weibo:[@于卫国](http://weibo.com/weiguo58)

gmail:[yuweiguocn@gmail.com](mailto:yuweiguocn@gmail.com)

blog:[yuweiguo's blog](http://yuweiguocn.github.io)
