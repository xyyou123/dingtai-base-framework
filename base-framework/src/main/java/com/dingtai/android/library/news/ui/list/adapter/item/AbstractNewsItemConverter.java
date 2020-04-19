package com.dingtai.android.library.news.ui.list.adapter.item;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;

import com.dingtai.android.library.news.model.NewsListModel;
import com.dingtai.android.library.news.ui.list.adapter.convertor.NewsBadgeConvertor;
import com.dingtai.android.library.news.ui.list.adapter.convertor.NewsItemConvertor;
import com.dingtai.android.library.resource.Resource;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.common.image.load.GlideHelper;
import com.lnr.android.base.framework.ui.control.view.recyclerview.ItemConverter;
import com.lnr.android.base.framework.uitl.ContextUtil;
import com.lnr.android.base.framework.uitl.NumberUtil;
import com.lnr.android.base.framework.uitl.SP;
import com.lnr.android.base.framework.uitl.date.DateUtil;
import com.lnr.android.base.framework.uitl.net.NetworkUtil;

import static com.dingtai.android.library.news.ui.list.adapter.convertor.NewsItemConvertor.showReadCount_Number;

/**
 * author:lnr
 * date:2018/10/8
 */
public abstract class AbstractNewsItemConverter implements ItemConverter<NewsListModel> {
    /**
     * 判断是否显示flag 的框
     *
     *
     */

    public static  boolean  SHOW_FLAG_BG = true;

    @Override
    public int layoutId() {
        int model = SP.getDefault().getInt(Resource.KEY.SETTING_READ_PATTERN, Resource.VALUE.SETTING_READ_PATTERN_AUTO);
        switch (model) {
            case Resource.VALUE.SETTING_READ_PATTERN_AUTO:
                NetworkUtil.NetworkType networkType = NetworkUtil.getNetworkType();
                if (networkType == NetworkUtil.NetworkType.NETWORK_4G || networkType == NetworkUtil.NetworkType.NETWORK_WIFI) {
                    return normalLayoutId();
                } else {
                    return noImageLayoutId();
                }
            case Resource.VALUE.SETTING_READ_PATTERN_IMAGE_TEXT:
                return normalLayoutId();
            case Resource.VALUE.SETTING_READ_PATTERN_TEXT:
                return noImageLayoutId();
        }

        return normalLayoutId();
    }

    @Override
    public void convert(BaseViewHolder holder, int position, NewsListModel model) {
        int type = SP.getDefault().getInt(Resource.KEY.SETTING_READ_PATTERN, Resource.VALUE.SETTING_READ_PATTERN_AUTO);
        switch (type) {
            case Resource.VALUE.SETTING_READ_PATTERN_AUTO:
                NetworkUtil.NetworkType networkType = NetworkUtil.getNetworkType();
                if (networkType == NetworkUtil.NetworkType.NETWORK_4G || networkType == NetworkUtil.NetworkType.NETWORK_WIFI) {
                    normalConvert(holder, position, model);
                } else {
                    noImageConvert(holder, position, model);
                }
                break;
            case Resource.VALUE.SETTING_READ_PATTERN_IMAGE_TEXT:
                normalConvert(holder, position, model);
                break;
            case Resource.VALUE.SETTING_READ_PATTERN_TEXT:
                noImageConvert(holder, position, model);
                break;
        }
        convertCommon(holder, model);
    }

    /**
     * 图文
     *
     * @return
     */
    protected abstract int normalLayoutId();

    /**
     * 无图
     *
     * @return
     */
    protected abstract int noImageLayoutId();

    /**
     * 图文
     */
    protected abstract void normalConvert(BaseViewHolder holder, int position, NewsListModel model);

    /**
     * 无图
     */
    protected abstract void noImageConvert(BaseViewHolder holder, int position, NewsListModel model);

    /**
     * 公共部分
     */
    protected void convertCommon(BaseViewHolder holder, NewsListModel model) {
        holder.setText(R.id.item_title, model.getTitle());
        holder.getView(R.id.item_title).setSelected(model.getIsRead());

        if(holder.getView(R.id.item_class) != null) {
            if(NewsItemConvertor.showChannelName) {
                if(NewsItemConvertor.userResourcePdForm && !TextUtils.isEmpty(model.getResourcePdForm())) {
                    holder.setGone(R.id.item_class, true);
                    holder.setText(R.id.item_class, model.getResourcePdForm());
                }else if(!NewsItemConvertor.userResourcePdForm && !TextUtils.isEmpty(model.getChannelName())) {
                    holder.setGone(R.id.item_class, true);
                    holder.setText(R.id.item_class, model.getChannelName());
                }else {
                    holder.setGone(R.id.item_class, false);
                }
            }else {
                holder.setGone(R.id.item_class, false);
            }
        }

        TextView textView = holder.getView(R.id.item_type);
        if(textView != null) {
            NewsBadgeConvertor.Badge badge;
            if(!NewsItemConvertor.showBadge || (badge = NewsBadgeConvertor.converterBadge(model.getResourceFlag())) == null) {
                textView.setVisibility(View.GONE);
            }else {
                textView.setText(badge.title);
                textView.setVisibility(View.VISIBLE);
                textView.setTextColor(badge.textColor);
                if (SHOW_FLAG_BG) {
                    textView.setBackground(ContextUtil.createShape(4, badge.background));
                }

            }
        }

        if(holder.getView(R.id.item_count_read) != null) {
            if(!NewsItemConvertor.showReadCount || TextUtils.isEmpty(model.getReadNo())) {
                holder.setGone(R.id.item_count_read, false);
            }else {

                int num = NumberUtil.parseInt(NewsItemConvertor.userFakeReadCount ? model.getFakeReadNo() : model.getReadNo());
                String countStr = num + "";
                if (!showReadCount_Number){
                    if(num > 10000) {
                        countStr = num / 10000 + "." + (num % 10000) / 1000 + "万";
                    }else if(num > 1000) {
                        countStr = num / 1000 + "." + (num % 1000) / 100 + "千";
                    }
                }

                holder.setGone(R.id.item_count_read, true)
                        .setText(R.id.item_count_read, countStr);
            }
        }

        if(holder.getView(R.id.item_time) != null) {
            if(!NewsItemConvertor.showTime) {
                holder.setGone(R.id.item_time, false);
            }else {
                if (NewsItemConvertor.showGhChangeTime){
                    holder.setGone(R.id.item_time, true);
                    holder.setText(R.id.item_time, model.getResUnitName());
                }else {
                    holder.setGone(R.id.item_time, true);
                    holder.setText(R.id.item_time, DateUtil.formatFromString(model.getCreateTime()));
                }
            }
        }
    }

    protected void loadImage(View view, Object src) {
        if (NewsItemConvertor.showCenterCropConner) {
            if (NewsItemConvertor.ROUND_IMAGE <= 0) {
                GlideHelper.load(view, src);
            } else {
                GlideHelper.loadCenterCropCircle(view, src, NewsItemConvertor.ROUND_IMAGE);
            }
        } else {
            if (NewsItemConvertor.ROUND_IMAGE <= 0) {
                GlideHelper.load(view, src);
            } else {
                GlideHelper.loadRound(view, src, NewsItemConvertor.ROUND_IMAGE);
            }
        }
    }
}
