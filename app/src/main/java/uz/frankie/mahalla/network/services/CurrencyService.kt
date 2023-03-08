package uz.frankie.mahalla.network.services

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import uz.frankie.mahalla.network.models.exchangerate.response.ExchangeRate

interface CurrencyService {

    @GET(" ")
    suspend fun getAllCurrency() : Response<List<ExchangeRate>>

    @GET("all/{date}/")
    suspend fun getDateAllCurrency(
        @Path("date") date: String
    ) : Response<List<ExchangeRate>>

    @GET("{ccy}/{date}/")
    suspend fun getSelectDateCurrency(
        @Path("ccy") ccy: String,
        @Path("date") date: String
    ) : Response<List<ExchangeRate>>

}