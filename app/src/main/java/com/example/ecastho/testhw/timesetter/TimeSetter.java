package com.example.ecastho.testhw.timesetter;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.ecastho.testhw.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class TimeSetter extends DialogFragment {
    private TimePicker mTimePicker;
    int mYear, mMonth, mDayOfMonth;
    private TextView mTextViewCurrentDay;
    private Date mDate;
    private OnTimeSetterFinishedListener mCallback;

    public TimeSetter() {
        // Empty constructor required for DialogFragment
    }

    public static TimeSetter newInstance(Date date) {
        TimeSetter ts = new TimeSetter();
        Bundle args = new Bundle();
        args.putLong("longdate", date.getTime());
        ts.setArguments(args);
        return ts;
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
        mTextViewCurrentDay = (TextView)view.findViewById(R.id.textViewCurrentDate);
        //mDayPicker = (CustomDayPicker) getFragmentManager().findFragmentById(R.id.dayPicker);
        mTimePicker.setIs24HourView(true);
        setPickers(mDate);
        view.findViewById(R.id.buttonPre).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNewDateFromButton(-1);
            }
        });
        view.findViewById(R.id.buttonNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNewDateFromButton(1);
            }
        });
        view.findViewById(R.id.textViewCurrentDate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewCurrentDateClicked();
            }
        });

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

    private Date getPickers() {
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
        calendar.set(mYear, mMonth, mDayOfMonth, h, min);
        return calendar.getTime();
    }

    private void setPickers(Date date) {

        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
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
        updateCurrentDate(0);
    }

    private void setNewDateFromButton(int increment) {
        Calendar cal = Calendar.getInstance();
        cal.set(mYear, mMonth, mDayOfMonth);
        cal.add(Calendar.DATE, increment);
        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH);
        mDayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        updateCurrentDate(increment);
    }
    private void updateCurrentDate(int increment) {
        Calendar cal = Calendar.getInstance();
        cal.set(mYear,mMonth,mDayOfMonth);
        String CurrentString = get_textToDisplayInUI(cal);
        mTextViewCurrentDay.setText(CurrentString);
        mTextViewCurrentDay.startAnimation(AnimationUtils.loadAnimation(getContext(), android.R.anim.fade_in));
    }
    private String get_textToDisplayInUI(Calendar cal) {
        String retStr;
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM");
        Calendar now = Calendar.getInstance();
        long t = cal.get(Calendar.DATE) - now.get(Calendar.DATE);

        switch (cal.get(Calendar.DATE) - now.get(Calendar.DATE))
        {
            case 0:
                retStr = getResources().getString(R.string.today);
                break;
            case 1:
                retStr = getResources().getString(R.string.tomorrow);
                break;
            case -1:
                retStr = getResources().getString(R.string.yesterday);
                break;
            default:
                retStr = sdf.format(cal.getTime());
                break;
        }
        return retStr;
    }
    private void textViewCurrentDateClicked() {
        DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                mYear=year;mMonth=month;mDayOfMonth=dayOfMonth;
                updateCurrentDate(0);
            }
        };
        new DatePickerDialog(getContext(), dateListener, mYear, mMonth, mDayOfMonth).show();
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
    public interface OnTimeSetterFinishedListener {
        void OnTimeSetterFinished(Date date);
    }
}