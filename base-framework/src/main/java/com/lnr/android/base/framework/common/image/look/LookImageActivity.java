package com.lnr.android.base.framework.common.image.look;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.common.image.load.GlideHelper;
import com.lnr.android.base.framework.dagger.ApplicationComponent;
import com.lnr.android.base.framework.data.asyn.http.retrofit.progress.ProgressListener;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;
import com.lnr.android.base.framework.ui.base.BaseActivity;
import com.lnr.android.base.framework.ui.control.listener.OnClickListener;
import com.lnr.android.base.framework.ui.control.listener.ViewListen;
import com.lnr.android.base.framework.ui.control.toast.ToastHelper;
import com.lnr.android.base.framework.ui.control.view.RingProgressBar;
import com.lnr.android.base.framework.ui.control.view.viewpager.BaseViewPagerAdapter;
import com.lnr.android.base.framework.uitl.FileProvider7;
import com.lnr.android.base.framework.uitl.FileUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.reactivex.functions.Consumer;

/**
 * author :  lnr
 * date : 2018/8/18.
 */
@Route(path = "/framework/image/look")
public class LookImageActivity extends BaseActivity {

    protected ViewPager viewPager;

    @Autowired(name = "images")
    protected ArrayList<String> imageList;
    @Autowired(name = "index")
    protected int index;
    @Autowired(name = "contents")
    protected ArrayList<String> contentList;
    @Autowired
    protected String content;

    protected TextView textCount, textContent;

    protected View bottomLayout;

    protected ImageView imageBack, imageSave;

    protected SparseArray<PagerProgressListener> mPagerProgressListener;

    protected Set<String> mSaveImageList = new HashSet<>();

    protected RequestOptions options = new RequestOptions()
            .error(GlideHelper.error)
            .diskCacheStrategy(DiskCacheStrategy.ALL);
    protected BaseViewPagerAdapter<String> mViewAdapter;

    @Override
    public String digest() {
        return null;
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_look_image);
    }

    @Override
    protected List<IPresenter> getIPresenters() {
        return null;
    }

    @Override
    protected void inject(ApplicationComponent component) {
    }

    @Override
    protected void initView() {
        mImmersionBar.statusBarColor(R.color.black).init();
        getWindow().setBackgroundDrawableResource(R.color.black);

        viewPager = findViewById(R.id.image_pager);
        textCount = findViewById(R.id.text_count);
        textContent = findViewById(R.id.text_content);

        bottomLayout = findViewById(R.id.layout_bottom);

        imageBack = findViewById(R.id.btn_back);
        imageSave = findViewById(R.id.btn_save);

        ViewListen.setClick(imageBack, new OnClickListener() {
            @Override
            protected void onSafeClick(View v) {
                finish();
            }
        });

        ViewListen.setClick(imageSave, new OnClickListener() {
            @Override
            protected void onSafeClick(View v) {
                requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .request(new Consumer<Boolean>() {
                            @Override
                            public void accept(Boolean aBoolean) throws Exception {
                                if(aBoolean) {
                                    saveImage();
                                }
                            }
                        });
            }
        });
    }

    @Override
    protected void afterInitView(@Nullable Bundle savedInstanceState) {
        if(imageList == null || imageList.isEmpty()) {
            onBackPressed();
            return;
        }

        if(content == null && contentList == null) {
            bottomLayout.setVisibility(View.GONE);
        }

        mPagerProgressListener = new SparseArray<>();

        viewPager.setOffscreenPageLimit(1);
        mViewAdapter = getViewAdapter();
        viewPager.setAdapter(mViewAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if(contentList == null) {
                    textContent.setText(content);
                }else if(contentList.size() > 0){
                    textContent.setText(contentList.get(position));
                }
                textCount.setText(MessageFormat.format("{0}/{1}", position + 1, imageList.size()));
            }
        });

        if(contentList == null) {
            textContent.setText(content);
        }else if(contentList.size() > 0){
            textContent.setText(contentList.get(0));
        }
        textCount.setText(MessageFormat.format("{0}/{1}", 1, imageList.size()));

        viewPager.setCurrentItem(Math.max(0, index));
    }

    @NonNull
    protected BaseViewPagerAdapter<String> getViewAdapter() {
        return new BaseViewPagerAdapter<String>(imageList) {

            @Override
            protected View createView(ViewGroup container, int position, String s) {
                View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_look_image, container, false);
                ViewListen.setClick(view.findViewById(R.id.PhotoView), new OnClickListener() {
                    @Override
                    protected void onSafeClick(View v) {
                        switchBottom();
                    }
                });
                return view;
            }

            @Override
            protected void convert(final View view, final int position, String s) {
                PagerProgressListener listener = mPagerProgressListener.get(position);
                if(listener == null) {
                    listener = new PagerProgressListener();
                    mPagerProgressListener.append(position, listener);
                }
                listener.bar = view.findViewById(R.id.RingProgressBar);
                GlideHelper.loadUseProgress((ImageView) view.findViewById(R.id.PhotoView), s, options, listener);
            }
        };
    }


    private static class PagerProgressListener implements ProgressListener {

        RingProgressBar bar;

        @Override
        public void onProgress(final int progress) {
            if(bar == null) return;
            bar.post(new Runnable() {
                @Override
                public void run() {
                    bar.setProgress(progress < 5 ? 5 : progress);
                    if(progress >= 100) {
                        bar.setVisibility(View.GONE);
                    }else if(bar.getVisibility() != View.VISIBLE) {
                        bar.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
    }


    protected void switchBottom() {
        if(content == null && contentList == null) {
            return;
        }
        if(bottomLayout.getVisibility() == View.VISIBLE) {
            bottomLayout.setVisibility(View.GONE);
            imageBack.setVisibility(View.GONE);
            imageSave.setVisibility(View.GONE);
        }else {
            bottomLayout.setVisibility(View.VISIBLE);
            imageBack.setVisibility(View.VISIBLE);
            imageSave.setVisibility(View.VISIBLE);
        }
    }

    protected void saveImage() {
        if(viewPager == null) return;
        if(imageList == null || imageList.isEmpty()) return;
        int position = viewPager.getCurrentItem();
        if(position >= imageList.size()) return;

        final String url = imageList.get(position);
        if(mSaveImageList.contains(url)) {
            return;
        }
        mSaveImageList.add(url);
        Glide.with(this).downloadOnly().load(url).into(new SimpleTarget<File>() {
            @Override
            public void onResourceReady(@NonNull File resource, @Nullable Transition<? super File> transition) {
                try {

                    String galleryPath = Environment.getExternalStorageDirectory()
                            + File.separator + Environment.DIRECTORY_DCIM
                            + File.separator + "Camera" + File.separator;

                    File file = FileUtil.saveFile(new File(galleryPath, System.currentTimeMillis() + ".jpg").getPath(), new FileInputStream(resource));
                    if(file == null) {
                        ToastHelper.toastError("图片保存失败");
                        mSaveImageList.remove(url);
                        return;
                    }
                    MediaStore.Images.Media.insertImage(getContentResolver(), file.getAbsolutePath(), file.getName(), null);
                    Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    Uri uri = FileProvider7.getUriForFile(mActivity, resource);
                    intent.setData(uri);
                    sendBroadcast(intent);
                    ToastHelper.toastSucceed("图片保存成功");
                } catch (Exception e) {
                    ToastHelper.toastError("图片保存失败");
                    mSaveImageList.remove(url);
                }
            }

            @Override
            public void onLoadFailed(@Nullable Drawable errorDrawable) {
                super.onLoadFailed(errorDrawable);
                ToastHelper.toastError("保存失败");
                mSaveImageList.remove(url);
            }
        });
    }
}
