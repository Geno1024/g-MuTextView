package com.geno.mutextview

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Base64
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView

class MainActivity : Activity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(EthTextView(this).apply {
            relation = object : EthTextView.OnEditTextChangedListener
            {
                override fun onEditText0Changed(editText0: EditText, editText1: EditText)
                {
                    editText1.setText("${editText0.text}1")
                }

                override fun onEditText1Changed(editText0: EditText, editText1: EditText)
                {
                    editText0.setText("${editText1.text}2")
                }
            }
        })
    }
}
