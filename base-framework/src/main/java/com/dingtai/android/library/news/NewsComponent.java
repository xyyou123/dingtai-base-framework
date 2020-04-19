package com.dingtai.android.library.news;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dingtai.android.library.database.DBDaoSessionGenerateManager;
import com.dingtai.android.library.database.DBTableMasterManager;
import com.dingtai.android.library.model.models.ADModel;
import com.dingtai.android.library.news.db.impl.ModelDBDaoSessionGenerateImpl;
import com.dingtai.android.library.news.db.impl.ModelDBTableImpl;
import com.dingtai.android.library.news.model.NewsListModel;
import com.dingtai.android.library.news.ui.NewsNavigation;
import com.dingtai.android.library.news.ui.list.adapter.NewsListAdapter;
import com.dingtai.android.library.news.ui.list.adapter.convertor.NewsBadgeConvertor;
import com.dingtai.android.library.resource.DataProviders;
import com.lnr.android.base.framework.AbstractComponent;
import com.lnr.android.base.framework.data.provider.DataProviderManager;
import com.lnr.android.base.framework.data.provider.SimpleDataProvider;
import com.lnr.android.base.framework.uitl.JsonUtil;

import java.util.HashMap;
import java.util.List;

/**
 * author :  lnr
 * date : 2018/8/18.
 */

public class NewsComponent extends AbstractComponent {

    @Override
    protected void initDatabase() {
        DBTableMasterManager.getInstance().add(new ModelDBTableImpl());
        DBDaoSessionGenerateManager.getInstance().add(new ModelDBDaoSessionGenerateImpl());
    }

    @Override
    protected void initInfo() {
        DataProviderManager.getInstance().registe(DataProviders.News.NEWS_MODEL, new SimpleDataProvider(){
            @Override
            public HashMap<String, Object> get(HashMap<String, Object> params) {
                List<NewsListModel> models = JsonUtil.parseArray((String) params.get("data"), NewsListModel.class);
                params.put("result", models);
                return params;
            }
        });

        DataProviderManager.getInstance().registe(DataProviders.News.NEWS_LIST_ADAPTER, new SimpleDataProvider(){
            @Override
            public HashMap<String, Object> get(HashMap<String, Object> params) {
                List<NewsListModel> models = JsonUtil.parseArray((String) params.get("data"), NewsListModel.class);
                NewsListAdapter adapter = new NewsListAdapter();
                adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        NewsListModel model = (NewsListModel) adapter.getItem(position);
                        if(model == null) return;
                        NewsNavigation.listItemNavigation(model);
                    }
                });
                adapter.setNewData(models);
                params.put("adapter", adapter);
                return params;
            }
        });


        DataProviderManager.getInstance().registe(DataProviders.News.NEWS_LIST_ADAPTER_BARGE, new SimpleDataProvider(){
            @Override
            public HashMap<String, Object> get(HashMap<String, Object> params) {
                NewsBadgeConvertor.Badge badge = NewsBadgeConvertor.converterBadge((String) params.get("flag"));
                if(badge == null) {
                    return null;
                }
                params.put("badge_title", badge.title);
                params.put("badge_textColor", badge.textColor);
                params.put("badge_background", badge.background);
                return params;
            }
        });

        DataProviderManager.getInstance().registe(DataProviders.News.NEWS_LIST_ADAPTER_CLICK, new SimpleDataProvider(){
            @Override
            public void put(HashMap<String, Object> params) {
                if(params == null) return;
                NewsListModel model = new NewsListModel();
                model.setResourceGUID((String) params.get("ResourceGUID"));
                model.setResourceFlag((String) params.get("ResourceFlag"));
                model.setResourceCSS((String) params.get("ResourceCSS"));
                model.setResourceType((String) params.get("ResourceType"));
                model.setResourceUrl((String) params.get("ResourceUrl"));
                model.setTitle((String) params.get("Title"));
                model.setChID((String) params.get("ChID"));
                model.setRPID((String) params.get("RPID"));
                model.setIsNewTopice((String) params.get("IsNewTopice"));
                NewsNavigation.listItemNavigation(model);
            }
        });

        DataProviderManager.getInstance().registe(DataProviders.News.AD_LIST_ADAPTER_CLICK, new SimpleDataProvider(){
            @Override
            public void put(HashMap<String, Object> params) {
                if(params == null) return;
                NewsNavigation.adNavigation((ADModel) params.get("ad"));
            }
        });
    }
}
