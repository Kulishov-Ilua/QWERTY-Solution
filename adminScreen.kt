//#####################################################################################################################
//#####################################################################################################################
//###############################                     Модуль администратора             ###############################
//#####################################################################################################################
//####    Автор: Кулишов Илья Вячеславович    #########################################################################
//####    Версия: v.0.0.0.1                   #########################################################################
//####    Дата: 16.04.2024                    #########################################################################
//#####################################################################################################################
//#####################################################################################################################

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
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
import kotlinx.coroutines.delay

//=====================================================================================
//Карточка пользователя
//=====================================================================================
@Composable
fun personBlock(user: User){
    Box(Modifier.padding(bottom = 5.dp).width(280.dp).height(150.dp).shadow(elevation = 4.dp, shape = RoundedCornerShape(8))
        .background(Color.White, shape = RoundedCornerShape(8))){
        Row {
            Box(Modifier.fillMaxHeight().width(100.dp).background(Color.Gray))
            Box(modifier = Modifier.fillMaxSize()){
                Column(Modifier.padding(top=10.dp, start = 5.dp)) {
                    Text("${user.surname} ${user.name}", style = TextStyle(
                        fontSize = 12.sp
                    ), modifier = Modifier.padding(bottom = 10.dp))
                    var departString = remember { mutableStateOf("") }
                    departString.value=""
                    for(x in depList){
                        if(user.departament.contains("_${x.id}_")&&x.id!=0){
                            if(!departString.value.contains("${x.name}")) departString.value+= "${x.name} "
                        }
                    }
                    Text(if(departString.value!="") departString.value else "Нет отдела", style = TextStyle(
                        fontSize = 12.sp,
                        color = Color.Gray
                    ))
                    Box(Modifier.padding(bottom = 10.dp).fillMaxSize(), contentAlignment = Alignment.BottomCenter){
                        Column {
                            Box(Modifier.width(160.dp).height(30.dp).background(themes[0].corpColor, shape = RoundedCornerShape(20)).clickable {
                                        profUserActiv.value=user
                                professionState.value=true


                            }, contentAlignment = Alignment.Center){
                                Text("Сменить должность", style = TextStyle(
                                    fontSize = 10.sp,
                                    color = Color.White
                                )
                                )
                            }
                            Box(Modifier.padding(top=5.dp).width(160.dp).height(30.dp).background(Color.Red, shape = RoundedCornerShape(20))
                                .clickable {
                                    userUpdateFlag.value=false
                                    var iterator1 = userList.iterator()
                                    /*var countdel=0
                                           for(x in userList){
                                               if(x.id==user.id) userList.removeAt(countdel)
                                               countdel++
                                           }
                                     */
                                    while(iterator1.hasNext()){
                                        val user1 = iterator1.next()
                                        if(user1.id==user.id&&user1.id!=userTek) iterator1.remove()
                                    }
                                    userList=userList.toMutableList()
                                    //var i =0
                                    //while( i< 100000000){i++}
                                    userUpdateFlag.value=true
                            }, contentAlignment = Alignment.Center){
                                Text("Уволить", style = TextStyle(
                                    fontSize = 10.sp,
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
}


//=====================================================================================
//Блок пользователей
//=====================================================================================
@Composable
fun personBlocklist(view:Boolean, dep:Int){
    var userListUpdate = remember { (userList) }
    userListUpdate=userList
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
                        if(!view) "Все пользователи"
                        else "Все пользователи"
                    }
                    else{
                        if(!view) depList[dep].name
                        else depList[dep].name
                    })
                }
                Box(Modifier.fillMaxWidth().height(1.dp).background(color = themes[0].secondColor))
            }
        }
        var applicarraywithdep = remember {   mutableListOf<User>()}
        if (activBlock.value == true) {
            var innerLazyColumnHeight = 0
            if(userUpdateFlag.value) {
                applicarraywithdep.clear()

                for (x in userListUpdate) {
                    if (dep == 0 || x.departament.contains("_${depList[dep].id}_")) {
                        innerLazyColumnHeight++
                        applicarraywithdep += x
                    }
                }
                //userUpdateFlag.value=false
            }
            //if(dep==0&&view==true) innerLazyColumnHeight++;
            var flag = mutableStateOf(false)
            if (innerLazyColumnHeight<4) flag.value = true
            if (innerLazyColumnHeight % 4 > 0) innerLazyColumnHeight += 4
            innerLazyColumnHeight /= 4
            innerLazyColumnHeight *= 180
            if(!flag.value||1==1) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(4),
                    verticalArrangement = Arrangement.spacedBy(25.dp),
                    horizontalArrangement = Arrangement.spacedBy(32.dp),
                    modifier = Modifier.height(innerLazyColumnHeight.dp).padding(top = 10.dp)
                ) {
                    items(applicarraywithdep) { item ->
                        personBlock(item)
                    }

                }
            }

        }

    }
}
