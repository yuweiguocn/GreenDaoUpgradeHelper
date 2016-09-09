package com.github.yuweiguocn.demo.greendao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.github.yuweiguocn.demo.greendao.about.AboutActivity;
import com.github.yuweiguocn.demo.greendao.base.BaseActivity;
import com.github.yuweiguocn.demo.greendao.db.DaoMaster;
import com.github.yuweiguocn.demo.greendao.db.MySQLiteOpenHelper;
import com.github.yuweiguocn.library.greendao.MigrationHelper;

public class MainActivity extends BaseActivity {

    private DaoMaster daoMaster;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.include_toolbar);
        setSupportActionBar(toolbar);

        MigrationHelper.DEBUG = true;

        MySQLiteOpenHelper helper = new MySQLiteOpenHelper(this, "test.db",
                null);
        daoMaster = new DaoMaster(helper.getWritableDatabase());

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        startActivity(new Intent(this, AboutActivity.class));
        return true;
    }

}
