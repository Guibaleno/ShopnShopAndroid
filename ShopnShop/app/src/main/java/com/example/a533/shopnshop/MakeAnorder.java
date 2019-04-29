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
    RecyclerView recyclerItemsCurrentOrder;
    SQLite dbShop;
    List<ItemToSell> itemsToSell;
    List<ItemToSell> itemsToSellOrder;
    RecyclerView.Adapter dataCurrentOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makeanorder);
        dbShop = new SQLite(this);
        itemsToSell = dbShop.getItemsToSell();
        itemsToSellOrder = new ArrayList<>();
        recyclerItemsToOrder = findViewById(R.id.recyclerItemsToOrder);
        recyclerItemsCurrentOrder = findViewById(R.id.recyclerCurrentOrder);

        recyclerItemsCurrentOrder.setHasFixedSize(true);
        LinearLayoutManager allo = new LinearLayoutManager(this);
        recyclerItemsCurrentOrder.setLayoutManager(allo);
        dataCurrentOrder= new ItemCurrentOrder(this, itemsToSellOrder);
        recyclerItemsCurrentOrder.setAdapter(dataCurrentOrder);


        InsertDataIntoRecyclerView();
    }

    private void InsertDataIntoRecyclerView()
    {
       recyclerItemsToOrder.setHasFixedSize(true);
       LinearLayoutManager allo = new LinearLayoutManager(this);
       recyclerItemsToOrder.setLayoutManager(allo);
        if (itemsToSell.size() != 0)
        {
            RecyclerView.Adapter data = new recycleViewItem(itemsToSell, new onBuy() {
                @Override
                public void addToOrder(int idItem, int quantity) {
                    InsertItemToOrder(Integer.parseInt(itemsToSell.get(0).getIdItem()), quantity);
                }
            });
            recyclerItemsToOrder.setAdapter(data);
        }
        else
        {

        }


    }

    public void InsertItemToOrder(int idItem, int quantity)
    {
        itemsToSellOrder.add(new ItemToSell(dbShop.getObject(idItem),String.valueOf(quantity), String.valueOf(idItem)));

        Log.d("idItem", String.valueOf(idItem));
        Log.d("quantity", String.valueOf(quantity));
        dataCurrentOrder.notifyDataSetChanged();
        //recyclerItemsCurrentOrder.notifyDataSetChanged();
    }

    public interface onBuy
    {
        void addToOrder(int idItem, int quantity);
    }


}
