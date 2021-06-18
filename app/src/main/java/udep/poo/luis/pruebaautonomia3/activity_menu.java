package udep.poo.luis.pruebaautonomia3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

public class activity_menu extends AppCompatActivity implements View.OnClickListener{
    Button btnRegresar2;
    Button btnVer1;
    Button btnVer2;
    Button btnVer3;
    Button btnVer4;
    FirebaseFirestore db;
    TextView nombreSitio,textviewCodigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        btnRegresar2 = (Button) findViewById(R.id.btnRegresar2);
        btnRegresar2.setOnClickListener(this);
        btnVer1 = (Button) findViewById(R.id.btnVer1);
        btnVer1.setOnClickListener(this);
        btnVer2 = (Button) findViewById(R.id.btnVer2);
        btnVer2.setOnClickListener(this);
        btnVer3 = (Button) findViewById(R.id.btnVer3);
        btnVer3.setOnClickListener(this);
        btnVer4 = (Button) findViewById(R.id.btnVer4);
        btnVer4.setOnClickListener(this);
        Bundle bundleSitio = getIntent().getExtras();
        nombreSitio = (TextView) findViewById(R.id.nombreSitio);
            if(nombreSitio != null) {
                String nombreSitio1 = bundleSitio.getString("nombreSitio");
                nombreSitio.setText(nombreSitio1);
            }
        Bundle bundleCodigo = getIntent().getExtras();
        textviewCodigo = findViewById(R.id.textviewCodigo);
            if(textviewCodigo != null){
                String tvCodigo = bundleCodigo.getString("codigo");
                textviewCodigo.setText(tvCodigo);
            }
        db = FirebaseFirestore.getInstance();
        }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRegresar2:
                Intent i1 = new Intent("android.intent.action.CONSULTA");
                startActivity(i1);
            case R.id.btnVer1:
                db.collection("base de sitios")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot doc : task.getResult()) {
                                        Bundle bundleSitio = getIntent().getExtras();
                                        String b = bundleSitio.getString("nombreSitio");
                                        String c = nombreSitio.toString();
                                        if (c.equalsIgnoreCase(b)) {
                                            String a = doc.getString("TSS");
                                            Intent browserTSS = new Intent(Intent.ACTION_VIEW, Uri.parse(a));
                                            startActivity(browserTSS);
                                            break;
                                        }
                                        else {
                                            Toast.makeText(activity_menu.this, "XD", Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                }
                            }
                        });
            case R.id.btnVer2:
                Intent i3 = new Intent("android.intent.action.BASE");
                Bundle bundle = new Bundle();
                String d = bundle.getString("codigo");
                Bundle bundleBase = new Bundle();
                bundleBase.putString("codigoBase",d);
                startActivity(i3);
                i3.putExtras(bundleBase);
            case R.id.btnVer3:
                Intent i4 = new Intent("android.intent.action.HISTORICO");
                startActivity(i4);
        }

    }
}