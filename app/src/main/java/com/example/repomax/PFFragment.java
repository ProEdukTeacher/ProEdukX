package com.example.repomax;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PFFragment extends Fragment {

    DatabaseReference mDatabaeReference;
    FirebaseUser mUser;
    EditText nameET,academicsET,emailET;
    TextView textVer, textVer2, textVer3, textVer4;
    Dialog dialog, dialog2, dialog3, dialog4;
    ArrayList<String> arrayList, arrayList2, arrayList3, arrayList4;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Initialize view

        View view = inflater.inflate(R.layout.fragment_p_f, container, false);

        textVer= view.findViewById(R.id.text_viewpf);
        textVer2 = view.findViewById(R.id.text_viewpf2);
        textVer3 = view.findViewById(R.id.text_viewpf3);
        textVer4 = view.findViewById(R.id.text_viewpf4);
        arrayList = new ArrayList<>();
        arrayList2 = new ArrayList<>();
        arrayList3 = new ArrayList<>();
        arrayList4 = new ArrayList<>();
        nameET= view.findViewById(R.id.nameDisplay);
        academicsET = view.findViewById(R.id.academicDisplay);
        emailET = view.findViewById(R.id.emailDisplay);
        mUser = SessionManager.getInstance().getmAuth().getCurrentUser();
        mDatabaeReference = SessionManager.getInstance().getFirebaseDatabase().getReference(TeacherUser.class.getSimpleName());

        mDatabaeReference.child(mUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                TeacherUser user = snapshot.getValue(TeacherUser.class);
                if(user!=null){
                    String fullName = user.getName();
                    String email = user.getEmail();
                    String academicLevel = user.getAcademiclevel();

                    nameET.setText(fullName);
                    emailET.setText(email);
                    academicsET.setText(academicLevel);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //ArrayList for Age
        arrayList.add("21"); arrayList.add("22"); arrayList.add("23"); arrayList.add("24"); arrayList.add("25");
        arrayList.add("26"); arrayList.add("27"); arrayList.add("28"); arrayList.add("29"); arrayList.add("30");
        arrayList.add("31"); arrayList.add("32"); arrayList.add("33"); arrayList.add("34"); arrayList.add("35");
        arrayList.add("36"); arrayList.add("37"); arrayList.add("38"); arrayList.add("39"); arrayList.add("40");
        arrayList.add("41"); arrayList.add("42"); arrayList.add("43"); arrayList.add("44"); arrayList.add("45");
        arrayList.add("46"); arrayList.add("47"); arrayList.add("48"); arrayList.add("49"); arrayList.add("50");
        arrayList.add("51"); arrayList.add("52"); arrayList.add("53"); arrayList.add("54"); arrayList.add("55");
        arrayList.add("56"); arrayList.add("57"); arrayList.add("58"); arrayList.add("59"); arrayList.add("60+");

        //ArrayList for Town
        arrayList2.add("Adjuntas"); arrayList2.add("Aguada"); arrayList2.add("Aguadilla");
        arrayList2.add("Aguas Buenas"); arrayList2.add("Aibonito"); arrayList2.add("Arecibo");
        arrayList2.add("Arroyo"); arrayList2.add("Añasco"); arrayList2.add("Barceloneta");
        arrayList2.add("Barranquitas"); arrayList2.add("Bayamón"); arrayList2.add("Cabo Rojo");
        arrayList2.add("Caguas"); arrayList2.add("Camuy"); arrayList2.add("Canóvanas");
        arrayList2.add("Carolina"); arrayList2.add("Cataño"); arrayList2.add("Cayey");
        arrayList2.add("Ceiba"); arrayList2.add("Ciales"); arrayList2.add("Cidra");
        arrayList2.add("Coamo"); arrayList2.add("Comerio"); arrayList2.add("Corozal");
        arrayList2.add("Culebra"); arrayList2.add("Dorado"); arrayList2.add("Fajardo");
        arrayList2.add("Florida"); arrayList2.add("Guayama"); arrayList2.add("Guayanilla");
        arrayList2.add("Guaynabo"); arrayList2.add("Gurabo"); arrayList2.add("Guánica");
        arrayList2.add("Hatillo"); arrayList2.add("Hormigueros"); arrayList2.add("Humacao");
        arrayList2.add("Isabela"); arrayList2.add("Jayuya"); arrayList2.add("Juana Díaz");
        arrayList2.add("Juncos"); arrayList2.add("Lajas"); arrayList2.add("Lares");
        arrayList2.add("Las Marías"); arrayList2.add("Las Piedras"); arrayList2.add("Loiza");
        arrayList2.add("Luquillo"); arrayList2.add("Manatí"); arrayList2.add("Maricao");
        arrayList2.add("Maunabo"); arrayList2.add("Moca"); arrayList2.add("Morovis");
        arrayList2.add("Naguabo"); arrayList2.add("Naranjito"); arrayList2.add("Orocovis");
        arrayList2.add("Patillas"); arrayList2.add("Peñuelas"); arrayList2.add("Ponce");
        arrayList2.add("Quebradillas"); arrayList2.add("Rincón"); arrayList2.add("Rio Grande");
        arrayList2.add("Sabana Grande"); arrayList2.add("Salinas"); arrayList2.add("San Germán");
        arrayList2.add("San Juan"); arrayList2.add("San Lorenzo"); arrayList2.add("San Sebastán");
        arrayList2.add("Santa Isabel"); arrayList2.add("Toa Alta"); arrayList2.add("Toa Baja");
        arrayList2.add("Trujillo Alto"); arrayList2.add("Utuado"); arrayList2.add("Vega Alta");
        arrayList2.add("Vega Baja"); arrayList2.add("Vieques"); arrayList2.add("Villalba");
        arrayList2.add("Yabucoa"); arrayList2.add("Yauco");

        arrayList3.add("PlayStore"); arrayList3.add("Google"); arrayList3.add("Recomendacion"); arrayList3.add("FaceBook Ads");
        arrayList3.add("Ninguna de las anteriores");

        arrayList4.add("1"); arrayList4.add("2"); arrayList4.add("3"); arrayList4.add("4"); arrayList4.add("5");
        arrayList4.add("6"); arrayList4.add("7"); arrayList4.add("8"); arrayList4.add("9"); arrayList4.add("10");
        arrayList4.add("11"); arrayList4.add("12"); arrayList4.add("13"); arrayList4.add("14"); arrayList4.add("15");
        arrayList4.add("16"); arrayList4.add("17"); arrayList4.add("18"); arrayList4.add("19"); arrayList4.add("20");
        arrayList4.add("21"); arrayList4.add("22"); arrayList4.add("23"); arrayList4.add("24"); arrayList4.add("25+");

        textVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog = new Dialog(getActivity());

                dialog.setContentView(R.layout.dialog_searchable_spinner);

                dialog.getWindow().setLayout(650, 800);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                dialog.show();

                EditText editText = dialog.findViewById(R.id.edit_text);
                ListView listView = dialog.findViewById(R.id.list_view);

                ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,
                        arrayList);

                listView.setAdapter(adapter);

                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        adapter.getFilter().filter(charSequence);

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        textVer.setText(adapter.getItem(i));

                        dialog.dismiss();

                    }
                });

            }
        });

        textVer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {

                dialog2 = new Dialog(getActivity());

                dialog2.setContentView(R.layout.dialog_searchable_spinner);

                dialog2.getWindow().setLayout(650, 800);

                dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                dialog2.show();

                EditText editText2 = dialog2.findViewById(R.id.edit_text);
                ListView listView2 = dialog2.findViewById(R.id.list_view);

                ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,
                        arrayList2);

                listView2.setAdapter(adapter2);

                editText2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        adapter2.getFilter().filter(charSequence);

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });

                listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        textVer2.setText(adapter2.getItem(i));

                        dialog2.dismiss();

                    }
                });
            }
        });
        textVer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {

                dialog3 = new Dialog(getActivity());

                dialog3.setContentView(R.layout.dialog_searchable_spinner);

                dialog3.getWindow().setLayout(650, 800);

                dialog3.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                dialog3.show();

                EditText editText3 = dialog3.findViewById(R.id.edit_text);
                ListView listView3 = dialog3.findViewById(R.id.list_view);

                ArrayAdapter<String> adapter3 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,
                        arrayList3);

                listView3.setAdapter(adapter3);

                editText3.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        adapter3.getFilter().filter(charSequence);

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });

                listView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        textVer3.setText(adapter3.getItem(i));

                        dialog3.dismiss();

                    }
                });
            }
        });

        textVer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {

                dialog4 = new Dialog(getActivity());

                dialog4.setContentView(R.layout.dialog_searchable_spinner);

                dialog4.getWindow().setLayout(650, 800);

                dialog4.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                dialog4.show();

                EditText editText4 = dialog4.findViewById(R.id.edit_text);
                ListView listView4 = dialog4.findViewById(R.id.list_view);

                ArrayAdapter<String> adapter4 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,
                        arrayList4);

                listView4.setAdapter(adapter4);

                editText4.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        adapter4.getFilter().filter(charSequence);

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });

                listView4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        textVer4.setText(adapter4.getItem(i));

                        dialog4.dismiss();

                    }
                });
            }
        });


        return view;

    }




}