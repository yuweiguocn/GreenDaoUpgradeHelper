# GreenDaoUpgradeHelper([中文版](./README_CH.md))
GreenDaoUpgradeHelper is a database upgrade helper for greenDao.Use GreenDaoUpgradeHelper can easy solve database upgrade that's just need one line code.



##How use
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

3.Add the following code,arguments is all of generated Dao class:

```
public class DaoMaster extends AbstractDaoMaster {
	...
	public static class DevOpenHelper extends OpenHelper {
		...
		@Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			MigrationHelper.getInstance().migrate(db,TestDataDao.class,TestData2Dao.class，TestData3Dao.class);
		}
	}
	...
}

```


##Thanks
[greenDAO](https://github.com/greenrobot/greenDAO)


##Contact me
weibo:[@于卫国](http://weibo.com/weiguo58)

gmail:[yuweiguocn@gmail.com](mailto:yuweiguocn@gmail.com)

blog:[yuweiguo's blog](http://yuweiguocn.github.io)