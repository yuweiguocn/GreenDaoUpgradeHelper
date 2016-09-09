package com.github.yuweiguocn.demo.greendao.about;


import com.github.yuweiguocn.demo.greendao.bean.About;

/**
 * Created by growth on 7/9/16.
 */
public class AboutPresenter implements AboutContract.Presenter{

    AboutContract.View view;

    public AboutPresenter(AboutContract.View view) {
        this.view = view;
    }

    @Override
    public void loadData() {
        About about = new About("weibo: <font color=\"#3F51B5\">@于卫国</font>","GitHub: <font color=\"#3F51B5\">http://github.com/yuweiguocn/</font>","Jianshu: <font color=\"#3F51B5\">http://www.jianshu.com/users/4cb0c84627c4</font>"
        ,"gmail: <font color=\"#3F51B5\">yuweiguocn@gmail.com</font>","blog: <font color=\"#3F51B5\">http://yuweiguocn.github.io/</font>");
        view.onSucess(about);
    }
}
