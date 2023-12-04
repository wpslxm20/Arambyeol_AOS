package com.my_app.arambyeol.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.my_app.arambyeol.R

object BannerView {
    @Composable
    fun main() {
        banner()
    }

    @Composable
    private fun banner() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val adId = stringResource(id = R.string.sample_banner_id)
            val adRequest = AdRequest.Builder().build()
            AndroidView(
                modifier = Modifier
                    .fillMaxWidth(),
                factory = { context ->
                    AdView(context).apply {
                        setAdSize(AdSize.BANNER)
                        adUnitId = adId
                        loadAd(adRequest)
                    }
                },
                update = { adView ->
                    adView.loadAd(adRequest)
                }
            )
        }

    }
}