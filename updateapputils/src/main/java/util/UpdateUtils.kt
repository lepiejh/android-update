package util

import com.ved.framework.utils.SPUtils

object UpdateUtils {
    fun getUpDateTime(): Boolean {
        SPUtils.getInstance("update_sp").get("update_time", 0L).let {
            if (it == 0L) {
                SPUtils.getInstance("update_sp").put("update_time", System.currentTimeMillis())
                return true
            } else {
                if (it is Long) {
                    (System.currentTimeMillis() - it).let { t ->
                        val saveTime = 24 * 60 * 60 * 1000L
                        if (t > saveTime) {  // 24*60*60*1000L
                            SPUtils.getInstance("update_sp")
                                .put("update_time", System.currentTimeMillis())
                            return true
                        } else {
                            return false
                        }
                    }
                } else {
                    return false
                }
            }
        }
    }
}