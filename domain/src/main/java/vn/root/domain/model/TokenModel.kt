package vn.root.domain.model

import vn.core.domain.BaseModel

data class TokenModel(
    val token: String? = "",
    val refreshToken: String? = "",
) : BaseModel()
