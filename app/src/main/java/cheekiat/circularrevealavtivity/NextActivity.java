package cheekiat.circularrevealavtivity;

import android.animation.Animator;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Transition;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewAnimationUtils;

public class NextActivity extends AppCompatActivity {
    int height;
    int width;
    View mFab;
    ConstraintLayout mConstraintLayout;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        getWindow().setEnterTransition(null);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;
        mFab = findViewById(R.id.next_fab);

        mConstraintLayout = (ConstraintLayout) findViewById(R.id.bg);

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAnim(mConstraintLayout);
            }
        });
//        mFab.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
//            @Override
//            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
//                v.removeOnLayoutChangeListener(this);
//                startAnim(mConstraintLayout);
//            }
//        });
        Transition sharedElementEnterTransition = getWindow().getSharedElementEnterTransition();
        sharedElementEnterTransition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {
                startAnim(mConstraintLayout);

            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });

    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void startAnim(View myView) {
        // previously invisible view

// get the center for the clipping circle
        int cx = mFab.getWidth() / 2;
        int cy = mFab.getHeight() / 2;

// get the final radius for the clipping circle
        float finalRadius = (float) Math.hypot(width, height);

        int[] startingLocation = new int[2];
        mFab.getLocationInWindow(startingLocation);

// create the animator for this view (the start radius is zero)
        Animator anim =
                ViewAnimationUtils.createCircularReveal(myView, (int) (mFab.getX() + cx), (int) (mFab.getY() + cy), 0, finalRadius);

// make the view visible and start the animation
        myView.setVisibility(View.VISIBLE);
        anim.setDuration(1600);
        anim.start();
    }
}
