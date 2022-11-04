package com.example.repomax;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Objects;


public class RegisterFragment extends Fragment implements  View.OnClickListener{

    TextView tvDat, textVew, textVew2, textVew3, textVew4;
    EditText etemail, etpass, etpassc, uname, ulastname;
    Button btnreg;
    Dialog dialog, dialog2, dialog3, dialog4;
    ArrayList<String> arrayList, arrayList2, arrayList3, arrayList4;

    FirebaseAuth mAuth;
    ArrayList<Integer> dayList = new ArrayList<>();
    DAOTeacher daoTeacher;
    public static final String REQUEST_KEY= "FragmentResult";
    public static final String RESULT_KEY="academicSelection";



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        uname = view.findViewById(R.id.username);
        ulastname = view.findViewById(R.id.lastnames);
        tvDat = view.findViewById(R.id.muestrame);
        etemail = view.findViewById(R.id.getEmail);
        etpass = view.findViewById(R.id.getPass);
        etpassc = view.findViewById(R.id.getPassc);
        mAuth = SessionManager.getInstance().getmAuth();
        daoTeacher = new DAOTeacher();
        btnreg = view.findViewById(R.id.btnregist);
        textVew = view.findViewById(R.id.text_view);
        textVew2 = view.findViewById(R.id.text_view2);
        textVew3 = view.findViewById(R.id.text_view3);
        textVew4 = view.findViewById(R.id.text_view4);
        arrayList = new ArrayList<>();
        arrayList2 = new ArrayList<>();
        arrayList3 = new ArrayList<>();
        arrayList4= new ArrayList<>();

        //ArrayLst values

        arrayList.add("Adjuntas"); arrayList.add("Aguada"); arrayList.add("Aguadilla");
        arrayList.add("Aguas Buenas"); arrayList.add("Aibonito"); arrayList.add("Arecibo");
        arrayList.add("Arroyo"); arrayList.add("Añasco"); arrayList.add("Barceloneta");
        arrayList.add("Barranquitas"); arrayList.add("Bayamón"); arrayList.add("Cabo Rojo");
        arrayList.add("Caguas"); arrayList.add("Camuy"); arrayList.add("Canóvanas");
        arrayList.add("Carolina"); arrayList.add("Cataño"); arrayList.add("Cayey");
        arrayList.add("Ceiba"); arrayList.add("Ciales"); arrayList.add("Cidra");
        arrayList.add("Coamo"); arrayList.add("Comerio"); arrayList.add("Corozal");
        arrayList.add("Culebra"); arrayList.add("Dorado"); arrayList.add("Fajardo");
        arrayList.add("Florida"); arrayList.add("Guayama"); arrayList.add("Guayanilla");
        arrayList.add("Guaynabo"); arrayList.add("Gurabo"); arrayList.add("Guánica");
        arrayList.add("Hatillo"); arrayList.add("Hormigueros"); arrayList.add("Humacao");
        arrayList.add("Isabela"); arrayList.add("Jayuya"); arrayList.add("Juana Díaz");
        arrayList.add("Juncos"); arrayList.add("Lajas"); arrayList.add("Lares");
        arrayList.add("Las Marías"); arrayList.add("Las Piedras"); arrayList.add("Loiza");
        arrayList.add("Luquillo"); arrayList.add("Manatí"); arrayList.add("Maricao");
        arrayList.add("Maunabo"); arrayList.add("Moca"); arrayList.add("Morovis");
        arrayList.add("Naguabo"); arrayList.add("Naranjito"); arrayList.add("Orocovis");
        arrayList.add("Patillas"); arrayList.add("Peñuelas"); arrayList.add("Ponce");
        arrayList.add("Quebradillas"); arrayList.add("Rincón"); arrayList.add("Rio Grande");
        arrayList.add("Sabana Grande"); arrayList.add("Salinas"); arrayList.add("San Germán");
        arrayList.add("San Juan"); arrayList.add("San Lorenzo"); arrayList.add("San Sebastán");
        arrayList.add("Santa Isabel"); arrayList.add("Toa Alta"); arrayList.add("Toa Baja");
        arrayList.add("Trujillo Alto"); arrayList.add("Utuado"); arrayList.add("Vega Alta");
        arrayList.add("Vega Baja"); arrayList.add("Vieques"); arrayList.add("Villalba");
        arrayList.add("Yabucoa"); arrayList.add("Yauco");


        arrayList2.add("21"); arrayList2.add("22"); arrayList2.add("23"); arrayList2.add("24"); arrayList2.add("25");
        arrayList2.add("26"); arrayList2.add("27"); arrayList2.add("28"); arrayList2.add("29"); arrayList2.add("30");
        arrayList2.add("31"); arrayList2.add("32"); arrayList2.add("33"); arrayList2.add("34"); arrayList2.add("35");
        arrayList2.add("36"); arrayList2.add("37"); arrayList2.add("38"); arrayList2.add("39"); arrayList2.add("40");
        arrayList2.add("41"); arrayList2.add("42"); arrayList2.add("43"); arrayList2.add("44"); arrayList2.add("45");
        arrayList2.add("46"); arrayList2.add("47"); arrayList2.add("48"); arrayList2.add("49"); arrayList2.add("50");
        arrayList2.add("51"); arrayList2.add("52"); arrayList2.add("53"); arrayList2.add("54"); arrayList2.add("55");
        arrayList2.add("56"); arrayList2.add("57"); arrayList2.add("58"); arrayList2.add("59"); arrayList2.add("60+");


        arrayList3.add("1"); arrayList3.add("2"); arrayList3.add("3"); arrayList3.add("4"); arrayList3.add("5");
        arrayList3.add("6"); arrayList3.add("7"); arrayList3.add("8"); arrayList3.add("9"); arrayList3.add("10");
        arrayList3.add("11"); arrayList3.add("12"); arrayList3.add("13"); arrayList3.add("14"); arrayList3.add("15");
        arrayList3.add("16"); arrayList3.add("17"); arrayList3.add("18"); arrayList3.add("19"); arrayList3.add("20");
        arrayList3.add("21"); arrayList3.add("22"); arrayList3.add("23"); arrayList3.add("24"); arrayList3.add("25+");

        arrayList4.add("PlayStore"); arrayList4.add("Google"); arrayList4.add("Recomendacion"); arrayList4.add("FaceBook Ads");
        arrayList4.add("Ninguna de las anteriores");

        textVew.setOnClickListener(new View.OnClickListener() {
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

                        textVew.setText(adapter.getItem(i));

                        dialog.dismiss();

                    }
                });

            }
        });

        textVew2.setOnClickListener(new View.OnClickListener() {
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

                        textVew2.setText(adapter2.getItem(i));

                        dialog2.dismiss();

                    }
                });

            }
        });

        textVew3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view3) {

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

                        textVew3.setText(adapter3.getItem(i));

                        dialog3.dismiss();

                    }
                });

            }
        });

        textVew4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

                        textVew4.setText(adapter4.getItem(i));

                        dialog4.dismiss();

                    }
                });

            }
        });









        btnreg.setOnClickListener(this);

        tvDat.setOnClickListener(v -> {
            //Initialize alert dialog

            RegistrationDialogueFragment registrationDialogueFragment = new RegistrationDialogueFragment();
            registrationDialogueFragment.show(getChildFragmentManager(),"Dialogue");


            getChildFragmentManager().setFragmentResultListener(REQUEST_KEY, this, new FragmentResultListener() {
                @Override
                public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                    String results = result.getString(RESULT_KEY);
                    tvDat.setText(results);
                }
            });
        });


        requireActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    return view;
    }



    private void createUser() {

        String email = etemail.getText().toString();
        String password = etpass.getText().toString();
        String passwordc = etpassc.getText().toString();
        String firstName = uname.getText().toString();
        String lastName = ulastname.getText().toString();

        if (TextUtils.isEmpty(firstName)) {

            uname.setError("Name cannot be empty");
            uname.requestFocus();
        } else if (TextUtils.isEmpty(lastName)) {

            ulastname.setError("Lname cannot be empty");
            ulastname.requestFocus();
        } else if (TextUtils.isEmpty(email)) {

            etemail.setError("Email cannot be empty");
            etemail.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            etpass.setError("Password cannot be empty");
            etpass.requestFocus();

        } else if (TextUtils.isEmpty(passwordc)) {
            etpassc.setError("Confirm your password");
            etpassc.requestFocus();
        } else if (!password.equals(passwordc)) {

            etpassc.setError("Passwords do not match");
            etpassc.requestFocus();

        } else {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {

                    TeacherUser tea = new TeacherUser(firstName,
                            lastName, email, tvDat.getText().toString());
                    daoTeacher.add(tea).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()) {
                                Toast.makeText(getActivity(), "Usuario Registrado", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getActivity(), LoginActivity.class));
                            }else {
                                Toast.makeText(getActivity(), "Please Retry", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

                } else {
                    Toast.makeText(getActivity(), "Error en registro" + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        }
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btnregist) {

            createUser();


        }

    }

}
