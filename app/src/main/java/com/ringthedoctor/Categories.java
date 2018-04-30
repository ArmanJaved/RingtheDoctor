package com.ringthedoctor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class Categories extends AppCompatActivity {

    String[] mobileArray = {"Allergy Specialist","Audiologist","Breast Surgeon","Cardiac Surgeon",
            "Cardiologist","Chest Specialist","Chiropractic Physician","Clinical Dietition", "Consultant ENT", "Consultant Family Physician",
    "Cosmetic Dentistry", "Cosmetic Medicine", "Dental Maxillo Facial Surgeon", "Dentist", "Dermatologist", "Endocrinologist", "Endoscopic Sinus Specialist",
    "Endoscopic Surgeon", "Family Physician", "Gastroenterologist", "General Physician", "General Surgeon", "Geriatrician", "Hematologist & Oncologist (Cancer)",
    "Implantologist", "Infection Diseases", "Infertility Specialist", "Nephtologist", "Neuro Physician", "Neurosurgeon", "Obstetrics", "Oncologist",
    "Ophthalmologist", "Orthopediatrician", "Orthopedic", "Orthopedic Surgeon", "Pain Management", "Pediatric Cardiologist", "Pediatrician",
    "Pediatrician Surgeon", "Physical Medicine & Rehabilitation", "Physician", "Physiotherapist", "Plastic Surgeon", "Psychiatrist", "Psychologist",
    "Pulmonologist", "Radio Logist", "Rheumatology", "Skin Specialist", "Sonologist", "Special Education & Behavioral Counsellor", "Speech Therapist",
    "Spine Specialist", "Surgeon", "Thalassemia", "Thoracic Surgeon", "Urologist", "Vascular Surgeon"};


    int[] catimage = {R.drawable.allergy_, R.drawable.audiologist_, R.drawable.breast_, R.drawable.cardiac_sug, R.drawable.cardiologist_
    , R.drawable.chest_spec, R.drawable.chiropractic, R.drawable.clinical, R.drawable.ent_, R.drawable.consult_family, R.drawable.cosmetic_dent,
            R.drawable.cosmetic_med, R.drawable.dental_max, R.drawable.dentist_, R.drawable.dermatology_, R.drawable.endocrino_,
            R.drawable.endoscop_, R.drawable.endoscopic_, R.drawable.physician_, R.drawable.gastro_, R.drawable.gen_physi, R.drawable.gen_surgeon,
            R.drawable.geriatri_, R.drawable.hematol_,
            R.drawable.implantology_, R.drawable.infection_, R.drawable.infertility_, R.drawable.nephrolo_, R.drawable.neuro_, R.drawable.neuros_,
            R.drawable.obstet_, R.drawable.oncologist_, R.drawable.ophta_, R.drawable.ortho_,R.drawable.orthop_, R.drawable.orthope_,
            R.drawable.pain_, R.drawable.pedi_, R.drawable.pediat_, R.drawable.pediat_, R.drawable.physical_med, R.drawable.physician_,
            R.drawable.physiotherapy_, R.drawable.sugreon_, R.drawable.psycho_, R.drawable.psycholo_, R.drawable.pulmonolo_,
            R.drawable.radiolo_, R.drawable.rheuma_, R.drawable.skin_spec_, R.drawable.sonolo_, R.drawable.special_edu, R.drawable.special_edu,
            R.drawable.spine_specialist, R.drawable.sugreon_, R.drawable.thalas_, R.drawable.thora_, R.drawable.urologist_, R.drawable.vascular_};


    String[] categoriesarr = {"Allergy_Specialist","Audiologist","Breast_surgeon","Cardiac_Surgeon",
            "Cardiologist","Chest_specialist","Chiropractic_physician","Clinical_dietition", "Consultant_ent", "Consultant_Family_Physician",
            "Cosmetic_Dentistry", "Cosmetic_Medicine", "Dental_Maxillo_Facial_Surgeon", "Dentist", "Dermatologist", "Endocrinologist", "Endoscopic_Sinus_Specialist",
            "Endoscopic_Surgeon", "Family_Physician", "Gastroenterologist", "General_Physician", "General_Surgeon", "Geriatrician", "Hematologist_&_Oncologist_(Cancer)",
            "Implantologist", "Infection_Diseases", "Infertility_Specialist", "Nephtologist", "Neuro_Physician", "Neurosurgeon", "Obstetrics", "Oncologist",
            "Ophthalmologist", "Orthopediatrician", "Orthopedic", "Orthopedic_Surgeon", "Pain_Management", "Pediatric_Cardiologist", "Pediatrician",
            "Pediatrician_Surgeon", "Physical_Medicine_&_Rehabilitation", "Physician", "Physiotherapist", "Plastic_Surgeon", "Psychiatrist", "Psychologist",
            "Pulmonologist", "Radio_Logist", "Rheumatology", "Skin_Specialist", "Sonologist", "Special Education_&_Behavioral_Counsellor", "Speech_Therapist",
            "Spine_Specialist", "Surgeon", "Thalassemia", "Thoracic_Surgeon", "Urologist", "Vascular_Surgeon"};
    ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories);


        getSupportActionBar().setTitle("Categories");



        listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(new CategoriesListAdaptor(Categories.this, mobileArray, catimage));



        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, android.view.View view, int i, long l) {


                Intent intent = new Intent(Categories.this, activity_financeoutfitter.class);
                String strName = null;
                intent.putExtra("Category", categoriesarr[i]);
                startActivity(intent);

            }

        });

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {

            startActivity(new Intent(Categories.this, MainPage.class));
            Categories.this.finish();
        }

        return super.onOptionsItemSelected(item);
    }


}
