package vn.root.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import vn.core.data.model.BaseRaw
import vn.core.domain.BaseModel
import vn.root.domain.model.ItemModel

@Entity
data class ItemRaw(
    @PrimaryKey val id: Int = 0,
    val name: String? = "",
) : BaseRaw() {

    override fun raw2Model(): BaseModel {
        return ItemModel(
            id = id,
            name = name ?: "",
        )
    }
}
