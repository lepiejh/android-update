package util

import android.view.View
import android.widget.TextView
import com.teprinciple.updateapputils.R
import listener.OnBtnClickListener
import listener.OnInitUiListener
import listener.OnUpdateListener
import listener.UpdateDownloadListener
import model.UiConfig
import model.UpdateConfig
import update.UpdateAppUtils.getInstance

class XUpdate private constructor() {
    fun update(
        apkUrl: String?,
        updateTitle: String?,
        updateContent: String?,
        isForce: Boolean,
        customLayoutId: Int,
        notifyImgRes: Int,
        alwaysShowDownLoadDialog: Boolean,
        cancelClick: OnBtnClickListener?,
        submitClick: OnBtnClickListener?,
        updateListener: OnUpdateListener?,
        downloadListener: UpdateDownloadListener?
    ) {
        val updateConfig = UpdateConfig()
        updateConfig.checkWifi = true
        updateConfig.needCheckMd5 = true
        updateConfig.force = isForce
        updateConfig.alwaysShowDownLoadDialog = alwaysShowDownLoadDialog
        if (notifyImgRes != -1) {
            updateConfig.notifyImgRes = notifyImgRes
        }
        val updateAppUtils = getInstance().apkUrl(apkUrl!!).updateTitle(
            updateTitle!!
        ).updateContent(updateContent!!).updateConfig(updateConfig)
        val uiConfig = UiConfig()
        if (customLayoutId == -1) {
            uiConfig.uiType = "PLENTIFUL"
        } else {
            uiConfig.uiType = "CUSTOM"
            uiConfig.customLayoutId = customLayoutId
            updateAppUtils.setOnInitUiListener(object : OnInitUiListener {
                override fun onInitUpdateUi(
                    view: View?,
                    updateConfig: UpdateConfig,
                    uiConfig: UiConfig
                ) {
                    if (view != null) {
                        val title = view.findViewById<View>(R.id.tv_update_title) as TextView
                        val tvUpdateContent =
                            view.findViewById<View>(R.id.tv_update_content) as TextView
                        title.text = updateTitle
                        tvUpdateContent.text = updateContent
                        view.findViewById<View>(R.id.btn_update_cancel).visibility =
                            if (isForce) View.GONE else View.VISIBLE
                        updateListener?.onInitUpdateUi(view, updateConfig, uiConfig)
                    }
                }
            })
        }
        updateAppUtils.uiConfig(uiConfig)
        if (cancelClick != null) {
            updateAppUtils.setCancelBtnClickListener(cancelClick)
        }
        if (submitClick != null) {
            updateAppUtils.setUpdateBtnClickListener(submitClick)
        }
        if (downloadListener != null) {
            updateAppUtils.setUpdateDownloadListener(downloadListener)
        }
        updateAppUtils.update()
    }

    private object Inner {
        val instance = XUpdate()
    }

    companion object {
        fun newInstance(): XUpdate {
            return Inner.instance
        }
    }
}