package net.proys.proysrail;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Deneme extends AppCompatActivity {
private EditText km_baslangıc;
private EditText km_son;
private EditText km_mesafe;

private String kmbaslangic="";
private String kmson="";
private String kmmesafe;
private int difference;
private Boolean flag=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_deneme);
        //km_baslangıc=findViewById(R.id.km_baslangıc);
        //km_son=findViewById(R.id.km_son);
        //km_mesafe=findViewById(R.id.mesafe);
km_mesafe.setOnFocusChangeListener(new View.OnFocusChangeListener() {
    @Override
    public void onFocusChange(View view, boolean b) {
if(b)
            flag=false;
    }
});
km_mesafe.addTextChangedListener(new TextWatcher() {


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


         if(flag==false)
         {

             if(!kmbaslangic.equals(""))
             {
                 kmmesafe=km_mesafe.getText().toString();
                 if(!kmmesafe.equals(""))
                 {
                     String son=  String.valueOf( Integer.valueOf(kmbaslangic)+Integer.valueOf(Integer.valueOf(kmmesafe)));
                     km_son.setText(son);
                     kmson=son;
                 }


             }

         }
    }


    @Override
    public void afterTextChanged(Editable editable) {

    }
});



        km_baslangıc.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                 if(!hasFocus)
                 {
                     km_baslangıc.setFilters(new InputFilter[] { new InputFilter.LengthFilter(7) });
                      kmbaslangic=km_baslangıc.getText().toString();
                     int length =km_baslangıc.getText().toString().length();
                      km_baslangıc.setText(leadingZeros(kmbaslangic,length));
                     if(!kmson.equals("")&&!kmbaslangic.equals(""))
                     {
                        flag=true;
                         difference= Math.abs(Integer.valueOf(kmbaslangic)-Integer.valueOf(kmson));
                         km_mesafe.setText(String.valueOf(difference));

                     }

                 }
                 if (hasFocus)
                 {
                     km_baslangıc.setFilters(new InputFilter[] { new InputFilter.LengthFilter(6) });
                    km_baslangıc.setText(kmbaslangic);

                 }
            }

        });



        km_son.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus)
                {
                    km_son.setFilters(new InputFilter[] { new InputFilter.LengthFilter(7) });
                    kmson=km_son.getText().toString();
                    int length =km_son.getText().toString().length();

                  
                        km_son.setText(leadingZeros(kmson,length));
                        if(!kmbaslangic.equals("")&&!kmson.equals(""))
                        {

                            flag=true;
                            difference= Math.abs(Integer.valueOf(kmbaslangic)-Integer.valueOf(kmson));
                            km_mesafe.setText(String.valueOf(difference));
                        }
                    


                }
                if (hasFocus)
                {
                    km_son.setFilters(new InputFilter[] { new InputFilter.LengthFilter(6) });
                    km_son.setText(kmson);




                }

            }
        });




        // 4 haneden az olanları sol tarafını 0 la dolduran fonksiyon
    }
    public final String leadingZeros(String s,int length) {
        if (s.length() >4)
        {
            return formatKm(s);
        }
        else if(s.length()==4)
            return s;
        else
        {
            return  formatKm( String.format("%0" + (4-s.length()) + "d%s", 0, s));

        }

    }
           // + lı format haline getiriyor
     public final String  formatKm(String s)
     {
      StringBuilder stringBuilder=new StringBuilder();
      stringBuilder.append(s);

        return     stringBuilder.insert(s.length()-3,"+").toString();

     }
     





}
