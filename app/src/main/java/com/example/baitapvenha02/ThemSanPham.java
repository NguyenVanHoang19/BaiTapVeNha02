package com.example.baitapvenha02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ThemSanPham extends AppCompatActivity {
    private EditText edt_maSanPham,edt_tenSanPham,edt_gia;
    private Button btnThem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_san_pham);
        connectView();
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String maSanPham =edt_maSanPham.getText().toString();
                String tenSanPham = edt_tenSanPham.getText().toString();
                double gia = Double.parseDouble(edt_gia.getText().toString());
                Intent travel =new Intent();
                travel.putExtra("ma_sanpham",maSanPham);
                travel.putExtra("ten_sanpham",tenSanPham);
                travel.putExtra("gia",gia);
                setResult(RESULT_OK,travel);
                finish();
            }
        });
    }
    public void connectView(){
        edt_maSanPham = (EditText) findViewById(R.id.edt_maSanPham);
        edt_tenSanPham = (EditText) findViewById(R.id.edt_tenSanPham);
        edt_gia = (EditText) findViewById(R.id.edt_gia);
        btnThem = (Button) findViewById(R.id.btnThem);
    }
}