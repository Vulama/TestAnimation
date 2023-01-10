package com.example.testanimation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Screen()
        }
    }
}

@Composable
fun Screen() {
    AnimatedContentStateExample()
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedContentStateExample() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        val state = remember { mutableStateOf<UiState>(UiState.None) }
        val mainScroll = rememberScrollState()
        val secondaryScroll = rememberScrollState()

        AnimatedContent(
            targetState = state
        ) { targetState ->
            // Make sure to use `targetState`, not `state`.
            when (targetState.value) {
                UiState.A -> {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .verticalScroll(secondaryScroll),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Spacer(modifier = Modifier.height(200.dp))
                        Text(text = "AAAAAAAAAAAAAAAA")
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(text = "AAAAAAAAAAAAAAAA")
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(text = "AAAAAAAAAAAAAAAA")
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(text = "AAAAAAAAAAAAAAAA")

                        Spacer(modifier = Modifier.height(500.dp))

                        Text(text = "AAAAAAAAAAAAAAAA")
                    }
                }
                UiState.B -> {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .verticalScroll(secondaryScroll),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Spacer(modifier = Modifier.height(200.dp))
                        Text(text = "BBBBBBBBBBBBBBBB")
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(text = "BBBBBBBBBBBBBBBB")
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(text = "BBBBBBBBBBBBBBBB")
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(text = "BBBBBBBBBBBBBBBB")

                        Spacer(modifier = Modifier.height(500.dp))

                        Text(text = "BBBBBBBBBBBBBBBB")
                    }
                }
                UiState.None -> {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 100.dp)
                            .verticalScroll(mainScroll),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(text = "NEUTRALNEUTRALNEUTRAL")
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(text = "NEUTRALNEUTRALNEUTRAL")
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(text = "NEUTRALNEUTRALNEUTRAL")
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(text = "NEUTRALNEUTRALNEUTRAL")

                        Spacer(modifier = Modifier.height(800.dp))

                        Text(text = "NEUTRALNEUTRALNEUTRAL")
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(text = "NEUTRALNEUTRALNEUTRAL")

                        Spacer(modifier = Modifier.height(100.dp))
                    }
                }
            }
        }

        A(state, mainScroll.value, secondaryScroll.value)

        Box(modifier = Modifier.padding(top = if(state.value == UiState.None) 50.dp else 0.dp)){
            B(state, mainScroll.value, secondaryScroll.value)
        }

        Box(modifier = Modifier.padding(top = if(state.value == UiState.None) 100.dp else 0.dp)){
            A(state, mainScroll.value, secondaryScroll.value)
        }
    }
}

@Composable
fun A(state: MutableState<UiState>, main: Int, secondary: Int) {
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        println("Vulama: $main")
        val offset = (main / LocalDensity.current.density).toInt()

        val padding: Float by animateFloatAsState(
            targetValue = calculatePadding(UiState.A, state.value, offset.toFloat(), secondary.toFloat()),
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioLowBouncy,
                stiffness = Spring.StiffnessLow
            )
        )

        Box(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .offset(y = padding.dp)
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Black, RoundedCornerShape(12.dp))
                .clickable {
                    if (state.value is UiState.None) {
                        state.value = UiState.A
                    } else {
                        state.value = UiState.None
                    }
                },
        )
    }
}

@Composable
fun B(state: MutableState<UiState>, main: Int, secondary: Int) {
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        println("Vulama: $main")
        val offset = (main / LocalDensity.current.density).toInt()
        val padding: Float by animateFloatAsState(
            targetValue = calculatePadding(UiState.B, state.value, offset.toFloat(), secondary.toFloat()),
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioLowBouncy,
                stiffness = Spring.StiffnessLow
            )
        )

        Box(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .offset(y = padding.dp)
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Yellow, RoundedCornerShape(12.dp))
                .clickable {
                    if (state.value is UiState.None) {
                        state.value = UiState.B
                    } else {
                        state.value = UiState.None
                    }
                },
        )
    }
}

@Composable
fun calculatePadding(ui: UiState, state: UiState, offset: Float, secondary: Float): Float =
    if (state == ui) {
        -secondary + 150f / LocalDensity.current.density
    } else if(state is UiState.None) {
        -offset + 1500f / LocalDensity.current.density
    } else {
        1000f
    }


sealed interface UiState{
    object A : UiState
    object B : UiState
    object C : UiState
    object None : UiState
}