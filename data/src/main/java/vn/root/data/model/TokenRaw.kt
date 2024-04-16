package vn.root.data.model

import androidx.room.Entity
import vn.root.domain.model.BaseModel
import vn.root.domain.model.TokenModel

@Entity
data class TokenRaw(
    val token: String? = "", val refreshToken: String? = ""
) : BaseRaw() {

    override fun raw2Model(): BaseModel = TokenModel(
        token = token, refreshToken = refreshToken
    )
}