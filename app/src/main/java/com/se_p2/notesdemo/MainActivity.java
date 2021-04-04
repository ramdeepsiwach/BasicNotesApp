package com.se_p2.notesdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {
    ListView listView;
    static ArrayList<String> notes=new ArrayList<>();
    static ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.listView);
        adapter=new ArrayAdapter<>(this,R.layout.list_view,notes);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getApplicationContext(),EditorActivity.class);
                intent.putExtra("noteId",i);
                startActivity(intent);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id)
            {
                AlertDialog.Builder alertDialog= new AlertDialog.Builder(MainActivity.this);
                alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
                alertDialog.setTitle("Delete?");
                alertDialog.setMessage("Are you sure you want to delete this note?");
                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                            {
                                notes.remove(position);
                                adapter.notifyDataSetChanged();
                            }
                        });
                alertDialog.setNegativeButton("No", null);
                alertDialog.show();
                return true;
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.option_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        if(item.getItemId()==R.id.addNote){
            Intent intent=new Intent(getApplicationContext(),EditorActivity.class);
            startActivity(intent);
            return true;
        }else if((item.getItemId()==R.id.clearAll)){
            AlertDialog.Builder alertDialog= new AlertDialog.Builder(MainActivity.this);
            alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
            alertDialog.setTitle("Clear All");
            alertDialog.setMessage("Are you sure you want to delete all the notes ?");
            alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    notes.clear();
                    adapter.notifyDataSetChanged();
                }
            });
            alertDialog.setNegativeButton("No", null);
            alertDialog.show();
        }
        return false;
    }
}