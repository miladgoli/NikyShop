package com.example.nikyshop.model.utils

import android.R.attr.*
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.text.InputType
import android.view.Window
import android.widget.*
import com.example.nikyshop.R
import com.google.android.material.button.MaterialButton


object Methods {

    val INPUT_TYPE_VISIBLE_PASSWORD =
        InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD

    val INPUT_TYPE_HIDDEN_PASSWORD =
        InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD


    fun isPasswordVisible(editText: EditText): Boolean {
        return editText.inputType == INPUT_TYPE_VISIBLE_PASSWORD
    }

    fun enableInputVisiblePassword(editText: EditText) {
        val cache = editText.typeface
        editText.inputType = INPUT_TYPE_VISIBLE_PASSWORD
        editText.typeface = cache
    }

    fun enableInputHiddenPassword(editText: EditText) {
        val cache = editText.typeface
        editText.inputType = INPUT_TYPE_HIDDEN_PASSWORD
        editText.typeface = cache
    }


    fun showDeleteProductDialog(context: Context, yesListener: View.OnClickListener) {

        val dialog = Dialog(context)

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_fragment_delete_product)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val yesBtn = dialog.findViewById(R.id.btnYesDialogDeleteProduct) as MaterialButton
        val noBtn = dialog.findViewById(R.id.btnNoDialogDeleteProduct) as MaterialButton

        yesBtn.setOnClickListener {

            yesListener.onClick(it)
            dialog.dismiss()

        }

        noBtn.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()

    }


    fun showSignoutDialog(context: Context, yesListener: View.OnClickListener) {

        val dialog = Dialog(context)

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_fragment_signout)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val yesBtn = dialog.findViewById(R.id.btnYesDialogSignOut) as MaterialButton
        val noBtn = dialog.findViewById(R.id.btnNoDialogSignOut) as MaterialButton

        yesBtn.setOnClickListener {

            yesListener.onClick(it)
            dialog.dismiss()

        }

        noBtn.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()

    }

}