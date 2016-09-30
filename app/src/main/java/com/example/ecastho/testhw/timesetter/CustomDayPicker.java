package com.example.ecastho.testhw.timesetter;

import android.app.DatePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.ecastho.testhw.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CustomDayPicker extends Fragment {
    private int mYear, mMonth, mDayOfMonth;
    private ImageButton mButtonPre;
    private ImageButton mButtonNext;
    private TextView mEditTextCurrentDay;

    private OnFragmentInteractionListener mListener;

    public CustomDayPicker() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_custom_day_picker, container, false);
        mButtonPre = (ImageButton)view.findViewById(R.id.buttonPre);
        mButtonNext = (ImageButton)view.findViewById(R.id.buttonNext);
        mEditTextCurrentDay = (TextView) view.findViewById(R.id.editTextCurrentDate);

        mButtonPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNewDateFromButton(-1);
            }
        });
        mEditTextCurrentDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextCurrentDateClicked();
            }
        });
        mButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNewDateFromButton(1);
            }
        });
        return view;
    }

    public void setDay(int year, int month, int dayOfMonth) {
        mYear=year;mMonth=month;mDayOfMonth=dayOfMonth;
        updateCurrentDate(0);
    }

    public int getYear() {
        return mYear;
    }
    public int getMonth() {
        return mMonth;
    }
    public int getDayOfMonth() {
        return mDayOfMonth;
    }

    private void setNewDateFromButton(int increment) {
        Calendar cal = Calendar.getInstance();
        cal.set(mYear,mMonth,mDayOfMonth);
        cal.add(Calendar.DATE, increment);
        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH);
        mDayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        updateCurrentDate(increment);
    }
    private void editTextCurrentDateClicked() {
        DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                mYear=year;mMonth=month;mDayOfMonth=dayOfMonth;
                updateCurrentDate(0);
            }
        };


        new DatePickerDialog(getContext(), dateListener, mYear, mMonth, mDayOfMonth).show();


    }

    private void updateCurrentDate(int increment) {
        Calendar cal = Calendar.getInstance();
        cal.set(mYear,mMonth,mDayOfMonth);
        String CurrentString = get_textToDisplayInUI(cal);
        mEditTextCurrentDay.setText(CurrentString);
        mEditTextCurrentDay.startAnimation(AnimationUtils.loadAnimation(getContext(), android.R.anim.fade_in));
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

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
