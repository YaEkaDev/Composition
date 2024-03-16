package com.example.composition.presentation

import android.content.Context
import android.content.res.ColorStateList
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.composition.R
import com.example.composition.domain.entity.GameResult

interface OnOptionClickListener{
    fun onOptionClick(option: Int)
}

@BindingAdapter("requiredAnswers")
fun bindRequiredAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_score),
        count
    )
}

@BindingAdapter("scoreAnswers")
fun bindScoreAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.score_answers),
        count
    )
}

@BindingAdapter("requiredPercentage")
fun bindRequiredPercentage(textView: TextView, count: Int) {
    textView.text= String.format(
        textView.context.getString(R.string.required_percentage),
        count
    )
}

@BindingAdapter("scorePercentage")
fun bindScorePercentage(textView: TextView, gameResult: GameResult) {
    textView.text = String.format(
        textView.context.getString(R.string.score_percentage),
        getPercentOfRightAnswers(gameResult)
    )
}

@BindingAdapter("emojiResult")
fun bindEmojiResult(imageView: ImageView, winner: Boolean) {
    imageView.setImageResource(getSmileId(winner))
}

@BindingAdapter("percentOfRightAnswers")
fun bindPercentOfRightAnswers(progressBar: ProgressBar, count: Int) {
    progressBar.setProgress(count, true)
}

@BindingAdapter("enoughPercentOfRightAnswers")
fun bindEnoughPercentOfRightAnswers(progressBar: ProgressBar, enough: Boolean) {
    val color = getColorByState(enough, progressBar.context)
    progressBar.progressTintList = ColorStateList.valueOf(color)
}

@BindingAdapter("minPercent")
fun bindMinPercent(progressBar: ProgressBar, count: Int) {
    progressBar.secondaryProgress = count
}

@BindingAdapter("enoughCountOfrightAnswers")
fun bindEnoughCountOfrightAnswers(textView: TextView, enough: Boolean) {
    val color = getColorByState(enough, textView.context)
    textView.setTextColor(color)
}

@BindingAdapter("numberAsText")
fun bindNumberAsText(textView: TextView, number: Int){
    textView.text = number.toString()
}

@BindingAdapter("onOptionClickListener")
fun bindOnOptionClickListener(textView: TextView, clickListener: OnOptionClickListener){
    textView.setOnClickListener {
        clickListener.onOptionClick(textView.text.toString().toInt())
    }

}


private fun getSmileId(winner: Boolean): Int {
    return if (winner){
        R.drawable.ic_smile
    } else {
        R.drawable.ic_sad
    }
}

private fun getPercentOfRightAnswers(gameResult: GameResult) = with(gameResult) {
    if (countOfQuestions == 0) {
        0
    } else {
        ((countOfRightAnswers / countOfQuestions.toDouble()) * 100).toInt()
    }
}

private fun getColorByState(goodState: Boolean, context: Context): Int {
    val colorResId = if (goodState) {
        android.R.color.holo_green_light
    } else {
        android.R.color.holo_red_light
    }
    return ContextCompat.getColor(context, colorResId)
}
