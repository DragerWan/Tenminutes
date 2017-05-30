package com.czy.tenminutes.Listener;


import com.czy.tenminutes.Bean.ArticleTheme;

/**
 * Created by ZY on 2016/7/31.
 * 加载特定主题下文章列表事件监听
 */
public interface OnLoadThemeContentListener {

    void onSuccess(ArticleTheme articleTheme);

    void onFailure();
}
