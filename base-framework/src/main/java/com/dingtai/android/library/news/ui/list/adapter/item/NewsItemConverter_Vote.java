package com.dingtai.android.library.news.ui.list.adapter.item;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.dingtai.android.library.model.db.ModelStatusDao;
import com.dingtai.android.library.model.models.ModelStatus;

import com.dingtai.android.library.news.model.NewsListModel;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.common.image.load.GlideHelper;
import com.lnr.android.base.framework.data.asyn.core.AsynCallAdapter;
import com.lnr.android.base.framework.data.asyn.core.AsynCallAdapterProvider;
import com.lnr.android.base.framework.data.asyn.core.AsynCallAdapterType;
import com.lnr.android.base.framework.uitl.ContextUtil;
import com.lnr.android.base.framework.uitl.SpannableStringUtils;

import java.text.MessageFormat;

/**
 * author:lnr
 * date:2018/12/15
 */
public class NewsItemConverter_Vote extends AbstractNewsItemConverter {

    private AsynCallAdapter mAsynCallAdapter;

    private Drawable[] mVoteDrawables;

    public NewsItemConverter_Vote() {
        mAsynCallAdapter = AsynCallAdapterProvider.getInstance().get(AsynCallAdapterType.DATABASE);
        mVoteDrawables = new Drawable[4];
        mVoteDrawables[0] = ContextUtil.getResources().getDrawable(R.drawable.icon_vote_left);
        mVoteDrawables[1] = ContextUtil.getResources().getDrawable(R.drawable.icon_voted_left);
        mVoteDrawables[2] = ContextUtil.getResources().getDrawable(R.drawable.icon_vote_right);
        mVoteDrawables[3] = ContextUtil.getResources().getDrawable(R.drawable.icon_voted_right);

        mVoteDrawables[0].setBounds(0, 0,  mVoteDrawables[0].getIntrinsicWidth(),  mVoteDrawables[0].getIntrinsicHeight());
        mVoteDrawables[1].setBounds(0, 0,  mVoteDrawables[1].getIntrinsicWidth(),  mVoteDrawables[1].getIntrinsicHeight());
        mVoteDrawables[2].setBounds(0, 0,  mVoteDrawables[2].getIntrinsicWidth(),  mVoteDrawables[2].getIntrinsicHeight());
        mVoteDrawables[3].setBounds(0, 0,  mVoteDrawables[3].getIntrinsicWidth(),  mVoteDrawables[3].getIntrinsicHeight());
    }

    @Override
    protected int normalLayoutId() {
        return R.layout.item_news_list_vote;
    }

    @Override
    protected int noImageLayoutId() {
        return R.layout.item_news_list_vote;
    }

    @Override
    protected void normalConvert(BaseViewHolder baseViewHolder, int i, NewsListModel newsListModel) {
        GlideHelper.load(baseViewHolder.getView(R.id.item_image), newsListModel.getSmallPicUrl());

        baseViewHolder.setText(R.id.item_title, newsListModel.getTitle());
        baseViewHolder.setText(R.id.item_title2, newsListModel.getTitle());


        TextView voteLeft = baseViewHolder.getView(R.id.item_vote_left);
        TextView voteRight = baseViewHolder.getView(R.id.item_vote_right);

        voteLeft.setText(newsListModel.getVoteSubject1Name());
        voteRight.setText(newsListModel.getVoteSubject2Name());

        baseViewHolder.setText(R.id.item_vote_left_count,
                MessageFormat.format("{0}({1}%)", newsListModel.getVoteSubject1(), newsListModel.getVoteSubject1Percent()));
        baseViewHolder.setText(R.id.item_vote_right_count,
                MessageFormat.format("{0}({1}%)", newsListModel.getVoteSubject2(), newsListModel.getVoteSubject2Percent()));

        baseViewHolder.setText(R.id.item_vote_count, SpannableStringUtils.getBuilder("已有")
                .append(newsListModel.getVoteNum())
                .setForegroundColor(Color.BLACK)
                .append("人参与")
                .create());

        ModelStatusDao dao = mAsynCallAdapter.call(ModelStatusDao.class, true);
        if (dao == null) {
            voteLeft.setTextColor(ContextUtil.getColor(R.color.textcolor_Body1));
            voteRight.setTextColor(ContextUtil.getColor(R.color.textcolor_Body1));

            voteLeft.setCompoundDrawables(mVoteDrawables[0], null, null, null);
            voteRight.setCompoundDrawables(null, null, mVoteDrawables[2], null);
        }else {
            ModelStatus status = dao.queryBuilder().where(ModelStatusDao.Properties.Key.eq("vote_" + newsListModel.getResourceGUID())).unique();
            if(status != null && status.getStatus() != 0) {
                voteLeft.setTextColor(status.getStatus() < 0 ? Color.BLUE : ContextUtil.getColor(R.color.textcolor_Body1));
                voteRight.setTextColor(status.getStatus() > 0 ? Color.BLUE : ContextUtil.getColor(R.color.textcolor_Body1));

                voteLeft.setCompoundDrawables(status.getStatus() < 0 ? mVoteDrawables[1] : mVoteDrawables[0], null, null, null);
                voteRight.setCompoundDrawables(null, null, status.getStatus() > 0 ? mVoteDrawables[3] : mVoteDrawables[2], null);
            }else {
                voteLeft.setTextColor(ContextUtil.getColor(R.color.textcolor_Body1));
                voteRight.setTextColor(ContextUtil.getColor(R.color.textcolor_Body1));

                voteLeft.setCompoundDrawables(mVoteDrawables[0], null, null, null);
                voteRight.setCompoundDrawables(null, null, mVoteDrawables[2], null);
            }
        }

        baseViewHolder.addOnClickListener(R.id.item_vote_left);
        baseViewHolder.addOnClickListener(R.id.item_vote_right);
    }

    @Override
    protected void noImageConvert(BaseViewHolder baseViewHolder, int i, NewsListModel newsListModel) {
        normalConvert(baseViewHolder, i, newsListModel);
    }
}
