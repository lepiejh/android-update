package view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import com.teprinciple.updateapputils.R

class WaybillHint14Dialog(context: Context) : Dialog(
    context, R.style.CustomDialog
), View.OnClickListener {
    private var content: String? = null
    private var cancel: String? = null
    private lateinit var onOkClick : (view : View?) -> Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val attr = window!!.attributes
        attr.height = ViewGroup.LayoutParams.WRAP_CONTENT
        attr.width = ViewGroup.LayoutParams.WRAP_CONTENT
        attr.gravity = Gravity.CENTER //设置dialog 在布局中的位置
        setContentView(R.layout.way_bill_hint14_layout)
        //按空白处不能取消动画
        setCanceledOnTouchOutside(false)
        initView()
    }

    private fun initView() {
        val cannel_tv = findViewById<ImageView>(R.id.cannel_tv)
        cannel_tv.setOnClickListener(this)
        findViewById<AppCompatTextView>(R.id.tv_01).text = content
        val tv_c = findViewById<AppCompatTextView>(R.id.tv_c)
        if (cancel?.isNotEmpty() == true){
            tv_c.text = cancel
        }
        tv_c.setOnClickListener {
            dismiss()
        }
        findViewById<AppCompatTextView>(R.id.tv_p).setOnClickListener {
            dismiss()
            onOkClick.invoke(it)
        }
    }

    fun setContent(content: String?): WaybillHint14Dialog {
        this.content = content
        return this
    }

    fun setCancel(cancel: String?): WaybillHint14Dialog {
        this.cancel = cancel
        return this
    }

    fun setOnOkClick(onOkClick : (view : View?) -> Unit) : WaybillHint14Dialog{
        this.onOkClick = onOkClick
        return this
    }

    override fun onClick(v: View) {
        if (v.id == R.id.cannel_tv) {
            dismiss()
        }
    }
}