import ramesh.data.ExchangeRateRepoImpl
import ramesh.service.Service
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

        args.indexOf("-p").also {
            if(it >= 0){
                if(args.size > it+1){
                    port = args[it+1]
                }
            }
        }


        val url = "http://localhost:$port/ws/currencyConvertService"
        Endpoint.publish( url , Service(ExchangeRateRepoImpl() ) )
        print("Service is running on : $url?wsdl")
    }catch (e : Exception){
        println(e.message)
    }

}