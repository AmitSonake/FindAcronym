package com.amits.findacronymapp.models

data class Acronym(
    val lfs: List<Lf?>?,
    val sf: String?
){
    data class Lf(
        val freq: Int?,
        val lf: String?,
        val since: Int?,
        val vars: List<Var?>
    ){
        data class Var(
            val freq: Int?,
            val lf: String?,
            val since: Int?
        )
    }



}











