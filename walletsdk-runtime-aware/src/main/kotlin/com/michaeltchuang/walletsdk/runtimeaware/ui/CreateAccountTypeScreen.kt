package com.michaeltchuang.walletsdk.runtimeaware.ui

import android.util.Log
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.michaeltchuang.walletsdk.runtimeaware.R
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.theme.AlgoKitTheme
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.theme.AlgoKitTheme.typography
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.widget.GroupChoiceWidget
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.widget.icon.PeraIcon
import com.michaeltchuang.walletsdk.runtimeaware.ui.viewmodel.CreateAccountTypeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

const val TERMS_AND_SERVICES_URL = "https://perawallet.app/terms-and-services/"
const val PRIVACY_POLICY_URL = "https://perawallet.app/privacy-policy/"

@Composable
fun CreateAccountTypeScreen(navController: NavHostController) {

    val viewModel: CreateAccountTypeViewModel = koinViewModel()
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect {
            when (it) {
                is CreateAccountTypeViewModel.ViewEvent.Algo25AccountCreated -> {
                    navController.currentBackStackEntry
                        ?.savedStateHandle
                        ?.set("accountCreation", it.accountCreation)
                    navController.navigate(OnBoardingScreens.CREATE_ACCOUNT_NAME.name)
                    Log.d("CreateAccountTypeScreen", it.accountCreation.address)
                }

                is CreateAccountTypeViewModel.ViewEvent.Error -> {
                    Log.d("CreateAccountTypeScreen", it.message)
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxHeight(.9f)
            .fillMaxWidth()
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
        CreateAlgo25AccountWidget(viewModel, scope)
        ImportAlgo25AccountWidget()
        WatchAddressWidget()
        Spacer(modifier = Modifier.weight(1f))
        TermsAndPrivacy()
    }
}

@Composable
private fun CreateAlgo25AccountWidget(
    viewModel: CreateAccountTypeViewModel,
    scope: CoroutineScope,
) {
    GroupChoiceWidget(
        title = stringResource(id = R.string.create_a_new_account),
        description = stringResource(id = R.string.create_a_new_algorand_account_with),
        icon = ImageVector.vectorResource(R.drawable.ic_wallet),
        iconContentDescription = stringResource(id = R.string.create_a_new_algorand_account_with),
        onClick = {
            scope.launch {
                viewModel.processIntent(CreateAccountTypeViewModel.CreateAccountIntent.CreateAlgo25Account)
            }
        }
    )
}

@Composable
private fun ImportAlgo25AccountWidget() {
    GroupChoiceWidget(
        title = stringResource(id = R.string.import_an_account),
        description = stringResource(id = R.string.import_an_existing),
        iconContentDescription = stringResource(id = R.string.import_an_existing),
        icon = ImageVector.vectorResource(R.drawable.ic_key),
        onClick = {}
    )
}

@Composable
private fun WatchAddressWidget() {
    GroupChoiceWidget(
        title = stringResource(id = R.string.watch_an_address),
        description = stringResource(id = R.string.monitor_an_algorand_address),
        iconContentDescription = stringResource(id = R.string.monitor_an_algorand_address),
        icon = ImageVector.vectorResource(R.drawable.ic_eye),
        onClick = {}
    )
}

@Composable
fun TermsAndPrivacy(modifier: Modifier = Modifier) {
    val layoutResult = remember {
        mutableStateOf<TextLayoutResult?>(null)
    }
    val annotatedString = createAnnotatedString()

    Text(
        style = typography.footnote.sans,
        color = AlgoKitTheme.colors.textGray,
        modifier = modifier
            .pointerInput(Unit) {
                detectTapGestures { pos ->
                    layoutResult.value?.let { layoutResult ->
                        val offset = layoutResult.getOffsetForPosition(pos)
                        annotatedString.getStringAnnotations(
                            tag = "TERMS_AND_CONDITIONS",
                            start = offset,
                            end = offset
                        ).firstOrNull()?.let {

                        }
                        annotatedString.getStringAnnotations(
                            tag = "PRIVACY_POLICY",
                            start = offset,
                            end = offset
                        ).firstOrNull()?.let {

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
