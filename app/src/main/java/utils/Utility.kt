package utils

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager


object Utility {
        fun hideKeyBoard(context:Context){
            val imm:InputMethodManager? = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm?.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
        }

}