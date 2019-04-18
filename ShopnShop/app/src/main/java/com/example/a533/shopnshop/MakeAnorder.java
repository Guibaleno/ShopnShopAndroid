package com.example.a533.shopnshop;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MakeAnorder extends AppCompatActivity {
    RecyclerView recyclerItemsToOrder;
    SQLite dbShop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makeanorder);
        dbShop = new SQLite(this);
        dbShop.CreateItems();
        recyclerItemsToOrder = findViewById(R.id.recyclerItemsToOrder);
        InsertDataIntoRecyclerView();
    }

    private void InsertDataIntoRecyclerView()
    {
       recyclerItemsToOrder.setHasFixedSize(true);
       LinearLayoutManager allo = new LinearLayoutManager(this);
       recyclerItemsToOrder.setLayoutManager(allo);
       List<String> itemsToSell = new ArrayList<String>();

       Cursor items = dbShop.GetItemsToSell();
       Log.d("asdj", String.valueOf(items.getCount()));
        if (items.getCount() != 0)
        {
            while (items.moveToNext())
            {
                itemsToSell.add(items.getString(0));
            }
            itemsToSell.add("test");
            itemsToSell.add("test2");
            RecyclerView.Adapter data = new recycleViewItem(itemsToSell);
            recyclerItemsToOrder.setAdapter(data);
        }
        else
        {

        }


    }
}
