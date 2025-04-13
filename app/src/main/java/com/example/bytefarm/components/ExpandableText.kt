package com.example.vsgarments.view_functions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.example.vsgarments.ui.theme.textcolorblue
import com.example.vsgarments.ui.theme.tintGrey

@Composable
fun ExpandableText(item: String, textColor: Color) {
    var isExpanded by remember { mutableStateOf(false) }

    Text(
        buildAnnotatedString {
            if (!isExpanded && item.length > 200) {
                append(item.take(200))
                append("... ")
                withStyle(style = SpanStyle(color = textcolorblue, textDecoration = TextDecoration.Underline)) {
                    append("More")
                }
            } else {
                append(item)
            }
        },
        color = textColor,
//        maxLines = if (isExpanded) Int.MAX_VALUE else 8,
//        overflow = TextOverflow.Ellipsis,
        modifier = Modifier.clickable {
            if (!isExpanded) isExpanded = true
        }
    )
}

