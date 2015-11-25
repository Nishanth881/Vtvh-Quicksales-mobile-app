package com.sevya.vtvhmobile;

/**
 * Created by abhinaym on 24/10/15.
 */
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import com.sevya.vtvhmobile.db.DataBaseAdapter;
import com.sevya.vtvhmobile.models.ResponseStatus;
import com.sevya.vtvhmobile.models.UserModel;
import com.sevya.vtvhmobile.util.SOAPServices;
import com.sevya.vtvhmobile.webservices.SOAPServiceClient;
import com.sevya.vtvhmobile.webservices.ServiceParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditCustomer extends AppCompatActivity implements View.OnTouchListener {

    DataBaseAdapter dataBaseHelper;
    Toolbar mToolbar;
    Cursor cursor;
    Context context;

    EditText phnnum;
    EditText cname;
    EditText cnum;
    EditText compName;
    EditText cgen;
    EditText cpro;
    EditText cln;
    EditText cadd;
    EditText cadd1;
    EditText cadd2;
    EditText cadd3;
    EditText cmail;

    ImageButton cbname;
    ImageButton cbnum;
    ImageButton cbcpmny;
    ImageButton cbmail;
    ImageButton cbadd;
    ImageButton cbadd1;
    ImageButton cbadd2;
    ImageButton cbadd3;
    ImageButton cbln;
    ImageButton cbpro;
    ImageButton cbgen;

    String cusName;
    String cusNum;
    String cusGen;
    String cusLn;
    String cusAdd;
    String cusAdd1;
    String cusEmail;
    String cusCompNmae;
    String cusActid;
    String cPro;


    Spinner spinner1;
    JSONArray array;

    ResponseStatus status;
    String actid;
    int acctId;
    UserModel userModel;

    RelativeLayout genderlayout;

    public RadioGroup rdg;
    public RadioGroup compGroup;
    public  RadioButton male;
    public RadioButton female;
    public  RadioButton yes;
    public RadioButton no;
    public String selectedType="Male";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_customer);
        dataBaseHelper=new DataBaseAdapter(this);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.backarrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setDisplayShowHomeEnabled(true);

        dataBaseHelper=new DataBaseAdapter(this);



        cname = (EditText) findViewById(R.id.cname);
        cnum = (EditText) findViewById(R.id.cnum);
        compName = (EditText) findViewById(R.id.compName);
        cgen = (EditText) findViewById(R.id.cgen);
        cpro = (EditText) findViewById(R.id.cpro);
        cln = (EditText) findViewById(R.id.cln);
        cadd = (EditText) findViewById(R.id.cadd);
        cadd1 = (EditText) findViewById(R.id.cadd1);
        cadd2 = (EditText) findViewById(R.id.cadd2);
        cadd3 = (EditText) findViewById(R.id.cadd3);
        cmail = (EditText) findViewById(R.id.cmail);

        cbname=(ImageButton)findViewById(R.id.cbname);
        cbnum=(ImageButton)findViewById(R.id.cbnum);
        cbcpmny=(ImageButton)findViewById(R.id.cbcpmny);
        cbmail=(ImageButton)findViewById(R.id.cbmail);
        cbadd=(ImageButton)findViewById(R.id.cbadd);
        cbadd=(ImageButton)findViewById(R.id.cbadd);
        cbadd1=(ImageButton)findViewById(R.id.cbadd1);
        cbadd2=(ImageButton)findViewById(R.id.cbadd2);
        cbadd3=(ImageButton)findViewById(R.id.cbadd3);
        cbln=(ImageButton)findViewById(R.id.cbln);
        cbpro=(ImageButton)findViewById(R.id.cbpro);
        cbgen=(ImageButton)findViewById(R.id.cbgen);

        cname.setOnTouchListener(this);
        cnum.setOnTouchListener(this);
        compName.setOnTouchListener(this);
        cadd.setOnTouchListener(this);
        cadd1.setOnTouchListener(this);
        cadd2.setOnTouchListener(this);
        cadd3.setOnTouchListener(this);
        cln.setOnTouchListener(this);
        cmail.setOnTouchListener(this);
        cpro.setOnTouchListener(this);
        cgen.setOnTouchListener(this);

        cbname.setOnTouchListener(this);
        cbnum.setOnTouchListener(this);
        cbcpmny.setOnTouchListener(this);
        cbadd.setOnTouchListener(this);
        cbadd1.setOnTouchListener(this);
        cbadd2.setOnTouchListener(this);
        cbadd3.setOnTouchListener(this);
        cbln.setOnTouchListener(this);
        cbmail.setOnTouchListener(this);
        cbgen.setOnTouchListener(this);
        cbpro.setOnTouchListener(this);

        genderlayout=(RelativeLayout)findViewById(R.id.genderlayout);


        getDetails();
        addItemsOnSpinner1();

    }


    public void getDetails() {



        try {
                Intent intent=getIntent();
            String name=intent.getStringExtra("cname");
            String compname=intent.getStringExtra("compName");
            String num=intent.getStringExtra("cnum");
            String pro=intent.getStringExtra("cpro");
            String mail=intent.getStringExtra("cmail");
            String add=intent.getStringExtra("cadd");
            String add1=intent.getStringExtra("cadd1");
            String add2=intent.getStringExtra("cadd2");
            String add3=intent.getStringExtra("cadd3");
            String gen=intent.getStringExtra("rb");
            String ln=intent.getStringExtra("cln");
             actid=intent.getStringExtra("actId");


           acctId=Integer.parseInt(actid);

            cname.setText(name);
            cnum.setText(num);
            compName.setText(compname);
            cgen.setText(gen);
            cpro.setText(pro);
            cln.setText(ln);
            cadd.setText(add);
            cadd1.setText(add1);
            cadd2.setText(add2);
            cadd3.setText(add3);
            cmail.setText(mail);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(View view) {
        ButtonAnimation.animation(view);



        compGroup=(RadioGroup)findViewById(R.id.company);
        yes=(RadioButton)findViewById(R.id.compyes);
        male = (RadioButton) findViewById(R.id.radioMale);
        no=(RadioButton)findViewById(R.id.compno);
        male.setChecked(true);
        no.setChecked(true);
        genderlayout.setVisibility(View.VISIBLE);
        compGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.compyes) {
                    genderlayout.setVisibility(View.GONE);
                    selectedType = "";


                } else if (i == R.id.compno) {
                    genderlayout.setVisibility(View.VISIBLE);
                    rdg = (RadioGroup) findViewById(R.id.radioSex);
                    male = (RadioButton) findViewById(R.id.radioMale);
                    female = (RadioButton) findViewById(R.id.radioFemale);

                    male.setChecked(true);

                    rdg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup radioGroup, int i) {

                            if (i == R.id.radioMale) {
                                selectedType = "Male";


                            } else if (i == R.id.radioFemale) {
                                selectedType = "Female";

                            }
                        }
                    });

                }
            }
        });

        cusName=cname.getText().toString();
        cusNum=cnum.getText().toString();
        cusAdd=cadd.getText().toString();
        cusAdd1=cadd1.getText().toString();
        cusEmail=cmail.getText().toString();
        cusCompNmae=compName.getText().toString();
        cusGen=selectedType;
        cusLn=cln.getText().toString();
        cPro=spinner1.getSelectedItem().toString();
        userModel=new UserModel();
        userModel.setPrimaryActID(acctId);
        userModel.setActName(cusName);
        userModel.setMobileNo(cusNum);
        userModel.setAddress1(cusAdd1);
        userModel.setPhone(cusLn);
        userModel.setFlatNo(cusAdd);
        userModel.setGender(selectedType);
        //userModel.setCity(cAdd2);
        //userModel.setCountry(cAdd3);
        userModel.setState("");
        userModel.setStreet("");
        userModel.setSurName("");
        userModel.setTinNo("");
        userModel.setDistrict("");
        userModel.setEmail(cusEmail);
        userModel.setIsPrimaryAct("");
        userModel.setPin("");
        userModel.setCompanyName(cusCompNmae);
        userModel.setMandal("");
        userModel.setDuplicateIds("");
        userModel.setUserId(new Integer(76));
        userModel.setProfession(cPro);


        if (cname.getText().toString().length() == 0) {

            cname.setError("Please enter the required details");

        } else if (cnum.getText().toString().length() != 10) {
            cnum.setError("please enter 10 digit Mobile number");
        } else if (cadd.getText().toString().length() <= 0) {
            cadd.setError("Please enter Valid Address");
        }
        else if (!isEmailValid(cusEmail))
            cmail.setError("please enter Valid email");
            else {
                   Thread  thread=new Thread() {
                    public void run() {
                        SOAPServiceClient soapServiceClient=new SOAPServiceClient();
                        ServiceParams modalParam = new ServiceParams(userModel,"userModel", UserModel.class);
                        {
                            try {
                                status = (ResponseStatus) soapServiceClient.callService(SOAPServices.getServices("updateCustomerService"), modalParam);

                                if (status.getStatusCode() == 200) {
                                    JSONObject object = new JSONObject(status.getStatusResponse());



                                            EditCustomer.this.runOnUiThread(new Thread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    Intent i = new Intent(EditCustomer.this, ReceiveDetails.class);
                                                    i.putExtra("cname", cusName);
                                                    i.putExtra("cnum", cusNum);
                                                    i.putExtra("compName", cusCompNmae);
                                                    i.putExtra("rb", cusGen);
                                                      i.putExtra("cpro", cPro);
                                                    i.putExtra("cmail", cusEmail);
                                                    i.putExtra("cadd",cusAdd);
                                                    i.putExtra("cln", cusLn);
                                                    i.putExtra("cadd1", cusAdd1);
                                                    //i.putExtra("cadd2", cAdd2);
                                                    //i.putExtra("cadd3", cAdd3);
                                                    i.putExtra("actId",actid);

                                                    startActivity(i);
                                                }
                                            }));


                                }
                                else if(status.getStatusCode()==500){
                                    EditCustomer.this.runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(EditCustomer.this, "" + status.getStatusResponse(), Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                };
                thread.start();
            }


        }
    public void addItemsOnSpinner1() {

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        List<String> list = new ArrayList<String>();
        list.add("Select Category");
        list.add("Business");
        list.add("Agriculture");
        list.add("Govt. Employee");
        list.add("Private Employee");
        list.add("Others");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapter);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ScrollView sv = (ScrollView) findViewById(R.id.scrollview);
                sv.fullScroll(view.getTop());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public  boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{1,4}$";
        CharSequence inputStr = email;
        if(email.length()>0) {
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(inputStr);
            if (matcher.matches()) {
                isValid = true;
            }

        }else{
            isValid=true;
        }
        return isValid;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_customer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onTouch(View v,MotionEvent event) {

            switch(v.getId())
            {
                case R.id.cname:

                    cbname.setVisibility(View.VISIBLE);
                    cbnum.setVisibility(View.INVISIBLE);
                    cbcpmny.setVisibility(View.INVISIBLE);
                    cbln.setVisibility(View.INVISIBLE);
                    cbadd.setVisibility(View.INVISIBLE);
                    cbadd1.setVisibility(View.INVISIBLE);
                    cbadd2.setVisibility(View.INVISIBLE);
                    cbadd3.setVisibility(View.INVISIBLE);
                    cbmail.setVisibility(View.INVISIBLE);
                    cbgen.setVisibility(View.INVISIBLE);
                    cbpro.setVisibility(View.INVISIBLE);
                    break;
                case R.id.cbname:
                    cname.setText("");
                    break;
                case R.id.cnum:

                    cbname.setVisibility(View.INVISIBLE);
                    cbnum.setVisibility(View.VISIBLE);
                    cbcpmny.setVisibility(View.INVISIBLE);
                    cbln.setVisibility(View.INVISIBLE);
                    cbadd.setVisibility(View.INVISIBLE);
                    cbadd1.setVisibility(View.INVISIBLE);
                    cbadd2.setVisibility(View.INVISIBLE);
                    cbadd3.setVisibility(View.INVISIBLE);
                    cbmail.setVisibility(View.INVISIBLE);
                    cbgen.setVisibility(View.INVISIBLE);
                    cbpro.setVisibility(View.INVISIBLE);
                    break;
                case R.id.cbnum:
                    cnum.setText("");
                    break;
                case R.id.compName:

                    cbname.setVisibility(View.INVISIBLE);
                    cbnum.setVisibility(View.INVISIBLE);
                    cbcpmny.setVisibility(View.VISIBLE);
                    cbln.setVisibility(View.INVISIBLE);
                    cbadd.setVisibility(View.INVISIBLE);
                    cbadd1.setVisibility(View.INVISIBLE);
                    cbadd2.setVisibility(View.INVISIBLE);
                    cbadd3.setVisibility(View.INVISIBLE);
                    cbmail.setVisibility(View.INVISIBLE);
                    cbgen.setVisibility(View.INVISIBLE);
                    cbpro.setVisibility(View.INVISIBLE);
                    break;
                case R.id.cbcpmny:
                    compName.setText("");
                    break;
                case R.id.cln:

                    cbname.setVisibility(View.INVISIBLE);
                    cbnum.setVisibility(View.INVISIBLE);
                    cbcpmny.setVisibility(View.INVISIBLE);
                    cbln.setVisibility(View.VISIBLE);
                    cbadd.setVisibility(View.INVISIBLE);
                    cbadd1.setVisibility(View.INVISIBLE);
                    cbadd2.setVisibility(View.INVISIBLE);
                    cbadd3.setVisibility(View.INVISIBLE);
                    cbmail.setVisibility(View.INVISIBLE);
                    cbgen.setVisibility(View.INVISIBLE);
                    cbpro.setVisibility(View.INVISIBLE);
                    break;
                case R.id.cbln:
                    cln.setText("");
                    break;
                case R.id.cadd:

                    cbname.setVisibility(View.INVISIBLE);
                    cbnum.setVisibility(View.INVISIBLE);
                    cbcpmny.setVisibility(View.INVISIBLE);
                    cbln.setVisibility(View.INVISIBLE);
                    cbadd.setVisibility(View.VISIBLE);
                    cbadd1.setVisibility(View.INVISIBLE);
                    cbadd2.setVisibility(View.INVISIBLE);
                    cbadd3.setVisibility(View.INVISIBLE);
                    cbmail.setVisibility(View.INVISIBLE);
                    cbgen.setVisibility(View.INVISIBLE);
                    cbpro.setVisibility(View.INVISIBLE);
                    break;
                case R.id.cbadd:
                    cadd.setText("");
                    break;
                case R.id.cadd1:

                    cbname.setVisibility(View.INVISIBLE);
                    cbnum.setVisibility(View.INVISIBLE);
                    cbcpmny.setVisibility(View.INVISIBLE);
                    cbln.setVisibility(View.INVISIBLE);
                    cbadd.setVisibility(View.INVISIBLE);
                    cbadd1.setVisibility(View.VISIBLE);
                    cbadd2.setVisibility(View.INVISIBLE);
                    cbadd3.setVisibility(View.INVISIBLE);
                    cbmail.setVisibility(View.INVISIBLE);
                    cbgen.setVisibility(View.INVISIBLE);
                    cbpro.setVisibility(View.INVISIBLE);
                    break;
                case R.id.cbadd1:
                    cadd1.setText("");
                    break;
                case R.id.cadd2:

                    cbname.setVisibility(View.INVISIBLE);
                    cbnum.setVisibility(View.INVISIBLE);
                    cbcpmny.setVisibility(View.INVISIBLE);
                    cbln.setVisibility(View.INVISIBLE);
                    cbadd.setVisibility(View.INVISIBLE);
                    cbadd1.setVisibility(View.INVISIBLE);
                    cbadd2.setVisibility(View.VISIBLE);
                    cbadd3.setVisibility(View.INVISIBLE);
                    cbmail.setVisibility(View.INVISIBLE);
                    cbgen.setVisibility(View.INVISIBLE);
                    cbpro.setVisibility(View.INVISIBLE);
                    break;
                case R.id.cbadd2:
                    cadd2.setText("");
                    break;
                case R.id.cadd3:

                    cbname.setVisibility(View.INVISIBLE);
                    cbnum.setVisibility(View.INVISIBLE);
                    cbcpmny.setVisibility(View.INVISIBLE);
                    cbln.setVisibility(View.INVISIBLE);
                    cbadd.setVisibility(View.INVISIBLE);
                    cbadd1.setVisibility(View.INVISIBLE);
                    cbadd2.setVisibility(View.INVISIBLE);
                    cbadd3.setVisibility(View.VISIBLE);
                    cbmail.setVisibility(View.INVISIBLE);
                    cbgen.setVisibility(View.INVISIBLE);
                    cbpro.setVisibility(View.INVISIBLE);
                    break;
                case R.id.cbadd3:
                    cadd3.setText("");
                    break;
                case R.id.cmail:

                    cbname.setVisibility(View.INVISIBLE);
                    cbnum.setVisibility(View.INVISIBLE);
                    cbcpmny.setVisibility(View.INVISIBLE);
                    cbln.setVisibility(View.INVISIBLE);
                    cbadd.setVisibility(View.INVISIBLE);
                    cbadd1.setVisibility(View.INVISIBLE);
                    cbadd2.setVisibility(View.INVISIBLE);
                    cbadd3.setVisibility(View.INVISIBLE);
                    cbmail.setVisibility(View.VISIBLE);
                    cbgen.setVisibility(View.INVISIBLE);
                    cbpro.setVisibility(View.INVISIBLE);
                    break;
                case R.id.cbmail:
                    cmail.setText("");
                    break;
                case R.id.cpro:

                    cbname.setVisibility(View.INVISIBLE);
                    cbnum.setVisibility(View.INVISIBLE);
                    cbcpmny.setVisibility(View.INVISIBLE);
                    cbln.setVisibility(View.INVISIBLE);
                    cbadd.setVisibility(View.INVISIBLE);
                    cbadd1.setVisibility(View.INVISIBLE);
                    cbadd2.setVisibility(View.INVISIBLE);
                    cbadd3.setVisibility(View.INVISIBLE);
                    cbmail.setVisibility(View.INVISIBLE);
                    cbpro.setVisibility(View.VISIBLE);
                    cbgen.setVisibility(View.INVISIBLE);

                    break;
                case R.id.cbpro:
                    cpro.setText("");
                    break;
                case R.id.cgen:

                    cbname.setVisibility(View.INVISIBLE);
                    cbnum.setVisibility(View.INVISIBLE);
                    cbcpmny.setVisibility(View.INVISIBLE);
                    cbln.setVisibility(View.INVISIBLE);
                    cbadd.setVisibility(View.INVISIBLE);
                    cbadd1.setVisibility(View.INVISIBLE);
                    cbadd2.setVisibility(View.INVISIBLE);
                    cbadd3.setVisibility(View.INVISIBLE);
                    cbmail.setVisibility(View.INVISIBLE);
                    cbgen.setVisibility(View.VISIBLE);
                    cbpro.setVisibility(View.INVISIBLE);
                    break;
                case R.id.cbgen:
                    cgen.setText("");
                    break;


        }
        return false;
    }
}
