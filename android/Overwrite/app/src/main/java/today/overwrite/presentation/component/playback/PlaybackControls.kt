package today.overwrite.presentation.component.playback

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import today.overwrite.R
import today.overwrite.presentation.theme.*

/**
 * 덧쓰기 애니메이션 재생 컨트롤
 *
 * 기능:
 * - 이전 / 재생(일시정지) / 다음
 * - 속도 조절 (1x, 2x)
 * - 커스텀 이미지 배경 지원
 * - 배경 이미지 사용 시 흰색 버튼 스타일 자동 적용
 * - 버튼들이 배경 이미지 정중앙에 배치
 */
@Composable
fun PlaybackControls(
    isPlaying: Boolean,
    playbackSpeed: Float,
    onPlayPauseClick: () -> Unit,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
    onSpeedClick: () -> Unit,
    modifier: Modifier = Modifier,
    canGoPrevious: Boolean = true,
    canGoNext: Boolean = true,
    // 배경 이미지 리소스 ID (옵션)
    backgroundImageRes: Int? = null
) {
    // 배경 이미지가 있으면 흰색 스타일, 없으면 기본 스타일
    val useWhiteStyle = backgroundImageRes != null

    Box(
        modifier = modifier
            .height(Dimensions.playbackControlHeight)
            .shadow(8.dp, RoundedCornerShape(32.dp))
            .clip(RoundedCornerShape(32.dp))
    ) {
        // 배경 이미지 또는 단색
        if (backgroundImageRes != null) {
            Image(
                painter = painterResource(id = backgroundImageRes),
                contentDescription = "재생 컨트롤 배경",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        } else {
            // 기본 배경색
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(PolaroidBrown)
            )
        }

        // 컨트롤 버튼들 (중앙 배치)
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center  // 핵심: 중앙 정렬!
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(Spacing.medium),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 이전 버튼
                ControlButton(
                    onClick = onPreviousClick,
                    enabled = canGoPrevious,
                    icon = Icons.Default.SkipPrevious,
                    contentDescription = "이전",
                    useWhiteStyle = useWhiteStyle
                )

                // 재생/일시정지 버튼 (강조)
                Box(
                    modifier = Modifier
                        .size(Dimensions.playbackButtonSize)
                        .shadow(4.dp, CircleShape)
                        .clip(CircleShape)
                        .background(if (useWhiteStyle) Color.White else OceanBlue)
                        .clickable(onClick = onPlayPauseClick),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                        contentDescription = if (isPlaying) "일시정지" else "재생",
                        tint = if (useWhiteStyle) OceanBlue else Color.White,
                        modifier = Modifier.size(32.dp)
                    )
                }

                // 다음 버튼
                ControlButton(
                    onClick = onNextClick,
                    enabled = canGoNext,
                    icon = Icons.Default.SkipNext,
                    contentDescription = "다음",
                    useWhiteStyle = useWhiteStyle
                )

                Spacer(modifier = Modifier.width(Spacing.small))

                // 속도 조절 버튼
                Box(
                    modifier = Modifier
                        .size(Dimensions.playbackSpeedButtonSize)
                        .clip(CircleShape)
                        .background(if (useWhiteStyle) Color.White else SandBeige)
                        .clickable(onClick = onSpeedClick),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "${playbackSpeed}x",
                        style = MaterialTheme.typography.labelMedium,
                        color = if (useWhiteStyle) OceanBlue else TextPrimary
                    )
                }
            }
        }
    }
}

/**
 * 재생 컨트롤 버튼 (공통)
 *
 * @param useWhiteStyle true일 경우 흰색 버튼 스타일 적용
 */
@Composable
private fun ControlButton(
    onClick: () -> Unit,
    enabled: Boolean,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    contentDescription: String,
    modifier: Modifier = Modifier,
    useWhiteStyle: Boolean = false
) {
    Box(
        modifier = modifier
            .size(Dimensions.playbackSpeedButtonSize)
            .clip(CircleShape)
            .background(
                when {
                    !enabled -> DisabledGray.copy(alpha = 0.3f)
                    useWhiteStyle -> Color.White
                    else -> SandBeige
                }
            )
            .clickable(enabled = enabled, onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = when {
                !enabled -> DisabledGray
                useWhiteStyle -> OceanBlue  // 흰색 배경에는 파란색 아이콘
                else -> TextPrimary
            },
            modifier = Modifier.size(24.dp)
        )
    }
}

/**
 * 프로그레스 바 (선택적)
 */
@Composable
fun PlaybackProgressBar(
    progress: Float,
    modifier: Modifier = Modifier,
    onSeek: ((Float) -> Unit)? = null
) {
    Column(modifier = modifier) {
        // 프로그레스 바
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .clip(RoundedCornerShape(2.dp))
                .background(DisabledGray.copy(alpha = 0.3f))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(progress)
                    .fillMaxHeight()
                    .background(OceanBlue)
            )
        }

        Spacer(modifier = Modifier.height(Spacing.extraSmall))

        // 타임스탬프 (선택적)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = formatTime((progress * 100).toInt()),
                style = MaterialTheme.typography.labelSmall,
                color = TextSecondary
            )
            Text(
                text = formatTime(100),
                style = MaterialTheme.typography.labelSmall,
                color = TextSecondary
            )
        }
    }
}

private fun formatTime(seconds: Int): String {
    val mins = seconds / 60
    val secs = seconds % 60
    return "%02d:%02d".format(mins, secs)
}

// ========================================
// Preview
// ========================================

@Preview(showBackground = true, name = "기본 배경 (중앙 배치)")
@Composable
fun PlaybackControlsPreview() {
    OverwriteTheme {
        var isPlaying by remember { mutableStateOf(false) }
        var speed by remember { mutableFloatStateOf(1f) }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            PlaybackProgressBar(
                progress = 0.35f
            )

            PlaybackControls(
                isPlaying = isPlaying,
                playbackSpeed = speed,
                onPlayPauseClick = { isPlaying = !isPlaying },
                onPreviousClick = {},
                onNextClick = {},
                onSpeedClick = {
                    speed = if (speed == 1f) 2f else 1f
                }
            )
        }
    }
}

@Preview(showBackground = true, name = "이미지 배경 (중앙 배치)")
@Composable
fun PlaybackControlsWithImagePreview() {
    OverwriteTheme {
        var isPlaying by remember { mutableStateOf(false) }
        var speed by remember { mutableFloatStateOf(1f) }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            PlaybackProgressBar(
                progress = 0.35f
            )

            // 이미지 배경 사용 (자동으로 흰색 버튼 스타일 + 중앙 배치)
            PlaybackControls(
                isPlaying = isPlaying,
                playbackSpeed = speed,
                onPlayPauseClick = { isPlaying = !isPlaying },
                onPreviousClick = {},
                onNextClick = {},
                onSpeedClick = {
                    speed = if (speed == 1f) 2f else 1f
                },
                backgroundImageRes = R.drawable.bg_playback_controls
            )
        }
    }
}

@Preview(showBackground = true, name = "하늘색 배경 시뮬레이션")
@Composable
fun PlaybackControlsBlueBackgroundPreview() {
    OverwriteTheme {
        var isPlaying by remember { mutableStateOf(false) }
        var speed by remember { mutableFloatStateOf(1f) }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // 하늘색 배경 Box로 시뮬레이션
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF87CEEB))  // 하늘색
                    .padding(16.dp)
            ) {
                PlaybackControls(
                    isPlaying = isPlaying,
                    playbackSpeed = speed,
                    onPlayPauseClick = { isPlaying = !isPlaying },
                    onPreviousClick = {},
                    onNextClick = {},
                    onSpeedClick = {
                        speed = if (speed == 1f) 2f else 1f
                    },
                    backgroundImageRes = R.drawable.bg_playback_controls
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "넓은 배경에서 중앙 배치 확인")
@Composable
fun PlaybackControlsWideCenterPreview() {
    OverwriteTheme {
        var isPlaying by remember { mutableStateOf(false) }
        var speed by remember { mutableFloatStateOf(1f) }

        // 매우 넓은 컨테이너로 중앙 배치 테스트
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color(0xFF87CEEB))
        ) {
            PlaybackControls(
                isPlaying = isPlaying,
                playbackSpeed = speed,
                onPlayPauseClick = { isPlaying = !isPlaying },
                onPreviousClick = {},
                onNextClick = {},
                onSpeedClick = {
                    speed = if (speed == 1f) 2f else 1f
                },
                modifier = Modifier.fillMaxWidth(),
                backgroundImageRes = R.drawable.bg_playback_controls
            )
        }
    }
}