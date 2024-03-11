package com.myapps.weathernow.ui.errors

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myapps.weathernow.R


@Composable
fun ErrorCard(
    errorMessage:String,
    errorDescription:String,
    onClickButton:()->Unit,
    modifier: Modifier
){
    Box(modifier = Modifier
        .fillMaxSize()
    ){
        Card(
            modifier = modifier
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(modifier = Modifier
                    .background(Color.Red)
                    .align(Alignment.CenterHorizontally)
                ){
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ){
                        Image(
                            painter = painterResource(id = R.drawable.error),
                            contentDescription = "null",
                            modifier = Modifier
                                .size(64.dp)
                        )
                        Spacer(modifier = Modifier.size(20.dp))
                        Text(
                            errorMessage,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }

                }
                Box(modifier = Modifier
                    .background(Color.White)
                    .align(Alignment.CenterHorizontally)
                ){
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ){
                        Text(
                            errorDescription,
                            textAlign = TextAlign.Center,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(30.dp))
                        Button(
                            onClick = onClickButton,
                            shape = RoundedCornerShape(40),
                            colors = ButtonDefaults.buttonColors(
                                contentColor = Color.Red,
                                containerColor = Color.Red
                            )
                        ){
                            Text(
                                text = "OK",
                                color = Color.LightGray
                            )
                        }
                    }
                }

            }
        }
    }

}