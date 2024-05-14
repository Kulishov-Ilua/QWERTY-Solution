//#####################################################################################################################
//#####################################################################################################################
//###############################                   Модуль заявок                       ###############################
//#####################################################################################################################
//####    Автор: Кулишов Илья Вячеславович    #########################################################################
//####    Версия: v.0.0.0.1                   #########################################################################
//####    Дата: 23.03.2024                    #########################################################################
//#####################################################################################################################
//#####################################################################################################################



import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import org.json.JSONObject

/*
fun main() = application {
    Window(onCloseRequest = ::exitApplication,
        state = rememberWindowState(width = Dp.Unspecified, height = Dp.Unspecified),
        title = "QWERTY solution",
        //icon = painterResource("pinquin.png")
    ) {
        applicScreenMain()
        //createForm()
        //redactForm(ApllicArray[0])
        //answerForm(ApllicArray[0])
        //viewForm(ApllicArray[0])
    }
}
 */
//#####################################################################################
//Переменная состояния экрана                                              ############
//#####################################################################################
var ApplicScreen = mutableStateOf(0)
var applicTek =  mutableStateOf(ApllicArray[0])
var activdep = mutableStateOf(0)
var professionState = mutableStateOf(false)
var profUserActiv = mutableStateOf(userList[0])

//=====================================================================================
//Экран заявок
//=====================================================================================
@Preview
@Composable
fun applicScreenMain(){
      var transact = JSONObject()
    transact.put("token",token.value)
    getRequest1(urlUserData, transact.toString(), callbackUserData)
    var isAdmin = false
    var findApplicForm = remember { mutableStateOf("") }
    var flagUserPanel = remember { mutableStateOf(false) }
        Column{
            Box(modifier = Modifier.fillMaxWidth().height(70.dp).background(color = Color(17, 31, 38)),
                contentAlignment = Alignment.Center) {
                Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {
                    Text(
                        "QWERTY Solution", style = TextStyle(
                            color = Color.White,
                            fontSize = 20.sp
                        ), modifier = Modifier.padding(start = 100.dp)
                    )
                }
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Box(
                        modifier = Modifier.height(55.dp).width(600.dp)
                            .background(color = Color.White, shape = RoundedCornerShape(15))
                    ) {
                        TextField(
                            value = findApplicForm.value,
                            onValueChange = {findApplicForm.value=it},
                            placeholder = {if(findApplicForm.value==""){ Text("Поиск")} },
                            singleLine = true,
                            leadingIcon ={ Icon( painterResource("lupa.svg"), contentDescription = "find")},
                            textStyle = TextStyle(
                                fontSize = 18.sp,
                                color = Color.Gray
                            ),
                            modifier = Modifier.padding( 2.dp).fillMaxSize()/*.background(color = Color.White).border(
                                width = 2.dp, shape = RoundedCornerShape(15.dp),
                                color = Color.Gray
                            )*/,
                            //shape = RoundedCornerShape(15.dp),
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
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            "${mySurname.value} ${myName.value[0]}.", style = TextStyle(
                                color = Color.White,
                                fontSize = 16.sp
                            ), modifier = Modifier.padding( end = 10.dp)
                        )
                        Box(
                            modifier = Modifier.padding(end=100.dp).width(55.dp).height(55.dp).background(
                                color = Color.White,
                                shape = RoundedCornerShape(15)
                            ).clickable {
                                if(flagUserPanel.value==false) {
                                    flagUserPanel.value = true
                                }else{
                                    flagUserPanel.value=false
                                }
                            }
                        ) {

                        }
                    }
                }


            }
            if(depUpdateFlag.value) {
                Box(
                    modifier = Modifier.fillMaxWidth().height(45.dp).background(themes[0].corpColor),
                    contentAlignment = Alignment.Center
                ) {
                    LazyRow {
                        items(depList) { item ->
                            if (findStrStr(findUser(userTek).departament, item.id.toString()) == true) {
                                Column(modifier = Modifier.padding(start = 25.dp, end = 25.dp).clickable {
                                    activdep.value = item.id
                                    ApplicScreen.value = 0
                                }, horizontalAlignment = Alignment.CenterHorizontally) {
                                    Box(contentAlignment = Alignment.Center) {
                                        Text(
                                            item.name, style = TextStyle(
                                                fontSize = 16.sp,
                                                color = Color.White
                                            )
                                        )
                                    }
                                    if (activdep.value == item.id) {
                                        Box(
                                            Modifier.padding(top = 5.dp).width(150.dp).height(2.dp)
                                                .background(color = Color.White)
                                        )
                                    }

                                }
                            }
                        }
                    }

                }
            }
            //главный экран заявок
            if(ApplicScreen.value==0){
                Box(Modifier.padding(top=20.dp)){
                    LazyColumn() {
                        //item{applicBlock(true,0)}
                        //item {applicBlock(true,0) }
                        item{ Box(){
                            applicBlock(true, activdep.value, if(activdep.value==0) false else true)
                        } }

                        item{ Box(){
                            applicBlock(false, activdep.value, if(activdep.value==0) false else true)
                        } }
                    }
                    

                    //boxApplic(ApllicArray[0])
                }
            }
            //экран создания формы
            if(ApplicScreen.value==1){
                Box(Modifier.padding(top=20.dp)){
                     createForm()

                }
            }
            // экран просмотра формы
            if(ApplicScreen.value==2){
                Box(Modifier.padding(top=20.dp)){
                    viewForm(applicTek.value)

                }
            }

            //экран редактирования формы
            if(ApplicScreen.value==3){
                Box(Modifier.padding(top=20.dp)){
                    redactForm(applicTek.value)

                }
            }
            //экран ответа на форму
            if(ApplicScreen.value==4){
                Box(Modifier.padding(top=20.dp)){
                    answerForm(applicTek.value)

                }
            }
            //экран пользователей
            if(ApplicScreen.value==5) {
                if(createDepFlag.value){
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                        var kod = remember { mutableStateOf("") }
                        //var kodState = remember { mutableStateOf(0) }
                        Column(modifier = Modifier.fillMaxSize().background(themes[0].mainColor/*(245, 246, 247, 1)*/)) {
                            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                Box(Modifier.width(448.dp).background(Color.White, shape = RoundedCornerShape(15.dp))) {
                                    Column {
                                        Box(modifier = Modifier.padding(top=15.dp, start = 15.dp).clickable {
                                            createDepFlag.value=false
                                        }){
                                            Text("< Назад", style = TextStyle(
                                                fontSize = 12.sp,
                                                color = Color(0, 0, 0)
                                            )
                                            )
                                        }
                                        Box(modifier = Modifier.padding(top = 30.dp).fillMaxWidth(), contentAlignment = Alignment.Center) {
                                            Text(
                                                "Новый департамент", style = TextStyle(
                                                    color = Color.Black,
                                                    fontSize = 20.sp,
                                                )
                                            )
                                        }
                                        Box(modifier = Modifier.padding(top = 5.dp).fillMaxWidth(), contentAlignment = Alignment.Center) {
                                            Text(
                                                "Пожалуйста, введите название нового департамента", style = TextStyle(
                                                    fontSize = 12.sp,
                                                    color = Color(0, 0, 0)
                                                )
                                            )
                                        }
                                        Box(modifier = Modifier.padding(top = 30.dp).fillMaxWidth(), contentAlignment = Alignment.Center) {
                                            Column(modifier = Modifier.padding(start = 30.dp, end = 30.dp)) {
                                                Text(
                                                    "Название департамента", style = TextStyle(
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
                                                        label = { Text("Введите название") },
                                                        singleLine = true,
                                                        modifier = Modifier.fillMaxSize().background(color = Color.White).border(
                                                            width = 2.dp, shape = RoundedCornerShape(15.dp),
                                                            color = if (kod.value!="") themes[0].corpColor else Color.Gray
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
                                        Box(modifier = Modifier.padding(top = 30.dp, start = 30.dp, end = 30.dp
                                            , bottom = 30.dp).fillMaxWidth().height(50.dp).background(color =themes[0].corpColor,
                                            shape = RoundedCornerShape(15.dp)
                                        ).clickable {
                                            var i = 0
                                            for(x in depList) i++
                                            depList+= Dep(i, kod.value,0)
                                            createDepFlag.value=false

                                        }){
                                            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                                                Text("Создать", style = TextStyle(
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
                }else {
                    Box(Modifier.padding(top = 20.dp)) {
                        LazyColumn {
                            item {
                                personBlocklist(true, 0)
                            }
                            items(depList) { dep ->
                                if (dep.id != 0) {
                                    personBlocklist(false, dep.id)
                                }
                            }
                            item{
                                Column(Modifier.padding(start = 110.dp, end=110.dp, bottom = 15.dp).fillMaxWidth()) {
                                    Box(Modifier.fillMaxWidth()){
                                        Column(modifier = Modifier.clickable {
                                            createDepFlag.value=true
                                        }) {
                                            Row(verticalAlignment = Alignment.CenterVertically) {
                                                Box(Modifier.width(18.dp).height(18.dp), contentAlignment = Alignment.Center){
                                                    Text("+", style = TextStyle(
                                                        fontSize = 18.sp
                                                    ), )
                                                }
                                                Text(text = "Создать")


                                            }
                                            Box(Modifier.fillMaxWidth().height(1.dp).background(color = themes[0].secondColor))
                                        }
                                    }


                                }
                            }
                            /*
                            item {
                                Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.BottomEnd){
                                    Box(Modifier.padding(25.dp).width(50.dp).height(50.dp).background(Color.Red).clickable {
                                        createDepFlag.value=true
                                    })
                                }
                            }*/
                        }
                    }
                }
            }



        }
    //окно поиска заявки
    if(findApplicForm.value!="") {
        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.TopCenter){
            Box(Modifier.padding(top = 70.dp).width(600.dp).height(600.dp)
                .background(color = Color.White, shape = RoundedCornerShape(3))
                .border(width= 2.dp, color = Color.Gray, shape = RoundedCornerShape(3))){
                LazyColumn {
                    items(ApllicArray){
                        item-> if(item.author==findUser(userTek).id&&item.name.contains(findApplicForm.value)){
                            Box(Modifier.fillMaxWidth().height(50.dp).clickable {
                                findApplicForm.value=""
                                applicTek.value=item
                                                                                ApplicScreen.value=2
                            }, contentAlignment = Alignment.CenterStart){
                                Column {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(painterResource("lupa.svg"), contentDescription = "", modifier = Modifier.padding(
                                            start=15.dp, end = 5.dp
                                        ))
                                        Text(item.name, modifier = Modifier.padding(15.dp))
                                    }
                                    //Box(Modifier.padding(start=10.dp, end=10.dp).fillMaxWidth().height(2.dp).background(color = Color.Gray, shape = CircleShape))
                                }
                            }
                    }
                    }
                }
            }
        }
    }
    //боковая панель пользователя
    if(flagUserPanel.value==true) {
        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd){
            Box(Modifier.padding(top = 70.dp).width(300.dp).height(180.dp)
                .background(color = Color.White, shape = RoundedCornerShape(10))
                .border(width= 2.dp, color = Color.Gray, shape = RoundedCornerShape(10))){
                Column {
                    Box(Modifier.fillMaxWidth().height(70.dp), contentAlignment = Alignment.CenterStart){
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(Modifier.padding(start = 20.dp).width(45.dp).height(45.dp).background(Color.Gray, shape = RoundedCornerShape(
                                15
                            )))
                            Text("${findUser(userTek).surname} ${findUser(userTek).name[0]}.", modifier = Modifier.padding(start = 20.dp))
                        }
                    }
                    if(adminTek==userTek){
                        Box(modifier = Modifier.fillMaxWidth().height(50.dp).clickable {
                                                                                                            ApplicScreen.value=5
                        }, contentAlignment = Alignment.CenterStart){
                            Text("Сотрудники", modifier = Modifier.padding(start=20.dp))
                        }
                    }
                    Box(modifier = Modifier.fillMaxWidth().height(50.dp).clickable {

                    }, contentAlignment = Alignment.CenterStart){
                        Text("Выйти", modifier = Modifier.padding(start=20.dp))
                     }
                }
            }
        }
    }
    //изменение должности
    if(professionState.value) {
        if(profUserActiv.value.id==userTek){
            depUpdateFlag.value=false
        }
        newProfi = remember { mutableStateOf(profUserActiv.value.departament) }
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Box(
                Modifier.padding(top = 20.dp).width(300.dp).height(300.dp)
                    .background(color = Color.White, shape = RoundedCornerShape(3))
                    .border(width = 2.dp, color = Color.Gray, shape = RoundedCornerShape(3))
            ) {
                Column(Modifier.padding(start = 25.dp, end = 25.dp)) {
                    Box(Modifier.fillMaxWidth().height(60.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            //Box(Modifier.width(50.dp).height(50.dp))
                            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                                Text(
                                    "${profUserActiv.value.surname} ${profUserActiv.value.name}", style =
                                    TextStyle(
                                        fontSize = 18.sp,
                                    ), modifier = Modifier.padding(top = 15.dp)
                                )
                            }
                        }
                    }
                        LazyColumn(Modifier.fillMaxWidth().height(150.dp)) {
                            items(depList) { dep ->
                                if (dep.id != 0) {
                                    Box(
                                        modifier = Modifier.fillMaxWidth().height(50.dp),
                                        contentAlignment = Alignment.CenterStart
                                    ) {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            var flagdep =
                                                remember { mutableStateOf(newProfi.value.contains("_${dep.id}_")) }
                                            flagdep.value = newProfi.value.contains("_${dep.id}_")
                                            Box(Modifier.width(20.dp).height(20.dp).border(
                                                width = 2.dp,
                                                color = Color.Gray,
                                                shape = RoundedCornerShape(30)
                                            )
                                                .background(
                                                    if (flagdep.value) Color.Green else Color.White,
                                                    RoundedCornerShape(30)
                                                ).clickable {
                                                    var updatevalue = newProfi.value
                                                    if (/*updatevalue.contains("_${dep.id}_")*/ "_${dep.id}_" in newProfi.value) {
                                                        updatevalue = updatevalue.replace("_${dep.id}_", "")
                                                        newProfi.value = updatevalue
                                                    } else {
                                                        updatevalue += "_${dep.id}_"
                                                        newProfi.value = updatevalue
                                                    }
                                                })
                                            Text(
                                                "${dep.name}", style = TextStyle(
                                                    fontSize = 16.sp,

                                                    ), modifier = Modifier.padding(start = 15.dp)
                                            )
                                        }
                                    }
                                }
                            }

                        }
                        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(modifier = Modifier.padding(end=25.dp).width(100.dp).height(40.dp)
                                    .background(Color(137, 9, 9), shape =
                                    RoundedCornerShape(5)
                                    ).clickable {
                                        depUpdateFlag.value=true
                                        professionState.value=false
                                    }, contentAlignment = Alignment.Center){
                                    Text("Отменить", style = TextStyle(
                                        fontSize = 14.sp,
                                        color = themes[0].mainColor
                                    )
                                    )
                                }
                                Box(modifier = Modifier.width(100.dp).height(40.dp)
                                    .background(Color(12, 69, 51), shape =
                                    RoundedCornerShape(5)).clickable {
                                        depUpdateFlag.value=true
                                        userUpdateFlag.value=false
                                        profUserActiv.value.departament=newProfi.value
                                        professionState.value=false
                                        userUpdateFlag.value=true
                                    }, contentAlignment = Alignment.Center){
                                    Text("Отправить", style = TextStyle(
                                        fontSize = 14.sp,
                                        color = themes[0].mainColor
                                    )
                                    )
                                }
                            }
                        }

                }
            }
        }
    }
}

@Composable
fun myApplic(){
    Box(Modifier.fillMaxSize()){

    }
}


//=====================================================================================
//Карточка заявки главного экрана
//=====================================================================================
@Composable
fun boxApplic(event:ApplicData){

    Box(modifier = Modifier.padding(bottom = 5.dp).width(310.dp).height(150.dp).shadow(elevation = 4.dp, shape = RoundedCornerShape(8)).background(color = Color.White, shape = RoundedCornerShape(8))
        ){
        Column {
            Text(event.name, style = TextStyle(
                fontSize = 16.sp,
                color = Color.Black
            ), modifier = Modifier.padding(top=20.dp, start = 10.dp, end = 10.dp)
            )
            Text(if(event.answerPep!=-1) "${findUser(event.answerPep).surname} ${findUser(event.answerPep).name[0]}., ${depList[event.dep].name}" else " ${depList[event.dep].name}", style = TextStyle(
                fontSize = 12.sp,
                color= Color.Black
            ), modifier = Modifier.padding(top=5.dp, start = 10.dp,end=10.dp)
            )
            Text(event.description, style = TextStyle(
                fontSize = 12.sp,
                color= Color.Black
            ), maxLines = 3, overflow = TextOverflow.Ellipsis, modifier = Modifier.padding(top=5.dp, start = 10.dp,end=10.dp)
            )
            Box(modifier = Modifier.padding(bottom = 10.dp).fillMaxHeight(), contentAlignment = Alignment.BottomStart){
                Row(modifier = Modifier.padding(start = 10.dp, end = 10.dp).fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        Modifier.width(18.dp).height(18.dp).background(
                        color = if(event.status==1){ Color.Gray}
                        else {
                            if (event.status == 2) {
                                Color.Blue
                            } else {
                                Color.Green
                            }
                        }, shape = CircleShape
                    ))
                    Text(text = if(event.status==1){ "Отправлено"}
                    else {
                        if (event.status == 2) {
                            "Рассматривается"
                        } else {
                            "Готово"
                        }
                    }, style = TextStyle(
                        fontSize = 12.sp,
                        color = Color.Black
                    ), modifier = Modifier.padding(start = 5.dp)
                    )
                    /*Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd){
                        Icon(painter = painterResource("eye.svg"), contentDescription = "eye",
                            modifier = Modifier.width(18.dp))
                    }*/
                }
            }
        }

    }
}


//=====================================================================================
//Блок заявок
//=====================================================================================
@Composable
fun applicBlock(view:Boolean, dep:Int, redactor:Boolean){
    var activBlock = remember { mutableStateOf(view) }
    Column(Modifier.padding(start = 110.dp, end=110.dp, bottom = 15.dp).fillMaxWidth()) {
        Box(Modifier.fillMaxWidth()){
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(Modifier.width(18.dp).height(18.dp).clickable {
                        if(activBlock.value==true) activBlock.value=false
                        else activBlock.value=true
                    }, contentAlignment = Alignment.Center){
                        if(activBlock.value==true){
                            Icon(painter = painterResource("arrow_down_icon.svg")
                                , contentDescription = "activ", modifier = Modifier.fillMaxSize(0.5f))
                        }
                        else{
                            Icon(painter = painterResource("arrow_in_right.svg")
                                , contentDescription = "unactiv", modifier = Modifier.fillMaxSize(0.5f))
                        }
                    }
                    Text(text = if(dep == 0){
                        if(!view) "Выполненные"
                        else "Мои заявки"
                    }
                    else{
                        if(!view) "Выполненно"
                        else "Актуальные заявки"
                    })
                }
                Box(Modifier.fillMaxWidth().height(1.dp).background(color = themes[0].secondColor))
            }
        }
        var applicarraywithdep =  listOf<ApplicData>()
            if (activBlock.value == true) {
                var innerLazyColumnHeight = 0
                for (x in ApllicArray) {
                    if (((x.dep == dep)||dep==0&&x.author==findUser(userTek).id)&&x.view==view) {
                        innerLazyColumnHeight++
                        applicarraywithdep += x
                    }
                }
                if(dep==0&&view==true) innerLazyColumnHeight++;
                var flag = mutableStateOf(false)
                if (innerLazyColumnHeight<4) flag.value = true
                if (innerLazyColumnHeight % 4 > 0) innerLazyColumnHeight += 4
                innerLazyColumnHeight /= 4
                innerLazyColumnHeight *= 175
                if(!flag.value||1==1) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(4),
                        verticalArrangement = Arrangement.spacedBy(25.dp),
                        horizontalArrangement = Arrangement.spacedBy(32.dp),
                        modifier = Modifier.height(innerLazyColumnHeight.dp).padding(top = 10.dp)
                    ) {
                        items(applicarraywithdep) { item ->
                            if  (((item.dep == dep)||dep==0&&item.author==findUser(userTek).id)&&item.view==view) {
                                Box(Modifier.clickable{
                                    applicTek.value=item
                                    if (redactor==true){
                                        ApplicScreen.value=4
                                    }
                                    else{
                                        if(item.status==1){
                                            ApplicScreen.value=3
                                        }
                                        else{
                                            ApplicScreen.value=2
                                        }
                                    }
                                }){
                                boxApplic(item)}
                            }
                        }
                        item {
                            if(dep==0 && view==true){
                                Box(modifier = Modifier.width(310.dp).height(150.dp).shadow(elevation = 4.dp, shape = RoundedCornerShape(8)).background(color = Color.White, shape = RoundedCornerShape(8))
                                    .clickable{
                                              ApplicScreen.value=1
                                    }, contentAlignment = Alignment.Center){
                                    Column(horizontalAlignment = Alignment.CenterHorizontally){
                                        Icon(painter = painterResource("plus_icon.svg"), contentDescription = "add")
                                        Text("Создать новую заявку", style = TextStyle(
                                            fontSize = 16.sp,
                                            color = Color(86, 73, 73)
                                        ), modifier = Modifier.padding(top=10.dp)
                                        )
                                    }

                                }
                            }
                        }
                    }
                }
                else{
                    Column() {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(32.dp),
                            modifier = Modifier.padding(top = 10.dp)
                        ) {
                            for (x in ApllicArray) {
                                if (x.dep == dep && x.view == view) {

                                    Box(Modifier.clickable{
                                        applicTek.value=x
                                        if (redactor==true){
                                            ApplicScreen.value=4
                                        }
                                        else{
                                            if(x.status==1){
                                                ApplicScreen.value=3
                                            }
                                            else{
                                                ApplicScreen.value=2
                                            }
                                        }
                                    }){
                                        boxApplic(x)}
                                }
                            }
                        }
                        if(dep==0&&view==true){
                            Box(modifier = Modifier.padding(top = 10.dp, bottom = 5.dp).width(310.dp).height(150.dp).shadow(elevation = 4.dp, shape = RoundedCornerShape(8)).background(color = Color.White, shape = RoundedCornerShape(8))
                                , contentAlignment = Alignment.Center){
                                Column(horizontalAlignment = Alignment.CenterHorizontally){
                                    Icon(painter = painterResource("plus_icon.svg"), contentDescription = "add")
                                    Text("Создать новую заявку", style = TextStyle(
                                        fontSize = 16.sp,
                                        color = Color(86, 73, 73)
                                    ), modifier = Modifier.padding(top=10.dp)
                                    )

                                }

                            }
                        }
                    }
                }
            }

    }
}
