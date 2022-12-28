package currencyConvertor.service

import currencyConvertor.dto.CodeNamePair
import currencyConvertor.service.annotations.ApiAuth
import java.lang.Exception
import javax.annotation.Resource
import javax.jws.WebMethod
import javax.jws.WebParam
import javax.jws.WebService
import javax.jws.soap.SOAPBinding
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlSeeAlso
import javax.xml.ws.WebServiceContext
import kotlin.jvm.Throws


@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
@XmlSeeAlso(CodeNamePair::class)
@ApiAuth()
interface IService  {

    @Throws(Exception::class)
    @WebMethod
    fun convertCurrency( @WebParam(name = "sourceCurrency") @XmlElement(required=true, nillable=false) sourceCurrency : String ,
                         @WebParam(name = "targetCurrency") @XmlElement(required=true, nillable=false) targetCurrency : String ,
                         @WebParam(name = "amount") @XmlElement(required=true, nillable=false) amount : Double ) : Double
    @WebMethod
    fun getAllCodeAndNames() : ArrayList<CodeNamePair>

}