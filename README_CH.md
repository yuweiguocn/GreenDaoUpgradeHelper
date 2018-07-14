# GreenDaoUpgradeHelper

[![Release](https://jitpack.io/v/yuweiguocn/GreenDaoUpgradeHelper.svg)](https://jitpack.io/#yuweiguocn/GreenDaoUpgradeHelper)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-GreenDaoUpgradeHelper-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/4853)
[ ![Download](https://api.bintray.com/packages/yuweiguocn/maven/GreenDaoUpgradeHelper/images/download.svg) ](https://bintray.com/yuweiguocn/maven/GreenDaoUpgradeHelper/_latestVersion)

GreenDaoUpgradeHelper是一个greenDao的数据库升级帮助类。使用它可以很容易解决数据库升级问题，只需一行代码。

原始代码来自[stackoverflow](http://stackoverflow.com/a/30334668/7161403)。

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

2.添加依赖（greendao 3.0及以上）
```
	dependencies {
	        compile 'org.greenrobot:greendao:3.2.0'
	        compile 'com.github.yuweiguocn:GreenDaoUpgradeHelper:v2.1.0'
	}
```
如果你使用的greendao是3.0以前的版本，请使用下面的依赖：
```
	dependencies {
	        compile 'de.greenrobot:greendao:2.0.0'
	        compile 'com.github.yuweiguocn:GreenDaoUpgradeHelper:v1.0.1'
	}
```

3.添加一个新类继承**DaoMaster.OpenHelper**，添加构造函数并实现**onUpgrade**方法,在onUpgrade方法添加如下代码即可，参数为所有的Dao类：  
```
MigrationHelper.migrate(db, new MigrationHelper.ReCreateAllTableListener() {
            
            @Override
            public void onCreateAllTables(Database db, boolean ifNotExists) {
                DaoMaster.createAllTables(db, ifNotExists);
            }
            
            @Override
            public void onDropAllTables(Database db, boolean ifExists) {
                DaoMaster.dropAllTables(db, ifExists);
            }
        },TestDataDao.class, TestData2Dao.class, TestData3Dao.class);
```
完整代码：  
```
public class MySQLiteOpenHelper extends DaoMaster.OpenHelper {
    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }
    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        MigrationHelper.migrate(db, new MigrationHelper.ReCreateAllTableListener() {
                    
                    @Override
                    public void onCreateAllTables(Database db, boolean ifNotExists) {
                        DaoMaster.createAllTables(db, ifNotExists);
                    }
                    
                    @Override
                    public void onDropAllTables(Database db, boolean ifExists) {
                        DaoMaster.dropAllTables(db, ifExists);
                    }
                },TestDataDao.class, TestData2Dao.class, TestData3Dao.class);
    }
}

```

4.初始化

```
//MigrationHelper.DEBUG = true; //如果你想查看日志信息，请将DEBUG设置为true
MySQLiteOpenHelper helper = new MySQLiteOpenHelper(this, "test.db",
                null);
        daoMaster = new DaoMaster(helper.getWritableDatabase());
```

### ProGuard混淆规则

```java
-keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
    public static void dropTable(org.greenrobot.greendao.database.Database, boolean);
    public static void createTable(org.greenrobot.greendao.database.Database, boolean);
}
```

## 感谢
[greenDAO](https://github.com/greenrobot/greenDAO)

[Pedro Okawa](http://stackoverflow.com/users/2200209/pedro-okawa)

## 联系方式
weibo:[@于卫国](http://weibo.com/weiguo58)

gmail:[yuweiguocn@gmail.com](mailto:yuweiguocn@gmail.com)


## License 
```  
Copyright 2016 yuweiguocn

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
