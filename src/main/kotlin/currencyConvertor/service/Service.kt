package currencyConvertor.service


import currencyConvertor.dto.CodeNamePair
import javax.jws.WebService
import java.lang.Exception



@WebService(
    endpointInterface = "ramesh.service.IService",
    serviceName = "currencyConverterSoap",
    portName = "currencyConverterPort"
)
open class Service(private val repo : IExchangeRateRepo) : IService {

    init {

    }


    override fun convertCurrency(sourceCurrency: String,
                                 targetCurrency: String,
                                 amount: Double): Double {
        val sourceRate = repo.getRateByCode(sourceCurrency)
        val targetRate = repo.getRateByCode(targetCurrency)


        sourceRate?:throw Exception("Wrong Source Currency Code")
        targetRate?:throw Exception("Wrong Target Currency Code")


        return String.format("%.4f" , amount/sourceRate*targetRate).toDouble()

    }


    override fun getAllCodeAndNames(): ArrayList<CodeNamePair> {
        val list = ArrayList<CodeNamePair>()
        repo.getCodesAndNames().forEach{ (key, value) -> list.add(CodeNamePair(key,value))}
        return list
    }
}

