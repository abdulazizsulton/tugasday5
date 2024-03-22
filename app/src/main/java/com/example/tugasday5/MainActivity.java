package com.example.tugasday5;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    EditText etNama, etKodebarang, etJumlahbarang;
    RadioGroup btnRadiogroup;
    RadioButton btnGold, btnSilver, btnBiasa;
    Button btnProses;
    TextView tvTipe, tvTitle;
    double totalHarga = 0;
    double hargaBarang = 0;
    double diskon = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNama = findViewById(R.id.etNama);
        etKodebarang = findViewById(R.id.etKodebarang);
        etJumlahbarang = findViewById(R.id.etJumlahbarang);
        btnRadiogroup = findViewById(R.id.btnRadiogroupp);
        btnGold = findViewById(R.id.btnGold);
        btnSilver = findViewById(R.id.btnSilver);
        btnBiasa = findViewById(R.id.btnBiasa);
        btnProses = findViewById(R.id.btnProses);
        tvTipe = findViewById(R.id.tvTipee);

        btnProses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String kodeBarang = etKodebarang.getText().toString().trim();
                int jumlahBarang = Integer.parseInt(etJumlahbarang.getText().toString().trim());

                String namaBarang;

                String namaPembeli = etNama.getText().toString().trim();

                String tipeMember;
                if (btnGold.isChecked()) {
                    tipeMember = "Gold";
                } else if (btnSilver.isChecked()) {
                    tipeMember = "Silver";
                } else if (btnBiasa.isChecked()) {
                    tipeMember = "Biasa";
                } else {
                    // Jika tidak ada yang dipilih, set default menjadi "Biasa"
                    tipeMember = "Biasa";
                }

                switch (kodeBarang) {
                    case "SGS":
                        hargaBarang = 12999999;
                        namaBarang = "Samsung Galaxy S";
                        break;
                    case "IPX":
                        hargaBarang = 5725300;
                        namaBarang = "Iphone X";
                        break;
                    case "PCO":
                        hargaBarang = 2730551;
                        namaBarang = "POCO M3";
                        break;

                    default:
                        Toast.makeText(MainActivity.this, "Kode barang tidak valid", Toast.LENGTH_SHORT).show();
                        return;
                }

                if (jumlahBarang <= 0) {
                    Toast.makeText(MainActivity.this, "Jumlah barang harus lebih dari 0", Toast.LENGTH_SHORT).show();
                    return;
                }

                totalHarga = hargaBarang * jumlahBarang;
                double potonganHarga = 0;
                double potonganDiskon = 0;

                if (totalHarga > 10000000) {
                    potonganHarga = 100000;
                }

                if (btnGold.isChecked()) {
                    diskon = 0.1;
                } else if (btnSilver.isChecked()) {
                    diskon = 0.05;
                } else if (btnBiasa.isChecked()) {
                    diskon = 0.02;
                }

                potonganDiskon = totalHarga * diskon;
                double totalBayar = totalHarga - potonganHarga - potonganDiskon;


                Intent intent = new Intent(MainActivity.this, detailllActivity2.class);

                intent.putExtra("Ucapan", namaPembeli);
                intent.putExtra("tipeMember", tipeMember);
                intent.putExtra("kodeBarang", kodeBarang);
                intent.putExtra("namaBarang", namaBarang);
                intent.putExtra("harga", hargaBarang);
                intent.putExtra("totalHarga", totalHarga);
                intent.putExtra("discountHarga", potonganHarga);
                intent.putExtra("discountMember", potonganDiskon);
                intent.putExtra("jumlahBayar", totalBayar);

                startActivity(intent);


            }
        });


    }}