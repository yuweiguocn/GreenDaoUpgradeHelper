package com.github.yuweiguocn.demo.greendao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.yuweiguocn.demo.greendao.db.DaoMaster;

public class MainActivity extends AppCompatActivity {

    private DaoMaster daoMaster;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "test",
                null);
    }

}
