package com.android.secretly.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.secretly.core.designsystem.R
import com.android.secretly.core.designsystem.icons.SecretlyIcons

/**
 * Secretly themed text field. Provides a basic text field without a bottom line stroke,
 * with a solid background, and no label or placeholder text.
 *
 * @param value The current text value of the text field.
 * @param onValueChange Callback invoked whenever the text value changes.
 * @param modifier Modifier to be applied to the text field.
 * @param enabled Controls the enabled state of the text field. When `false`, the text field will
 * not be clickable or focusable.
 * @param readOnly Controls whether the text field is read-only or editable.
 * @param keyboardOptions Software keyboard options that contains configuration such as
 * [KeyboardType] and [ImeAction].
 * @param visualTransformation The visual transformation to apply to the text.
 * @param backgroundColor The background color of the text field.
 */
@Composable
fun SecretlyTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    isError: Boolean = false,
    error: String = "",
    isPassword: Boolean = false,
    isEmail: Boolean = false,
    isNumber: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Done,
    onImeAction: () -> Unit = {},
    isList: Boolean = false,
    list: List<String> = emptyList(),
    onListItemClick: (String) -> Unit = {},
    isDate: Boolean = false,
    onDateSelected: (String, Long) -> Unit = { _, _ -> },
) {
    var passwordVisible by remember { mutableStateOf(false) }
    var isListOpen by remember { mutableStateOf(false) }
    var isDatePickerOpen by remember { mutableStateOf(false) }

    val focusManager = LocalFocusManager.current

    val dynamicKeyboardType =
        if (isNumber) KeyboardType.Number
        else if (isPassword) KeyboardType.Password
        else if (isEmail) KeyboardType.Email
        else keyboardType

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        TextFieldBox(
            modifier = modifier,
            value = value,
            onValueChange = onValueChange,
            placeholder = placeholder,
            isError = isError,
            isPassword = isPassword,
            passwordVisible = passwordVisible,
            onPasswordToggleClick = { passwordVisible = !passwordVisible },
            isList = isList,
            isListOpen = isListOpen,
            onListToggleClick = {
                isListOpen = !isListOpen
                if (isListOpen) {
                    focusManager.clearFocus()
                }
            },
            keyboardType = dynamicKeyboardType,
            imeAction = imeAction,
            onImeAction = {
                focusManager.clearFocus()
                onImeAction()
            },
            list = list,
            onListItemClick = { selectedItem ->
                onListItemClick(selectedItem)
                isListOpen = false
            },
            isDate = isDate,
            isDatePickerOpen = isDatePickerOpen,
            onDateSelected = onDateSelected,
            onDateDialogDismiss = { isDatePickerOpen = false },
            onDateIconClick = { isDatePickerOpen = true },
        )

        if (isError) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = error, color = MaterialTheme.colorScheme.error, fontSize = 10.sp)
        }
    }
}

@Composable
private fun TextFieldBox(
    modifier: Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    isError: Boolean,
    isPassword: Boolean,
    passwordVisible: Boolean,
    onPasswordToggleClick: () -> Unit,
    isList: Boolean,
    isListOpen: Boolean,
    onListToggleClick: () -> Unit,
    keyboardType: KeyboardType,
    imeAction: ImeAction,
    onImeAction: () -> Unit,
    list: List<String>,
    onListItemClick: (String) -> Unit,
    isDate: Boolean,
    isDatePickerOpen: Boolean,
    onDateSelected: (String, Long) -> Unit,
    onDateDialogDismiss: () -> Unit,
    onDateIconClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .border(
                1.dp,
                MaterialTheme.colorScheme.onSecondaryContainer,
                RoundedCornerShape(10.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            isError = isError,
            colors = TextFieldDefaults.colors().copy(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent
            ),
            readOnly = isList || isDate,
            visualTransformation = if (isPassword && !passwordVisible) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            },
            placeholder = {
                Text(text = placeholder)
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = keyboardType,
                imeAction = imeAction
            ),
            keyboardActions = KeyboardActions(onDone = { onImeAction() }),
            trailingIcon = {
                if (isPassword) {
                    PasswordToggleIcon(passwordVisible, onPasswordToggleClick)
                } else if (isList) {
                    ListToggleIcon(isListOpen, onListToggleClick)
                } else if (isDate) {
                    DatePickerIcon(onDateIconClick)
                }
            }
        )
    }

    if (isDatePickerOpen) {
        DateDialog(
            onDateSelected = onDateSelected,
            onDismiss = onDateDialogDismiss
        )
    }

    if (isListOpen) {
        DropdownList(
            items = list,
            onListItemClick = onListItemClick
        )
    }
}

@Composable
private fun PasswordToggleIcon(
    passwordVisible: Boolean,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick
    ) {
        val passwordIcon =
            if (passwordVisible) SecretlyIcons.NotVisible
            else SecretlyIcons.Visible
        Icon(
            imageVector = passwordIcon,
            contentDescription = null
        )
    }
}

@Composable
private fun ListToggleIcon(
    isListOpen: Boolean,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick
    ) {
        Icon(
            imageVector = if (isListOpen) SecretlyIcons.ArrowUp else SecretlyIcons.ArrowDown,
            contentDescription = null
        )
    }
}

@Composable
private fun DatePickerIcon(onClick: () -> Unit) {
    IconButton(
        onClick = onClick
    ) {
        Icon(
            imageVector = SecretlyIcons.Date,
            contentDescription = null
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DateDialog(onDateSelected: (String, Long) -> Unit, onDismiss: () -> Unit) {
    var selectedDate by remember { mutableStateOf("") }
    val dateState = rememberDatePickerState(
        initialSelectedDateMillis = System.currentTimeMillis(),
        initialDisplayMode = DisplayMode.Picker
    )

    selectedDate =
        dateState.selectedDateMillis?.let { DateFormatter.formatDate(it) }.toString()
    DatePickerDialog(
        modifier = Modifier.padding(16.dp),
        onDismissRequest = { onDismiss() },
        confirmButton = {
            Button(onClick = {
                onDateSelected(selectedDate, dateState.selectedDateMillis ?: 0)
                onDismiss()
            }) {
                Text(text = stringResource(id = R.string.ok))
            }
        }) {
        DatePicker(state = dateState)
    }
}

@Composable
private fun DropdownList(
    items: List<String>,
    onListItemClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
    ) {
        items.forEachIndexed { index, item ->
            Text(
                text = item,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onListItemClick(item) }
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            )
            if (index == 0) {
                HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
            }
        }
    }
}




















