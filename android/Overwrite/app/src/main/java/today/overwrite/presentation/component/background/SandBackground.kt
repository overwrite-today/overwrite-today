package today.overwrite.presentation.component.background

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import today.overwrite.R
import today.overwrite.presentation.theme.OverwriteTheme
import today.overwrite.presentation.theme.SandOverlay

/**
 * 나머지 페이지 배경 (해변 모래)
 *
 * 사용처:
 * - 글 작성 페이지
 * - 글 상세 페이지
 * - 글 목록 페이지
 * - 설정 페이지
 */
@Composable
fun SandBackground(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = {}
) {
    Box(modifier = modifier.fillMaxSize()) {
        // 배경 이미지
        Image(
            painter = painterResource(R.drawable.bg_sand_pages),
            contentDescription = "모래 배경",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // 반투명 오버레이 (선택적)
        // 원고지가 잘 보이도록 아주 약하게
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(SandOverlay)
        )

        // 콘텐츠
        content()
    }
}

// ========================================
// Preview
// ========================================

@Preview(showBackground = true)
@Composable
fun SandBackgroundPreview() {
    OverwriteTheme {
        SandBackground {
            // 작성/상세/목록 화면 내용이 여기에 들어감
        }
    }
}