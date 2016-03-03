package com.github.yuweiguocn.demo.greendao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.yuweiguocn.demo.greendao.db.DaoMaster;
import com.github.yuweiguocn.demo.greendao.db.MySQLiteOpenHelper;

public class MainActivity extends AppCompatActivity {

    private DaoMaster daoMaster;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MySQLiteOpenHelper helper = new MySQLiteOpenHelper(this, "test.db",
                null);
        daoMaster = new DaoMaster(helper.getWritableDatabase());

    }

}
