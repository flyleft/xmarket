package me.jcala.xmarket.util;

public class TimeUtils {

    public static String timeDiff(long time){
        long diffMinute =(System.currentTimeMillis()-time)/60000;
        if (diffMinute < 60){
            return diffMinute+" minutes ago";
        }else if (diffMinute < 24*60){
            return diffMinute/60 +" hours ago";
        }else if (diffMinute < 24*60*30){
            return diffMinute/60/24 +" days ago";
        }else {
            return diffMinute/60/24/30 +" months ago";
        }
    }
}
