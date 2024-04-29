package com.example.almatartask.shopping.presentation.add_edit_shopping

import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.almatartask.shopping.presentation.add_edit_shopping.component.TransparentHintTextField
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun AddEditShoppingScreen(
    navController: NavController,
    viewModel: AddEditShoppingViewModel = hiltViewModel(),
){

    val titleState = viewModel.shoppingTitle.value
    val contentState = viewModel.shoppingContent.value
    val quantityState = viewModel.shoppingQuantity.value
    val scope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }



    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is AddEditShoppingViewModel.UiEvent.ShowSnackBar -> {
                    scope.launch {
                        snackBarHostState.showSnackbar(
                            message = event.message
                        )
                    }

                }
                is AddEditShoppingViewModel.UiEvent.SaveShopping -> {
                    navController.navigateUp()
                }
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(AddEditShoppingEvent.SaveShopping)
                },
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Save Shopping")
            }
        }
    ){ padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color.Cyan)
                .padding(16.dp)

        ) {
            Spacer(modifier = Modifier.height(24.dp))

            TransparentHintTextField(
                text = titleState.text,
                hint = titleState.hint,
                onValueChange = { viewModel.onEvent(AddEditShoppingEvent.EnteredTitle(it)) },
                onFocusChange = { viewModel.onEvent(AddEditShoppingEvent.ChangeTitleFocus(it)) },
                isHintVisible = titleState.isHintVisible,
                singleLine = true,
                textStyle = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(24.dp))

            TransparentHintTextField(
                text = quantityState.text,
                hint = quantityState.hint,
                onValueChange = { viewModel.onEvent(AddEditShoppingEvent.EnteredQuantity(it)) },
                onFocusChange = { viewModel.onEvent(AddEditShoppingEvent.ChangeQuantityFocus(it)) },
                isHintVisible = quantityState.isHintVisible,
                singleLine = true,
                keyboardType = KeyboardType.Number,
                textStyle = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(24.dp))

            TransparentHintTextField(
                text = contentState.text,
                hint = contentState.hint,
                onValueChange = { viewModel.onEvent(AddEditShoppingEvent.EnteredContent(it)) },
                onFocusChange = { viewModel.onEvent(AddEditShoppingEvent.ChangeContentFocus(it)) },
                isHintVisible = contentState.isHintVisible,
                textStyle = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.height(60.dp)
            )
        }
    }

}