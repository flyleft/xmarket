package me.jcala.xmarket.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;

public class ViewToGlass {

    public static void toBlur(View fromView, View toView, float radius, float scaleFactor) {

        fromView.buildDrawingCache();
        Bitmap bkg = fromView.getDrawingCache();

        if (radius<1||radius>26) {
            scaleFactor = 8;
            radius = 2;
        }

        Bitmap overlay = Bitmap.createBitmap((int) (toView.getMeasuredWidth()/scaleFactor),
                (int) (toView.getMeasuredHeight()/scaleFactor), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(overlay);
        canvas.translate(-toView.getLeft()/scaleFactor, -toView.getTop()/scaleFactor);
        canvas.scale(1 / scaleFactor, 1 / scaleFactor);
        Paint paint = new Paint();
        paint.setFlags(Paint.FILTER_BITMAP_FLAG);
        canvas.drawBitmap(bkg, 0, 0, paint);

        overlay = FastBlurUtil.doBlur(overlay, (int) radius, true);
        toView.setBackground(new BitmapDrawable(overlay));

    }

}
