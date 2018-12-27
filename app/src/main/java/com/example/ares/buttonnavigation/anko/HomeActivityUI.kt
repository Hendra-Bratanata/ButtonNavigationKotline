package com.example.ares.buttonnavigation.anko

import android.annotation.SuppressLint
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.LinearLayout
import com.example.ares.buttonnavigation.R
import org.jetbrains.anko.*
import org.jetbrains.anko.design.bottomNavigationView

class HomeActivityUI<T> : AnkoComponent<T> {
	@SuppressLint("ResourceType")
	override fun createView(ui: AnkoContext<T>): View {
		return with(ui) {
			relativeLayout {

				frameLayout {
					id = R.id.main_container
				}.lparams(width = matchParent, height = matchParent) {
					above(R.id.bottom_layout)
				}
				linearLayout {
					id = R.id.bottom_layout
					orientation = LinearLayout.VERTICAL
					view {
						backgroundResource = R.drawable.shadow
					}.lparams(width = matchParent, height = dip(4))
					bottomNavigationView {
						id = R.id.bottom_navigation
						itemIconTintList = ContextCompat.getColorStateList(ctx,R.drawable.nav_item_color_state)
						itemTextColor = ContextCompat.getColorStateList(ctx,R.drawable.nav_item_color_state)
						inflateMenu(R.menu.bottom_navigation_menu)
					}.lparams(width = matchParent)
				}.lparams(width = matchParent) {
					alignParentBottom()
				}
			}
		}
	}
}