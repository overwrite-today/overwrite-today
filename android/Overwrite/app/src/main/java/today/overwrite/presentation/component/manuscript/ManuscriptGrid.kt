package today.overwrite.presentation.component.manuscript

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.tooling.preview.Preview
import today.overwrite.presentation.theme.Dimensions
import today.overwrite.presentation.theme.ManuscriptBackground
import today.overwrite.presentation.theme.ManuscriptGridLine
import today.overwrite.presentation.theme.OverwriteTheme

/**
 * 원고지 그리드 컴포넌트
 *
 * 한국식 원고지 스타일의 그리드를 Canvas로 그립니다.
 * - 10열 x 13행 (130자)
 * - 각 셀: 32dp x 32dp
 */
@Composable
fun ManuscriptGrid(
    modifier: Modifier = Modifier,
    showVerticalLines: Boolean = true,
    showHorizontalLines: Boolean = true,
    gridLineColor: Color = ManuscriptGridLine,
    backgroundColor: Color = ManuscriptBackground
) {
    val cellSize = Dimensions.manuscriptCellSize
    val lineWidth = Dimensions.manuscriptGridLineWidth
    val cols = Dimensions.MANUSCRIPT_CHARS_PER_LINE
    val rows = Dimensions.MANUSCRIPT_LINES_PER_PAGE

    Canvas(
        modifier = modifier
            .size(
                width = cellSize * cols,
                height = cellSize * rows
            )
    ) {
        // 배경 그리기
        drawRect(
            color = backgroundColor,
            size = size
        )

        // 세로선 그리기 (열 구분선)
        if (showVerticalLines) {
            for (i in 0..cols) {
                val x = i * cellSize.toPx()
                drawLine(
                    color = gridLineColor,
                    start = Offset(x, 0f),
                    end = Offset(x, size.height),
                    strokeWidth = lineWidth.toPx()
                )
            }
        }

        // 가로선 그리기 (행 구분선)
        if (showHorizontalLines) {
            for (i in 0..rows) {
                val y = i * cellSize.toPx()
                drawLine(
                    color = gridLineColor,
                    start = Offset(0f, y),
                    end = Offset(size.width, y),
                    strokeWidth = lineWidth.toPx()
                )
            }
        }

        // 5열마다 강조선 그리기 (가독성 향상)
        if (showVerticalLines) {
            for (i in 0..cols step 5) {
                if (i == 0 || i == cols) continue  // 테두리는 이미 그렸음

                val x = i * cellSize.toPx()
                drawLine(
                    color = gridLineColor.copy(alpha = 0.6f),
                    start = Offset(x, 0f),
                    end = Offset(x, size.height),
                    strokeWidth = (lineWidth * 1.5f).toPx()
                )
            }
        }
    }
}

/**
 * 점선 스타일 원고지 그리드 (선택적 사용)
 */
@Composable
fun ManuscriptGridDashed(
    modifier: Modifier = Modifier,
    gridLineColor: Color = ManuscriptGridLine,
    backgroundColor: Color = ManuscriptBackground
) {
    val cellSize = Dimensions.manuscriptCellSize
    val lineWidth = Dimensions.manuscriptGridLineWidth
    val cols = Dimensions.MANUSCRIPT_CHARS_PER_LINE
    val rows = Dimensions.MANUSCRIPT_LINES_PER_PAGE

    Canvas(
        modifier = modifier
            .size(
                width = cellSize * cols,
                height = cellSize * rows
            )
    ) {
        // 배경
        drawRect(
            color = backgroundColor,
            size = size
        )

        // 점선 효과
        val dashEffect = PathEffect.dashPathEffect(
            intervals = floatArrayOf(10f, 5f),
            phase = 0f
        )

        // 세로 점선
        for (i in 1 until cols) {
            val x = i * cellSize.toPx()
            drawLine(
                color = gridLineColor.copy(alpha = 0.5f),
                start = Offset(x, 0f),
                end = Offset(x, size.height),
                strokeWidth = lineWidth.toPx(),
                pathEffect = dashEffect
            )
        }

        // 가로 점선
        for (i in 1 until rows) {
            val y = i * cellSize.toPx()
            drawLine(
                color = gridLineColor.copy(alpha = 0.5f),
                start = Offset(0f, y),
                end = Offset(size.width, y),
                strokeWidth = lineWidth.toPx(),
                pathEffect = dashEffect
            )
        }

        // 테두리는 실선
        drawRect(
            color = gridLineColor,
            size = size,
            style = androidx.compose.ui.graphics.drawscope.Stroke(
                width = (lineWidth * 2).toPx()
            )
        )
    }
}

// ========================================
// Preview
// ========================================

@Preview(showBackground = true)
@Composable
fun ManuscriptGridPreview() {
    OverwriteTheme {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            ManuscriptGrid()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ManuscriptGridDashedPreview() {
    OverwriteTheme {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            ManuscriptGridDashed()
        }
    }
}