package com.danielmaia.desafio_android.util;

import android.os.Build;

public class Fonts {

    public static final String HELVETICA_NEUE = (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP_MR1
            ? "roboto.ttf"
            : "helvetica-neue.ttf");
    public static final String HELVETICA_NEUE_MEDIUM = "helvetica-neue-medium.ttf";
    public static final String HELVETICA_NEUE_BOLD = "helvetica-neue-bold.ttf";
    public static final String HELVETICA_NEUE_ITALIC = "helvetica-neue-italic.otf";
    public static final String GEORGIA_BOLD = "georgia-bold.ttf";
    public static final String GEORGIA_BOLD_ITALIC = "georgia-bold-italic.ttf";

    public static final String RALEWAY_BOLD = "Raleway-Bold.ttf";
    public static final String RALEWAY_EXTRA_BOLD = "Raleway-ExtraBold.ttf";
    public static final String RALEWAY_MEDIUM = "Raleway-Medium.ttf";
    public static final String RALEWAY_REGULAR = "Raleway-Regular.ttf";

    public static final String ROBOTO_BOLD ="Roboto-Bold.ttf";
    public static final String ROBOTO_MEDIUM = "Roboto-Medium.ttf";
    public static final String ROBOTO_REGULAR = "Roboto-Regular.ttf";

    public static final String FUTURA_MEDIUM = "futura-medium.ttf";
    public static final String FUTURA_BOLD = "futura-bold.ttf";
}