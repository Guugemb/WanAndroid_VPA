package com.example.wanandroid_vpa.discover.activity

import android.app.Activity
import android.graphics.Bitmap
import android.os.Bundle
import com.example.wanandroid_vpa.R
import kotlinx.android.synthetic.main.layout_activity_qrcode_test.*

class QRCodeTestActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_activity_qrcode_test)
        val bitmap = intent.getParcelableExtra<Bitmap>("QR_CODE")
        val intArray = intent.getIntArrayExtra("ORIGINAL_VIEW_INFO")
        ivQRCode.setOriginView(intArray,bitmap)
    }
}