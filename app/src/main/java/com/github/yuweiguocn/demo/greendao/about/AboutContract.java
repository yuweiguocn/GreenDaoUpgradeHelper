package com.github.yuweiguocn.demo.greendao.about;


import com.github.yuweiguocn.demo.greendao.base.BasePresenter;
import com.github.yuweiguocn.demo.greendao.base.BaseView;
import com.github.yuweiguocn.demo.greendao.bean.About;

/**
 * Created by growth on 7/9/16.
 */
public interface AboutContract {
    interface Presenter extends BasePresenter {
        void loadData();
    }

    interface View extends BaseView<Presenter> {
        void onSucess(About about);
    }
}
