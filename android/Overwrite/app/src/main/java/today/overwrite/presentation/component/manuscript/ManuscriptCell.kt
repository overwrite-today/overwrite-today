package today.overwrite.presentation.component.manuscript

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import today.overwrite.presentation.theme.Dimensions
import today.overwrite.presentation.theme.InkBlack
import today.overwrite.presentation.theme.ManuscriptBackground
import today.overwrite.presentation.theme.ManuscriptGridLine
import today.overwrite.presentation.theme.OverwriteBlue
import today.overwrite.presentation.theme.OverwriteTheme
import today.overwrite.presentation.theme.StrikeThroughRed

/**
 * 원고지 개별 셀
 *
 * 한 글자를 담는 셀 단위
 * - 일반 텍스트
 * - 취소선 텍스트
 * - 덧쓰기 텍스트
 */
@Composable
fun ManuscriptCell(
    char: Char?,
    modifier: Modifier = Modifier,
    isStrikeThrough: Boolean = false,
    isOverwritten: Boolean = false,
    textStyle: TextStyle = MaterialTheme.typography.bodyLarge,
    textColor: Color = InkBlack,
    strikeThroughColor: Color = StrikeThroughRed,
    overwriteColor: Color = OverwriteBlue
) {
    val cellSize = Dimensions.manuscriptCellSize

    Box(
        modifier = modifier
            .size(cellSize)
            .background(ManuscriptBackground)
            .border(
                width = Dimensions.manuscriptGridLineWidth,
                color = ManuscriptGridLine
            )
            .then(
                // 취소선이 있으면 그리기
                if (isStrikeThrough) {
                    Modifier.drawBehind {
                        drawLine(
                            color = strikeThroughColor,
                            start = Offset(size.width * 0.1f, size.height / 2),
                            end = Offset(size.width * 0.9f, size.height / 2),
                            strokeWidth = 3.dp.toPx()
                        )
                    }
                } else {
                    Modifier
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        if (char != null && char != ' ') {
            Text(
                text = char.toString(),
                style = textStyle.copy(
                    color = when {
                        isOverwritten -> overwriteColor
                        else -> textColor
                    }
                )
            )
        }
    }
}

/**
 * 취소선이 있는 셀
 */
@Composable
fun StrikeThroughCell(
    char: Char?,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.bodyLarge
) {
    ManuscriptCell(
        char = char,
        modifier = modifier,
        isStrikeThrough = true,
        textStyle = textStyle
    )
}

/**
 * 덧쓰기 된 셀 (파란색 텍스트)
 */
@Composable
fun OverwrittenCell(
    char: Char?,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.bodyLarge
) {
    ManuscriptCell(
        char = char,
        modifier = modifier,
        isOverwritten = true,
        textStyle = textStyle
    )
}

/**
 * 취소선 + 덧쓰기 조합 셀
 * (취소선 위에 새 글자가 덧쓰기된 경우)
 */
@Composable
fun StrikeThroughWithOverwriteCell(
    originalChar: Char?,
    overwrittenChar: Char?,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.bodyLarge
) {
    val cellSize = Dimensions.manuscriptCellSize

    Box(
        modifier = modifier
            .size(cellSize)
            .background(ManuscriptBackground)
            .border(
                width = Dimensions.manuscriptGridLineWidth,
                color = ManuscriptGridLine
            )
            .drawBehind {
                // 취소선 그리기
                drawLine(
                    color = StrikeThroughRed,
                    start = Offset(size.width * 0.1f, size.height / 2),
                    end = Offset(size.width * 0.9f, size.height / 2),
                    strokeWidth = 3.dp.toPx()
                )
            },
        contentAlignment = Alignment.Center
    ) {
        // 취소선 위에 덧쓰기된 글자 표시 (파란색)
        if (overwrittenChar != null && overwrittenChar != ' ') {
            Text(
                text = overwrittenChar.toString(),
                style = textStyle.copy(color = OverwriteBlue)
            )
        }
        // 원래 글자는 취소선으로만 표시 (글자는 안 보임)
    }
}

// ========================================
// Preview
// ========================================

@Preview(showBackground = true)
@Composable
fun ManuscriptCellPreview() {
    OverwriteTheme {
        ManuscriptCell(char = '가')
    }
}

@Preview(showBackground = true)
@Composable
fun StrikeThroughCellPreview() {
    OverwriteTheme {
        StrikeThroughCell(char = '나')
    }
}

@Preview(showBackground = true)
@Composable
fun OverwrittenCellPreview() {
    OverwriteTheme {
        OverwrittenCell(char = '다')
    }
}

@Preview(showBackground = true)
@Composable
fun StrikeThroughWithOverwriteCellPreview() {
    OverwriteTheme {
        StrikeThroughWithOverwriteCell(
            originalChar = '라',
            overwrittenChar = '마'
        )
    }
}