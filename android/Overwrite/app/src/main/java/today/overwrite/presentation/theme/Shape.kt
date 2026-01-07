package today.overwrite.presentation.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

/**
 * 앱 전체에서 사용하는 도형 스타일
 */
object AppShapes {

    /** 모서리 없음 (직사각형) */
    val none: Shape = RoundedCornerShape(0.dp)

    /** 아주 작은 둥근 모서리 (4dp) */
    val extraSmall: Shape = RoundedCornerShape(4.dp)

    /** 작은 둥근 모서리 (8dp) */
    val small: Shape = RoundedCornerShape(8.dp)

    /** 중간 둥근 모서리 (12dp) */
    val medium: Shape = RoundedCornerShape(12.dp)

    /** 큰 둥근 모서리 (16dp) */
    val large: Shape = RoundedCornerShape(16.dp)

    /** 아주 큰 둥근 모서리 (24dp) */
    val extraLarge: Shape = RoundedCornerShape(24.dp)

    /** 완전히 둥근 모서리 (50%) */
    val full: Shape = RoundedCornerShape(50)


    // ========================================
    // 특정 용도별 Shape
    // ========================================

    /** 원고지 모서리 */
    val manuscript: Shape = RoundedCornerShape(8.dp)

    /** 폴라로이드 카드 모서리 */
    val polaroidCard: Shape = RoundedCornerShape(4.dp)

    /** 버튼 모서리 */
    val button: Shape = RoundedCornerShape(12.dp)

    /** 작은 버튼 모서리 */
    val smallButton: Shape = RoundedCornerShape(8.dp)

    /** 다이얼로그 모서리 */
    val dialog: Shape = RoundedCornerShape(16.dp)

    /** 바텀 시트 모서리 (위쪽만) */
    val bottomSheet: Shape = RoundedCornerShape(
        topStart = 16.dp,
        topEnd = 16.dp,
        bottomStart = 0.dp,
        bottomEnd = 0.dp
    )
}