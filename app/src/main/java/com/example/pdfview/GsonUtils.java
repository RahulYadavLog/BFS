package com.example.pdfview;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.List;
public class GsonUtils {

        public static Gson gson;

        public static Gson getInstance() {
            if (gson == null) {
                return new Gson();
            }
            return gson;
        }

        public static Type getLongListType() {
            return new TypeToken<List<Long>>() {
            }.getType();
        }

        public static Type getStringListType() {
            return new TypeToken<List<String>>() {
            }.getType();
        }

        public static Type getStringSetType() {
            return new TypeToken<HashSet<String>>() {
            }.getType();

    }
}
