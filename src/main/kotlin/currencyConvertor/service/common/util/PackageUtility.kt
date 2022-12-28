package currencyConvertor.service.common.util

import java.io.File

object PackageUtility {
    private fun addFile(file : File, list:  MutableList<File>){
        if(file.isDirectory){
            file.listFiles().forEach {
                addFile(it , list)
            }
            return
        }

        list.add(file)

    }



    fun getClassesInPackage(): List<Class<*>> {
        val classLoader = ClassLoader.getSystemClassLoader()
        val packageURL = classLoader.getResource( "./")
        val path = packageURL.path
        val directory = File(path)
        if (!directory.exists()) {
            return emptyList()
        }
        val classes = mutableListOf<File>()
        addFile(directory,classes)



        return classes.filter {
            it.path.endsWith(".class")
        }.map {
            val classPath = it.relativeTo(directory).path.substringBefore(".class").replace('\\','.')
            classLoader.loadClass(classPath)
        }
    }
}