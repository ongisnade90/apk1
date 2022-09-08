package com.example.apk1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

public class MainActivity extends AppCompatActivity {

    TextView intentBerkasMasuk;
    TextView intentBerkasKeluar;

    Animation atg, atgtwo, atgthree, atgfour, atgfive;
    ImageView imageView;
    TextView textView2, textView3, textView4, textView5;
    MeowBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intentBerkasMasuk = findViewById(R.id.TextBerkasMasuk);
        intentBerkasMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent pindahkeBerkasMasuk = new Intent(MainActivity.this,berkasmasuk.class);
                startActivity(pindahkeBerkasMasuk);

            }
        });

        intentBerkasKeluar = findViewById(R.id.TextBerkasKeluar);
        intentBerkasKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent pindahkeBerkasKeluar = new Intent(MainActivity.this,berkaskeluar.class);
                startActivity(pindahkeBerkasKeluar);

            }
        });

        atg = AnimationUtils.loadAnimation(this, R.anim.atg);
        atgtwo = AnimationUtils.loadAnimation(this, R.anim.atgtwo);

        imageView = findViewById(R.id.imageView);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        textView5 = findViewById(R.id.textView5);

        //pass an animation
        imageView.startAnimation(atg);
        textView2.startAnimation(atgtwo);
        textView3.startAnimation(atgtwo);
        textView4.startAnimation(atgtwo);
        textView5.startAnimation(atgtwo);

        //assign variable
        bottomNavigation = findViewById(R.id.bottom_navigation);

        //menambahkan item menu
        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.home));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.notif));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.profile));

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                //inisialisasi fragmen
                Fragment fragment = null;
                //Cek kondisi
                switch (item.getId()) {
                    case 1:
                        //ketika id adalah 1
                        //inisialisasi home fragment
                        fragment = new Fragment();
                        break;
                    case 2:
                        //ketika id adalah 2
                        //inisialisasi notif fragment
                        fragment = new NotificationFragment();
                        break;
                    case 3:
                        //ketika id adalah 3
                        //inisialisasi profile fragment
                        fragment = new ProfileFragment();
                        break;
                }
                //Load Fragment
                loadFragment(fragment);
            }
        });

        //set home count
        bottomNavigation.setCount(2, "10");
        //set home fragment inisilisasi pilihan
        bottomNavigation.show(1, true);

        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {

            }
        });
    }

    private void loadFragment(Fragment fragment) {
        //replace fragment
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, fragment)
                .commit();
    }
}