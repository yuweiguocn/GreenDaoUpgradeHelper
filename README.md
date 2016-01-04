# GreenDaoUpgradeHelper
一行代码搞定使用GreenDao开源框架的数据库升级

## 数据库升级处理
1.修改表结构
//版本1 只有TestData表，版本2修改了TestData表结构时的处理
```
	MigrationHelper.getInstance().migrate(db,TestDataDao.class);
```

2.新增表
//版本3新加表TestData2时的处理
```
	MigrationHelper.getInstance().migrate(db,TestDataDao.class,TestData2Dao.class);
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