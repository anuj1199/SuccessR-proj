package com.vinu.internship;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class OnBoardingScreen extends AppCompatActivity {
    ViewPager viewPager;
    LinearLayout dotsLayout;
    SliderAdapter sliderAdapter;
    TextView[] dots;
    Button letsgetstarted,skip,next;
    Animation animation;
    int currentpos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding_screen);
        viewPager = findViewById(R.id.slider);
        dotsLayout = findViewById(R.id.dots);
        letsgetstarted = findViewById(R.id.get_started_btn);
        next = findViewById(R.id.next_btn);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(currentpos + 1);

            }
        });
        letsgetstarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(OnBoardingScreen.this,R.style.AlertDialogTheme);
                View view = LayoutInflater.from(OnBoardingScreen.this).inflate(R.layout.layout_warning_dialog,(ConstraintLayout)findViewById(R.id.layoutdialogcontainer));
                builder.setView(view);

                ((TextView) view.findViewById(R.id.texttitle)).setText(getResources().getString(R.string.success_title));

                ((TextView) view.findViewById(R.id.textmessage)).setTextColor(getResources().getColor(R.color.colorwhite));
                ((TextView) view.findViewById(R.id.textmessage)).setText(getResources().getString(R.string.dummy_text));
                ((Button) view.findViewById(R.id.buttonemployee)).setText(getResources().getString(R.string.employee));
                ((Button) view.findViewById(R.id.buttonintern)).setText(getResources().getString(R.string.intern));
                ((Button) view.findViewById(R.id.buttonemployerr)).setText(getResources().getString(R.string.employer));
                ((ImageView) view.findViewById(R.id.imageicon)).setImageResource(R.drawable.ic_warning);
                final AlertDialog alertDialog = builder.create();
                view.findViewById(R.id.buttonemployee).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                        Intent intent = new Intent(OnBoardingScreen.this,employeelogin.class);
                        startActivity(intent);
                    }
                });
                view.findViewById(R.id.buttonemployerr).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                        Intent intent = new Intent(OnBoardingScreen.this,employeerlogin.class);
                        startActivity(intent);

                    }
                });
                view.findViewById(R.id.buttonintern).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                        Intent intent = new Intent(OnBoardingScreen.this,Internlogin.class);
                        startActivity(intent);

                    }
                });
                if(alertDialog.getWindow()!=null)
                {
                    alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                }
                alertDialog.show();


            }
        });
        skip = findViewById(R.id.skip_btn_onboarding);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(OnBoardingScreen.this,R.style.AlertDialogTheme);
                View view = LayoutInflater.from(OnBoardingScreen.this).inflate(R.layout.layout_warning_dialog,(ConstraintLayout)findViewById(R.id.layoutdialogcontainer));
                builder.setView(view);
                ((TextView) view.findViewById(R.id.texttitle)).setText(getResources().getString(R.string.success_title));
                ((TextView) view.findViewById(R.id.textmessage)).setText(getResources().getString(R.string.dummy_text));
                ((Button) view.findViewById(R.id.buttonemployee)).setText(getResources().getString(R.string.employee));
                ((Button) view.findViewById(R.id.buttonemployerr)).setText(getResources().getString(R.string.employer));
                ((Button) view.findViewById(R.id.buttonintern)).setText(getResources().getString(R.string.intern));
                ((ImageView) view.findViewById(R.id.imageicon)).setImageResource(R.drawable.ic_warning);
                final AlertDialog alertDialog = builder.create();
                view.findViewById(R.id.buttonemployee).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                        Intent intent = new Intent(OnBoardingScreen.this,employeelogin.class);
                        startActivity(intent);
                    }
                });
                view.findViewById(R.id.buttonemployerr).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                        Intent intent = new Intent(OnBoardingScreen.this,employeerlogin.class);
                        startActivity(intent);

                    }
                });
                view.findViewById(R.id.buttonintern).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                        Intent intent = new Intent(OnBoardingScreen.this,Internlogin.class);
                        startActivity(intent);

                    }
                });
                if(alertDialog.getWindow()!=null)
                {
                    alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                }
                alertDialog.show();

            }
        });
        sliderAdapter = new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);
        addDots(0);
        viewPager.addOnPageChangeListener(changeListener);
    }

    private void addDots(int position)
    {
        dots = new TextView[8];
        dotsLayout.removeAllViews();
        for(int i =0;i<dots.length;i++)
        {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dotsLayout.addView(dots[i]);
        }
        if(dots.length>0)
        {
            dots[position].setTextColor(getResources().getColor(R.color.colorred));
        }

    }
    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);
            currentpos = position;
            if(position == 0)
            {
                letsgetstarted.setVisibility(View.INVISIBLE);
            }
            else if(position == 1)
            {
                letsgetstarted.setVisibility(View.INVISIBLE);
            }
            else if(position == 2)
            {
                letsgetstarted.setVisibility(View.INVISIBLE);
            }
            else if(position == 3)
            {
                letsgetstarted.setVisibility(View.INVISIBLE);
            }
            else if(position == 4)
            {
                letsgetstarted.setVisibility(View.INVISIBLE);
            }
            else if(position == 5)
            {
                letsgetstarted.setVisibility(View.INVISIBLE);
            }
            else if(position == 6)
            {
                letsgetstarted.setVisibility(View.INVISIBLE);
            }

            else
            {   animation = AnimationUtils.loadAnimation(OnBoardingScreen.this,R.anim.bottom_anim);
                letsgetstarted.setAnimation(animation);
                letsgetstarted.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}