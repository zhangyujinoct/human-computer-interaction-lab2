package com.example.collage_basecode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.collage_basecode.drawing.support.draw.IconImage;
import com.example.collage_basecode.drawing.support.draw.SolidBackDrop;
import com.example.collage_basecode.drawing.support.layout.RowLayout;
import com.example.collage_basecode.tests.CollageViewTestHelper;
import com.example.collage_basecode.views.CollageView;


public class CollageActivity extends AppCompatActivity {

    public static final String TAG = "HCI-COLLAGE-TESTS";

    // The toolbar with the settings icon
    private Toolbar mSupportActionBar;
    // The container holding out collage view
    private FrameLayout mCollageFrame;
    // The host view that holds a reference to our custom view hierarchy
    private CollageView mCollageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Magic for creating the settings icon/choices
        setContentView(R.layout.activity_main);
        mSupportActionBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mSupportActionBar);

        // Grab "Frame" then create basic view to hold the collage
        mCollageFrame = (FrameLayout) findViewById(R.id.frame_collage);
        if (mCollageFrame != null) {
            mCollageView = new CollageView(this);
            mCollageFrame.addView(mCollageView);
            // TODO create the root visual element of your collage view
            // using your created BaseVisualElement class and set it

            // mCollageView.setChildVisualElement(rootVisualElement);
            initCustomCollage();
            refreshViewHierarchy();
        }
    }

    /**
     * Gets called every time the user presses the menu button.
     * Use if your menu is dynamic.
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        // Adds out test options to the menu bar
        CollageViewTestHelper.createTestMenuItems(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        boolean didHandleAction = CollageViewTestHelper.onTestItemSelected(
                item,
                mCollageView,
                this);
        if (didHandleAction) {
            refreshViewHierarchy();
        }
        return didHandleAction;
    }

    /**
     * Function to put your custom collage into
     * You may create additional methods like this to test
     * functionality
     */
    private void initCustomCollage() {
        // TODO: Part 2: Implement a Custom Collage
        SolidBackDrop rootVisualElement = new SolidBackDrop(0, 0, 2000, 2000, Color.LTGRAY);
        mCollageView.setChildVisualElement(rootVisualElement);
        Resources res = getApplicationContext().getResources();
        Bitmap bitmap1 = BitmapFactory.decodeResource(res, R.drawable.img_1);
        int width = bitmap1.getWidth();
        int height = bitmap1.getHeight();
        int newWidth = 400;
        int newHeight = 600;
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        Matrix matrix = new Matrix();
        matrix.postScale(1, scaleHeight);
        bitmap1 = Bitmap.createBitmap(bitmap1, 0, 0, width, height, matrix, true);
        IconImage iconImage1 = new IconImage(0, 100, bitmap1);
        rootVisualElement.addChild(iconImage1);

        RowLayout rowLayout1 = new RowLayout(50, 700, 600, 30);
        for (int i = 0; i < 20; i++) {
            rowLayout1.addChild(new SolidBackDrop(0, 0, 30, 30, i % 2 == 1 ? Color.BLUE : Color.YELLOW));
        }
        rootVisualElement.addChild(rowLayout1);

        // Finish off by refreshing the view Hierarchy
        refreshViewHierarchy();
    }

    /**
     * Helper method to refresh the custom drawing hierarchy
     */
    private void refreshViewHierarchy() {
        if (mCollageView == null) {
            return;
        }
        mCollageView.requestLayout();
        mCollageView.invalidate();
    }
}