package com.example.a533.shopnshop;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

    Button btnCompleteOrder;
    Button btnCompleteOrderLater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makeanorder);
        dbShop = new SQLite(this);
        itemsToSell = dbShop.getItemsToSell();
        itemsToSellOrder = new ArrayList<>();
        recyclerItemsToOrder = findViewById(R.id.recyclerItemsToOrder);
        recyclerItemsCurrentOrder = findViewById(R.id.recyclerCurrentOrder);
        btnCompleteOrder = findViewById(R.id.btnCompleteOrder);
        btnCompleteOrderLater = findViewById(R.id.btnCompleteOrderLater);

        recyclerItemsCurrentOrder.setHasFixedSize(true);
        LinearLayoutManager allo = new LinearLayoutManager(this);
        recyclerItemsCurrentOrder.setLayoutManager(allo);
        dataCurrentOrder= new ItemCurrentOrder(itemsToSellOrder, new onDeleteItem() {
            @Override
            public void deleteItem(int positionToRemove) {
                itemsToSellOrder.remove(positionToRemove);
                recyclerItemsCurrentOrder.getAdapter().notifyItemRemoved(positionToRemove);
            }
        });
        recyclerItemsCurrentOrder.setAdapter(dataCurrentOrder);

        setListeners();
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
                    InsertItemToOrder(Integer.parseInt(itemsToSell.get(idItem).getIdItem()), quantity);
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
        Log.d("idItem", String.valueOf(idItem));
        Log.d("quantity", String.valueOf(quantity));
        if (quantity <= dbShop.getQuantity(idItem))
        {
            itemsToSellOrder.add(new ItemToSell(dbShop.getObject(idItem),String.valueOf(quantity), String.valueOf(idItem)));
            dataCurrentOrder.notifyDataSetChanged();
        }
        else
        {
            Toast.makeText(this,"Quantity too high", Toast.LENGTH_SHORT).show();
        }
    }

    private void setListeners()
    {
        btnCompleteOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int cptItem = 0; cptItem < itemsToSellOrder.size(); cptItem ++)
                {
                    dbShop.updateItemQuantity(itemsToSellOrder.get(cptItem));
                }
                dbShop.InsertOrder("123ChaChaCha", getIntent().getStringExtra("Username").toString(), true);
            }
        });
        btnCompleteOrderLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbShop.InsertOrder("123ChaChaCha", getIntent().getStringExtra("Username").toString(), false);
            }
        });
    }

    public interface onBuy
    {
        void addToOrder(int idItem, int quantity);
    }

    public interface onDeleteItem
    {
        void deleteItem(int positionToRemove);
    }


}
