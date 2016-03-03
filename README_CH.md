# GreenDaoUpgradeHelper
GreenDaoUpgradeHelper是一个greenDao的数据库升级帮助类。使用它可以很容易解决数据库升级问题，只需一行代码。

## 使用说明

1.在根目录的build.gradle文件的repositories内添加如下代码：
```
	allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
```

2.添加依赖
```
	dependencies {
	        compile 'com.github.yuweiguocn:GreenDaoUpgradeHelper:v0.0.5'
	}
```

3.添加一个新类继承**DaoMaster.OpenHelper**，添加构造函数并实现**onUpgrade**方法,在onUpgrade方法添加如下代码即可，参数为所有的Dao类：  
```
	MigrationHelper.getInstance().migrate(db,TestDataDao.class,TestData2Dao.class，TestData3Dao.class);
```
完整代码：  
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

4.初始化

```
MySQLiteOpenHelper helper = new MySQLiteOpenHelper(this, "test.db",
                null);
        daoMaster = new DaoMaster(helper.getWritableDatabase());
```


##感谢
[greenDAO](https://github.com/greenrobot/greenDAO)


##联系方式
weibo:[@于卫国](http://weibo.com/weiguo58)

gmail:[yuweiguocn@gmail.com](mailto:yuweiguocn@gmail.com)
