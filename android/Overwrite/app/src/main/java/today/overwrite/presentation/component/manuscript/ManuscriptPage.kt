package today.overwrite.presentation.component.manuscript

import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import today.overwrite.presentation.theme.*

/**
 * 원고지 페이지 컴포넌트 (Perfect Alignment)
 *
 * 각 글자를 셀 정중앙에 정확히 배치
 */
@Composable
fun ManuscriptPage(
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

            // 글자들을 셀에 배치
            if (!readOnly) {
                // 입력 모드
                ManuscriptTextInput(
                    text = text,
                    onTextChange = onTextChange,
                    maxChars = maxChars,
                    cellSize = cellSize,
                    cols = cols,
                    rows = rows
                )
            } else {
                // 읽기 전용 모드
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
 * 텍스트 입력 레이어
 * - 투명한 BasicTextField로 입력 받음
 * - 실제 글자는 ManuscriptTextGrid로 렌더링
 */
@Composable
private fun ManuscriptTextInput(
    text: String,
    onTextChange: (String) -> Unit,
    maxChars: Int,
    cellSize: androidx.compose.ui.unit.Dp,
    cols: Int,
    rows: Int
) {
    Box(modifier = Modifier.fillMaxSize()) {
        // 보이지 않는 TextField (실제 입력만 받음)
        BasicTextField(
            value = text,
            onValueChange = { newText ->
                // 개행 문자 제거 및 최대 글자 수 제한
                val filteredText = newText.replace("\n", "")
                if (filteredText.length <= maxChars) {
                    onTextChange(filteredText)
                }
            },
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent),
            textStyle = TextStyle(
                color = Color.Transparent,  // 투명
                fontSize = 1.sp  // 최소 크기
            ),
            cursorBrush = SolidColor(InkBlack),
            decorationBox = { innerTextField ->
                Box(modifier = Modifier.fillMaxSize()) {
                    // 커서만 보임
                    innerTextField()
                }
            }
        )

        // 실제로 보이는 글자들
        ManuscriptTextGrid(
            text = text,
            cellSize = cellSize,
            cols = cols,
            rows = rows
        )
    }
}

/**
 * 텍스트를 그리드 셀에 정확히 배치
 *
 * 핵심: Box로 각 셀을 만들고 그 안에서 Text를 중앙 정렬
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

                // 각 셀을 Box로 만들어서 정중앙 정렬
                Box(
                    modifier = Modifier
                        .offset(
                            x = cellSize * col,
                            y = cellSize * row
                        )
                        .size(cellSize),
                    contentAlignment = Alignment.Center  // 핵심!
                ) {
                    Text(
                        text = char.toString(),
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = InkBlack,
                            fontSize = 18.sp,
                            lineHeight = 18.sp,  // lineHeight를 fontSize와 동일하게
                            textAlign = TextAlign.Center
                        ),
                        maxLines = 1
                    )
                }
            }
        }
    }
}

/**
 * 여러 페이지를 스와이프로 넘기는 페이저 (향후 구현)
 */
@Composable
fun ManuscriptPager(
    pages: List<String>,
    onPageTextChange: (pageIndex: Int, text: String) -> Unit,
    modifier: Modifier = Modifier,
    currentPage: Int = 0,
    onPageChange: (Int) -> Unit = {}
) {
    // TODO: HorizontalPager 구현
    // 지금은 단일 페이지만 표시

    if (pages.isNotEmpty() && currentPage < pages.size) {
        ManuscriptPage(
            text = pages[currentPage],
            onTextChange = { newText ->
                onPageTextChange(currentPage, newText)
            },
            modifier = modifier,
            pageNumber = currentPage + 1
        )
    }
}

/**
 * 읽기 전용 원고지 페이지 (상세보기용)
 */
@Composable
fun ManuscriptPageReadOnly(
    text: String,
    modifier: Modifier = Modifier,
    pageNumber: Int = 1
) {
    ManuscriptPage(
        text = text,
        onTextChange = {},
        modifier = modifier,
        pageNumber = pageNumber,
        readOnly = true
    )
}

// ========================================
// Preview
// ========================================

@Preview(showBackground = true)
@Composable
fun ManuscriptPagePreview() {
    OverwriteTheme {
        var text by remember { mutableStateOf("오늘 하루는 정말 좋았다. 모래 해변에 발자국을 남겼기 때문이다.") }

        ManuscriptPage(
            text = text,
            onTextChange = { text = it },
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ManuscriptPageReadOnlyPreview() {
    OverwriteTheme {
        ManuscriptPageReadOnly(
            text = "읽기전용모드수정할수없습니다가나다라마바사아자차카타파하",
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true, name = "글자 배치 테스트")
@Composable
fun ManuscriptPageAlignmentTestPreview() {
    OverwriteTheme {
        var text by remember { mutableStateOf("가나다라마바사아자차") }

        Column(modifier = Modifier.padding(16.dp)) {
            ManuscriptPage(
                text = text,
                onTextChange = { text = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "셀 정중앙에 정확히 배치되었는지 확인",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}