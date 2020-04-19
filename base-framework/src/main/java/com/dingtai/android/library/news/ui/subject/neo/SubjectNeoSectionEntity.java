package com.dingtai.android.library.news.ui.subject.neo;

import com.chad.library.adapter.base.entity.SectionEntity;
import com.dingtai.android.library.news.model.NewsListModel;

import java.text.MessageFormat;

/**
 * author:lnr
 * date:2018/9/17
 */
public class SubjectNeoSectionEntity extends SectionEntity<NewsListModel> {

    private String id;
    private String title;

    public SubjectNeoSectionEntity(int position, int count, String id, String title) {
        super(true, MessageFormat.format("{0}/{1} {2}", position + 1, count, title));
        this.id = id;
        this.title = title;
    }

    public SubjectNeoSectionEntity(NewsListModel model) {
        super(model);
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
