package com.michaeltchuang.walletsdk.runtimeaware.account.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.final_class.webview_multiplatform_mobile.webview.WebViewPlatform
import com.final_class.webview_multiplatform_mobile.webview.controller.rememberWebViewController
import com.michaeltchuang.walletsdk.runtimeaware.R
import com.michaeltchuang.walletsdk.runtimeaware.account.ui.components.OnBoardingScreens
import com.michaeltchuang.walletsdk.runtimeaware.account.ui.viewmodel.OnboardingAccountTypeViewModel
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.theme.AlgoKitTheme
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.theme.AlgoKitTheme.typography
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.widget.GroupChoiceWidget
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.widget.icon.PeraIcon
import com.michaeltchuang.walletsdk.runtimeaware.utils.WalletSdkConstants
import com.michaeltchuang.walletsdk.runtimeaware.utils.WalletSdkConstants.PRIVACY_POLICY_URL
import com.michaeltchuang.walletsdk.runtimeaware.utils.WalletSdkConstants.TERMS_AND_SERVICES_URL
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun CreateAccountTypeScreen(navController: NavHostController, onClick: (message: String) -> Unit) {

    val viewModel: OnboardingAccountTypeViewModel = koinViewModel()
    val scope = rememberCoroutineScope()
    val viewState = viewModel.state.collectAsStateWithLifecycle().value
    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect {
            when (it) {
                is OnboardingAccountTypeViewModel.ViewEvent.AccountCreated -> {
                    navController.currentBackStackEntry
                        ?.savedStateHandle
                        ?.set("accountCreation", it.accountCreation)
                    navController.navigate(OnBoardingScreens.CREATE_ACCOUNT_NAME.name)
                    Log.d("CreateAccountTypeScreen", it.accountCreation.address)
                }

                is OnboardingAccountTypeViewModel.ViewEvent.Error -> {
                    Log.d("CreateAccountTypeScreen", it.message)
                }
            }
        }
    }
    when (viewState) {
        is OnboardingAccountTypeViewModel.ViewState.Idle -> {}
        is OnboardingAccountTypeViewModel.ViewState.Loading -> LoadingState()
        is OnboardingAccountTypeViewModel.ViewState.Content -> ContentState(
            isHasAnySeed = viewState.hasAnySeed,
            viewModel = viewModel,
            scope = scope,
            navController = navController,
            onClick = onClick
        )
    }
}

@Composable
private fun ContentState(
    isHasAnySeed: Boolean,
    viewModel: OnboardingAccountTypeViewModel,
    scope: CoroutineScope,
    navController: NavHostController,
    onClick: (message: String) -> Unit
) {
    Column(
        modifier = Modifier
            .background(color = AlgoKitTheme.colors.background)
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                style = typography.title.regular.sansMedium,
                color = AlgoKitTheme.colors.textMain,
                text = stringResource(
                    R.string.welcome_to_pera
                )
            )
            Spacer(modifier = Modifier.weight(1f))
            PeraIcon(
                painter = painterResource(R.drawable.pera_icon_3d),
                contentDescription = stringResource(id = R.string.add_a_wallet_or_account)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        if (isHasAnySeed)
            CreateNewAccountCard {
                navController.navigate(OnBoardingScreens.HD_WALLET_SELECTION_SCREEN.name)
            }
        Spacer(modifier = Modifier.height(20.dp))
        CreateWalletHdWidget(viewModel, scope)
        ImportHdWalletWidget(navController)
        WatchAddressWidget(onClick)
        Spacer(modifier = Modifier.weight(1f))
        TermsAndPrivacy()
    }
}

@Composable
private fun CreateWalletHdWidget(
    viewModel: OnboardingAccountTypeViewModel,
    scope: CoroutineScope,
) {
    GroupChoiceWidget(
        title = stringResource(id = R.string.create_a_new_account),
        description = stringResource(id = R.string.create_a_new_algorand_account_with),
        icon = ImageVector.vectorResource(R.drawable.ic_wallet),
        iconContentDescription = stringResource(id = R.string.create_a_new_algorand_account_with),
        onClick = {
            scope.launch {
                viewModel.createHdKeyAccount()
            }
        }
    )
}

@Composable
private fun ImportHdWalletWidget(navController: NavController) {
    GroupChoiceWidget(
        title = stringResource(id = R.string.import_an_account),
        description = stringResource(id = R.string.import_an_existing),
        iconContentDescription = stringResource(id = R.string.import_an_existing),
        icon = ImageVector.vectorResource(R.drawable.ic_key),
        onClick = { navController.navigate(OnBoardingScreens.ACCOUNT_RECOVERY_TYPE_SCREEN.name) }
    )
}

@Suppress("LongMethod")
@Composable
fun CreateNewAccountCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    val icon = ImageVector.vectorResource(id = R.drawable.ic_hd_wallet)
    val outlineColor = AlgoKitTheme.colors.wallet3IconGovernor
    val dashEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)

    Box(
        modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .pointerInput(Unit) { detectTapGestures(onTap = { onClick() }) }
            .drawBehind {
                drawRoundRect(
                    color = outlineColor,
                    size = Size(size.width, size.height),
                    cornerRadius = CornerRadius(12.dp.toPx(), 12.dp.toPx()),
                    style = Stroke(width = 2.dp.toPx(), pathEffect = dashEffect)
                )
            }
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(AlgoKitTheme.colors.layerGrayLighter)
                    .padding(8.dp),
                imageVector = icon,
                contentDescription = stringResource(id = R.string.add_a_new_account_desc),
                tint = AlgoKitTheme.colors.textMain
            )

            Spacer(Modifier.width(24.dp))
            Column {
                Text(
                    style = typography.body.regular.sansMedium,
                    color = AlgoKitTheme.colors.textMain,
                    text = stringResource(id = R.string.add_a_new_account)
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    style = typography.footnote.sans,
                    color = AlgoKitTheme.colors.textGray,
                    text = stringResource(id = R.string.add_a_new_account_desc)
                )
            }
        }

        Row(
            Modifier
                .align(Alignment.TopCenter)
                .offset(y = (-12).dp)
                .background(AlgoKitTheme.colors.background)
                .padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            PeraIcon(
                painter = painterResource(id = R.drawable.ic_info),
                contentDescription = stringResource(id = R.string.warning),
                modifier = Modifier.size(20.dp),
                tintColor = AlgoKitTheme.colors.wallet3IconGovernor
            )
            Spacer(Modifier.width(4.dp))
            Text(
                text = stringResource(id = R.string.because_you_have_already),
                style = typography.footnote.sansMedium,
                color = AlgoKitTheme.colors.wallet3IconGovernor
            )
        }
    }
}

@Composable
private fun WatchAddressWidget(onClick: (message: String) -> Unit) {
    GroupChoiceWidget(
        title = stringResource(id = R.string.watch_an_address),
        description = stringResource(id = R.string.monitor_an_algorand_address),
        iconContentDescription = stringResource(id = R.string.monitor_an_algorand_address),
        icon = ImageVector.vectorResource(R.drawable.ic_eye),
        onClick = { onClick(WalletSdkConstants.FEATURE_NOT_SUPPORTED_YET) }
    )
}

@Composable
fun TermsAndPrivacy(modifier: Modifier = Modifier) {
    val layoutResult = remember {
        mutableStateOf<TextLayoutResult?>(null)
    }
    val annotatedString = createAnnotatedString()
    val webViewController by rememberWebViewController()
    WebViewPlatform(webViewController = webViewController)
    Text(
        style = typography.footnote.sans,
        color = AlgoKitTheme.colors.textGray,
        modifier = modifier
            .pointerInput(annotatedString) {
                detectTapGestures { pos ->
                    layoutResult.value?.let { layoutResult ->
                        // Adjust the position to account for padding
                        val adjustedPos =
                            pos.copy(x = pos.x - 43.dp.toPx(), y = pos.y - 24.dp.toPx())
                        val offset = layoutResult.getOffsetForPosition(adjustedPos)
                        annotatedString.getStringAnnotations(
                            tag = "TERMS_AND_CONDITIONS",
                            start = offset,
                            end = offset + 1
                        ).firstOrNull()?.let {
                            webViewController.open(TERMS_AND_SERVICES_URL)
                        }
                        annotatedString.getStringAnnotations(
                            tag = "PRIVACY_POLICY",
                            start = offset,
                            end = offset + 1
                        ).firstOrNull()?.let {
                            webViewController.open(PRIVACY_POLICY_URL)
                        }
                    }
                }
            }
            .padding(horizontal = 43.dp, vertical = 24.dp),
        text = annotatedString,
        onTextLayout = {
            layoutResult.value = it
        }
    )
}

@Composable
private fun createAnnotatedString() = buildAnnotatedString {
    val fullText = stringResource(
        R.string.by_creating_account
    )
    val termsAndConditionsText = stringResource(id = R.string.terms_and_conditions)
    val privacyPolicyText = stringResource(id = R.string.privacy_policy)

    val termsAndConditionsStartIndex = fullText.indexOf(termsAndConditionsText)
    val termsAndConditionsEndIndex = termsAndConditionsStartIndex + termsAndConditionsText.length
    val privacyPolicyStartIndex = fullText.indexOf(privacyPolicyText)
    val privacyPolicyEndIndex = privacyPolicyStartIndex + privacyPolicyText.length

    append(fullText)

    addStyle(
        style = SpanStyle(
            color = AlgoKitTheme.colors.linkPrimary
        ),
        start = termsAndConditionsStartIndex,
        end = termsAndConditionsEndIndex
    )
    addStringAnnotation(
        tag = "TERMS_AND_CONDITIONS",
        annotation = TERMS_AND_SERVICES_URL,
        start = termsAndConditionsStartIndex,
        end = termsAndConditionsEndIndex
    )

    addStyle(
        style = SpanStyle(
            color = AlgoKitTheme.colors.linkPrimary
        ),
        start = privacyPolicyStartIndex,
        end = privacyPolicyEndIndex
    )
    addStringAnnotation(
        tag = "PRIVACY_POLICY",
        annotation = PRIVACY_POLICY_URL,
        start = privacyPolicyStartIndex,
        end = privacyPolicyEndIndex
    )
}

@Composable
private fun LoadingState() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}
