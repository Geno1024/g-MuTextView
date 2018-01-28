package com.geno1024.mutextview.sample

import android.app.Activity
import android.os.Bundle
import android.widget.EditText
import com.geno1024.mutextview.EthTextView

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
