package ramesh.data

data class ExchangeRateData(val code : String ,
                            val alphaCode: String ,
                            val numericCode: String ,
                            val name: String ,
                            val rate: Double ,
                            val date: String ,
                            val inverseRate: String)
