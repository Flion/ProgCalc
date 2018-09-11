package com.flion.progcalc;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    /* variable definitions */
    /*
        At beginning, calculator registers are empty, integer, quad-word storage, and
        decimal by default.
     */
    String inputStr;
    boolean integer_flag;
    boolean negative_flag;
    boolean conversion_error;
    boolean single_mode;    // true = hex and false = binary
    boolean double_mode;    // true = hex and false = binary
    boolean ones_mode;      // true = hex and false = binary
    boolean twos_mode;      // true = hex and false = binary
    long int_total;
    long int_input;
    long int_undo_op;
    long int_memory;
    double float_total;
    double float_input;
    double float_undo_op;
    double float_memory;
    int input_base;     // 0 = hex, 1 = decimal, 2 = octal, 3 = binary
    int data_size;      // 0 = byte, 1 = word, 2 = double-word, 3 = quad_word
    int binary_display; // 0 = binary, 1 = 1s complement, 2 = 2s complement,
                        // 3 = single precision float, 4 = double precision float

    /*
        UI items are defined here to be found by id at startup
     */
    TextView mTextViewInput;
    TextView mTextViewHexSelect;
    TextView mTextViewDecimalSelect;
    TextView mTextViewOctalSelect;
    TextView mTextViewBinarySelect;
    TextView mTextView1sModeSelect;
    TextView mTextView2sModeSelect;
    TextView mTextViewHexOut;
    TextView mTextViewDecimalOut;
    TextView mTextViewOctalOut;
    TextView mTextViewBinaryOutUpper;
    TextView mTextViewBinaryOutLower;
    Button mButton1sComplement;
    Button mButton2sComplement;
    Button mButtonFloatSingle;
    Button mButtonFloatDouble;
    Button mButtonShiftLeft;
    Button mButtonShiftRight;
    Button mButtonRotateLeft;
    Button mButtonRotateRight;
    Button mButtonWordSize;
    Button mButtonBits;
    Button mButtonAnd;
    Button mButtonOr;
    Button mButtonXor;
    Button mButtonMemStore;
    Button mButtonMemAdd;
    Button mButtonMemRecall;
    Button mButtonComplement;
    Button mButtonModulus;
    Button mButtonClearEntry;
    Button mButtonClearAll;
    Button mButtonA;
    Button mButtonB;
    Button mButtonC;
    Button mButtonD;
    Button mButtonE;
    Button mButtonF;
    Button mButton0;
    Button mButton1;
    Button mButton2;
    Button mButton3;
    Button mButton4;
    Button mButton5;
    Button mButton6;
    Button mButton7;
    Button mButton8;
    Button mButton9;
    Button mButtonDivide;
    Button mButtonMultiply;
    Button mButtonMinus;
    Button mButtonPlus;
    Button mButtonSign;
    Button mButtonRadix;
    Button mButtonEquals;
    Button mButtonOpenParen;
    Button mButtonCloseParen;
    ImageButton mImageButtonBackspace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set default starting values
        inputStr = "";
        integer_flag = true;
        negative_flag = false;
        conversion_error = false;
        single_mode = false;    // true = hex and false = binary
        double_mode = false;    // true = hex and false = binary
        ones_mode = false;      // true = hex and false = binary
        twos_mode = false;      // true = hex and false = binary
        int_total = 0;
        int_input = 0;
        int_undo_op = 0;
        int_memory = 0;
        float_total = 0;
        float_input = 0;
        float_undo_op = 0;
        float_memory = 0;
        input_base = 1;     // starting default is 1 = decimal
        data_size = 3;      // starting default is 3 = quad_word
        binary_display = 0; // starting default is 0 = binary

        // get UI elements by ID
        mTextViewInput = (TextView) findViewById(R.id.tv_input);
        mTextViewHexSelect = (TextView) findViewById(R.id.tv_hex_select);
        mTextViewDecimalSelect = (TextView) findViewById(R.id.tv_decimal_select);
        mTextViewOctalSelect = (TextView) findViewById(R.id.tv_octal_select);
        mTextViewBinarySelect = (TextView) findViewById(R.id.tv_binary_select);
        mTextView1sModeSelect = (TextView) findViewById(R.id.tv_1s_mode_select);
        mTextView2sModeSelect = (TextView) findViewById(R.id.tv_2s_mode_select);
        mTextViewHexOut = (TextView) findViewById(R.id.tv_hex_out);
        mTextViewDecimalOut = (TextView) findViewById(R.id.tv_decimal_out);
        mTextViewOctalOut = (TextView) findViewById(R.id.tv_octal_out);
        mTextViewBinaryOutUpper = (TextView) findViewById(R.id.tv_binary_upper_out);
        mTextViewBinaryOutLower = (TextView) findViewById(R.id.tv_binary_lower_out);
        mButton1sComplement = (Button) findViewById(R.id.button_1s_complement);
        mButton2sComplement = (Button) findViewById(R.id.button_2s_complement);
        mButtonFloatSingle = (Button) findViewById(R.id.button_float_single);
        mButtonFloatDouble = (Button) findViewById(R.id.button_float_double);
        mButtonShiftLeft = (Button) findViewById(R.id.button_shift_left);
        mButtonShiftRight = (Button) findViewById(R.id.button_shift_right);
        mButtonRotateLeft = (Button) findViewById(R.id.button_rotate_left);
        mButtonRotateRight = (Button) findViewById(R.id.button_rotate_right);
        mButtonWordSize = (Button) findViewById(R.id.button_word_size);
        mButtonBits = (Button) findViewById(R.id.button_bits);
        mButtonAnd = (Button) findViewById(R.id.button_and);
        mButtonOr = (Button) findViewById(R.id.button_or);
        mButtonXor = (Button) findViewById(R.id.button_xor);
        mButtonMemStore = (Button) findViewById(R.id.button_mem_store);
        mButtonMemAdd = (Button) findViewById(R.id.button_mem_add);
        mButtonMemRecall = (Button) findViewById(R.id.button_mem_recall);
        mButtonComplement = (Button) findViewById(R.id.button_complement);
        mButtonModulus = (Button) findViewById(R.id.button_modulus);
        mButtonClearEntry = (Button) findViewById(R.id.button_clear_entry);
        mButtonClearAll = (Button) findViewById(R.id.button_clear_all);
        mButtonA = (Button) findViewById(R.id.button_A);
        mButtonB = (Button) findViewById(R.id.button_B);
        mButtonC = (Button) findViewById(R.id.button_C);
        mButtonD = (Button) findViewById(R.id.button_D);
        mButtonE = (Button) findViewById(R.id.button_E);
        mButtonF = (Button) findViewById(R.id.button_F);
        mButton0 = (Button) findViewById(R.id.button_0);
        mButton1 = (Button) findViewById(R.id.button_1);
        mButton2 = (Button) findViewById(R.id.button_2);
        mButton3 = (Button) findViewById(R.id.button_3);
        mButton4 = (Button) findViewById(R.id.button_4);
        mButton5 = (Button) findViewById(R.id.button_5);
        mButton6 = (Button) findViewById(R.id.button_6);
        mButton7 = (Button) findViewById(R.id.button_7);
        mButton8 = (Button) findViewById(R.id.button_8);
        mButton9 = (Button) findViewById(R.id.button_9);
        mButtonDivide = (Button) findViewById(R.id.button_divide);
        mButtonMultiply = (Button) findViewById(R.id.button_multiply);
        mButtonMinus = (Button) findViewById(R.id.button_minus);
        mButtonPlus = (Button) findViewById(R.id.button_plus);
        mButtonSign = (Button) findViewById(R.id.button_sign);
        mButtonRadix = (Button) findViewById(R.id.button_radix);
        mButtonEquals = (Button) findViewById(R.id.button_equals);
        mButtonOpenParen = (Button) findViewById(R.id.button_open_paren);
        mButtonCloseParen = (Button) findViewById(R.id.button_close_paren);
        mImageButtonBackspace = (ImageButton) findViewById(R.id.imageButton_backspace);

        // initialize
        mTextViewInput.setText("");
        mTextViewHexOut.setText("");
        mTextViewDecimalOut.setText("");
        mTextViewOctalOut.setText("");
        NumToBinary(0L);
        mButtonBits.setEnabled(false);
        mButtonMemRecall.setEnabled(false);
        mButtonA.setEnabled(false);
        mButtonB.setEnabled(false);
        mButtonC.setEnabled(false);
        mButtonD.setEnabled(false);
        mButtonE.setEnabled(false);
        mButtonF.setEnabled(false);
        mButtonOpenParen.setEnabled(false);
        mButtonCloseParen.setEnabled(false);

        // Button onClick Listeners
        mButton0.setOnClickListener(new View.OnClickListener()
        {
            /*
                When 0 is pressed, add to right of input and check for validity before
                changing the input value.
             */
            public void onClick(View v)
            {
                if (inputStr.equals("")) return;  // can't pad leading 0s
                inputStr = inputStr + "0";
                inputHandler();
            }
        });

        mButton1.setOnClickListener(new View.OnClickListener()
        {
            /*
                When 1 is pressed, add to right of input and check for validity before
                changing the input value.
             */
            public void onClick(View v)
            {
                inputStr = inputStr + "1";
                inputHandler();
            }
        });

        mButton2.setOnClickListener(new View.OnClickListener()
        {
            /*
                When 2 is pressed, add to right of input and check for validity before
                changing the input value.
             */
            public void onClick(View v)
            {
                inputStr = inputStr + "2";
                inputHandler();
            }
        });

        mButton3.setOnClickListener(new View.OnClickListener()
        {
            /*
                When 3 is pressed, add to right of input and check for validity before
                changing the input value.
             */
            public void onClick(View v)
            {
                inputStr = inputStr + "3";
                inputHandler();
            }
        });

        mButton4.setOnClickListener(new View.OnClickListener()
        {
            /*
                When 4 is pressed, add to right of input and check for validity before
                changing the input value.
             */
            public void onClick(View v)
            {
                inputStr = inputStr + "4";
                inputHandler();
            }
        });

        mButton5.setOnClickListener(new View.OnClickListener()
        {
            /*
                When 5 is pressed, add to right of input and check for validity before
                changing the input value.
             */
            public void onClick(View v)
            {
                inputStr = inputStr + "5";
                inputHandler();
            }
        });

        mButton6.setOnClickListener(new View.OnClickListener()
        {
            /*
                When 6 is pressed, add to right of input and check for validity before
                changing the input value.
             */
            public void onClick(View v)
            {
                inputStr = inputStr + "6";
                inputHandler();
            }
        });

        mButton7.setOnClickListener(new View.OnClickListener()
        {
            /*
                When 7 is pressed, add to right of input and check for validity before
                changing the input value.
             */
            public void onClick(View v)
            {
                inputStr = inputStr + "7";
                inputHandler();
            }
        });

        mButton8.setOnClickListener(new View.OnClickListener()
        {
            /*
                When 8 is pressed, add to right of input and check for validity before
                changing the input value.
             */
            public void onClick(View v)
            {
                inputStr = inputStr + "8";
                inputHandler();
            }
        });

        mButton9.setOnClickListener(new View.OnClickListener()
        {
            /*
                When 9 is pressed, add to right of input and check for validity before
                changing the input value.
             */
            public void onClick(View v)
            {
                inputStr = inputStr + "9";
                inputHandler();
            }
        });

        mButtonA.setOnClickListener(new View.OnClickListener()
        {
            /*
                When A is pressed, add to right of input and check for validity before
                changing the input value.
             */
            public void onClick(View v)
            {
                inputStr = inputStr + "A";
                inputHandler();
            }
        });

        mButtonB.setOnClickListener(new View.OnClickListener()
        {
            /*
                When B is pressed, add to right of input and check for validity before
                changing the input value.
             */
            public void onClick(View v)
            {
                inputStr = inputStr + "B";
                inputHandler();
            }
        });

        mButtonC.setOnClickListener(new View.OnClickListener()
        {
            /*
                When C is pressed, add to right of input and check for validity before
                changing the input value.
             */
            public void onClick(View v)
            {
                inputStr = inputStr + "C";
                inputHandler();
            }
        });

        mButtonD.setOnClickListener(new View.OnClickListener()
        {
            /*
                When D is pressed, add to right of input and check for validity before
                changing the input value.
             */
            public void onClick(View v)
            {
                inputStr = inputStr + "D";
                inputHandler();
            }
        });

        mButtonE.setOnClickListener(new View.OnClickListener()
        {
            /*
                When E is pressed, add to right of input and check for validity before
                changing the input value.
             */
            public void onClick(View v)
            {
                inputStr = inputStr + "E";
                inputHandler();
            }
        });

        mButtonF.setOnClickListener(new View.OnClickListener()
        {
            /*
                When F is pressed, add to right of input and check for validity before
                changing the input value.
             */
            public void onClick(View v)
            {
                inputStr = inputStr + "F";
                inputHandler();
            }
        });

        mButtonRadix.setOnClickListener(new View.OnClickListener()
        {
            /*
                When . is pressed, add to right of input and check for validity before
                changing the input value.
             */
            public void onClick(View v)
            {
                if (inputStr.equals("")) return;
                inputStr = inputStr + ".";
                mButtonRadix.setEnabled(false);
                integer_flag = false;
                inputHandler();
            }
        });
    }

    // START OF HELPER FUNCTIONS
    /*
        This function converts an integer to a hex string
     */
    private String NumToHex(long numToConvert)
    {
        String retStr;

        if (negative_flag)
        {
            retStr = "-" + Long.toHexString(Math.abs(numToConvert));
        }
        else
        {
            retStr = Long.toUnsignedString(numToConvert, 16);
        }

        return retStr.toUpperCase();
    }

    /*
    This function converts an integer to a octal string
     */
    private String NumToOctal(long numToConvert)
    {
        String retStr;

        if (negative_flag)
        {
            retStr = "-" + Long.toOctalString(Math.abs(numToConvert));
        }
        else
        {
            retStr = Long.toUnsignedString(numToConvert, 8);
        }

        return retStr;    }

    /*
        This function converts an integer to a binary string
     */
    private String NumToBinary(long numToConvert)
    {
        String retStr;
        String lowerStr;
        String upperStr;

        if (negative_flag)
        {
            retStr = "-" + Long.toBinaryString(Math.abs(numToConvert));
        }
        else
        {
            retStr = Long.toUnsignedString(numToConvert, 2);
        }

        if (retStr.length() > 32)
        {
            lowerStr = retStr.substring(retStr.length() - 32);
            upperStr = retStr.substring(0, retStr.length() - 32);
            mTextViewBinaryOutLower.setText(lowerStr);
            mTextViewBinaryOutUpper.setText(upperStr);
        }
        else
        {
            mTextViewBinaryOutLower.setText(retStr);
            mTextViewBinaryOutUpper.setText("");
        }
        return retStr;
    }

    /*
        This function converts an integer to a decimal string
     */
    private String NumToDecimal(long numToConvert)
    {
        String retStr;

        if (negative_flag)
        {
            retStr = "-" + Long.toString(Math.abs(numToConvert));
        }
        else
        {
            retStr = Long.toUnsignedString(numToConvert, 10);
        }

        return retStr;
    }

    /*
        This function converts a string to a long integer
     */
    private long StringToInt(String strToConvert)
    {
        switch (input_base)
        {
            case 0:
                return HexStringToInt(strToConvert);
            case 1:
                return DecimalStringToInt(strToConvert);
            case 2:
                return OctalStringToInt(strToConvert);
            case 3:
                return BinaryStringToInt(strToConvert);
            default:
                Context context = getApplicationContext();
                CharSequence text = "StringToInt - Invalid Base";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                conversion_error = true;
                return 0;
        }
    }

    /*
        This function converts a hexadecimal string to a long integer
     */
    private long HexStringToInt(String strToConvert)
    {
        long numOut = 0;
        char charIn;
        long digit;

        for (int index = 0; index < strToConvert.length(); index++)
        {
            charIn = strToConvert.charAt(index);
            switch (charIn)
            {
                case '-':   // handle leading negative sign
                case '0':
                    digit = 0;
                    break;
                case '1':
                    digit = 1;
                    break;
                case '2':
                    digit = 2;
                    break;
                case '3':
                    digit = 3;
                    break;
                case '4':
                    digit = 4;
                    break;
                case '5':
                    digit = 5;
                    break;
                case '6':
                    digit = 6;
                    break;
                case '7':
                    digit = 7;
                    break;
                case '8':
                    digit = 8;
                    break;
                case '9':
                    digit = 9;
                    break;
                case 'A':
                    digit = 10;
                    break;
                case 'B':
                    digit = 11;
                    break;
                case 'C':
                    digit = 12;
                    break;
                case 'D':
                    digit = 13;
                    break;
                case 'E':
                    digit = 14;
                    break;
                case 'F':
                    digit = 15;
                    break;
                default:
                    Context context = getApplicationContext();
                    CharSequence text = "HexStringToInt - Invalid Character";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    conversion_error = true;
                    return 0;
            }

            try // to add the digit
            {
                numOut = (numOut * 16) + digit;
            }
            catch (Exception e)
            {
                Context context = getApplicationContext();
                CharSequence text = "HexStringToInt - Math Error";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                conversion_error = true;
                return 0;
            }
        }

        if (strToConvert.charAt(0) == '-')
        {
            numOut = 0 - numOut;
        }

        return numOut;
    }

    /*
        This function converts a decimal string to a long integer
     */
    private long DecimalStringToInt(String strToConvert)
    {
        long numOut = 0;
        char charIn;
        long digit;

        for (int index = 0; index < strToConvert.length(); index++)
        {
            charIn = strToConvert.charAt(index);
            switch (charIn)
            {
                case '-':   // handle leading negative sign
                case '0':
                    digit = 0;
                    break;
                case '1':
                    digit = 1;
                    break;
                case '2':
                    digit = 2;
                    break;
                case '3':
                    digit = 3;
                    break;
                case '4':
                    digit = 4;
                    break;
                case '5':
                    digit = 5;
                    break;
                case '6':
                    digit = 6;
                    break;
                case '7':
                    digit = 7;
                    break;
                case '8':
                    digit = 8;
                    break;
                case '9':
                    digit = 9;
                    break;
                default:
                    Context context = getApplicationContext();
                    CharSequence text = "DecimalStringToInt - Invalid Character";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    conversion_error = true;
                    return 0;
            }

            try // to add the digit
            {
                numOut = (numOut * 10) + digit;
            }
            catch (Exception e)
            {
                Context context = getApplicationContext();
                CharSequence text = "DecimalStringToInt - Math Error";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                conversion_error = true;
                return 0;
            }
        }

        if (strToConvert.charAt(0) == '-')
        {
            numOut = 0 - numOut;
        }

        return numOut;
    }

    /*
        This function converts an octal string to a long integer
     */
    private long OctalStringToInt(String strToConvert)
    {
        long numOut = 0;
        char charIn;
        long digit;

        for (int index = 0; index < strToConvert.length(); index++)
        {
            charIn = strToConvert.charAt(index);
            switch (charIn)
            {
                case '-':   // handle leading negative sign
                case '0':
                    digit = 0;
                    break;
                case '1':
                    digit = 1;
                    break;
                case '2':
                    digit = 2;
                    break;
                case '3':
                    digit = 3;
                    break;
                case '4':
                    digit = 4;
                    break;
                case '5':
                    digit = 5;
                    break;
                case '6':
                    digit = 6;
                    break;
                case '7':
                    digit = 7;
                    break;
                default:
                    Context context = getApplicationContext();
                    CharSequence text = "OctalStringToInt - Invalid Character";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    conversion_error = true;
                    return 0;
            }

            try // to add the digit
            {
                numOut = (numOut * 8) + digit;
            }
            catch (Exception e)
            {
                Context context = getApplicationContext();
                CharSequence text = "OctalStringToInt - Math Error";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                conversion_error = true;
                return 0;
            }
        }

        if (strToConvert.charAt(0) == '-')
        {
            numOut = 0 - numOut;
        }

        return numOut;
    }

    /*
        This function converts a string to a long integer
     */
    private long BinaryStringToInt(String strToConvert)
    {
        long numOut = 0;
        char charIn;
        long digit;

        for (int index = 0; index < strToConvert.length(); index++)
        {
            charIn = strToConvert.charAt(index);
            switch (charIn)
            {
                case '-':   // handle leading negative sign
                case '0':
                    digit = 0;
                    break;
                case '1':
                    digit = 1;
                    break;
                default:
                    Context context = getApplicationContext();
                    CharSequence text = "BinaryStringToInt - Invalid Character";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    conversion_error = true;
                    return 0;
            }

            try // to add the digit
            {
                numOut = (numOut * 2) + digit;
            }
            catch (Exception e)
            {
                Context context = getApplicationContext();
                CharSequence text = "BinaryStringToInt - Math Error";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                conversion_error = true;
                return 0;
            }
        }

        if (strToConvert.charAt(0) == '-')
        {
            numOut = 0 - numOut;
        }

        return numOut;
    }

    /*
        This function converts a string to a float (double)
     */
    private double StringToDouble(String strToConvert) throws NumberFormatException, NullPointerException
    {
        return Double.parseDouble(strToConvert);
    }

    /*
        This function handles the input conversions from the input string
        to either long or double as appropriate
     */
    public void inputHandler()
    {
        long input;

        if (!integer_flag)  // if a real number, different conversion
        {
            float_input = StringToDouble(inputStr);
            int_input = 0;
            mTextViewInput.setText(inputStr);
            return;
        }

        // otherwise we have to do base and data size for integer
        switch (input_base)
        {
            case 0:
                input = HexStringToInt(inputStr);
                break;
            case 1:
                input = DecimalStringToInt(inputStr);
                break;
            case 2:
                input = OctalStringToInt(inputStr);
                break;
            case 3:
                input = BinaryStringToInt(inputStr);
                break;
            default:
                Context context = getApplicationContext();
                CharSequence text = "0 Button - Invalid Base";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                return;
        }

        if (conversion_error)
        {
            conversion_error = true;  // reset flag and do nothing
            return;
        }

        switch (data_size)
        {
            case 3:
                break;      // long, so nothing to check
            case 2:
                if ((input < -2147483648) || (input > 2147483647))
                {
                    Context context = getApplicationContext();
                    CharSequence text = "0 Button - Out of Range";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    return;
                }
            case 1:
                if ((input < -32768) || (input > 32767))
                {
                    Context context = getApplicationContext();
                    CharSequence text = "0 Button - Out of Range";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    return;
                }
            case 0:
                if ((input < -128) || (input > 127))
                {
                    Context context = getApplicationContext();
                    CharSequence text = "0 Button - Out of Range";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    return;
                }
            default:
                Context context = getApplicationContext();
                CharSequence text = "0 Button - Invalid Data Size";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                return;
        }

        // if we got here, the input was Ok, so display the string and
        // change the input value
        int_input = input;
        float_input = 0;
        mTextViewInput.setText(inputStr);
    }
}
