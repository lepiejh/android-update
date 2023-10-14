package listener

import android.view.View
import model.UpdateConfig
import model.UiConfig

interface OnUpdateListener {
    fun onInitUpdateUi(var1: View?, var2: UpdateConfig?, var3: UiConfig?)
}