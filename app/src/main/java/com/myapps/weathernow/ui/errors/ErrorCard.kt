package com.myapps.weathernow.ui.errors

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myapps.weathernow.R
import com.myapps.weathernow.ui.ui.theme.LightBlue
import com.myapps.weathernow.ui.ui.theme.Magenta
import com.myapps.weathernow.ui.ui.theme.OceanBlueSoft


@Composable
fun ErrorCard(
    errorMessage: String,
    errorDescription: String,
    onRefresh: () -> Unit,
    onAction: () -> Unit,
    actionName: String?,
    modifier: Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Card(
            modifier = modifier
                .align(Alignment.Center),
            border = BorderStroke(
                2.dp, Brush.linearGradient(
                    colorStops = arrayOf(
                        0.15f to LightBlue,
                        0.85f to Magenta
                    )
                )
            ),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(modifier = Modifier
                    .drawBehind {
                        drawRect(
                            color = OceanBlueSoft
                        )
                    }
                    .align(Alignment.CenterHorizontally)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
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
                Box(
                    modifier = Modifier
                        .background(Color.White)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            errorDescription,
                            textAlign = TextAlign.Center,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(30.dp))

                        Row(
                            horizontalArrangement = Arrangement.SpaceAround,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            if (actionName != null) {
                                Button(
                                    onClick = { onAction() },
                                    shape = RoundedCornerShape(10),
                                    colors = ButtonDefaults.buttonColors(
                                        contentColor = OceanBlueSoft,
                                        containerColor = OceanBlueSoft
                                    )
                                ) {
                                    Text(text = actionName, color = Color.White, fontSize = 12.sp)
                                }
                            }
                            Button(
                                onClick = { onRefresh() },
                                shape = RoundedCornerShape(10),
                                colors = ButtonDefaults.buttonColors(
                                    contentColor = OceanBlueSoft,
                                    containerColor = OceanBlueSoft
                                )
                            ) {
                                Row {
                                    Icon(
                                        painter = painterResource(id = R.drawable.refresh),
                                        contentDescription = null,
                                        tint = Color.White
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(text = "refresh", color = Color.White, fontSize = 12.sp)
                                }
                            }
                        }
                    }
                }

            }
        }
    }

}