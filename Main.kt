import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.TextFieldDefaults.indicatorLine
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import java.net.http.HttpClient
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

var userState = mutableStateOf(0)
var serverState = mutableStateOf(1)
fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        val client = OkHttpClient()
        getRequest(urlDefault, callbackServer)
        if(serverState.value==1/*&&1==0*/){
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        "Ошибка связи с сервером", style = TextStyle(
                            fontSize = 64.sp,

                        )
                    )
                    Box(modifier = Modifier.padding(top=15.dp).width(300.dp).height(70.dp).background(themes[0].corpColor,
                        shape = RoundedCornerShape(30)).clickable {
                        getRequest(urlDefault, callbackServer)
                    }, contentAlignment = Alignment.Center){
                        Text("Перезагрузить", style = TextStyle(
                            fontSize = 32.sp,
                            color = Color.White
                        ))
                    }
                }
            }
        }
        else{
            if(userState.value==0) AuthorisRegist()
            else applicScreenMain()
        }




        //SignUp()
        //LogIn()
        //Text("${('a').toInt()} ${('z').toInt()} ${('A').toInt()} ${('Z').toInt()} ${('А').toInt()} ${('Я').toInt()} ${('а').toInt()} ${('я').toInt()}")
    }
}
