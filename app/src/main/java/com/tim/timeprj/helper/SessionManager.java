package com.tim.timeprj.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

import com.tim.timeprj.entity.Member;

public class SessionManager {
    // LogCat tag
    private static String TAG = SessionManager.class.getSimpleName();

    // Shared Preferences
    SharedPreferences pref;

    Editor editor;
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "TPLogin";

    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private static final String KEY_IS_TOKEN_ID = "tokenId";
    private static final String KEY_IS_USER_NAME = "userName";
    private static final String KEY_IS_FULL_NAME = "fullName";
    private static final String KEY_IS_USER_ID = "userId";
    public static String temptoken, tempusername;

    public static Member currMember;


    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setLogin(boolean isLoggedIn, String username) {

        editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn);
        editor.putString(KEY_IS_USER_NAME, username);
        // commit changes
        editor.commit();

        Log.d(TAG, "User login session modified!");
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public String getUserName() {
        Log.d(TAG, "777777777777777getUserName:" + pref.getString(KEY_IS_USER_NAME, "GUEST"));
        return pref.getString(KEY_IS_USER_NAME, "GUEST");
    }

    public String getUserID() {
        Log.d(TAG, "777777777777777getUserID:" + pref.getString(KEY_IS_USER_ID, ""));
        return pref.getString(KEY_IS_USER_ID, "");
    }

}
