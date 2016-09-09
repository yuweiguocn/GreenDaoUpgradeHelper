package com.github.yuweiguocn.demo.greendao.about;

import android.content.Intent;
import android.net.Uri;
import android.view.View;

/**
 * Created by growth on 7/9/16.
 */
public class AboutClickHandlers {

    public void onClickWeibo(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://weibo.com/weiguo58"));
        if (intent.resolveActivity(v.getContext().getPackageManager()) != null) {
            v.getContext().startActivity(intent);
        }
    }

    public void onClickGitHub(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://github.com/yuweiguocn/"));
        if (intent.resolveActivity(v.getContext().getPackageManager()) != null) {
            v.getContext().startActivity(intent);
        }
    }

    public void onClickBlog(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://yuweiguocn.github.io/"));
        if (intent.resolveActivity(v.getContext().getPackageManager()) != null) {
            v.getContext().startActivity(intent);
        }
    }

    public void onClickJianshu(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://www.jianshu.com/users/4cb0c84627c4"));
        if (intent.resolveActivity(v.getContext().getPackageManager()) != null) {
            v.getContext().startActivity(intent);
        }
    }



}
