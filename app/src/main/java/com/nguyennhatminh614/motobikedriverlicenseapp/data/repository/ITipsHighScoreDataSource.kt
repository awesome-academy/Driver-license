package com.nguyennhatminh614.motobikedriverlicenseapp.data.repository

import com.nguyennhatminh614.motobikedriverlicenseapp.data.model.TipsHighScore
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.interfaces.IResponseListener

interface ITipsHighScoreDataSource {
    interface Remote {
        suspend fun callTipsHighScoreData(listener: IResponseListener<MutableList<TipsHighScore>>)
    }
}
