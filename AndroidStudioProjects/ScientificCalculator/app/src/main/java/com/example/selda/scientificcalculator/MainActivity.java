package com.example.selda.scientificcalculator;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    double firstNumber;
    EditText editTextScient;
    String processType;
    Button buttonClear,buttonDivided,buttonMultiple,buttonBack,buttonSeven,buttonEight,buttonNine,buttonMines,
    buttonFour,buttonFive,buttonSix,buttonPlus,buttonOne,buttonTwo,buttonThree,buttonMinesAndPlus,buttonPercentage,
    buttonZero,buttonComma,buttonEquals;

    EditText editTextProcessScreen;
    ListView listView;
    String processLine;
    List<String>processList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonClear=(Button)findViewById(R.id.buttonClear);
        buttonDivided=(Button)findViewById(R.id.buttonDivided);
        buttonMultiple=(Button)findViewById(R.id.buttonMultiple);
        buttonBack=(Button)findViewById(R.id.buttonBackSpace);
        buttonSeven=(Button)findViewById(R.id.buttonSeven);
        buttonEight=(Button)findViewById(R.id.buttonEight);
        buttonNine=(Button)findViewById(R.id.buttonNine);
        buttonMines=(Button)findViewById(R.id.buttonMines);
        buttonFour=(Button)findViewById(R.id.buttonFour);
        buttonFive=(Button)findViewById(R.id.buttonFive);
        buttonSix=(Button)findViewById(R.id.buttonSix);
        buttonPlus=(Button)findViewById(R.id.buttonPlus);
        buttonOne=(Button)findViewById(R.id.buttonOne);
        buttonTwo=(Button)findViewById(R.id.buttonTwo);
        buttonThree=(Button)findViewById(R.id.buttonThree);
        buttonMinesAndPlus=(Button)findViewById(R.id.buttonPlusMines);
        buttonPercentage=(Button)findViewById(R.id.buttonPercentage);
        buttonZero=(Button)findViewById(R.id.buttonZero);
        buttonComma=(Button)findViewById(R.id.buttonComma);
        buttonEquals=(Button)findViewById(R.id.buttonEquals);
        editTextProcessScreen=(EditText)findViewById(R.id.editText);
        listView=(ListView)findViewById(R.id.listViewProcessLines);

        buttonClear.setOnClickListener(this);
        buttonDivided.setOnClickListener(this);
        buttonMultiple.setOnClickListener(this);
        buttonBack.setOnClickListener(this);
        buttonSeven.setOnClickListener(this);
        buttonEight.setOnClickListener(this);
        buttonNine.setOnClickListener(this);
        buttonMines.setOnClickListener(this);
        buttonFour.setOnClickListener(this);
        buttonFive.setOnClickListener(this);
        buttonSix.setOnClickListener(this);
        buttonPlus.setOnClickListener(this);
        buttonOne.setOnClickListener(this);
        buttonTwo.setOnClickListener(this);
        buttonThree.setOnClickListener(this);
        buttonMinesAndPlus.setOnClickListener(this);
        buttonPercentage.setOnClickListener(this);
        buttonZero.setOnClickListener(this);
        buttonComma.setOnClickListener(this);
        buttonEquals.setOnClickListener(this);
        processList=new ArrayList<>();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                scientificCalc();
            }
        });

    }



    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.buttonClear:
                editTextProcessScreen.setText("");
                break;
            case R.id.buttonDivided:
                    dividedProcess();
                break;

            case R.id.buttonMultiple:
                    multipleProcess();
                break;
            case R.id.buttonBackSpace:
                    backSpace();
                break;
            case R.id.buttonSeven:
                newText("7");
                break;
            case R.id.buttonEight:
                newText("8");
                break;
            case R.id.buttonNine:
                newText("9");
                break;
            case R.id.buttonMines:
                    minesProcess();
                break;
            case R.id.buttonFour:
                newText("4");
                break;
            case R.id.buttonFive:
                newText(buttonFive.getText().toString());
                break;
            case R.id.buttonSix:
                newText("6");
                break;
            case R.id.buttonPlus:
                plusProcess();
                break;
            case R.id.buttonOne:
                newText("1");
                break;
            case R.id.buttonTwo:
                newText("2");
                break;
            case R.id.buttonThree:
                newText("3");
                break;
            case R.id.buttonPlusMines:
                plusAndMines();
                break;
            case R.id.buttonPercentage:
                percentage();
                break;
            case R.id.buttonZero:
                newText("0");
                break;
            case R.id.buttonComma:
                addComma();
                break;
            case R.id.buttonEquals:
                equalsProcess();
                break;
            default:
                break;
        }
    }

    private void scientificCalc() {
        setContentView(R.layout.scientificpad);
        editTextScient=(EditText)findViewById(R.id.editTextScient);

    }

    private void minesProcess() {
        if(!editTextProcessScreen.getText().toString().equals("")) {
            firstNumber = Double.valueOf(editTextProcessScreen.getText().toString());
            editTextProcessScreen.setText("");
            processType="mines";

        }
        else
            Toast.makeText(this, "lütfen önce sayı girin", Toast.LENGTH_SHORT).show();
    }

    private void multipleProcess() {
        if(!editTextProcessScreen.getText().toString().equals("")) {
            firstNumber = Double.valueOf(editTextProcessScreen.getText().toString());
            editTextProcessScreen.setText("");
            processType="multiple";

        }
        else
            Toast.makeText(this, "lütfen önce sayı girin", Toast.LENGTH_SHORT).show();
    }

    private void dividedProcess() {
        if(!editTextProcessScreen.getText().toString().equals("")) {
            firstNumber = Double.valueOf(editTextProcessScreen.getText().toString());
            editTextProcessScreen.setText("");
            processType="divided";

        }
        else
            Toast.makeText(this, "lütfen önce sayı girin", Toast.LENGTH_SHORT).show();
    }

    private void equalsProcess() {
        if(!editTextProcessScreen.getText().toString().equals("")){
            if(processType.equals("plus")) {
                processLine=String.valueOf(firstNumber)+" + "+editTextProcessScreen.getText().toString()+" = "+String.valueOf(firstNumber+Double.valueOf(editTextProcessScreen.getText().toString()));
                editTextProcessScreen.setText(String.valueOf(Double.valueOf(editTextProcessScreen.getText().toString()) + firstNumber));
                processList.add(processLine);
                showList();
            }
            else if (processType.equals("mines")){
                processLine=String.valueOf(firstNumber)+" - "+editTextProcessScreen.getText().toString()+" = "+String.valueOf(firstNumber-Double.valueOf(editTextProcessScreen.getText().toString()));
                editTextProcessScreen.setText(String.valueOf(firstNumber-Double.valueOf(editTextProcessScreen.getText().toString())));
                processList.add(processLine);
                showList();
            }
            else if (processType.equals("multiple")){
                processLine=String.valueOf(firstNumber)+" * "+editTextProcessScreen.getText().toString()+" = "+String.valueOf(firstNumber*Double.valueOf(editTextProcessScreen.getText().toString()));
                editTextProcessScreen.setText(String.valueOf(firstNumber*Double.valueOf(editTextProcessScreen.getText().toString())));
                processList.add(processLine);
                showList();
            }
            else if (processType.equals("divided")){
                if(Double.valueOf(editTextProcessScreen.getText().toString())!=0) {
                    processLine = String.valueOf(firstNumber) + " / " + editTextProcessScreen.getText().toString() + " = " + String.valueOf(firstNumber / Double.valueOf(editTextProcessScreen.getText().toString()));
                    editTextProcessScreen.setText(String.valueOf(firstNumber / Double.valueOf(editTextProcessScreen.getText().toString())));
                    processList.add(processLine);
                    showList();
                }
                else
                    Toast.makeText(this, "0 ile bölme işlemi yapılmaz", Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(this, "belirlenemeyen işlem adı", Toast.LENGTH_SHORT).show();

        }
        else
            Toast.makeText(this, "2. sayıyı girin", Toast.LENGTH_SHORT).show();
    }

    private void showList() {
        ArrayAdapter arrayAdapter=new ArrayAdapter(MainActivity.this, R.layout.customtext,R.id.textViewMy,processList);
        listView.setAdapter(arrayAdapter);

    }

    private void plusProcess() {
        if(!editTextProcessScreen.getText().toString().equals("")) {
            firstNumber = Double.valueOf(editTextProcessScreen.getText().toString());
            editTextProcessScreen.setText("");
            processType="plus";

        }
        else
            Toast.makeText(this, "lütfen önce sayı girin", Toast.LENGTH_SHORT).show();
    }

    private void percentage() {
        if(!editTextProcessScreen.getText().toString().equals("")){
            editTextProcessScreen.setText(String.valueOf(Double.valueOf(editTextProcessScreen.getText().toString())*0.01));
        }
    }

    private void addComma() {

        if(!editTextProcessScreen.getText().toString().equals("")){
            if(editTextProcessScreen.getText().toString().contains(",")==false){
                editTextProcessScreen.setText(editTextProcessScreen.getText().toString()+",");
            }
        }
        else
            editTextProcessScreen.setText("0,");
    }

    private void backSpace() {
        if(!editTextProcessScreen.getText().toString().equals("")){
            editTextProcessScreen.setText(editTextProcessScreen.getText().toString().substring(0,editTextProcessScreen.getText().toString().length()-1));
        }
    }

    private void plusAndMines() {
        if(!editTextProcessScreen.getText().toString().startsWith("-")){
            editTextProcessScreen.setText("-"+editTextProcessScreen.getText().toString());
        }
        else{
            editTextProcessScreen.setText(editTextProcessScreen.getText().toString().substring(1));
        }
    }

    private void newText(String str) {
        editTextProcessScreen.setText(editTextProcessScreen.getText().toString()+str);
    }

    public void processClick(View view) {

        String s=((Button) view).getText().toString();
        if(s.equals("sin")){
            editTextScient.setText(String.valueOf(Math.sin(Double.valueOf(editTextScient.getText().toString()))));
        }
        else if(s.equals("cos")){
            editTextScient.setText(String.valueOf(Math.cos(Double.valueOf(editTextScient.getText().toString()))));
        }
        else if(s.equals("log")){
            editTextScient.setText(String.valueOf(Math.log(Double.valueOf(editTextScient.getText().toString()))));
        }
        else if(s.equals("tan")){
            editTextScient.setText(String.valueOf(Math.tan(Double.valueOf(editTextScient.getText().toString()))));
        }
        else if(s.equals("pow")){
            editTextScient.setText(String.valueOf(Math.pow(Double.valueOf(editTextScient.getText().toString()),2)));
        }
        else if(s.equals("+/-")){
            if(!editTextScient.getText().toString().startsWith("-")){
                editTextScient.setText("-"+editTextScient.getText().toString());
            }
            else{
                editTextScient.setText(editTextScient.getText().toString().substring(1));
            }
        }
        else if(s.equals("back")){
            if(!editTextScient.getText().toString().equals("")){
                editTextScient.setText(editTextScient.getText().toString().substring(0,editTextScient.getText().toString().length()-1));
            }
        }
        else if(s.equals("C")){
            editTextScient.setText("");
        }
        else if(s.equals(",")){
            if(!editTextScient.getText().toString().equals("")){
                if(editTextScient.getText().toString().contains(",")==false){
                    editTextScient.setText(editTextScient.getText().toString()+",");
                }
            }
            else
                editTextScient.setText("0,");
        }
        else
            Toast.makeText(this, "henüz tanımlanmayan işlem girdiniz", Toast.LENGTH_SHORT).show();
    }

    public void rakamClick(View view) {
        editTextScient.setText(editTextScient.getText().toString()+((Button) view).getText().toString());

    }

    public void backToBasic(View view) {
        Intent intent = new Intent (this, MainActivity.class);
        startActivity(intent);
    }

    public void ToScientific(View view) {
        scientificCalc();
    }
}
