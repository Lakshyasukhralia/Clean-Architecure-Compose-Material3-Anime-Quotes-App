package com.sukhralia.animequotes.presentation

import android.content.ActivityNotFoundException
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sukhralia.animequotes.domain.model.Quote

@Preview
@Composable
fun QuotesListItem(
    modifier: Modifier = Modifier,
    quote: Quote = Quote(
        "Eren",
        "If we kill all our enemies beyond the sea.. Will we finally be free..?"
    )
) {
    val context = LocalContext.current
        Box(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .border(4.dp, Color.Black, shape = RoundedCornerShape(10.dp))
                .background(color = Color.White, shape = RoundedCornerShape(10.dp))

        ) {
            Row {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .weight(1f)
                ) {
                    Text(text = quote.quote, fontSize = 18.sp)
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = "~ ${quote.character}",
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                        modifier = modifier
                            .align(Alignment.End)
                            .padding(2.dp)
                    )
                }
                Icon(
                    tint = Color(0xFF000000),
                    imageVector = Icons.Default.Share,
                    contentDescription = "share",
                    modifier = Modifier
                        .padding(horizontal = 10.dp, vertical = 20.dp)
                        .size(25.dp)
                        .clickable {
                            val sendIntent = Intent().apply {
                                action = Intent.ACTION_SEND
                                putExtra(Intent.EXTRA_TEXT, "${quote.quote}\n~${quote.character}")
                                type = "text/plain"
                            }
                            try {
                                context.startActivity(sendIntent)
                            } catch (e: ActivityNotFoundException) {
                                Toast
                                    .makeText(context, "Something went wrong!", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        },
                )
            }
    }

}

