package today.overwrite.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import today.overwrite.R

/**
 * 덧쓰기 앱 타이포그래피 시스템
 *
 * KCC 임권택체: 손글씨 느낌의 따뜻한 폰트
 * - 일기 작성 및 모든 UI에 사용
 * - MVP에서는 단일 Weight(Regular)만 사용하여 일관성 유지
 */

// ========================================
// 폰트 패밀리 정의
// ========================================

/**
 * KCC 임권택체 (Regular only)
 *
 * Note: Bold, Thin 등이 없으므로 Regular만 사용
 * - 제목/본문 구분은 fontSize로만 처리
 * - 손글씨 특성상 단일 굵기가 더 자연스러움
 */
val ImkwontaekFont = FontFamily(
    Font(R.font.kcc_imkwontaek, FontWeight.Normal)
)


// ========================================
// 타이포그래피 스타일
// ========================================

val Typography = Typography(
    // ===== 일기 작성 영역 =====

    /** 원고지 본문 - 일기 작성 텍스트 */
    bodyLarge = TextStyle(
        fontFamily = ImkwontaekFont,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        lineHeight = 32.sp,  // 원고지 셀 크기(32dp)에 맞춤
        letterSpacing = 0.sp
    ),


    // ===== UI 텍스트 (제목, 버튼 등) =====

    /** 메인 타이틀 - "흔적" 로고 등 */
    headlineLarge = TextStyle(
        fontFamily = ImkwontaekFont,
        fontWeight = FontWeight.Normal,  // Bold 없으므로 Normal 사용
        fontSize = 36.sp,
        lineHeight = 44.sp,
        letterSpacing = 0.sp
    ),

    /** 화면 제목 - 각 페이지 헤더 */
    headlineMedium = TextStyle(
        fontFamily = ImkwontaekFont,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.sp
    ),

    /** 섹션 제목 */
    headlineSmall = TextStyle(
        fontFamily = ImkwontaekFont,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    ),

    /** 카드 제목 - 폴라로이드 타이틀 등 */
    titleLarge = TextStyle(
        fontFamily = ImkwontaekFont,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),

    /** 리스트 아이템 제목 */
    titleMedium = TextStyle(
        fontFamily = ImkwontaekFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),

    /** 작은 제목 */
    titleSmall = TextStyle(
        fontFamily = ImkwontaekFont,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    ),

    /** 일반 본문 텍스트 */
    bodyMedium = TextStyle(
        fontFamily = ImkwontaekFont,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 22.sp,
        letterSpacing = 0.sp
    ),

    /** 작은 본문 텍스트 */
    bodySmall = TextStyle(
        fontFamily = ImkwontaekFont,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.sp
    ),

    /** 버튼 텍스트 */
    labelLarge = TextStyle(
        fontFamily = ImkwontaekFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),

    /** 작은 버튼/탭 텍스트 */
    labelMedium = TextStyle(
        fontFamily = ImkwontaekFont,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    ),

    /** 아주 작은 레이블 */
    labelSmall = TextStyle(
        fontFamily = ImkwontaekFont,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.sp
    )
)


// ========================================
// 커스텀 텍스트 스타일 (특수 용도)
// ========================================

/** 날짜 표시용 (폴라로이드 하단 등) */
val DateTextStyle = TextStyle(
    fontFamily = ImkwontaekFont,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    lineHeight = 20.sp,
    letterSpacing = 0.sp
)

/** 페이지 번호 표시용 */
val PageNumberStyle = TextStyle(
    fontFamily = ImkwontaekFont,
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp,
    lineHeight = 16.sp,
    letterSpacing = 0.sp
)

/** 타임라인 월 표시용 */
val TimelineMonthStyle = TextStyle(
    fontFamily = ImkwontaekFont,
    fontWeight = FontWeight.Normal,
    fontSize = 18.sp,
    lineHeight = 24.sp,
    letterSpacing = 0.sp
)

/** 인용/강조 텍스트 (크기는 동일하지만 색상으로 구분) */
val QuoteTextStyle = TextStyle(
    fontFamily = ImkwontaekFont,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp,
    lineHeight = 24.sp,
    letterSpacing = 0.sp
)