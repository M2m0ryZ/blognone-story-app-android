package com.benzneststudios.blognonestory;

import java.util.ArrayList;

/**
 * Created by benznest on 16-Mar-18.
 */

public class MyThemes {

    private static ArrayList<MyThemeObject> listTheme;


    public static int getDefaultThemeBuildVariant() {
        return 0;
    }

    public static void init() {
        int i = 0;
        listTheme = new ArrayList<>();
        listTheme.add(new MyThemeObject(
                R.style.AppTheme,
                MyThemeName.themeName[i++],
                R.color.colorSteelBlue, R.color.colorSteelBlueDark, R.color.colorBlognoneGreen, R.color.colorTextPrimary,
                true));

        listTheme.add(new MyThemeObject(
                R.style.AppThemeGreen_Yellow,
                MyThemeName.themeName[i++],
                R.color.colorGreen, R.color.colorGreenDark, R.color.colorYellow, R.color.colorTextPrimary,
                true));

        listTheme.add(new MyThemeObject(
                R.style.AppThemeBlue_Tomato,
                MyThemeName.themeName[i++],
                R.color.colorBlue, R.color.colorBlueDark, R.color.colorTomato, R.color.colorTextPrimary,
                true));

        listTheme.add(new MyThemeObject(
                R.style.AppThemeAsphalt_Tomato,
                MyThemeName.themeName[i++],
                R.color.colorWetAsphalt, R.color.colorWetAsphaltDark, R.color.colorTomato, R.color.colorTextPrimary,
                true));

        listTheme.add(new MyThemeObject(
                R.style.AppThemeAsphalt_Turquoise,
                MyThemeName.themeName[i++],
                R.color.colorWetAsphalt, R.color.colorWetAsphaltDark, R.color.colorTurquoise, R.color.colorTextPrimary,
                true));


        listTheme.add(new MyThemeObject(
                R.style.AppThemeBlue_Yellow,
                MyThemeName.themeName[i++],
                R.color.colorBlue, R.color.colorBlueDark, R.color.colorYellow, R.color.colorTextPrimary,
                true));

        listTheme.add(new MyThemeObject(
                R.style.AppThemeRed_Yellow,
                MyThemeName.themeName[i++],
                R.color.colorRed, R.color.colorRedDark, R.color.colorYellow, R.color.colorTextPrimary,
                true));

        listTheme.add(new MyThemeObject(
                R.style.AppThemeGreyBlack_Yellow,
                MyThemeName.themeName[i++],
                R.color.colorGreyBlack, R.color.colorGreyBlackDark, R.color.colorYellow, R.color.colorTextPrimary,
                true));

        listTheme.add(new MyThemeObject(
                R.style.AppThemeGreyBlack_Blue,
                MyThemeName.themeName[i++],
                R.color.colorGreyBlack, R.color.colorGreyBlackDark, R.color.colorBlue, R.color.colorTextPrimary,
                true));

        listTheme.add(new MyThemeObject(
                R.style.AppThemeTomato_Yellow,
                MyThemeName.themeName[i++],
                R.color.colorTomato, R.color.colorTomatoDark, R.color.colorYellow, R.color.colorTextPrimary,
                true));

        listTheme.add(new MyThemeObject(
                R.style.AppThemeYellow_GreyBlack,
                MyThemeName.themeName[i++],
                R.color.colorYellow, R.color.colorYellowDark, R.color.colorGreyBlack, R.color.colorTextPrimary,
                true));

        listTheme.add(new MyThemeObject(
                R.style.AppThemeBlue_GreyBlack,
                MyThemeName.themeName[i++],
                R.color.colorBlue, R.color.colorBlueDark, R.color.colorGreyBlack, R.color.colorTextPrimary,
                true));

        listTheme.add(new MyThemeObject(
                R.style.AppThemeBlue_Green,
                MyThemeName.themeName[i++],
                R.color.colorBlue, R.color.colorBlueDark, R.color.colorGreen, R.color.colorTextPrimary,
                true));

        listTheme.add(new MyThemeObject(
                R.style.AppThemeYellow_Tomato,
                MyThemeName.themeName[i++],
                R.color.colorYellow, R.color.colorYellowDark, R.color.colorTomato, R.color.colorTextPrimary,
                true));

        listTheme.add(new MyThemeObject(
                R.style.AppThemeGreen_GreyBlack,
                MyThemeName.themeName[i++],
                R.color.colorGreen, R.color.colorGreenDark, R.color.colorGreyBlack, R.color.colorTextPrimary,
                true));

        listTheme.add(new MyThemeObject(
                R.style.AppThemeGreyBlack_Tomato,
                MyThemeName.themeName[i++],
                R.color.colorGreyBlack, R.color.colorGreyBlackDark, R.color.colorTomato, R.color.colorTextPrimary,
                true));


        listTheme.add(new MyThemeObject(
                R.style.AppThemeRed_GreyBlack,
                MyThemeName.themeName[i++],
                R.color.colorRed, R.color.colorRedDark, R.color.colorGreyBlack, R.color.colorTextPrimary,
                true));

        listTheme.add(new MyThemeObject(
                R.style.AppThemeGreyBlack_Red,
                MyThemeName.themeName[i++],
                R.color.colorWetAsphalt, R.color.colorWetAsphaltDark, R.color.colorRed, R.color.colorTextPrimary,
                true));

        listTheme.add(new MyThemeObject(
                R.style.AppThemeYellow_Green,
                MyThemeName.themeName[i++],
                R.color.colorYellow, R.color.colorYellowDark, R.color.colorGreen, R.color.colorTextPrimary,
                true));

        listTheme.add(new MyThemeObject(
                R.style.AppThemeTomato_WetAsphalt,
                MyThemeName.themeName[i++],
                R.color.colorTomato, R.color.colorTomatoDark, R.color.colorWetAsphalt, R.color.colorTextPrimary,
                true));

        listTheme.add(new MyThemeObject(
                R.style.AppThemeTomato_Purple,
                MyThemeName.themeName[i++],
                R.color.colorTomato, R.color.colorTomatoDark, R.color.colorPurple, R.color.colorTextPrimary,
                true));

        listTheme.add(new MyThemeObject(
                R.style.AppThemeGreen_Blue,
                MyThemeName.themeName[i++],
                R.color.colorGreen, R.color.colorGreenDark, R.color.colorBlue, R.color.colorTextPrimary,
                true));

        listTheme.add(new MyThemeObject(
                R.style.AppThemeBlue_WetAsphalt,
                MyThemeName.themeName[i++],
                R.color.colorBlue, R.color.colorBlueDark, R.color.colorWetAsphalt, R.color.colorTextPrimary,
                true));

        listTheme.add(new MyThemeObject(
                R.style.AppThemeBlue_Turquoise,
                MyThemeName.themeName[i++],
                R.color.colorBlue, R.color.colorBlueDark, R.color.colorTurquoise, R.color.colorTextPrimary,
                true));

        listTheme.add(new MyThemeObject(
                R.style.AppThemePurple_Yellow,
                MyThemeName.themeName[i++],
                R.color.colorPurple, R.color.colorPurpleDark, R.color.colorYellow, R.color.colorTextPrimary,
                true));

        listTheme.add(new MyThemeObject(
                R.style.AppThemePurple_Tomato,
                MyThemeName.themeName[i++],
                R.color.colorPurple, R.color.colorPurpleDark, R.color.colorTomato, R.color.colorTextPrimary,
                true));

        listTheme.add(new MyThemeObject(
                R.style.AppThemePurple_Turquoise,
                MyThemeName.themeName[i++],
                R.color.colorPurple, R.color.colorPurpleDark, R.color.colorTurquoise, R.color.colorTextPrimary,
                true));

        listTheme.add(new MyThemeObject(
                R.style.AppThemeWildGreen_Yellow,
                MyThemeName.themeName[i++],
                R.color.colorWildGreen, R.color.colorWildGreenDark, R.color.colorYellow, R.color.colorTextPrimary,
                true));

        listTheme.add(new MyThemeObject(
                R.style.AppThemeWildGreen_WetAsphalt,
                MyThemeName.themeName[i++],
                R.color.colorWildGreen, R.color.colorWildGreenDark, R.color.colorWetAsphalt, R.color.colorTextPrimary,
                true));

        listTheme.add(new MyThemeObject(
                R.style.AppThemeWildGreen_Blue,
                MyThemeName.themeName[i++],
                R.color.colorWildGreen, R.color.colorWildGreenDark, R.color.colorBlue, R.color.colorTextPrimary,
                true));

        listTheme.add(new MyThemeObject(
                R.style.AppThemeYellow_Blue,
                MyThemeName.themeName[i++],
                R.color.colorSand, R.color.colorSandDark, R.color.colorBlue, R.color.colorTextPrimary,
                true));

        listTheme.add(new MyThemeObject(
                R.style.AppThemeSand_Tomato,
                MyThemeName.themeName[i++],
                R.color.colorSand, R.color.colorSandDark, R.color.colorTomato, R.color.colorTextPrimary,
                true));

        listTheme.add(new MyThemeObject(
                R.style.AppThemeSand_WetAsphalt,
                MyThemeName.themeName[i++],
                R.color.colorSand, R.color.colorSandDark, R.color.colorWetAsphalt, R.color.colorTextPrimary,
                true));

        listTheme.add(new MyThemeObject(
                R.style.AppThemeWetAsphalt_Sand,
                MyThemeName.themeName[i++],
                R.color.colorWetAsphalt, R.color.colorWetAsphaltDark, R.color.colorSand, R.color.colorTextPrimary,
                true));

        listTheme.add(new MyThemeObject(
                R.style.AppThemeWetAsphalt_Pink,
                MyThemeName.themeName[i++],
                R.color.colorWetAsphalt, R.color.colorWetAsphaltDark, R.color.colorPink, R.color.colorTextPrimary,
                true));

        listTheme.add(new MyThemeObject(
                R.style.AppThemePink_Red,
                MyThemeName.themeName[i++],
                R.color.colorPink, R.color.colorPinkDark, R.color.colorRed, R.color.colorTextPrimary,
                true));


        listTheme.add(new MyThemeObject(
                R.style.AppThemePink_Green,
                MyThemeName.themeName[i++],
                R.color.colorPink, R.color.colorPinkDark, R.color.colorGreen, R.color.colorTextPrimary,
                true));

        listTheme.add(new MyThemeObject(
                R.style.AppThemePink_Yellow,
                MyThemeName.themeName[i++],
                R.color.colorPink, R.color.colorPinkDark, R.color.colorYellow, R.color.colorTextPrimary,
                true));

        listTheme.add(new MyThemeObject(
                R.style.AppThemePink_Blue,
                MyThemeName.themeName[i++],
                R.color.colorPink, R.color.colorPinkDark, R.color.colorBlue, R.color.colorTextPrimary,
                true));

        listTheme.add(new MyThemeObject(
                R.style.AppThemeTurquoise_Yellow,
                MyThemeName.themeName[i++],
                R.color.colorTurquoise, R.color.colorTurquoiseDark, R.color.colorYellow, R.color.colorTextPrimary,
                true));

        listTheme.add(new MyThemeObject(
                R.style.AppThemeTurquoise_Tomato,
                MyThemeName.themeName[i++],
                R.color.colorTurquoise, R.color.colorTurquoiseDark, R.color.colorTomato, R.color.colorTextPrimary,
                true));

        listTheme.add(new MyThemeObject(
                R.style.AppThemeTurquoise_WetAsphalt,
                MyThemeName.themeName[i++],
                R.color.colorTurquoise, R.color.colorTurquoiseDark, R.color.colorWetAsphalt, R.color.colorTextPrimary,
                true));

        listTheme.add(new MyThemeObject(
                R.style.AppThemeYellow_YellowDark,
                MyThemeName.themeName[i++],
                R.color.colorYellow, R.color.colorYellowDark, R.color.colorYellowDark, R.color.colorTextPrimary,
                true));

        listTheme.add(new MyThemeObject(
                R.style.AppThemeYellowDark_YellowDark2,
                MyThemeName.themeName[i++],
                R.color.colorYellowDark, R.color.colorYellowDark2, R.color.colorYellowDark2, R.color.colorTextPrimary,
                true));

        listTheme.add(new MyThemeObject(
                R.style.AppThemePink_PinkDark,
                MyThemeName.themeName[i++],
                R.color.colorPink, R.color.colorPinkDark, R.color.colorPinkDark, R.color.colorTextPrimary,
                true));

        listTheme.add(new MyThemeObject(
                R.style.AppThemeTomato_TomatoDark,
                MyThemeName.themeName[i++],
                R.color.colorTomato, R.color.colorTomatoDark, R.color.colorTomatoDark, R.color.colorTextPrimary,
                true));
        listTheme.add(new MyThemeObject(
                R.style.AppThemeRed_RedDark,
                MyThemeName.themeName[i++],
                R.color.colorRed, R.color.colorRedDark, R.color.colorRedDark, R.color.colorTextPrimary,
                true));

        listTheme.add(new MyThemeObject(
                R.style.AppThemeBlue_BlueDark,
                MyThemeName.themeName[i++],
                R.color.colorBlue, R.color.colorBlueDark, R.color.colorBlueDark, R.color.colorTextPrimary,
                true));

        listTheme.add(new MyThemeObject(
                R.style.AppThemePurple_PurpleDark,
                MyThemeName.themeName[i++],
                R.color.colorPurple, R.color.colorPurpleDark, R.color.colorPurpleDark, R.color.colorTextPrimary,
                true));

        listTheme.add(new MyThemeObject(
                R.style.AppThemeGreen_GreenDark,
                MyThemeName.themeName[i++],
                R.color.colorGreen, R.color.colorGreenDark, R.color.colorGreenDark, R.color.colorTextPrimary,
                true));

        listTheme.add(new MyThemeObject(
                R.style.AppThemeWildGreen_WildGreenDark,
                MyThemeName.themeName[i++],
                R.color.colorWildGreen, R.color.colorWildGreenDark, R.color.colorWildGreenDark, R.color.colorTextPrimary,
                true));

        listTheme.add(new MyThemeObject(
                R.style.AppThemeTurquoise_TurquoiseDark,
                MyThemeName.themeName[i++],
                R.color.colorTurquoise, R.color.colorTurquoiseDark, R.color.colorTurquoiseDark, R.color.colorTextPrimary,
                true));

        listTheme.add(new MyThemeObject(
                R.style.AppThemeWetAsphalt_WetAsphaltDark,
                MyThemeName.themeName[i++],
                R.color.colorWetAsphalt, R.color.colorWetAsphaltDark, R.color.colorWetAsphaltDark, R.color.colorTextPrimary,
                true));
        listTheme.add(new MyThemeObject(
                R.style.AppThemeGreyBlack_GreyBlackDark,
                MyThemeName.themeName[i++],
                R.color.colorGreyBlack, R.color.colorGreyBlackDark, R.color.colorGreyBlackDark, R.color.colorTextPrimary,
                true));

        listTheme.add(new MyThemeObject(
                R.style.AppThemePoloniex_YellowDark,
                MyThemeName.themeName[i++],
                R.color.colorPoloniex, R.color.colorPoloniexDark, R.color.colorYellowDark, R.color.colorTextPrimary,
                true));
    }

    public static ArrayList<MyThemeObject> getTheme() {
        if (listTheme == null) {
            init();
        }
        return listTheme;
    }

    public static MyThemeObject getCurrentTheme() {
        int index = MyCache.getTheme(MyContextor.getInstance());
        try {
            return listTheme.get(index);
        } catch (Exception e) {
            return listTheme.get(0);
        }
    }

    public static class MyThemeObject {
        private int theme;
        private String name;
        private int colorPrimary;
        private int colorSecondary;
        private int colorIndicator;
        private int colorText;
        private boolean isProOnly;
        private int dayFree;
        private int monthFree;

        public MyThemeObject(int theme, String name, int colorPrimary, int colorSecondary, int colorIndicator, int colorText, boolean isProOnly) {
            this.theme = theme;
            this.name = name;
            this.colorPrimary = colorPrimary;
            this.colorSecondary = colorSecondary;
            this.colorIndicator = colorIndicator;
            this.colorText = colorText;
            this.isProOnly = isProOnly;
        }

        public MyThemeObject(int theme, String name, int colorPrimary, int colorSecondary, int colorIndicator, int colorText, boolean isProOnly, int dayFree, int monthFree) {
            this.theme = theme;
            this.name = name;
            this.colorPrimary = colorPrimary;
            this.colorSecondary = colorSecondary;
            this.colorIndicator = colorIndicator;
            this.colorText = colorText;
            this.isProOnly = isProOnly;
            this.dayFree = dayFree;
            this.monthFree = monthFree;
        }

        public int getTheme() {
            return theme;
        }

        public String getName() {
            return name;
        }

        public int getPrimaryColor() {
            return colorPrimary;
        }


        public int getColorSecondary() {
            return colorSecondary;
        }

        public int getIndicatorColor() {
            return colorIndicator;
        }

        public int getTextColor() {
            return colorText;
        }

        public boolean isProOnly() {
            return isProOnly;
        }

        public int getDayFree() {
            return dayFree;
        }

        public void setDayFree(int dayFree) {
            this.dayFree = dayFree;
        }

        public int getMonthFree() {
            return monthFree;
        }

        public void setMonthFree(int monthFree) {
            this.monthFree = monthFree;
        }
    }
}
