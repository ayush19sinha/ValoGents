package my.android.valogents.ui.screens.agentScreens.components

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import my.android.valogents.data.model.Agent

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.AgentCard(
    agent: Agent,
    onAgentClick: (String) -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    val gradientColor = agent.backgroundGradientColors.map {
        Color(android.graphics.Color.parseColor("#${it.dropLast(2)}"))
    }

    val transition = rememberInfiniteTransition()
    val animatedOffset by transition.animateFloat(
        initialValue = 0f,
        targetValue = 400f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(280.dp)
            .padding(16.dp)
            .clickable { onAgentClick(agent.uuid) },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .drawWithCache {
                    val brushSize = size.minDimension
                    val brush = Brush.linearGradient(
                        colors = gradientColor,
                        start = Offset(animatedOffset, animatedOffset),
                        end = Offset(
                            x = animatedOffset + brushSize,
                            y = animatedOffset + brushSize
                        ),
                        tileMode = TileMode.Mirror
                    )
                    onDrawBehind {
                        drawRect(brush = brush)
                    }
                }
                .fillMaxSize()
        ) {
            AsyncImage(
                model = agent.background,
                contentDescription = "Agent Background",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                alpha = 0.3f
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.3f))
            )

            AsyncImage(
                model = agent.fullPortrait,
                contentDescription = "Agent Image",
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxHeight()
                    .sharedElement(
                        state = rememberSharedContentState(key = "image-${agent.uuid}"),
                        animatedVisibilityScope = animatedVisibilityScope
                    )
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
            ) {
                Text(
                    text = agent.displayName,
                    color = Color.White,
                    fontSize = 32.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.sharedElement(
                        state = rememberSharedContentState(key = "name-${agent.uuid}"),
                        animatedVisibilityScope = animatedVisibilityScope
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.sharedElement(
                        state = rememberSharedContentState(key = "agentRole-${agent.uuid}"),
                        animatedVisibilityScope = animatedVisibilityScope
                    )
                ) {
                    AsyncImage(
                        model = agent.role.displayIcon,
                        contentDescription = "Role icon",
                        modifier = Modifier.size(24.dp)
                    )
                    Text(
                        text = agent.role.displayName,
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}