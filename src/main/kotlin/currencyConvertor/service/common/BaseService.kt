package currencyConvertor.service.common

import javax.annotation.Resource
import javax.xml.ws.WebServiceContext

abstract class BaseService {
    @Resource
    lateinit var context : WebServiceContext
}