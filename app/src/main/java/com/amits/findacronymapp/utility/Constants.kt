package com.amits.findacronymapp.utility

import android.content.Context
import android.widget.Toast

object ApiConstant{
    const val BASE_URL= "http://www.nactem.ac.uk/software/acromine/"
}

fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun isValidInput(inputString:String?):Boolean{
    if(inputString?.isNullOrEmpty() == true){
        return false
    }else if(inputString?.length==0){
        return false
    }else if(inputString?.length<=2){
        return false
    }
    return true
}
