package com.example.vsgarments.authentication

data class User(
    val userName: String,
    val email: String,
    val localUrl: String = "" ,
    val remoteUrl: String = "" ,
    val mobileNumber: String = "" ,
    val alterEmail: String = "" ,
    val alterMobileNumber: String = "" ,
    val state: String = "" ,
    val city: String = "" ,
    val pincode: String = "" ,

){
    constructor() : this("" ,"" ,"" , ""  ,"", "" ,"","","","")
}
