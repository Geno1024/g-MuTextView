package com.geno.mutextview

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.LinearLayout

/**
 * Created by geno1024 on 2018-01-27 10:25.
 *
 * A layout combined with two LinearLayout which are influenced with each other, avoided a dead
 * loop of calling each other's TextWatcher.
 *
 * "Eth" comes from the chemical prefix of organics, are used to mean there are two carbons.
 *
 * @param context
 * @param editText0 The first EditText.
 * @param editText1 The second EditText.
 */
class EthTextView(context: Context, val editText0: EditText, val editText1: EditText): LinearLayout(context)
{
    /**
     * Boolean array for status to build "mutex lock".
     */
    private val status = arrayOf(false, false)

    /**
     * Relation definition of two EditText.
     */
    var relation: EthTextView.OnEditTextChangedListener = object : OnEditTextChangedListener
    {
        override fun onEditText0Changed(editText0: EditText, editText1: EditText) {}
        override fun onEditText1Changed(editText0: EditText, editText1: EditText) {}
    }

    /**
     * Define the two EditText with some default.
     * @param context
     */
    constructor(context: Context): this(context, EditText(context), EditText(context))
    {
        orientation = LinearLayout.VERTICAL
        addView(editText0, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        addView(editText1, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
    }

    init
    {
        editText0.addTextChangedListener(object : TextWatcher
        {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int)
            {
                status[0] = true
            }

            override fun afterTextChanged(s: Editable?)
            {
                if (status[0] && !status[1])
                    relation.onEditText0Changed(editText0, editText1)
                status[0] = false
            }
        })

        editText1.addTextChangedListener(object : TextWatcher
        {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int)
            {
                status[1] = true
            }

            override fun afterTextChanged(s: Editable?)
            {
                if (!status[0] && status[1])
                    relation.onEditText1Changed(editText0, editText1)
                status[1] = false
            }
        })
    }

    /**
     * Activated when the EditText has changed.
     */
    interface OnEditTextChangedListener
    {
        fun onEditText0Changed(editText0: EditText, editText1: EditText)
        fun onEditText1Changed(editText0: EditText, editText1: EditText)
    }
}