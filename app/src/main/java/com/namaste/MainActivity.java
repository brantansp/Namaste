package com.namaste;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.os.Handler;
import android.text.method.ScrollingMovementMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView tv = null;
    private Boolean exit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tv = (TextView) findViewById(R.id.textView);

        loadtv();

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.design_default_color_primary));
        }
      /*  FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                *//*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*//*

            }
        });*/
    }

    private void loadtv() {
        tv.setMovementMethod(new ScrollingMovementMethod());
        tv.setText("\n\n\n\n\nCentral Council for Research in Siddha (CCRS) is the apex body pertaining to research in Siddha system of Medicine. CCRS is working towards the scientific validation of Siddha System of Medicine through clinical research, drug research, medicinal plants research, fundamental research, literary research and documentation. CCRS is carrying out its research activities and health care services mainly through 8 peripheral Institutes/Units – 3 located in Tamil Nadu, one each in Puducherry, Kerala, Karnataka, Andhra Pradesh and New Delhi. CCRS is currently carrying out 33 Intra-mural Research (IMR) Projects funded by Ministry of AYUSH. CCRS has more than 1000 palm leaf manuscripts and involved in decoding the formulations and other contents and publishing as books. CCRS is also publishing rare Siddha literatures, palm leaf manuscripts and till now 50 books have been published. The research outcomes are converted into intellectual property rights through patents and publications in peer reviewed journals.For the development of Siddha system of Medicine, Govt. of India, by bifurcating the erstwhile CCRAS, formed CCRS (Central Council for Research in Siddha) with its headquarters in Chennai and eight Research Institutes Units in six states namely, Tamil Nadu (Chennai, Mettur, Palayamkottai), Puducherry, New Delhi, Kerala (Thiruvananthapuram), Karnataka(Banglore) and Andra Pradesh (Tirupati). Siddha is a science of holistic health emphasizing both drug and diet for human health care. The Council has the vision of preservation and transmission of Knowledge and enhancement of the quality of research for developing drugs with quality, safety and efficacy through well-established preclinical and clinical research facilities — to prevent / manage /cure the diseases of varied aetiology. To undertake scientific research works in Siddha in a time-bound and cost-effective manner, to coordinate, aid, promote and collaborate research with different units of sister Councils and Research Organizations. To publish research articles/research journals, to exhibit achievements and to propagate research outcomes for all the stakeholders. To provide consultancy services for research projects and drug development (adopting both classical and modern techniques/equipments for Diagnosis, evolving evidence based Siddha drug treatment/therapy and promoting Siddha drug manufacture in collaboration with the other technical organizations).\n\n\n\n");
    }

    public void onSearchClick(View view) {
        /*Intent getSearchIntent = new Intent(this, SearchActivity.class);
        startActivity(getSearchIntent);*/
        Intent getSearchIntent = new Intent(this, AndroidSQLite.class);
        startActivity(getSearchIntent);
    }

    @Override
    public void onBackPressed(){
        if (exit) {
            finish(); // finish activity
        }
        else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    Intent a = new Intent(Intent.ACTION_MAIN);
                    a.addCategory(Intent.CATEGORY_HOME);
                    a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(a);
                }
            }, 1000);
        }
    }

}
