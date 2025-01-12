package vn.root.app.pages.materialKit

data class MaterialKit(
    val id: Int,
    val header: String,
    val subHeader: String,
    val action: () -> Unit,
)
