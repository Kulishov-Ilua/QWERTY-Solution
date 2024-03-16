import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color


//#####################################################################################################################
//#####################################################################################################################
//###############################                     База данных                       ###############################
//#####################################################################################################################
//####    Автор: Кулишов Илья Вячеславович    #########################################################################
//####    Версия: v.0.0.0.6                   #########################################################################
//####    Дата: 04.02.2024                    #########################################################################
//#####################################################################################################################
//#####################################################################################################################



data class AuthenticationState(
    val email: String? = null,
    val password: String? = null
)

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//класс для хранения тем                                                   ~~~~~~~~~~~~
//Поля:                                                                    ~~~~~~~~~~~~
//      mainColor: Color    - основной цвет                                ~~~~~~~~~~~~
//      secondColor:Color   - дополнительный цвет                          ~~~~~~~~~~~~
//      corpColor:Color     - корпоративный цвет                           ~~~~~~~~~~~~
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
class Theme(mainColor: Color,secondColor:Color,corpColor:Color){
    var mainColor:Color=mainColor
    var secondColor:Color = secondColor
    var corpColor:Color=corpColor
}


//#####################################################################################
//Массив для хранения задач                                                ############
//#####################################################################################
var themes = listOf<Theme>(
    Theme(Color(245, 246, 247), Color.Black, Color(17, 69, 51))
)

var corpKod = mutableStateOf("0")
var token = mutableStateOf("")

class User(name:String, surname:String, departament:Int, photo:Int){
    var name:String=name
    var surname:String=surname
    var departament:Int=departament
    var photo:Int=photo
}
var userTek = User("Ivanov", "Ivan", 0, 0)

class ApplicData(name:String, author:String, dep:Int, answerPep:String, status:Int,
                 description:String, answerText:String, file1:String, file2:String, view:Boolean){
    var name:String=name
    var author:String=author
    var dep:Int= dep
    var answerPep:String = answerPep
    var status:Int = status
    var description:String=description
    var answerText:String=answerText
    var file1:String=file1
    var file2:String=file2
    var view:Boolean=view
}

var ApllicArray = listOf(
    ApplicData("Сломался стул", "Ivanov I", 0, "",0,"","","","", true)
)

class Dep(id:Int, name:String, pepId:Int){
    var id:Int=id
    var name:String=name
    var pepId:Int = pepId
}

var depList = listOf(
    Dep(0,"Отдел по закупке",0)
)