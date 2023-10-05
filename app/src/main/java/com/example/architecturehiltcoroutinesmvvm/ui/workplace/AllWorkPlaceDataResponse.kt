package com.example.architecturehiltcoroutinesmvvm.ui.workplace

data class AllWorkPlaceDataResponse(
    val code: String = "",
    val `data`: List<Data> = listOf(),
    val message: String = "",
    val status: String = ""
) {
    data class Data(
        val dtAddedDate: String = "",
        val dtUpdatedDate: Any = Any(),
        val eCurrentlyWorking: String = "",
        val eStatus: String = "",
        val iCityId: Any = Any(),
        val iCountryId: Any = Any(),
        val iStateId: Any = Any(),
        val iUserId: Int = 0,
        val iWorkplaceId: Int = 0,
        val tDescription: String = "",
        val vCity: String = "",
        val vCompany: String = "",
        val vCountry: Any = Any(),
        val vEndDay: String = "",
        val vEndMonth: String = "",
        val vEndYear: String = "",
        val vPosition: String = "",
        val vStartDay: String = "",
        val vStartMonth: String = "",
        val vStartYear: String = "",
        val vState: Any = Any()
    )
}
