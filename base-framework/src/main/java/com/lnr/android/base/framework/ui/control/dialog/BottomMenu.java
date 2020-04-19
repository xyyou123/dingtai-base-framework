package com.lnr.android.base.framework.ui.control.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import com.lnr.android.base.framework.R;

import java.util.ArrayList;
import java.util.List;

/**
 * author:lnr
 * date:2018/6/19
 */

public class BottomMenu {

    private Context context;
    private Dialog dialog;
    private TextView txt_title;
    private View txt_title_line;
    private TextView txt_cancel;

    private ListView listView;

    private boolean showTitle = false;
    private List<MenuItem> sheetItemList;
    private Display display;
    private View.OnClickListener cancelListener;

    public BottomMenu(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    public BottomMenu builder() {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_bottom_menu, null);
        view.setMinimumWidth(display.getWidth());

        // 获取自定义Dialog布局中的控件
        txt_title = view.findViewById(R.id.dialog_bottom_menu_title);
        txt_title_line = view.findViewById(R.id.dialog_bottom_menu_title_line);
        txt_cancel = view.findViewById(R.id.dialog_bottom_menu_cancel);

        listView = view.findViewById(R.id.dialog_bottom_menu_list);

        // 定义Dialog布局和参数
        dialog = new Dialog(context, R.style.BottomMenu);
        dialog.setContentView(view);
        Window dialogWindow = dialog.getWindow();
        if(dialogWindow == null) return this;
        dialogWindow.setGravity(Gravity.START | Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.x = 0;
        lp.y = 0;
        dialogWindow.setAttributes(lp);

        return this;
    }

    public BottomMenu setTitle(String title, MenuColor color) {
        showTitle = true;
        txt_title.setVisibility(View.VISIBLE);
        txt_title.setText(title);

        if(color != null) {
            txt_title.setTextColor(Color.parseColor(color.name));
        }

        return this;
    }

    public BottomMenu setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    public BottomMenu setCanceledOnTouchOutside(boolean cancel) {
        dialog.setCanceledOnTouchOutside(cancel);
        return this;
    }

    public BottomMenu setCancelListener(View.OnClickListener listener) {
        cancelListener = listener;
        return this;
    }

    /**
     *
     * @param strItem
     *            条目名称
     * @param color
     *            条目字体颜色，设置null则默认蓝色
     */
    public BottomMenu addMenuItem(String strItem, MenuColor color,
                                   OnMenuClickListener listener) {
        if (sheetItemList == null) {
            sheetItemList = new ArrayList<>();
        }
        sheetItemList.add(new MenuItem(strItem, color, listener));
        return this;
    }

    /** 设置条目布局 */
    private void setMenuItems() {
        if (sheetItemList == null || sheetItemList.size() <= 0) {
            return;
        }


        if(showTitle) {
            txt_title.setVisibility(View.VISIBLE);
            txt_title_line.setVisibility(View.VISIBLE);
        }

        final int size = sheetItemList.size();

        // 添加条目过多的时候控制高度
        if (size >= 7) {
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) listView.getLayoutParams();
            params.height = display.getHeight() / 2;
            listView.setLayoutParams(params);
        }

        BaseAdapter adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return size;
            }

            @Override
            public MenuItem getItem(int position) {
                return sheetItemList.get(position);
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) View.inflate(parent.getContext(), R.layout.item_dialog_bottom_menu, null);
                textView.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                final MenuItem item = getItem(position);
                textView.setTextColor(Color.parseColor(item.color.name));
                textView.setText(item.name);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        item.itemClickListener.onClick();
                    }
                });
                return textView;
            }
        };

        listView.setAdapter(adapter);

        txt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if(cancelListener != null) {
                    cancelListener.onClick(v);
                }
            }
        });
    }

    public void show() {
        setMenuItems();
        dialog.show();
    }

    public interface OnMenuClickListener {
        void onClick();
    }

    public class MenuItem {
        String name;
        OnMenuClickListener itemClickListener;
        MenuColor color;

        public MenuItem(String name, MenuColor color, OnMenuClickListener itemClickListener) {
            this.name = name;
            this.color = color;
            this.itemClickListener = itemClickListener;
            if(this.color == null) {
                this.color = MenuColor.Blue;
            }
        }
    }

    public enum MenuColor {
        Blue("#01a1ef"), Red("#e84a47"),Black("#171717");

        private String name;

        MenuColor(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
