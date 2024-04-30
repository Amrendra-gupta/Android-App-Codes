import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class EnergyCount extends AppCompatActivity {

    ImageButton addbulb,addfan,addcharger,addlaptop, subcharger,sublaptop,subbulb,subfan;
    TextView updatetext, bulbmin, fanmin,chargermin,laptopmin;
    Button update;

    int bulbtime = 0;
    int fantime = 0;
    int chargertime = 0;
    int laptoptime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_energy_count);

        addbulb = findViewById(R.id.addblub);
        addfan = findViewById(R.id.addfan);
        addcharger = findViewById(R.id.addcharger);
        addlaptop = findViewById(R.id.addlaptop);

        subbulb = findViewById(R.id.subblub);
        subfan = findViewById(R.id.subfan);
        subcharger = findViewById(R.id.subcharger);
        sublaptop = findViewById(R.id.sublaptop);

        bulbmin = findViewById(R.id.blubmin);
        fanmin = findViewById(R.id.fanmin);
        chargermin = findViewById(R.id.chargermin);
        laptopmin = findViewById(R.id.laptopmin);

        update = findViewById(R.id.update);
        updatetext = findViewById(R.id.current);

        addbulb.setOnClickListener(clickListener);
        addfan.setOnClickListener(clickListener);
        addcharger.setOnClickListener(clickListener);
        addlaptop.setOnClickListener(clickListener);
        subbulb.setOnClickListener(clickListener);
        subfan.setOnClickListener(clickListener);
        subcharger.setOnClickListener(clickListener);
        sublaptop.setOnClickListener(clickListener);
        update.setOnClickListener(clickListener);

        SharedPreferences bal = getSharedPreferences("energy_current", MODE_PRIVATE);
        float current_bal = bal.getFloat("balance", 0f);

        updatetext.setText("Current Balance :  "+ current_bal+ " W");

    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.addblub) {
                bulbtime += 5;
                bulbmin.setText(bulbtime + " min");
            } else if (view.getId() == R.id.addfan) {
                fantime += 5;
                fanmin.setText(fantime + " min");
            } else if (view.getId() == R.id.addcharger) {
                chargertime += 5;
                chargermin.setText(chargertime + " min");
            } else if (view.getId() == R.id.addlaptop) {
                laptoptime += 5;
                laptopmin.setText(laptoptime + " min");
            } else if (view.getId() == R.id.subblub) {
                bulbtime -= 5;
                bulbmin.setText(bulbtime + " min");
            } else if (view.getId() == R.id.subfan) {
                fantime -= 5;
                fanmin.setText(fantime + " min");
            } else if (view.getId() == R.id.subcharger) {
                chargertime -= 5;
                chargermin.setText(chargertime + " min");
            } else if (view.getId() == R.id.sublaptop) {
                laptoptime -= 5;
                laptopmin.setText(laptoptime + " min");
            } else if (view.getId() == R.id.update) {
                updatebalance();
            }
        }
    };

    private void updatebalance() {
        float bulbpower = 0.15f;  // it is power comsume per minute
        float fanpower = 1f;
        float chargerpower = 0.25f;
        float laptoppower = 1.1f;

        float updatebulb = bulbtime*bulbpower;
        float updatefan = fantime*fanpower;
        float updatecharger = chargertime*chargerpower;
        float updatelaptop = laptoptime*laptoppower;

        SharedPreferences bal = getSharedPreferences("energy_current", MODE_PRIVATE);
        float current_bal = bal.getFloat("balance", 0f);

        float update =updatebulb+updatecharger+updatefan+updatelaptop+current_bal;

        SharedPreferences.Editor editor = bal.edit();
        editor.putFloat("balance",update);
        editor.apply();

        update();
        updatetext.setText("Current Balance :  "+ update+ " W");
    }
    private void update() {
        bulbtime = 0;
        chargertime = 0;
        fantime = 0;
        laptoptime = 0;
        bulbmin.setText(bulbtime + " min");
        fanmin.setText(fantime + " min");
        chargermin.setText(chargertime + " min");
        laptopmin.setText(laptoptime + " min");
    }
}