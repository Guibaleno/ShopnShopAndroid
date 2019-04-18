package com.example.a533.shopnshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MakeAnorder extends AppCompatActivity {
    RecyclerView recyclerItemsToOrder;
    SQLite dbShop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makeanorder);

        recyclerItemsToOrder = findViewById(R.id.recyclerItemsToOrder);
        InsertDataIntoRecyclerView();
    }

    private void InsertDataIntoRecyclerView()
    {
       recyclerItemsToOrder.setHasFixedSize(true);
       LinearLayoutManager allo = new LinearLayoutManager(this);
       recyclerItemsToOrder.setLayoutManager(allo);
       List<String> itemsToSell = new ArrayList<String>();
       itemsToSell.add("test");
       itemsToSell.add("test2");
       RecyclerView.Adapter data = new recycleViewItem(itemsToSell);
       recyclerItemsToOrder.setAdapter(data);

    }
}
