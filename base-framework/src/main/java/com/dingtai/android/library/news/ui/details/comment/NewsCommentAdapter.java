package com.dingtai.android.library.news.ui.details.comment;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;

import com.dingtai.android.library.news.model.NewsCommentModel;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.common.image.load.GlideHelper;
import com.lnr.android.base.framework.ui.control.listener.OnClickListener;
import com.lnr.android.base.framework.ui.control.listener.ViewListen;
import com.lnr.android.base.framework.ui.control.view.adapterview.BaseAdapterViewAdapter;
import com.lnr.android.base.framework.ui.control.view.adapterview.FixListView;
import com.lnr.android.base.framework.ui.control.view.recyclerview.BaseAdapter;
import com.lnr.android.base.framework.ui.control.view.recyclerview.ItemConverter;
import com.lnr.android.base.framework.uitl.NumberUtil;
import com.lnr.android.base.framework.uitl.SpannableStringUtils;
import com.lnr.android.base.framework.uitl.StringUtil;

import java.util.List;

/**
 * author:lnr
 * date:2018/9/26
 */
public class NewsCommentAdapter extends BaseAdapter<NewsCommentModel> {

    private OnSubChildZanClickListener mOnSubChildZanClickListener;
    public static boolean USER_SubComment2Adapter = false;//新株洲改了
    public NewsCommentAdapter(OnSubChildZanClickListener listener) {
        this.mOnSubChildZanClickListener = listener;
    }

    @Override
    protected ItemConverter<NewsCommentModel> createItemConverter(int viewType) {
        return new ItemConverter<NewsCommentModel>() {
            @Override
            public int layoutId() {
                return R.layout.item_news_comment;
            }

            @Override
            public void convert(BaseViewHolder holder, int position, NewsCommentModel model) {
                GlideHelper.loadCircle(holder.getView(R.id.item_icon), model.getUserIcon());

                String name =  TextUtils.isEmpty(model.getUserNickName()) ?
                        StringUtil.formatPhone(model.getUserName()) : model.getUserNickName();
                holder.setText(R.id.item_name, name);
                holder.setText(R.id.item_time, model.getCommentTime());

                holder.setGone(R.id.item_top, false);

                holder.getView(R.id.item_zan_image).setSelected(model.isGoodPoint());

                holder.setText(R.id.item_zan_count, NumberUtil.parseInt(model.getGetGoodPoint()) + "");

                holder.setText(R.id.item_content, model.getCommentContent());

                holder.addOnClickListener(R.id.item_zan_image);
                holder.addOnClickListener(R.id.item_edit);

                FixListView listView = holder.getView(R.id.item_sublist);
                List<NewsCommentModel> subCommentList = model.getSubList();
                if(subCommentList != null && subCommentList.size() > 0) {
                    if (USER_SubComment2Adapter) {
                        if (subCommentList.size() > 3) {
                            holder.setGone(R.id.item_sublist_hint, true);
                            holder.addOnClickListener(R.id.item_sublist_hint);
                            if (model.isExpandAllSubList()) {
                                listView.setAdapter(new SubComment2Adapter(name, model, subCommentList));
                                holder.setText(R.id.item_sublist_hint, "收起回复");
                            } else {
                                listView.setAdapter(new SubComment2Adapter(name, model, subCommentList.subList(0, 3)));
                                holder.setText(R.id.item_sublist_hint, "查看全部" + subCommentList.size() + "条回复");
                            }
                        } else {
                            holder.setGone(R.id.item_sublist_hint, false);
                            listView.setAdapter(new SubComment2Adapter(name, model, subCommentList));
                        }
                    } else {
                        if (subCommentList.size() > 3) {
                            holder.setGone(R.id.item_sublist_hint, true);
                            holder.addOnClickListener(R.id.item_sublist_hint);
                            if (model.isExpandAllSubList()) {
                                listView.setAdapter(new SubCommentAdapter(name, model, subCommentList));
                                holder.setText(R.id.item_sublist_hint, "收起回复");
                            } else {
                                listView.setAdapter(new SubCommentAdapter(name, model, subCommentList.subList(0, 3)));
                                holder.setText(R.id.item_sublist_hint, "查看全部" + subCommentList.size() + "条回复");
                            }
                        } else {
                            holder.setGone(R.id.item_sublist_hint, false);
                            listView.setAdapter(new SubCommentAdapter(name, model, subCommentList));
                        }
                    }
                }else {
                    listView.setAdapter(null);
                    holder.setGone(R.id.item_sublist_hint, false);
                }
            }
        };
    }

    public class SubCommentAdapter extends BaseAdapterViewAdapter<NewsCommentModel> {

        private String name;
        private NewsCommentModel parent;

        public SubCommentAdapter(String name, NewsCommentModel parent, List<NewsCommentModel> datas) {
            super(datas);
            this.name = name;
            this.parent = parent;
        }

        @Override
        protected View createView(ViewGroup viewGroup, Context context, int i) {
            return LayoutInflater.from(context).inflate(R.layout.item_news_comment_sub, viewGroup, false);
        }

        @Override
        protected void convert(ViewHolder holder, int i, final NewsCommentModel model) {
            String subName = TextUtils.isEmpty(model.getUserNickName()) ?
                    model.getUserName() : model.getUserNickName();

            TextView textView = holder.getView(R.id.item_sub_name);
            SpannableStringUtils.Builder builder = SpannableStringUtils.getBuilder(subName)
                    .setForegroundColor(Color.BLUE)
                    .append(" 回复 ")
                    .append(name)
                    .setForegroundColor(Color.BLUE);
            textView.setText(builder.create());

            GlideHelper.loadCircle(holder.getView(R.id.item_sub_icon), model.getUserIcon());

            holder.setText(R.id.item_sub_time, model.getCommentTime());

            holder.getView(R.id.item_sub_zan_image).setSelected(model.isGoodPoint());

            holder.setText(R.id.item_sub_zan_count, NumberUtil.parseInt(model.getGetGoodPoint()) + "");

            holder.setText(R.id.item_sub_content, model.getCommentContent());

            ViewListen.setClick(holder.getView(R.id.item_sub_zan_image), new OnClickListener() {
                @Override
                protected void onSafeClick(View v) {
                    if(mOnSubChildZanClickListener != null) {
                        mOnSubChildZanClickListener.onSubChildZanClick(parent, model);
                    }
                }
            });
        }
    }

    public class SubComment2Adapter extends BaseAdapterViewAdapter<NewsCommentModel> {

        private String name;
        private NewsCommentModel parent;

        public SubComment2Adapter(String name, NewsCommentModel parent, List<NewsCommentModel> datas) {
            super(datas);
            this.name = name;
            this.parent = parent;
        }

        @Override
        protected View createView(ViewGroup viewGroup, Context context, int i) {
            return LayoutInflater.from(context).inflate(R.layout.item_news_comment_sub2, viewGroup, false);
        }

        @Override
        protected void convert(ViewHolder holder, int i, final NewsCommentModel model) {

            TextView textView = holder.getView(R.id.item_sub_content);
            SpannableStringUtils.Builder builder = SpannableStringUtils.getBuilder(name)
                    .append(": ")
                    .append(model.getCommentContent());
            textView.setText(builder.create());

        }
    }
    public interface OnSubChildZanClickListener {
        void onSubChildZanClick(NewsCommentModel parent, NewsCommentModel model);
    }

    public void notifyData(NewsCommentModel parent, NewsCommentModel current) {
        int index;
        if(parent == null) {
            index = getData().indexOf(current);
        }else {
            index = getData().indexOf(parent);
        }

        if(index >= 0) {
            notifyItemChanged(index);
        }
    }
}
