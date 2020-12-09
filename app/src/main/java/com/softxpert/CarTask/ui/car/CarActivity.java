package com.softxpert.CarTask.ui.car;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.softxpert.CarTask.base.listeners.PaginationListener;
import com.softxpert.car.R;
import com.softxpert.CarTask.adapters.CarAdapter;
import com.softxpert.CarTask.model.Car;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CarActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "CarActivity";
    private CarViewModel viewModel;
    private RecyclerView recyclerView;
    private CarAdapter adapter;
    SwipeRefreshLayout swipeRefresh;

    private int currentPage = 1;
    private boolean isLastPage = false;
    private boolean isLoading = false;
    int itemCount = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUI();
        setupViewModel();

    }

    private void setupUI() {
        recyclerView = findViewById(R.id.pokemon_recyclerView);
        swipeRefresh = findViewById(R.id.swipe_view);
//        adapter = new CarAdapter(this);
//        recyclerView.setAdapter(adapter);


        swipeRefresh.setOnRefreshListener(this);
//        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CarAdapter(this);
        recyclerView.setAdapter(adapter);

        setupPagination(layoutManager);
    }

    private void setupViewModel() {
        viewModel = new ViewModelProvider(this).get(CarViewModel.class);

        viewModel.getCars(currentPage);

        viewModel.getCarList().observe(this, new Observer<ArrayList<Car>>() {
            @Override
            public void onChanged(ArrayList<Car> cars) {
                adapter.setList(cars);
            }
        });
    }

    private void setupPagination(LinearLayoutManager layoutManager){

        final boolean[] loading = {true};
        final int[] pastVisiblesItems = new int[1];
        final int[] visibleItemCount = new int[1];
        final int[] totalItemCount = new int[1];

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) { //check for scroll down



                    visibleItemCount[0] = layoutManager.getChildCount();
                    itemCount = layoutManager.getItemCount();
                    pastVisiblesItems[0] = layoutManager.findFirstVisibleItemPosition();

                    if (loading[0]) {
                        if ((visibleItemCount[0] + pastVisiblesItems[0]) >= totalItemCount[0]) {
                            loading[0] = false;

                            Log.e(TAG, "onScrolled: page ---> "+currentPage);
                            currentPage ++;
                            viewModel.getCars(currentPage);
                            Toast.makeText(CarActivity.this, "scroll", Toast.LENGTH_SHORT).show();

                            Log.v("...", "Last Item Wow !");

                            loading[0] = true;
                        }
                    }

                }
            }
        });

//        recyclerView.addOnScrollListener(new PaginationListener(layoutManager) {
//            @Override
//            protected void loadMoreItems() {
//                if(isLoading == true || isLastPage == true){
//                    Log.e(TAG, "loadMoreItems: isloading");
//                    return;
//                }
//                isLoading = true;
//
//                Log.e(TAG, "onScrolled: page ---> "+currentPage);
//                currentPage ++;
//                //viewModel.getCars(currentPage);
//                Toast.makeText(CarActivity.this, "scroll", Toast.LENGTH_SHORT).show();
//
//                //viewModel.getCars(currentPage);
//
//            }
//
//            @Override
//            public boolean isLastPage() {
//                return isLastPage;
//            }
//
//            @Override
//            public boolean isLoading() {
//                return isLoading;
//            }
//        });
    }


    @Override
    public void onRefresh() {
        itemCount = 0;
        currentPage = 1;
        isLastPage = false;
        adapter.clear();
        isLoading = true;
        viewModel.getCars(currentPage);
        swipeRefresh.setRefreshing(false);
    }



}