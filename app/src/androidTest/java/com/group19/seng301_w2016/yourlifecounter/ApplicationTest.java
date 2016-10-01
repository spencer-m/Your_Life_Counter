package com.group19.seng301_w2016.yourlifecounter;

import android.app.Application;

import android.app.Activity;
import android.app.Application;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ApplicationTestCase;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.regex.Pattern;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ActivityInstrumentationTestCase2<createNew> {
    public ApplicationTest() {
        super("com.group19.seng301_w2016.yourlifecounter",createNew.class);
    }

    public void	nameField(){ // first test
        fail("This is supposed to be letters"); // will print out "AssertionFailedError: This is supposed to be letters"
    }

    // test cases you can write once they are implemented
    public void testnameFieldDefault(){
        /*Activity test_createNew = getActivity();
        TextView nameField = new TextView(test_createNew); // access UI elements use "R.id"
        nameField.setText("Apple");
        assertEquals("Apple", nameField.getText().toString());
        // can change first value to be "" as well*/
        fail("Test not implemented");
    }

    public void testCategory(){
        /*String[] testArray;
        Activity test_createNew = getActivity();
        testArray= new String[]{
                "Early Meal", "Lunch", "Dinner", "Snack", "H2O"
        };
        Spinner spinner = new Spinner(test_createNew);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(test_createNew, android.R.layout.simple_spinner_item, testArray); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);
        if(testArray[0].equals("Early Meal")){
            spinner.setSelection(0);
            assertEquals("Early Meal", spinner.getItemAtPosition(0).toString());
        }*/
        fail("Test not implemented");
    }

    public void testServingSize(){
        /*Activity test_createNew = getActivity();
        TextView servingSizeField = new TextView(test_createNew);
        servingSizeField.setText("5");
        assertEquals("5", servingSizeField.getText().toString());
        */
        fail("Test not implemented");
    }

    public void testServingType(){
        /*String[] testArray;
        Activity test_createNew = getActivity();
        testArray = new String[]{
                "Gram(s)", "Cup(s)", "Litre(s)", "Piece(s)",
        };
        Spinner spinner = new Spinner(test_createNew);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(test_createNew, android.R.layout.simple_spinner_item, testArray); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);
        if(testArray[0].equals("Gram(s)")){
            spinner.setSelection(0);
            assertEquals("Gram(s)", spinner.getItemAtPosition(0).toString());
        }*/
        fail("Test not implemented");
    }

    public void testCalPerServing(){
        /*Activity test_createNew = getActivity();
        TextView servingSizeField = new TextView(test_createNew);
        servingSizeField.setText("500");
        assertEquals("500", servingSizeField.getText().toString());
        */
        fail("Test not implemented");
    }


}