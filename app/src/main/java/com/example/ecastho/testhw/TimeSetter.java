package com.example.ecastho.testhw;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class TimeSetter extends DialogFragment {
    private TimePicker mTimePicker;
    private DatePicker mDatePicker;
    private Date mDate;
    private OnTimeSetterFinishedListener mCallback;

    public interface OnTimeSetterFinishedListener {
        void OnTimeSetterFinished(Date date);
    }

    static TimeSetter newInstance(Date date) {
        TimeSetter ts = new TimeSetter();
        Bundle args = new Bundle();
        args.putLong("longdate", date.getTime());
        ts.setArguments(args);
        return ts;
    }

    public TimeSetter() {
        // Empty constructor required for DialogFragment
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDate = new Date(getArguments().getLong("longdate"));
    }

    public void SetOnTimeSetterFinishedListener(OnTimeSetterFinishedListener callback) {
        mCallback = callback;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_time_setter, container);
        mTimePicker = (TimePicker)view.findViewById(R.id.timePicker);
        mDatePicker = (DatePicker)view.findViewById(R.id.datePicker);
        mTimePicker.setIs24HourView(true);
        setPickers(mDate);
        view.findViewById(R.id.buttonCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonCancelClicked();
            }
        });
        view.findViewById(R.id.buttonNow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonNowClicked();
            }
        });
        view.findViewById(R.id.buttonOk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonOkClicked();
            }
        });
        getDialog().setTitle("");
        return view;
    }

    private void setPickers(Date date) {

        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int mon = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int h = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mTimePicker.setHour(h);
            mTimePicker.setMinute(min);
        }
        else {
            mTimePicker.setCurrentHour(h);
            mTimePicker.setCurrentMinute(min);
        }
        mDatePicker.init(year, mon, day, null);
    }
    private Date getPickers() {
        int year = mDatePicker.getYear();
        int mon = mDatePicker.getMonth();
        int day = mDatePicker.getDayOfMonth();
        int h, min;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            h = mTimePicker.getHour();
            min = mTimePicker.getMinute();
        }
        else {
            h = mTimePicker.getCurrentHour();
            min = mTimePicker.getCurrentMinute();
        }
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.set(year, mon, day, h, min);
        return calendar.getTime();
    }

    private void buttonNowClicked() {
        setPickers(new Date());
    }
    private void buttonOkClicked() {
        if (mCallback != null) {
            mCallback.OnTimeSetterFinished(getPickers());
        }
        this.dismiss();
    }
    private void buttonCancelClicked() {
        this.dismiss();
    }
}