package com.example.ares.buttonnavigation.anko

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.text.TextUtils
import android.view.View
import android.widget.LinearLayout
import com.example.ares.buttonnavigation.R
import com.example.ares.buttonnavigation.R.id.*
import org.jetbrains.anko.*


class DetailViewUi< T>: AnkoComponent<T> {
	@SuppressLint("SetTextI18n")
	override fun createView(ui: AnkoContext<T>): View {
		return with(ui){
			scrollView {
				id= R.id.scrollViewDetail
                padding = dip (10)
                linearLayout {

				orientation = LinearLayout.VERTICAL
				padding = dip(5)
					relativeLayout {

						imageView {
							id = R.id.imgHome
						}.lparams(width = dip(100), height = dip(80)) {

						}

						imageView {
							id = imgAway
						}.lparams(width = dip(100), height = dip(80)) {

							alignParentEnd()
						}

						textView {
							id = R.id.tvVS
							text = resources.getString(R.string.txVS)
							textColor = Color.BLACK
							textSize = 20f
							setTypeface(typeface, Typeface.BOLD_ITALIC)
						}.lparams {
							centerHorizontally()
							centerVertically()
						}

						textView {
							id = tvTanggal
							textColor = ContextCompat.getColor(context,R.color.colorPrimary)
							textSize = 12f

						}.lparams{ centerHorizontally() }

						textView {
							id = tvHomeScoreDetail
							textSize = 40f
							textColor = Color.BLACK
							setTypeface(typeface, Typeface.BOLD)
						}.lparams {
							leftOf(tvVS)
							centerVertically()
							marginEnd = dip(15)
						}

						textView {
							id = AwayScoredetail
							textSize = 40f
							textColor = Color.BLACK
							setTypeface(typeface, Typeface.BOLD)
						}.lparams {
							rightOf(tvVS)
							centerVertically()
							marginStart = dip(15)
						}

						textView{
							id = R.id.homeTitle
							setSingleLine()
							ellipsize = TextUtils.TruncateAt.END
							textSize = 16f
							textAlignment=View.TEXT_ALIGNMENT_CENTER
							setTypeface(typeface,Typeface.BOLD)
							textColor = ContextCompat.getColor(ctx,R.color.colorPrimary)
						}.lparams(dip (100), wrapContent){
							below(R.id.imgHome)
							alignStart(R.id.imgHome)
						}
						textView{
							id = R.id.awayTitle
							ellipsize = TextUtils.TruncateAt.END
							textSize = 16f
							textAlignment=View.TEXT_ALIGNMENT_CENTER
							setTypeface(typeface,Typeface.BOLD)
							textColor = ContextCompat.getColor(ctx,R.color.colorPrimary)
						}.lparams(dip(100), wrapContent){
							below(R.id.imgAway)
							alignStart(R.id.imgAway)

						}
						textView {
							id = R.id.homeFormation
							setSingleLine()
							textAlignment = View.TEXT_ALIGNMENT_CENTER
						}.lparams(dip (100), wrapContent){
							below(R.id.homeTitle)
							alignParentLeft()
						}
						textView {
							id = R.id.awayFormation
							setSingleLine()
							textAlignment = View.TEXT_ALIGNMENT_CENTER
						}.lparams(dip (100), wrapContent){
							below(R.id.awayTitle)
							alignParentRight()
						}

					}.lparams(width = matchParent)

				view{ backgroundColor= Color.GRAY

					}.lparams(width = matchParent, height = dip(1)){
							topMargin =  dip (5) }
				relativeLayout {
					id = R.id.rlGoals
					textView {
						text = context.getString(R.string.goals)
						textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
					}.lparams{centerHorizontally()}
					textView{
						id = R.id.tvGoalsHome

					}.lparams(dip(130), wrapContent)
					textView {
						id = R.id.tvGoalsAway
						textAlignment = View.TEXT_ALIGNMENT_TEXT_END

					}.lparams(dip(130), wrapContent
					){
						alignParentRight()
					}

				}.lparams(matchParent, wrapContent)

				relativeLayout {
					id = R.id.rlShot

					textView{
						id =R.id.homeShots
					}.lparams{
						alignParentLeft()

					}
					textView {
						id = R.id.awayShots

					}.lparams{
						alignParentRight()

					}
					textView{
						text = context.getString(R.string.shot)
						textColor = ContextCompat.getColor(ctx,R.color.colorPrimary)
					}.lparams {
						centerHorizontally()
					}
				}

				view{backgroundColor = Color.GRAY}.lparams(matchParent,2)
					textView {
						text = resources.getString(R.string.lineUps)
						textSize = 16f
						setTypeface(typeface,Typeface.BOLD)
						textAlignment = View.TEXT_ALIGNMENT_CENTER
				}

				relativeLayout {
					id = R.id.rlgoalsKiper

					textView {
						id = R.id.tvGoalKiper
						text = context.getString(R.string.Golkeeper)
						textColor =ContextCompat.getColor(ctx,R.color.colorPrimary)
					}.lparams{
						centerHorizontally()
					}

					textView {
						id = R.id.tvHomeKeeper
					}.lparams(dip(100), wrapContent){
						alignParentLeft()
					}
					textView {
						id = R.id.tvAwayKeeper
						textAlignment= View.TEXT_ALIGNMENT_TEXT_END
					}.lparams(dip(100), wrapContent){
						alignParentRight()
					}
				}.lparams(matchParent, wrapContent)

				view().lparams (matchParent,dip(5))

				relativeLayout {
					id = R.id.rlDefense

					textView {
						id = R.id.tvDefese
						text = context.getString(R.string.defense)
						textColor =ContextCompat.getColor(ctx,R.color.colorPrimary)
					}.lparams{
						centerHorizontally()
					}

					textView {
						id = R.id.tvHomeDefense

					}.lparams(dip(100), wrapContent){
						alignParentLeft()
					}
					textView {
						id = R.id.tvAwayDefense
						textAlignment= View.TEXT_ALIGNMENT_TEXT_END
					}.lparams(dip(100), wrapContent){
						alignParentRight()
					}
				}.lparams(matchParent, wrapContent)


				relativeLayout {
					id = R.id.rlMindField

					textView {
						id = R.id.tvMindField
						text = context.getString(R.string.mindFiead)
						textColor =ContextCompat.getColor(ctx,R.color.colorPrimary)
					}.lparams{
						centerHorizontally()
					}

					textView {
						id = R.id.tvHomeMindField

					}.lparams(dip(100), wrapContent){
						alignParentLeft()
					}
					textView {
						id = R.id.tvAwayMindField
						textAlignment= View.TEXT_ALIGNMENT_TEXT_END
					}.lparams(dip(100), wrapContent){
						alignParentRight()
					}
				}.lparams(matchParent, wrapContent)


				relativeLayout {
					id = R.id.rlForward

					textView {
						id = R.id.tvForward
						text = resources.getString(R.string.forward)
						textColor =ContextCompat.getColor(ctx,R.color.colorPrimary)
					}.lparams{
						centerHorizontally()
					}

					textView {
						id = R.id.tvHomeForward
					}.lparams(dip(100), wrapContent){
						alignParentLeft()
					}
					textView {
						id = R.id.tvAwayForward
						textAlignment= View.TEXT_ALIGNMENT_TEXT_END
					}.lparams(dip(100), wrapContent){
						alignParentRight()
					}
				}.lparams(matchParent, wrapContent)


				relativeLayout {
					id = R.id.rlSubstitutes

					textView {
						id = R.id.tvForward
						text = resources.getString(R.string.subtitusi)
						textColor =ContextCompat.getColor(ctx,R.color.colorPrimary)
					}.lparams{
						centerHorizontally()
					}

					textView {
						id = R.id.tvHomeSubstitutes
					}.lparams(dip(100), wrapContent){
						alignParentLeft()
					}
					textView {
						id = R.id.tvAwaySubstitutes
						textAlignment= View.TEXT_ALIGNMENT_TEXT_END
					}.lparams(dip(100), wrapContent){
						alignParentRight()
					}
				}.lparams(matchParent, wrapContent)

			} }




		}
	}
}
