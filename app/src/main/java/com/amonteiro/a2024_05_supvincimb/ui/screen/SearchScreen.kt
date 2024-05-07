package com.amonteiro.a2024_05_supvincimb.ui.screen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amonteiro.a2024_05_supvincimb.MainViewModel
import com.amonteiro.a2024_05_supvincimb.ui.theme._2024_05_supvincimBTheme

@Composable
fun SearchScreen(mainViewModel: MainViewModel) {

    Column {
        println("SearchScreen()")
        Text(text = "Text1",fontSize = 20.sp)
        Spacer(Modifier.size(8.dp))
        Text(text = "Text2",fontSize = 14.sp)
        repeat(mainViewModel.pictureList.size) {
            PictureRowItem(mainViewModel.pictureList[it].title)
        }

    }
}

@Composable
fun PictureRowItem(text:String, color:Color = Color.Blue) {
    Text(text = text,fontSize = 20.sp, color = color)
}

@Preview(showBackground = true, showSystemUi = true)
@Preview(showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SearchScreenPreview() {
    //Il faut remplacer NomVotreAppliTheme par le thème de votre application
    //Utilisé par exemple dans MainActivity.kt sous setContent {...}
    _2024_05_supvincimBTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {

            val viewModel = MainViewModel()
            viewModel.loadFakeData()
            SearchScreen(MainViewModel())
        }
    }
}
