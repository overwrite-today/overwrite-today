package today.overwrite.presentation.component.manuscript

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import today.overwrite.presentation.theme.*

/**
 * 원고지 페이지 컴포넌트 (커서 위치 표시 포함)
 */
@Composable
fun ManuscriptPageWithCursor(
    text: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    pageNumber: Int = 1,
    maxChars: Int = Dimensions.MANUSCRIPT_CHARS_PER_PAGE,
    readOnly: Boolean = false
) {
    val cellSize = Dimensions.manuscriptCellSize
    val cols = Dimensions.MANUSCRIPT_CHARS_PER_LINE
    val rows = Dimensions.MANUSCRIPT_LINES_PER_PAGE

    var isFocused by remember { mutableStateOf(false) }
    val cursorPosition = text.length  // 커서는 항상 마지막

    Column(
        modifier = modifier
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(Dimensions.manuscriptCornerRadius)
            )
            .clip(RoundedCornerShape(Dimensions.manuscriptCornerRadius))
            .background(ManuscriptBackground)
            .padding(Dimensions.manuscriptPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.size(
                width = cellSize * cols,
                height = cellSize * rows
            )
        ) {
            // 배경 그리드
            ManuscriptGrid()

            // 커서 위치 하이라이트 (포커스 시)
            if (!readOnly && isFocused && cursorPosition < maxChars) {
                CursorCell(
                    position = cursorPosition,
                    cellSize = cellSize,
                    cols = cols
                )
            }

            // 글자들을 셀에 배치
            if (!readOnly) {
                Box(modifier = Modifier.fillMaxSize()) {
                    // 투명한 TextField
                    BasicTextField(
                        value = text,
                        onValueChange = { newText ->
                            val filteredText = newText.replace("\n", "")
                            if (filteredText.length <= maxChars) {
                                onTextChange(filteredText)
                            }
                        },
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Transparent)
                            .onFocusChanged { focusState ->
                                isFocused = focusState.isFocused
                            },
                        textStyle = TextStyle(
                            color = Color.Transparent,
                            fontSize = 1.sp
                        ),
                        cursorBrush = SolidColor(Color.Transparent),  // 기본 커서 숨김
                        decorationBox = { innerTextField ->
                            Box(modifier = Modifier.fillMaxSize()) {
                                innerTextField()
                            }
                        }
                    )

                    // 실제 글자들
                    ManuscriptTextGrid(
                        text = text,
                        cellSize = cellSize,
                        cols = cols,
                        rows = rows
                    )
                }
            } else {
                ManuscriptTextGrid(
                    text = text,
                    cellSize = cellSize,
                    cols = cols,
                    rows = rows
                )
            }
        }

        // 페이지 번호
        Text(
            text = "$pageNumber / 13",
            style = PageNumberStyle,
            color = TextSecondary,
            modifier = Modifier.padding(top = Spacing.small)
        )
    }
}

/**
 * 커서 위치를 표시하는 셀 하이라이트
 */
@Composable
private fun CursorCell(
    position: Int,
    cellSize: androidx.compose.ui.unit.Dp,
    cols: Int
) {
    val col = position % cols
    val row = position / cols

    // 깜빡이는 애니메이션
    val infiniteTransition = rememberInfiniteTransition(label = "cursor")
    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 0.8f,
        animationSpec = infiniteRepeatable(
            animation = tween(500),
            repeatMode = RepeatMode.Reverse
        ),
        label = "cursor_alpha"
    )

    Box(
        modifier = Modifier
            .offset(
                x = cellSize * col,
                y = cellSize * row
            )
            .size(cellSize)
            .border(
                width = 2.dp,
                color = OceanBlue.copy(alpha = alpha),
                shape = RoundedCornerShape(4.dp)
            )
            .background(
                color = OceanBlue.copy(alpha = alpha * 0.1f),
                shape = RoundedCornerShape(4.dp)
            )
    )
}

/**
 * 텍스트를 그리드 셀에 정확히 배치
 */
@Composable
private fun ManuscriptTextGrid(
    text: String,
    cellSize: androidx.compose.ui.unit.Dp,
    cols: Int,
    rows: Int
) {
    Box(modifier = Modifier.fillMaxSize()) {
        text.forEachIndexed { index, char ->
            if (index < cols * rows) {
                val col = index % cols
                val row = index / cols

                Box(
                    modifier = Modifier
                        .offset(
                            x = cellSize * col,
                            y = cellSize * row
                        )
                        .size(cellSize),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = char.toString(),
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = InkBlack,
                            fontSize = 18.sp,
                            lineHeight = 18.sp,
                            textAlign = TextAlign.Center
                        ),
                        maxLines = 1
                    )
                }
            }
        }
    }
}

// ========================================
// Preview
// ========================================

@Preview(showBackground = true, name = "커서 표시 포함")
@Composable
fun ManuscriptPageWithCursorPreview() {
    OverwriteTheme {
        var text by remember { mutableStateOf("오늘하루는정말좋았다") }

        ManuscriptPageWithCursor(
            text = text,
            onTextChange = { text = it },
            modifier = Modifier.padding(16.dp)
        )
    }
}