package com.example.ares.buttonnavigation.ankoUI

import android.graphics.Color
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.text.TextUtils
import android.view.View
import android.widget.LinearLayout
import com.example.ares.buttonnavigation.R
import com.example.ares.buttonnavigation.R.id.*
import org.jetbrains.anko.*

/**
 * Generate with Plugin
 * @plugin Kotlin Anko Converter For Xml
 * @version 1.3.4
 */
class RecycleViewUI<T> : AnkoComponent<T> {
	val TSize : Float = 16f

	override fun createView(ui: AnkoContext<T>): View {
		return with(ui){
			linearLayout {
				linearLayout {
					orientation = LinearLayout.VERTICAL
					padding = dip(5)


					textView {
						id = tglIndo
						textSize = TSize
						textColor = ContextCompat.getColor(context,R.color.colorPrimary)
						textAlignment = View.TEXT_ALIGNMENT_CENTER
					}.lparams(width = matchParent) {
						bottomMargin = dip(5)
					}
					relativeLayout {

						textView {
							id = tvHome
							textSize = TSize
							setSingleLine()
							ellipsize= TextUtils.TruncateAt.END

						}.lparams{width = dip(100)
							marginStart = dip (15)
						}

						textView {
							id = tvVS
							text = resources.getString(R.string.txVS)
							textSize = 12f
							setTypeface(typeface, Typeface.BOLD)
						}.lparams {
							centerHorizontally()
						}
						textView {
							id = tvHomeScore
							setTypeface(typeface, Typeface.BOLD)
							textSize = 16f
							textColor = Color.BLACK
						}.lparams {
							marginEnd = dip(8)
							leftOf(tvVS)
						}
						textView {
							id = tvAwayScore
							textSize = TSize
							textColor = Color.BLACK
							setTypeface(typeface, Typeface.BOLD)
						}.lparams {
							marginStart = dip(8)
							rightOf(tvVS)
						}
						textView {
							id =tvAway
							setSingleLine()
							ellipsize= TextUtils.TruncateAt.END
							textSize = TSize
						}.lparams {
							width = dip(100)
							marginEnd = dip(8)
							marginStart = dip(15)
							alignParentEnd()
						}
					}.lparams(width = matchParent){
						backgroundColor = Color.WHITE
						bottomMargin = dip (15)
					}
					view {
						backgroundColor = Color.BLUE

					}.lparams(matchParent,dip(1)){
						bottomMargin = dip(1)
					}
					view {
						backgroundColor = Color.BLUE

					}.lparams(matchParent,dip(2))
				}.lparams{
                    padding = dip (8)


                }
			}
			}

	}
}
