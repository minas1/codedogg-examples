package com.example.ui

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui.theme.AppTheme

@Composable
fun RoundedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    Button(
        onClick = onClick,
        content = content,
        modifier = modifier.clip(RoundedCornerShape(4.dp))
    )
}

@Preview
@Composable
fun RoundedButtonPreview() = AppTheme {
    RoundedButton(onClick = {}) {
        Text(text = "Click me!")
    }
}