package abc.com.example.vijsu.chart;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    HorizontalBarChart chart;
    Button yes,no;
    float yesFloat, noFloat,totalFloat;
    float yesPercent,noPercent;
    String TAG = "graph";
    ArrayList<BarEntry> entries;
    BarDataSet dataset;
    BarData data;
    ArrayList<String> labels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        yes = (Button)findViewById(R.id.yes);
        no = (Button)findViewById(R.id.no);

        yes.setOnClickListener(this);
        no.setOnClickListener(this);

        chart = (HorizontalBarChart)findViewById(R.id.chart);
        labels = new ArrayList<String>();
        labels.add("Yes");
        labels.add("No");
        chart.setDescription("Polls activity");


    }

    private void calculatePercentages() {
        if ((yesFloat != 0f) && (noFloat !=0f)){
            totalFloat = yesFloat + noFloat;
            yesPercent = (yesFloat * 100) / totalFloat;
            noPercent = (noFloat * 100)/totalFloat;
        }else {
            yesFloat = 0f;
            noFloat = 0f;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.yes:
                yesFloat = yesFloat + 1f;
                Toast.makeText(MainActivity.this, "clicked yes", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onClick of yes"+yesPercent);
                entries = new ArrayList<>();
                entries.add(new BarEntry(yesPercent, 0));
                entries.add(new BarEntry(noPercent, 1));

                dataset = new BarDataSet(entries, "# of Calls");

                data = new BarData(labels, dataset);
                chart.setData(data);
                calculatePercentages();
                finish();
                startActivity(getIntent());
                break;
            case R.id.no:
                noFloat = noFloat + 1f;
                Toast.makeText(MainActivity.this, "clicked no", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onClick of yes"+noPercent);
                entries = new ArrayList<>();
                entries.add(new BarEntry(yesPercent, 0));
                entries.add(new BarEntry(noPercent, 1));

                dataset = new BarDataSet(entries, "# of Calls");

                data = new BarData(labels, dataset);
                chart.setData(data);

                calculatePercentages();
                finish();
                startActivity(getIntent());
                break;
        }
    }
}
