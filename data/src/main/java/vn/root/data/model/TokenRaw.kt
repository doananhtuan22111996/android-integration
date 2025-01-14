package vn.root.data.model

import androidx.room.Entity
import vn.core.data.model.BaseRaw
import vn.core.domain.BaseModel
import vn.root.domain.model.TokenModel

@Entity
data class TokenRaw(
    val token: String? = "",
    val refreshToken: String? = "",
) : BaseRaw() {

    override fun raw2Model(): BaseModel = TokenModel(
        token = token,
        refreshToken = refreshToken,
    )
}
