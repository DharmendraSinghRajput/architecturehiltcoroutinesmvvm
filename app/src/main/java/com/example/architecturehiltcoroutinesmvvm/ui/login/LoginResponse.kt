package com.example.architecturehiltcoroutinesmvvm.ui.login

data class LoginResponse(
    val code: String = "",
    val `data`: Data = Data(),
    val message: String = "",
    val status: String = ""
) {
    data class Data(
        val dBirthDate: String = "",
        val dtAddedDate: String = "",
        val dtUpdatedDate: String = "",
        val eEmailVerified: String = "",
        val eStatus: String = "",
        val eUnder13: String = "",
        val eUserType: Any = Any(),
        val iRoleId: Any = Any(),
        val iUserId: Int = 0,
        val vAuthCode: String = "",
        val vEmail: String = "",
        val vFirstName: String = "",
        val vIPAddress: String = "",
        val vImage: String = "",
        val vLastName: String = "",
        val vPassword: String = "",
        val vRole: Any = Any(),
        val vUniqueId: String = "",
        val eInterest: String = "",
        val eUserAccountType: String = "",
        val eTwoStepVerification: String = "",
        val eTwoStepVerificationType: String = "",
        val vCountryCode: String = "",
        val vMobileNumber:String="")
}