package net.proys.proysrail;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import net.proys.proysrail.Adapters.PuantajMainAdapter;
import net.proys.proysrail.Adapters.makine_isletme_popup_adapter;
import net.proys.proysrail.Fragments.MakineFault;
import net.proys.proysrail.Fragments.MakineFuel;
import net.proys.proysrail.Fragments.MakinePopup;
import net.proys.proysrail.Fragments.MakinePuantaj;
import net.proys.proysrail.Fragments.MakineStatement;
import net.proys.proysrail.Internfaces.Visibility;
import net.proys.proysrail.Items.MakinePopupItem;

import java.util.ArrayList;

import static net.proys.proysrail.R.drawable.background;
import static net.proys.proysrail.R.drawable.navbar_back;

public class Makine_Isletme_MainFrame extends AppCompatActivity  implements Visibility {
   RelativeLayout ustbar;
   TextView mainTitle,subTitle,machineName;
   ImageView more;
    LinearLayout navbar;
   LinearLayout choose_machine;
    Intent coming_intent;
    Dialog dialog;
     makine_isletme_popup_adapter adapter;


    FragmentTransaction ft;

    private ImageView puantaj,fuel,fault,statement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makine__isletme__main_frame);
         init();
        eventHandler();
        fragmentChooser("PUANTAJ");
             if(getIntent().hasExtra("Fragment"))
             {
                if(TextUtils.equals("PUANTAJ",getIntent().getStringExtra("Fragment").trim().toUpperCase()))
                {
                    if(getIntent().hasExtra("Position"))
                        fragmentChooser("PUANTAJ");
                    else
                    fragmentChooser("PUANTAJ");

                }




             }

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void fragmentChooser(String fragmentTag) {
        ft=getSupportFragmentManager().beginTransaction();
        if(fragmentTag=="PUANTAJ")
        {

            choose_machine.setVisibility(View.VISIBLE);
            more.setVisibility(View.VISIBLE);
            ft.replace(R.id.container,new MakinePuantaj());

            ft.addToBackStack(null);

        }
        else if(fragmentTag=="FUEL")
        {
            ft.replace(R.id.container,new MakineFuel());
            ft.addToBackStack(null);

        }
        else if(fragmentTag=="FAULT")
        {
            ft.replace(R.id.container,new MakineFault());
            ft.addToBackStack(null);

        }
      else  if(fragmentTag=="STATEMENT")
        {
            ft.replace(R.id.container,new MakineStatement());
            ft.addToBackStack(null);

        }
      else if(fragmentTag=="POPUPMAKİNE")
        {
            ustbar.setAlpha(0.2f);


             navbar.setVisibility(View.GONE);
             ft.replace(R.id.container,new MakinePopup());
             ft.addToBackStack(null);
             choose_machine.setVisibility(View.GONE);
             more.setVisibility(View.GONE);

        }
  ft.commit();








    }

    private void eventHandler() {
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Makine_Isletme_MainFrame.this, "Henüz Hazır Değil", Toast.LENGTH_SHORT).show();
            }
        });
        choose_machine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                        showDialog();




            }


        });


        fuel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragmentChooser("FUEL");


            }
        });
        puantaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragmentChooser("PUANTAJ");

            }
        });
        fault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragmentChooser("FAULT");
            }
        });
        statement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragmentChooser("STATEMENT");
            }
        });

    }


    private void showDialog() {
        fragmentChooser("POPUPMAKİNE");





    }

    private void init() {
     navbar =findViewById(R.id.navbar);

        ustbar=findViewById(R.id.ust_bar);
        mainTitle=ustbar.findViewById(R.id.main_title);
        more=findViewById(R.id.more);
        choose_machine=findViewById(R.id.choose_machine);

        //navbar  iconss**************
        puantaj=findViewById(R.id.imageImalat);
        fuel=findViewById(R.id.fuel);
        fault=findViewById(R.id.fauult);
        statement=findViewById(R.id.statement);




    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        navbar.setVisibility(View.VISIBLE);
        ustbar.setAlpha(1f);
    }
}
