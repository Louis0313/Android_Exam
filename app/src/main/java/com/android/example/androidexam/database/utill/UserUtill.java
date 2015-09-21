
package com.android.example.androidexam.database.utill;

/**
 * Created by ws on 2015-09-18.
 */
public class UserUtill {

    public interface OnSignUpListener{
        void signUpListener(boolean result);
    }

//    public static boolean signUp(Context context, String nickname, String email, String password) {
//        UserDbHelper helper = new UserDbHelper(context);
//        ContentValues values = new ContentValues();
//        values.put(UserContract.UserEntry.COLUMN_NAME_NICKNAME, nickname);
//        values.put(UserContract.UserEntry.COLUMN_NAME_EMAIL, email);
//        values.put(UserContract.UserEntry.COLUMN_NAME_PASSWORD, password);
//        return helper.insert(values) != -1;
//    }
}
