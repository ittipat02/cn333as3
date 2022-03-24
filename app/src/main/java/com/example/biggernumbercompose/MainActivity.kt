package com.example.biggernumbercompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            findNumber()
        }
    }
}

fun randomNum(): Int{
    val random = Random()
    var res = random.nextInt(1000)
    return res
}
fun checkAns(answer: Int, checkN: Int): Int{
    if(answer > checkN)
        return 1
    else if(answer < checkN)
        return -1
    else
        return 0
}
fun checkString(ans: String, r: Int):String{
    print("in checkString")

    var res = ans.filter { it.isDigit() }
    if(res.isEmpty()){
        return "not number"
    }

    if(ans.toInt() is Int){
        var check = ans.toInt()
        return if(check == r ){
            "Correct!!"
        }
        else if(check < r){
            "Higher"
        }else{
            "Lower"
        }
    }
    return "Not number"
}






@Composable
fun SubmitButton(name: String ,onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "$name",
            fontSize = 24.sp,

            textAlign = TextAlign.Center,
            modifier = Modifier
                //.padding(8.dp)

                .fillMaxWidth()
            )
    }
}

@Composable
fun findNumber() {
    var message = remember { mutableStateOf("") }

    var isCorrect = remember {
        mutableStateOf(true)
    }
    var ansForCheck = ""
    var r = remember {
        mutableStateOf(randomNum())
    }
    val resetR =  {
        val newR = randomNum()
        r.value = newR
    }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = if (isCorrect.value) {
                    Color.White
                } else {
                    Color.Green
                }
            )
            .fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text(

            text = "value is in range 0-1000",
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        )
        Text(
            text = "${message.value}",
            fontSize = 24.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            textAlign = TextAlign.Center,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()

                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ansForCheck = NumberGuess() //NumberGuess()
            SubmitButton("submit",onClick = { message.value = checkString(ansForCheck, r = r.value)})
            //SubmitButton("reset",onClick = {resetR})

        }
        SubmitButton("reset",onClick = {resetR()})

    }
}

@Composable
fun NumberGuess():String {

    var message = remember { mutableStateOf("") }
    var s = remember { mutableStateOf("") }

    //(value = message.value, onValueChange = {newText -> message.value = newText})
    Column(
        verticalArrangement = Arrangement.SpaceAround,

    ) {
        OutlinedTextField(
            value = s.value,
            onValueChange = { newText -> s.value = newText },
            placeholder = {
                Text(
                    text = "this is text",
                    //fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                )
            },
        )
    }
    message.value = s.value
    return s.value
}


