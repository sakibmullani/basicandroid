package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.myapplication.api_calling.MainActivity_API;
import com.example.myapplication.api_calling.Post_API_Activity;
import com.example.myapplication.api_calling.ToolTip_activity_Main;
import com.example.myapplication.api_calling.Update_API_Activity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
     

   String ROOT_FRAGMENT_TAG ="root_Fragment";
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    BottomNavigationView bottomNavigationView;
    RecyclerView recyclerViewId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//       getSupportFragmentManager().beginTransaction().add(R.id.container,new firstFragment()).commit();

        drawerLayout=findViewById(R.id.drawerLayout);
        navigationView=findViewById(R.id.navigationView);
        toolbar=findViewById(R.id.toolBar);
        bottomNavigationView=findViewById(R.id.bottomView);
        recyclerViewId=findViewById(R.id.recycleViewId);


        //Step 1

        setSupportActionBar(toolbar);



        ActionBarDrawerToggle toggle =new ActionBarDrawerToggle(
                this, drawerLayout,toolbar,R.string.openDraw,R.string.closeDraw);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));


        //Fragment load first HOME

//        FragmentManager fm =getSupportFragmentManager();
//        FragmentTransaction ft =fm.beginTransaction();
//        ft.add(R.id.container, new HomeFragment());
//        ft.addToBackStack(null);
//        ft.commit();


        loadFragment(new firstFragment(), 0);   //load Fragment Home Default



        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id=item.getItemId();

                if (id==R.id.optHome){
                    loadFragment(new HomeFragment(),1);

                }
                else if (id==R.id.optAbout){
                    loadFragment(new about_fragment(),1);

                }
                else if (id==R.id.optContact){

                    loadFragment(new firstFragment(),0);
                }
                else if (id==R.id.optRecyclerView){

                    startActivity(new Intent(MainActivity.this, recyclerView.class));

                }
                else if (id==R.id.optNestedRecyclerView){

                    startActivity(new Intent(MainActivity.this, Nested_RecyclerView_Main.class));

                }
                else if (id==R.id.optPagination){

                    startActivity(new Intent(MainActivity.this, Pagination_Main.class));

                }
                else if (id==R.id.optViewBinding){

                    startActivity(new Intent(MainActivity.this, ViewBinding_main.class));

                }
                else if (id==R.id.optGallery){

                    startActivity(new Intent(MainActivity.this, Gallery.class));

                }
                else if (id==R.id.optGetAPI){

                    startActivity(new Intent(MainActivity.this, MainActivity_API.class));

                }
                else if (id==R.id.optPostAPI){

                    startActivity(new Intent(MainActivity.this, Post_API_Activity.class));

                }
                else if (id==R.id.optPutAPI){

                    startActivity(new Intent(MainActivity.this, Update_API_Activity.class));

                }
                else if (id==R.id.optToolTip){

                    startActivity(new Intent(MainActivity.this, ToolTip_activity_Main.class));

                }
                else {
                    loadFragment(new firstFragment(),0);

                }

                drawerLayout.closeDrawer(GravityCompat.START);

                return true;
            }
        });

        bottomNavigationView.setSelectedItemId(R.id.optContact2);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                int id=item.getItemId();

                if (id==R.id.optHome2){

                    loadFragment(new HomeFragment(),1);
                }
                else if (id==R.id.optContact2){
                    loadFragment(new firstFragment(), 0);

                }
                else if (id==R.id.optAbout2){
                    loadFragment(new about_fragment(),1);

                }


                return true;
            }
        });


    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);

        }
        else{
            super.onBackPressed();
        }
    }

    private void loadFragment(Fragment fragment, int flag) {

        FragmentManager fm =getSupportFragmentManager();
        FragmentTransaction ft =fm.beginTransaction();

        if(flag==0) {
            ft.add(R.id.container, fragment);
            fm.popBackStack(ROOT_FRAGMENT_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            ft.addToBackStack(ROOT_FRAGMENT_TAG);
        }
        else {
            ft.replace(R.id.container, fragment);
            ft.addToBackStack(null);
        }

            ft.commit();



    }


}
