package br.com.concrete.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import br.com.concrete.R;
import br.com.concrete.activity.LoginActivity;

@SuppressLint("ValidFragment")
public class DialogUtil extends DialogFragment {

    // Views
    private View view;
    private Button sair_sim, sair_nao;

    // Utils
    private Context context;
    private SharedPreferences sp;

    public DialogUtil(Context context){
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        sp = context.getSharedPreferences(Constantes.SP, Context.MODE_PRIVATE);
        view = inflater.inflate(R.layout.dialog_util, container);
        sair_sim = view.findViewById(R.id.sair_sim);
        sair_nao = view.findViewById(R.id.sair_nao);
        setOnClickListener();
        return view;
    }

    private void setOnClickListener(){
        sair_sim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp.edit().putString(Constantes.USUARIO, "").commit();
                startActivity(new Intent(getActivity(), LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                getActivity().finish();
            }
        });
        sair_nao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
        getDialog().requestWindowFeature(STYLE_NO_TITLE);
        getDialog().setCanceledOnTouchOutside(false);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().setTitle(null);
    }
}