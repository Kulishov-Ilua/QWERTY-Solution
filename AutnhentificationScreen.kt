import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import org.json.JSONObject


//#####################################################################################################################
//#####################################################################################################################
//###############################               Авторизация/регистрация                 ###############################
//#####################################################################################################################
//####    Автор: Кулишов Илья Вячеславович    #########################################################################
//####    Версия: v.0.0.0.7                   #########################################################################
//####    Дата: 04.02.2024                    #########################################################################
//#####################################################################################################################
//#####################################################################################################################

//=====================================================================================
//Экраны авторизации/ регистрации
//=====================================================================================
var RegistscreenState = mutableStateOf(0)
var logState= mutableStateOf(0)
@Composable
fun AuthorisRegist(){

    Column {
        Box(modifier = Modifier.fillMaxWidth().height(70.dp).background(color = Color(17, 31, 38)),
            contentAlignment = Alignment.CenterStart) {
            Text("QWERTY Solution", style = TextStyle(
                color= Color.White,
                fontSize = 20.sp
            ), modifier = Modifier.padding(start = 60.dp))
        }
        if(RegistscreenState.value==0) LogIn()
        if(RegistscreenState.value==1) kodBlock()
        if(RegistscreenState.value==2) SignUp()
        if(RegistscreenState.value==3) logInSucces()
        if(RegistscreenState.value==4) registSucces()
        if(RegistscreenState.value==5) registFailed()
    }
}


//=====================================================================================
//Экран авторизации
//=====================================================================================
@Composable
fun LogIn() {
    var colorLog = remember { mutableStateOf(Color.Gray) }
    val log ="1"
    val pas = "1"
    var logIn = remember { mutableStateOf("") }
    var pasIn = remember { mutableStateOf("") }
    var logCorect = remember { mutableStateOf(0) }
    var pasLogCorect = remember { mutableStateOf(true) }
    Column(modifier = Modifier.fillMaxSize().background(themes[0].mainColor/*(245, 246, 247, 1)*/)) {

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            Box(Modifier.width(448.dp).background(Color.White, shape = RoundedCornerShape(15.dp))){
                Column {
                    Box(modifier = Modifier.padding(top=30.dp).fillMaxWidth(), contentAlignment = Alignment.Center){
                        Text("Авторизация", style = TextStyle(
                            color = Color.Black,
                            fontSize = 20.sp,
                        )
                        )
                    }
                    Box(modifier = Modifier.padding(top = 5.dp).fillMaxWidth(), contentAlignment = Alignment.Center ){
                        Text("Пожалуйста, введите данные вашего профиля в полях.", style = TextStyle(
                            fontSize = 12.sp,
                            color= Color(0,0,0)
                        )
                        )
                    }
                    Box(modifier = Modifier.padding(top=30.dp).fillMaxWidth(), contentAlignment = Alignment.Center){
                        Column(modifier = Modifier.padding(start = 30.dp, end = 30.dp)) {
                            Text("Логин/Почта", style = TextStyle(
                                fontSize = 12.sp,
                                color = Color.Black
                            )
                            )
                            Box(modifier = Modifier.padding(top=5.dp).height(50.dp).fillMaxWidth()/*.border(width = 3.dp,
                                shape = RoundedCornerShape(15.dp), color =colorLog.value )*/){
                                TextField(
                                    value = logIn.value,
                                    onValueChange = {logIn.value=it},
                                    label = { Text("Введите логин") },
                                    singleLine = true,
                                    isError = !emailUICorrect(logIn.value),
                                    modifier = Modifier.fillMaxSize().background(color = Color.White).border(
                                        width = 2.dp, shape = RoundedCornerShape(15.dp),
                                        color = if(!emailUICorrect(logIn.value)||
                                            logState.value==1) Color.Red else Color.Gray
                                    ),
                                    shape = RoundedCornerShape(15.dp),
                                    colors = TextFieldDefaults.textFieldColors(
                                        backgroundColor = Color.White,
                                        focusedLabelColor = Color(17, 69, 51),
                                        focusedIndicatorColor = Color.Transparent,
                                        unfocusedIndicatorColor = Color.Transparent,
                                        errorIndicatorColor = Color.Transparent
                                    )
                                )
                            }
                        }
                    }
                    if(!emailUICorrect(logIn.value)){
                        Box(modifier = Modifier.fillMaxWidth().padding(end = 30.dp, top = 5.dp),
                            contentAlignment = Alignment.CenterEnd){
                            Text("Некорректный логин", style =
                            TextStyle(
                                fontSize = 12.sp,
                                color = Color.Red
                            )
                            )
                        }
                    }
                    Box(modifier = Modifier.padding(top=15.dp).fillMaxWidth(), contentAlignment = Alignment.Center){
                        Column(modifier = Modifier.padding(start = 30.dp, end = 30.dp)) {
                            Text("Пароль", style = TextStyle(
                                fontSize = 12.sp,
                                color = Color.Black
                            )
                            )
                            Box(modifier = Modifier.padding(top=5.dp).height(50.dp).fillMaxWidth()/*.border(width = 3.dp,
                                shape = RoundedCornerShape(15.dp), color =colorLog.value )*/){
                                TextField(
                                    value = pasIn.value,
                                    onValueChange = {pasIn.value=it},
                                    label = { Text("Введите пароль") },
                                    singleLine = true,
                                    visualTransformation = PasswordVisualTransformation(),
                                    modifier = Modifier.fillMaxSize().background(color = Color.White).border(
                                        width = 2.dp, shape = RoundedCornerShape(15.dp),
                                        color = if(logState.value==1) Color.Red else Color.Gray
                                    ),
                                    shape = RoundedCornerShape(15.dp),
                                    colors = TextFieldDefaults.textFieldColors(
                                        backgroundColor = Color.White,
                                        focusedLabelColor = Color(17, 69, 51),
                                        focusedIndicatorColor = Color.Transparent,
                                        unfocusedIndicatorColor = Color.Transparent,
                                        errorIndicatorColor = Color.Transparent
                                    )
                                )
                            }
                        }
                    }
                    if(logState.value==1){
                        Box(modifier = Modifier.fillMaxWidth().padding(end = 30.dp, top = 5.dp),
                            contentAlignment = Alignment.CenterEnd){
                            Text("Неверный логин или пароль", style =
                            TextStyle(
                                fontSize = 12.sp,
                                color = Color.Red
                            )
                            )
                        }
                    }
                    Box(modifier = Modifier.padding(top = 30.dp, start = 30.dp, end = 30.dp
                        , bottom = 15.dp).fillMaxWidth().height(50.dp).background(color =themes[0].corpColor,
                        shape = RoundedCornerShape(15.dp)
                    ).clickable {
                        if(emailUICorrect(logIn.value)){
                            var transact = JSONObject()
                            transact.put("username", logIn.value)
                            transact.put("password", pasIn.value)
                            //transact.put("department", "HR")
                            getRequest("http://localhost:3000", callbackServer)
                            if(serverState.value==0){
                                getRequest1("http://localhost:3000/login",transact.toString(),callbackLogIn)
                            }
                        }
                    }){
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                            Text("Войти", style = TextStyle(
                                fontSize = 15.sp,
                                color = Color.White
                            )
                            )
                        }
                    }

                    Box(modifier = Modifier.padding(start=30.dp, end=30.dp, bottom = 30.dp).fillMaxWidth(),
                        contentAlignment = Alignment.CenterEnd){
                        Row{
                            Text("Нет аккаунта? ", style = TextStyle(
                                fontSize = 12.sp,
                                color = Color.Gray
                            )
                            )
                            Text("Регистрация", style = TextStyle(
                                fontSize = 12.sp,
                                color = themes[0].corpColor
                            ),
                                modifier = Modifier.clickable {
                                    RegistscreenState.value=1
                                }
                            )
                        }
                    }

                }
            }
        }
    }
}


//=====================================================================================
//Экран регистрации
//=====================================================================================
@Composable
fun SignUp() {
    var logCreate = remember { mutableStateOf("") }
    var pasCreate = remember { mutableStateOf("") }
    var pasTwo = remember { mutableStateOf("") }
    var logCorect1 = remember { mutableStateOf(0) }
    var name = remember { mutableStateOf("") }
    var surname = remember { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxSize().background(themes[0].mainColor/*(245, 246, 247, 1)*/)) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            Box(Modifier.width(448.dp).background(Color.White, shape = RoundedCornerShape(15.dp))){
                Column {
                    Box(modifier = Modifier.padding(top=15.dp, start = 15.dp).clickable {
                        RegistscreenState.value=0
                    }){
                        Text("<Назад", style = TextStyle(
                            fontSize = 12.sp,
                            color = Color(0, 0, 0)
                        )
                        )
                    }
                    Box(modifier = Modifier.padding(top=30.dp).fillMaxWidth(), contentAlignment = Alignment.Center){
                        Text("Регистрация", style = TextStyle(
                            color = Color.Black,
                            fontSize = 20.sp,
                        )
                        )
                    }
                    Box(modifier = Modifier.padding(top = 5.dp).fillMaxWidth(), contentAlignment = Alignment.Center ){
                        Text("Пожалуйста, введите данные вашего будущего профиля в полях.", style = TextStyle(
                            fontSize = 12.sp,
                            color= Color(0,0,0)
                        )
                        )
                    }
                    Box(modifier = Modifier.padding(top=30.dp).fillMaxWidth(), contentAlignment = Alignment.Center){
                        Column(modifier = Modifier.padding(start = 30.dp, end = 30.dp)) {
                            Text("Почта", style = TextStyle(
                                fontSize = 12.sp,
                                color = Color.Black
                            )
                            )
                            Box(modifier = Modifier.padding(top=5.dp).height(50.dp).fillMaxWidth()/*.border(width = 3.dp,
                                shape = RoundedCornerShape(15.dp), color =colorLog.value )*/){
                                TextField(
                                    value = logCreate.value,
                                    onValueChange = {logCreate.value=it},
                                    label = { Text("Введите почту") },
                                    singleLine = true,
                                    isError = !emailUICorrect(logCreate.value),
                                    modifier = Modifier.fillMaxSize().background(color = Color.White).border(
                                        width = 2.dp, shape = RoundedCornerShape(15.dp),
                                        color = if(!emailUICorrect(logCreate.value)) Color.Red else Color.Gray
                                    ),
                                    shape = RoundedCornerShape(15.dp),
                                    colors = TextFieldDefaults.textFieldColors(
                                        backgroundColor = Color.White,
                                        focusedLabelColor = Color(17, 69, 51),
                                        focusedIndicatorColor = Color.Transparent,
                                        unfocusedIndicatorColor = Color.Transparent,
                                        errorIndicatorColor = Color.Transparent
                                    )
                                )
                            }
                        }
                    }
                    if(!emailUICorrect(logCreate.value)){
                        Box(modifier = Modifier.fillMaxWidth().padding(end = 30.dp, top = 5.dp),
                            contentAlignment = Alignment.CenterEnd){
                            Text("Некорректный адрес почты", style =
                            TextStyle(
                                fontSize = 12.sp,
                                color = Color.Red
                            )
                            )
                        }
                    }
                    Row {
                        Box(modifier = Modifier.padding(top=15.dp).fillMaxWidth(0.5f), contentAlignment = Alignment.CenterStart){
                            Column(modifier = Modifier.width(350.dp).padding(start = 30.dp, end = 30.dp)) {
                                Text("Имя", style = TextStyle(
                                    fontSize = 12.sp,
                                    color = Color.Black
                                )
                                )
                                Box(modifier = Modifier.padding(top=5.dp).height(50.dp).fillMaxWidth()/*.border(width = 3.dp,
                                shape = RoundedCornerShape(15.dp), color =colorLog.value )*/){
                                    TextField(
                                        value = name.value,
                                        onValueChange = {name.value=it},
                                        singleLine = true,
                                        label = { Text("Введите имя") },
                                        modifier = Modifier.fillMaxSize().background(color = Color.White).border(
                                            width = 2.dp, shape = RoundedCornerShape(15.dp),
                                            color = if(nameCorrect(name.value)==false) Color.Red else Color.Gray
                                        ),
                                        shape = RoundedCornerShape(15.dp),
                                        colors = TextFieldDefaults.textFieldColors(
                                            backgroundColor = Color.White,
                                            focusedLabelColor = Color(17, 69, 51),
                                            focusedIndicatorColor = Color.Transparent,
                                            unfocusedIndicatorColor = Color.Transparent,
                                            errorIndicatorColor = Color.Transparent
                                        )
                                    )
                                }
                                if(nameCorrect(name.value)==false){
                                    Box(modifier = Modifier.fillMaxWidth().padding(start = 30.dp, top = 5.dp),
                                        contentAlignment = Alignment.Center){
                                        Text("Некорректное имя", style =
                                        TextStyle(
                                            fontSize = 12.sp,
                                            color = Color.Red
                                        )
                                        )
                                    }
                                }

                            }
                        }

                        Box(modifier = Modifier.padding(top=15.dp).fillMaxWidth(), contentAlignment = Alignment.CenterStart){
                            Column(modifier = Modifier.width(350.dp).padding( end = 30.dp)) {
                                Text("Фамилия", style = TextStyle(
                                    fontSize = 12.sp,
                                    color = Color.Black
                                )
                                )
                                Box(modifier = Modifier.padding(top=5.dp).height(50.dp).fillMaxWidth()/*.border(width = 3.dp,
                                shape = RoundedCornerShape(15.dp), color =colorLog.value )*/){
                                    TextField(
                                        value = surname.value,
                                        onValueChange = {surname.value=it},
                                        singleLine = true,
                                        label = { Text("Введите фамилию") },
                                        modifier = Modifier.fillMaxSize().background(color = Color.White).border(
                                            width = 2.dp, shape = RoundedCornerShape(15.dp),
                                            color = if(nameCorrect(surname.value)==false) Color.Red else Color.Gray
                                        ),
                                        shape = RoundedCornerShape(15.dp),
                                        colors = TextFieldDefaults.textFieldColors(
                                            backgroundColor = Color.White,
                                            focusedLabelColor = Color(17, 69, 51),
                                            focusedIndicatorColor = Color.Transparent,
                                            unfocusedIndicatorColor = Color.Transparent,
                                            errorIndicatorColor = Color.Transparent
                                        )
                                    )
                                }

                                if(nameCorrect(surname.value)==false){
                                    Box(modifier = Modifier.fillMaxWidth().padding(start = 30.dp, top = 5.dp),
                                        contentAlignment = Alignment.Center){
                                        Text("Некорректное имя", style =
                                        TextStyle(
                                            fontSize = 12.sp,
                                            color = Color.Red
                                        )
                                        )
                                    }
                                }
                            }
                        }

                    }



//------------------------------------------------------------------
                    Box(modifier = Modifier.padding(top=15.dp).fillMaxWidth(), contentAlignment = Alignment.Center){
                        Column(modifier = Modifier.padding(start = 30.dp, end = 30.dp)) {
                            Text("Пароль", style = TextStyle(
                                fontSize = 12.sp,
                                color = Color.Black
                            )
                            )
                            Box(modifier = Modifier.padding(top=5.dp).height(50.dp).fillMaxWidth()/*.border(width = 3.dp,
                                shape = RoundedCornerShape(15.dp), color =colorLog.value )*/){
                                TextField(
                                    value = pasCreate.value,
                                    onValueChange = { newV:String -> pasCreate.value=newV},
                                    label = { Text("Введите пароль") },
                                    visualTransformation = PasswordVisualTransformation(),
                                    singleLine = true,
                                    //keyboardOptions = KeyboardType.Password,
                                    isError = !pasCorectUi(pasCreate.value)||pasCreate.value!=""&&pasTwo.value!=""&&pasTwo.value!=pasCreate.value,
                                    modifier = Modifier.fillMaxSize().background(color = Color.White).border(
                                        width = 2.dp, shape = RoundedCornerShape(15.dp),
                                        color = if(!pasCorectUi(pasCreate.value)||pasCreate.value!=""&&pasTwo.value!=""&&pasTwo.value!=pasCreate.value)
                                            Color.Red else if(pasCorrect(pasCreate.value)==0 || pasCreate.value=="")
                                                Color.Gray else Color.Yellow
                                    ),
                                    shape = RoundedCornerShape(15.dp),
                                    colors = TextFieldDefaults.textFieldColors(
                                        backgroundColor = Color.White,
                                        focusedLabelColor = Color(17, 69, 51),
                                        focusedIndicatorColor = Color.Transparent,
                                        unfocusedIndicatorColor = Color.Transparent,
                                        errorIndicatorColor = Color.Transparent
                                    )
                                )
                            }


                        }
                    }
                    if(pasCorrect(pasCreate.value)==1){
                        Box(modifier = Modifier.fillMaxWidth(0.5f).padding(start = 30.dp, top = 5.dp),
                            contentAlignment = Alignment.Center){
                            Text("Используйте заглавные и строчные символы", style =
                            TextStyle(
                                fontSize = 12.sp,
                                color = Color.Yellow
                            )
                            )
                        }
                    }
                    if(pasCorectUi(pasCreate.value)==false){
                        Box(modifier = Modifier.fillMaxWidth(0.5f).padding(start = 30.dp, top = 5.dp),
                            contentAlignment = Alignment.Center){
                            Text("Не менее 8 символов", style =
                            TextStyle(
                                fontSize = 12.sp,
                                color = Color.Red
                            )
                            )
                        }
                    }
                    Box(modifier = Modifier.padding(top=15.dp).fillMaxWidth(), contentAlignment = Alignment.Center){
                        Column(modifier = Modifier.padding(start = 30.dp, end = 30.dp)) {
                            Text("Пароль повторно", style = TextStyle(
                                fontSize = 12.sp,
                                color = Color.Black
                            )
                            )

                            Box(modifier = Modifier.padding(top=5.dp).height(50.dp).fillMaxWidth()/*.border(width = 3.dp,
                                shape = RoundedCornerShape(15.dp), color =colorLog.value )*/){
                                TextField(
                                    value = pasTwo.value,
                                    onValueChange = { newValue -> pasTwo.value = newValue },
                                    label = { Text("Повторите пароль") },
                                    visualTransformation = PasswordVisualTransformation(),
                                    singleLine = true,
                                    //keyboardOptions = KeyboardType.Password,
                                    isError = pasCreate.value.isNotEmpty() && pasTwo.value.isNotEmpty() && pasTwo.value != pasCreate.value,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(color = Color.White)
                                        .border(
                                            width =  2.dp,
                                            shape = RoundedCornerShape(15.dp),
                                            color = if (pasCreate.value.isNotEmpty() && pasTwo.value.isNotEmpty() && pasTwo.value != pasCreate.value) Color.Red else Color.Gray
                                        ),
                                    shape = RoundedCornerShape(15.dp),
                                    colors = TextFieldDefaults.textFieldColors(
                                        backgroundColor = Color.White,
                                        focusedLabelColor = Color(17,  69,  51),
                                        focusedIndicatorColor = Color.Transparent,
                                        unfocusedIndicatorColor = Color.Transparent,
                                        errorIndicatorColor = Color.Transparent
                                    )
                                )

                            }


                        }
                    }
                    if(pasCreate.value!=""&&pasTwo.value!=""&&pasTwo.value!=pasCreate.value){
                        Box(modifier = Modifier.fillMaxWidth(0.5f).padding(start = 30.dp, top = 5.dp),
                            contentAlignment = Alignment.Center){
                            Text("Пароли не совпадают", style =
                            TextStyle(
                                fontSize = 12.sp,
                                color = Color.Red
                            )
                            )
                        }
                    }


                    Box(modifier = Modifier.padding(top = 30.dp, start = 30.dp, end = 30.dp
                        , bottom = 30.dp).fillMaxWidth().height(50.dp).background(color =themes[0].corpColor,
                        shape = RoundedCornerShape(15.dp)
                    ).clickable {
                        if(pasTwo.value==pasCreate.value&&pasCorectUi(pasCreate.value)&&pasCorectUi(pasTwo.value)
                            &&pasCreate.value!=""&&pasTwo.value!=""&&name.value!=""&&surname.value!=""&&logCreate.value!=""
                            &&nameCorrect(name.value)&&nameCorrect(surname.value)&&emailUICorrect(logCreate.value)){
                            getRequest("http://localhost:3000", callbackServer)
                            if(serverState.value==0){
                                var transact = JSONObject()
                                transact.put("username", logCreate.value)
                                transact.put("password", pasCreate.value)
                                transact.put("department", "HR")
                                getRequest1("http://localhost:3000/register",transact.toString(),callbackRegist)
                                //RegistscreenState.value=0
                            }
                        }


                    }){
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                            Text("Регистрация", style = TextStyle(
                                fontSize = 15.sp,
                                color = Color.White
                            )
                            )
                        }
                    }
                }
            }
        }
    }
}


//=====================================================================================
//Экран ввода кода
//=====================================================================================
@Composable
fun kodBlock() {
    var kod = remember { mutableStateOf("") }
    var kodState = remember { mutableStateOf(0) }
    Column(modifier = Modifier.fillMaxSize().background(themes[0].mainColor/*(245, 246, 247, 1)*/)) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Box(Modifier.width(448.dp).background(Color.White, shape = RoundedCornerShape(15.dp))) {
                Column {
                    Box(modifier = Modifier.padding(top=15.dp, start = 15.dp).clickable {
                        RegistscreenState.value=0
                    }){
                        Text("<Назад", style = TextStyle(
                                fontSize = 12.sp,
                                color = Color(0, 0, 0)
                            )
                        )
                    }
                    Box(modifier = Modifier.padding(top = 30.dp).fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Text(
                            "Код доступа", style = TextStyle(
                                color = Color.Black,
                                fontSize = 20.sp,
                            )
                        )
                    }
                    Box(modifier = Modifier.padding(top = 5.dp).fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Text(
                            "Пожалуйста, введите код доступа к регистрации в поле.", style = TextStyle(
                                fontSize = 12.sp,
                                color = Color(0, 0, 0)
                            )
                        )
                    }
                    Box(modifier = Modifier.padding(top = 30.dp).fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Column(modifier = Modifier.padding(start = 30.dp, end = 30.dp)) {
                            Text(
                                "Код доступа", style = TextStyle(
                                    fontSize = 12.sp,
                                    color = Color.Black
                                )
                            )
                            Box(
                                modifier = Modifier.padding(top = 5.dp).height(50.dp).fillMaxWidth()/*.border(width = 3.dp,
                                shape = RoundedCornerShape(15.dp), color =colorLog.value )*/
                            ) {
                                TextField(
                                    value = kod.value,
                                    onValueChange = { kod.value = it },
                                    label = { Text("Введите код") },
                                    singleLine = true,
                                    isError = kod.value!=""&&kodState.value==1,
                                    modifier = Modifier.fillMaxSize().background(color = Color.White).border(
                                        width = 2.dp, shape = RoundedCornerShape(15.dp),
                                        color = if (kod.value!=""&&kodState.value==1) Color.Red else Color.Gray
                                    ),
                                    shape = RoundedCornerShape(15.dp),
                                    colors = TextFieldDefaults.textFieldColors(
                                        backgroundColor = Color.White,
                                        focusedLabelColor = Color(17, 69, 51),
                                        focusedIndicatorColor = Color.Transparent,
                                        unfocusedIndicatorColor = Color.Transparent,
                                        errorIndicatorColor = Color.Transparent
                                    )
                                )
                            }
                            if(kodState.value==1&&kod.value!=""){
                                Box(modifier = Modifier.fillMaxWidth().padding(start = 30.dp, top = 5.dp),
                                    contentAlignment = Alignment.CenterEnd){
                                    Text("Неверный код", style =
                                    TextStyle(
                                        fontSize = 12.sp,
                                        color = Color.Red
                                    )
                                    )
                                }
                            }
                        }
                    }
                    Box(modifier = Modifier.padding(top = 30.dp, start = 30.dp, end = 30.dp
                        , bottom = 30.dp).fillMaxWidth().height(50.dp).background(color =themes[0].corpColor,
                        shape = RoundedCornerShape(15.dp)
                    ).clickable {
                        if(kod.value==corpKod.value){
                            kodState.value=0
                            RegistscreenState.value=2
                        }
                        else{
                            kodState.value=1
                        }

                    }){
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                            Text("Подтвердить", style = TextStyle(
                                fontSize = 15.sp,
                                color = Color.White
                            )
                            )
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun registFailed(){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Ошибка регистрации")
            Box(modifier = Modifier.padding(top=15.dp).width(100.dp).height(70.dp).background(themes[0].corpColor,
                shape = RoundedCornerShape(30)).clickable {
                RegistscreenState.value=0
            }, contentAlignment = Alignment.Center){
                Text("Выйти", style = TextStyle(
                    fontSize = 32.sp
                ))
            }
        }
    }
}

@Composable
fun registSucces(){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Успешно")
            Box(modifier = Modifier.padding(top=15.dp).width(100.dp).height(70.dp).background(themes[0].corpColor,
                shape = RoundedCornerShape(30)).clickable {
                RegistscreenState.value=0
            }, contentAlignment = Alignment.Center){
                Text("Войти", style = TextStyle(
                    fontSize = 32.sp
                ))
            }
        }
    }
}
@Composable
fun logInSucces(){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = token.value)

        }
    }
}