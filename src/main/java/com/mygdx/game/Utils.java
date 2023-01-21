package com.mygdx.game;

public class Utils {
    public static final String BASE_ASSETS_PATH = "assets/";

    private static final boolean debugging = true;

    public static void debug(String s) {
        if (debugging) {
            System.out.println(s);
        }
    }
}
