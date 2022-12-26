package currencyConvertor.service

interface IExchangeRateRepo {

    fun getRateByCode(code : String) : Double?
    fun getCodesAndNames() : HashMap<String,String>

}
