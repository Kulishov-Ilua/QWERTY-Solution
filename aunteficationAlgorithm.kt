import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.jetbrains.skia.impl.Log

import java.io.IOException

//#####################################################################################################################
//#####################################################################################################################
//###############################      Авторизация/регистрация - блок алгоритмов        ###############################
//#####################################################################################################################
//####    Автор: Кулишов Илья Вячеславович    #########################################################################
//####    Версия: v.0.0.0.7                   #########################################################################
//####    Дата: 04.02.2024                    #########################################################################
//#####################################################################################################################
//#####################################################################################################################

//=====================================================================================
//Функции проверки корректности email
//Входные данные:
//                      adress:String   - адресс
//Возращаемое значение:
//                      ret:Boolean     - да/нет
//=====================================================================================
fun emailFormatDetect(adress:String): Boolean{
    var ret = false
    var dog = false
    var dot = false
    for(i in adress){
        if(i=='@'){
            dog=true
        }
        if(dog==true){
            if(i=='.'){
                dot=true
            }
        }
    }
    if(dog&&dot){
        ret=true
    }
    return ret
}

//=====================================================================================
//Функции проверки корректности email, без учета пустой строки
//Входные данные:
//                      adress:String   - адресс
//Возращаемое значение:
//                      ret:Boolean     - да/нет
//=====================================================================================
fun emailUICorrect(adress: String):Boolean{
    var ret = true
    if(adress!=""){
        ret=emailFormatDetect(adress)
    }
    return ret
}


//=====================================================================================
//Функции проверки корректности имени/фамилии
//Входные данные:
//                      name:String   - имя
//Возращаемое значение:
//                      ret:Boolean   - да/нет
//=====================================================================================
fun nameCorrect(name:String):Boolean{
    var ret = true
    for(i in name){
        if(!((i.toInt()>96)&&(i.toInt()<123))&&!((i.toInt()>64)&&(i.toInt()<91))&&!((i.toInt()>1039)&&(i.toInt()<1104))){
            ret = false
        }
    }
    return ret
}

//=====================================================================================
//Функции проверки корректности пароля
//Входные данные:
//                      pas:String   - пароль
//Возращаемое значение:
//                      ret:Int   - 0 - надежный
//                                  1 - можно и лучше
//                                  2 - не надежный
//=====================================================================================
fun pasCorrect(pas:String):Int{
    var ret = 2
    var count = 0
    var big = false
    var small = false
    for(i in pas){
        if(i.toInt()>64&&i.toInt()<91){
            big = true
        }
        if(i.toInt()>96&&i.toInt()<124){
            small = true
        }
        count++
    }
    if(count<8){
        ret=2
    }
    else{
        if(big&&small){
            ret=0
        }
        else{
            ret=1
        }
    }
    return ret
}


//=====================================================================================
//Функции проверки корректности пароля учитывая пустую строку
//Входные данные:
//                      pas:String   - пароль
//Возращаемое значение:
//                      ret:Boolean   - да/нет
//=====================================================================================
fun pasCorectUi(pas:String):Boolean{
    var ret = true
    if (pas!=""){
        if(pasCorrect(pas)==2){
            ret=false
        }
    }
    return ret
}

fun getRequest(url: String, callback: Callback) {
    val client = OkHttpClient()
    val request = Request.Builder()
        .url(url)
        .build()

    client.newCall(request).enqueue(callback)
}
fun getRequest1(url: String, json: String? = null, callback: Callback) {
    val client = OkHttpClient()
    val mediaType = "application/json; charset=utf-8".toMediaTypeOrNull()
    val requestBuilder = Request.Builder().url(url)

    if (json != null) {
        val body = json .toRequestBody(mediaType)
        requestBuilder.post(body) // Используйте .post() для POST запросов
    }

    val request = requestBuilder.build()
    client.newCall(request).enqueue(callback)
}


val callback = object : Callback {
    override fun onFailure(call: Call, e: IOException) {
        e.printStackTrace()
    }

    override fun onResponse(call: Call, response: Response) {
        if (response.isSuccessful) {
            val myResponse = response.body?.string()
            println(myResponse)
        } else {
            println("Request failed")
            response.body?.close()
        }
    }
}
val callbackServer = object : Callback {
    override fun onFailure(call: Call, e: IOException) {
        e.printStackTrace()
    }

    override fun onResponse(call: Call, response: Response) {
        if (response.isSuccessful) {
            serverState.value=0
        } else {
            serverState.value=1
            response.body?.close()
        }
    }
}

val callbackRegist = object : Callback {

    override fun onFailure(call: Call, e: IOException) {
        e.printStackTrace()
    }

    override fun onResponse(call: Call, response: Response) {
        //println(response.message)
        //println(response.code)
        if (response.isSuccessful) {
            RegistscreenState.value=4
        } else {
            RegistscreenState.value=5
            response.body?.close()
        }
    }
}
val callbackLogIn = object : Callback {
    override fun onFailure(call: Call, e: IOException) {
        e.printStackTrace()
    }

    override fun onResponse(call: Call, response: Response) {

        if (response.isSuccessful) {
            val myResponse = response.body?.string()
            token.value=myResponse.toString()
            logState.value=0
            RegistscreenState.value=3

        } else {
           logState.value=1

            response.body?.close()

        }

    }
}

val callbackUserData = object : Callback {
    override fun onFailure(call: Call, e: IOException) {
        e.printStackTrace()
    }

    override fun onResponse(call: Call, response: Response) {

        if (response.isSuccessful) {
            val myResponse = response.body?.string()
            mySurname.value=response.message

        } else {
            mySurname.value="Error"
            response.body?.close()

        }

    }
}