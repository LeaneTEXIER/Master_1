package com.example.deffontaine.voyageapresvoyage.Others;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateInputMask implements TextWatcher {

    private String currentDate;
    private Date datecurrent_date;
    private SimpleDateFormat simpleDateFormat;
    private String defaultStr;
    private Calendar calendrier;
    private boolean isComplete;
    private EditText inputDate;

    public DateInputMask(EditText input, String dateStrDefault) {
        this.currentDate = "";
        this.simpleDateFormat = new SimpleDateFormat("ddMMyyyy");
        this.defaultStr = dateStrDefault;
        this.calendrier = Calendar.getInstance();
        if(input.getText().equals("")){
            this.datecurrent_date = null;
            this.isComplete = false;
        }else{
            try {
                this.datecurrent_date = simpleDateFormat.parse(input.getText().toString().replaceAll("[^\\d.]|\\.", ""));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            this.isComplete = true;
        }
        this.inputDate = input;
        this.inputDate.addTextChangedListener(this);
    }

    public boolean isComplete() {
        return this.isComplete;
    }

    public Date getDatecurrent_date() {
        return this.datecurrent_date;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.toString().equals(this.currentDate)) {
            return;
        }

        String dateWithoutSlash = s.toString().replaceAll("[^\\d.]|\\.", "");
        String currentDateWithoutSlash = this.currentDate.replaceAll("[^\\d.]|\\.", "");

        int dateLength = dateWithoutSlash.length();
        int pos = dateLength;

        for (int i = 2; i <= dateLength && i < 6; i += 2) {
            pos++;
        }

        if (dateWithoutSlash.equals(currentDateWithoutSlash)) pos--;

        if (dateWithoutSlash.length() < 8){
            dateWithoutSlash = dateWithoutSlash + this.defaultStr.substring(dateWithoutSlash.length());
            this.datecurrent_date = null;
            this.isComplete = false;
        }else{
            int jour  = Integer.parseInt(dateWithoutSlash.substring(0,2));
            int mois  = Integer.parseInt(dateWithoutSlash.substring(2,4));
            int annee = Integer.parseInt(dateWithoutSlash.substring(4,8));

            mois = mois < 1 ? 1 : mois > 12 ? 12 : mois;
            this.calendrier.set(Calendar.MONTH, mois-1);
            annee = annee < 1900 ? 1900 : annee;
            this.calendrier.set(Calendar.YEAR, annee);
            jour = jour < 1 ? 1 : (jour > this.calendrier.getActualMaximum(Calendar.DATE)) ? this.calendrier.getActualMaximum(Calendar.DATE) : jour;

            dateWithoutSlash = String.format("%02d%02d%02d",jour, mois, annee);

            try {
                this.datecurrent_date = simpleDateFormat.parse(dateWithoutSlash);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            this.isComplete = true;
        }

        this.currentDate = String.format("%s/%s/%s", dateWithoutSlash.substring(0, 2),
                                                     dateWithoutSlash.substring(2, 4),
                                                     dateWithoutSlash.substring(4, 8));

        pos = pos < 0 ? 0 : pos;
        this.inputDate.setText(this.currentDate);
        this.inputDate.setSelection(pos < this.currentDate.length() ? pos : this.currentDate.length());
    }

    @Override
    public void afterTextChanged(Editable s) {}

}