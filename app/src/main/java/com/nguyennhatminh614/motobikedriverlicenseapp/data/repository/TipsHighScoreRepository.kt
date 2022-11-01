package com.nguyennhatminh614.motobikedriverlicenseapp.data.repository

import com.nguyennhatminh614.motobikedriverlicenseapp.data.model.TipsHighScore
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.interfaces.IResponseListener

class TipsHighScoreRepository(
    private val remote: ITipsHighScoreDataSource.Remote,
) : ITipsHighScoreDataSource.Remote {

    override suspend fun callTipsHighScoreData(listener: IResponseListener<MutableList<TipsHighScore>>) =
        remote.callTipsHighScoreData(listener)
}
