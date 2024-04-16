package vn.root.data.model

import vn.root.domain.model.BaseModel

abstract class BaseRaw {
    abstract fun raw2Model(): BaseModel?
}
