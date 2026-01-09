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
import today.overwrite.presentation.theme.BeachOverlay
import today.overwrite.presentation.theme.OverwriteTheme

/**
 * 메인 페이지 배경 (바다 배경의 해변)
 *
 * 사용처:
 * - 메인 화면 (홈)
 * - 스플래시 화면
 */
@Composable
fun BeachBackground(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = {}
) {
    Box(modifier = modifier.fillMaxSize()) {
        // 배경 이미지
        Image(
            painter = painterResource(R.drawable.bg_beach_main),
            contentDescription = "해변 배경",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // 반투명 오버레이 (선택적)
        // 텍스트 가독성을 위해 약간 어둡게 처리
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BeachOverlay)
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
fun BeachBackgroundPreview() {
    OverwriteTheme {
        BeachBackground {
            // 메인 화면 내용이 여기에 들어감
        }
    }
}