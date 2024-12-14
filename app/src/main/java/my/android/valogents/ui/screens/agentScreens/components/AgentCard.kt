package my.android.valogents.ui.screens.agentScreens.components

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import my.android.valogents.data.model.Agent

@Composable
fun AgentCard(agent: Agent, onAgentClick:(String)-> Unit) {
    val gradientColor = agent.backgroundGradientColors.map {
        Color(android.graphics.Color.parseColor("#${it.dropLast(2)}"))
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
            .padding(16.dp)
            .clickable { onAgentClick(agent.uuid) },
        shape = RoundedCornerShape(16.dp),
        shadowElevation = 8.dp
    ) {
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.linearGradient(gradientColor)
                )
                .fillMaxSize()
        ) {
            AsyncImage(
                model = agent.background,
                contentDescription = "Agent Background",
                modifier = Modifier.fillMaxHeight(),
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
                    .aspectRatio(1.1f),
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
                    fontSize = 28.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    AsyncImage(
                        model = agent.role.displayIcon,
                        contentDescription = "Role icon",
                        modifier = Modifier.size(22.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = agent.role.displayName,
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}