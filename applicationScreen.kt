import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
/*
var ApplicScreen = mutableStateOf(0)
@Preview
@Composable
fun applicScreenMain(){
    Box(modifier = Modifier.fillMaxSize()){
        Column{
            Box(modifier = Modifier.fillMaxWidth().height(70.dp).background(color = Color(17, 31, 38)),
                contentAlignment = Alignment.Center) {
                Row (verticalAlignment = Alignment.CenterVertically){
                    Text("QWERTY Solution", style = TextStyle(
                        color= Color.White,
                        fontSize = 20.sp
                    ), modifier = Modifier.padding(end = 100.dp))
                    Box(modifier = Modifier.padding(start = 100.dp, end=100.dp).height(45.dp).width(600.dp)
                        .background(color = Color.White, shape = RoundedCornerShape(15))){

                    }
                    Text("{userTek.surname} {userTek.name[0]}.", style = TextStyle(
                        color= Color.White,
                        fontSize = 16.sp
                    ), modifier = Modifier.padding(start = 100.dp,end = 5.dp))
                    Box(modifier = Modifier.width(45.dp).height(45.dp).background(color = Color.White,
                        shape = RoundedCornerShape(15)
                    )){

                    }
                }

            }
            Box(modifier = Modifier.fillMaxWidth().height(45.dp).background(themes[0].corpColor),
                contentAlignment = Alignment.Center){
                Box{
                    Text("Мои заявки", style = TextStyle(
                        fontSize = 16.sp,
                        color = Color.White
                    )
                    )
                }
            }
            if(ApplicScreen.value==0){}
        }
    }
}

@Composable
fun myApplic(){
    Box(Modifier.fillMaxSize()){

    }
}

@Composable
fun boxApplic(event:ApplicData){
    Box(modifier = Modifier.width(280.dp).height(150.dp).background(color = Color.White, shape = RoundedCornerShape(15))){
        Column {
            Text(event.name, style = TextStyle(
                fontSize = 16.sp,
                color = Color.Black
            ), modifier = Modifier.padding(top=15.dp, start = 5.dp)
            )
            Text("${event.answerPep} ${event.dep}", style = TextStyle(
                fontSize = 12.sp,
                color= Color.Black
            ), modifier = Modifier.padding(top=5.dp, start = 5.dp)
            )
            Box(modifier = Modifier.padding(bottom = 5.dp).fillMaxHeight(), contentAlignment = Alignment.BottomStart){
                Row(modifier = Modifier.padding(start = 5.dp, end = 5.dp).fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically) {
                    Box(Modifier.width(18.dp).height(18.dp).background(
                        color = if(event.status==0){ Color.Gray}
                                else {
                            if (event.status == 1) {
                                Color.Blue
                            } else {
                                Color.Green
                            }
                        }, shape = CircleShape
                    ))
                    Text(text = if(event.status==0){ "Отправлено"}
                    else {
                        if (event.status == 1) {
                            "Рассматривается"
                        } else {
                            "Готово"
                        }
                    }, style = TextStyle(
                        fontSize = 12.sp,
                        color = Color.Black
                    )
                    )
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd){
                        
                    }
                }
            }
        }

    }
}

@Composable
fun applicBlock(view:Boolean, dep:Int){
    Column(Modifier.padding(start = 110.dp, end=110.dp).fillMaxWidth()) {
        Box(Modifier.fillMaxWidth()){
            Column {
                Row {
                    Box(Modifier.width(10.dp).height(10.dp))
                    Text(text = if(dep == 0){
                        if(!view) "Скрыто"
                        else "Мои заявки"
                    }
                    else{
                      ""
                    })
                }
                Box(Modifier.fillMaxWidth().height(1.dp).background(color = themes[0].secondColor))
            }
        }

        LazyColumn {
            items(ApllicArray){
                item->var Appliccount= listOf<ApplicData>()
            }
        }
    }
}*/