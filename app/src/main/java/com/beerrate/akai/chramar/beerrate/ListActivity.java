package com.beerrate.akai.chramar.beerrate;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.beerrate.akai.chramar.beerrate.Adapters.BeerRecyclerViewAdapter;
import com.beerrate.akai.chramar.beerrate.RecyclerViewClickListener.BeerRecyclerViewItemListener;
import com.beerrate.akai.chramar.beerrate.RecyclerViewClickListener.ClickListener;
import com.beerrate.akai.chramar.beerrate.connector.ConnectorFacade;
import com.beerrate.akai.chramar.beerrate.datamodel.Beer;

import java.util.ArrayList;
import java.util.List;


public class ListActivity extends AppCompatActivity implements ClickListener {

    private String MY_LOG = "Kossa";

    private RecyclerView recyclerView;
    private BeerRecyclerViewAdapter adapter;
    public List<Beer> allBeers;
    public List<Beer> beerList;
    private ArrayList<String> namesList;
    private RecyclerView.LayoutManager layoutManager;
    private android.support.v7.widget.Toolbar toolbar;
    private ActionBar actionBar;
    private LayoutAnimationController layoutAnimationController;

    private  float density;
    private float name_width;
    private float name_height;
    private int screen_width;
    private float ratingBar_height;
    private SearchView searchView;

    private ImageView row_imageView;
    private TextView row_nanme_textView;
    private TextView row_brawery_textView;
    private RatingBar row_ratingBar;
    private TextView row_style_textView;
    private TextView row_price_textView;
    private LinearLayout row_sp_ll;
    private LinearLayout row_pia_ll;


    private int DURATION = 200;
    private int SHORT_DURATION = 100;
    private ValueAnimator imageView_animator;
    private ValueAnimator textSize_animator_15_25;
    private ValueAnimator name_x_change;
    private ValueAnimator brawery_x_change;
    private ValueAnimator brawery_y_change;
    private ValueAnimator ratingbar_x_change;
    private ValueAnimator ratingbar_y_change;
    private ScaleAnimation ratingBar_scale_anim;
    private ValueAnimator textSize_animator_10_15;
    private ValueAnimator sp_ll_x_change;
    private ValueAnimator pia_ll_y_change;
    private ValueAnimator pia_ll_height;
    private AlphaAnimation pia_ll_alpha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        toolbar = findViewById(R.id.recyclerList_toolbar);
        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        beerList = new ArrayList<Beer>();
        allBeers = new ArrayList<Beer>();

        density = getApplicationContext().getResources().getDisplayMetrics().density;

        ///Geting Beers List!!
        beerList = new ConnectorFacade().getAll();
        allBeers.addAll(beerList);
        Log.d(MY_LOG, "118");
        layoutAnimationController = AnimationUtils.loadLayoutAnimation(
                getApplicationContext(), R.anim.row_anim);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        adapter = new BeerRecyclerViewAdapter((ArrayList<Beer>) beerList, density);
        layoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setLayoutAnimation(layoutAnimationController);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(
                new BeerRecyclerViewItemListener(
                        getApplicationContext(), recyclerView, this));

        adapter.notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_list_actions, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        if (searchView != null){
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            searchView.setIconifiedByDefault(false);
            SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    findBeerByNameOrBrewery(query.toLowerCase());
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    findBeerByNameOrBrewery(newText.toLowerCase());
                    return false;
                }
            };
            searchView.setOnQueryTextListener(queryTextListener);
        }
        return super.onCreateOptionsMenu(menu);
    }

    private void findBeerByNameOrBrewery(String query) {

        beerList.clear();
        if(!query.isEmpty()) {
            for (int i = 0; i < allBeers.size(); i++) {
                if (allBeers.get(i).getName().toLowerCase().contains(query)
                        || allBeers.get(i).getBrewery().toLowerCase().contains(query)) {
                    beerList.add(allBeers.get(i));
                }
            }
        }
        adapter.notifyDataSetChanged();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(getApplicationContext(),"CLick", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLongClick(final View row) {

        row_imageView = row.findViewById(R.id.row_imageView);

        imageView_animator = ValueAnimator.ofInt((int) convertDpToPx(75), (int) convertDpToPx(150));
        imageView_animator.setDuration(DURATION);
        imageView_animator.setInterpolator(new AccelerateDecelerateInterpolator());
        imageView_animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (int) valueAnimator.getAnimatedValue();
                row_imageView.getLayoutParams().height = value;
                row_imageView.getLayoutParams().width = value;
                row_imageView.requestLayout();
            }
        });

        row_nanme_textView = row.findViewById(R.id.row_name_textView);
        row_brawery_textView = row.findViewById(R.id.row_brewery_textView);

        textSize_animator_15_25 = ValueAnimator.ofInt(15, 25);
        textSize_animator_15_25.setDuration(DURATION);
        textSize_animator_15_25.setInterpolator(new AccelerateDecelerateInterpolator());
        textSize_animator_15_25.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (int) valueAnimator.getAnimatedValue();
                row_nanme_textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,value);
                row_nanme_textView.requestLayout();
                row_brawery_textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, value);
                row_brawery_textView.requestLayout();
            }
        });

        name_x_change = ValueAnimator.ofInt((int) convertDpToPx(99), (int) convertDpToPx(174));
        name_x_change.setDuration(DURATION);
        name_x_change.setInterpolator(new AccelerateDecelerateInterpolator());
        name_x_change.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (int) valueAnimator.getAnimatedValue();
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) row_nanme_textView.getLayoutParams();
                layoutParams.leftMargin = value;
                row_nanme_textView.setLayoutParams(layoutParams);
                row_nanme_textView.requestLayout();
            }
        });

        name_width = row_nanme_textView.getMeasuredWidth();
        name_height = row_nanme_textView.getMeasuredHeight();

        brawery_x_change = ValueAnimator.ofInt(
                (int) (convertDpToPx(115) + name_width), (int) convertDpToPx(174));
        brawery_x_change.setDuration(SHORT_DURATION);
        brawery_x_change.setInterpolator(new AccelerateDecelerateInterpolator());
        brawery_x_change.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (int) valueAnimator.getAnimatedValue();
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) row_brawery_textView.getLayoutParams();
                layoutParams.leftMargin = value;
                row_brawery_textView.setLayoutParams(layoutParams);
                row_brawery_textView.requestLayout();
            }
        });

        brawery_y_change = ValueAnimator.ofInt(
                (int) convertDpToPx(8), (int) (convertDpToPx(54)));
        brawery_y_change.setDuration(SHORT_DURATION);
        brawery_y_change.setInterpolator(new AccelerateDecelerateInterpolator());
        brawery_y_change.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (int) valueAnimator.getAnimatedValue();
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) row_brawery_textView.getLayoutParams();
                layoutParams.topMargin = value;
                row_brawery_textView.setLayoutParams(layoutParams);
                row_brawery_textView.requestLayout();
            }
        });
        brawery_y_change.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                brawery_x_change.start();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

        row_ratingBar = row.findViewById(R.id.row_ratingbar);
        ratingBar_height = row_ratingBar.getHeight();

        ratingBar_scale_anim = new ScaleAnimation(1.0f, 2.0f, 1.0f, 2.0f, 0.0f, 0.0f);
        ratingBar_scale_anim.setDuration(SHORT_DURATION);
        ratingBar_scale_anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                row_ratingBar.setScaleY(2.0f);
                row_ratingBar.setScaleX(2.0f);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        ratingbar_y_change = ValueAnimator.ofInt((int) convertDpToPx(44), (int) convertDpToPx(174));
        ratingbar_y_change.setDuration(DURATION);
        ratingbar_y_change.setInterpolator(new AccelerateDecelerateInterpolator());
        ratingbar_y_change.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (int) valueAnimator.getAnimatedValue();
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) row_ratingBar.getLayoutParams();
                layoutParams.topMargin = value;
                row_ratingBar.setLayoutParams(layoutParams);
                row_ratingBar.requestLayout();
            }
        });

        screen_width = getWindowManager().getDefaultDisplay().getWidth();

        ratingbar_x_change = ValueAnimator.ofInt((int) convertDpToPx(99), (int)((screen_width - row_ratingBar.getWidth()) / 2));
        ratingbar_x_change.setDuration(DURATION);
        ratingbar_x_change.setInterpolator(new AccelerateDecelerateInterpolator());
        ratingbar_x_change.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (int) valueAnimator.getAnimatedValue();
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) row_ratingBar.getLayoutParams();
                layoutParams.leftMargin = value;
                row_ratingBar.setLayoutParams(layoutParams);
                row_ratingBar.requestLayout();
            }
        });

        row_style_textView = row.findViewById(R.id.row_style_textView);
        row_price_textView = row.findViewById(R.id.row_price_textView);

        textSize_animator_10_15 = ValueAnimator.ofInt(10, 15);
        textSize_animator_10_15.setDuration(DURATION);
        textSize_animator_10_15.setInterpolator(new AccelerateDecelerateInterpolator());
        textSize_animator_10_15.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (int) valueAnimator.getAnimatedValue();
                row_style_textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, value);
                row_price_textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, value);
            }
        });

        row_sp_ll = row.findViewById(R.id.row_style_price_LL);

        sp_ll_x_change = ValueAnimator.ofInt(
                (int) (name_height + ratingBar_height + convertDpToPx(40)), (int) convertDpToPx(220));
        sp_ll_x_change.setDuration(DURATION);
        sp_ll_x_change.setInterpolator(new AccelerateDecelerateInterpolator());
        sp_ll_x_change.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (int) valueAnimator.getAnimatedValue();
                FrameLayout.LayoutParams margins = (FrameLayout.LayoutParams) row_sp_ll.getLayoutParams();
                margins.topMargin = value;
                row_sp_ll.setLayoutParams(margins);
                row_sp_ll.requestLayout();
            }
        });

        row_pia_ll = row.findViewById(R.id.row_place_ibu_alc_LL);

        pia_ll_y_change = ValueAnimator.ofInt((int) convertDpToPx(89), (int) (convertDpToPx(256)));
        pia_ll_y_change.setDuration(DURATION);
        pia_ll_y_change.setInterpolator(new AccelerateDecelerateInterpolator());
        pia_ll_y_change.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (int) valueAnimator.getAnimatedValue();
                FrameLayout.LayoutParams margins = (FrameLayout.LayoutParams) row_pia_ll.getLayoutParams();
                margins.topMargin = value;
                row_pia_ll.setLayoutParams(margins);
                row_pia_ll.requestLayout();
            }
        });

        pia_ll_alpha = new AlphaAnimation(0.0f, 1.0f);
        pia_ll_alpha.setDuration(DURATION);
        pia_ll_alpha.setInterpolator(new AccelerateDecelerateInterpolator());
        pia_ll_alpha.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                row_pia_ll.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        pia_ll_height = ValueAnimator.ofInt(0, (int) convertDpToPx(40));
        pia_ll_height.setDuration(DURATION);
        pia_ll_height.setInterpolator(new AccelerateDecelerateInterpolator());
        pia_ll_height.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (int) valueAnimator.getAnimatedValue();
                FrameLayout.LayoutParams margins = (FrameLayout.LayoutParams) row_pia_ll.getLayoutParams();
                margins.height = value;
                row_pia_ll.setLayoutParams(margins);
                row_pia_ll.requestLayout();
            }
        });

        pia_ll_height.start();
        row_pia_ll.startAnimation(pia_ll_alpha);
        pia_ll_y_change.start();
        sp_ll_x_change.start();
        textSize_animator_10_15.start();
        row_ratingBar.startAnimation(ratingBar_scale_anim);
        ratingbar_y_change.start();
        ratingbar_x_change.start();
        name_x_change.start();
        brawery_y_change.start();
        textSize_animator_15_25.start();
        imageView_animator.start();
    }

    @Override
    public void onLongClickEnd(final View row) {

        //Zmiania wielkości imageView
        row_imageView = row.findViewById(R.id.row_imageView);
        imageView_animator = ValueAnimator.ofInt((int) convertDpToPx(150), (int) convertDpToPx(75));
        imageView_animator.setDuration(DURATION);
        imageView_animator.setInterpolator(new AccelerateDecelerateInterpolator());
        imageView_animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (int) valueAnimator.getAnimatedValue();
                row_imageView.getLayoutParams().height = value;
                row_imageView.getLayoutParams().width = value;
                row_imageView.requestLayout();
            }
        });

        row_nanme_textView = row.findViewById(R.id.row_name_textView);
        row_brawery_textView = row.findViewById(R.id.row_brewery_textView);

        textSize_animator_15_25 = ValueAnimator.ofInt(25, 15);
        textSize_animator_15_25.setDuration(DURATION);
        textSize_animator_15_25.setInterpolator(new AccelerateDecelerateInterpolator());
        textSize_animator_15_25.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (int) valueAnimator.getAnimatedValue();
                row_nanme_textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, value);
                row_nanme_textView.requestLayout();
                row_brawery_textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, value);
                row_brawery_textView.requestLayout();
            }
        });

        name_x_change = ValueAnimator.ofInt((int) convertDpToPx(174), (int) convertDpToPx(99));
        name_x_change.setDuration(DURATION);
        name_x_change.setInterpolator(new AccelerateDecelerateInterpolator());
        name_x_change.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (int) valueAnimator.getAnimatedValue();
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) row_nanme_textView.getLayoutParams();
                layoutParams.leftMargin = value;
                row_nanme_textView.setLayoutParams(layoutParams);
                row_nanme_textView.requestLayout();
            }
        });

        brawery_x_change = ValueAnimator.ofInt(
                (int) convertDpToPx(174), (int) (convertDpToPx(115) + name_width));
        brawery_x_change.setDuration(SHORT_DURATION);
        brawery_x_change.setInterpolator(new AccelerateDecelerateInterpolator());
        brawery_x_change.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (int) valueAnimator.getAnimatedValue();
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) row_brawery_textView.getLayoutParams();
                layoutParams.leftMargin = value;
                row_brawery_textView.setLayoutParams(layoutParams);
                row_brawery_textView.requestLayout();
            }
        });

        brawery_y_change = ValueAnimator.ofInt(
                (int) (convertDpToPx(54)), (int) convertDpToPx(8));
        brawery_y_change.setDuration(SHORT_DURATION);
        brawery_y_change.setInterpolator(new AccelerateDecelerateInterpolator());
        brawery_y_change.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (int) valueAnimator.getAnimatedValue();
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) row_brawery_textView.getLayoutParams();
                layoutParams.topMargin = value;
                row_brawery_textView.setLayoutParams(layoutParams);
                row_brawery_textView.requestLayout();
            }
        });
        brawery_y_change.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                brawery_x_change.start();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

        row_ratingBar = row.findViewById(R.id.row_ratingbar);
        ratingBar_scale_anim = new ScaleAnimation(2.0f, 1.0f, 2.0f, 1.0f, 0.0f, 0.0f);
        ratingBar_scale_anim.setDuration(SHORT_DURATION);
        ratingBar_scale_anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                row_ratingBar.setScaleX(1.0f);
                row_ratingBar.setScaleY(1.0f);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        ratingbar_y_change = ValueAnimator.ofInt((int) convertDpToPx(174), (int) (convertDpToPx(44)));
        ratingbar_y_change.setDuration(DURATION);
        ratingbar_y_change.setInterpolator(new AccelerateDecelerateInterpolator());
        ratingbar_y_change.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (int) valueAnimator.getAnimatedValue();
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) row_ratingBar.getLayoutParams();
                layoutParams.topMargin = value;
                row_ratingBar.setLayoutParams(layoutParams);
                row_ratingBar.requestLayout();
            }
        });

        ratingbar_x_change = ValueAnimator.ofInt((int)((screen_width - row_ratingBar.getWidth()) / 2),(int) convertDpToPx(99));
        ratingbar_x_change.setDuration(DURATION);
        ratingbar_x_change.setInterpolator(new AccelerateDecelerateInterpolator());
        ratingbar_x_change.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (int) valueAnimator.getAnimatedValue();
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) row_ratingBar.getLayoutParams();
                layoutParams.leftMargin = value;
                row_ratingBar.setLayoutParams(layoutParams);
                row_ratingBar.requestLayout();
            }
        });

        row_style_textView = row.findViewById(R.id.row_style_textView);
        row_price_textView = row.findViewById(R.id.row_price_textView);

        textSize_animator_10_15 = ValueAnimator.ofInt(15, 10);
        textSize_animator_10_15.setDuration(DURATION);
        textSize_animator_10_15.setInterpolator(new AccelerateDecelerateInterpolator());
        textSize_animator_10_15.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (int) valueAnimator.getAnimatedValue();
                row_style_textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, value);
                row_price_textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, value);
            }
        });

        row_sp_ll = row.findViewById(R.id.row_style_price_LL);

        sp_ll_x_change = ValueAnimator.ofInt(
                (int) convertDpToPx(220), (int) (convertDpToPx(60)));
        sp_ll_x_change.setDuration(DURATION);
        sp_ll_x_change.setInterpolator(new AccelerateDecelerateInterpolator());
        sp_ll_x_change.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (int) valueAnimator.getAnimatedValue();
                FrameLayout.LayoutParams margins = (FrameLayout.LayoutParams) row_sp_ll.getLayoutParams();
                margins.topMargin = value;
                row_sp_ll.setLayoutParams(margins);
                row_sp_ll.requestLayout();
            }
        });

        row_pia_ll = row.findViewById(R.id.row_place_ibu_alc_LL);

        pia_ll_y_change = ValueAnimator.ofInt((int) convertDpToPx(256), (int) (convertDpToPx(89)));
        pia_ll_y_change.setDuration(DURATION);
        pia_ll_y_change.setInterpolator(new AccelerateDecelerateInterpolator());
        pia_ll_y_change.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (int) valueAnimator.getAnimatedValue();
                FrameLayout.LayoutParams margins = (FrameLayout.LayoutParams) row_pia_ll.getLayoutParams();
                margins.topMargin = value;
                row_pia_ll.setLayoutParams(margins);
                row_pia_ll.requestLayout();
            }
        });

        pia_ll_alpha = new AlphaAnimation(1.0f, 0.0f);
        pia_ll_alpha.setDuration(DURATION);
        pia_ll_alpha.setInterpolator(new AccelerateDecelerateInterpolator());
        pia_ll_alpha.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                row_pia_ll.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        pia_ll_height = ValueAnimator.ofInt((int) convertDpToPx(40), 0);
        pia_ll_height.setDuration(DURATION);
        pia_ll_height.setInterpolator(new AccelerateDecelerateInterpolator());
        pia_ll_height.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (int) valueAnimator.getAnimatedValue();
                FrameLayout.LayoutParams margins = (FrameLayout.LayoutParams) row_pia_ll.getLayoutParams();
                margins.height = value;
                row_pia_ll.setLayoutParams(margins);
                row_pia_ll.requestLayout();
            }
        });


        pia_ll_height.start();
        row_pia_ll.startAnimation(pia_ll_alpha);
        pia_ll_y_change.start();
        sp_ll_x_change.start();
        textSize_animator_10_15.start();
        row_ratingBar.startAnimation(ratingBar_scale_anim);
        ratingbar_y_change.start();
        ratingbar_x_change.start();
        name_x_change.start();
        brawery_y_change.start();
        textSize_animator_15_25.start();
        imageView_animator.start();

    }

    public float convertDpToPx(float dp){
        float px = dp * density;
        return px;
    }

}
