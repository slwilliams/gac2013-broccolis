package com.google.code.broccolis.xydroid.ui.component;


import android.app.Activity;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.text.Editable;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class CustomKeyboard
{
    private Activity hostActivity;
    private Keyboard keyboard;
    private KeyboardView keyboardView;

    private KeyboardView.OnKeyboardActionListener onKeyboardActionListener;

    public CustomKeyboard(Activity host, int viewid, int layoutid)
    {
        hostActivity = host;
        keyboard = new Keyboard(host, layoutid);
        keyboardView = (KeyboardView) hostActivity.findViewById(viewid);
        keyboardView.setKeyboard(keyboard);
        keyboardView.setPreviewEnabled(false);
        onKeyboardActionListener = new KeyboardView.OnKeyboardActionListener()
        {
            @Override
            public void onPress(int primaryCode)
            {

            }

            @Override
            public void onRelease(int primaryCode)
            {

            }

            @Override
            public void onKey(int primaryCode, int[] keyCodes)
            {
                // Get the EditText and its Editable
                View focusCurrent = hostActivity.getWindow().getCurrentFocus();
                if (focusCurrent == null || focusCurrent.getClass() != EditText.class)
                {
                    return;
                }
                EditText edittext = (EditText) focusCurrent;
                Editable editable = edittext.getText();
                int start = edittext.getSelectionStart();
                // Handle key
                switch (primaryCode)
                {
                    case Keyboard.KEYCODE_DELETE:
                        if (editable != null && start > 0)
                        {
                            editable.delete(start - 1, start);
                        }
                        break;
                    case 55000:
                        editable.insert(start, "^2");
                        break;
                    case 55002:
                        editable.insert(start, "sin()");
                        edittext.setSelection(start + 4);
                        break;
                    case 55003:
                        editable.insert(start, "log()");
                        edittext.setSelection(start + 4);
                        break;
                    case 55004:
                        editable.insert(start, "sqrt()");
                        edittext.setSelection(start + 5);
                        break;
                    case 55005:
                        editable.insert(start, "()");
                        edittext.setSelection(start + 1);
                        break;
                    case 55007: // clear all
                        if (editable != null)
                        {
                            editable.clear();
                        }
                        break;
                    case 55008: // move left
                        if (start > 0)
                        {
                            edittext.setSelection(start - 1);
                        }
                        break;
                    case 55009: // move right
                        if (start < edittext.length())
                        {
                            edittext.setSelection(start + 1);
                        }
                        break;
                    case 55010: // move to start
                        edittext.setSelection(0);
                        break;
                    case 55011: // move to end
                        edittext.setSelection(edittext.length());
                        break;
                    default: // insert character
                        editable.insert(start, Character.toString((char) primaryCode));
                }
            }

            @Override
            public void onText(CharSequence text)
            {

            }

            @Override
            public void swipeLeft()
            {

            }

            @Override
            public void swipeRight()
            {

            }

            @Override
            public void swipeDown()
            {

            }

            @Override
            public void swipeUp()
            {

            }
        };

        keyboardView.setOnKeyboardActionListener(onKeyboardActionListener);
        hostActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    public void hideCustomKeyboard()
    {
        keyboardView.setVisibility(View.GONE);
        keyboardView.setEnabled(false);
    }

    public void showCustomKeyboard(View v)
    {
        keyboardView.setVisibility(View.VISIBLE);
        keyboardView.setEnabled(true);
        if (v != null)
        {
            ((InputMethodManager) hostActivity.getSystemService(Activity.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    public boolean isCustomKeyboardVisible()
    {
        return keyboardView.getVisibility() == View.VISIBLE;
    }

    public void registerEditText(EditText editText)
    {
        // Make the custom keyboard appear
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                if (hasFocus)
                {
                    showCustomKeyboard(v);
                }
                else
                {
                    hideCustomKeyboard();
                }
            }
        });
        editText.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                showCustomKeyboard(v);
            }
        });
        // Disable standard keyboard hard way
        editText.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                EditText edittext = (EditText) v;
                int inType = edittext.getInputType();       // Backup the input type
                edittext.setInputType(InputType.TYPE_NULL); // Disable standard keyboard
                edittext.onTouchEvent(event);               // Call native handler
                edittext.setInputType(inType);              // Restore input type
                return true; // Consume touch event
            }
        });
        // Disable spell check (hex strings look like words to Android)
        editText.setInputType(editText.getInputType() | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
    }
}
