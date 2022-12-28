package currencyConvertor.service.common

import currencyConvertor.service.annotations.ApiAuth
import currencyConvertor.service.common.util.PackageUtility.getClassesInPackage
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy
import javax.xml.ws.handler.MessageContext

data class ServiceFactory(val targetService : BaseService){

    private var services : MutableList<BaseService> = mutableListOf()
    private lateinit var itr : Class<*>



    class WebMethodInterceptor : InvocationHandler {
        private lateinit var targetService : BaseService

        constructor(target : BaseService){
            targetService = target
        }

        override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any? {
            if(proxy is BaseService){
                (proxy.context.messageContext[MessageContext.HTTP_REQUEST_HEADERS] as Map<String, List<String>>).forEach{
                        entry -> println("${entry.key} :${entry.value}" )
                }

                return method?.invoke(targetService,args)
            }

            return null
        }

    }



    private fun <T : BaseService> createMethodProxy(target : T, itr : Class<*> ): T {
        // Create a proxy for the method
        val proxy = Proxy.newProxyInstance(
            itr.classLoader,
            arrayOf(itr,),
            WebMethodInterceptor(target)
        )

        // Return the proxy
        return proxy as T
    }

    private fun processAnnotations() : Unit {

        val list = getClassesInPackage()
        list.forEach {
            if(it.isAnnotationPresent(ApiAuth::class.java)){
                if(it.isInterface){
                    list.forEach {clz ->
                        if((clz != it).and(it.isAssignableFrom(clz)).and(clz.isAssignableFrom(BaseService::class.java))){
                            services.add(createMethodProxy(targetService,itr))
                        }
                    }
                }
            }
        }
    }

    fun setInterface( _interface : Class<*>): ServiceFactory {
        itr = _interface
        return this
    }

    fun build(): List<BaseService> {
        processAnnotations()
        return services
    }


}