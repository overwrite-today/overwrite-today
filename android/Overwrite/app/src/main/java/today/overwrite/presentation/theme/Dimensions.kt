package today.overwrite.presentation.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * 원고지 및 UI 관련 크기 정의
 */
object Dimensions {

    // ========================================
    // 원고지 (Manuscript) 크기
    // ========================================

    /** 원고지 한 셀의 크기 */
    val manuscriptCellSize: Dp = 32.dp

    /** 원고지 셀 간격 (그리드 라인 두께) */
    val manuscriptGridLineWidth: Dp = 1.dp

    /** 원고지 한 줄당 글자 수 */
    const val MANUSCRIPT_CHARS_PER_LINE = 10

    /** 원고지 한 페이지당 줄 수 */
    const val MANUSCRIPT_LINES_PER_PAGE = 13

    /** 원고지 한 페이지당 총 글자 수 */
    const val MANUSCRIPT_CHARS_PER_PAGE = MANUSCRIPT_CHARS_PER_LINE * MANUSCRIPT_LINES_PER_PAGE // 130자

    /** 원고지 전체 너비 */
    val manuscriptWidth: Dp = manuscriptCellSize * MANUSCRIPT_CHARS_PER_LINE

    /** 원고지 전체 높이 */
    val manuscriptHeight: Dp = manuscriptCellSize * MANUSCRIPT_LINES_PER_PAGE

    /** 원고지 패딩 (원고지 주변 여백) */
    val manuscriptPadding: Dp = 24.dp

    /** 원고지 모서리 둥글기 */
    val manuscriptCornerRadius: Dp = 8.dp


    // ========================================
    // 일기 카드 (Diary Card) 크기
    // ========================================

    /** 폴라로이드 카드 너비 */
    val polaroidCardWidth: Dp = 280.dp

    /** 폴라로이드 카드 높이 */
    val polaroidCardHeight: Dp = 320.dp

    /** 폴라로이드 이미지 영역 높이 */
    val polaroidImageHeight: Dp = 240.dp

    /** 폴라로이드 텍스트 영역 높이 */
    val polaroidTextHeight: Dp = 80.dp

    /** 폴라로이드 패딩 */
    val polaroidPadding: Dp = 16.dp

    /** 폴라로이드 그림자 */
    val polaroidElevation: Dp = 4.dp


    // ========================================
    // 재생 컨트롤 (Playback Controls)
    // ========================================

    /** 재생 버튼 크기 */
    val playbackButtonSize: Dp = 48.dp

    /** 재생 컨트롤 바 높이 */
    val playbackControlHeight: Dp = 64.dp

    /** 재생 속도 버튼 크기 */
    val playbackSpeedButtonSize: Dp = 40.dp


    // ========================================
    // 타임라인 (Timeline)
    // ========================================

    /** 월별 타임라인 항목 높이 */
    val timelineItemHeight: Dp = 80.dp

    /** 타임라인 발자국 크기 */
    val timelineFootprintSize: Dp = 32.dp

    /** 타임라인 간격 */
    val timelineSpacing: Dp = 16.dp
}