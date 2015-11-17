package com.example.feliche.holataxi;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by feliche on 08/10/15.
 */
public class AddUser extends Fragment implements View.OnClickListener{

    Button btnAddUser;
    EditText etNUUser;
    EditText etNUPass;
    EditText etNUName;
    EditText etNUPhone;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.add_user, container, false);

        btnAddUser = (Button)rootView.findViewById(R.id.btnNURegistrar);
        btnAddUser.setOnClickListener(this);

        etNUUser = (EditText)rootView.findViewById(R.id.etNUUser);
        etNUPass = (EditText)rootView.findViewById(R.id.etNUPass);
        etNUName = (EditText)rootView.findViewById(R.id.etNUName);
        etNUPhone = (EditText)rootView.findViewById(R.id.etNUCelular);

        return rootView;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnNURegistrar){
            newUser();
        }
    }

    private void newUser() {
        if (!XmppService.getState().equals(XmppConnection.ConnectionState.CONNECTED)) {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
            prefs.edit()
                    .putString("xmpp_user", etNUUser.getText().toString())
                    .putString("xmpp_password", etNUPass.getText().toString())
                    .commit();
            Intent intent = new Intent(getActivity(), XmppService.class);
            getActivity().startService(intent);
        }
    }
}