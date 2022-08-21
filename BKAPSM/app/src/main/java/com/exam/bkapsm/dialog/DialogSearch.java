package com.exam.bkapsm.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.exam.bkapsm.R;

public class DialogSearch extends Dialog {
    private Context mCtx;
    private ICallback callback;

    public interface ICallback {
        public void search(String prdName);
    }

    public DialogSearch(@NonNull Context context) {
        super(context);
        this.mCtx = context;
        this.callback = (ICallback) context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_tim_kiem);

        Button btnS = findViewById(R.id.formBtnSearch);
        btnS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strSearch = ((EditText) findViewById(R.id.formEdtSearch)).getText().toString();
                callback.search(strSearch);
                dismiss();
            }
        });
    }
}
