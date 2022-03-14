package com.example.homework.api

import com.example.homework.constance.ApiConst
import com.example.homework.entity.Business
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RestaurantService {
    @Headers("Authorization:Bearer XPFgzKwZGK1yqRxHi0d5xsARFOLpXIvccQj5jekqTnysweGyoIfVUHcH2tPfGq5Oc9kwKHPkcOjk2d1Xobn7aTjOFeop8x41IUfVvg2Y27KiINjYPADcE7Qza0RkX3Yx")
    @GET(ApiConst.PATH_RESTAURANT)
    suspend fun fetchRestaurant(
        @Query("term") term: String?,
        @Query("location") location: String?,
        @Query("radius") radius: Int?,
        @Query("sort_by") sort: String = "distance",
        @Query("categories") categories: String = "restaurant",
        @Query("limit") limit: Int = 20
    ): Response<Business?>
}