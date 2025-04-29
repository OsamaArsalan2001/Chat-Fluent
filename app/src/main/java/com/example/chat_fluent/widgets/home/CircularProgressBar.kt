package com.example.chat_fluent.widgets.home

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chat_fluent.ui.theme.LightBlue
import com.example.chat_fluent.ui.theme.buttonColorSignup

@Composable
fun LearningProgressBar(
    progress: Float,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.size(70.dp)

    ) {
        CircularProgressIndicator(
            progress = 1f,
            color = LightBlue,
            strokeWidth = 10.dp,
            modifier = Modifier.size(70.dp)
        )
        CircularProgressIndicator(
            progress = progress,
            color = MaterialTheme.colorScheme.primary,
            strokeWidth = 10.dp,
            modifier = Modifier.size(70.dp)
        )
        Text(
            text = "${(progress * 100).toInt()}%",
            style = MaterialTheme.typography.labelLarge
        )
    }
}
/*@Composable
fun LearningProgressBar(
    percentage:Float,
    number:Int,
    fontSize:TextUnit=28.sp,
    radius: Dp =50.dp,
    color:Color= buttonColorSignup,
    strokeWidth:Dp=8.dp,
    animDuration:Int=1000,
    animDelay:Int=0

    )
{
    var animationPlayed by remember {
        mutableStateOf(false)
    }
    val curPercentage= animateFloatAsState(
        targetValue = if(animationPlayed)percentage else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = animDelay
        )
    )
}*/