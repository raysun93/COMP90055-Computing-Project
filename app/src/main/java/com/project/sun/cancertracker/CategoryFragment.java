package com.project.sun.cancertracker;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.sun.cancertracker.Global.Common;
import com.project.sun.cancertracker.Interface.ItemClickListener;
import com.project.sun.cancertracker.ViewHolder.CategoryViewHolder;
import com.project.sun.cancertracker.models.Category;
import com.project.sun.cancertracker.models.User;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class CategoryFragment extends Fragment {

    private View myFragment;
    private RecyclerView listCategory;
    private RecyclerView.LayoutManager layoutManager;
    private FirebaseRecyclerAdapter<Category,CategoryViewHolder> adapter;

    private FirebaseDatabase database;
    private DatabaseReference categories;
    private DatabaseReference Qol;
    private DatabaseReference VitalLife;

    public static CategoryFragment newInstance(){
        CategoryFragment categoryFragment = new CategoryFragment();
        return categoryFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        database = FirebaseDatabase.getInstance();
        categories = database.getReference("Category");
        Qol = database.getReference("Qol");
        VitalLife = database.getReference("LifeVital").child(Common.currentUser.getUserName());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myFragment = inflater.inflate(R.layout.fragment_category, container, false);

        listCategory = myFragment.findViewById(R.id.listCategory);
        listCategory.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(container.getContext());
        listCategory.setLayoutManager(layoutManager);

        loadCategories();
        return myFragment;
    }

    private void loadCategories() {
        adapter = new FirebaseRecyclerAdapter<Category, CategoryViewHolder>(
                Category.class,
                R.layout.category_layout,
                CategoryViewHolder.class,
                categories
        ) {
            @Override
            protected void populateViewHolder(CategoryViewHolder viewHolder, final Category model, int position) {
                viewHolder.category_name.setText(model.getName());
                Picasso.with(getActivity()).load(model.getImage()).into(viewHolder.category_image);

                viewHolder.setItemClickListener(new ItemClickListener() {
                    @SuppressLint("DefaultLocale")
                    @Override
                    public void onClick(View view, int position, boolean IsLongClick) {

                        if(adapter.getRef(position).getKey().equals("01")) {
                            Common.currentApp = "Poo";
                            Intent BristolMain = new Intent(getActivity(),BristolMainActivity.class);
                            startActivity(BristolMain);
                        }else if(adapter.getRef(position).getKey().equals("02")){
                            Common.currentApp = "Qol";
                            Date currentDate = Calendar.getInstance().getTime();
                            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.UK);
                            final String date =df.format(currentDate);
                            final User currentUser = Common.currentUser;
                            Qol.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    if(dataSnapshot.child(currentUser.getUserName()).child(date).exists()){
                                        Toast.makeText(getActivity(),"You have finished today's Qol Survey",Toast.LENGTH_SHORT).show();
                                    }else{
                                        Intent QolMain = new Intent(getActivity(),QolActivity.class);
                                        startActivity(QolMain);
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                        }else if(adapter.getRef(position).getKey().equals("03")) {
                            Common.currentApp = "Vital_life";
                            Date currentDate = Calendar.getInstance().getTime();
                            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.UK);
                            final String date =df.format(currentDate);
                            VitalLife.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    if(dataSnapshot.child(date).exists()){
                                        Toast.makeText(getActivity(),"You have finished today's life vital survey",Toast.LENGTH_SHORT).show();
                                    }else{
                                        Intent VitalMain = new Intent(getActivity(),VitalMainActivity.class);
                                        startActivity(VitalMain);
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });

                        }
                    }
                });
            }
        };
        adapter.notifyDataSetChanged();
        listCategory.setAdapter(adapter);
    }
}
