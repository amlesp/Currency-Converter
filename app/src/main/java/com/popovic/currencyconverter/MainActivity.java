package com.popovic.currencyconverter;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.popovic.currencyconverter.home.IMainMVP;
import com.popovic.currencyconverter.home.MainActivityPresenter;
import com.popovic.currencyconverter.home.MainActivityViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements IMainMVP.View {

    private final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.et_user_entry)
    EditText mUserEntry;
    @BindView(R.id.tv_result)
    TextView mResult;
    @BindView(R.id.sp_from)
    Spinner mSpinnerConvertFrom;
    @BindView(R.id.sp_to)
    Spinner mSpinnerConvertTo;
    @BindView(R.id.btn_convert)
    Button mButtonConvert;

    private IMainMVP.Presenter mPresenter;
    private MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //init presenter
        mPresenter = new MainActivityPresenter(this);

        //avoid fetching data on rotation
        if (savedInstanceState == null)
            mPresenter.fetchData();

        //initialising viewmodel and setting observer's
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mainActivityViewModel.getCodeList().observe(MainActivity.this, new Observer<List<String>>() {
            @Override
            public void onChanged(@Nullable List<String> strings) {
                if (strings != null) {
                    setUpSpinners(strings);
                }
            }
        });

        mainActivityViewModel.getResult().observe(MainActivity.this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                if (s != null) {
                    mResult.setText(s);
                }
            }
        });
    }

    private void setUpSpinners(List<String> data) {

        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_spinner_item, data);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerConvertFrom.setAdapter(adapter1);
        mSpinnerConvertFrom.setSelection(mainActivityViewModel.getSpinnerFromSelection());

        mSpinnerConvertFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mainActivityViewModel.setSpinnerFromSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_spinner_item, data);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerConvertTo.setAdapter(adapter2);
        mSpinnerConvertTo.setSelection(mainActivityViewModel.getSpinnerToSelection());

        mSpinnerConvertTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mainActivityViewModel.setSpinnerToSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @OnClick(R.id.btn_convert)
    public void convert() {

        String userEntry = mUserEntry.getText().toString().trim();

        if (TextUtils.isEmpty(userEntry)) {
            displayMssage(getString(R.string.please_enter_value));
            return;
        }

        mPresenter.convert(
                mSpinnerConvertFrom.getSelectedItem().toString(),
                mSpinnerConvertTo.getSelectedItem().toString(),
                Double.parseDouble(userEntry)
        );
    }

    @Override
    public void showError(String error) {
        displayMssage(error);
    }

    @Override
    public void showResult(String result) {
        mainActivityViewModel.getResult().setValue(result);
    }

    private void displayMssage(String message) {
        Snackbar.make(mResult, message, Snackbar.LENGTH_SHORT)
                .show();
    }
}
