# GreenDaoUpgradeHelper
一行代码搞定使用GreenDao开源框架的数据库升级

## 数据库升级处理
在DaoMaster类的onUpgrade方法添加如下代码即可，参数为所有的Dao类
```
	MigrationHelper.getInstance().migrate(db,TestDataDao.class,TestData2Dao.class，TestData3Dao.class);
```

##使用说明
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
	        compile 'com.github.yuweiguocn:GreenDaoUpgradeHelper:v0.0.2'
	}
```