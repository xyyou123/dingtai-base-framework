package com.lnr.android.base.framework.common.html;

import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.text.style.ImageSpan;
import android.text.style.URLSpan;
import android.widget.TextView;

import com.lnr.android.base.framework.common.html.span.AbstractClickSpan;
import com.lnr.android.base.framework.common.html.span.ImageClickSpan;
import com.lnr.android.base.framework.common.html.span.LinkClickSpan;
import com.lnr.android.base.framework.common.html.span.VideoClickSpan;
import com.lnr.android.base.framework.common.html.span.VideoSpan;

import java.util.ArrayList;
import java.util.List;

public class HtmlText {
    private HtmlImageLoader imageLoader;
    private OnTagClickListener onTagClickListener;
    private After after;
    private String source;

    public interface After {
        CharSequence after(SpannableStringBuilder ssb);
    }

    private HtmlText(String source) {
        this.source = source;
    }

    /**
     * 设置源文本
     */
    public static HtmlText from(String source) {
        return new HtmlText(source);
    }

    /**
     * 设置加载器
     */
    public HtmlText setImageLoader(HtmlImageLoader imageLoader) {
        this.imageLoader = imageLoader;
        return this;
    }

    /**
     * 设置图片、链接点击监听器
     */
    public HtmlText setOnTagClickListener(OnTagClickListener onTagClickListener) {
        this.onTagClickListener = onTagClickListener;
        return this;
    }

    /**
     * 对处理完成的文本再次处理
     */
    public HtmlText after(After after) {
        this.after = after;
        return this;
    }

    /**
     * 注入TextView
     */
    public CharSequence format(TextView textView) {
        if (TextUtils.isEmpty(source)) {
            return null;
        }

        HtmlImageGetter imageGetter = new HtmlImageGetter();
        HtmlTagHandler tagHandler = new HtmlTagHandler(imageGetter);
        List<String> imageUrls = new ArrayList<>();

        imageGetter.setTextView(textView);
        imageGetter.setImageLoader(imageLoader);
        imageGetter.getImageSize(source);

        tagHandler.setTextView(textView);
//        source = tagHandler.overrideTags(source);

        Spanned spanned = Html.fromHtml(source, imageGetter, tagHandler);
        SpannableStringBuilder ssb;
        if (spanned instanceof SpannableStringBuilder) {
            ssb = (SpannableStringBuilder) spanned;
        } else {
            ssb = new SpannableStringBuilder(spanned);
        }

        // Hold text url link
        URLSpan[] urlSpans = ssb.getSpans(0, ssb.length(), URLSpan.class);
        if (urlSpans != null) {
            for (URLSpan urlSpan : urlSpans) {
                int start = ssb.getSpanStart(urlSpan);
                int end = ssb.getSpanEnd(urlSpan);
                ssb.removeSpan(urlSpan);
                LinkClickSpan linkClickSpan = new LinkClickSpan(textView.getContext(), urlSpan.getURL());
                linkClickSpan.setListener(onTagClickListener);
                ssb.setSpan(linkClickSpan, start, end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            }
        }

        // Hold image url link
        imageUrls.clear();
        ImageSpan[] imageSpans = ssb.getSpans(0, ssb.length(), ImageSpan.class);

        int position = 0;
        for (int i = 0; i < imageSpans.length; i++) {
            ImageSpan imageSpan = imageSpans[i];

            String imageUrl = imageSpan.getSource();
            int start = ssb.getSpanStart(imageSpan);
            int end = ssb.getSpanEnd(imageSpan);
            LinkClickSpan[] spans = ssb.getSpans(start, end, LinkClickSpan.class);
            if(spans != null && spans.length > 0) {
                continue;
            }

            AbstractClickSpan clickableSpan;
            if(imageSpan instanceof VideoSpan) {
                clickableSpan = new VideoClickSpan(textView.getContext(), ((VideoSpan) imageSpan).getVideoPath());
            }else {
                clickableSpan = new ImageClickSpan(textView.getContext(), imageUrls, position);
                imageUrls.add(imageUrl);
                position++;
            }

            clickableSpan.setListener(onTagClickListener);
            ClickableSpan[] clickableSpans = ssb.getSpans(start, end, ClickableSpan.class);
            if (clickableSpans != null) {
                for (ClickableSpan cs : clickableSpans) {
                    ssb.removeSpan(cs);
                }
            }
            ssb.setSpan(clickableSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        CharSequence charSequence = ssb;
        if (after != null) {
            charSequence = after.after(ssb);
        }

        return charSequence;
    }
}
