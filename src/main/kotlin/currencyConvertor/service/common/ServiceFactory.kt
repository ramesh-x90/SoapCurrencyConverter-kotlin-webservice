package currencyConvertor.service.common

import currencyConvertor.service.CurrencyService
import currencyConvertor.service.annotations.ApiAuth
import currencyConvertor.service.common.util.PackageUtility.getClassesInPackage
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy
import javax.xml.ws.handler.MessageContext

data class ServiceFactory<T>(val targetService : T){

    private var services : MutableList<T> = mutableListOf()




    class WebMethodInterceptor<T> : InvocationHandler {
        private val targetService : T

         constructor(target : T){
            targetService = target
        }

        override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any? {
            if(targetService is BaseService){
                (targetService.context.messageContext[MessageContext.HTTP_REQUEST_HEADERS] as Map<String, List<String>>).forEach{
                        entry -> println("${entry.key} :${entry.value}" )
                }
            }

            return method?.invoke(targetService,args)
        }

    }



    private fun <T2> createMethodProxy(target : T, itr : Class<T2> ): T {
        // Create a proxy for the method
        val proxy = Proxy.newProxyInstance(
            itr.classLoader,
            arrayOf(itr,),
            WebMethodInterceptor(target)
        )

        // Return the proxy
        println(proxy is CurrencyService)
        return proxy as T
    }

    private fun processAnnotations() : Unit {

        val list = getClassesInPackage()
        list.forEach {
            if(it.isAnnotationPresent(ApiAuth::class.java)){
                if(it.isInterface){
                    list.forEach {clz ->
                        if((clz != it).and(it.isAssignableFrom(clz))){
                            services.add(createMethodProxy(targetService,it))
                        }
                    }
                }
            }
        }
    }


    fun build(): List<T> {
        processAnnotations()
        return services
    }


}