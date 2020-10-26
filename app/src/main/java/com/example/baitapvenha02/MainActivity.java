package com.example.baitapvenha02;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listViewSanPham;
    private List<SanPham> listSanPham = new ArrayList<SanPham>();
    private String [] dataSanPham ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listSanPham.add(new SanPham("123","Sữa",12500));
        listSanPham.add(new SanPham("124","Trà Xanh",20500));
        listSanPham.add(new SanPham("125","Trà Đào",22500));
        listSanPham.add(new SanPham("126","Trà Trân Châu",34500));
        listSanPham.add(new SanPham("127","Trà Hạt Sen",72500));
        listSanPham.add(new SanPham("128","Trà Hạnh Nhân",21500));
        updateListView();

    }
    public void updateListView(){
        dataSanPham = new String[listSanPham.size()];
        listViewSanPham = (ListView) findViewById(R.id.listViewSanPham);
        for(int i = 0; i<listSanPham.size() ; i++){
            SanPham sp = listSanPham.get(i);
            dataSanPham[i] = sp.getMaSanPham() + "              " + sp.getTenSanPham() +"              " + sp.getGia();
        }
        ArrayAdapter lisArrayAdapter = new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,dataSanPham);
        listViewSanPham.setAdapter(lisArrayAdapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    public void showDialogTimKiem(){
        LayoutInflater layoutInflater =LayoutInflater.from(MainActivity.this);
        View  view= layoutInflater.inflate(R.layout.dialog_timkiem,null);
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setView(view);
        final  EditText edtTenSanPhamTimKiem = (EditText)  view.findViewById(R.id.edtTimKiem);
        builder.setPositiveButton("Tìm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String tenSanPhamTimKiem = edtTenSanPhamTimKiem.getText().toString().toLowerCase();
                List<SanPham> listSpTimKiem =  new ArrayList<SanPham>();
                for (SanPham sanPham: listSanPham) {
                    if(sanPham.getTenSanPham().toLowerCase().contains(tenSanPhamTimKiem)){
                        listSpTimKiem.add(sanPham);
                    }
                }
                if(listSpTimKiem.isEmpty()){
                    Toast.makeText(MainActivity.this,"Không có sản phẩm cần tìm",Toast.LENGTH_LONG).show();
                }
                listSanPham = listSpTimKiem;
                updateListView();
            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alertDialog =builder.create();
        alertDialog.show();
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nhapSanPham:
                Toast.makeText(this,"Nhap San Pham",Toast.LENGTH_LONG).show();
                Intent intent =new Intent(MainActivity.this,ThemSanPham.class);
                startActivityForResult(intent,999);
                break;
            case R.id.timKiemSanPham:
                showDialogTimKiem();
                break;
            case R.id.menuThoat:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 999 && resultCode == RESULT_OK){
            String ma_sanpham =data.getStringExtra("ma_sanpham").toString();
            String ten_sanpham =data.getStringExtra("ten_sanpham").toString();
            double gia = data.getDoubleExtra("gia",0.0);
            SanPham sanPham = new SanPham(ma_sanpham,ten_sanpham,gia);
            if(listSanPham.contains(sanPham)){
                Toast.makeText(MainActivity.this,"Trùng mã sản phẩm",Toast.LENGTH_LONG).show();
            }
            else{
                listSanPham.add(sanPham);
                updateListView();
            }
        }
    }
}