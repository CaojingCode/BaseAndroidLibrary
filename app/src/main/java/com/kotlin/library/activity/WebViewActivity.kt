package com.kotlin.library.activity

import android.os.Bundle
import com.kotlin.baselibrary.BaseWebActivity
import com.kotlin.library.R


/**
 * Created by Caojing on 2019/9/3.
 *  你不是一个人在战斗
 */
class WebViewActivity : BaseWebActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
//        AgentWebConfig.syncCookie(url,"ERP-Test=BD429CD317369A5C0F1D8B8730E5491B15462CE5180D532D32F9A86E81D8DECBB1AC67DF8557BE27CF486920E846485C59757E55CD233F53BAD631E4BB7A5C368A2150D7B9E2B07DA1223D373281C25AD8C0A8C07D737DD7A9A4C381E2A2F5E7FAE2958ADE9CD706A60A2E227B89EE164B2A7BDDA7B10F8D6647617A61053E815B2360376693095BDB6C02F07AAAE885DC482FC4F845FB1D513059C4666DCE54")
    }

    override fun getUrl(): String {
        return "http://m.jjw.com"
//        return "http://wf.t.jjw.com:8901/Flowapp/Home"
    }


}