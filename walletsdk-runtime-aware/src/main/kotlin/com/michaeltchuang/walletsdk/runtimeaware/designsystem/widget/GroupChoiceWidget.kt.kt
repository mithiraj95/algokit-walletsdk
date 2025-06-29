package com.michaeltchuang.walletsdk.runtimeaware.designsystem.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.michaeltchuang.walletsdk.runtimeaware.R
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.theme.AlgoKitTheme


@SuppressWarnings("LongMethod")
@Composable
fun GroupChoiceWidget(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    icon: ImageVector,
    iconContentDescription: String,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .clickable { onClick() }
            .padding(horizontal = 24.dp)
            .fillMaxWidth()
            .padding(vertical = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(AlgoKitTheme.colors.layerGrayLighter)
                .padding(8.dp),
            imageVector = icon,
            contentDescription = iconContentDescription,
            tint = AlgoKitTheme.colors.textMain
        )
        Spacer(modifier = Modifier.width(24.dp))
        Column {
            Text(
                style = AlgoKitTheme.typography.body.regular.sansMedium,
                color = AlgoKitTheme.colors.textMain,
                text = title
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                style = AlgoKitTheme.typography.footnote.sans,
                color = AlgoKitTheme.colors.textMain,
                text = description
            )
        }
    }
}

@PreviewLightDark
@Composable
fun GroupChoiceWidgetPreview() {
    GroupChoiceWidget(
        title = stringResource(id = R.string.import_an_account),
        description = stringResource(id = R.string.import_an_existing),
        iconContentDescription = stringResource(id = R.string.import_an_existing),
        icon = ImageVector.vectorResource(R.drawable.ic_key),
        onClick = { },
    )
}
