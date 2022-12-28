import currencyConvertor.data.ExchangeRateRepoImpl
import currencyConvertor.service.CurrencyServiceImpl
import java.lang.Exception

import javax.xml.ws.Endpoint

fun main(args: Array<String>) {

    val text = """
  _________                  ___________             .__                                __________         __                 
 /   _____/ _________  ______\_   _____/__  ___ ____ |  |__ _____    ____    ____   ____\______   \_____ _/  |_  ____   ______
 \_____  \ /  _ \__  \ \____ \|    __)_\  \/  // ___\|  |  \\__  \  /    \  / ___\_/ __ \|       _/\__  \\   __\/ __ \ /  ___/
 /        (  <_> ) __ \|  |_> >        \>    <\  \___|   Y  \/ __ \|   |  \/ /_/  >  ___/|    |   \ / __ \|  | \  ___/ \___ \ 
/_______  /\____(____  /   __/_______  /__/\_ \\___  >___|  (____  /___|  /\___  / \___  >____|_  /(____  /__|  \___  >____  >
        \/           \/|__|          \/      \/    \/     \/     \/     \//_____/      \/       \/      \/          \/     \/ 
                                              ___.     _________                  .__                                                                         
                                __  _  __ ____\_ |__  /   _____/ ______________  _|__| ____  ____                                                             
                                \ \/ \/ // __ \| __ \ \_____  \_/ __ \_  __ \  \/ /  |/ ___\/ __ \                                                            
                                 \     /\  ___/| \_\ \/        \  ___/|  | \/\   /|  \  \__\  ___/                                                            
                                  \/\_/  \___  >___  /_______  /\___  >__|    \_/ |__|\___  >___  >                                                           
                                             \/    \/        \/     \/                    \/    \/                                                                                                                     
    """

    text.split("\n").forEach {
        println(it)
        Thread.sleep(100)
    }

    try {
        var port = "8080"
        var apiKey = "UkXp2s5v8x/A?D(G+KbPeShVmYq3t6w9z\$B&E)H@McQfTjWnZr4u7x!A%D*F-JaNdRgUkXp2s5v8y/B?E(H+KbPeShVmYq3t6w9z\$C&F)J@NcRfTjWnZr4u7x!A%D*G-"

        args.indexOf("-p").also {
            if(it >= 0){
                if(args.size > it+1){
                    port = args[it+1]
                }
            }
        }

        args.indexOf("-k").also {
            if(it >= 0){
                if(args.size > it+1){
                    apiKey = args[it+1]
                }
            }
        }


        val serviceImpl = CurrencyServiceImpl(ExchangeRateRepoImpl() )
        serviceImpl.setApiKey(apiKey)

        val url = "http://localhost:$port/ws/currencyConvertService"
        Endpoint.publish( url , serviceImpl)
        println("Service is running on : $url?wsdl")

    }catch (e : Exception){
        throw e
    }

}