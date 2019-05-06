package com.example.a533.shopnshop;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    Toolbar myToolbar;

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
        btnCompleteOrder.setEnabled(false);
        btnCompleteOrderLater = findViewById(R.id.btnCompleteOrderLater);
        btnCompleteOrderLater.setEnabled(false);

        myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Deconnexion();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        // ...
//
        // Define the listener
        MenuItem.OnActionExpandListener expandListener = new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                // Do something when action item collapses
                return true;  // Return true to collapse action view
            }
            //
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                // Do something when expanded
                return true;  // Return true to expand action view
            }


        };

        // Get the MenuItem for the action item
        MenuItem actionMenuItem = menu.findItem(R.id.toolbar);
//
        // Assign the listener to that action item

        //actionMenuItem.setOnActionExpandListener(MenuItem.OnActionExpandListener);
//
        // Any other things you have to do when creating the options menu...
//
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Disconnection.getDisconnection() == true) {
            this.finish();
        }

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
                    btnCompleteOrder.setEnabled(true);
                    btnCompleteOrderLater.setEnabled(true);
                }
            });
            recyclerItemsToOrder.setAdapter(data);
        }

    }

    public void InsertItemToOrder(int idItem, int quantity)
    {
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
                AlertDialog.Builder builder = new AlertDialog.Builder(MakeAnorder.this);
                builder.setTitle("Choose a name for your order");

                final EditText input = new EditText(MakeAnorder.this);

                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("Size", String.valueOf(itemsToSellOrder.size()));
                        for (int cptItem = 0; cptItem < itemsToSellOrder.size(); cptItem ++)
                        {
                            dbShop.updateItemQuantity(itemsToSellOrder.get(cptItem));
                            itemsToSellOrder.remove(cptItem);
                        }
                        dbShop.InsertOrder(input.getText().toString(), getIntent().getStringExtra("Username").toString(), true);
                        btnCompleteOrder.setEnabled(false);
                        btnCompleteOrderLater.setEnabled(false);
                        dataCurrentOrder.notifyDataSetChanged();

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });
        btnCompleteOrderLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MakeAnorder.this);
                builder.setTitle("Choose a name for your order");

                final EditText input = new EditText(MakeAnorder.this);

                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int cptItem = 0; cptItem < itemsToSellOrder.size(); cptItem ++)
                        {
                            dbShop.updateItemQuantity(itemsToSellOrder.get(cptItem));
                        }
                        dbShop.InsertOrder(input.getText().toString(), getIntent().getStringExtra("Username").toString(), false);
                        btnCompleteOrder.setEnabled(false);
                        btnCompleteOrderLater.setEnabled(false);
                        itemsToSellOrder = new ArrayList<>();
                        dataCurrentOrder.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
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

    private void Deconnexion(){
        this.finish();
        Disconnection.setDisconnection(true);
    }


}
