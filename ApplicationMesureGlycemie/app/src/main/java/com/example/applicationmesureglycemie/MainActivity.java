package com.example.applicationmesureglycemie;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText etValeur;
    private Button btnConsulter;
    private RadioButton rbtoui;
    private RadioButton rbtnon;
    private SeekBar sbAge;
    private TextView tvage,tvresultat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //seakbar Listnear explicite

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initialisation des éléments de l'interface utilisateur
        init();
        // Ajout d'un écouteur de clic sur le bouton Consulter
        btnConsulter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculer(v);
            }
        });
        // Ajout d'un écouteur de changement de progression sur la SeekBar sbAge
        //Seakbar Listnear implicite
        sbAge.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                                             @Override
                                             public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                                 Log.i("Information", "onProgressChanged " + progress);
                                                 // Affichage dans le Log de Android studio pour voir les changements détectés sur le SeekBar ..
                                                 tvage.setText("Votre âge : " + progress);
                                                 // Mise à jour du TextView par la valeur : progress à chaque changement.

                                             }

                                             @Override
                                             public void onStartTrackingTouch(SeekBar seekBar) {
                                             }

                                             @Override
                                             public void onStopTrackingTouch(SeekBar seekBar) {
                                             }
                                         }
        );
    }
    // Méthode de calcul appelée lors du clic sur le bouton Consulter
    public void calculer(View v) {
        int age;
        float valeurmasuree;
        boolean verifage = false;
        boolean verifvaleur = false;
        if (sbAge.getProgress() != 0) {
            verifage = true;
        } else {
            Toast.makeText(MainActivity.this, "Veuillez vérifier votre age", Toast.LENGTH_SHORT).show();
        }
        if (!etValeur.getText().toString().isEmpty()) {
            verifvaleur = true;
        } else {
            Toast.makeText(MainActivity.this, "Veuillez vérifier la valeur mesurée", Toast.LENGTH_LONG).show();
        }
        if (verifage && verifvaleur) {
            //Traitement
            age = sbAge.getProgress();
            valeurmasuree = Float.valueOf(etValeur.getText().toString());
            boolean isFasting = rbtoui.isChecked();
            if (isFasting) {
                if (age >= 13) {
                    if (valeurmasuree < 5)
                        tvresultat.setText("Niveau de glycémie est bas");
                    else if (valeurmasuree >= 5 && valeurmasuree <= 7.2)
                        tvresultat.setText("Niveau de glycémie est normale");
                    else
                        tvresultat.setText("Niveau de glycémie est élevé");
                } else if (age >= 6 && age <= 12) {
                    if (valeurmasuree < 5)
                        tvresultat.setText("Niveau de glycémie est bas");
                    else if (valeurmasuree >= 5 && valeurmasuree <= 10)
                        tvresultat.setText("Niveau de glycémie est normale");
                    else
                        tvresultat.setText("Niveau de glycémie est élevé");
                } else if (age < 6) {
                    if (valeurmasuree < 5.5)
                        tvresultat.setText("Niveau de glycémie est bas");
                    else if (valeurmasuree >= 55 && valeurmasuree <= 10.0)
                        tvresultat.setText("Niveau de glycémie est normale");
                    else
                        tvresultat.setText("Niveau de glycémie est élevé ");
                } else {
                    //false
                    if (valeurmasuree > 10.5)
                        tvresultat.setText("Niveau de glycémie est élevé ");
                    else
                        tvresultat.setText("Niveau de glycémie est normale ");
                }
            }

        }
    }
    // Initialisation des éléments de l'interface utilisateur
    private void init() {
        etValeur =(EditText) findViewById(R.id.edittext);
        btnConsulter =(Button) findViewById(R.id.btnConsulter);
        rbtoui = (RadioButton)findViewById(R.id.rbtoui);
        rbtnon= (RadioButton)findViewById(R.id.rbtnon);
        sbAge= (SeekBar)findViewById(R.id.sbAge);
        tvage= (TextView)findViewById(R.id.tvage);
        tvresultat=(TextView) findViewById(R.id.tvresultat);

    }
}
