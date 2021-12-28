package com.example.collage_basecode.tests;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.NinePatch;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.collage_basecode.R;
import com.example.collage_basecode.drawing.VisualElement;
import com.example.collage_basecode.drawing.support.draw.IconImage;
import com.example.collage_basecode.drawing.support.draw.NinePartImage;
import com.example.collage_basecode.drawing.support.draw.OvalClip;
import com.example.collage_basecode.drawing.support.draw.SimpleFrame;
import com.example.collage_basecode.drawing.support.draw.SolidBackDrop;
import com.example.collage_basecode.drawing.support.draw.TextVisualElement;
import com.example.collage_basecode.drawing.support.layout.CircleLayout;
import com.example.collage_basecode.drawing.support.layout.ColumnLayout;
import com.example.collage_basecode.drawing.support.layout.PileLayout;
import com.example.collage_basecode.drawing.support.layout.RowLayout;
import com.example.collage_basecode.views.CollageView;

/**
 * Simple Collage view test helper class that creates menu items and runs tests
 * for the CollageView while you are running the app
 */
public abstract class CollageViewTestHelper {

    // Identifier for this class
    public static final String TAG = "A2_TESTS";

    private static final String TEST_TEXT = "Much Wow!";
    // Starting Index for putting menu items
    private static final int sMenuItemStartIndex = Menu.FIRST;

    private static final int sAPITestNumber = sMenuItemStartIndex;

    // TODO Increase if you add tests
    private static final int sNumTestItems = 4;

    /**
     * Runs a test based on the associated menu item passed in
     * If you add a test case in this file, you need to tie it to the assocaited option here
     *
     * @param item - the item that was selected
     * @return true if the test item was handled
     */
    public static boolean onTestItemSelected(
            MenuItem item,
            CollageView collageView,
            Context context) {
        if (collageView == null) {
            Log.e(TAG, "CollageView can't be null for testing");
            return false;
        }
        if (context == null) {
            Log.e(TAG, "Context can't be null for testing");
            return false;
        }
        if (item == null || sNumTestItems <= 0) {
            Log.e(TAG, "Null Item or 0 tests declared!");
            return false;
        }

        int itemId = item.getItemId();
        switch (itemId) {
            case sAPITestNumber:
                runAPITest1(collageView, context);
                break;
            case 2:
                runTest2(collageView, context);
                break;
            case 3:
                runTest3(collageView, context);
                break;
            case 4:
                runTest4(collageView, context);
                break;
            default:
                throw new IllegalArgumentException("Test Item selected has no action to perform!");
        }
        return true;
    }

    /**
     * Adds a number of test options to particular menu
     *
     * @param menu - the menu to add test options to
     */
    public static void createTestMenuItems(Menu menu) {
        if (menu == null) {
            return;
        }
        // Normally the strings below would be in the strings.xml file
        // We don't care about these not being there since is a simple
        // testing framework for your eyes only  :)
        for (int i = 0; i < sNumTestItems; i++) {
            int itemId = sMenuItemStartIndex + i;
            String itemLabel = String.format("Test %d", itemId);
            if (itemId == sAPITestNumber) {
                itemLabel = itemLabel + " (API Tests)";
            }
            menu.add(0, itemId, Menu.NONE, itemLabel);
        }
    }

    private static final void updateCollageViewWithRoot(CollageView collageView, VisualElement root) {
        if (collageView == null) {
            Log.e(TAG, "CollageView can't be null for testing");
        }
        collageView.setChildVisualElement(root);
    }

    // TODO: Below are a series of commented out testing coding - once you implement the requirements
    // you can use these to test your view hierarchy and the expected output

    private static void putSamplesInElement(
            VisualElement rootVisualElement,
            boolean placeElementsAtOrigin,
            Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context should not be null!");
        }
        if (rootVisualElement == null) {
            throw new IllegalArgumentException("rootVisualElement should not be null");
        }
        // TODO uncomment once you have implemented:
//         SimpleFrame, SolidBackDrop, IconImage, NinePartImage, TextVisualElement

        Resources res = context.getResources();
        if (placeElementsAtOrigin) {
            rootVisualElement.addChild(new SimpleFrame(0, 0, 100, 100));
            rootVisualElement.addChild(new SolidBackDrop(0, 0, 100, 100, Color.MAGENTA));
            rootVisualElement.addChild(new IconImage(0, 0, BitmapFactory.decodeResource(res, R.drawable.ic_noun_cat)));
            Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.bluebutton);
            NinePatch patches = new NinePatch(bitmap, bitmap.getNinePatchChunk(), null);
            rootVisualElement.addChild(new NinePartImage(0, 0, 40, 40, patches));
            rootVisualElement.addChild(new TextVisualElement(0, 0, TEST_TEXT, Typeface.DEFAULT_BOLD, 25f));
        } else {
            rootVisualElement.addChild(new SimpleFrame(10, 10, 40, 40));
            rootVisualElement.addChild(new SolidBackDrop(10, 60, 40, 40, Color.MAGENTA));
            rootVisualElement.addChild(new IconImage(10, 110, BitmapFactory.decodeResource(res, R.drawable.ic_noun_robot2)));
            Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.bluebutton);
            NinePatch patches = new NinePatch(bitmap, bitmap.getNinePatchChunk(), null);
            rootVisualElement.addChild(new NinePartImage(60, 10, 400, 40, patches));
            rootVisualElement.addChild(new TextVisualElement(60, 60, TEST_TEXT, Typeface.DEFAULT_BOLD, 25f));
        }
    }

    private static final void runAPITest1(CollageView collageView, Context context) {
        // TODO uncomment when you have implemented SolidBackDrop
        SolidBackDrop rootVisualElement = new SolidBackDrop(0, 0, 1000, 1000, Color.WHITE);
        SolidBackDrop fakeChild = new SolidBackDrop(0, 0, 400, 800, Color.WHITE);
        int numTestsPassed = 0;
        int numTestsFailed = 0;
        Log.d(TAG, "Running API Tests #1");
        try {
            rootVisualElement.addChild(null);
            Log.d(TAG, "Add null child:PASS");
            numTestsPassed += 1;
        } catch (Exception e) {
            Log.d(TAG, "Add null Child:FAIL");
            numTestsFailed += 1;
        }

        try {
            if (rootVisualElement.getChildAt(-1) == null) {
                Log.d(TAG, "Get bad Child:PASS");
                numTestsPassed += 1;
            } else {
                Log.d(TAG, "Get bad Child:FAIL");
                numTestsFailed += 1;
            }
        } catch (Exception e) {
            Log.d(TAG, "Get bad Child:FAIL");
            numTestsFailed += 1;
        }

        try {
            if (rootVisualElement.findChild(fakeChild) == -1
                    && rootVisualElement.findChild(null) == -1) {
                Log.d(TAG, "Find bad Child:PASS");
                numTestsPassed += 1;
            } else {
                Log.d(TAG, "Find bad Child:FAIL");
                numTestsFailed += 1;
            }
        } catch (Exception e) {
            Log.d(TAG, "Find bad Child:FAIL");
            numTestsFailed += 1;
        }


        try {
            rootVisualElement.moveChildLater(fakeChild);
            rootVisualElement.moveChildLater(null);
            Log.d(TAG, "Move bad Child:PASS");
            numTestsPassed += 1;
        } catch (Exception e) {
            Log.d(TAG, "Move bad Child:FAIL");
            numTestsFailed += 1;
        }

        try {
            SolidBackDrop s = new SolidBackDrop(0, 0, 100, 100, Color.GREEN);
            s.setPosition(null);
            s.setSize(null);
            Log.d(TAG, "Set position/size null:PASS");
            numTestsPassed += 1;
        } catch (Exception e) {
            Log.d(TAG, "Set position/size null:FAIL");
            numTestsFailed += 1;
        }
        String outputText = String.format(
                "API Tests Passed: %d \n API Tests Failed: %d \n See Log for Details",
                numTestsPassed,
                numTestsFailed);
        Toast testToast = Toast.makeText(context, outputText, Toast.LENGTH_LONG);
        testToast.show();
    }

    private static final void runTest2(CollageView collageView, Context context) {
        Log.d(TAG, "Running test 2!");
        /**
         * TODO uncomment when you have implemented the following:
         * SolidBackDrop, PileLayout, RowLayout, ColumnLayout
         **/
        SolidBackDrop rootVisualElement = new SolidBackDrop(0, 0, 1000, 1000, Color.WHITE);
        putSamplesInElement(rootVisualElement, false, context);
        PileLayout p = new PileLayout(10, 400, 200, 200);
        p.addChild(new SolidBackDrop(0, 0, 900, 900, Color.YELLOW));
        p.addChild(new SimpleFrame(0, 0, 200, 200));
        rootVisualElement.addChild(p);
        putSamplesInElement(p, false, context);
        p.addChild(new SolidBackDrop(0, 0, 40, 40, Color.CYAN));
        RowLayout r = new RowLayout(10, 620, 700, 100);
        rootVisualElement.addChild(r);
        putSamplesInElement(r, false, context);
        ColumnLayout col = new ColumnLayout(10, 740, 100, 400);
        rootVisualElement.addChild(col);
        putSamplesInElement(col, false, context);
        updateCollageViewWithRoot(collageView, rootVisualElement);
    }


    private static final void runTest3(CollageView collageView, Context context) {
        Log.d(TAG, "Running test 3!");
        /**
         * TODO uncomment when you have implemented the following:
         * SolidBackDrop, CircleLayout, OvalClip
         **/
        SolidBackDrop rootVisualElement = new SolidBackDrop(0, 0, 2000, 2000, Color.LTGRAY);
        SolidBackDrop s = new SolidBackDrop(0, 0, 100, 100, Color.GREEN);
        rootVisualElement.addChild(s);
        s.setPosition(-40, -40);
        SolidBackDrop s2 = new SolidBackDrop(0, 0, 100, 100, Color.BLUE);
        rootVisualElement.addChild(s2);
        SolidBackDrop s3 = new SolidBackDrop(0, 0, 100, 100, Color.RED);
        rootVisualElement.addChild(s3);
        rootVisualElement.moveChildLast(s);
        rootVisualElement.removeChild(s3);

        CircleLayout c = new CircleLayout(500, 500, 500, 500, 200, 200, 100);
        rootVisualElement.addChild(c);
        for (int i = 0; i < 8; i++) {
            c.addChild(new SolidBackDrop(0, 0, 20, 20, i % 2 == 1 ? Color.BLUE : Color.YELLOW));
        }
        SolidBackDrop s4 = new SolidBackDrop(0, 0, 50, 50, Color.MAGENTA);
        c.addChild(s4);

        OvalClip o = new OvalClip(5, 300, 1000, 1080);
        o.addChild(new SolidBackDrop(0, 0, 1000, 1000, Color.GREEN));
        putSamplesInElement(o, true, context);
        rootVisualElement.addChild(o);

        IconImage i = new IconImage(
                20,
                70,
                BitmapFactory.decodeResource(
                        context.getResources(),
                        R.drawable.ic_noun_whale));
        rootVisualElement.addChild(i);

        updateCollageViewWithRoot(collageView, rootVisualElement);

    }


    //   TODO add more test cases below, if necessary
    private static final void runTest4(CollageView collageView, Context context) {
        Log.d(TAG, "running test 4!");
        SolidBackDrop rootVisualElement = new SolidBackDrop(0, 0, 2000, 2000, Color.LTGRAY);

        TextVisualElement textVisualElement = new TextVisualElement(300, 80, "Honor", Typeface.DEFAULT, 50f);
        rootVisualElement.addChild(textVisualElement);

        //字体——“中”
        RowLayout rowLayout1 = new RowLayout(150, 300, 400, 30);
        for (int i = 0; i < 20; i++) {
            rowLayout1.addChild(new SolidBackDrop(0, 0, 30, 30, i % 2 == 1 ? Color.BLUE : Color.YELLOW));
        }
        rootVisualElement.addChild(rowLayout1);
        RowLayout rowLayout2 = new RowLayout(150, 500, 400, 30);
        for (int i = 0; i < 20; i++) {
            rowLayout2.addChild(new SolidBackDrop(0, 0, 30, 30, i % 2 == 1 ? Color.RED : Color.GREEN));
        }
        rootVisualElement.addChild(rowLayout2);
        ColumnLayout columnLayout1 = new ColumnLayout(150, 300, 30, 200);
        for (int i = 0; i < 10; i++) {
            columnLayout1.addChild(new SolidBackDrop(0, 0, 30, 30, i % 2 == 1 ? Color.TRANSPARENT : Color.GRAY));
        }
        rootVisualElement.addChild(columnLayout1);
        ColumnLayout columnLayout2 = new ColumnLayout(550, 300, 30, 200);
        for (int i = 0; i < 10; i++) {
            columnLayout2.addChild(new SolidBackDrop(0, 0, 30, 30, i % 2 == 1 ? Color.GRAY : Color.TRANSPARENT));
        }
        rootVisualElement.addChild(columnLayout2);
        ColumnLayout columnLayout3 = new ColumnLayout(350, 150, 30, 700);
        for (int i = 0; i < 30; i++) {
            columnLayout3.addChild(new SolidBackDrop(0, 0, 30, 30, i % 2 == 1 ? Color.YELLOW : Color.GREEN));
        }
        rootVisualElement.addChild(columnLayout3);

        //放置图片（压缩）
        Resources res = context.getApplicationContext().getResources();
        Bitmap bitmap1 = BitmapFactory.decodeResource(res, R.drawable.img);
        int width = bitmap1.getWidth();
        int height = bitmap1.getHeight();
        int newWidth = 170;
        int newHeight = 170;
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        bitmap1 = Bitmap.createBitmap(bitmap1, 0, 0, width, height, matrix, true);
        IconImage iconImage1 = new IconImage(180, 330, bitmap1);
        rootVisualElement.addChild(iconImage1);

        IconImage iconImage2 = new IconImage(380, 330, bitmap1);
        rootVisualElement.addChild(iconImage2);

        updateCollageViewWithRoot(collageView, rootVisualElement);
    }

}
