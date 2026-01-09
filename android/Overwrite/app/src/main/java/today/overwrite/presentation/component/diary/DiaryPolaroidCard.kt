package today.overwrite.presentation.component.diary

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import today.overwrite.R
import today.overwrite.presentation.theme.*

/**
 * 폴라로이드 스타일 일기 카드
 *
 * 구성:
 * - 상단: AI 생성 이미지 영역
 * - 하단: 날짜 + 한 줄 텍스트
 */
@Composable
fun DiaryPolaroidCard(
    imageUrl: String?,
    date: String,
    previewText: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .width(Dimensions.polaroidCardWidth)
            .shadow(
                elevation = Dimensions.polaroidElevation,
                shape = RoundedCornerShape(Dimensions.manuscriptCornerRadius)
            )
            .clip(RoundedCornerShape(Dimensions.manuscriptCornerRadius))
            .background(PolaroidBrown)
            .clickable(onClick = onClick)
            .padding(Dimensions.polaroidPadding)
    ) {
        // 이미지 영역
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(Dimensions.polaroidImageHeight)
                .clip(RoundedCornerShape(4.dp))
                .background(SandBeige),
            contentAlignment = Alignment.Center
        ) {
            if (imageUrl != null) {
                // TODO: Coil로 이미지 로드
                // AsyncImage(model = imageUrl, ...)

                // 임시: 플레이스홀더
                Image(
                    painter = painterResource(R.drawable.ic_launcher_foreground),
                    contentDescription = "일기 이미지",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            } else {
                // 이미지 없을 때
                Text(
                    text = "🖼️",
                    style = MaterialTheme.typography.displayLarge
                )
            }
        }

        Spacer(modifier = Modifier.height(Spacing.medium))

        // 날짜
        Text(
            text = date,
            style = DateTextStyle,
            color = TextSecondary,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(Spacing.small))

        // 미리보기 텍스트 (첫 줄)
        Text(
            text = previewText,
            style = MaterialTheme.typography.bodyMedium,
            color = TextPrimary,
            textAlign = TextAlign.Center,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

/**
 * 간단한 일기 카드 (목록용)
 * 폴라로이드보다 작고 간단한 버전
 */
@Composable
fun DiarySimpleCard(
    date: String,
    previewText: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    hasStrikeThrough: Boolean = false
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .shadow(2.dp, RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp))
            .background(PolaroidBrown)
            .clickable(onClick = onClick)
            .padding(Spacing.medium),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 날짜 (왼쪽)
        Column(
            modifier = Modifier.width(60.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = date.split(".")[0], // 월
                style = MaterialTheme.typography.labelSmall,
                color = TextSecondary
            )
            Text(
                text = date.split(".").getOrNull(1) ?: "", // 일
                style = MaterialTheme.typography.titleLarge,
                color = TextPrimary
            )
        }

        Spacer(modifier = Modifier.width(Spacing.medium))

        // 내용 미리보기 (오른쪽)
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = previewText,
                style = MaterialTheme.typography.bodyMedium,
                color = TextPrimary,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            if (hasStrikeThrough) {
                Spacer(modifier = Modifier.height(Spacing.extraSmall))
                Text(
                    text = "취소선 포함",
                    style = MaterialTheme.typography.labelSmall,
                    color = StrikeThroughRed
                )
            }
        }
    }
}

// ========================================
// Preview
// ========================================

@Preview(showBackground = true)
@Composable
fun DiaryPolaroidCardPreview() {
    OverwriteTheme {
        DiaryPolaroidCard(
            imageUrl = null,
            date = "2025.12.18",
            previewText = "서랍 속 일기장 대신 덧쓰기로 마음을 쓰다",
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DiarySimpleCardPreview() {
    OverwriteTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            DiarySimpleCard(
                date = "12.18",
                previewText = "오늘 하루는 정말 좋았다. 바다를 보며 걸었는데 발자국이 남았다.",
                onClick = {},
                hasStrikeThrough = true
            )

            DiarySimpleCard(
                date = "12.17",
                previewText = "피곤한 하루였지만 보람찼다.",
                onClick = {}
            )
        }
    }
}