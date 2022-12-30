package currencyConvertor.service


import currencyConvertor.dto.CodeNamePair
import currencyConvertor.service.common.BaseService
import javax.jws.WebService
import java.lang.Exception


@WebService(
    endpointInterface = "currencyConvertor.service.CurrencyService",
    serviceName = "currencyConverterSoap",
    portName = "currencyConverterPort"
)
open class CurrencyServiceImpl(private val repo : IExchangeRateRepo) : CurrencyService , BaseService() {

    override fun convertCurrency(sourceCurrency: String,
                                 targetCurrency: String,
                                 amount: Double): Double {

        authenticate()

        val sourceRate = repo.getRateByCode(sourceCurrency)
        val targetRate = repo.getRateByCode(targetCurrency)


        sourceRate?:throw Exception("Wrong Source Currency Code")
        targetRate?:throw Exception("Wrong Target Currency Code")


        return String.format("%.4f" , amount*targetRate/sourceRate).toDouble()

    }

    override fun getAllCodeAndNames(): ArrayList<CodeNamePair> {

        authenticate()

        val list = ArrayList<CodeNamePair>()
        repo.getCodesAndNames().forEach{ (key, value) -> list.add(CodeNamePair(key,value))}
        return list
    }
}

