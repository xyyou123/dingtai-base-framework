package com.dingtai.android.library.model.helper;

import android.content.Context;

import com.dingtai.android.library.database.DB;
import com.dingtai.android.library.model.db.AccountModelDao;
import com.dingtai.android.library.model.models.AccountModel;

/**
 * author:lnr
 * date:2018/9/11
 */
public class AccountHelper {

    private static final AccountHelper helper = new AccountHelper();

    private AccountModel user;

    private boolean inited;

    private AccountHelper() {
    }


    public static AccountHelper getInstance() {
        return helper;
    }

    public String getUserId() {
        return user == null ? null : user.getUserGUID();
    }

    public void login(Context context, AccountModel model) {
        this.user = model;
        DB.getInstance().initUser(context, user.getUserGUID());
    }

    public void logout() {
        user = null;
        AccountModelDao dao = (AccountModelDao) DB.getInstance().getConmon().getDao(AccountModelDao.class);
        if(dao != null) {
            dao.deleteAll();
        }
    }

    public boolean isLogin() {
        return user != null;
    }

    public AccountModel getUser() {
        return user;
    }

    public void init(Context context) {
        if(!inited && user == null) {
            AccountModelDao dao = (AccountModelDao) DB.getInstance().getConmon().getDao(AccountModelDao.class);
            if(dao != null) {
                user = dao.queryBuilder().unique();
                if(user != null) {
                    DB.getInstance().initUser(context, user.getUserGUID());
                }
            }
        }
        inited = true;
    }

    public void update2Database() {
        if(user == null) return;
        AccountModelDao dao = (AccountModelDao) DB.getInstance().getConmon().getDao(AccountModelDao.class);
        if(dao != null) {
            dao.updateInTx(user);
        }
    }

    public void updateFromDatabase() {
        AccountModelDao dao = (AccountModelDao) DB.getInstance().getConmon().getDao(AccountModelDao.class);
        if(dao != null) {
            user = dao.queryBuilder().unique();
        }
    }
}
