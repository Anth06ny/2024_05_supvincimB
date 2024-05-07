package com.amonteiro.a2024_05_supvincimb.ui.screen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amonteiro.a2024_05_supvincimb.MainViewModel
import com.amonteiro.a2024_05_supvincimb.PictureBean
import com.amonteiro.a2024_05_supvincimb.R
import com.amonteiro.a2024_05_supvincimb.ui.theme._2024_05_supvincimBTheme
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder

@Composable
fun SearchScreen(mainViewModel: MainViewModel) {

    Column(modifier = Modifier.padding(4.dp)) {

        var searchText = remember {
            mutableStateOf("")
        }

        SearchBar(searchText =  searchText)

        Spacer(Modifier.size(8.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.weight(1f)
        ) {

//            val filterList = mainViewModel.pictureList2.value.filter { it.title.contains(searchText.value, true) }

            val filterList = mainViewModel.pictureList.filter { it.title.contains(searchText.value, true) }

            items(filterList.size) {
                PictureRowItem(data = filterList[it])
            }
        }


        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Button(
                onClick = { searchText.value = "" },
                contentPadding = ButtonDefaults.ButtonWithIconContentPadding
            ) {
                Icon(
                    Icons.Filled.Clear,
                    contentDescription = "Localized description",
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text("Clear filter")
            }



            Button(
                onClick = { mainViewModel.loadRealData() },
                contentPadding = ButtonDefaults.ButtonWithIconContentPadding
            ) {
                Icon(
                    Icons.Filled.Send,
                    contentDescription = "Localized description",
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text(stringResource(id = R.string.bt_load_data))
            }

        }
    }
}

@Composable
fun SearchBar(modifier: Modifier = Modifier, searchText : MutableState<String>) {

    TextField(
        value = searchText.value, //Valeur affichée
        onValueChange = { searchText.value = it}, //Nouveau texte entrée
        leadingIcon = { //Image d'icone
            Icon(
                imageVector = Icons.Default.Search,
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = null
            )
        },
        singleLine = true,
        label = { Text("Enter text") }, //Texte d'aide qui se déplace
        placeholder = { //Texte d'aide qui disparait
            //Pour aller le chercher dans string.xml
            //Text(stringResource(R.string.placeholder_search))
            //En dur
            Text("Recherche")
        },
        //Comment le composant doit se placer
        modifier = modifier
            .fillMaxWidth() // Prend toute la largeur
            .heightIn(min = 56.dp) //Hauteur minimum
    )
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PictureRowItem(modifier: Modifier = Modifier, data: PictureBean) {

    var expended by remember {        mutableStateOf(false)    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.tertiaryContainer)
    ) {

//Permission Internet nécessaire
        GlideImage(
            model = data.url,
            //Pour aller le chercher dans string.xml
            //contentDescription = getString(R.string.picture_of_cat),
            //En dur
            contentDescription = "une photo de chat",
            // Image d'attente. Permet également de voir l'emplacement de l'image dans la Preview
            loading = placeholder(R.mipmap.ic_launcher_round),
            // Image d'échec de chargement
            failure = placeholder(R.mipmap.ic_launcher),
            contentScale = ContentScale.Fit,
            //même autres champs qu'une Image classique
            modifier = Modifier
                .heightIn(max = 100.dp) //Sans hauteur il prendra tous l'écran
                .widthIn(max = 100.dp)
        )

        Column(modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clickable { expended = !expended }
        ) {
            Text(text = data.title, fontSize = 20.sp)
            Text(
                text = if(expended) data.longText else (data.longText.take(20) + "..."),
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.animateContentSize()
            )
        }
    }


}

@Preview(showBackground = true, showSystemUi = true)
@Preview(showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_YES, locale = "fr")
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
