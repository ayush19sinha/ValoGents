package my.android.valogents.ui.screens.agentScreens

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import my.android.valogents.data.model.Ability
import my.android.valogents.ui.theme.poppinsFontFamily
import my.android.valogents.ui.viewmodel.AgentViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.AgentDetailScreen(
    agentViewModel: AgentViewModel,
    animatedVisibilityScope: AnimatedVisibilityScope,
    agentId: String, onBackClick: () -> Unit
) {
    val agent by agentViewModel.getAgentById(agentId).collectAsState(initial = null)
    var specialAbility by rememberSaveable { mutableIntStateOf(0) }

    agent?.let {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            it.displayName,
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = onBackClick) {
                            Icon(
                                Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back Arrow", tint = Color.White
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xFF111922),
                        titleContentColor = Color.White
                    )
                )
            }
        ) { padding ->
            Box(
                Modifier
                    .fillMaxSize()
                    .background(Color(0xFF111922))
                    .padding(padding)
            ) {
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(16.dp)
                ) {
                    AgentPortrait(
                        it.fullPortrait,
                        modifier = Modifier.sharedElement(
                            state = rememberSharedContentState(
                                key = "image-${it.uuid}"
                            ),
                            animatedVisibilityScope = animatedVisibilityScope
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    AgentRole(
                        it.role.displayIcon, it.role.displayName,
                        modifier = Modifier
                            .sharedElement(
                                state = rememberSharedContentState(
                                    key = "agentRole-${it.uuid}"
                                ),
                                animatedVisibilityScope = animatedVisibilityScope
                            )
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    AgentDescription(it.description)
                    Spacer(modifier = Modifier.height(24.dp))
                    AbilitiesSection(it.abilities, specialAbility) { index ->
                        specialAbility = index
                    }
                }
            }
        }
    }
}

@Composable
fun AgentPortrait(portraitUrl: String, modifier: Modifier = Modifier) {
    AsyncImage(
        model = portraitUrl,
        contentDescription = "Agent Portrait",
        modifier = modifier
            .fillMaxWidth()
            .height(320.dp)
            .clip(RoundedCornerShape(16.dp)),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun AgentRole(
    roleIconUrl: String, roleName: String,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        AsyncImage(
            model = roleIconUrl,
            contentDescription = "Role Icon",
            modifier = Modifier.size(24.dp)
        )
        Text(roleName, fontSize = 18.sp, color = Color.White)
    }
}

@Composable
fun AgentDescription(description: String) {
    Column {
        SectionTitle("Description")
        Text(
            description,
            color = Color.White.copy(alpha = 0.8f),
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
fun AbilitiesSection(
    abilities: List<Ability>,
    selectedAbilityIndex: Int,
    onAbilitySelected: (Int) -> Unit
) {
    Column {
        SectionTitle("Special Abilities")
        LazyRow (
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(top = 8.dp)
        ) {
            itemsIndexed(abilities) { index, ability ->
                AbilityButton(
                    ability = ability,
                    isSelected = index == selectedAbilityIndex,
                    onClick = { onAbilitySelected(index) }
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        abilities.getOrNull(selectedAbilityIndex)?.let { selectedAbility ->
            Text(
                selectedAbility.description,
                color = Color.White.copy(alpha = 0.8f),
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        title,
        fontSize = 22.sp,
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.Bold,
        color = Color.White
    )
}

@Composable
fun AbilityButton(ability: Ability, isSelected: Boolean, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(72.dp)
    ) {
        IconButton(
            onClick = onClick,
            modifier = Modifier
                .size(64.dp)
                .background(
                    color = if (isSelected) Color.White.copy(alpha = 0.3f) else Color.Transparent,
                    shape = RoundedCornerShape(8.dp)
                )
        ) {
            AsyncImage(
                model = ability.displayIcon,
                contentDescription = "Ability Icon",
                modifier = Modifier.size(48.dp)
            )
        }
        Text(
            ability.displayName,
            textAlign = TextAlign.Center,
            fontSize = 12.sp,
            color = if (isSelected) Color.White else Color.White.copy(alpha = 0.7f),
            maxLines = 1
        )
    }
}