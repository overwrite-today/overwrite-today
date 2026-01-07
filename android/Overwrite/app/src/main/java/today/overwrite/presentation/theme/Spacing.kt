package today.overwrite.presentation.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * 앱 전체에서 사용하는 일관된 간격 시스템
 *
 * 4dp 기반 시스템 (4, 8, 12, 16, 24, 32, 48, 64...)
 */
object Spacing {

    /** 아주 작은 간격 (4dp) */
    val extraSmall: Dp = 4.dp

    /** 작은 간격 (8dp) */
    val small: Dp = 8.dp

    /** 중간-작은 간격 (12dp) */
    val mediumSmall: Dp = 12.dp

    /** 중간 간격 (16dp) */
    val medium: Dp = 16.dp

    /** 중간-큰 간격 (20dp) */
    val mediumLarge: Dp = 20.dp

    /** 큰 간격 (24dp) */
    val large: Dp = 24.dp

    /** 아주 큰 간격 (32dp) */
    val extraLarge: Dp = 32.dp

    /** 특대 간격 (48dp) */
    val huge: Dp = 48.dp

    /** 초대형 간격 (64dp) */
    val massive: Dp = 64.dp


    // ========================================
    // 특정 용도별 간격
    // ========================================

    /** 화면 가장자리 패딩 */
    val screenPadding: Dp = 20.dp

    /** 카드 간 간격 */
    val cardSpacing: Dp = 16.dp

    /** 섹션 간 간격 */
    val sectionSpacing: Dp = 32.dp

    /** 아이템 간 간격 (리스트) */
    val itemSpacing: Dp = 12.dp

    /** 버튼 내부 패딩 */
    val buttonPadding: Dp = 16.dp

    /** 작은 버튼 내부 패딩 */
    val smallButtonPadding: Dp = 12.dp

    /** 텍스트 필드 패딩 */
    val textFieldPadding: Dp = 16.dp
}