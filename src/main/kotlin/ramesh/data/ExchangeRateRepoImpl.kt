package ramesh.data

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ramesh.service.IExchangeRateRepo
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.lang.Exception
import java.lang.RuntimeException

class ExchangeRateRepoImpl : IExchangeRateRepo {

    private lateinit var data : List<ExchangeRateData>
    private val codeRateMap = HashMap<String , Double>()
    private val codeNameMap = HashMap<String , String>()

    init {
        try {
            val file = this::class.java.classLoader.getResource("ExchangeRates.json")
            file?.also {
                val br = BufferedReader(FileReader(File(it.path)))
                val tt = object : TypeToken<List<ExchangeRateData>>() {}.type
                data = Gson().fromJson(br ,tt)

            }

            if(data.isEmpty()) throw Exception("DataSource is empty")

            data.forEach { it ->
                codeRateMap[it.code] = it.rate
                codeNameMap[it.code] = it.name
            }



        }catch (e :  Exception){
            throw RuntimeException(e)
        }

    }


    override fun getRateByCode(code: String): Double? {
        val data = codeRateMap[code];

        return data

    }

    override fun getCodesAndNames(): HashMap<String, String> {
        return codeNameMap
    }
}