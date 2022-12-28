package currencyConvertor.service.common

import java.lang.Exception
import javax.annotation.Resource
import javax.xml.ws.WebServiceContext
import javax.xml.ws.handler.MessageContext
import kotlin.jvm.Throws

abstract class BaseService {

    private var apiKey : String = ""

    @Resource
    lateinit var context : WebServiceContext

    fun setApiKey( key : String){
        apiKey = key
    }


    @Throws(Exception::class)
    protected fun authenticate() : Unit {
        val authHeaders = (context.messageContext[MessageContext.HTTP_REQUEST_HEADERS] as Map<String, List<String>>)["Authorization"]

        authHeaders?:throw Exception("Unauthorized")

        authHeaders.also {
            try {
                val headerValue = it[0].split(" ")
                val ( tokenType , token)  = headerValue
                if(tokenType != "Bearer") throw Exception("Unauthorized")
                if(token != apiKey) throw Exception("Unauthorized")
                return@authenticate
            }catch (e : Exception){
                throw Exception("Unauthorized")
            }

        }



    }


}