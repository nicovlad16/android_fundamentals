package com.example.kotlin_server1.data

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

class NetworkApiAdapter private constructor() {
    private object Holder {
        val INSTANCE = NetworkApiAdapter()
    }

    companion object {
        val INSTANCE: NetworkApiAdapter by lazy {
            Holder.INSTANCE
        }
        const val BASE_URL: String = "http://127.0.0.1:5000/"
        private const val URL_MONEY_ITEMS_ALL: String = "/money_items"
        private const val URL_MONEY_ITEM_INDIVIDUAL: String = "/money_items/{id}"
    }

    private val moneyItemService: MoneyItemService

    init {
        val gson: Gson =
            GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        moneyItemService = retrofit.create(MoneyItemService::class.java)
    }

    fun fetchAll():List<MoneyItem>
    {
        return moneyItemService.fetchAll().execute().body()!!
    }


    fun insert(moneyItem: MoneyItem):Observable<MoneyItem>
    {
        Log.d("INSERT", moneyItem.toString())
        return moneyItemService.insert(
            moneyItem.name!!, moneyItem.category!!, moneyItem.type!!, moneyItem.amount!!
        )
    }

//    fun update(id:String, moneyItem:MoneyItem):Observable<ResponseBody>
//    {
//        return moneyItemService.update(id, moneyItem.name!!, moneyItem.category!!, moneyItem.type!!, moneyItem.amount!!)
//    }

//    fun delete(id:String):Observable<ResponseBody>
//    {
//        return moneyItemService.delete(id)
//    }

    interface MoneyItemService {
        @GET(URL_MONEY_ITEMS_ALL)
        fun fetchAll(): Call<List<MoneyItem>>

        @POST(URL_MONEY_ITEMS_ALL)
        @FormUrlEncoded
        fun insert(
            @Field("name") name: String,
            @Field("category") category: String,
            @Field("type") type: String,
            @Field("amount") amount: Int
        ): Observable<MoneyItem>

        @PUT(URL_MONEY_ITEM_INDIVIDUAL)
        @FormUrlEncoded
        fun update(
            @Path("id") id: String,
            @Path("name") name: String,
            @Path("category") category: String,
            @Path("type") type: String,
            @Path("amount") amount: Int
        ): Observable<ResponseBody>

        @DELETE(URL_MONEY_ITEM_INDIVIDUAL)
        fun delete(@Path("id") id: String): Observable<ResponseBody>
    }

}