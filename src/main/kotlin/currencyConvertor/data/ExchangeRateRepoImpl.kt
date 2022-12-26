package currencyConvertor.data

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import currencyConvertor.service.IExchangeRateRepo
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.RuntimeException
import kotlin.Exception

class ExchangeRateRepoImpl : IExchangeRateRepo {

    private lateinit var data : List<ExchangeRateData>
    private val codeRateMap = HashMap<String , Double>()
    private val codeNameMap = HashMap<String , String>()

    init {
        try {
            val inStream = this::class.java.classLoader.getResourceAsStream("ExchangeRates.json")
            inStream?.also {
                val br = BufferedReader(InputStreamReader(inStream))
                val tt = object : TypeToken<List<ExchangeRateData>>() {}.type
                data = Gson().fromJson(br ,tt)

            }

            if(data.isEmpty()) throw Exception("DataSource is empty")

            data.forEach {
                codeRateMap[it.code] = it.rate
                codeNameMap[it.code] = it.name
            }



        }catch (e :  Exception){
            println("data file : ${e.message}")
            throw RuntimeException(e)
        }

    }


    override fun getRateByCode(code: String): Double? {

        return codeRateMap[code]

    }

    override fun getCodesAndNames(): HashMap<String, String> {
        return codeNameMap
    }
}