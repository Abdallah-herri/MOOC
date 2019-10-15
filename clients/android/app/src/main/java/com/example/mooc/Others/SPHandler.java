package com.example.mooc.Others;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.mooc.Entities.User;
import com.example.mooc.Entities.UserParent;
import com.example.mooc.Entities.UserStudent;
import com.example.mooc.Entities.UserType;
import com.google.gson.Gson;

public class SPHandler {

    public static final String RESSOURCES = "Ressources";
    public static final String USER = "User";
    public static final String USERTYPE = "UserType";

    public static User getUser(Context context) {
        User user;
        UserType userType;
        Class<? extends User> userClass;

        userType = SPHandler.getSavedObjectFromPreference(context, SPHandler.RESSOURCES, SPHandler.USERTYPE, UserType.class);

        if(userType == null)
            return  null;

        userClass = (userType.getId() == UserType.STUDENT) ? UserStudent.class : UserParent.class;
        user = SPHandler.getSavedObjectFromPreference(context, SPHandler.RESSOURCES, SPHandler.USER, userClass);

        return user;
    }
    public static void saveObjectToSharedPreference(Context context, String preferenceFileName, String serializedObjectKey, Object object) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(preferenceFileName, 0);
        SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
        final Gson gson = new Gson();
        String serializedObject = gson.toJson(object);
        sharedPreferencesEditor.putString(serializedObjectKey, serializedObject);
        sharedPreferencesEditor.apply();
    }

    public static <GenericClass> GenericClass getSavedObjectFromPreference(Context context, String preferenceFileName, String preferenceKey, Class<GenericClass> classType) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(preferenceFileName, 0);
        if (sharedPreferences.contains(preferenceKey)) {
            final Gson gson = new Gson();
            return gson.fromJson(sharedPreferences.getString(preferenceKey, ""), classType);
        }
        return null;
    }

    public static void clearSharedPreferences(Context context, String preferenceFileName) {
        SharedPreferences settings = context.getSharedPreferences(preferenceFileName, Context.MODE_PRIVATE);
        settings.edit().clear().commit();
    }
}
