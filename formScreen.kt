//#####################################################################################################################
//#####################################################################################################################
//###############################                     Модуль форм                       ###############################
//#####################################################################################################################
//####    Автор: Кулишов Илья Вячеславович    #########################################################################
//####    Версия: v.0.0.0.1                   #########################################################################
//####    Дата: 23.03.2024                    #########################################################################
//#####################################################################################################################
//#####################################################################################################################


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.awt.FileDialog
import java.awt.Frame


//=====================================================================================
//Создание формы
//=====================================================================================
@Composable
fun createForm(){
    var qwestionName = remember { mutableStateOf("") }
    var category = remember { mutableStateOf(0) }
    var description = remember { mutableStateOf("") }
    var filePath = remember { mutableStateOf("") }
    var activCategVyb = remember { mutableStateOf(false) }
    var activCateg = remember { mutableStateOf(0) }


    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        LazyColumn {
            item {
                Box(Modifier.padding(bottom = 5.dp).width(800.dp).shadow(elevation = 4.dp, shape = RoundedCornerShape(4)).background(color = Color.White, shape = RoundedCornerShape(4))){
                    Column {
                        Box(Modifier.fillMaxWidth().height(45.dp).background(color = themes[0].corpColor), contentAlignment = Alignment.Center){
                            Text("Заполнение заявки", style = TextStyle(
                                color = Color.White,
                                fontSize = 18.sp
                            ))
                        }
                        Column(Modifier.padding(50.dp)) {
                            Box(){
                                Column {
                                    Text("1. Тема", style = TextStyle(
                                        fontSize = 16.sp,
                                        color =if(qwestionName.value!="")themes[0].corpColor else  themes[0].secondColor
                                    )
                                    )
                                    Box(Modifier.padding(start = 10.dp, end=10.dp,top=5.dp)){
                                        Column {
                                            Text("Опишите свою проблему в размере нескольких слов.", style = TextStyle(
                                                fontSize = 12.sp,
                                                color =if(qwestionName.value!="") themes[0].corpColor else Color(86, 73, 73)
                                            ))
                                            TextField(
                                                value = qwestionName.value,
                                                onValueChange = {qwestionName.value=it},
                                                label = { Text("Введите тему") },
                                                singleLine = true,
                                                modifier = Modifier.padding(top=5.dp).fillMaxSize().background(color = Color.White).border(
                                                    width = 2.dp, shape = RoundedCornerShape(15.dp),
                                                    color =if(qwestionName.value!="")themes[0].corpColor else Color.Gray
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

                            }

                            Box(Modifier.padding(top = 50.dp)){
                                Column {
                                    Text("2. Категория", style = TextStyle(
                                        fontSize = 16.sp,
                                        color = themes[0].secondColor
                                    )
                                    )
                                    Box(Modifier.padding(start = 10.dp, end=10.dp,top=5.dp)){
                                        Column {
                                            Text("Выберите категорию, которая лучше всего подходит под вашу проблему.", style = TextStyle(
                                                fontSize = 12.sp,
                                                color = Color(86, 73, 73)
                                            ))

                                        }
                                    }
                                    if(activCategVyb.value==false) {
                                        Box(Modifier.padding(top=5.dp, start = 10.dp, end = 10.dp).border(color= if(activCateg.value==0) Color.Gray else themes[0].corpColor, width = 2.dp, shape = RoundedCornerShape(30)).fillMaxWidth().height(60.dp)
                                            .background(color= Color.White, shape = RoundedCornerShape(30)),
                                            contentAlignment = Alignment.CenterStart) {
                                            Row(verticalAlignment = Alignment.CenterVertically) {
                                                Text(
                                                    if(activCateg.value==0)"Выберите отдел" else depList[activCateg.value].name, style = TextStyle(
                                                        fontSize = 16.sp,
                                                        color = if(activCateg.value==0) Color.Gray else themes[0].corpColor
                                                    ), modifier = Modifier.padding(start = 15.dp)
                                                )
                                                Box(Modifier.padding(end = 10.dp).fillMaxSize(), contentAlignment = Alignment.CenterEnd){
                                                    Icon(painterResource("vectorvverh.svg"), contentDescription = "open", modifier =
                                                    Modifier.clickable{
                                                        activCategVyb.value=true
                                                    })
                                                }
                                            }

                                        }
                                    }
                                    else{
                                        Box(Modifier.padding(top = 5.dp, start = 10.dp, end = 10.dp).border(color= Color.Gray, width = 2.dp, shape = RoundedCornerShape(8))
                                            .fillMaxWidth().background(color= Color.White, shape = RoundedCornerShape(8))) {
                                            Column {
                                                Box(
                                                    Modifier.fillMaxWidth().height(60.dp),
                                                    contentAlignment = Alignment.CenterStart
                                                ) {
                                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                                        Text(
                                                            "Выберите отдел" , style = TextStyle(
                                                                fontSize = 16.sp,
                                                                color = Color.Gray
                                                            ), modifier = Modifier.padding(start = 10.dp)
                                                        )
                                                        Box(
                                                            Modifier.padding(end = 10.dp).fillMaxSize(),
                                                            contentAlignment = Alignment.CenterEnd
                                                        ) {
                                                            Icon(painterResource("vectorvniz.svg"),
                                                                contentDescription = "close",
                                                                modifier =
                                                                Modifier.clickable {
                                                                    activCategVyb.value = false
                                                                })
                                                        }
                                                    }


                                                }
                                                LazyColumn(Modifier.padding(bottom = 20.dp).height((40*depList.size).dp)) {
                                                    items(depList){
                                                        item ->
                                                        Box(Modifier.fillMaxWidth().height(40.dp), contentAlignment = Alignment.CenterStart){
                                                            Row(Modifier.padding(start=10.dp),verticalAlignment = Alignment.CenterVertically) {
                                                                Box(Modifier.width(20.dp).height(20.dp).border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(30)).clickable{
                                                                    activCateg.value=item.id
                                                                }){
                                                                    if(activCateg.value==item.id){
                                                                        Box(Modifier.fillMaxSize().background(color = Color.Green,shape = RoundedCornerShape(30)))
                                                                    }
                                                                }
                                                                Text(text = item.name, style = TextStyle(
                                                                    fontSize = 16.sp,
                                                                    color = themes[0].secondColor

                                                                ), modifier = Modifier.padding(start=10.dp)
                                                                )
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }

                            }

                            Box(Modifier.padding(top = 50.dp)){
                                Column {
                                    Text("3. Описание", style = TextStyle(
                                        fontSize = 16.sp,
                                        color  =if(description.value!="")themes[0].corpColor else themes[0].secondColor
                                    )
                                    )
                                    Box(Modifier.padding(start = 10.dp, end=10.dp,top=5.dp)){
                                        Column {
                                            Text("Расскажите подробнее о причине вашего обращения.", style = TextStyle(
                                                fontSize = 12.sp,
                                                color =if(description.value!="")themes[0].corpColor else Color(86, 73, 73)
                                            ))
                                            TextField(
                                                value = description.value,
                                                onValueChange = {description.value=it},
                                                label = { Text("описание проблемы") },
                                                singleLine = false,
                                                modifier = Modifier.padding(top=5.dp).fillMaxSize().background(color = Color.White).border(
                                                    width = 2.dp, shape = RoundedCornerShape(15.dp),
                                                    color =if(description.value!="")themes[0].corpColor else  Color.Gray
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

                            }
                            Box(Modifier.padding(top = 50.dp)){
                                Column {
                                    Text("4. Файл", style = TextStyle(
                                        fontSize = 16.sp,
                                        color = if (filePath.value=="") themes[0].secondColor else Color(12, 69, 51)
                                    )
                                    )
                                    Box(Modifier.padding(start = 10.dp, end=10.dp,top=5.dp, bottom = 10.dp)){
                                        Column {
                                            Text("Загрузите дополнительные файлы, которые помогут нам лучше разобраться в ситуации.", style = TextStyle(
                                                fontSize = 12.sp,
                                                color = if (filePath.value=="") Color(86, 73, 73) else Color(12, 69, 51)
                                            ))
                                            Box(Modifier.padding(top=10.dp).fillMaxWidth().height(200.dp)
                                                .border(color =if (filePath.value=="") Color.Gray else Color(12, 69, 51), width = 2.dp, shape = RoundedCornerShape(5))
                                                .background(Color(245, 246, 247), shape = RoundedCornerShape(5))
                                               .clickable {
                                                   if(filePath.value=="") {
                                                       var fileDialog = FileDialog(Frame(), "Выберите файл")
                                                       fileDialog.isVisible = true
                                                       filePath.value =
                                                           if (fileDialog.file != null) fileDialog.file else ""
                                                   }
                                            },
                                                contentAlignment = Alignment.Center){
                                                if(filePath.value=="") {
                                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                                        Icon(
                                                            painter = painterResource("upload_icon.svg"),
                                                            contentDescription = "add"
                                                        )
                                                        Column(
                                                            horizontalAlignment = Alignment.CenterHorizontally,
                                                            modifier = Modifier.padding(start = 15.dp)
                                                        ) {
                                                            Text(
                                                                text = "Перетащите файл сюда", style = TextStyle(
                                                                    fontSize = 12.sp,
                                                                    color = Color(130, 117, 118)
                                                                )
                                                            )
                                                            Text(
                                                                text = "или выберите его вручную", style = TextStyle(
                                                                    fontSize = 12.sp,
                                                                    color = Color(130, 117, 118)
                                                                ), modifier = Modifier.padding(top = 5.dp)
                                                            )
                                                        }
                                                    }
                                                }
                                                else{
                                                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopStart){
                                                        Box(Modifier.padding(top=15.dp,start=15.dp).width(100.dp).height(100.dp).background(color = Color.White,
                                                            shape = RoundedCornerShape(10)
                                                        ), contentAlignment = Alignment.Center){
                                                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                                                Icon(painterResource("fileicon.svg"), contentDescription = null, modifier = 
                                                                Modifier.height(40.dp), contentColorFor(Color.Gray)
                                                                )
                                                                Box(Modifier.padding(end=5.dp, top=10.dp).width(70.dp).height(20.dp).background(Color(137, 9, 9), shape =
                                                                RoundedCornerShape(15)).clickable {
                                                                    filePath.value=""
                                                                }, contentAlignment = Alignment.Center){
                                                                    Text("Отменить", style = TextStyle(
                                                                        fontSize = 9.sp,
                                                                        color = themes[0].mainColor
                                                                    )
                                                                    )
                                                                }

                                                            }
                                                        }
                                                    }
                                                    /*
                                                    Column(horizontalAlignment = Alignment.CenterHorizontally){
                                                        Box(modifier = Modifier.border(width = 2.dp, color = Color.Gray, shape = RoundedCornerShape(30)).width(100.dp).height(100.dp))
                                                        Box(Modifier.padding(end=10.dp, top=15.dp).width(100.dp).height(30.dp).background(Color(137, 9, 9), shape =
                                                        RoundedCornerShape(5)).clickable {
                                                                                         filePath.value=""
                                                        }, contentAlignment = Alignment.Center){
                                                            Text("Отменить", style = TextStyle(
                                                                fontSize = 14.sp,
                                                                color = themes[0].mainColor
                                                            )
                                                            )
                                                        }
                                                    }*/
                                                }
                                            }
                                        }
                                    }

                                }

                            }
                            Box(modifier = Modifier.padding(top=40.dp).fillMaxWidth(), contentAlignment = Alignment.CenterEnd){
                                Row{
                                    Box(Modifier.padding(end=10.dp).width(136.dp).height(40.dp).background(Color(137, 9, 9), shape =
                                    RoundedCornerShape(5)
                                    ).clickable{
                                               ApplicScreen.value=0
                                    }, contentAlignment = Alignment.Center){
                                        Text("Отменить", style = TextStyle(
                                            fontSize = 14.sp,
                                            color = themes[0].mainColor
                                        )
                                        )
                                    }
                                    Box(Modifier.width(136.dp).height(40.dp).background(Color(12, 69, 51), shape =
                                    RoundedCornerShape(5)
                                    ).clickable {
                                                if(qwestionName.value!=""&&activCateg.value!=0){
                                                    ApllicArray+=ApplicData(ApllicArray.size, qwestionName.value,findUser(userTek).id, activCateg.value,
                                                        -1,1,description.value,"",filePath.value,"",true )

                                                }
                                        ApplicScreen.value=0
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

    }
}


//=====================================================================================
//Блок категорий в экране создания форм
//=====================================================================================
@Composable
fun categoryBlock(category: Category, redact:Boolean){
    var categoryStatus = remember { mutableStateOf(category.state) }
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.height(20.dp)) {
        Box(Modifier.width(15.dp).height(15.dp).border(color= Color(74, 74, 74), width = 1.dp, shape = CircleShape)
            .clickable {
                if(redact==true) {
                    if (category.state == true) {
                        category.state = false
                        categoryStatus.value = false
                    } else {
                        for (x in categoryList) {
                            x.state = false
                        }
                        category.state = true
                        categoryStatus.value = true
                    }
                }
            },
            contentAlignment = Alignment.Center){
            Box(Modifier.width(10.dp).height(10.dp).background(color = if(categoryStatus.value==true) Color(74, 74, 74)
            else Color.White, shape = CircleShape))
        }
        Text(text = category.name, style = TextStyle(
            fontSize = 14.sp,
            color = Color.Black
        )
        )
    }
}



//=====================================================================================
//Редактирование формы
//=====================================================================================
@Composable
fun redactForm(applic:ApplicData){
    var description = remember { mutableStateOf(applic.description) }
    var filePath = remember { mutableStateOf(applic.file1) }

    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        LazyColumn {
            item {
                Box(Modifier.padding(bottom = 5.dp).width(800.dp).shadow(elevation = 4.dp, shape = RoundedCornerShape(4)).background(color = Color.White, shape = RoundedCornerShape(4))){
                    Column {
                        Box(Modifier.fillMaxWidth().height(45.dp).background(color = themes[0].corpColor), contentAlignment = Alignment.Center){
                            Text("Редактирование заявки", style = TextStyle(
                                color = Color.White,
                                fontSize = 18.sp
                            ))
                        }
                        Column(Modifier.padding(50.dp)) {
                            Box(){
                                Column {
                                    Text("1. Тема", style = TextStyle(
                                        fontSize = 16.sp,
                                        color = Color.Gray
                                    )
                                    )
                                    Box(Modifier.padding(start = 10.dp, end=10.dp,top=5.dp)){
                                        Column {

                                            TextField(
                                                value = applic.name,
                                                onValueChange = {},
                                                readOnly = true,
                                                //label = { Text("Введите тему") },
                                                //colors = Color.Gray,
                                                singleLine = true,
                                                modifier = Modifier.padding(top=5.dp).fillMaxSize().background(color = Color.White).border(
                                                    width = 2.dp, shape = RoundedCornerShape(15.dp),
                                                    color = Color.Gray
                                                ),
                                                shape = RoundedCornerShape(15.dp),
                                                colors = TextFieldDefaults.textFieldColors(
                                                    backgroundColor = Color.White,
                                                    focusedLabelColor = Color(17, 69, 51),
                                                    focusedIndicatorColor = Color.Transparent,
                                                    unfocusedIndicatorColor = Color.Transparent,
                                                    errorIndicatorColor = Color.Transparent,
                                                    textColor = Color.Gray
                                                )
                                            )

                                        }
                                    }
                                }

                            }

                            Box(Modifier.padding(top = 50.dp)){
                                Column {
                                    Text("2. Категория", style = TextStyle(
                                        fontSize = 16.sp,
                                        color = Color.Gray
                                    )
                                    )
                                    //if(activCategVyb.value==false) {
                                        Box(Modifier.padding(top=5.dp, start = 10.dp, end = 10.dp).border(color= Color.Gray, width = 2.dp, shape = RoundedCornerShape(30)).fillMaxWidth().height(60.dp)
                                            .background(color= Color.White, shape = RoundedCornerShape(30)),
                                            contentAlignment = Alignment.CenterStart) {
                                            Row(verticalAlignment = Alignment.CenterVertically) {
                                                Text(
                                                    depList[applic.dep].name, style = TextStyle(
                                                        fontSize = 16.sp,
                                                        color = Color.Gray
                                                    ), modifier = Modifier.padding(start = 15.dp)
                                                )

                                            }

                                        }
                                   // }

                                }

                            }

                            Box(Modifier.padding(top = 50.dp)){
                                Column {
                                    Text("3. Описание", style = TextStyle(
                                        fontSize = 16.sp,
                                        color  =if(description.value!="")themes[0].corpColor else themes[0].secondColor
                                    )
                                    )
                                    Box(Modifier.padding(start = 10.dp, end=10.dp,top=5.dp)){
                                        Column {
                                            Text("Можете отредактировать описание", style = TextStyle(
                                                fontSize = 12.sp,
                                                color =if(description.value!="")themes[0].corpColor else Color(86, 73, 73)
                                            ))
                                            TextField(
                                                value = description.value,
                                                onValueChange = {description.value=it},
                                                //label = { Text("описание проблемы") },
                                                singleLine = false,
                                                modifier = Modifier.padding(top=5.dp).fillMaxSize().background(color = Color.White).border(
                                                    width = 2.dp, shape = RoundedCornerShape(15.dp),
                                                    color =if(description.value!="")themes[0].corpColor else  Color.Gray
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

                            }
                            Box(Modifier.padding(top = 50.dp)){
                                Column {
                                    Text("4. Файл", style = TextStyle(
                                        fontSize = 16.sp,
                                        color = if (filePath.value=="") themes[0].secondColor else Color(12, 69, 51)
                                    )
                                    )
                                    Box(Modifier.padding(start = 10.dp, end=10.dp,top=5.dp, bottom = 10.dp)){
                                        Column {
                                            Text("Загрузите дополнительные файлы, которые помогут лучше разобраться в ситуации.", style = TextStyle(
                                                fontSize = 12.sp,
                                                color = if (filePath.value=="") Color(86, 73, 73) else Color(12, 69, 51)
                                            ))
                                            Box(Modifier.padding(top=10.dp).fillMaxWidth().height(200.dp)
                                                .border(color =if (filePath.value=="") Color.Gray else Color(12, 69, 51), width = 2.dp, shape = RoundedCornerShape(5))
                                                .background(Color(245, 246, 247), shape = RoundedCornerShape(15))
                                                .clickable {
                                                    if(filePath.value=="") {
                                                        var fileDialog = FileDialog(Frame(), "Выберите файл")
                                                        fileDialog.isVisible = true
                                                        filePath.value =
                                                            if (fileDialog.file != null) fileDialog.file else ""
                                                    }
                                                },
                                                contentAlignment = Alignment.Center){
                                                if(filePath.value=="") {
                                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                                        Icon(
                                                            painter = painterResource("upload_icon.svg"),
                                                            contentDescription = "add"
                                                        )
                                                        Column(
                                                            horizontalAlignment = Alignment.CenterHorizontally,
                                                            modifier = Modifier.padding(start = 15.dp)
                                                        ) {
                                                            Text(
                                                                text = "Перетащите файл сюда", style = TextStyle(
                                                                    fontSize = 12.sp,
                                                                    color = Color(130, 117, 118)
                                                                )
                                                            )
                                                            Text(
                                                                text = "или выберите его вручную", style = TextStyle(
                                                                    fontSize = 12.sp,
                                                                    color = Color(130, 117, 118)
                                                                ), modifier = Modifier.padding(top = 5.dp)
                                                            )
                                                        }
                                                    }
                                                }
                                                else{
                                                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopStart){
                                                        Box(Modifier.padding(top=15.dp,start=15.dp).width(100.dp).height(100.dp).background(color = Color.White,
                                                            shape = RoundedCornerShape(10)
                                                        ), contentAlignment = Alignment.Center){
                                                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                                                Icon(painterResource("fileicon.svg"), contentDescription = null, modifier =
                                                                Modifier.height(40.dp), contentColorFor(Color.Gray)
                                                                )
                                                                Box(Modifier.padding(end=5.dp, top=10.dp).width(70.dp).height(20.dp).background(Color(137, 9, 9), shape =
                                                                RoundedCornerShape(15)).clickable {
                                                                    filePath.value=""
                                                                }, contentAlignment = Alignment.Center){
                                                                    Text("Отменить", style = TextStyle(
                                                                        fontSize = 9.sp,
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

                                }

                            }
                            Box(modifier = Modifier.padding(top=40.dp).fillMaxWidth(), contentAlignment = Alignment.CenterEnd){
                                Row{
                                    Box(Modifier.padding(end=10.dp).width(136.dp).height(40.dp).background(Color(137, 9, 9), shape =
                                    RoundedCornerShape(5)
                                    ).clickable{
                                        ApplicScreen.value=0
                                    }, contentAlignment = Alignment.Center){
                                        Text("Отменить", style = TextStyle(
                                            fontSize = 14.sp,
                                            color = themes[0].mainColor
                                        )
                                        )
                                    }
                                    Box(Modifier.width(136.dp).height(40.dp).background(Color(12, 69, 51), shape =
                                    RoundedCornerShape(5)
                                    ).clickable {
                                                //ApllicArray[findApplic(applic.id)].description=description.value
                                        //ApllicArray[findApplic(applic.id)].file1=filePath.value
                                        applic.description=description.value
                                        applic.file1=filePath.value
                                        ApplicScreen.value=0
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

    }
}

//=====================================================================================
//Ответ формы
//=====================================================================================
@Composable
fun answerForm(applic:ApplicData){
    val answerStartStatus = applic.status
    var answerText = remember { mutableStateOf(applic.answerText) }
    var filePath = remember { mutableStateOf("") }
    var status = remember { mutableStateOf(applic.status) }

    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        LazyColumn {
            item {
                Box(Modifier.padding(bottom = 5.dp).width(800.dp).shadow(elevation = 4.dp, shape = RoundedCornerShape(4)).background(color = Color.White, shape = RoundedCornerShape(4))){
                    Column {
                        Box(Modifier.fillMaxWidth().height(45.dp).background(color = themes[0].corpColor), contentAlignment = Alignment.Center){
                            Text("Заявка #${applic.id}", style = TextStyle(
                                color = Color.White,
                                fontSize = 18.sp
                            ))
                        }
                        Column(Modifier.padding(50.dp)) {
                            Box(){
                                Column {
                                    Text("1. Тема", style = TextStyle(
                                        fontSize = 16.sp,
                                        color = Color.Gray
                                    )
                                    )
                                    Box(Modifier.padding(start = 10.dp, end=10.dp,top=5.dp)){
                                        Column {

                                            TextField(
                                                value = applic.name,
                                                onValueChange = {},
                                                readOnly = true,
                                                //label = { Text("Введите тему") },
                                                //colors = Color.Gray,
                                                singleLine = true,
                                                modifier = Modifier.padding(top=5.dp).fillMaxSize().background(color = Color.White).border(
                                                    width = 2.dp, shape = RoundedCornerShape(15.dp),
                                                    color = Color.Gray
                                                ),
                                                shape = RoundedCornerShape(15.dp),
                                                colors = TextFieldDefaults.textFieldColors(
                                                    backgroundColor = Color.White,
                                                    focusedLabelColor = Color(17, 69, 51),
                                                    focusedIndicatorColor = Color.Transparent,
                                                    unfocusedIndicatorColor = Color.Transparent,
                                                    errorIndicatorColor = Color.Transparent,
                                                    textColor = Color.Gray
                                                )
                                            )

                                        }
                                    }
                                }

                            }
                            Box(modifier = Modifier.padding(top=50.dp)){
                                Column {
                                    Text("2. Отправитель", style = TextStyle(
                                        fontSize = 16.sp,
                                        color = Color.Gray
                                    )
                                    )
                                    Box(Modifier.padding(start = 10.dp, end=10.dp,top=5.dp)){
                                        Column {

                                            TextField(
                                                value = "${findUser(applic.author).surname} ${findUser(applic.author).name[0]}.",
                                                onValueChange = {},
                                                readOnly = true,
                                                //label = { Text("Введите тему") },
                                                //colors = Color.Gray,
                                                singleLine = true,
                                                modifier = Modifier.padding(top=5.dp).fillMaxSize().background(color = Color.White).border(
                                                    width = 2.dp, shape = RoundedCornerShape(15.dp),
                                                    color = Color.Gray
                                                ),
                                                shape = RoundedCornerShape(15.dp),
                                                colors = TextFieldDefaults.textFieldColors(
                                                    backgroundColor = Color.White,
                                                    focusedLabelColor = Color(17, 69, 51),
                                                    focusedIndicatorColor = Color.Transparent,
                                                    unfocusedIndicatorColor = Color.Transparent,
                                                    errorIndicatorColor = Color.Transparent,
                                                    textColor = Color.Gray
                                                )
                                            )

                                        }
                                    }
                                }

                            }

                            Box(Modifier.padding(top = 50.dp)){
                                Column {
                                    Text("3. Категория", style = TextStyle(
                                        fontSize = 16.sp,
                                        color = Color.Gray
                                    )
                                    )
                                    //if(activCategVyb.value==false) {
                                    Box(Modifier.padding(top=5.dp, start = 10.dp, end = 10.dp).border(color= Color.Gray, width = 2.dp, shape = RoundedCornerShape(30)).fillMaxWidth().height(60.dp)
                                        .background(color= Color.White, shape = RoundedCornerShape(30)),
                                        contentAlignment = Alignment.CenterStart) {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Text(
                                                depList[applic.dep].name, style = TextStyle(
                                                    fontSize = 16.sp,
                                                    color = Color.Gray
                                                ), modifier = Modifier.padding(start = 15.dp)
                                            )

                                        }

                                    }
                                    // }

                                }

                            }

                            Box(Modifier.padding(top = 50.dp)){
                                Column {
                                    Text("4. Описание", style = TextStyle(
                                        fontSize = 16.sp,
                                        color  =Color.Gray
                                    )
                                    )
                                    Box(Modifier.padding(start = 10.dp, end=10.dp,top=5.dp)){
                                        Column {
                                            TextField(
                                                value = applic.description,
                                                onValueChange = {},
                                                readOnly = true,
                                                //label = { Text("описание проблемы") },
                                                singleLine = false,
                                                modifier = Modifier.padding(top=5.dp).fillMaxSize().background(color = Color.White).border(
                                                    width = 2.dp, shape = RoundedCornerShape(15.dp),
                                                    color = Color.Gray
                                                ),
                                                shape = RoundedCornerShape(15.dp),
                                                colors = TextFieldDefaults.textFieldColors(
                                                    backgroundColor = Color.White,
                                                    focusedLabelColor = Color(17, 69, 51),
                                                    focusedIndicatorColor = Color.Transparent,
                                                    unfocusedIndicatorColor = Color.Transparent,
                                                    errorIndicatorColor = Color.Transparent,
                                                    textColor = Color.Gray
                                                )
                                            )
                                        }
                                    }
                                }

                            }
                            Box(Modifier.padding(top = 50.dp)){
                                Column {
                                    Text("5. Дополнительные файлы", style = TextStyle(
                                        fontSize = 16.sp,
                                        color = Color.Gray
                                        //color = if (filePath.value=="") themes[0].secondColor else Color(12, 69, 51)
                                    )
                                    )
                                    Box(Modifier.padding(start = 10.dp, end=10.dp,top=5.dp, bottom = 10.dp)){
                                        Column {
                                            Box(Modifier.padding(top=10.dp).fillMaxWidth().height(200.dp)
                                                .border(color = Color.Gray , width = 2.dp, shape = RoundedCornerShape(5))
                                                .background(Color(245, 246, 247), shape = RoundedCornerShape(5))
                                                ,
                                                contentAlignment = Alignment.Center){
                                                if(applic.file1=="") {

                                                }
                                                else{
                                                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopStart){
                                                        Box(Modifier.padding(top=15.dp,start=15.dp).width(100.dp).height(100.dp).background(color = Color.White,
                                                            shape = RoundedCornerShape(10)
                                                        ), contentAlignment = Alignment.Center){
                                                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                                                Icon(painterResource("fileicon.svg"), contentDescription = null, modifier =
                                                                Modifier.height(40.dp), contentColorFor(Color.Gray)
                                                                )
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }

                                }

                            }
                            if(answerStartStatus!=3) {
                                Box(Modifier.padding(top = 50.dp)) {
                                    Column {
                                        Text(
                                            "6. Статус", style = TextStyle(
                                                fontSize = 16.sp,
                                                color = Color.Gray
                                            )
                                        )
                                        //if(activCategVyb.value==false) {
                                        Box(
                                            Modifier.padding(top = 5.dp, start = 10.dp, end = 10.dp),
                                            contentAlignment = Alignment.CenterStart
                                        ) {
                                            //-------------------------------------------------------------------------------------------------------------------------------------------
                                            Box(
                                                Modifier.padding(top = 5.dp, start = 10.dp, end = 10.dp).border(
                                                    color = Color.Gray,
                                                    width = 2.dp,
                                                    shape = RoundedCornerShape(8)
                                                )
                                                    .fillMaxWidth()
                                                    .background(color = Color.White, shape = RoundedCornerShape(8))
                                            ) {
                                                Column {
                                                    Box(
                                                        Modifier.fillMaxWidth().height(40.dp),
                                                        contentAlignment = Alignment.CenterStart
                                                    ) {
                                                        Row(
                                                            Modifier.padding(start = 10.dp),
                                                            verticalAlignment = Alignment.CenterVertically
                                                        ) {
                                                            Box(
                                                                Modifier.width(20.dp).height(20.dp).border(
                                                                    width = 1.dp,
                                                                    color = Color.Gray,
                                                                    shape = RoundedCornerShape(30)
                                                                ).clickable {
                                                                    status.value = 1
                                                                }) {
                                                                if (status.value == 1) {
                                                                    Box(
                                                                        Modifier.fillMaxSize()
                                                                            .background(color = Color.Green, shape =
                                                                            RoundedCornerShape(30)
                                                                            )
                                                                    )
                                                                }
                                                            }
                                                            Text(
                                                                text = "Отправлено", style = TextStyle(
                                                                    fontSize = 16.sp,
                                                                    color = themes[0].secondColor

                                                                ), modifier = Modifier.padding(start = 10.dp)
                                                            )
                                                        }
                                                    }
                                                    Box(
                                                        Modifier.fillMaxWidth().height(40.dp),
                                                        contentAlignment = Alignment.CenterStart
                                                    ) {
                                                        Row(
                                                            Modifier.padding(start = 10.dp),
                                                            verticalAlignment = Alignment.CenterVertically
                                                        ) {
                                                            Box(
                                                                Modifier.width(20.dp).height(20.dp).border(
                                                                    width = 1.dp,
                                                                    color = Color.Gray,
                                                                    shape = RoundedCornerShape(30)
                                                                ).clickable {
                                                                    status.value = 2
                                                                }) {
                                                                if (status.value == 2) {
                                                                    Box(
                                                                        Modifier.fillMaxSize()
                                                                            .background(color = Color.Green,
                                                                                shape = RoundedCornerShape(30)
                                                                            )
                                                                    )
                                                                }
                                                            }
                                                            Text(
                                                                text = "В работе", style = TextStyle(
                                                                    fontSize = 16.sp,
                                                                    color = themes[0].secondColor

                                                                ), modifier = Modifier.padding(start = 10.dp)
                                                            )
                                                        }
                                                    }
                                                    Box(
                                                        Modifier.fillMaxWidth().height(40.dp),
                                                        contentAlignment = Alignment.CenterStart
                                                    ) {
                                                        Row(
                                                            Modifier.padding(start = 10.dp),
                                                            verticalAlignment = Alignment.CenterVertically
                                                        ) {
                                                            Box(
                                                                Modifier.width(20.dp).height(20.dp).border(
                                                                    width = 1.dp,
                                                                    color = Color.Gray,
                                                                    shape = RoundedCornerShape(30)
                                                                ).clickable {
                                                                    status.value = 3
                                                                }) {
                                                                if (status.value == 3) {
                                                                    Box(
                                                                        Modifier.fillMaxSize()
                                                                            .background(color = Color.Green, shape =
                                                                            RoundedCornerShape(30)
                                                                            )
                                                                    )
                                                                }
                                                            }
                                                            Text(
                                                                text = "Отвечено", style = TextStyle(
                                                                    fontSize = 16.sp,
                                                                    color = themes[0].secondColor

                                                                ), modifier = Modifier.padding(start = 10.dp)
                                                            )
                                                        }
                                                    }
                                                }
                                            }
                                            //-------------------------------------------------------------------------------------------------------------------------------------------


                                        }
                                        // }

                                    }

                                }
                            }else{
                                Box(Modifier.padding(top = 50.dp)){
                                    Column {
                                        Text("6. Статус", style = TextStyle(
                                            fontSize = 16.sp,
                                            color = Color.Gray
                                        )
                                        )
                                        //if(activCategVyb.value==false) {
                                        Box(Modifier.padding(top=5.dp, start = 10.dp, end = 10.dp).border(color= Color.Gray, width = 2.dp, shape = RoundedCornerShape(30)).fillMaxWidth().height(60.dp)
                                            .background(color= Color.White, shape = RoundedCornerShape(30)),
                                            contentAlignment = Alignment.CenterStart) {
                                            Row(verticalAlignment = Alignment.CenterVertically) {
                                                Text(
                                                    "Отвечено", style = TextStyle(
                                                        fontSize = 16.sp,
                                                        color = Color.Gray
                                                    ), modifier = Modifier.padding(start = 15.dp)
                                                )

                                            }

                                        }
                                        // }

                                    }

                                }
                            }
                            if(answerStartStatus!=3) {
                                Box(Modifier.padding(top = 50.dp)) {
                                    Column {
                                        Text(
                                            "7. Ответ", style = TextStyle(
                                                fontSize = 16.sp,
                                                color = if (answerText.value != "") themes[0].corpColor else themes[0].secondColor
                                            )
                                        )
                                        Box(Modifier.padding(start = 10.dp, end = 10.dp, top = 5.dp)) {
                                            Column {
                                                Text(
                                                    "Заполните ответ.", style = TextStyle(
                                                        fontSize = 12.sp,
                                                        color = if (answerText.value != "") themes[0].corpColor else Color(
                                                            86,
                                                            73,
                                                            73
                                                        )
                                                    )
                                                )
                                                TextField(
                                                    value = answerText.value,
                                                    onValueChange = { answerText.value = it },
                                                    label = { Text("описание ответа") },
                                                    singleLine = false,
                                                    modifier = Modifier.padding(top = 5.dp).fillMaxSize()
                                                        .background(color = Color.White).border(
                                                        width = 2.dp, shape = RoundedCornerShape(15.dp),
                                                        color = if (answerText.value != "") themes[0].corpColor else Color.Gray
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

                                }
                            }else{
                                Box(Modifier.padding(top = 50.dp)) {
                                    Column {
                                        Text(
                                            "7. Ответ", style = TextStyle(
                                                fontSize = 16.sp,
                                                color = Color.Gray
                                            )
                                        )
                                        Box(Modifier.padding(start = 10.dp, end = 10.dp, top = 5.dp)) {
                                            Column {

                                                TextField(
                                                    value = answerText.value,
                                                    onValueChange = { },
                                                    //label = {  },
                                                    readOnly = true,
                                                    singleLine = false,
                                                    modifier = Modifier.padding(top = 5.dp).fillMaxSize()
                                                        .background(color = Color.White).border(
                                                            width = 2.dp, shape = RoundedCornerShape(15.dp),
                                                            color =  Color.Gray
                                                        ),
                                                    shape = RoundedCornerShape(15.dp),
                                                    colors = TextFieldDefaults.textFieldColors(
                                                        backgroundColor = Color.White,
                                                        focusedLabelColor = Color(17, 69, 51),
                                                        focusedIndicatorColor = Color.Transparent,
                                                        unfocusedIndicatorColor = Color.Transparent,
                                                        errorIndicatorColor = Color.Transparent,
                                                        textColor = Color.Gray
                                                    )
                                                )
                                            }
                                        }
                                    }

                                }
                            }
                            if(answerStartStatus!=3) {
                                Box(Modifier.padding(top = 50.dp)) {
                                    Column {
                                        Text(
                                            "8. Файл ответа", style = TextStyle(
                                                fontSize = 16.sp,
                                                color = if (filePath.value == "") themes[0].secondColor else Color(
                                                    12,
                                                    69,
                                                    51
                                                )
                                            )
                                        )
                                        Box(Modifier.padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 10.dp)) {
                                            Column {
                                                Text(
                                                    "Загрузите дополнительные файлы к ответу.", style = TextStyle(
                                                        fontSize = 12.sp,
                                                        color = if (filePath.value == "") Color(86, 73, 73) else Color(
                                                            12,
                                                            69,
                                                            51
                                                        )
                                                    )
                                                )
                                                Box(
                                                    Modifier.padding(top = 10.dp).fillMaxWidth().height(200.dp)
                                                        .border(
                                                            color = if (filePath.value == "") Color.Gray else Color(
                                                                12,
                                                                69,
                                                                51
                                                            ), width = 2.dp, shape = RoundedCornerShape(5)
                                                        )
                                                        .background(
                                                            Color(245, 246, 247),
                                                            shape = RoundedCornerShape(15)
                                                        )
                                                        .clickable {
                                                            if(filePath.value=="") {
                                                                var fileDialog = FileDialog(Frame(), "Выберите файл")
                                                                fileDialog.isVisible = true
                                                                filePath.value =
                                                                    if (fileDialog.file != null) fileDialog.file else ""
                                                            }
                                                        },
                                                    contentAlignment = Alignment.Center
                                                ) {
                                                    if (filePath.value == "") {
                                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                                            Icon(
                                                                painter = painterResource("upload_icon.svg"),
                                                                contentDescription = "add"
                                                            )
                                                            Column(
                                                                horizontalAlignment = Alignment.CenterHorizontally,
                                                                modifier = Modifier.padding(start = 15.dp)
                                                            ) {
                                                                Text(
                                                                    text = "Перетащите файл сюда", style = TextStyle(
                                                                        fontSize = 12.sp,
                                                                        color = Color(130, 117, 118)
                                                                    )
                                                                )
                                                                Text(
                                                                    text = "или выберите его вручную",
                                                                    style = TextStyle(
                                                                        fontSize = 12.sp,
                                                                        color = Color(130, 117, 118)
                                                                    ),
                                                                    modifier = Modifier.padding(top = 5.dp)
                                                                )
                                                            }
                                                        }
                                                    } else {
                                                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopStart){
                                                            Box(Modifier.padding(top=15.dp,start=15.dp).width(100.dp).height(100.dp).background(color = Color.White,
                                                                shape = RoundedCornerShape(10)
                                                            ), contentAlignment = Alignment.Center){
                                                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                                                    Icon(painterResource("fileicon.svg"), contentDescription = null, modifier =
                                                                    Modifier.height(40.dp), contentColorFor(Color.Gray)
                                                                    )
                                                                    Box(Modifier.padding(end=5.dp, top=10.dp).width(70.dp).height(20.dp).background(Color(137, 9, 9), shape =
                                                                    RoundedCornerShape(15)).clickable {
                                                                        filePath.value=""
                                                                    }, contentAlignment = Alignment.Center){
                                                                        Text("Отменить", style = TextStyle(
                                                                            fontSize = 9.sp,
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

                                    }

                                }
                            }else{
                                Box(Modifier.padding(top = 50.dp)){
                                    Column {
                                        Text("8. Файл ответа", style = TextStyle(
                                            fontSize = 16.sp,
                                            color = Color.Gray
                                            //color = if (filePath.value=="") themes[0].secondColor else Color(12, 69, 51)
                                        )
                                        )
                                        Box(Modifier.padding(start = 10.dp, end=10.dp,top=5.dp, bottom = 10.dp)){
                                            Column {
                                                Box(Modifier.padding(top=10.dp).fillMaxWidth().height(200.dp)
                                                    .border(color = Color.Gray , width = 2.dp, shape = RoundedCornerShape(5))
                                                    .background(Color(245, 246, 247), shape = RoundedCornerShape(15))
                                                    ,
                                                    contentAlignment = Alignment.Center){
                                                   /* if(filePath.value=="") {

                                                    }
                                                    else{
                                                        Column(horizontalAlignment = Alignment.CenterHorizontally){
                                                            Box(modifier = Modifier.border(width = 2.dp, color = Color.Gray, shape = RoundedCornerShape(30)).width(100.dp).height(100.dp))

                                                        }
                                                    }*/
                                                }
                                            }
                                        }

                                    }

                                }
                            }
                            Box(modifier = Modifier.padding(top=40.dp).fillMaxWidth(), contentAlignment = Alignment.CenterEnd){
                                Row{
                                    Box(Modifier.padding(end=10.dp).width(136.dp).height(40.dp).background(Color(137, 9, 9), shape =
                                    RoundedCornerShape(5)
                                    ).clickable{
                                        ApplicScreen.value=0
                                    }, contentAlignment = Alignment.Center){
                                        Text("Отменить", style = TextStyle(
                                            fontSize = 14.sp,
                                            color = themes[0].mainColor
                                        )
                                        )
                                    }
                                    Box(Modifier.width(136.dp).height(40.dp).background(Color(12, 69, 51), shape =
                                    RoundedCornerShape(5)
                                    ).clickable{
                                               //ApllicArray[findApplic(applic.id)].answerPep=findUser(userTek).id
                                        //ApllicArray[findApplic(applic.id)].answerText=answerText.value
                                        //ApllicArray[findApplic(applic.id)].file2=filePath.value
                                        //ApllicArray[findApplic(applic.id)].status=status.value
                                        applic.answerPep=findUser(userTek).id
                                        applic.answerText=answerText.value
                                        applic.file2=filePath.value
                                        applic.status=status.value
                                        if(applic.status==3){
                                            applic.view=false
                                        }
                                        else{
                                            applic.view=true
                                        }
                                        ApplicScreen.value=0
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

    }
}

//=====================================================================================
//Ответ формы
//=====================================================================================
@Composable
fun viewForm(applic:ApplicData){

    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        LazyColumn {
            item {
                Box(Modifier.padding(bottom = 5.dp).width(800.dp).shadow(elevation = 4.dp, shape = RoundedCornerShape(4)).background(color = Color.White, shape = RoundedCornerShape(4))){
                    Column {
                        Box(Modifier.fillMaxWidth().height(45.dp).background(color = themes[0].corpColor), contentAlignment = Alignment.Center){
                            Text("Заявка #${applic.id}", style = TextStyle(
                                color = Color.White,
                                fontSize = 18.sp
                            ))
                        }
                        Column(Modifier.padding(50.dp)) {
                            Box(){
                                Column {
                                    Text("1. Тема", style = TextStyle(
                                        fontSize = 16.sp,
                                        color = Color.Gray
                                    )
                                    )
                                    Box(Modifier.padding(start = 10.dp, end=10.dp,top=5.dp)){
                                        Column {

                                            TextField(
                                                value = applic.name,
                                                onValueChange = {},
                                                readOnly = true,
                                                //label = { Text("Введите тему") },
                                                //colors = Color.Gray,
                                                singleLine = true,
                                                modifier = Modifier.padding(top=5.dp).fillMaxSize().background(color = Color.White).border(
                                                    width = 2.dp, shape = RoundedCornerShape(15.dp),
                                                    color = Color.Gray
                                                ),
                                                shape = RoundedCornerShape(15.dp),
                                                colors = TextFieldDefaults.textFieldColors(
                                                    backgroundColor = Color.White,
                                                    focusedLabelColor = Color(17, 69, 51),
                                                    focusedIndicatorColor = Color.Transparent,
                                                    unfocusedIndicatorColor = Color.Transparent,
                                                    errorIndicatorColor = Color.Transparent,
                                                    textColor = Color.Gray
                                                )
                                            )

                                        }
                                    }
                                }

                            }
                            Box(modifier = Modifier.padding(top=50.dp)){
                                Column {
                                    Text("2. Отправитель", style = TextStyle(
                                        fontSize = 16.sp,
                                        color = Color.Gray
                                    )
                                    )
                                    Box(Modifier.padding(start = 10.dp, end=10.dp,top=5.dp)){
                                        Column {

                                            TextField(
                                                value = "${findUser(applic.author).surname} ${findUser(applic.author).name[0]}.",
                                                onValueChange = {},
                                                readOnly = true,
                                                //label = { Text("Введите тему") },
                                                //colors = Color.Gray,
                                                singleLine = true,
                                                modifier = Modifier.padding(top=5.dp).fillMaxSize().background(color = Color.White).border(
                                                    width = 2.dp, shape = RoundedCornerShape(15.dp),
                                                    color = Color.Gray
                                                ),
                                                shape = RoundedCornerShape(15.dp),
                                                colors = TextFieldDefaults.textFieldColors(
                                                    backgroundColor = Color.White,
                                                    focusedLabelColor = Color(17, 69, 51),
                                                    focusedIndicatorColor = Color.Transparent,
                                                    unfocusedIndicatorColor = Color.Transparent,
                                                    errorIndicatorColor = Color.Transparent,
                                                    textColor = Color.Gray
                                                )
                                            )

                                        }
                                    }
                                }

                            }


                            Box(modifier = Modifier.padding(top=50.dp)){
                                Column {
                                    Text("3. Обработчик", style = TextStyle(
                                        fontSize = 16.sp,
                                        color = Color.Gray
                                    )
                                    )
                                    Box(Modifier.padding(start = 10.dp, end=10.dp,top=5.dp)){
                                        Column {

                                            TextField(
                                                value = if(applic.answerPep==-1)"" else  "${findUser(applic.answerPep).surname} ${findUser(applic.answerPep).name[0]}.",
                                                onValueChange = {},
                                                readOnly = true,
                                                //label = { Text("Введите тему") },
                                                //colors = Color.Gray,
                                                singleLine = true,
                                                modifier = Modifier.padding(top=5.dp).fillMaxSize().background(color = Color.White).border(
                                                    width = 2.dp, shape = RoundedCornerShape(15.dp),
                                                    color = Color.Gray
                                                ),
                                                shape = RoundedCornerShape(15.dp),
                                                colors = TextFieldDefaults.textFieldColors(
                                                    backgroundColor = Color.White,
                                                    focusedLabelColor = Color(17, 69, 51),
                                                    focusedIndicatorColor = Color.Transparent,
                                                    unfocusedIndicatorColor = Color.Transparent,
                                                    errorIndicatorColor = Color.Transparent,
                                                    textColor = Color.Gray
                                                )
                                            )

                                        }
                                    }
                                }

                            }

                            Box(Modifier.padding(top = 50.dp)){
                                Column {
                                    Text("4. Категория", style = TextStyle(
                                        fontSize = 16.sp,
                                        color = Color.Gray
                                    )
                                    )
                                    //if(activCategVyb.value==false) {
                                    Box(Modifier.padding(top=5.dp, start = 10.dp, end = 10.dp).border(color= Color.Gray, width = 2.dp, shape = RoundedCornerShape(30)).fillMaxWidth().height(60.dp)
                                        .background(color= Color.White, shape = RoundedCornerShape(30)),
                                        contentAlignment = Alignment.CenterStart) {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Text(
                                                depList[applic.dep].name, style = TextStyle(
                                                    fontSize = 16.sp,
                                                    color = Color.Gray
                                                ), modifier = Modifier.padding(start = 15.dp)
                                            )

                                        }

                                    }
                                    // }

                                }

                            }

                            Box(Modifier.padding(top = 50.dp)){
                                Column {
                                    Text("5. Описание", style = TextStyle(
                                        fontSize = 16.sp,
                                        color  =Color.Gray
                                    )
                                    )
                                    Box(Modifier.padding(start = 10.dp, end=10.dp,top=5.dp)){
                                        Column {
                                            TextField(
                                                value = applic.description,
                                                onValueChange = {},
                                                readOnly = true,
                                                //label = { Text("описание проблемы") },
                                                singleLine = false,
                                                modifier = Modifier.padding(top=5.dp).fillMaxSize().background(color = Color.White).border(
                                                    width = 2.dp, shape = RoundedCornerShape(15.dp),
                                                    color = Color.Gray
                                                ),
                                                shape = RoundedCornerShape(15.dp),
                                                colors = TextFieldDefaults.textFieldColors(
                                                    backgroundColor = Color.White,
                                                    focusedLabelColor = Color(17, 69, 51),
                                                    focusedIndicatorColor = Color.Transparent,
                                                    unfocusedIndicatorColor = Color.Transparent,
                                                    errorIndicatorColor = Color.Transparent,
                                                    textColor = Color.Gray
                                                )
                                            )
                                        }
                                    }
                                }

                            }
                            Box(Modifier.padding(top = 50.dp)){
                                Column {
                                    Text("6. Дополнительные файлы", style = TextStyle(
                                        fontSize = 16.sp,
                                        color = Color.Gray
                                        //color = if (filePath.value=="") themes[0].secondColor else Color(12, 69, 51)
                                    )
                                    )
                                    Box(Modifier.padding(start = 10.dp, end=10.dp,top=5.dp, bottom = 10.dp)){
                                        Column {
                                            Box(Modifier.padding(top=10.dp).fillMaxWidth().height(200.dp)
                                                .border(color = Color.Gray , width = 2.dp, shape = RoundedCornerShape(5))
                                                .background(Color(245, 246, 247), shape = RoundedCornerShape(15))
                                                ,
                                                contentAlignment = Alignment.Center){
                                                if(applic.file1=="") {

                                                }
                                                else{
                                                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopStart){
                                                        Box(Modifier.padding(top=15.dp,start=15.dp).width(100.dp).height(100.dp).background(color = Color.White,
                                                            shape = RoundedCornerShape(10)
                                                        ), contentAlignment = Alignment.Center){
                                                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                                                Icon(painterResource("fileicon.svg"), contentDescription = null, modifier =
                                                                Modifier.height(40.dp), contentColorFor(Color.Gray)
                                                                )
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }

                                }

                            }
                            Box(Modifier.padding(top = 50.dp)){
                                Column {
                                    Text("7. Статус", style = TextStyle(
                                        fontSize = 16.sp,
                                        color = Color.Gray
                                    )
                                    )
                                    //if(activCategVyb.value==false) {
                                    Box(Modifier.padding(top=5.dp, start = 10.dp, end = 10.dp).border(color= Color.Gray, width = 2.dp, shape = RoundedCornerShape(30)).fillMaxWidth().height(60.dp)
                                        .background(color= Color.White, shape = RoundedCornerShape(30)),
                                        contentAlignment = Alignment.CenterStart) {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Text(
                                                if(applic.status==1){
                                                                    "Отправлено"
                                                                    }
                                                else{
                                                    if(applic.status==2){
                                                        "В работе"
                                                    }
                                                    else{
                                                        "Обработано"
                                                    }
                                                    }, style = TextStyle(
                                                    fontSize = 16.sp,
                                                    color = Color.Gray
                                                ), modifier = Modifier.padding(start = 15.dp)
                                            )

                                        }

                                    }
                                    // }

                                }

                            }



                            Box(Modifier.padding(top = 50.dp)){
                                Column {
                                    Text("8. Ответ", style = TextStyle(
                                        fontSize = 16.sp,
                                        color  = Color.Gray
                                    )
                                    )
                                    Box(Modifier.padding(start = 10.dp, end=10.dp,top=5.dp)){
                                        Column {
                                            TextField(
                                                value = applic.answerText,
                                                onValueChange = {},
                                                //label = { Text("описание ответа") },
                                                readOnly = true,
                                                singleLine = false,
                                                modifier = Modifier.padding(top=5.dp).fillMaxSize().background(color = Color.White).border(
                                                    width = 2.dp, shape = RoundedCornerShape(15.dp),
                                                    color = Color.Gray
                                                ),
                                                shape = RoundedCornerShape(15.dp),
                                                colors = TextFieldDefaults.textFieldColors(
                                                    backgroundColor = Color.White,
                                                    focusedLabelColor = Color(17, 69, 51),
                                                    focusedIndicatorColor = Color.Transparent,
                                                    unfocusedIndicatorColor = Color.Transparent,
                                                    errorIndicatorColor = Color.Transparent,
                                                    textColor = Color.Gray
                                                )
                                            )
                                        }
                                    }
                                }

                            }
                            Box(Modifier.padding(top = 50.dp)){
                                Column {
                                    Text("9. Файл ответа", style = TextStyle(
                                        fontSize = 16.sp,
                                        color = Color.Gray
                                        //color = if (filePath.value=="") themes[0].secondColor else Color(12, 69, 51)
                                    )
                                    )
                                    Box(Modifier.padding(start = 10.dp, end=10.dp,top=5.dp, bottom = 10.dp)){
                                        Column {
                                            Box(Modifier.padding(top=10.dp).fillMaxWidth().height(200.dp)
                                                .border(color = Color.Gray , width = 2.dp, shape = RoundedCornerShape(5))
                                                .background(Color(245, 246, 247), shape = RoundedCornerShape(15))
                                                ,
                                                contentAlignment = Alignment.Center){
                                                if(applic.file2=="") {

                                                }
                                                else{
                                                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopStart){
                                                        Box(Modifier.padding(top=15.dp,start=15.dp).width(100.dp).height(100.dp).background(color = Color.White,
                                                            shape = RoundedCornerShape(10)
                                                        ), contentAlignment = Alignment.Center){
                                                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                                                Icon(painterResource("fileicon.svg"), contentDescription = null, modifier =
                                                                Modifier.height(40.dp), contentColorFor(Color.Gray)
                                                                )
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }

                                }

                            }
                            Box(modifier = Modifier.padding(top=40.dp).fillMaxWidth(), contentAlignment = Alignment.CenterEnd){
                                Row{
                                    Box(Modifier.padding(end=10.dp).width(136.dp).height(40.dp).background(Color(137, 9, 9), shape =
                                    RoundedCornerShape(5)
                                    ).clickable{
                                        ApplicScreen.value=0
                                    }, contentAlignment = Alignment.Center){
                                        Text("Выйти", style = TextStyle(
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

    }
}
